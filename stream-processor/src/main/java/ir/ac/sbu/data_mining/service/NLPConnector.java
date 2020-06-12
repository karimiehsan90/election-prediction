package ir.ac.sbu.data_mining.service;

import com.fasterxml.jackson.databind.ObjectWriter;
import ir.ac.sbu.data_mining.conf.Conf;
import ir.ac.sbu.data_mining.request.TweetRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class NLPConnector {
    private Conf conf;
    private ObjectWriter writer;

    public NLPConnector(Conf conf, ObjectWriter writer) {
        this.conf = conf;
        this.writer = writer;
    }

    public String getEmotion(TweetRequest request) throws IOException {
        return sendRequest(conf.getNlpUrl(), writer.writeValueAsString(request));
    }

    private String sendRequest(String url, String body) throws IOException {
        HttpPost postRequest = new HttpPost(url);
        postRequest.setEntity(new StringEntity(body));
        try (CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = client.execute(postRequest)) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw e;
        }
    }
}
