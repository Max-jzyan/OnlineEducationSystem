package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// The class name is LikedCourses, containing one field which is list of courses.
// It stores all the courses users like, and user can add or remove courses or filter the courses.

public class LikedCourses implements Writable {
    private List<Course> listOfCourses;

    // EFFECTS: construct a LikedCourse with an empty list of Course
    public LikedCourses() {
        listOfCourses = new ArrayList<>();
    }
    // REQUIRES: No such course in the list
    // MODIFIES: this
    // EFFECTS: add a course into the end of the list

    public void addCourse(Course course) {
        listOfCourses.add(course);
        EventLog.getInstance().logEvent(new Event(course.getCourseName() + " added into your liked list."));
    }

    // MODIFIES: this
    // EFFECTS: if a given course inside the list, then remove it;
    //          if not, then do nothing;

    public void removeCourse(Course course) {
        for (int i = 0;i < listOfCourses.size();i++) {
            if (listOfCourses.get(i).equals(course)) {
                listOfCourses.remove(i);
                EventLog.getInstance().logEvent(new Event(course.getCourseName() + " deleted from your liked list."));
            }
        }
        
    }

    // MODIFIES: this
    // EFFECTS: remove a course based on it's given course name

    public void removeCourse(String courseName) {
        Course course = getCourse(courseName);
        removeCourse(course);
        EventLog.getInstance().logEvent(new Event(courseName + " deleted from your liked list."));
    }

    // EFFECTS: get a course based on a given course name
    public Course getCourse(String courseName) {
        for (int i = 0;i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if (tempCourse.getCourseName().equals(courseName)) {
                return tempCourse;
            } 
        }
        return null;
    }

    // EFFECTS: filter out all the courses having or not having ads

    public LikedCourses isAds(boolean ads) {
        LikedCourses adCourses = new LikedCourses();
        for (int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if (tempCourse.isIncludeAds() == ads) {
                adCourses.addCourse(tempCourse);
            }
        }
        EventLog.getInstance().logEvent(
            new Event("Your liked courses list has been filtered based on having Ads or not"));
        return adCourses;
    }
    // EFFECTS: filter out all the courses having or not having age limit

    public LikedCourses isAgeLimited(boolean age) {
        LikedCourses ageCourses = new LikedCourses();
        for (int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if (tempCourse.isAgeLimited() == age) {
                ageCourses.addCourse(tempCourse);
            }
        }
        EventLog.getInstance().logEvent(
            new Event("Your liked courses list has been filtered based on age limited"));
        return ageCourses;
    }

    // EFFECTS: filter out all the courses fulfilling the given time range

    public LikedCourses idealTimeRange(int min, int max) {
        LikedCourses timeCourses = new LikedCourses();
        for (int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if (tempCourse.getTimeLength() <= max && tempCourse.getTimeLength() >= min) {
                timeCourses.addCourse(tempCourse);
            }
        }
        return timeCourses;
    }

    // EFFECTS: filter out all the courses fulfilling the given star rating range

    public LikedCourses idealStarRating(int min, int max) {
        LikedCourses starCourses = new LikedCourses();
        for (int i = 0; i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if (tempCourse.getStarRating() <= max && tempCourse.getStarRating() >= min) {
                starCourses.addCourse(tempCourse);
            }
        }
        return starCourses;
    }

    // // EFFECTS: filter out all the courses fulfilling the given range of comments
    // public LikedCourses idealComments(int min, int max) {
    //     LikedCourses commentCourses = new LikedCourses();
    //     for(int i = 0; i < listOfCourses.size();i++) {
    //         Course tempCourse = listOfCourses.get(i);
    //         if(tempCourse.getNumberOfComments() <= max && tempCourse.getNumberOfComments() >= min) {
    //             commentCourses.addCourse(tempCourse);
    //         }
    //     }
    //     return commentCourses;
    // }


    // // EFFECTS: filter out all the courses fulfilling the given range of number of being watched
    // public LikedCourses idealNumberOfWatched(int min, int max) {
    //     LikedCourses watchedCourses = new LikedCourses();
    //     for(int i = 0; i < listOfCourses.size();i++) {
    //         Course tempCourse = listOfCourses.get(i);
    //         if(tempCourse.getNumberOfBeingWatched() <= max && tempCourse.getNumberOfBeingWatched() >= min) {
    //             watchedCourses.addCourse(tempCourse);
    //         }
    //     }
    //     return watchedCourses;
    // }

    // EFFECTS: filter out all the courses fulfilling the given creator

    public LikedCourses idealCreator(String str) {
        LikedCourses creatorCourses = new LikedCourses();
        for (int i = 0;i < listOfCourses.size();i++) {
            Course tempCourse = listOfCourses.get(i);
            if (tempCourse.getCreator() == str) {
                creatorCourses.addCourse(tempCourse);
            }
        }
        return creatorCourses;
    }

    // EFFECTS: get all courses in the list
    public List<Course> viewLikedCourses() {
        return listOfCourses;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("list of courses", coursesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : listOfCourses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }



}
