import java.sql.*;
import java.util.Scanner;

public class BaseModel {
    Scanner scanner;
    Connection connection;

    public BaseModel(Connection connection, Scanner scanner) {
        this.scanner = scanner;
        this.connection = connection;
    }
    
    /*
     * Get valid integer
     * 
     * @params start and end integer (inclusive)
     * 
     * @return integer
     */
    public int getValidChoice(int start, int end) {
        while (true) {
            int choice = 0;
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                System.out.println("");

                if (choice >= start && choice <= end) {
                    return choice;
                } else {
                    System.out
                            .println(String.format("Incorrect Input. Please enter a number from %d - %d.", start, end));
                }

            } catch (Exception e) {
                System.out.println(String.format("Incorrect Input. Please enter a number from %d - %d.", start, end));
                scanner.nextLine();
            }
        }
    }
}
