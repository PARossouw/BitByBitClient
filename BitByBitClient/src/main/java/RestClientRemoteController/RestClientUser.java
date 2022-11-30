package RestClientRemoteController;

import Category.Model.Category;
import User.Model.Editor;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

public class RestClientUser {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;

    public RestClientUser(String url) {
        this.url = url + "/User";
        this.mapper = new ObjectMapper();
    }

    public User login(User user) throws JsonProcessingException {
    public String login(User user) throws JsonProcessingException {
        String uri = url + "/login";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);

        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(user)));
    }

    //@FormParam must be put in the aruguments on the rest controller side
    public String addPreferredCategoriesToUser(Reader reader, List<Category> categories) throws JsonProcessingException {
        String uri = url + "/categories/add";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        HashMap<Reader, List> preferredCategories = new HashMap();
        preferredCategories.put(reader, categories);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(preferredCategories)));
        return response.readEntity(String.class);
    }

    public String registerUser(User user) throws JsonProcessingException {
        String uri = url + "/register";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(user)));
        return response.readEntity(String.class);
    }

    public String blockWriter(Writer writer) throws JsonProcessingException {
        String uri = url + "/writer/block";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;

        response = webTarget.request().post(Entity.json(toJsonString(writer)));

        return response.readEntity(String.class);
    }

    public String addNewEditor(Editor editor) throws JsonProcessingException {
        String uri = url + "/editor/add";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(editor)));
        return response.readEntity(String.class);
    }

    public String removeEditor(Editor editor) throws JsonProcessingException {
        String uri = url + "/editor/remove";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);
        Response response = null;
        response = webTarget.request().post(Entity.json(toJsonString(editor)));
        return response.readEntity(String.class);
    }

    public Map<Writer, Integer> topWriters() throws JsonProcessingException {
        String uri = url + "/writer/top";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);

        Map<Writer, Integer> writers = null;
        writers = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<Map<Writer, Integer>>() {
        });
        return writers;
    }

    public Map<Writer, Integer> topRejectedWritersForMonth() throws JsonProcessingException {
        String uri = url + "/writer/mostRejected";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);

        Map<Writer, Integer> writers = null;
        writers = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<Map<Writer, Integer>>() {
        });
        return writers;
    }

    public Map<Writer, Integer> topApprovingEditors() throws JsonProcessingException {
        String uri = url + "/editor/mostApproving";
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(uri);

        Map<Writer, Integer> writers = null;
        writers = mapper.readValue(
                webTarget.request().accept(MediaType.APPLICATION_JSON).get(String.class), new TypeReference<Map<Writer, Integer>>() {
        });
        return writers;
    }

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

    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
