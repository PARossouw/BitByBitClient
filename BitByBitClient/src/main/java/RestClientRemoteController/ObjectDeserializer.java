package RestClientRemoteController;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import java.io.IOException;


public class ObjectDeserializer extends KeyDeserializer { 

    @Override
    public Object deserializeKey(String string, DeserializationContext dc) throws IOException {
        
       
        return null;
    }
}