package RestClientRemoteController;

import Story.Model.Story;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class ExampleJacksonModule extends SimpleModule
{
    public ExampleJacksonModule()
    {
        addKeyDeserializer(
            Story.class,
            new ObjectDeserializer() );
    }
}
