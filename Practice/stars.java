import javax.print.event.PrintEvent;

/**
 * stars
 */
public class stars {

    public static void main(String[] args) {
        for(int rows=0; rows<5 ; rows++){
            for(int col=0; col <= rows; col++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}