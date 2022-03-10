package XTRA;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringReader;

public class JacksonTest{

     public void test ()throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String forDeserialize = "{\"name\":\"No!\",\"id\":123}";
        StringReader reader = new StringReader(forDeserialize);

        JacksonHead jacksonHead1 = (JacksonHead) mapper.readValue(reader, JacksonHead.class);






    }
}
