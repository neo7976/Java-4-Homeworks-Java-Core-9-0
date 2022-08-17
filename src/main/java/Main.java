
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static final String REMOTE_SERVICE_URL = "https://jsonplaceholder.typicode.com/posts";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        //        CloseableHttpClient httpClient = HttpClients.createDefault(); //настройки по умолчанию
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My test service")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();
        //Создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet(REMOTE_SERVICE_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        try {
            //отправка запроса
            CloseableHttpResponse response = httpClient.execute(request);

//            //вывод полученных заголовков
//            Arrays.stream(response.getAllHeaders()).forEach(System.out::println);

//            //чтение тела ответа
//            String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
//            System.out.println(body);

            List<Post> posts = mapper.readValue(response.getEntity().getContent(),
                    new TypeReference<List<Post>>() {
                    }
            );
            posts.forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }

        httpClient.close();
    }
}
