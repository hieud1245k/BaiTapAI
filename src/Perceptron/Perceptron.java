package Perceptron;

import java.text.DecimalFormat;

class Perceptron {
	static double learingRate = 0.1; // toc do hoc hoi
	static int totalNumbers = 100; // tong so phan tu
	static int thershold = 0; // nguong

	public static void main(String args[]) {


		// khoi tao mang gia tri
		double[] x = new double[totalNumbers];
		double[] y = new double[totalNumbers];
		double[] z = new double[totalNumbers];
		int[] outputs = new int[totalNumbers];

		// random 1/2 tong so phan tu voi label = 1
		for (int i = 0; i < totalNumbers / 2; i++) {
			x[i] = randomNumber(5, 10);
			y[i] = randomNumber(4, 8);
			z[i] = randomNumber(2, 9);
			outputs[i] = 1;
			System.out.println(x[i] + "\t" + y[i] + "\t" + z[i] + "\t" + outputs[i]);
		}

		// random 1/2 tong so phan tu voi label = 0
		for (int i = 50; i < totalNumbers; i++) {
			x[i] = randomNumber(-1, 3);
			y[i] = randomNumber(-4, 2);
			z[i] = randomNumber(-3, 5);
			outputs[i] = 0;
			System.out.println(x[i] + "\t" + y[i] + "\t" + z[i] + "\t" + outputs[i]);
		}

		// khoi tao mang result voi [w1,w2,w3,bias]
		double[] weights = new double[4];
		double localError; // sai so du doan
		double globalError; // tong so loi
		int p, iteration, output;

		weights[0] = randomNumber(0, 1);// w1
		weights[1] = randomNumber(0, 1);// w2
		weights[2] = randomNumber(0, 1);// w3
		weights[3] = randomNumber(0, 1);// bias

		iteration = 0;
		do {
			iteration++;
			globalError = 0;
			
			// thực hiện dự đoán kết quả bằng thuật toán perceptron với tập dữ liệu vừa khởi tạo 
			for (p = 0; p < totalNumbers; p++) {
				
				// tinh toan gia tri du doan
				output = calculateOutput(thershold, weights, x[p], y[p], z[p]);
				
				// so sanh su khac nhau giua label du doan va label thuc -> sai so du doan
				localError = outputs[p] - output;
				
				// cap nhat ket qua
				weights[0] += learingRate * localError * x[p];
				weights[1] += learingRate * localError * y[p];
				weights[2] += learingRate * localError * z[p];
				weights[3] += learingRate * localError;
				
				// tong so loi(sai so du doan)
				globalError += localError* localError; // == abs(localError) 
				System.out.println("globalError: " + globalError);
			}

			// show so diem sai lech so voi tap du lieu
			System.out.println("so lan lap: " + iteration + " : Sai so MSE = " + Math.sqrt(globalError / totalNumbers));
			
			// neu khong xuat hien loi hoac thuc hien lap 100 lan(ket qua gan chinh xac)
		} while (globalError != 0 && iteration <= 100);

		System.out.println("_______Phuong trinh phan chia 2 lop thu duoc________:");
		System.out.println(weights[0] + "*x + " + weights[1] + "*y +  " + weights[2] + "*z + " + weights[3] + " = 0");

	}

	public static double randomNumber(int min, int max) { // random so thuc trong khoang[min, max]
		DecimalFormat decimalFormat = new DecimalFormat("#.####"); // format
		double d = Math.random() * (max - min) + min; 
		String strResult = decimalFormat.format(d); 
		double result = Double.parseDouble(strResult);
		return result;
	}

	// Xét điểm so với ngưỡng, xác định lại phân lớp của 2 class
	static int calculateOutput(int thershold, double weights[], double x, double y, double z) { 
		double sum = x * weights[0] + y * weights[1] + z * weights[2] + weights[3]; 
		return (sum >= thershold) ? 1 : 0;
	}

}