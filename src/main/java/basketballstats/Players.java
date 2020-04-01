package basketballstats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Players {
    @JsonIgnoreProperties(ignoreUnknown = true)

    @JsonProperty("first_name")
    private String first_name;
    public String getFirstName() { return this.first_name; }
    public void setFirstName(String first_name) { this.first_name = first_name; }

    @JsonProperty("last_name")
    private String last_name;
    public String getLastName() { return this.last_name; }
    public void setLastName(String last_name) { this.last_name = last_name; }

    @JsonProperty("position")
    private String position;
    public String getPosition() { return this.position; }
    public void setPosition(String position) { this.position = position; }

    @JsonProperty("height_feet")
    private int height_feet;
    public int getHeightFeet() { return this.height_feet; }
    public void setHeightFeet(int height_feet) { this.height_feet = height_feet; }

    @JsonProperty("height_inches")
    private int height_inches;
    public int getHeightInches() { return this.height_inches; }
    public void setHeightInches(int height_inches) { this.height_inches = height_inches; }

    @JsonProperty("weight_pounds")
    private int weight;
    public int getWeight() { return this.weight; }
    public void setWeight(int weight) { this.weight = weight; }

    @SuppressWarnings("unchecked")
    @JsonProperty("team")
    private int team_id;
    private void unpackTeam(Map<String, Object> team) {
        this.team_id = (int)team.get("id");
    }
    public int getId() { return this.team_id; }

    public Players() {}
}