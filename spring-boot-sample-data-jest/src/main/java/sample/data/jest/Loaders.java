package sample.data.jest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sample.data.jest.DatasetMetadata;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Component
public class Loaders {
    @Autowired
    private DatasetRepository repository;

    @PostConstruct
    @Transactional
    public void loadAll(){
        DatasetRepository cr = repository;
//         this.repository.deleteAll();
//          saveDatasetMetadata();
 //       getElasticRecord();
    }

    private void getElasticRecord() {
        JSONParser jsonParser = new JSONParser();
        Object object;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet getRequest = new HttpGet(
                    "http://34.230.72.180:9200/metadata/_search/");
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {

                try {
                    object = jsonParser.parse(output);
                    JSONObject jsonObject = (JSONObject) object;
                    JSONObject outerHits =  (JSONObject) jsonObject.get("hits");
                    JSONArray innerHits = (JSONArray) outerHits.get("hits");

                    if(innerHits!=null && innerHits.size()>0) {
                        for (int i = 0; i < innerHits.size(); i++) {
                            JSONObject _outerSource = (JSONObject) innerHits.get(i);
                            JSONObject _innerSource = (JSONObject) _outerSource.get("_source");
                            System.out.println(_innerSource);
                        }
                    }
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            httpClient.getConnectionManager().shutdown();
        }
        catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void saveDatasetMetadata() {

//        Attribute attribute = new Attribute("attr-1","attribute name 1","attribute description 1",
//                "attribute datatype 1");
//        List<Attribute> attributes = new ArrayList<>();
//        attributes.add(attribute);

        DatasetMetadata metadata = new DatasetMetadata("ds id 1","ds name 1",
                "ds desc 1","ds src 1");
        this.repository.save(metadata);

        DatasetMetadata metadata2 = new DatasetMetadata("ds id 2","ds name 2",
                "ds desc 2","ds src 2");
        this.repository.save(metadata2);
    }
}
