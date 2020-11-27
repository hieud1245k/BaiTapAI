package KMean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Áp dụng thuật toán KMean cho mảng dữ liệu 1 chiều
public class KMeanMain {
	private static Integer data[] = { 2, 4, 10, 12, 3, 20, 30, 11, 25, 45, 67, 43, 12, 78, 34, 100, 12, 34 };
	private static Random generator = new Random();

	public static void main(String[] args) {
		KMeanMain kMeanMain = new KMeanMain();
		List<Cluster> clusters = kMeanMain.kmean(data, 2); // k = 2
		System.out.println("Result:");
		for (Cluster cluster : clusters) {
			System.out.println(cluster.toString());
		}
	}
	
	// train data
	public List<Cluster> kmean(Integer[] data, int k) {
		List<Cluster> clusters = new ArrayList<KMeanMain.Cluster>();
		for (int i = 0; i < k; i++) {
			clusters.add(new Cluster());
		}
//		clusters.add(new Cluster(3));
//		clusters.add(new Cluster(4));
		int count = 0;
		int loopCount = 0;
		do {
			count = 0;
			int minClusterId = 0;

			for (int i = 0; i < data.length; i++) {
				Double min = Math.abs(data[i] - clusters.get(0).key);
				for (int j = 0; j < clusters.size(); j++) {
					Double m = Math.abs(data[i] - clusters.get(j).key);
					if (m <= min) {
						minClusterId = j;
						min = m;
					}
				}
				clusters.get(minClusterId).list.add((double) data[i]);
			}
			System.out.println("Show:");
			for (Cluster cluster : clusters) {
				System.out.println(cluster.toString());
				count += cluster.compare();
			}
			
			if (count == 0)
				loopCount++;
			else
				loopCount = 0;
			
			if(loopCount > 50) {
				break;
			}
			
			for (Cluster cluster : clusters)
				cluster.reset();
			
		} while (true);
		
		return clusters;
	}

	static class Cluster { // cau truc 1 cluster

		public Double key;
		private List<Double> list;

		public Cluster() {
			key = (double) data[generator.nextInt(data.length)];
			list = new ArrayList<Double>();
		}

		public Cluster(double k) {
			key = k;
			list = new ArrayList<Double>();
		}

		public int compare() {
			Double newKey = sum() / list.size();
			if (newKey.equals(key))
				return 0;
			return 1;
		}

		public Double sum() {
			Double s = 0.0;
			for (Double i : list) {
				s += i;
			}
			return s;
		}

		public void reset() {
			key = sum() / list.size();
			list.removeAll(list);
		}

		@Override
		public String toString() {
			return "key: " + key + " -> " + list.toString() + "\n";
		}
	}
	
	

}
