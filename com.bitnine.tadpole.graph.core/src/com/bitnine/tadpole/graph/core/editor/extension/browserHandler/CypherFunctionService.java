/*******************************************************************************
 * Copyright 2016 hangum
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.bitnine.tadpole.graph.core.editor.extension.browserHandler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import net.bitnine.agensgraph.graph.Edge;
import net.bitnine.agensgraph.graph.Path;
import net.bitnine.agensgraph.graph.Vertex;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye.Metadata;
import com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye.RunkiEyeGraph;
import com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye.RurukiEdge;
import com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye.RurukiNode;
import com.bitnine.tadpole.graph.core.editor.extension.sigma.AgensUtils;
import com.bitnine.tadpole.graph.core.editor.extension.sigma.GEdge;
import com.bitnine.tadpole.graph.core.editor.extension.sigma.Node;
import com.bitnine.tadpole.graph.core.editor.extension.sigma.SigmaGraph;
import com.google.gson.Gson;
import com.hangum.tadpole.engine.manager.TadpoleSQLManager;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Cypher browser function
 * 
 * @author hangum
 *
 */
public class CypherFunctionService extends BrowserFunction {
	private static final Logger logger = Logger.getLogger(CypherFunctionService.class);
	private UserDBDAO userDB;

	public CypherFunctionService(Browser browser, UserDBDAO userDB, String name) {
		super(browser, name);
		this.userDB = userDB;
	}

	/**
	 * 그래프 표현을 위한 확장 브라우져에서 발생하는 이벤트를 수신한다.
	 */
	@Override
	public Object function(Object[] arguments) {

		int intActionId = NumberUtils.toInt(arguments[0].toString());
		if (logger.isDebugEnabled())
			logger.debug("==========>  CypherFunctionService called " + arguments[0] + " : " + arguments[1]);

		switch (intActionId) {
		case CypherEditorFunction.SAVE_OPTIONS:
			// 사용하지 않는 메소드. 그래프 브라우져에서의 설정값 같은것을 저장하여 사용하고자 하는 경우 사용한다.
			saveOptions((String) arguments[1]);
			break;
		case CypherEditorFunction.LOAD_DATA:
			// 더블클릭 이벤트가 발생할 경우 관련 노드와 엣지를 조회한다.
			return loadData((String) arguments[1]);
		default:
			logger.error("Undefined Action ID : " + intActionId);
			return null;
		}
		return null;
	}

	/**
	 * RurukiEye.js 라이브러리를 이용하여 그래프를 표시할때 사용한다.
	 * 
	 * @param nodeJsonString
	 * @return
	 */
	protected String loadData(final String nodeJsonString) {
		ResultSet rs = null;
		java.sql.Connection javaConn = null;
		Statement statement = null;

		JsonObject jsonNode = JsonObject.readFrom(nodeJsonString);
		RunkiEyeGraph graph = new RunkiEyeGraph();

		try {

			SqlMapClient client = TadpoleSQLManager.getInstance(userDB);

			String reqQuery = "match (n)-[r]->(p) where id(n) = '" + jsonNode.get("id").asString() + "' return n, r, p UNION match (n)-[r]->(p) where id(p) = '" + jsonNode.get("id").asString() + "' return n, r, p";
			if (logger.isDebugEnabled())
				logger.debug("Execute Math CQL : " + reqQuery);

			javaConn = client.getDataSource().getConnection();
			statement = javaConn.createStatement();

			rs = statement.executeQuery(reqQuery);
			ResultSetMetaData rsmt = rs.getMetaData();

			RurukiNode node = null;
			RurukiEdge rurukiEdge = null;
			Path path = null;

			while (rs.next()) {
				for (int columnIndex = 1; columnIndex <= rsmt.getColumnCount(); columnIndex++) {

					Object obj = rs.getObject(columnIndex);
					if (obj instanceof Vertex) {
						node = new RurukiNode();
						Vertex vertex = (Vertex) obj;
						node.setId(vertex.getVertexId().getOid() + "." + vertex.getVertexId().getId());
						node.setLabel(vertex.getLabel());
						node.setProperties(vertex.getProperty().toMap());
						node.setMetadata(new Metadata(0, 0, vertex));
						graph.addVertex(node);
					} else if (obj instanceof Edge) {
						rurukiEdge = new RurukiEdge();
						Edge relation = (Edge) obj;
						rurukiEdge.setId(relation.getEdgeId().getOid() + "." + relation.getEdgeId().getId());
						rurukiEdge.setLabel(relation.getLabel());
						rurukiEdge.setHeadId(relation.getStartVertexId().getOid() + "." + relation.getStartVertexId().getId());
						rurukiEdge.setTailId(relation.getEndVertexid().getOid() + "." + relation.getEndVertexid().getId());
						rurukiEdge.setProperties(relation.getProperty().toMap());
						graph.addEdge(rurukiEdge);
					} else if (obj instanceof Path) {

						path = (Path) obj;

						for (Edge relation : path.edges()) {

							rurukiEdge = new RurukiEdge();

							rurukiEdge.setId(relation.getEdgeId().getOid() + "." + relation.getEdgeId().getId());
							rurukiEdge.setLabel(relation.getLabel());
							rurukiEdge.setHeadId(relation.getStartVertexId().getOid() + "." + relation.getStartVertexId().getId());
							rurukiEdge.setTailId(relation.getEndVertexid().getOid() + "." + relation.getEndVertexid().getId());
							rurukiEdge.setProperties(relation.getProperty().toMap());

							graph.addEdge(rurukiEdge);

						}

						if (logger.isDebugEnabled())
							logger.debug("path start " + path.start());
						if (logger.isDebugEnabled())
							logger.debug("path length " + String.valueOf(path.length()));
						for (Vertex vertex : path.vertexs()) {
							node = new RurukiNode();
							node.setId(vertex.getVertexId().getOid() + "." + vertex.getVertexId().getId());
							node.setLabel(vertex.getLabel());
							node.setProperties(vertex.getProperty().toMap());
							node.setMetadata(new Metadata(0, 0, vertex));

							graph.addVertex(node);
						}
					} else {
						if (logger.isDebugEnabled())
							logger.debug("Unknow Class " + obj.getClass().toString());
					}
				}
			}

			graph.calcInOutEdgeCount();

		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (javaConn != null)
					javaConn.close();
			} catch (Exception e) {
			}

		}
		return graph.toJSONString();
	}

	/**
	 * Sigma.js 라이브러리를 이용하여 그래프를 표현할때 사용한다.
	 * 
	 * @param nodeJsonString
	 * @return
	 */
	protected String loadDataWithSigma(final String nodeJsonString) {
		String result = "";

		JsonObject jsonNode = JsonObject.readFrom(nodeJsonString);

		ResultSet rs = null;
		java.sql.Connection javaConn = null;
		Statement statement = null;

		try {

			SqlMapClient client = TadpoleSQLManager.getInstance(userDB);

			String reqQuery = "match (n)-[r]->(p) where id(n) = '" + jsonNode.get("id").asString() + "' return n, r, p UNION match (n)-[r]->(p) where id(p) = '" + jsonNode.get("id").asString() + "' return n, r, p";
			if (logger.isDebugEnabled())
				logger.debug("Execute Math CQL : " + reqQuery);

			javaConn = client.getDataSource().getConnection();
			statement = javaConn.createStatement();

			rs = statement.executeQuery(reqQuery);
			ResultSetMetaData rsmt = rs.getMetaData();

			SigmaGraph graph = new SigmaGraph();
			Node node = null;
			GEdge edge = null;
			long row = 0;
			double parentAngle = 0;
			double angleDeg = 0;
			double nx = 0;
			double ny = 0;

			while (rs.next()) {
				row++;
				for (int columnIndex = 1; columnIndex <= rsmt.getColumnCount(); columnIndex++) {

					Object obj = rs.getObject(columnIndex);

					if (obj instanceof Vertex) {
						node = new Node();

						Vertex vertex = (Vertex) obj;
						node.setLabel(vertex.getLabel());
						angleDeg = (((360 / (rsmt.getColumnCount() * 10)) * (columnIndex * row)));
						nx = jsonNode.get("x").asDouble() + (100 * Math.cos((angleDeg * (Math.PI / 180)) - parentAngle));
						ny = jsonNode.get("y").asDouble() + (100 * Math.sin((angleDeg * (Math.PI / 180)) - parentAngle));
						node.setX(nx);
						node.setY(ny);

						node.setColor(AgensUtils.getRandomRGB());
						node.setSize(500);
						graph.addNode(node);

					} else if (obj instanceof Edge) {

						edge = new GEdge();
						Edge relation = (Edge) obj;
						edge.setId(relation.getEdgeId().getOid() + "." + relation.getEdgeId().getId());
						edge.setLabel(relation.getLabel());
						edge.setSource(relation.getStartVertexId().getOid() + "." + relation.getStartVertexId().getId());
						edge.setTarget(relation.getEndVertexid().getOid() + "." + relation.getEndVertexid().getId());
						edge.setType("arrow");//'line', 'curve', 'arrow', 'curvedArrow'
						edge.setColor(AgensUtils.getRandomRGB("100"));
						edge.setSize(0.5);

						if (logger.isDebugEnabled())
							logger.debug("Relation is " + relation.toString() + ", edge is " + edge.toString());

						graph.addEdge(edge);
					} else {
						if (logger.isDebugEnabled())
							logger.debug("Unknow Class " + obj.getClass().toString());
					}
				}
			}

			Gson gson = new Gson();
			result = gson.toJson(graph);

			if (logger.isDebugEnabled())
				logger.debug("##### Graph ####====>" + result);

		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (javaConn != null)
					javaConn.close();
			} catch (Exception e) {
			}

		}
		return result;
	}

	/**
	 * save options
	 */
	protected void saveOptions(String userData) {
		//		try {
		//			SpatialGetPreferenceData.updateUserOptions(userData);
		//		} catch (Exception e) {
		//			logger.error("Spatial save options", e);
		//		}
	}
}
