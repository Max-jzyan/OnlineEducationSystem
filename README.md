# Online Education system
## Introduction
There is a popular trend on online education these days, due to its time-saving and distance-saving. Most importantly, thanks to my neighber, Shisten, she intoduces me her wonderful idea on her business regarding to online courses and I was quite impressed by her idea. So I would like to invent an simplified online education system facing to users. The main function of this application is getting info about all courses and creating a collection of user's favourite courses. To be detail, the project will be separated into two classes.

Class Course:
- **Elements** in Course constructor: 
    - timeLength
    - content
    - starRating
    - creater
    - numberOfBeingWatched
    - numberOfComments
    - includeAds
    - ageLimited
- **Methods** in Course 
    - getters
    - setters
    - revisors(adder, subtracter,starCalculater)


Class likedCourses:
- **Methods** in likedCourses
    - addCourse
    - removeCourse
    - getCourse
    - isAds
    - idealTimeRange
    - isAgeLimited
    - idealComments
    - idealStarRating
    - idealNumberOfWatched 
    - idealCreator
    - filterCourses(?)
    - displayAll

## User Story
- A user may want to add a Course into the likedCourses;
- A user may want to delete a Course from the likedCourses;
- A user may want to view a Course's detail infomation from his/her likedCourses;
- A user may want to filter some courses from likedCourses;
- A user may want to view all the courses name in likedCourses;
- As a user, I want to save my liked courses to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my liked courses from file.



# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by typing one of the course name shown in the bottom of the frame, and then clicking add Course button.(i.e. type Math100 into the input field and then click add Course button)
- You can generate the second required action related to the user story by typing the course name you want to delete in the input field, and then clicking the delete button. Then by clicking the view button, you can find that the course you want to delete has been deleted.
- You can locate my visual component by...
- You can save the state of my application by clicking save button.
- You can reload the state of my application by clicking load button, then you can view all the loaded courses by clicking view all courses.


