package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pegs implements Serializable{
    private int total_count;
    private List<Type> types;
    
    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public List<Type> getTypes() {
        return types;
    }
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public JsonObjectBuilder toJSON(){
        // create JSON array first
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfTypes = this.getTypes()
            .stream()
            .map(t -> t.toJSON()) // convert type to JSON object
            .toList();
        // add all JSON objects into arrBuilder
        for (JsonObjectBuilder x : listOfTypes) {
            arrBuilder.add(x);
        }
        return Json.createObjectBuilder()
            .add("total_count", this.getTotal_count())
            .add("types", arrBuilder);
    }

    public static Pegs createJson(JsonObject o) {
        Pegs pp = new Pegs();
        List<Type> result = new LinkedList<>();
        JsonNumber totCnt = o.getJsonNumber("total_count");
        JsonArray types = o.getJsonArray("types");
        pp.setTotal_count(totCnt.intValue());
        // from JSON array, perform a for loop
        for (int i = 0; i < types.size(); i++) {
            JsonObject x = types.getJsonObject(i);
            Type t = Type.createJson(x); // get Type object from JSON
            result.add(t); // add Type object to list
        }
        pp.setTypes(result);
        return pp;
    }
}
