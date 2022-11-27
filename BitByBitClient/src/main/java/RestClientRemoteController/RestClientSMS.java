package RestClientRemoteController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;

public class RestClientSMS {

    private String url;
    private Client restClient;
    private WebTarget webTarget;
    private ObjectMapper mapper;

    public RestClientSMS(String url) {
        this.url = url;
        this.mapper = new ObjectMapper();
    }

    public String sendMessage(StringWriter sw) throws JsonProcessingException {
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(url);
        Response response = null;
        try {

            String xmlToString = sw.toString();

            response = webTarget.request().post(Entity.xml(xmlToString));
        } catch (Exception ex) {
            Logger.getLogger(RestClientSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

    private String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }

}
