
package RestClientRemoteController;
import Story.Model.Story;
import User_Interactions.Comment.Model.Comment;
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



public class RestClientComment {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;

    public RestClientComment(String url) {
        this.url = url + "/RIP/Comment";
        this.mapper = new ObjectMapper();
    }
    
     public String commentOnAStory(Comment comment) throws JsonProcessingException {
        
       String uri = url + "/add";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(comment)));
        return response.readEntity(String.class);
    }

  
     // Not sure about this one **
    public List<Comment> getAllComments(Story story) throws JsonProcessingException {
        
        String uri = url + "/getAll";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);

        List<Comment> comments = null;
    
        comments = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Comment>>() {
        });
        return comments;
    }
    
    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
    
}
