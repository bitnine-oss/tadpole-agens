package com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import net.bitnine.agensgraph.graph.Vertex;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bitnine.tadpole.graph.core.editor.extension.AgensGraphEditor;
import com.google.gson.Gson;
import com.hangum.tadpole.engine.manager.TadpoleSQLManager;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;
import com.ibatis.sqlmap.client.SqlMapClient;

public class RunkiEyeGraph extends RurunkiEye {
	private static final Logger logger = Logger.getLogger(AgensGraphEditor.class);

	/**
	 * 중복되는 아이디의 노드가 없을경우 추가한다.
	 * 
	 * @param newVetex
	 */
	public void addVertex(RurukiNode newVetex) {
		for (RurukiNode rurukiNode : this.getVertices()) {

			if (rurukiNode.getId().equals(newVetex.getId()))
				return;
		}
		this.getVertices().add(newVetex);
	}

	/**
	 * 중복되는 아이디가 없을경우 추가한다.
	 * 
	 * @param newEdge
	 */
	public void addEdge(RurukiEdge newEdge) {
		for (RurukiEdge rurukiEdge : this.getEdges()) {

			if (rurukiEdge.getId().equals(newEdge.getId()))
				return;
		}
		this.getEdges().add(newEdge);
	}

	/**
	 * 노드의 엣지 갯수를 계산한다.
	 */
	public void calcInOutEdgeCount() {

		for (RurukiNode rurukiNode : this.getVertices()) {
			for (RurukiEdge rurukiEdge : this.getEdges()) {
				if (rurukiNode.getId().equals(rurukiEdge.getHeadId())) {
					rurukiNode.getMetadata().setOutEdgeCount(rurukiNode.getMetadata().getOutEdgeCount() + 1);
				}
				if (rurukiNode.getId().equals(rurukiEdge.getTailId())) {
					rurukiNode.getMetadata().setInEdgeCount(rurukiNode.getMetadata().getInEdgeCount() + 1);
				}
			}
		}

	}

	/**
	 * 그래프 정보를 json문자열로 변환하여 리턴한다.
	 * 
	 * @return
	 */
	public String toJSONString() {
		Gson gson = null;
		String strGraphJson = "";
		try {
			gson = new Gson();
			strGraphJson = gson.toJson(this);
			if (logger.isDebugEnabled())
				logger.debug("##### Graph ####====>" + strGraphJson);
		} catch (Exception e) {
			logger.error(e);
		}
		return strGraphJson;
	}

	/**
	 * 엣지만 있을경우 관련 노드를 조회하여 추가한다.
	 */
	public void autoLoadbyEdges(UserDBDAO userDB) {

		StringBuffer edgeIDs = new StringBuffer("");
		HashMap<String, String> map = new HashMap<String, String>();

		if (this.getEdges().size() <= 0)
			return;

		// 엣지에서 시작, 종료 노드의 아이디를 중복값을 제거하고 추출한다.
		for (RurukiEdge rurukiEdge : this.getEdges()) {
			if (!map.containsKey(rurukiEdge.getHeadId())) {
				map.put(rurukiEdge.getHeadId(), "");
			}
			if (!map.containsKey(rurukiEdge.getTailId())) {
				map.put(rurukiEdge.getTailId(), "");
			}
		}

		for (String id : map.keySet()) {
			edgeIDs.append(String.format("'%s',", id));
		}
		loadVertexbyEdgeIds(userDB, StringUtils.removeEnd(edgeIDs.toString(), ","));
	}

	/**
	 * 최종결과에 엣지만 있을경우 엣지의 시작, 종료 노드를 조회하여 최종결과에 노드를 추가한다.
	 * @param userDB
	 * @param nodeIDs
	 */
	protected void loadVertexbyEdgeIds(UserDBDAO userDB, String nodeIDs) {

		ResultSet rs = null;
		java.sql.Connection javaConn = null;
		Statement statement = null;

		try {

			SqlMapClient client = TadpoleSQLManager.getInstance(userDB);

			String reqQuery = "match (n) where id(n) in (" + nodeIDs + ") return n ";
			if (logger.isDebugEnabled())
				logger.debug("Execute Math CQL : " + reqQuery);

			javaConn = client.getDataSource().getConnection();
			statement = javaConn.createStatement();

			rs = statement.executeQuery(reqQuery);

			RurukiNode node = null;
			RurukiEdge rurukiEdge = null;

			while (rs.next()) {
				Object obj = rs.getObject(1);
				if (obj instanceof Vertex) {
					node = new RurukiNode();
					Vertex vertex = (Vertex) obj;
					node.setId(vertex.getVertexId().getOid() + "." + vertex.getVertexId().getId());
					node.setLabel(vertex.getLabel());
					node.setProperties(vertex.getProperty().toMap());
					node.setMetadata(new Metadata(0, 0, vertex ));
					this.addVertex(node);
				}
			}

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
	}

	/**
	 * 연결된 노드가 가장많은 노드를 찾는다.
	 * 
	 * @param startNodeID
	 * @return
	 */
	public String getBaseNodeID(String startNodeID) {
		String baseNodeID = startNodeID;
		int maxNodeCount = 0;
		try {
			for (RurukiNode rurukiNode : this.getVertices()) {
				// 맨처음 노드를 기본노드로 설정한다.
				if (maxNodeCount == 0) baseNodeID = rurukiNode.getId();
				
				if (maxNodeCount < rurukiNode.getMetadata().getOutEdgeCount() ) {
					maxNodeCount = rurukiNode.getMetadata().getOutEdgeCount();
					baseNodeID = rurukiNode.getId();
				}else if ( maxNodeCount < rurukiNode.getMetadata().getInEdgeCount()) {
					maxNodeCount = rurukiNode.getMetadata().getInEdgeCount();
					baseNodeID = rurukiNode.getId();
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return baseNodeID;
	}
}
