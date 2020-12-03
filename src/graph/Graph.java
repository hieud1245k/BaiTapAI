package graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	public Node nodeRoot;

	public List<Node> nodesList;
	
	public Graph() {
		nodeRoot = new Node();
		nodesList = new ArrayList<>();
		nodesList.add(nodeRoot);
	}
	
	public void addNode(Node...nodes) {
		for (Node node : nodes) {
			nodesList.add(node);
		}
	}
	
	public void addEdge(String key_A, String key_B, Double distance) {
		if(key_A.equals(key_B)) 
			return;
		Node node_A = null, node_B = null;
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
//		nodesList.get(index_B).edges.put(node_A, distance);
	}
	
//	public int print(Node nRoot) {
//		
//	}
}
