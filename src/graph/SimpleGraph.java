package graph;

import java.util.ArrayList;
import java.util.List;

public class SimpleGraph {
	public SimpleNode nodeRoot;
	public List<SimpleNode> nodesList;

	public SimpleGraph() {
		nodesList = new ArrayList<>();
		nodeRoot = new SimpleNode();
		nodesList.add(nodeRoot);
	}
	
	public void addNode(SimpleNode...nodes) {
		for (SimpleNode node : nodes) {
			this.nodesList.add(node);
		}
	}
	
	public void addEdge(String key_A, String key_B, double distance) {
		if(key_A.equals(key_B)) 
			return;
		SimpleNode node_A = null, node_B = null;
		int index_A = 0, index_B = 0;
		for(int i = 0; i < nodesList.size(); i++) {
			if(nodesList.get(i).key.equals(key_A)) {
				node_A = nodesList.get(i);
				index_A = i;
			}
			if(nodesList.get(i).key.equals(key_B)) {
				node_B = nodesList.get(i);
				index_B = i;
			}
		}
		nodesList.get(index_A).edges.put(node_B, distance);
		nodesList.get(index_B).edges.put(node_A, distance);
	}
}
