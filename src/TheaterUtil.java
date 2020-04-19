import model.Movie;
import model.Show;
import model.Theater;
import model.TheaterManagement;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TheaterUtil {

    public static void addTheaterUtil(TheaterManagement theaterManagement) {
        System.out.println("Input city");
        Scanner cityInput = new Scanner(System.in);
        int newTheaterId = theaterManagement.addTheater(cityInput.next());
        System.out.println("Added new theater with id " + newTheaterId);

    }

    public static void displayCities(TheaterManagement theaterManagement) {
        Set<String> cities = theaterManagement.getCities();
        System.out.println("Cities : " );
        for(String city : cities){
            System.out.println(city);
        }
    }

    public static void addMovie(TheaterManagement theaterManagement) {
        System.out.println("Input valid theater id to add movie");
        Scanner theaterInput = new Scanner(System.in);
        Integer id = theaterInput.nextInt();
        if(!theaterManagement.getTheaterMap().containsKey(id)){
            System.out.println("Doesn't contain theater id");
        } else {
            System.out.println("Add movie and epoch time");
            String movie = theaterInput.next();
            Long time = theaterInput.nextLong();
            Movie newMovie = new Movie(movie);
            Theater theater = theaterManagement.getTheaterMap().get(id);
            theater.addMovieShow(newMovie.getMovieName().toLowerCase(), time);
            System.out.println("Movie "+ movie + " added for epoch time " + time);
        }
    }

    public static void bookMovie(TheaterManagement theaterManagement) {
        System.out.println("Book ticket!");
        System.out.println("See all cities");
        Scanner sc = new Scanner(System.in);
        Set<String> cities = theaterManagement.getCities();
        if(cities == null || cities.size() < 1) {
            System.out.println("No cities. Breaking");
            return;
        }
        for(String city : cities) {
            System.out.println(city);
        }
        System.out.println("Choose a city");
        String chosenCity = sc.next();
        if(theaterManagement.getCities().contains(chosenCity)) {
            System.out.println("Valid city " + chosenCity);
            List<Theater> allTheatersForChosenCity = theaterManagement.getCityTheaterMap().get(chosenCity.toLowerCase());
            if(allTheatersForChosenCity != null && allTheatersForChosenCity.size() > 0) {
                Set<String> allMovies = new HashSet<>();
                for(Theater theater : allTheatersForChosenCity) {
                    allMovies.addAll(theater.getMoviesAvailableTheater());
                }
                System.out.println("Showing all movies");
                for(String movie : allMovies) {
                    System.out.println(movie);
                }

                System.out.println("Choose movie");
                String chosenMovie = sc.next();
                System.out.println("Timings : ");
                for(Theater theater : allTheatersForChosenCity) {
                    List<Show> showList = theater.getMovieShowMap().get(chosenMovie.toLowerCase());
                    if(showList == null || showList.size() == 0) {
                        continue;
                    }
                    for(Show show : showList) {
                        System.out.println("Epoch time : " + show.getMillis() + " in theater id " + theater.getId());
                    }
                }
                System.out.println("Choose theaterId and show time");
                int chosenTheaterId = sc.nextInt();
                long chosenShowTime = sc.nextLong();

                //Calculate final show
                Theater finalTheater = null;
                Show finalShow = null;
                for(Theater theater : allTheatersForChosenCity) {
                    if(theater.getId() == chosenTheaterId){
                        finalTheater = theater;
                        break;
                    }
                }
                for(Show show : finalTheater.getMovieShowMap().get(chosenMovie)) {
                    if(show.getMillis() == chosenShowTime){
                        finalShow = show;
                        break;
                    }
                }

                Set<Integer> allSeats = finalShow.getTotalSeats();
                Set<Integer> bookedSeats = finalShow.getBookedSeats();
                System.out.println("Available seats are in number " + (allSeats.size() - bookedSeats.size()));
                for(Integer seat : allSeats) {
                    if(!bookedSeats.contains(seat))
                        System.out.println("Available seat is " + seat);
                }

                System.out.println("How many seats do you want ?");
                int totalSeatsToBeBooked = Math.min(sc.nextInt(),(allSeats.size() - bookedSeats.size()));
                Set<Integer> willingSeats = new HashSet<>();
                while(totalSeatsToBeBooked > 0) {
                    int willingSeat = sc.nextInt();
                    if(bookedSeats.contains(willingSeat)){
                        System.out.println("Already booked, please chose again");
                        continue;
                    }
                    willingSeats.add(willingSeat);
                    totalSeatsToBeBooked--;
                }
                System.out.println("Print Y to confirm");
                String confirmation = sc.next();
                if(confirmation.toLowerCase().equals("y")) {
                    finalShow.bookSeats(willingSeats);
                    System.out.println("Successfully booked tickets !!!");
                } else {
                    System.out.println("Booking failed");
                }
            }
        } else {
            System.out.println("Not valid city try all over again!");
        }
    }
}
