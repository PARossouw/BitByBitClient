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

    public String sendMessage(String xmlMessage) throws JsonProcessingException {
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target(url);
        Response response = null;
        try {

            //String xmlToString = sw.toString();
//            String xmlToString = "<smsreq>"
//                    + "<datetime>2022/05/14.10:10:10</datetime>"
//                    + "<user>GROUP2</user>"
//                    + "<pass>2group</pass>"
//                    + "<msisdn>0739068691</msisdn>"
//                    + "<message>Hello Anton, you have successfully sent a msg</message>"
//                    + "</smsreq>";

            response = webTarget.request().post(Entity.xml(xmlMessage));
        } catch (Exception ex) {
            Logger.getLogger(RestClientSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.readEntity(String.class);
    }

//    private String toJsonString(Object o) throws JsonProcessingException {
//        return mapper.writeValueAsString(o);
//    }

}
