package persistence;

import model.Course;
import model.LikedCourses;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
/*
 * This class includes all the test method for JsonWriter
 */

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            LikedCourses wr = new LikedCourses();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            LikedCourses wr = new LikedCourses();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            wr = reader.read();
            assertEquals(0, wr.viewLikedCourses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            LikedCourses wr = new LikedCourses();
            Course courseOne = new Course();
            createCourseOne(courseOne);
            Course courseTwo = new Course();
            createCourseTwo(courseTwo);

            wr.addCourse(courseOne);
            wr.addCourse(courseTwo);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            wr = reader.read();
            List<Course> courses = wr.viewLikedCourses();
            assertEquals(2, courses.size());
            checkCourse(courses.get(0),100,"Calculus I",4,"Max",0,0,false,false,"Math100");
            checkCourse(courses.get(1),60,"Dynamics I",5,"Jane",0,0,true,false,"Phys131");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    void createCourseOne(Course courseOne) {
        courseOne.setAgeLimited(false);
        courseOne.setContent("Calculus I");
        courseOne.setCourseName("Math100");
        courseOne.setCreator("Max");
        courseOne.setIncludeAds(false);
        courseOne.setNumberOfBeingWatched(0);
        courseOne.setNumberOfComments(0);
        courseOne.setStarRating(4);
        courseOne.setTimeLength(100);
    }

    void createCourseTwo(Course courseTwo) {
        courseTwo.setAgeLimited(false);
        courseTwo.setContent("Dynamics I");
        courseTwo.setCourseName("Phys131");
        courseTwo.setCreator("Jane");
        courseTwo.setIncludeAds(true);
        courseTwo.setNumberOfBeingWatched(0);
        courseTwo.setNumberOfComments(0);
        courseTwo.setStarRating(5);
        courseTwo.setTimeLength(60);
    }
}
