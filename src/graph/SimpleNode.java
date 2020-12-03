package graph;

import java.util.HashMap;
import java.util.Map;

public class SimpleNode {
		public String key;
		public Map<SimpleNode, Double> edges = new HashMap<>();

		public SimpleNode(String key) {
			this.key = key;
		}
		
		public SimpleNode() {}
		
		@Override
		public String toString() {
			return key;
		}
	}