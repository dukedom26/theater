import model.Movie;
import model.Show;
import model.Theater;
import model.TheaterManagement;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Driver {
    public static void main(String[] args){
        System.out.println("Welcome to Antarin Theater Management System");
        TheaterManagement tm = new TheaterManagement();
        while(true){
            System.out.println("Select the option \n1. Add Theater \n2. Show Cities \n3. Add Movie \n4. Book Movie \n5. Exit");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter selected option");
            int input = sc.nextInt();
            switch(input){
                case 1 :
                    TheaterUtil.addTheaterUtil(tm);
                    break;
                case 2 :
                    TheaterUtil.displayCities(tm);
                    break;
                case 3 :
                    TheaterUtil.addMovie(tm);
                    break;
                case 4 :
                    TheaterUtil.bookMovie(tm);
                    break;
                case 5 :
                    System.out.println("Exiting!");
                    break;
                default :
                    System.out.println("Invalid option! Reselect!");
            }
            if(input == 5){
                break;
            }
        }
    }
}
