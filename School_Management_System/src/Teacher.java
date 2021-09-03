package src;  
//Class keeps track of teacher object

public class Teacher {
    private int id;
    private String name;
    private int salary; //yearly salary
    private double salaryEarnedToDate; //keeps track of how much the employee has earned to date


    public Teacher(int id, String name, int salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.salaryEarnedToDate = 0;
    }

    public String getName(){
        return name;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getSalary(){
        return salary; 
    }

    //set the salary of the teacher. Since an employee
    public void setSalary(int salary){
        this.salary = salary;
    }

    public double getSalaryEarnedToDate(){
        return this.salaryEarnedToDate;
    }




}
