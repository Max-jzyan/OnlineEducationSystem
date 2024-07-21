package persistence;


import model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(Course course, int timeLength, String content, 
            int starRating, String creator, int numberOfBeingWatched, 
                int numberOfComments, boolean includeAds, boolean ageLimited, String courseName) {
        assertEquals(timeLength, course.getTimeLength());
        assertEquals(content, course.getContent());
        assertEquals(starRating, course.getStarRating());
        assertEquals(creator,course.getCreator());
        assertEquals(includeAds,course.isIncludeAds());
        assertEquals(ageLimited,course.isAgeLimited());
        assertEquals(numberOfBeingWatched, course.getNumberOfBeingWatched());
        assertEquals(numberOfComments,course.getNumberOfComments());
        assertEquals(courseName, course.getCourseName());
    }
}
