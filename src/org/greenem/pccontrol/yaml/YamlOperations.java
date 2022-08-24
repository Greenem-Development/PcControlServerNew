package org.greenem.pccontrol.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.greenem.pccontrol.util.OwnLogger;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

public class YamlOperations {
	public static Map<String, Object> loadYaml(String f) {
		return loadYaml(new File(f));
	}
	
	public static Map<String, Object> loadYaml(File f) {
		Yaml y = new Yaml();
		Map<String, Object> data = null;
		try {
			data = y.load(new FileInputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static Object getValue(Map<String, Object> y, String key) throws Exception {
		String[] parts = key.split("\\.");
		if(parts.length==1) return y.get(key);
		return getDeeperValue(y, key);
	}
	
	@SuppressWarnings("unchecked")
	private static Object getDeeperValue(Map<String, Object> y, String key) throws Exception { // directory_permissions.dirperms.default
		String[] parts = key.split("\\.");
		Object o = y.get(parts[0]);
		if(o instanceof Map && parts.length!=1) {
			return getDeeperValue((Map<String, Object>) o, key.substring(1 + parts[0].length()));
		} else {
			if(parts.length==1) {
				return y.get(key);
			} else {
//				throw new Exception("Such key doesn't exist!");
				return null;
			}
		}
	}
	
	public static void setValue(Map<String, Object> y, String key, Object value) throws Exception {
		String[] parts = key.split("\\.");
		if(parts.length==1) y.put(key, value);
		setDeeperValue(y, key, value);
	}
	
	@SuppressWarnings("unchecked")
	private static void setDeeperValue(Map<String, Object> y, String key, Object value) throws Exception { // directory_permissions.dirperms.default
		String[] parts = key.split("\\.");
		Object o = y.get(parts[0]);
		if(o instanceof Map && parts.length!=1) {
			getDeeperValue((Map<String, Object>) o, key.substring(1 + parts[0].length()));
		} else {
			if(parts.length==1) {
				if(value==null) y.remove(key);
				else y.put(key, value);
			} else {
				y.put(parts[0], new HashMap<String, Object>());
				setDeeperValue((Map<String, Object>) o, key.substring(1 + parts[0].length()), value);
//				throw new Exception("Such key doesn't exist!");
				return;
			}
		}
	}
	
	public static void saveYaml(Map<String, Object> y, File f) throws IOException { // TODO check changes
		FileWriter writer = new FileWriter(f);
		final DumperOptions options = new DumperOptions();
	    options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
	    options.setPrettyFlow(true);
		Yaml yaml = new Yaml(options);
		yaml.dump(y, writer);
	}
	
	public static void main(String[] args) throws Exception {
//		OwnLogger.log(getValue(loadYaml(f), "directory_permissions.dirperms_default"));
//		System.out.println("jhgjk".split("\\.").length);
		File f = new File("D:\\Stuff\\Development\\PcControl\\groupsConfigConcept1.txt");
		Map<String, Object> map = loadYaml(f);
		setValue(map, "directory_permissions.dirperms_default1", "va");
		saveYaml(map, f);
	}
}
