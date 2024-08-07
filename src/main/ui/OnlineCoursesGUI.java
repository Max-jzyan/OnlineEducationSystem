package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;


import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;


import model.Course;
import model.EventLog;
import model.Event;
import model.LikedCourses;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * The graphical user interface of this project
 */

public class OnlineCoursesGUI extends JFrame 
        implements WindowListener {
    private static final String JSON_STORE = "./data/myList.json";
    private JLabel displayLabel; // showing all the changes of the list
    private static JLabel allCoursesName;
    private JTextField inputField;
    private Popup popup;
    private JLabel imageAsLabel;
    private ImageIcon calc;
    private ImageIcon phys;
    private ImageIcon comm;
    private ImageIcon chem;

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

        JButton adder = getAdder();

        JButton deleter = getDeleter();

        JButton viewer = getViewer();

        JButton loader = getLoader();

        JButton saver = getSaver();

        JButton filter = getFilter();



        JPanel panel = new JPanel();
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new FlowLayout());
        
        panel.add(adder);
        panel.add(deleter);
        panel.add(viewer);
        panel.add(loader);
        panel.add(saver);
        panel.add(filter);
        panel.add(inputField,BorderLayout.SOUTH);
        panel.add(displayLabel);
        panel.add(allCoursesName);

        panel.setPreferredSize(new Dimension(500,400));
        addWindowListener(this);
        add(panel);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: the concrete steps to create a filter button, return a filter button
    @SuppressWarnings("methodlength")
    private JButton getFilter() {
        // The button with ability to filter your selected courses
        JButton filter = new JButton("Filter courses based on your preference");
        filter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel subPanel = new JPanel();
                subPanel.setLayout(new FlowLayout());

                // Create two buttons for the popup
                JButton button1 = new JButton("Filter by not having ads");
                JButton button2 = new JButton("Filter by not having age limited");
                
                button1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        myList = myList.isAds(false);
                        popup.hide();
                    }
                });

                button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        myList = myList.isAgeLimited(false);
                        popup.hide();
                    }
                });

                subPanel.add(button1);
                subPanel.add(button2);

                PopupFactory popupFactory = new PopupFactory();
                popup = popupFactory.getPopup(filter, subPanel, 180, 180);
                popup.show();
            }
		});
        return filter;
    }

    //MODIFIES: this
    //EFFECTS: the concrete steps to create a saver button, return a saver button
    private JButton getSaver() {
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
        return saver;
    }

    //MODIFIES: this
    //EFFECTS: the concrete steps to create a loader button, return a loader button
    private JButton getLoader() {
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
        return loader;
    }

    //MODIFIES: this
    // EFFECTS: the concrete steps to create a viewer button, return a viewer button
    private JButton getViewer() {
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
        return viewer;
    }

    //MODIFIES: this
    // EFFECTS: the concrete steps to create a deleter button, return a deleter button
    private JButton getDeleter() {
        // The button with ability to delete a course from the list
        JButton deleter = new JButton("Delete a course from your list");
        deleter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String courseName = inputField.getText();
                myList.removeCourse(courseName);
                inputField.setText("");
                displayLabel.setText("The following course has been deleted " + courseName);
            }
		});
        return deleter;
    }

    // MODIFIES: this
    // EFFECT: The concrete steps to create the adder button, return an adder button
    @SuppressWarnings("methodlength")
    private JButton getAdder() {
        // The button with ability to add a course into the list
        JButton adder = new JButton("Add a course to your List");
        adder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String courseName = inputField.getText();
                Course course = getCourse(courseName);
                if (course != null && !myList.viewLikedCourses().contains(course)) {
                    myList.addCourse(course);
                    if (imageAsLabel != null) {
                        remove(imageAsLabel);
                    }
                    if (courseName.equals("Math100")) {   
                        imageAsLabel = new JLabel(calc);
                    } else if (courseName.equals("Phys131")) { 
                        imageAsLabel = new JLabel(phys);
                    } else if (courseName.equals("Chem121")) {
                        imageAsLabel = new JLabel(chem);
                    } else if (courseName.equals("Comm157")) {
                        imageAsLabel = new JLabel(comm);
                    }
                    add(imageAsLabel);
                    validate();
		            repaint();

                }
                inputField.setText(""); // Clear the input field
                displayLabel.setText("The course name your select " + courseName);
            }
            
		});
        return adder;
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
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Online Course Education System");
        setSize(100,200);
        inputField = new JTextField(10);
        displayLabel = new JLabel("Choose the function you want!");
        allCoursesName = new JLabel("All courses name: Math100,Phys131,Comm157,Chem121");
        loadImages();
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

    // EFFECT: load image from package
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        calc = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "calculus.jpg");
        chem = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "chemistry.jpg");
        comm = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "commerce.jpg");
        phys = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "physics.jpg");

    }

    // EFFECTS: print all the logs while the window is closed
    public void windowClosed(WindowEvent e) {
        //This will only be seen on standard output.
        // System.out.println("Window closed");
        printLogs(EventLog.getInstance());
        
    }

    // EFFECT: implementation requirement, but nothing should be done
    public void windowOpened(WindowEvent e) {
        
    }
    
    // EFFECT: implementation requirement, but nothing should be done
    public void windowIconified(WindowEvent e) {
        
    }
    
    // EFFECT: implementation requirement, but nothing should be done
    public void windowDeiconified(WindowEvent e) {
        
    }
    
    // EFFECT: implementation requirement, but nothing should be done
    public void windowActivated(WindowEvent e) {
        
    }
    
    // EFFECT: implementation requirement, but nothing should be done
    public void windowDeactivated(WindowEvent e) {
        
    }

    // EFFECT: close the window immediately
    public void windowClosing(WindowEvent e) {
        ActionListener task = new ActionListener() {
            boolean alreadyDisposed = false;
            public void actionPerformed(ActionEvent e) {
                if (isDisplayable()) {
                    alreadyDisposed = true;
                    dispose();
                }
            }
        };
        Timer timer;
        timer = new Timer(0, task); //fire every half second
        timer.setInitialDelay(0);        //first delay 2 seconds
        timer.setRepeats(false);
        timer.start();
    }

    // EFFECTS: print all the logs to the console
    public void printLogs(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }

    }


    // EFFECTS: main function of GUI
    public static void main(String[] args) {
        new OnlineCoursesGUI();
    }

    
}
