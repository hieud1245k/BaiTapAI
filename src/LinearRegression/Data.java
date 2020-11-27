package LinearRegression;

// model doc tu file .csv
public class Data { 
	private int height; // chieu cao
	private int weight; // can nang

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Data(int weight, int height) {
		super();
		this.height = height;
		this.weight = weight;
	}
}
