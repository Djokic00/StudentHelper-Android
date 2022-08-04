# StudentHelper-Android

## About The Project

[Project Specification](https://github.com/Djokic00/StudentHelper-Android/blob/main/RAF%20Student%20Helper.pdf)

Student Helper is an Android application written in Kotlin using Android Studio and it was the second project for "Mobile Development" 
course at Faculty of Computing. The main purpose of the application is to help students quickly and easily find schedule information about lectures at the university (RAF). In addition, application has an option for creating a note which can be edited, archived and deleted and it has a statistical screen which represents number of notes created in the last 5 days. 

## How to Use the Project

- Login - To login you need to enter <b>1234</b> for pin and username is optional. User is not stored in database but because of the project specification login screen had to be added. 
- Main Screen - On the main screen there are 4 icons: schedule, notes, statistics and logout. You can enter either of them just by clicking on the icon.
- Schedule Screen - List of all lectures containg subject name, professor name, day, groups. You can filter lectures by all parameters (group, day, professor/subject) or with combination of those parameters.
- Notes Screen - Used for creating, editing, archiving and deleting notes. Additionally, it has a search option and toggle button for archived / non archived notes. It has 4 icons: plus icon for creating a new note, trash icon for deleting, pen icon for editing and arrow down / up for archived / non archived notes.
- Logout - Click the logout icon and you will be redirected to login screen.


## Technology stack

Technologies used in the project:

- Koin framework for dependency injection
- Retrofit library for communicating with the server and REST convention
- RxJava library for easier work with threads
- Room library for working with database (ORM)
- Moshi library for working with json
- Jetpack Compose for login activity and statistics activity
