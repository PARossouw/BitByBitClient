
package RestClientRemoteController;
import Story.Model.Story;
import User.Model.Writer;
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
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class RestClientComment {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper = new ObjectMapper();
    private Comment comment;

    public RestClientComment(String url) {
        this.url = url + "/Comment";
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
    public List<Comment> getAllComments(String storySearch) throws JsonProcessingException {
        
//        String uri = url + "/getAll";
//        restClient = ClientBuilder.newClient();
//        webTarget = restClient.target(uri);


// Start of altered code 

//        List<Comment> comments = new ArrayList();
List<Comment> comments;
         try {
            String uri = url + "/getAllComments/{storySearch}";
            restClient = ClientBuilder.newClient();
            webTarget = restClient.target(uri).resolveTemplate("storySearch", storySearch);

            comments = new ArrayList(Arrays.asList(
                    mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Comment[].class)));

            return comments;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientUser.class.getName()).log(Level.SEVERE, null, ex);
        }
//        return comments;
return null;
//        
        
        //End of Altered Code
        //
        
        // << Test Code 
//        List<Comment> allStoryComments = new ArrayList();
//            Comment testComment = new Comment();
//                testComment.setCommentBody("Good StoryLine");
//
//                Comment testComment2 = new Comment();
//                testComment2.setCommentBody("Nice Plot twist");
//
//                Comment testComment3 = new Comment();
//                testComment3.setCommentBody("Long and insightful");
//
//                allStoryComments.add(testComment);
//                allStoryComments.add(testComment2);
//                allStoryComments.add(testComment3);
//            return allStoryComments;
        // >> End of Test code
    
        
        
        
        
        
        
    
//        comments = mapper.readValue(
//                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Comment>>() {
//        });
      //  return comments;
    }
    
    /*
    public List<Writer> searchWriter(String writerSearch) {
        List<Writer> writers = new ArrayList();
        try {
            String uri = url + "/searchWriter/{writerSearch}";
            restClient = ClientBuilder.newClient();
            webTarget = restClient.target(uri).resolveTemplate("writerSearch", writerSearch);

            writers = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Writer[].class));

            return writers;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return writers;
    
    */
    
    
    
    
    
    
    
    
    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
    
}
