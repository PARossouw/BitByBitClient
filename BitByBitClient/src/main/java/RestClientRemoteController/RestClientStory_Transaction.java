
package RestClientRemoteController;

import SMS.smsreq;
import Story.Model.Story;
import User.Model.User;
import User.Model.Writer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import org.json.simple.JSONObject;

public class RestClientStory_Transaction {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;

    public RestClientStory_Transaction(String url) {
        this.url = url + "/StoryTransaction";
        this.mapper = new ObjectMapper();
    }
    
    
    
     public String approvePendingStory(User editor, Story story) throws JsonProcessingException {
         
        editor.setDateAdded(null);
        String uri = url + "/approve";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("editor", editor);
        jsonObject.put("story", story);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(jsonObject)));
        
        return response.readEntity(String.class);
     }

       //@FormParam must be put in the aruguments on the rest controller side
    public String rejectPendingStory(User editor, Story story) throws JsonProcessingException {
        String uri = url + "/reject";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("editor", editor);
        jsonObject.put("story", story);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(jsonObject)));
        return response.readEntity(String.class);

}

 
      //@FormParam must be put in the aruguments on the rest controller side
    public String removeStoryByWriter(Writer writer, Story story) throws JsonProcessingException {
          String uri = url + "/removeWriter";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        HashMap<Writer, Story> storiesToRemove = new HashMap();
        storiesToRemove.put(writer, story);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(storiesToRemove)));
        return response.readEntity(String.class);
        
    }
    
    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
    
    
    
}


