package model;
import java.util.*;

public class Theater {
    private int id;
    private String city;
    private Set<Show> showList = new HashSet<>();
    private Map<String, List<Show>> movieShowMap = new HashMap<>();

    public Theater(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public void addMovieShow(String movie, Long time) {
        Show show = new Show(movie.toLowerCase(), time);
        if(this.movieShowMap.containsKey(movie)){
            List<Show> movieShowList = this.movieShowMap.get(movie);
            movieShowList.add(show);
        } else {
            List<Show> movieShowList = new ArrayList<>();
            movieShowList.add(show);
            this.movieShowMap.put(movie, movieShowList);
        }
        this.showList.add(show);
    }

    public Set<String> getMoviesAvailableTheater(){
        return movieShowMap.keySet();
    }

    public Map<String, List<Show>> getMovieShowMap() {
        return movieShowMap;
    }

    public int getId() {
        return id;
    }
}
