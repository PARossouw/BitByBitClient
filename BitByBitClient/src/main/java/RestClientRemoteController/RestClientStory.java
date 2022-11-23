package RestClientRemoteController;

import Category.Model.Category;
import Story.Model.Story;
import User.Model.Reader;
import User.Model.Writer;
import com.fasterxml.jackson.core.JsonProcessingException;
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

public class RestClientStory {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;

    public RestClientStory(String url) {
        this.url = url + "/RIP/Story";
        this.mapper = new ObjectMapper();
    }

    public List<Story> searchStoriesByCategories(List<Category> categories) throws JsonProcessingException {
        String uri = url + "/search/categories/{categories}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("categories", categories);
        List<Story> stories = null;
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        return stories;

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
    }

    public List<Story> searchForStory(String storyParameter) throws JsonProcessingException {
        String uri = url + "/search/";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri + storyParameter);
        List<Story> stories = null;
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        return stories;
    }
    
    public List<Story> getFiveStoriesForStoryOfTheDay()throws JsonProcessingException{
        String uri = url + "/getFiveStoriesForStoryOfTheDay";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        List<Story> stories = new ArrayList();
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        
        
        
        List<Story> storyList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
                
        Story story = new Story(1, "mock title", "mock writer", "mock description", "mock imagepath", "mock body", false, true, cal, true, true, 10, 57, 4.0);
        storyList.add(story);
        storyList.add(story);
        storyList.add(story);
        storyList.add(story);
        storyList.add(story);
        
        
        
        return stories;
        
    }
    
    public List<Story> viewLikedStories(Reader reader) throws JsonProcessingException {
        String uri = url + "/viewLikedStories/{reader}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("reader", reader);
        List<Story> stories = null;
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        
        return stories;
    }

    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }

}
