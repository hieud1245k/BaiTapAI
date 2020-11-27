package KMean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeanPointMain {
	public static List<Point> listInput = new ArrayList<>();
	static {
		for (int i = 0; i < 30; i++) {
//			listInput.add(new Point().randomInit());
			listInput.add(new Point(20, 30));
			listInput.add(new Point(343, 232));
			listInput.add(new Point(56, 90));
			listInput.add(new Point(222, 55));
			listInput.add(new Point(23, 789));
			listInput.add(new Point(345, 330));
			listInput.add(new Point(20, 9));
			listInput.add(new Point(221, 310));
			listInput.add(new Point(67, 34));
			listInput.add(new Point(90, 230));
			listInput.add(new Point(256, 354));
			listInput.add(new Point(12, 34));
			listInput.add(new Point(961, 68));
			listInput.add(new Point(46, 124));
			listInput.add(new Point(98, 2));
			listInput.add(new Point(66, 678));
			listInput.add(new Point(34, 356));
			listInput.add(new Point(12, 788));
			listInput.add(new Point(987, 24));
			listInput.add(new Point(124, 90));
		}
	}

	public static void main(String[] args) {
		KMeanPointMain kMeanPointMain = new KMeanPointMain();
		List<Cluster> clusters = kMeanPointMain.KMeanPoint(listInput, 3);
		System.out.println();
		System.out.println("Result:");
		for (Cluster cluster : clusters) {
			System.out.println(cluster.toString());
		}
	}

	public List<Cluster> KMeanPoint(List<Point> listInput, int k) {
		List<Cluster> clusters = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			clusters.add(new Cluster());
		}
		
		int count;
		int loopCount = 0;;
		
		while(true) {
			count = 0;
			int minClusterId = 0;
			
			for (int i = 0; i < listInput.size(); i++) {
				Double min = Math.abs(clusters.get(0).distanceBetweenTwoPoint(listInput.get(i)));
				for (int j = 0; j < clusters.size(); j++) {
					Double m = Math.abs(clusters.get(j).distanceBetweenTwoPoint(listInput.get(i)));
					if (m <= min) {
						minClusterId = j;
						min = m;
					}
				}
				clusters.get(minClusterId).list.add(listInput.get(i));
			}
			System.out.println("Show: "+ loopCount);
			for (Cluster cluster : clusters) {
				System.out.println(cluster.toString());
				count += cluster.compare();
			}
			
			if (count == 0)
				loopCount++;
			else
				loopCount = 0;
			
			if(loopCount > 100) {
				break;
			}
			
			for (Cluster cluster : clusters)
				cluster.reset();
		}
		return clusters;
	}

	static class Point {
		public double x;
		public double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public Point() {
			this.x = 0;
			this.y = 0;
		};

		public Point randomInit() {
			Random random = new Random();
			this.x = random.nextInt(500);
			this.y = random.nextInt(500);
			return this;
		}

		@Override
		public String toString() {
			return "x: " + x + ", y: " + y;
		}
	}

	static class Cluster {

		public Point key;
		private List<Point> list;

		public Cluster() {
			key = listInput.get(new Random().nextInt(listInput.size()));
			list = new ArrayList<>();
		}

		public Cluster(Point k) {
			key = k;
			list = new ArrayList<>();
		}

		public int compare() {
			Point newkey = newKey();
			if (this.key.x == newkey.x && this.key.y == newkey.y)
				return 0;
			return 1;
		}

		public Point newKey() {
			Point newKey = new Point();
			for (Point p : list) {
				newKey.x += p.x / list.size();
				newKey.y += p.y / list.size();
			}
			return newKey;
		}

		public void reset() {
			key = newKey();
			list.removeAll(list);
		}
		
		public double distanceBetweenTwoPoint(Point p) {
			return Math.sqrt((p.x - key.x)*(p.x - key.x) + (p.y - key.y)*(p.y - key.y));
		}

		@Override
		public String toString() {
			StringBuilder strBuilder = new StringBuilder("key: " + key + " -> ");
			for (Point p : list) {
				strBuilder.append(p.toString() + " ");
			}
			return strBuilder.toString();
		}
	}
}
