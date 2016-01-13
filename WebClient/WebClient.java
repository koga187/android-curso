package br.com.caelum.cadastro.WebClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class WebClient {
    private final String url = "http://www.caelum.com.br/mobile";

    public String post (String json) {
        HttpPost post = new HttpPost(url);

        String jsonResposta = null;

        try {
            post.setEntity(new StringEntity(json));

            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            DefaultHttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(post);
            jsonResposta = EntityUtils.toString(response.getEntity());

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return jsonResposta;

    }

}
