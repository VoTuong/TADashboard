package com.tadashboard.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import extentreports.ExtentLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.*;

public class JsonHelper {

	public static Map<String, String> convertJsonToMap(String json) {
		try {
			Type mapType = new TypeToken<Map<String, String>>() {
			}.getType();
			Gson gson = new Gson();
			return gson.fromJson(json, mapType);
		} catch (Exception e) {
			ExtentLogger.fail(e.getMessage());
			throw e;
		}
	}

	public static <T> List<T> getListData(String jsonPath, Type type) {
		try {
			JsonReader reader = getJsonReader(jsonPath);
			List<T> lst;
			Gson gson = new Gson();
			lst = gson.fromJson(Objects.requireNonNull(reader), type);
			return lst;
		} catch (Exception e) {
			ExtentLogger.fail(e.getMessage());
			throw e;
		}
	}

	public static <T> List<T> getListData(String jsonPath, Class<?> clazz) {
		try {
			JsonReader reader = getJsonReader(jsonPath);
			List<T> lst;
			Gson gson = new Gson();
			lst = gson.fromJson(Objects.requireNonNull(reader), clazz);
			return lst;
		} catch (Exception e) {
			ExtentLogger.fail(e.getMessage());
			throw e;
		}
	}

	public static <T> T getData(String jsonPath, Type type) {
		try {
			Gson gson = new Gson();
			JsonReader reader = getJsonReader(jsonPath);
			return gson.fromJson(Objects.requireNonNull(reader), type);
		} catch (Exception e) {
			ExtentLogger.fail(e.getMessage());
			throw e;
		}
	}

	public static <T> T getData(String jsonPath, Class<?> clazz) {
		try {
			Gson gson = new Gson();
			JsonReader reader = getJsonReader(jsonPath);
			return gson.fromJson(Objects.requireNonNull(reader), clazz);
		} catch (Exception e) {
			ExtentLogger.fail(e.getMessage());
			throw e;
		}
	}

	public static <T> T getJsonAsClass(JsonObject json, Class<?> clazz) {
		try {
			Gson gson = new Gson();
			return (T) gson.fromJson(json, clazz);
		} catch (Exception e) {
			ExtentLogger.fail(e.getMessage());
			throw e;
		}
	}

	public static JsonObject getJsonObject(String jsonPath) {
		try {
			JsonObject obj;
			Gson gson = new Gson();
			JsonReader reader = getJsonReader(jsonPath);
			obj = gson.fromJson(Objects.requireNonNull(reader), JsonObject.class);
			return obj;
		} catch (Exception e) {
			ExtentLogger.fail(e.getMessage());
			throw e;
		}
	}

	public static JsonArray getJsonArray(String jsonPath) {
		try {
			JsonArray jsonArray;
			Gson gson = new Gson();
			JsonReader reader = getJsonReader(jsonPath);
			jsonArray = gson.fromJson(Objects.requireNonNull(reader), JsonArray.class);
			return jsonArray;
		} catch (Exception e) {
			ExtentLogger.fail(e.getMessage());
			throw e;
		}
	}

	public static DesiredCapabilities convertJsonToCapabilities(String json) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		Map<String, String> caps = JsonHelper.convertJsonToMap(json);
		if (caps != null) {
			Set<String> keys = caps.keySet();
			for (String key : keys) {
				capabilities.setCapability(key, caps.get(key));
			}
		}
		return capabilities;
	}

	public static List<String> convertJsonToArguments(String json) {
		List<String> args = new ArrayList<>();

		Map<String, String> maps = JsonHelper.convertJsonToMap(json);
		if (maps != null) {
			Set<String> keys = maps.keySet();
			for (String key : keys) {
				args.add(maps.get(key));
			}
		}
		return args;
	}

	private static JsonReader getJsonReader(String jsonPath) {
		try {
			JsonReader reader;
			reader = new JsonReader(new FileReader(jsonPath));
			return reader;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static JSONObject convertObjectToJsonObject(Object Object) throws ParseException {
		JSONObject jsonSimple;
		JSONParser parser = new JSONParser();
		jsonSimple = (JSONObject) parser.parse(Object.toString());
		return jsonSimple;

	}

	public static JSONObject getJsonObjectFromJsonArray (JSONArray JsonArray, int index) throws ParseException {
		JSONObject jsonSimple;
		JSONParser parser = new JSONParser();
		jsonSimple = (JSONObject) parser.parse(JsonArray.get(index).toString());
		return jsonSimple;
	}

	public static void writeJsonToFile(String jsonPath, Object object){
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(object);
			FileWriter fileWriter = new FileWriter(jsonPath);
			fileWriter.write(jsonString);
			fileWriter.close();
		} catch (Exception e){
			ExtentLogger.fail("Error in write json to file" + e);
		}
	}
}
