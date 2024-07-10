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
    - showCourseInfo
    - isAds
    - idealTimeRange
    - isAgeLimited
    - idealComments
    - idealStarRating
    - idealNumberOfWatched
    - idealCreator
    - filterCourses
    - getHighestRating
    - displayAll

## User Story
A user may want to add a Course into the likedCourses;
A user may want to delete a Course from the likedCourses;
A user may want to view a Course into the likedCourses;
A user may want to filter some courses from likedCourses;
A user may want to pick the most xxx course from likedCourse;
A user may want to rearrange the likedCourse based on some condition;



An example of text with **bold** and *italic* fonts.  