package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLikedCourses {
    private Course math;
    private Course calculus;
    private Course physics;
    private Course french;
    private LikedCourses worklist;
    @BeforeEach
    void runBefore() {
        math = new Course();
        calculus = new Course();
        physics = new Course();
        french = new Course();
        worklist = new LikedCourses();
    }

    @Test
    void testConstructor() {
        assertEquals(0,worklist.viewLikedCourses().size());
    }

    @Test
    void testAddCourses() {
        assertEquals(0, worklist.viewLikedCourses().size());
        math.setCourseName("Math100");
        worklist.addCourse(math);
        assertEquals(1, worklist.viewLikedCourses().size());
        assertEquals(math, worklist.viewLikedCourses().get(0));
        assertEquals(math.getCourseName(), worklist.viewLikedCourses().get(0).getCourseName());
        worklist.addCourse(physics);
        assertEquals(2, worklist.viewLikedCourses().size());
    }

    // test the one with Course type input
    @Test
    void testRemoveCourse() {
        french.setCourseName("French");
        assertEquals(0, worklist.viewLikedCourses().size());
        worklist.addCourse(calculus);
        assertEquals(1, worklist.viewLikedCourses().size());
        worklist.removeCourse(physics);
        assertEquals(1, worklist.viewLikedCourses().size());
        worklist.removeCourse(calculus);
        assertEquals(0, worklist.viewLikedCourses().size());
        worklist.removeCourse(physics);
        assertEquals(0, worklist.viewLikedCourses().size());
        worklist.addCourse(physics);
        worklist.addCourse(french);
        assertEquals(2, worklist.viewLikedCourses().size());
        worklist.removeCourse(physics);
        assertEquals(1, worklist.viewLikedCourses().size());
        Course course = worklist.getCourse("French");
        assertEquals(course, french);
    }

    // test the one with String input
    @Test
    void testRemoveCourseString() {
        french.setCourseName("French");
        calculus.setCourseName("Advanced Calculus");
        physics.setCourseName("Dynamics");
        assertEquals(0, worklist.viewLikedCourses().size());
        worklist.addCourse(calculus);
        assertEquals(1, worklist.viewLikedCourses().size());
        worklist.removeCourse("Advanced Calculus");
        assertEquals(0, worklist.viewLikedCourses().size());
        worklist.addCourse(physics);
        worklist.addCourse(french);
        assertEquals(2, worklist.viewLikedCourses().size());
        worklist.removeCourse("Dynamics");
        assertEquals(1, worklist.viewLikedCourses().size());
        Course course = worklist.getCourse("French");
        assertEquals(course, french);
    }

    @Test
    void testGetCourse() {
        french.setCourseName("French");
        assertEquals(0, worklist.viewLikedCourses().size());
        worklist.addCourse(french);
        Course course = worklist.getCourse("French");
        assertEquals(french,course);
        course = worklist.getCourse("none");
        assertEquals(null,course);
    }

    @Test
    void testFilterAge() {
        french.setAgeLimited(true);
        physics.setAgeLimited(true);
        calculus.setAgeLimited(false);
        math.setAgeLimited(false);
        french.setCourseName("French");
        physics.setCourseName("Physics");
        worklist.addCourse(french);
        worklist.addCourse(physics);
        worklist.addCourse(calculus);
        worklist.addCourse(math);
        LikedCourses idealResultTrue = new LikedCourses();
        idealResultTrue.addCourse(french);
        idealResultTrue.addCourse(physics);
        LikedCourses idealResultFalse = new LikedCourses();
        idealResultFalse.addCourse(calculus);
        idealResultFalse.addCourse(math);
        // assertTrue(idealResultTrue.equals(worklist.isAgeLimited(true)));
        assertEquals(french, worklist.isAgeLimited(true).viewLikedCourses().get(0));
        assertEquals(physics, worklist.isAgeLimited(true).viewLikedCourses().get(1));
        assertEquals(idealResultTrue.viewLikedCourses().get(0), worklist.isAgeLimited(true).viewLikedCourses().get(0));
        assertEquals(idealResultTrue.viewLikedCourses().get(1), worklist.isAgeLimited(true).viewLikedCourses().get(1));
    }

    @Test
    void testFilterAds() {
        french.setIncludeAds(true);
        physics.setIncludeAds(true);
        calculus.setIncludeAds(false);
        math.setIncludeAds(false);
        worklist.addCourse(french);
        worklist.addCourse(physics);
        worklist.addCourse(calculus);
        worklist.addCourse(math);
        LikedCourses idealResultTrue = new LikedCourses();
        idealResultTrue.addCourse(french);
        idealResultTrue.addCourse(physics);
        LikedCourses idealResultFalse = new LikedCourses();
        idealResultFalse.addCourse(calculus);
        idealResultFalse.addCourse(math);
        assertEquals(french, worklist.isAds(true).viewLikedCourses().get(0));
        assertEquals(physics, worklist.isAds(true).viewLikedCourses().get(1));
        assertEquals(idealResultTrue.viewLikedCourses().get(0), worklist.isAds(true).viewLikedCourses().get(0));
        assertEquals(idealResultTrue.viewLikedCourses().get(1), worklist.isAds(true).viewLikedCourses().get(1));
        // assertEquals(idealResultTrue, worklist.isAds(true));
        // assertEquals(idealResultFalse,worklist.isAds(false));
    }

    @Test
    void testIdealTimeRange() {
        assertEquals(0, worklist.viewLikedCourses().size());
        math.setTimeLength(100);
        calculus.setTimeLength(120);
        worklist.addCourse(math);
        worklist.addCourse(calculus);
        LikedCourses checker = worklist.idealTimeRange(100,120);
        assertEquals(math, checker.viewLikedCourses().get(0));
        assertEquals(calculus, checker.viewLikedCourses().get(1));
        assertEquals(2, checker.viewLikedCourses().size());
        checker = worklist.idealTimeRange(110,120);
        assertEquals(calculus, checker.viewLikedCourses().get(0));
        assertEquals(1, checker.viewLikedCourses().size());
        checker = worklist.idealTimeRange(90,110);
        assertEquals(math, checker.viewLikedCourses().get(0));
        assertEquals(1, checker.viewLikedCourses().size());  
    }

    @Test
    void testIdealStarRating() {
        assertEquals(0, worklist.viewLikedCourses().size());
        math.setStarRating(100);
        calculus.setStarRating(120);
        worklist.addCourse(math);
        worklist.addCourse(calculus);
        LikedCourses checker = worklist.idealStarRating(100,120);
        assertEquals(math, checker.viewLikedCourses().get(0));
        assertEquals(calculus, checker.viewLikedCourses().get(1));
        assertEquals(2, checker.viewLikedCourses().size());
        checker = worklist.idealStarRating(110,120);
        assertEquals(calculus, checker.viewLikedCourses().get(0));
        assertEquals(1, checker.viewLikedCourses().size());
        checker = worklist.idealStarRating(90,110);
        assertEquals(math, checker.viewLikedCourses().get(0));
        assertEquals(1, checker.viewLikedCourses().size());  
    }

    @Test
    void testIdealCreator() {
        assertEquals(0, worklist.viewLikedCourses().size());
        math.setCreator("Max");
        calculus.setCreator("Max");
        french.setCreator("Jane");
        worklist.addCourse(math);
        worklist.addCourse(calculus);
        worklist.addCourse(french);
        LikedCourses checker = worklist.idealCreator("Max");
        assertEquals(math, checker.viewLikedCourses().get(0));
        assertEquals(calculus, checker.viewLikedCourses().get(1));
        assertEquals(2, checker.viewLikedCourses().size());
    }
}
