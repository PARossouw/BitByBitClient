package RestClientRemoteController;

import Story.Model.Story;
import User.Model.Editor;
import User.Model.Reader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.TreeMap;
import org.json.simple.JSONObject;

public class RestClientView {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;

    public RestClientView(String url) {
        this.url = url + "/View";
        this.mapper = new ObjectMapper();
    }

    //@FormParam must be put in the aruguments on the rest controller side
    public String viewStory(Story story, Reader reader) throws JsonProcessingException {
        String uri = url + "/addToStory";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        HashMap<Story, Reader> viewInfo = new HashMap();
        viewInfo.put(story, reader);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(viewInfo)));
        return response.readEntity(String.class);
    }

    //not sure about this one
    //@FormParam must be put in the aruguments on the rest controller side
    public HashMap<String, Integer> getAllStoryViewsInPeriod(String startDate, String endDate) throws JsonProcessingException {
        String uri = url + "/AllStoryViewsInPeriod/{startDate}/{endDate}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("startDate", startDate).resolveTemplate("endDate", endDate);
        HashMap<String, Integer> stories = null;
        stories = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<HashMap<String, Integer>>(){});
        return stories;
//        

        //hardcoding ***************  uncomment everything above this to revert changes

        

        
        
//        hardcoding
//        String uri = url + "/AllStoryViewsInPeriod/{startDate}/{endDate}";
//        restClient = ClientBuilder.newClient();
//        webTarget = restClient.target(uri).resolveTemplate("startDate", startDate).resolveTemplate("endDate", endDate);
//        HashMap<Story, Integer> stories = new HashMap<>();
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addKeyDeserializer(Story.class, new ObjectDeserializer());
//        mapper.registerModule( new ExampleJacksonModule() );
//        stories = mapper.readValue(
//                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<HashMap<Story, Integer>>(){});
//        HashMap<String, Integer> storiesZ = new HashMap<>();
//       
//    
//        
//        return storiesZ;
        

//        
//        return hardCodeMap;
        
        
    }

    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
