package com.bitnine.tadpole.graph.core.editor.extension;

import java.util.Map;

import net.bitnine.agensgraph.graph.Edge;
import net.bitnine.agensgraph.graph.Path;
import net.bitnine.agensgraph.graph.Vertex;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.bitnine.tadpole.graph.core.editor.extension.browserHandler.CypherEditorFunction;
import com.bitnine.tadpole.graph.core.editor.extension.browserHandler.CypherFunctionService;
import com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye.Metadata;
import com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye.RunkiEyeGraph;
import com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye.RurukiEdge;
import com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye.RurukiNode;
import com.bitnine.tadpole.graph.core.editor.extension.sigma.AgensUtils;
import com.bitnine.tadpole.graph.core.editor.extension.sigma.GEdge;
import com.bitnine.tadpole.graph.core.editor.extension.sigma.Node;
import com.bitnine.tadpole.graph.core.editor.extension.sigma.SigmaGraph;
import com.google.gson.Gson;
import com.hangum.tadpole.commons.libs.core.define.SystemDefine;
import com.hangum.tadpole.engine.query.dao.system.UserDBDAO;
import com.hangum.tadpole.engine.sql.util.resultset.QueryExecuteResultDTO;
import com.hangum.tadpole.rdb.core.editors.main.MainEditor;
import com.hangum.tadpole.rdb.core.extensionpoint.definition.AMainEditorExtension;

/**
 * Agens graph ui
 * 
 * @author hangum
 *
 */
public class AgensGraphEditor extends AMainEditorExtension {
	private static final Logger logger = Logger.getLogger(AgensGraphEditor.class);

	protected Composite compositeBody;
	/** graph가 들어갈 브라우저 */
	protected Browser browserGraph;
	/** browser.browserFunction의 서비스 헨들러 */
	protected BrowserFunction editorService;

	protected UserDBDAO userDB;

	/**
	 * @wbp.parser.entryPoint
	 */
	public AgensGraphEditor() {
	}

	@Override
	public void createPartControl(Composite parent, MainEditor mainEditor) {
		this.mainEditor = mainEditor;

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(1, false);
		gl_composite.verticalSpacing = 2;
		gl_composite.horizontalSpacing = 2;
		gl_composite.marginHeight = 2;
		gl_composite.marginWidth = 2;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		compositeBody = new Composite(composite, SWT.NONE);
		compositeBody.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		compositeBody.setLayout(new GridLayout(1, false));

		browserGraph = new Browser(compositeBody, SWT.BORDER);
		browserGraph.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		initUI();
	}

	@Override
	public void initExtension(UserDBDAO userDB) {
		if (userDB == null) {
			super.setEnableExtension(false);
			return;
		}
		this.userDB = userDB;
		super.initExtension(userDB);
		super.setEnableExtension(true);
	}

	/**
	 * UI가 처음 호출될때 초기화 합니다.
	 */
	public static final String DUMY_DATA = String.format("?%s=%s", SystemDefine.MAJOR_VERSION, SystemDefine.RELEASE_DATE);

	private void initUI() {
		try {
			browserGraph.setUrl("/resources/graph/TadpoleAgensGraph.html" + DUMY_DATA);
			// 올챙이(java)와 그래프브라우져(js)간의 통신을 위한 이벤트 헨들러 등록.
			registerBrowserFunctions();
		} catch (Exception e) {
			logger.error("initialize graph browser error", e);
		}
	}

	/**
	 * 쿼리를 agens graph에 맞게 조작한다.
	 */
	@Override
	public String sqlCostume(String strSQL) {

		//	Cypher 쿼리결과를 표시한 그래프 브라우져 영역을 확장하여 표시한다.
		try {
			super.setEnableExtension(true);

			mainEditor.getSashFormExtension().getDisplay().asyncExec(new Runnable() {
				public void run() {
					int[] intWidgetSizes = mainEditor.getSashFormExtension().getWeights();
					if (intWidgetSizes[0] != 100) {
						mainEditor.getSashFormExtension().setWeights(new int[] { 50, 50 });
					}
				}
			});
		} catch (Exception e) {
			super.setEnableExtension(false);
		}

		return strSQL;
	}

	/**
	 * register browser function js, java간 통신을 위한 이벤트 헨들러(함수)등록.
	 */
	protected void registerBrowserFunctions() {
		try {
			editorService = new CypherFunctionService(browserGraph, userDB, CypherEditorFunction.GRAPH_SERVICE_HANDLER);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public void resultSetClick(int selectIndex, Map<Integer, Object> mapColumns) {
		if (logger.isDebugEnabled())
			logger.debug("===> 쿼리 결과를 클릭했다..");
	}

	@Override
	public void resultSetDoubleClick(int selectIndex, Map<Integer, Object> mapColumns) {
		if (logger.isDebugEnabled())
			logger.debug("===> 쿼리 결과를 더블클릭했다..");
	}

	@Override
	public void queryEndedExecute(QueryExecuteResultDTO rsDAO) {
		if (logger.isDebugEnabled())
			logger.debug("===> 쿼리 결과를 끝냈다.");

		if (rsDAO.getDataList() != null) {
			//drawGraphWithSigma(rsDAO, "");
			drawGraphWithRurunkiEye(rsDAO, "");
		}

	}

	/**
	 * RurunkiEye.js 라이브러리를 이용해서 그래프를 그릴때 사용한다.
	 * 
	 * @param rsDAO
	 * @param strUserOptions
	 */
	private void drawGraphWithRurunkiEye(final QueryExecuteResultDTO rsDAO, final String strUserOptions) {
		browserGraph.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {

				String baseNodeID = "0";
				RunkiEyeGraph graph = new RunkiEyeGraph();
				RurukiNode node = null;
				RurukiEdge rurukiEdge = null;
				Path path = null;
				int display_cnt = 0;

				for (Map<Integer, Object> column : rsDAO.getDataList().getData()) {

					for (int i = 0; i < column.size(); i++) {

						Object obj = column.get(i);
						if (obj instanceof Vertex) {
							node = new RurukiNode();
							Vertex vertex = (Vertex) obj;
							node.setId(vertex.getVertexId().getOid() + "." + vertex.getVertexId().getId());
							node.setLabel(vertex.getLabel());
							node.setProperties(vertex.getProperty().toMap());
							node.setMetadata(new Metadata(0, 0, vertex));

							graph.addVertex(node);
							display_cnt++;
						} else if (obj instanceof Edge) {

							rurukiEdge = new RurukiEdge();
							Edge relation = (Edge) obj;
							rurukiEdge.setId(relation.getEdgeId().getOid() + "." + relation.getEdgeId().getId());
							rurukiEdge.setLabel(relation.getLabel());
							rurukiEdge.setHeadId(relation.getStartVertexId().getOid() + "." + relation.getStartVertexId().getId());
							rurukiEdge.setTailId(relation.getEndVertexid().getOid() + "." + relation.getEndVertexid().getId());
							rurukiEdge.setProperties(relation.getProperty().toMap());

							graph.addEdge(rurukiEdge);
							display_cnt++;
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
								display_cnt++;
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
								display_cnt++;
							}

						} else {
							if (logger.isDebugEnabled())
								logger.debug("Unknow Class " + obj.getClass().toString());
						}
					}
					if (display_cnt > 100)
						break;
				}

				graph.autoLoadbyEdges(userDB);

				graph.calcInOutEdgeCount();

				browserGraph.evaluate(String.format("drawingGraphData('%s', '%s');", graph.toJSONString(), graph.getBaseNodeID(baseNodeID)));

			}
		});
	}

	/**
	 * Sigma.js라이브러리를 이용한 그래프 표시.
	 * 
	 * @param rsDAO
	 * @param strUserOptions
	 */
	private void drawGraphWithSigma(final QueryExecuteResultDTO rsDAO, final String strUserOptions) {
		browserGraph.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {

				SigmaGraph graph = new SigmaGraph();
				Node node = null;
				GEdge edge = null;
				long row = 0;
				double parentAngle = 0;
				double angleDeg = 0;
				double nx = 0;
				double ny = 0;

				for (Map<Integer, Object> column : rsDAO.getDataList().getData()) {
					row++;
					for (int i = 0; i < column.size(); i++) {
						Object obj = column.get(i);
						if (obj instanceof Vertex) {
							node = new Node();

							Vertex vertex = (Vertex) obj;
							node.setLabel(vertex.getLabel());
							angleDeg = (((360 / rsDAO.getDataList().getData().size()) * (i * row)));
							nx = Math.random() + (100 * Math.cos((angleDeg * (Math.PI / 180)) - parentAngle));
							ny = Math.random() + (100 * Math.sin((angleDeg * (Math.PI / 180)) - parentAngle));
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

							graph.addEdge(edge);
						} else {
							if (logger.isDebugEnabled())
								logger.debug("Unknow Class " + obj.getClass().toString());
						}
					}
				}

				Gson gson = new Gson();
				String strGraphJson = gson.toJson(graph);

				if (logger.isDebugEnabled())
					logger.debug("##### Graph ####====>" + strGraphJson);

				browserGraph.evaluate(String.format("drawingGraphData('%s');", strGraphJson));

			}
		});
	}

}
