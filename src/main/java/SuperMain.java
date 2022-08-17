import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Быстрый способ обрабатывать информацию в формате Json
 *
 */

public class SuperMain {
    public static void main(String[] args) throws IOException {
        final CloseableHttpClient httpClient = HttpClients.createDefault();

        final HttpUriRequest httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts?_limit=20");
        try {
            CloseableHttpResponse response1 = httpClient.execute(httpGet);
            final HttpEntity entity1 = response1.getEntity();
            System.out.println(EntityUtils.toString(entity1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpClient.close();

    }
}
