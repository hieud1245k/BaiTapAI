package LinearRegression;

import java.util.List;

public class LRMain {
	private static Parameter gradientDescent(List<Data> points, double learningRate, int numItearations) {
		Parameter parameter = new Parameter(0, 0);
		for (int i = 0; i < numItearations; i++) {
			double[] gd = stepGradient(points, parameter, learningRate);
			parameter.m -= learningRate * gd[0]; // cap nhat lai weight
			parameter.b -= learningRate * gd[1]; // cap nhat lai bias
		}
		return parameter;
	}

	private static double[] stepGradient(List<Data> points, Parameter parameter, double learningRate) { 
		int N = points.size();
		double gdm = 0;
		double gdb = 0;
		for (Data d : points) {
			double x = d.getWeight();
			double y = d.getHeight();
			gdm += (2 / (float) N) * (-1 * x * (y - (parameter.m * x + parameter.b))); // dao ham cua cost function theo w
			gdb += (2 / (float) N) * (-1 * (y - (parameter.m * x + parameter.b))); // dao ham cua cost function theo b
		}
		System.out.println("Gdm: " + gdm);
		System.out.println("Gdb: " + gdb);
		System.out.println();
		return new double[] { gdm, gdb };
	}
	
	public static void main(String[] args) {
		List<Data> datas = CSVReaderInJava.readDataFromCSV("db/data.csv"); // doc data tu file csv
		Parameter parameter = gradientDescent(datas, 0.0001, 1000); // tim cuc tri bang phuong phap gradient descent
		System.out.println(parameter.toString());
	}
	
}
