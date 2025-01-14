package ui;

import model.Course;
import model.LikedCourses;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/*
 * This class contains the main user interface, providing several options for user to choose from.
 */

public class MyCourse {
    // The following are given courses
    
    Course math = new Course();
    Course physics = new Course();
    Course chemistry = new Course();
    Course business = new Course();
    List<Course> allCourses = new ArrayList<>();
    
    LikedCourses myList;
    private static final String JSON_STORE = "./data/myList.json";
    private Scanner scanner;
    private boolean isProgramRunning;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    // private int currentCourseIndex = 0;

    // EFFECTS: initialize Mycourse with a scanner to read in our input, an empty
    //          likedCourses and list of given courses to choose from
    public MyCourse() throws FileNotFoundException {
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

    // EFFECTS: part of the initialization, creating a LikeCourse and Scanner,
    //          setting the status of program to be to true
    public void init() {
        myList = new LikedCourses();
        scanner = new Scanner(System.in);
        isProgramRunning = true;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: show all the function and then let the user to input 
    //          a number to pick the function they want to use
    public void processOnFunctionList() {
        // test
        // myList.addCourse(math);
        // System.out.println(myList.viewLikedCourses().get(0).getCourseName());
        functionList();
        String typeIn = scanner.nextLine();
        processFunctionCommands(typeIn);

    }

    // EFFECTS: a table of function list
    public void functionList() {
        System.out.println("Select what you want to do:");
        System.out.println("1.Add a Course from a given list of courses into myList");
        System.out.println("2.Delete a course from my video list");
        System.out.println("3.View the info for a specific Course in your video list");
        System.out.println("4.Filter some courses from your video list");
        System.out.println("5.View all the Course Name of your list of course");
        System.out.println("6.save all your work to the file");
        System.out.println("7.load work room from file");
        System.out.println("8.Quit");
        dashLine();
    }

    // EFFECTS: switch to the realted method based on the number user choose
    @SuppressWarnings("methodlength")
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
                saveWorkList();
                break;
            case "7":
                loadWorkList();
                break;
            case "8":
                quit();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        dashLine();
    }

    // Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS: saves the workroom to file
    private void saveWorkList() {
        try {
            jsonWriter.open();
            jsonWriter.write(myList);
            jsonWriter.close();
            System.out.println("Saved myList to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkList() {
        try {
            myList = jsonReader.read();
            System.out.println("Loaded myList from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    
    // MODIFIES: this
    // EFFECTS: add a course into my worklist
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

    // EFFECTS: return a type of Course by input the courseName
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

    // EFFECTS: show all the given courses
    public void displayAllCoursesInDataBase() {
        for (int i = 0;i < allCourses.size();i++) {
            System.out.println(allCourses.get(i).getCourseName());
        }
    }

    // EFFECTS: print out all the info of a specific course
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

    // EFFECTS: translate true of false into yes or no
    public String printTrueOrFalse(boolean checker) {
        if (checker) {
            return "Yes.";
        } else {
            return "No.";
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a course from a list
    public void deleteCourseFromList() {
        dashLine();
        System.out.println("Please type the course name to delete");
        String courseName = scanner.nextLine();
        myList.removeCourse(courseName);
        dashLine();
    }

    // MODIFIES: this
    // EFFECTS: filter the course based on user's preference
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

    // EFFECTS: the table of the filter
    public void tableForFilter() {
        System.out.println("Mention that you will make changes on your liked courses based on the conditions provided");
        System.out.println("Please choose how would you like to filter your course");
        System.out.println("1.filter by time length");
        System.out.println("2.filter by having advertisement");
        System.out.println("3.filer by having age limit");
        System.out.println("4.filter by star rating");
        System.out.println("5.fiter by creator");
    }

    // MODIFIES: this
    // EFFECTS: filter courses based on given time length
    public void filterByTime() {
        System.out.println("Please enter the minimum time length");
        String min = scanner.nextLine();
        int valMin = Integer.parseInt(min);

        System.out.println("Please enter the maximum time length");
        String max = scanner.nextLine();
        int valMax = Integer.parseInt(max);

        myList = myList.idealTimeRange(valMin, valMax);

    }

    // MODIFIES: this
    // EFFECTS: filter courses based on whether have ads
    public void filterByAds() {
        System.out.println("Do you want Ads or Non-Ads courses? Answer yes or no.");
        String checker = scanner.nextLine();
        myList = myList.isAds(checker.equalsIgnoreCase("yes"));

    }

    // MODIFIES: this
    // EFFECTS: filter courses based on whether have age limited
    public void filterByAgeLimited() {
        System.out.println("Do you want Age Limited or Non Age Limited courses? Answer yes or no.");
        String checker = scanner.nextLine();
        myList = myList.isAgeLimited(checker.equalsIgnoreCase("yes"));
    }

    // MODIFIES: this
    // EFFECTS: filter courses based on star rating
    public void filterByStar() {
        System.out.println("Please enter the minimum star");
        String min = scanner.nextLine();
        int valMin = Integer.parseInt(min);

        System.out.println("Please enter the maximum star");
        String max = scanner.nextLine();
        int valMax = Integer.parseInt(max);

        myList = myList.idealStarRating(valMin, valMax);
    }

    // MODIFIES: this
    // EFFECTS: filter courses based on creator
    public void filterByCreator() {
        System.out.println("Please enter the name of creator");
        String creatorName = scanner.nextLine();
        myList = myList.idealCreator(creatorName);
    }

    // EFFECTS: display all courses in my work list
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

    // EFFECTs: terminal the program
    public void quit() {
        System.out.println("That's the end. Thanks for playing with me.");
        isProgramRunning = false;
    }

    

    // EFFECTS: initialize a given course list
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

    // EFFECTS: initialize the math course
    public void mathInitialize() {
        math.setTimeLength(100);
        math.setContent("Calculus I");
        math.setStarRating(4);
        math.setCreator("Max");
        math.setIncludeAds(false);
        math.setAgeLimited(false);
        math.setCourseName("Math100");
    }

    // EFFECTS: initialize the physics course
    public void physicsInitialize() {
        physics.setTimeLength(60);
        physics.setContent("Dynamics I");
        physics.setStarRating(5);
        physics.setCreator("Jane");
        physics.setIncludeAds(true);
        physics.setAgeLimited(false);
        physics.setCourseName("Phys131");
    }

    // EFFECTS: initialize the chemistry course
    public void chemistryInitialize() {
        chemistry.setTimeLength(150);
        chemistry.setContent("Inorganic Chemistry");
        chemistry.setStarRating(3);
        chemistry.setCreator("Ben");
        chemistry.setIncludeAds(false);
        chemistry.setAgeLimited(true);
        chemistry.setCourseName("Chem121");

    }

    // EFFECTS: initialize the business course
    public void businessInitialize() {
        business.setTimeLength(210);
        business.setContent("Accounting");
        business.setStarRating(5);
        business.setCreator("Bill");
        business.setIncludeAds(true);
        business.setAgeLimited(true);
        business.setCourseName("Comm157");
    }

    // EFFECTS: create a dashline to separate every interaction with users
    public void dashLine() {
        System.out.println("--------------------");
    }
}
