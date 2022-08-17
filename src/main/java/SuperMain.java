import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Быстрый способ обрабатывать информацию в формате Json
 */

public class SuperMain {
    public static void main(String[] args) {
        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final HttpUriRequest httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts?_limit=20");
        try {
            CloseableHttpResponse response1 = httpClient.execute(httpGet);
            final HttpEntity entity1 = response1.getEntity();
            System.out.println(EntityUtils.toString(entity1));

            final HttpPost httpPost = new HttpPost("https://jsonplaceholder.typicode.com/posts");
            final List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("title", "foo"));
            params.add(new BasicNameValuePair("body", "bar"));
            params.add(new BasicNameValuePair("userId", "1"));

            httpPost.setEntity(new UrlEncodedFormEntity(params));

            CloseableHttpResponse response2 = httpClient.execute(httpPost);
            final HttpEntity entity2 = response2.getEntity();
            System.out.println(EntityUtils.toString(entity2));


            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
