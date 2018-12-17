package reactionserver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtil {

	private final static String RESULT_JSON_PATH = "resources/results.json";

	private JSONUtil() {
	}
	
	@SuppressWarnings("unchecked")
	public static void writeResultsToResults(String name, String score) throws IOException, ParseException {
		JSONArray list = JSONUtil.readJsonFile();
		try (FileWriter file = new FileWriter(RESULT_JSON_PATH)) {
			JSONObject player = new JSONObject();
			player.put("name", name);
			player.put("score", score);	
			list.add(player);
			file.write(list.toJSONString());
			System.out.println("Player added! " + player.get("name") + " score was " + player.get("score"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static JSONArray readJsonFile() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		Reader reader = new FileReader(RESULT_JSON_PATH);
		Object jsonObj = parser.parse(reader);
		JSONArray jsonObject = (JSONArray) jsonObj;
		reader.close();
		return jsonObject;
	}

}
