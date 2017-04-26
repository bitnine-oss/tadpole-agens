package com.bitnine.tadpole.graph.core.editor.extension.sigma;

public class SigmaGraph extends GraphObject {
	public void addNode(Node node) {
		for (Node n : this.getNodes()) {
			if (n.getId().equals(node.getId()))
				return;
		}
		this.getNodes().add(node);
	}
	public void addEdge(GEdge edge) {
		for (GEdge n : this.getEdges()) {
			if (n.getId().equals(edge.getId()))
				return;
		}
		this.getEdges().add(edge);
	}
}
