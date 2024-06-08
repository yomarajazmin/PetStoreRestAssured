package api.entitites;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("photoUrls")
    private String photoUrls[];
    @JsonProperty("tags")
    private Tag tags[];
    @JsonProperty("status")
    private String status;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Pet(@JsonProperty("id") int id, @JsonProperty("name") String name,
               @JsonProperty("category") Category category, @JsonProperty("photoUrls") String[] photoUrls,
               @JsonProperty("tags") Tag[] tags, @JsonProperty("status") String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Pet(int value) {
        this.id = value;
        this.name = "Name" + value;
        this.category = new Category();
        this.photoUrls = new String[10];
        this.tags = new Tag[10];
        this.status = "available";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }

    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
