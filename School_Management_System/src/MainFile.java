package src;
import java.util.ArrayList;
import java.util.Scanner;

public class MainFile {
    public static void main(String[] args){
        // Below is to populate the values for both array lists to start. 
        Scanner scan = new Scanner(System.in);

        Teacher truscott = new Teacher(1, "Truscott", 500);
        Teacher lays = new Teacher(2, "Lays", 800);
        Teacher radder = new Teacher(3, "Radder", 1500); 

        ArrayList<Teacher> listOfTeachers = new ArrayList<Teacher>();  
        listOfTeachers.add(truscott);
        listOfTeachers.add(lays);
        listOfTeachers.add(radder); 

        Student mitch = new Student(1, "Mitch", 2.0);
        Student kevin = new Student(2, "Kevin", 3.0);
        Student pringle = new Student(3, "PringlesMan", 4.0);

        ArrayList<Student> listOfStudents = new ArrayList<Student>();  

        listOfStudents.add(mitch);
        listOfStudents.add(kevin);
        listOfStudents.add(pringle);

        School forest = new School(listOfTeachers, listOfStudents); //must move this into while loop later to continuously update school


        //creates the infinite loop for menu
        boolean var = true;
        
        while(var){
            
            System.out.println("Please enter a number based on the following options: \n 1) Enter a new student into system. \n 2) Enter a new teacher into system \n 3) Check money earned to date \n 9) To Quit program ");
            int choice = scan.nextInt();
            scan.nextLine(); //clears out the buffer

            if(choice == 1){
                System.out.println("Enter the student's first and last name: ");
                String name = scan.nextLine(); 
                System.out.println("Enter the students GPA average: ");
                double avg = scan.nextDouble();

                //find the highest value in the ID field so we can create an ID for them. Assumes the size will equal the ID.
                int expectedID = listOfStudents.size() + 1;

                Student s = new Student(expectedID, name, avg);
                listOfStudents.add(s); 
                System.out.println("Student successfully added!");
                System.out.println("ID:"  + listOfStudents.get(listOfStudents.size()-1).getID() + " Name: " + listOfStudents.get(listOfStudents.size()-1).getName() + " GPA: "  + listOfStudents.get(listOfStudents.size()-111).getGrade());
            
            }
            else if(choice == 2){
                System.out.println("Enter the teacher's First and Last Name");
                String name = scan.nextLine();
                System.out.println("Enter the teacher's Salary: ");
                int salary = scan.nextInt();
                scan.nextLine(); //resolves buffer from next Int

                int expectedID = listOfTeachers.size() + 1;
                
                Teacher t = new Teacher(expectedID, name, salary);
                listOfTeachers.add(t);
                System.out.println("Teacher successfully added!");
                System.out.println("ID:"  + listOfTeachers.get(listOfTeachers.size()-1).getID() + " Name: " + listOfTeachers.get(listOfTeachers.size()-1).getName() + " Salary: "  + listOfTeachers.get(listOfTeachers.size()-1).getSalary());

            }
            else if(choice == 3){
                System.out.println("Total money earned to date from Forest is: " + forest.getTotalMoneyEarned());
            }

            else if(choice == 9){
                System.exit(0);
            }


        }

    }    
}
