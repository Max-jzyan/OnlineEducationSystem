package persistence;

import org.json.JSONObject;

// Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// This is the parent of JsonReader and JsonWriter

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
