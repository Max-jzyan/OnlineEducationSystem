package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * This class is to test the methods in Course class
 */

public class TestCourse {
    private Course math;
    private Course calculus;

    @BeforeEach
    void runBefore() {
        math = new Course();
        calculus = new Course();
    }

    @Test
    void testConstructor() {
        assertEquals(math.getTimeLength(),0);
        assertEquals(math.getNumberOfBeingWatched(),0);
        assertEquals(math.getNumberOfComments(),0);
    }

    @Test
    void testGettersAndSettersTimeLength() {
        // TimeLength
        math.setTimeLength(90);
        calculus.setTimeLength(100);
        assertEquals(90,math.getTimeLength());
        assertEquals(100, calculus.getTimeLength());
        
 
    }

    
    @Test
    void testGettersAndSettersContent() {
        // Content
        math.setContent("High School Education");
        calculus.setContent("University Education");
        assertEquals("High School Education",math.getContent());
        assertEquals("University Education",calculus.getContent());
        
    }

    @Test
    void testGettersAndSettersStarRating() {
        // Star Rating
        math.setStarRating(4);
        calculus.setStarRating(5);
        assertEquals(4,math.getStarRating());
        assertEquals(5, calculus.getStarRating());     
    }

    @Test
    void testGettersAndSettersCreator() {
         // Creator
        math.setCreator("UHill Secondary School");
        calculus.setCreator("UBC");
        assertEquals("UHill Secondary School", math.getCreator());
        assertEquals("UBC",calculus.getCreator());
    }

    @Test
    void testGettersAndSettersNumberOfBeingWatched() {
        math.setNumberOfBeingWatched(10);
        calculus.setNumberOfBeingWatched(100);
        assertEquals(10, math.getNumberOfBeingWatched());
        assertEquals(100, calculus.getNumberOfBeingWatched());
    }

    @Test
    void testGettersAndSettersNumberOfComments() {
        math.setNumberOfComments(599);
        calculus.setNumberOfComments(1000);
        assertEquals(599, math.getNumberOfComments());
        assertEquals(1000, calculus.getNumberOfComments());
    }

    @Test
    void testGettersAndSettersAdsAge() {
        // age
        math.setAgeLimited(false);
        calculus.setAgeLimited(true);
        assertFalse(math.isAgeLimited());
        assertTrue(calculus.isAgeLimited());

        math.setAgeLimited(true);
        assertTrue(math.isAgeLimited());
        math.setAgeLimited(true);
        assertTrue(math.isAgeLimited());
        math.setAgeLimited(false);
        assertFalse(math.isAgeLimited());

        // ads
        math.setIncludeAds(false);
        calculus.setIncludeAds(true);
        assertFalse(math.isIncludeAds());
        assertTrue(calculus.isIncludeAds());

        math.setIncludeAds(true);
        assertTrue(math.isIncludeAds());
        math.setIncludeAds(true);
        assertTrue(math.isIncludeAds());
        math.setIncludeAds(false);
        assertFalse(math.isIncludeAds());

    }

    @Test
    void testGettersAndSettersCourseName() {
        math.setCourseName("Trignometry");
        calculus.setCourseName("Advanced Calculus");
        assertEquals("Trignometry", math.getCourseName());
        assertEquals("Advanced Calculus", calculus.getCourseName());
    }

    @Test
    void testAddTime() {
        math.setTimeLength(90);
        math.addTime(100);
        assertEquals(190, math.getTimeLength());
    }

    @Test
    void testReduceTime() {
        math.setTimeLength(90);
        math.reduceTime(89);
        assertEquals(1, math.getTimeLength());
        math.reduceTime(100);
        assertEquals(0,math.getTimeLength());
    }

    @Test
    void testAddNumberOfBeingWatched() {
        math.setNumberOfBeingWatched(100);
        math.addNumberOfBeingWatched(20);
        assertEquals(120,math.getNumberOfBeingWatched());
    }

    @Test
    void testAddNumberOfComments() {
        math.setNumberOfComments(500);
        math.addNumberOfComments(23);
        assertEquals(523, math.getNumberOfComments());
    }


}
