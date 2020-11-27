package LinearRegression;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class CSVReaderInJava {

	public static List<Data> readDataFromCSV(String fileName) {
		List<Data> datas = new ArrayList<Data>();
		Path path = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] attributes = line.split(",");
				Data data = null;
				try {
					data = new Data(Integer.parseInt(attributes[0].trim()), Integer.parseInt(attributes[1].trim()));
				} catch (Exception e) {
					continue;
				}
				datas.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return datas;
	}
}
