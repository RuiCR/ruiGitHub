package edu.fjnu.cr.util;
import com.google.gson.Gson;

public class JsonUtil {
	private static Gson gson = null;
	static {
		if (gson == null) {
			gson = new Gson();
		}

	}

	public static String objToJson(Object obj) {
		String jsonStr = "";
		if (obj != null) {
			jsonStr = gson.toJson(obj);
		}
		return jsonStr;
	}
}
