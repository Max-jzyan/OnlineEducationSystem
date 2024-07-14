package model;

import java.util.ArrayList;
import java.util.List;


public class LikedCourses {
    private List<Course> listOfCourses;

    public LikedCourses() {
        listOfCourses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        listOfCourses.add(course);
    }

    public void removeCourse(Course course) {
        for(int i = 0;i < listOfCourses.size();i++) {
            if(listOfCourses.get(i).equals(course)) {
                listOfCourses.remove(i);
            }
        }
    }

    public void removeCourse(String courseName) {
        Course course = getCourse(courseName);
        removeCourse(course);
    }

    public Course getCourse(String courseName) {
        for(int i = 0;i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if(tempCourse.getCourseName() == courseName) {
                return tempCourse;
            } 
        }
        return null;
    }

    // EFFECTS: filter out all the courses having or not having ads
    public LikedCourses isAds(boolean Ads) {
        LikedCourses adCourses = new LikedCourses();
        for(int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if(tempCourse.isIncludeAds() == Ads) {
                adCourses.addCourse(tempCourse);
            }
        }
        return adCourses;
    }

    public LikedCourses isAgeLimited(boolean age) {
        LikedCourses ageCourses = new LikedCourses();
        for(int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if(tempCourse.isAgeLimited() == age) {
                ageCourses.addCourse(tempCourse);
            }
        }
        return ageCourses;
    }

    public LikedCourses idealTimeRange(int min, int max) {
        LikedCourses timeCourses = new LikedCourses();
        for(int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if(tempCourse.getTimeLength() <= max && tempCourse.getTimeLength() >= min) {
                timeCourses.addCourse(tempCourse);
            }
        }
        return timeCourses;
    }

    public LikedCourses idealStarRating(int min, int max) {
        LikedCourses starCourses = new LikedCourses();
        for(int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if(tempCourse.getStarRating() <= max && tempCourse.getStarRating() >= min) {
                starCourses.addCourse(tempCourse);
            }
        }
        return starCourses;
    }

    public LikedCourses idealComments(int min, int max) {
        LikedCourses commentCourses = new LikedCourses();
        for(int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if(tempCourse.getNumberOfComments() <= max && tempCourse.getNumberOfComments() >= min) {
                commentCourses.addCourse(tempCourse);
            }
        }
        return commentCourses;
    }

    public LikedCourses idealNumberOfWatched(int min, int max) {
        LikedCourses watchedCourses = new LikedCourses();
        for(int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if(tempCourse.getNumberOfBeingWatched() <= max && tempCourse.getNumberOfBeingWatched() >= min) {
                watchedCourses.addCourse(tempCourse);
            }
        }
        return watchedCourses;
    }

    public LikedCourses idealCreator(String str) {
        LikedCourses creatorCourses = new LikedCourses();
        for(int i = 0;i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if(tempCourse.getCreator() == str) {
                creatorCourses.addCourse(tempCourse);
            }
        }
        return creatorCourses;
    }

    public List<Course> viewLikedCourses() {
        return listOfCourses;
    }





}
