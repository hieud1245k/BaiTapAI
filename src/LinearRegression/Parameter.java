package LinearRegression;

public class Parameter {
	public double m; // weight
	public double b; // bias

	public Parameter(double m, double b) {
		this.m = m;
		this.b = b;
	}
	@Override
	public String toString() {
		return "m: " + m + ", b: " + b;
	}
}
