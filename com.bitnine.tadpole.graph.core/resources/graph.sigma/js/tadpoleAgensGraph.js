/*
 * define java browfunction code
 * com.bitnine.tadpole.graph.core.editor.extension.browserHandler.CypherEditorFunction 과 동일하게 정의되어야 한다.
 */
var tadpole_sigma;
var tadpole_graph;
var editorService = {
	/** save option */
	LOAD_DATA           : "1",
	SAVE_OPTION			: "5"
};

$(document).ready(onLoad);

/*
 * initial event
 */
function onLoad() {
	console.log('******************onLoad()************************');
	try {
		tadpole_graph = {
			      nodes: [],
			      edges: []
			    };
		
		tadpole_sigma = new sigma({
		  graph: tadpole_graph,
		  renderer: {
		    container: document.getElementById('graph-container'),
		    type: 'canvas'
		  },
		  settings: {
		    edgeLabelSize: 'proportional',
		    labelAlignment:'inside'
		  }
		});
		
		//노드 더블클릭 이벤트를 정의한다.
		tadpole_sigma.bind('doubleClickNode', function(e) {
			console.log(e.type, e.data.node, e.data.captor);
			  
			if('doubleClickNode' == e.type){
				//TadpoleBrowserHandler(editorService.LOAD_DATA, JSON.stringify(e)); 
				//var result = TadpoleBrowserHandler(editorService.LOAD_DATA, e.data.node.id, e.data.node.label);
				var result = loadNodeExpandData(editorService.LOAD_DATA, e.data.node);
				appendGraphData(result, "");
			}
		});
	} catch (err) {
		console.log(err);
	}
}

// 노드를 더블클릭한 경우 엣지와 관련 노드를 추가로 조회하여 표시한다.
function loadNodeExpandData(option, selectNode) {
	console.log('******************loadNodeExpandData()************************');
	try {
		var nodeStr = JSON.stringify(selectNode);
		return TadpoleBrowserHandler(option, nodeStr);
    } catch(e) {
		console.log(e);
		return "{}";
	}
}

// 올챙이의 쿼리 결과에 노드와 엣지가 있을경우 그래프 뷰어에 표시한다.
function drawingGraphData(txtGraphJSON, txtUserOption) {
	console.log('******************drawingGraphData************************');
	try {	
		sigma.parsers.jsonData(txtGraphJSON, tadpole_sigma, updateGraph);
	} catch (err) {
		console.log(err);
	}
	tadpole_sigma.refresh();
};

// 기존 노드와 엣지를 그대로 유지하고 새로 조회된 노드와 엣지를 추가하여 표시한다.
function appendGraphData(txtGraphJSON, txtUserOption) {
	console.log('******************appendGraphData************************');
	try {	
		sigma.parsers.jsonAppendData(txtGraphJSON, tadpole_sigma, updateGraph);
	} catch (err) {
		console.log(err);
	}
	tadpole_sigma.refresh();
};

// 노드가 추가되면 노드에 드래그 이벤트를 할당한다.
function updateGraph(sig, g){

	try {
		// Initialize the dragNodes plugin:
		var dragListener = sigma.plugins.dragNodes(sig, sig.renderers[0]);
		
		//dragListener.bind('startdrag', function(event) {		  console.log(event);		});
		//dragListener.bind('drag', function(event) {		  console.log(event);		});
		//dragListener.bind('drop', function(event) {		  console.log(event);		});
		//dragListener.bind('dragend', function(event) {		  console.log(event);		});
		
		
		//sig.bind('overNode outNode clickNode doubleClickNode rightClickNode', function(e) {			  console.log(e.type, e.data.node, e.data.captor); });
		//sig.bind('doubleClickNode', function(e) {			console.log(e.type, e.data.node, e.data.captor);		});
		//sig.bind('overEdge outEdge clickEdge doubleClickEdge rightClickEdge', function(e) {			  console.log(e.type, e.data.edge, e.data.captor);			});
		//sig.bind('clickStage', function(e) {			  console.log(e.type, e.data.captor);			});
		//sig.bind('doubleClickStage rightClickStage', function(e) {			  console.log(e.type, e.data.captor);			});
		
		
		
		
		
	} catch (err) {
		console.log(err);
	}
	
	try {
	
		var fa = sig.layouts.configForceLink(s, {
			  worker: true,
			  barnesHutOptimize: false,
			  autoStop: true,
			  background: true,
			  easing: 'cubicInOut',
			  alignNodeSiblings: true,
			  nodeSiblingsScale: 1,
			  nodeSiblingsAngleMin: 0.3
			});
	
		// Bind the events:
		fa.bind('start interpolate stop', function(e) {
		  console.log(e.type);
		  var el = document.getElementById('notice');
		  if (e.type === 'start') {
		    el.className = '';
		  }
		  else if (e.type === 'interpolate') {
		    el.className = 'hidden';
		  }
		});
	
		// Start the ForceLink algorithm:
		sig.layouts.startForceLink();
			
	} catch (err) {
		console.log(err);
	}

	
	sig.refresh();
}






