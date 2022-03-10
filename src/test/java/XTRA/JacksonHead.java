package XTRA;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class JacksonHead {
    public String name;
    public int id;

    JacksonHead(@JsonProperty(value = "name") String name, @JsonProperty(value = "id") int id) {
        this.name = name;
        this.id = id;
    }

    protected JacksonHead(String name) {
        this.name = name;
        this.id = 123;
    }

    protected JacksonHead(int id) {
        this.id = id;
        this.name = "Yes!";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
