package src;
import java.util.ArrayList;

public class MainFile {
    public static void main(String[] args){

        Teacher truscott = new Teacher(1, "Truscott", 500);
        Teacher lays = new Teacher(2, "Lays", 800);
        Teacher radder = new Teacher(3, "Radder", 1500); 

        ArrayList<Teacher> listOfTeachers = new ArrayList<Teacher>();  
        listOfTeachers.add(truscott);
        listOfTeachers.add(lays);
        listOfTeachers.add(radder); 

        Student mitch = new Student(1, "Mitch", 2);
        Student kevin = new Student(2, "Kevin", 3);
        Student pringle = new Student(3, "PringlesMan", 4);

        ArrayList<Student> listOfStudents = new ArrayList<Student>();  

        listOfStudents.add(mitch);
        listOfStudents.add(kevin);
        listOfStudents.add(pringle);

        School forest = new School(listOfTeachers, listOfStudents);
        System.out.println("Testing amount earned in forest high school: $" + forest.getTotalMoneyEarned());
        System.out.println("Testing teacher salary: $" + lays.getSalary());

        System.out.println("Hello world");

    }    
}
