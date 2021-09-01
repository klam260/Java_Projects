package src;
import java.util.List; 

//Implements teachers and students using an arraylist as we do not know the specific amount of students or teachers in a school
 
public class School {

    private int totalMoneyEarned;
    private int totalMoneySpent;
    private List<Teacher> teachers;
    private List<Student> students;

    public School (List<Teacher> teachers, List<Student> students){
        this.teachers = teachers;
        this.students = students;         
        totalMoneyEarned = 0;
        totalMoneySpent = 0; 
    }

    public List<Teacher> getTeachers(){
        return teachers;
    }

    public List<Student> getStudents(){
        return students;
    }

    public void addTeachers(Teacher teacher){
        teachers.add(teacher);
    }

    public void addStudents(Student student){
        students.add(student);
    }

    //how much the school made from students
    public int getTotalMoneyEarned(){
       return totalMoneyEarned; 
    }

    //total money the school has spent on supplies.
    public int getTotalMoneySpent(){
        return totalMoneySpent;
    }

    public void updateTotalMoneyEarned(int totalMoneyEarned){
        this.totalMoneyEarned += totalMoneyEarned; 
    }

    public void updateMoneySpent(int moneySpent){
        this.totalMoneySpent += moneySpent;
    }

}