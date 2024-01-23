package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.cucumber.datatable.DataTable;

public class PropertyReader {

	public static Properties loadPropertyFile(String path) {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream(new File(path))) {
			properties.load(fis);
		} catch (Exception e) {
			throw new RuntimeException("Exception while loding properties file..." + e.getMessage());
		}
		return properties;
	}

	public static Map<String, String> loadDataTableMap(DataTable dataTable) {
		return dataTable.asMap(String.class, String.class);
	}

	public static List<Map<String, String>> loadDataTableMaps(DataTable dataTable) {
		return dataTable.asMaps(String.class, String.class);
	}

}
