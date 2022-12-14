package RestClientRemoteController;

import Story.Model.Story;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RestClientStory {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper = new ObjectMapper();
    //private Story story;

    public RestClientStory(String url) {
        this.url = url + "/Story";
        this.mapper = new ObjectMapper();
    }

    public List<Story> searchStoriesByCategories(User reader) throws JsonProcessingException {
        String uri = url + "/search/categories/{reader}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("reader", reader.getUserID());
        List<Story> stories = new ArrayList();
        stories = Arrays.asList(
                mapper.readValue(
                        webTarget.request().accept(
                                MediaType.APPLICATION_JSON).get(
                                        String.class), Story[].class));
        return stories;
    }

    public List<Story> searchStoriesByRandomCategoriesChosen(String categoryStr) throws JsonProcessingException {
        String uri = url + "/search/categories/random/{categoryStr}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("categoryStr", categoryStr);
        List<Story> stories = new ArrayList();
        stories = new ArrayList(Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class)));
        return stories;
    }

    public List<Story> searchStoriesByTitleorAuthor(String searchText) throws JsonProcessingException {
        String uri = url + "/search/stories/{searchText}";
        restClient = ClientBuilder.newClient();

        webTarget = restClient.target(uri).resolveTemplate("searchText", searchText);
        List<Story> stories = new ArrayList();
        stories = new ArrayList(Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class)));

        return stories;

    }

    public List<Story> viewStoriesByWriter(Integer writerID) throws JsonProcessingException {
        User writer = new User();
        writer.setUserID(writerID);

        String uri = url + "/viewByWriter/{writerID}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("writerID", writer.getUserID());

        List<Story> stories = new ArrayList(
                Arrays.asList(
                        mapper.readValue(
                                webTarget.request().accept(
                                        MediaType.APPLICATION_JSON).get(
                                                String.class), Story[].class)));
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

    public Story retrieveStoryGet(String storyID) {
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
        String uri = url + "/search";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri + storyParameter);
        List<Story> stories = new ArrayList();
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));
        return stories;
    }

    public List<Story> storyReview() throws JsonProcessingException {
        String uri = url + "/getPendingStories";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        List<Story> stories = new ArrayList();
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));

        return stories;
    }

    public List<Story> getStoriesForStoryOfTheDay() throws JsonProcessingException {
        String uri = url + "/getStoriesForStoryOfTheDay";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        List<Story> stories = new ArrayList();
        stories = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));

        return stories;
    }

    public List<Story> viewLikedStories(Integer readerID) throws JsonProcessingException {
        User reader = new User();
        reader.setUserID(readerID);
        String uri = url + "/viewLikedStories/{readerID}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("readerID", reader.getUserID());

        List<Story> stories = new ArrayList(
                Arrays.asList(
                        mapper.readValue(
                                webTarget.request().accept(
                                        MediaType.APPLICATION_JSON).get(
                                                String.class), Story[].class)));
        return stories;
    }

    public String turnOffComments(Story story) throws JsonProcessingException {
        String uri = url + "/turnOffComments";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(story)));
        return response.readEntity(String.class);

    }

    public Map<String, Integer> getTop20StoriesForMonth(String month) throws JsonProcessingException {
        String uri = url + "/getTop20StoriesForMonth/{month}";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri).resolveTemplate("month", month);
        Map<String, Integer> topStories = new HashMap<>();

        topStories = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<HashMap<String, Integer>>() {
        });
        return topStories;
    }

    public List<Story> getTop20StoriesForMonth() throws JsonProcessingException {
        String uri = url + "/getTop20StoriesForMonth";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        List<Story> topStories = new ArrayList<>();

        topStories = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Story>>() {
        });
        return topStories;
    }

    public List<Story> getRandomApprovedStories() throws JsonProcessingException {
        String uri = url + "/getRandomApprovedStories";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        List<Story> storiesNew = new ArrayList();
        storiesNew = Arrays.asList(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story[].class));

        return storiesNew;
    }

    public List<Story> viewPendingStories() throws JsonProcessingException {
        String uri = url + "/getPendingStories";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);

        List<Story> pendingStories = new ArrayList(
                Arrays.asList(
                        mapper.readValue(
                                webTarget.request().accept(
                                        MediaType.APPLICATION_JSON).get(
                                                String.class), Story[].class)));
        return pendingStories;
    }

    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }

    public String makeStoryOfTheDay(Story story) throws JsonProcessingException {
        String uri = url + "/makeStoryOfTheDay";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;

        response = webTarget.request().post(Entity.json(toJsonString(story)));
        return response.readEntity(String.class);
    }

    public Story getStoryOfTheDay() throws JsonProcessingException {

        String uri = url + "/getStoryOfTheDay";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;

        return(mapper.readValue(webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), Story.class));

    }

    public String blockStory(Story toBlock) throws JsonProcessingException {
        
        String uri = url + "/story/block";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;

        response = webTarget.request().post(Entity.json(toJsonString(toBlock)));

        return response.readEntity(String.class);
    }

    public String incrementViews(Story story) throws JsonProcessingException {
        String uri = url + "/story/incrementViews";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;

        response = webTarget.request().post(Entity.json(toJsonString(story)));

        return response.readEntity(String.class);
    }

}
