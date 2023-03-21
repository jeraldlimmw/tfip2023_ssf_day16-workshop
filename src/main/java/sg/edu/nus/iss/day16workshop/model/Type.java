package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Type implements Serializable{
    private String type;
    private int count;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    // must create the following two methods: 
    // object to JSON object and create object from JSON

    public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
            .add("type", this.getType())
            .add("count", this.getCount());
    }

    public static Type createJson(JsonObject o) {
        // 1. create new object
        Type t = new Type();
        
        // 2. get the values from the JSON object
        JsonNumber count = o.getJsonNumber("count");
        String type = o.getString("type");
        
        // 3. set objects' values
        t.setCount(count.intValue());
        t.setType(type);
        return t;
    }
    
}
