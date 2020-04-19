package model;

public class Movie {
    private String movieName;

    public String getMovieName() {
        return movieName;
    }

    public Movie(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public int hashCode() {
        return 31 * movieName.hashCode();
    }

    //Compare only movie name
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        Movie that = (Movie) obj;
        return that.movieName.equals(((Movie) obj).movieName);
    }
}

