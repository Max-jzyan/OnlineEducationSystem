package model;

public class Course {
    private int timeLength;// unit: seconds
    private String content;
    private double starRating; // Five stars means full mark
    private String creator;
    private int numberOfBeingWatched;
    private int numberOfComments;
    private boolean includeAds;
    private boolean ageLimited;
    private String courseName;


    public Course() {
        timeLength = 0;
        numberOfBeingWatched = 0;
        numberOfComments = 0;       
    }

    public int getTimeLength() {
        return this.timeLength;
    } 

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getNumberOfBeingWatched() {
        return numberOfBeingWatched;
    }

    public void setNumberOfBeingWatched(int numberOfBeingWatched) {
        this.numberOfBeingWatched = numberOfBeingWatched;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public boolean isIncludeAds() {
        return includeAds;
    }

    public void setIncludeAds(boolean includeAds) {
        this.includeAds = includeAds;
    }

    public boolean isAgeLimited() {
        return ageLimited;
    }

    public void setAgeLimited(boolean ageLimited) {

        this.ageLimited = ageLimited;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    // MODIFIES: this
    // EFFECTS: time length is added based on addedTime
    public void addTime(int addedTime) {
        this.timeLength = this.timeLength + addedTime;
    }

    // REQUIRES: timeLength >= reducedTime
    // MODIFIES: this
    // EFFECTS: time length is reduced based on reducedTime
    public void reduceTime(int reducedTime) {
        this.timeLength -= reducedTime;
    }

    // MODIFIES: this
    // EFFECTS: number of being watched is added based on a given number
    public void addNumberOfBeingWatched(int num) {
        this.numberOfBeingWatched += num; 
    }

    // MODIFIES: this
    // EFFECTS: number of comments is added based on a given number
    public void addNumberOfComments(int num) {
        this.numberOfComments += num; 
    }
}
