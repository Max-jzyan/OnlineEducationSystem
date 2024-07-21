package persistence;


import model.Course;
import model.LikedCourses;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;


public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            LikedCourses wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            LikedCourses wr = reader.read();
            assertEquals(0, wr.viewLikedCourses().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            LikedCourses wr = reader.read();
            List<Course> listOfCourses = wr.viewLikedCourses();
            assertEquals(2, listOfCourses.size());
            checkCourse(listOfCourses.get(0),100,"Calculus I",4,"Max",0,0,false,false,"Math100");
            checkCourse(listOfCourses.get(1),60,"Dynamics I",5,"Jane",0,0,true,false,"Phys131");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
