package RestClientRemoteController;

import Category.Model.Category;
import Story.Model.Story;
import User.Model.Reader;
import User.Model.User;
import User.Model.Writer;
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
import java.util.Calendar;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;


public class RestClientStory {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;
    //private Story story;

    public RestClientStory(String url) {
        this.url = url + "/Story";
        this.mapper = new ObjectMapper();
    }

    public List<Story> searchStoriesByCategories(List<Category> categories) throws JsonProcessingException {
        String uri = url + "/search/categories";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        List<Story> stories = new ArrayList<>();
        
        JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("amount", categories.size());
        for(int i = 0; i < categories.size(); i++){
            jsonObject.put(""+i, categories.get(i));
        }
            
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(jsonObject)));
        return response.readEntity(List.class);
        
//        Response response = null;
//        
//        response = webTarget.request().post(Entity.json(toJsonString(categories)));
//        //stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
//        //stories = mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Story>>(){});
//        //return stories;
//        stories = response.readEntity(List.class);
//        return stories;

    }

    public List<Story> viewStoriesByWriter(Writer writer) throws JsonProcessingException {
        String uri = url + "/viewByWriter/{writer}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("writer", writer);
        List<Story> stories = null;
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        
        return stories;
    }

    public String saveStory(Story story) throws JsonProcessingException {
        String uri = url + "/save";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;
        
        response = webTarget.request().post(Entity.json(toJsonString(story)));
        return response.readEntity(String.class);
//        return storyReturn;
    }

    public String submitCompletedStory(Story story) throws JsonProcessingException {
        String uri = url + "/submit";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(story)));
        return response.readEntity(String.class);
    }

    public Story retrieveStory(Story story) throws JsonProcessingException {
        String uri = url + "/retrieve";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(story)));
        return response.readEntity(Story.class);

// test purposes below 
//Story storyObj = new Story();
//storyObj.setStoryID(420);
//        storyObj.setTitle("DAO practice Title");
//        storyObj.setAvgRating(2.9);
//        storyObj.setWriter("DAO Pratice Author Tarun Sing");
//        storyObj.setDescription("DAO Practice Description");
//        storyObj.setBody("DAO Practice Body");
//        storyObj.setViews(637);
//        storyObj.setLikes(88);
//
//return storyObj;
    }
    public Story retrieveStoryGet(String storyID)
    {
         try {
            String uri = url + "/getStory/{storyID}";
            restClient = ClientBuilder.newClient();
            webTarget = restClient.target(uri).resolveTemplate("storyID", storyID);

            Story story = new Story();
           story = (mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story.class));
//           
//Response response = null;
// response = webTarget.request().post(Entity.json(toJsonString(story)));
// return response.readEntity(Story.class);
return story;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(RestClientUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    }
    
    
    */
    
    
    
    
    
    

    public List<Story> searchForStory(String storyParameter) throws JsonProcessingException {
        String uri = url + "/search/";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri + storyParameter);
        List<Story> stories = null;
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        return stories;
    }
    
    public List<Story> storyReview()throws JsonProcessingException{
        String uri = url + "/getPendingStories";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        List<Story> stories = new ArrayList();
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        
        return stories;
    }
    
    public List<Story> getStoriesForStoryOfTheDay()throws JsonProcessingException{
        String uri = url + "/getStoriesForStoryOfTheDay";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        List<Story> stories = new ArrayList();
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        
        return stories;
    }
    
    public List<Story> viewLikedStories(User reader) throws JsonProcessingException {
        String uri = url + "/viewLikedStories";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("reader", reader.getUsername());
        List<Story> stories = null;
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        
        return stories;
    }

    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }

}
