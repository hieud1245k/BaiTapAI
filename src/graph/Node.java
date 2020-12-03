package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {

	public Node(String key, Double value) {
		this.key = key;
		this.value = value;
	}

	public Node() {
	}

	public Node(String key) {
		this.key = key;
	}

	public String key;
	public Double value = 0.0;
	public Map<Node, Double> edges = new HashMap<>();
	public List<Node> reviewed = new ArrayList<>();

	public Double distanceTo(Node node) {
		return edges.get(node);
	};
}