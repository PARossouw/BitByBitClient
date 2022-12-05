package RestClientRemoteController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

public class RestClientRating {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;

    public RestClientRating(String url) {
        this.url = url + "/Rating";
        this.mapper = new ObjectMapper();
    }

    //has 3 args, can use either a map inside a map or a list?
    //@FormParam must be put in the aruguments on the rest controller side
    public void rateStory(String ratingInfo) throws JsonProcessingException {
//        String uri = url + "/rateStory";
//        restClient = ClientBuilder.newClient();
//        webTarget = restClient.target(uri);
//        HashMap<Reader, Story> rateInfo = new HashMap();
//        rateInfo.put(reader, story);
//        Response response = null;
//        response = webTarget.request().post(Entity.json(toJsonString(rateInfo)));
//        return response.readEntity(String.class);

        String uri = url + "/rateStory/{ratingInfo}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("ratingInfo", ratingInfo);
        
        webTarget.request();
       
    }

    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
