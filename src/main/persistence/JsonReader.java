package persistence;

import model.Course;
import model.LikedCourses;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

// Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
/*
 * This class is used to read from the file "myList.json"
 */

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LikedCourses read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private LikedCourses parseWorkRoom(JSONObject jsonObject) {
        LikedCourses lc = new LikedCourses();
        addThingies(lc, jsonObject);
        return lc;
    }

    // MODIFIES: lc
    // EFFECTS: parses thingies from JSON object and adds them to likedCourses
    private void addThingies(LikedCourses lc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list of courses");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(lc, nextThingy);
        }
    }
        
    // MODIFIES: courses
    // EFFECTS: parses thingy from JSON object and adds it to courses
    private void addThingy(LikedCourses courses, JSONObject jsonObject) {
        int timeLength = jsonObject.getInt("time length");
        String content = jsonObject.getString("content");
        int starRating = jsonObject.getInt("star rating");
        String creator = jsonObject.getString("creator");
        int numberOfBeingWatched = jsonObject.getInt("number of being watched");
        int numberOfComments = jsonObject.getInt("number of comments");
        boolean includeAds = jsonObject.getBoolean("ads");
        boolean ageLimited = jsonObject.getBoolean("age");
        String courseName = jsonObject.getString("name");
        
        Course course = new Course();
        course.setAgeLimited(ageLimited);
        course.setContent(content);
        course.setCourseName(courseName);
        course.setCreator(creator);
        course.setIncludeAds(includeAds);
        course.setNumberOfBeingWatched(numberOfBeingWatched);
        course.setNumberOfComments(numberOfComments);
        course.setStarRating(starRating);
        course.setTimeLength(timeLength);

        courses.addCourse(course);
    }
}
