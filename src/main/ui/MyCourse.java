package ui;

import model.Course;
import model.LikedCourses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyCourse {
    // The following are given courses
    Course math = new Course();
    Course physics = new Course();
    Course chemistry = new Course();
    Course business = new Course();
    List<Course> allCourses = new ArrayList<>();
    LikedCourses myList;
    private Scanner scanner;
    private boolean isProgramRunning;
    // private int currentCourseIndex = 0;

    public MyCourse() {
        init();
        initialCoursesList();
        // test
        // myList.addCourse(math);
        // System.out.println(myList.viewLikedCourses().get(0).getCourseName());
        System.out.println("Welcome to my online course system");
        while (isProgramRunning) {
            processOnFunctionList();
        }
        
    }

    public void processOnFunctionList() {
        // test
        // myList.addCourse(math);
        // System.out.println(myList.viewLikedCourses().get(0).getCourseName());
        functionList();
        String typeIn = scanner.nextLine();
        processFunctionCommands(typeIn);

    }

    public void functionList() {
        System.out.println("Select what you want to do:");
        System.out.println("1.Add a Course from a given list of courses into myList");
        System.out.println("2.Delete a course from my video list");
        System.out.println("3.View the info for a specific Course in your video list");
        System.out.println("4.Filter some courses from your video list");
        System.out.println("5.View all the Course Name of your list of course");
        System.out.println("6.Quit");
        dashLine();
    }

    public void processFunctionCommands(String typeIn) {
        switch (typeIn) {
            case "1":
                addCourseIntoList();
                break;
            case "2":
                deleteCourseFromList();
                break;
            case "3":
                viewInfoForACourse();
                break;
            case "4":
                filterCourses();
                break;
            case "5":
                viewCoursesNameOfYourList();
                break;
            case "6":
                quit();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        dashLine();
    }
    
    public void addCourseIntoList() {
        // test
        // myList.addCourse(math);
        // System.out.println(myList.viewLikedCourses().get(0).getCourseName());

        dashLine();
        System.out.println("Here is all our courses,please choose one to add into your liked course list");
        displayAllCoursesInDataBase();
        String courseName = scanner.nextLine();
        System.out.println("The type in course name is " + courseName);
        Course course = getCourse(courseName);
        if (course != null) {
            System.out.println(course.getCourseName());
            int sizeInitial = myList.viewLikedCourses().size();
        // System.out.println("Initial Size is " + sizeInitial);
            myList.addCourse(course);
            int sizeAfter = myList.viewLikedCourses().size();
        // System.out.println("Current Size is " + sizeAfter);
            if (sizeAfter - sizeInitial == 1) {
                // for test
                // System.out.println("One course has been added");
                System.out.println(courseName + "Added Successfully");
            } else {
                System.out.println("Fail");
            }
        } else {
            System.out.println("Fail to add a course. Please check your type in.");
        }
        
        dashLine();
    }

    // public Course getCourse(String courseName, List<Course> listOfCourses) {
    //     for (int i = 0;i < listOfCourses.size();i++) {
    //         Course tempCourse = listOfCourses.get(i);
    //         if (tempCourse.getCourseName() == courseName) {
    //             // System.out.println(tempCourse.getCourseName());
    //             return tempCourse;
    //         } 
    //     }
    //     return null;
    // }

    public Course getCourse(String courseName) {
        // System.out.println("The course size of all courses is "+ allCourses.size());
        // System.out.println("The course name get into getCourse() is" + courseName);
        for (int i = 0;i < allCourses.size();i++) {
            Course tempCourse = allCourses.get(i);
            // System.out.println(tempCourse.getCourseName());
            // This is the bug,we can only use .equals to compare strings, not ==
            // System.out.println(tempCourse.getCourseName().equals(courseName));

            if (tempCourse.getCourseName().equals(courseName)) {
                // System.out.println(tempCourse.getCourseName());
                return tempCourse;
            } 
        }
        System.out.println("Null is added");
        return null;
    }

    public void displayAllCoursesInDataBase() {
        for (int i = 0;i < allCourses.size();i++) {
            System.out.println(allCourses.get(i).getCourseName());
        }
    }

    public void viewInfoForACourse() {
        dashLine();
        System.out.println("Please type the course name and you will get detail info about that:");
        String courseName = scanner.nextLine();
        Course course = myList.getCourse(courseName);
        if (course != null) {
            System.out.println("The time length is " + course.getTimeLength() + " min");
            System.out.println("The content is " + course.getContent());
            System.out.println("The star Rating of this course is " + course.getStarRating());
            System.out.println("The creator of this course is " + course.getCreator());
            System.out.println("Containing Ads? " + printTrueOrFalse(course.isIncludeAds()));
            System.out.println("Having age limited? " + printTrueOrFalse(course.isAgeLimited()));
            System.out.println("Just to confirm, the course name is " + course.getCourseName());
        } else {
            System.out.println("There's no such a course in your liked course list");
        }
        
        dashLine();

    }

    public String printTrueOrFalse(boolean checker) {
        if (checker) {
            return "Yes.";
        } else {
            return "No.";
        }
    }

    public void deleteCourseFromList() {
        dashLine();
        System.out.println("Please type the course name to delete");
        String courseName = scanner.nextLine();
        myList.removeCourse(courseName);
        dashLine();
    }

    public void filterCourses() {
        tableForFilter();
        String typeIn = scanner.nextLine();
        switch (typeIn) {
            case "1":
                filterByTime();
                break;
            case "2":
                filterByAds();
                break;
            case "3":
                filterByAgeLimited();
                break;
            case "4":
                filterByStar();
                break;
            case "5":
                filterByCreator();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        dashLine();
        
    }

    public void tableForFilter() {
        System.out.println("Mention that you will make changes on your liked courses based on the conditions provided");
        System.out.println("Please choose how would you like to filter your course");
        System.out.println("1.filter by time length");
        System.out.println("2.filter by having advertisement");
        System.out.println("3.filer by having age limit");
        System.out.println("4.filter by star rating");
        System.out.println("5.fiter by creator");
    }

    public void filterByTime() {
        System.out.println("Please enter the minimum time length");
        String min = scanner.nextLine();
        int valMin = Integer.parseInt(min);

        System.out.println("Please enter the maximum time length");
        String max = scanner.nextLine();
        int valMax = Integer.parseInt(max);

        myList = myList.idealTimeRange(valMin, valMax);

    }

    public void filterByAds() {
        System.out.println("Do you want Ads or Non-Ads courses? Answer yes or no.");
        String checker = scanner.nextLine();
        myList = myList.isAds(checker.equalsIgnoreCase("yes"));

    }

    public void filterByAgeLimited() {
        System.out.println("Do you want Age Limited or Non Age Limited courses? Answer yes or no.");
        String checker = scanner.nextLine();
        myList = myList.isAgeLimited(checker.equalsIgnoreCase("yes"));
    }

    public void filterByStar() {
        System.out.println("Please enter the minimum star");
        String min = scanner.nextLine();
        int valMin = Integer.parseInt(min);

        System.out.println("Please enter the maximum star");
        String max = scanner.nextLine();
        int valMax = Integer.parseInt(max);

        myList = myList.idealStarRating(valMin, valMax);
    }

    public void filterByCreator() {
        System.out.println("Please enter the name of creator");
        String creatorName = scanner.nextLine();
        myList = myList.idealCreator(creatorName);
    }

    public void viewCoursesNameOfYourList() {
        dashLine();
        List<Course> allMyCourses = myList.viewLikedCourses();
        System.out.println("Here's all your courses in the list");
        // System.out.println(allMyCourses.size());
        for (int i = 0;i < allMyCourses.size();i++) {
            String str = allMyCourses.get(i).getCourseName();
            System.out.println(str);
        }
        dashLine();
    }

    public void quit() {
        System.out.println("That's the end. Thanks for playing with me.");
        isProgramRunning = false;
    }

    public void init() {
        myList = new LikedCourses();
        scanner = new Scanner(System.in);
        isProgramRunning = true;
    }

    public void initialCoursesList() {
        
        mathInitialize();
        
        physicsInitialize();
        
        chemistryInitialize();

        businessInitialize();

        allCourses.add(math);
        allCourses.add(physics);
        allCourses.add(chemistry);
        allCourses.add(business);

        // test
        // myList.addCourse(math);
        // System.out.println(myList.viewLikedCourses().get(0).getCourseName());
    }

    public void mathInitialize() {
        math.setTimeLength(100);
        math.setContent("Calculus I");
        math.setStarRating(4);
        math.setCreator("Max");
        math.setIncludeAds(false);
        math.setAgeLimited(false);
        math.setCourseName("Math100");
    }

    public void physicsInitialize() {
        physics.setTimeLength(60);
        physics.setContent("Dynamics I");
        physics.setStarRating(5);
        physics.setCreator("Jane");
        physics.setIncludeAds(true);
        physics.setAgeLimited(false);
        physics.setCourseName("Phys131");
    }

    public void chemistryInitialize() {
        chemistry.setTimeLength(150);
        chemistry.setContent("Inorganic Chemistry");
        chemistry.setStarRating(3);
        chemistry.setCreator("Ben");
        chemistry.setIncludeAds(false);
        chemistry.setAgeLimited(true);
        chemistry.setCourseName("Chem121");

    }

    public void businessInitialize() {
        business.setTimeLength(210);
        business.setContent("Accounting");
        business.setStarRating(5);
        business.setCreator("Bill");
        business.setIncludeAds(true);
        business.setAgeLimited(true);
        business.setCourseName("Comm157");
    }




    public void dashLine() {
        System.out.println("--------------------");
    }
}
