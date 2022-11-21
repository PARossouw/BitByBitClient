package RestClientRemoteController;

import Category.Model.Category;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

    public class RestClientCategory {
    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;

    public RestClientCategory(String url) {
        this.url = url + "/Category";
        this.mapper = new ObjectMapper();
    }

    public List<Category> displayAllCategories() throws JsonProcessingException {
        String uri = url + "/displayAll";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        
        List<Category> allCategories = null;
        allCategories = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Category>>() {
        });
        return allCategories;
    }

    public String addCategoriesToStory(Story story, List<Category> categories) throws JsonProcessingException {
        String uri = url + "/addToStory";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        
        JSONObject jsonObject = new JSONObject();
        Map<Story, List> categoriesToAdd = new HashMap();
        categoriesToAdd.put(story, categories);
        
        jsonObject.put("story", story);
        jsonObject.put("categories", categories);
        
        Response response = null;
        //response = webTarget.request().post(Entity.json(toJsonString(categoriesToAdd)));
        response = webTarget.request().post(Entity.json(toJsonString(jsonObject)));
        return response.readEntity(String.class);
    }

    public List<Category> topCategoriesForTheMonth() throws JsonProcessingException {
        String uri = url + "/topForMonth";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);

        List<Category> topCategories = null;
        topCategories = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Category>>() {
        });
        return topCategories;
    }
    
    public List<Category> getPreferredCategories(Reader reader) throws JsonProcessingException{
        String uri = url + "/preferredCategories";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        
        List<Category> preferredCategories = null;
        preferredCategories = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<List<Category>>() {
        });
        return preferredCategories;
    }
    
    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
