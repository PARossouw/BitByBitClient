
package RestClientRemoteController;

import Story.Model.Story;
import User.Model.Reader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class RestClientLike {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;

    public RestClientLike(String url) {
        this.url = url + "/Like";
        this.mapper = new ObjectMapper();
    }

    //@FormParam must be put in the aruguments on the rest controller side
    public String likeStory(Reader reader, Story story) throws JsonProcessingException {
        String uri = url + "/likeStory";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);

        Response response = null;
         JSONObject jObject = new JSONObject();
        jObject.put("reader", reader);
        jObject.put("story", story);

        response = webTarget.request().post(Entity.json(toJsonString(jObject)));
        return response.readEntity(String.class);
    }

    

    public Map<String, Integer> getAllLikesInPeriod(String yearMonth) throws JsonProcessingException {
        String uri = url + "/allLikes/{yearMonth}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("yearMonth", yearMonth);

        Map<String, Integer> stories = null;
        stories = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<Map<String, Integer>>() {
        });
        return stories;
    }

    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
