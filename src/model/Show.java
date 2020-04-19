package model;
import java.util.*;

public class Show {
    private Long millis;
    private String movie;
    private String screen;
    private Set<Integer> totalSeats = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    private Set<Integer> bookedSeats = new HashSet<>();

    public Show(String movie, Long millis) {
        this.movie = movie;
        this.millis = millis;
    }

    public void bookSeats(Set<Integer> selectedSeats) {
        this.bookedSeats.addAll(selectedSeats);
    }

    public Long getMillis() {
        return millis;
    }

    public String getMovie() {
        return movie;
    }

    public Set<Integer> getTotalSeats() {
        return totalSeats;
    }

    public Set<Integer> getBookedSeats() {
        return bookedSeats;
    }
}
