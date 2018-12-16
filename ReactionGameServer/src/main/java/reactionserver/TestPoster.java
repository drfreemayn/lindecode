package reactionserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class TestPoster {
	
	
	private TestPoster() {
		
	}

	public static void testPost2() {
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost("http://localhost:7000/scoreboard/setscore");
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("name", "robert"));
			params.add(new BasicNameValuePair("score", "666"));
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			CloseableHttpResponse response = client.execute(httpPost);
			System.out.println("POST Response :" + response.getStatusLine().getStatusCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
