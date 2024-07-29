package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Course;
import model.LikedCourses;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * The graphical user interface of this project
 */

public class OnlineCoursesGUI extends JFrame {
    private static final String JSON_STORE = "./data/myList.json";
    private JLabel displayLabel; // showing all the changes of the list
    private static JLabel allCoursesName;
    private JTextField inputField;
    private JList<Course> courseList;

    private LikedCourses myList;
    List<Course> allCourses = new ArrayList<>();
    Course math = new Course();
    Course physics = new Course();
    Course chemistry = new Course();
    Course business = new Course();

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECT: add the buttons with different functionality and initialize the system
    @SuppressWarnings("methodlength")
    public OnlineCoursesGUI() {
        super("Online Course Education System");
        initialization();    

        // The button with ability to add a course into the list
        JButton adder = new JButton("Add a course to your List");
        adder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String courseName = inputField.getText();
                Course course = getCourse(courseName);
                if (course != null && !myList.viewLikedCourses().contains(course)) {
                    myList.addCourse(course);
                    // System.out.println("course: " + myList.viewLikedCourses().get(0).getCourseName() + "added");
                }
                inputField.setText(""); // Clear the input field
                displayLabel.setText("The course name your select " + courseName);
            }
		});

        // The button with ability to delete a course from the list
        JButton deleter = new JButton("Delete a course from your list");
        deleter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String courseName = inputField.getText();
                myList.removeCourse(courseName);
                inputField.setText("");
            }
		});

        // The button with ability to view all the course name in the list
        JButton viewer = new JButton("View all the courses");
        viewer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder listContent = new StringBuilder();
                for (int i = 0; i < myList.viewLikedCourses().size(); i++) {
                    listContent.append(myList.viewLikedCourses().get(i).getCourseName()).append(", ");
                }
                displayLabel.setText("My Liked Courses: " + listContent.toString());
            }
		});

        // The button with ability to load courses from Json File
        JButton loader = new JButton("Load courses from file");
        loader.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    myList = jsonReader.read();
                    displayLabel.setText("Loaded myList from " + JSON_STORE);
                    // System.out.println(myList.viewLikedCourses().size() + " courses has been added");
                } catch (IOException f) {
                    displayLabel.setText("Unable to read from file: " + JSON_STORE);
                }
            }
		});

        // The button with ability to save your List into Json File
        JButton saver = new JButton("Save liked courses into file");
        saver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(myList);
                    jsonWriter.close();
                    displayLabel.setText("Saved myList to " + JSON_STORE);
                } catch (FileNotFoundException g) {
                    displayLabel.setText("Unable to write to file: " + JSON_STORE);
                }
            }
		});

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(adder);
        panel.add(deleter);
        panel.add(viewer);
        panel.add(loader);
        panel.add(saver);
        panel.add(inputField);
        panel.add(displayLabel);
        panel.add(allCoursesName);

        add(panel);
        pack();
        setVisible(true);
    }

    // EFFECT: initialize all the courses to be selected, initial Json Reader,Writer,input Field,displayLabel
    public void initialization() {
        myList = new LikedCourses();
        mathInitialize();
        physicsInitialize();
        chemistryInitialize();
        businessInitialize();
        allCourses.add(math);
        allCourses.add(physics);
        allCourses.add(chemistry);
        allCourses.add(business);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Online Course Education System");
        setSize(100,200);
        inputField = new JTextField(10);
        displayLabel = new JLabel("Choose the function you want!");
        allCoursesName = new JLabel("All courses name: Math100,Phys131,Comm157,Chem121");
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

    // EFFECTS: return a type of Course by input the courseName
    public Course getCourse(String courseName) {
        for (int i = 0;i < allCourses.size();i++) {
            Course tempCourse = allCourses.get(i);
            if (tempCourse.getCourseName().equals(courseName)) {
                return tempCourse;
            } 
        }
        return null;
    }



    // EFFECTS: main function of GUI
    public static void main(String[] args) {
        new OnlineCoursesGUI();
    }
}
