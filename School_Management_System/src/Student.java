package src;

public class Student {
    private int id;
    private String name;
    private double grade;
    private double feesPaid;
    private double feesTotal;

    public Student(int id, String name, double grade){
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.feesPaid = 0;
        this.feesTotal= 40000;

    }

    //standard setters and getters for object student 
    public void setGrade(double grade){
        this.grade = grade;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setName (String name){
        this.name = name; 
    }

    public double getFeesPaid(){
        return feesPaid;
    }

    public double getFeesTotal(){
        return feesTotal;
    }

    public double getGrade(){
        return this.grade;
    }

    public int getID(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    //a student could take more courses and add to their fees. This method will add to the total fees owing. 
    public void updateFeesTotal(double amountPaid){
        this.feesTotal += amountPaid;
    }

    //add fees to the fees paid so far by the student
    public void updateFeedsPaid(double fees){
        feesPaid = feesPaid + fees;
    }

    //returns remaining balance the student has left to pay
    public double getRemainingBalance(){
        return feesTotal - feesPaid;
    }




}
