package RestClientRemoteController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    public String rateStory(String ratingInfo) throws JsonProcessingException {
        String uri = url + "/rateStory";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(ratingInfo)));
        return response.readEntity(String.class);

    }

    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
