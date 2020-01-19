# Web-Student-book-
Java school project using JSF

html files:
There are four xhtml files in my project (edit-studs.xhtml, add-studs.xhtml, Welcome.xhtml and list-studs.xhtml) and they are all located in the WebContent folder.
I started my web application by creating a Welcome.xhtml file asking the user to enter his name.

When the user clicks “Submit” the welcome page calls the list-studs.xhtml file so the user can see the list of students in the database.
The list-studs.xhtml file prints the name the user entered previously and, in a datatable, all the characteristics of the students in the database (first name, last name, id and email).
At this point, the user is left with the choice of:
- adding a student to the database.
- editing a student already in the database.
- deleting a student.

If the user wants to add a student, he will have to enter the first name, last name and email but not the id because this parameter is auto incremented by the mySQL database.
This is done in the xhtml file with the inputText keyword.
There are validation features for these three parameters the user will have to enter:
- The first and last name must be between 2 to 20 characters and have at least a capital and a lowercase letter.
- The email should contain the character ‘@’ and be from 6 to 20 characters.

These validations are made with the key word validateregex.
Thanks to validateregex, it is possible to specify the minimum and maximum length, as well as the special characters that need (or need not) to be in the input.
If the input is not correct, a message in red is shown to indicate what are the requirements for the parameter the user failed to complete.

The final instruction in add-studs.xhtml is
<h:commandButton action="#{studentManager.addStudent(student)}"value="Submit" />
This means that the submit button will call the addStudent method of studentManager, with the student entered by the user as parameter.
The edit-studs.xhtml is almost identical as the add-studs.xhtml file except it calls the updateStudent
method of the studentManager instead of addStudents.

As for the deleting a student part, there is no need for a xhtml file to do this, so the deleteStudent
method of studentManager is directly called from list-studs.xhtml when the user deletes a student.

The StudentManager ManagedBean:
The student manager is a managed bean class created to handle the communication between the
database (with a DBUtil attribute) and the view (the xhtml files call a method from StudentManager).
We have two attributes, a list of students which will contain all students in the database and a DBUtil
object, useful to call methods from the dbutil class.

When add-studs.xhtml calls the addStudent method of StudentManager, this one calls the
addStudent method of dbutil, which inserts the student in the database. Then, the addStudent
method of StudentManager returns “list-studs” so the user can see the list again.

If the user wants to edit a student by clicking the “edit a student” button of list-studs.xhtml, the
fetchStudents method of StudentManager is called. This is because the application needs to have the
information concerning the students to modify one. After showing information of the student, the
fetchStudents method returns “edit-studs” so the user can actually change the information.

When edit-studs.xhtml calls the updateStudent method of StudentManager, this one calls the
updateStudent method from dbutil, which updates the student of corresponding id. Then, the
updateStudent method of StudentManager returns the list of students.

FetchStudents also has a method declaration without parameter, which is not used to edit a student
but to call the getStudents method from dbutil. This method is used as a listener in the liststuds.
xhtml file so the list is always up to date with the database.*

The DBUtil class:
Finally, the DBUtil class handles all the communication with the mySQL database.
This class almost has not changed since the previous project in JSP.
The methods are:
- getDataSource() : called to initialize the dataSource object in the constructor.
- getStudents(): executes the query “select * from students” and returns a list of all the
students returned by the query.
- Close() : closes connection to the database.
- addStudent(Student student): inserts a student to the database using the query “INSERT
INTO student (first_name, last_name …”
- fetchStudent(int id): this method returns the student corresponding to the id asked in
parameter.
- updateStudent(Student student): this method updates the student in the database once it
got changed in edit-studs and passed as a parameter.
- deleteStudent(int id): deletes the row corresponding to the id passed in parameter.
