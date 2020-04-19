package model;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TheaterManagement {
    AtomicInteger id = new AtomicInteger(0);
    private Map<Integer, Theater> theaterMap = new HashMap<>();

    private Map<String, List<Theater>> cityTheaterMap = new HashMap<>();


    public Map<Integer, Theater> getTheaterMap() {
        return theaterMap;
    }

    public void setTheaterMap(Map<Integer, Theater> theaterMap) {
        this.theaterMap = theaterMap;
    }

    public Map<String, List<Theater>> getCityTheaterMap() {
        return cityTheaterMap;
    }

    public int addTheater(String city){
        int id = this.id.incrementAndGet();
        Theater theater = new Theater(id, city);
        this.theaterMap.put(id, theater);

        if(this.cityTheaterMap.containsKey(city.toLowerCase())){
            List<Theater> existingCityTheaterList = this.cityTheaterMap.get(city.toLowerCase());
            existingCityTheaterList.add(theater);
        } else {
            this.cityTheaterMap.put(city.toLowerCase(), new ArrayList<>(Arrays.asList(theater)));
        }
        return id;
    }

    public Set<String> getCities(){
        return this.cityTheaterMap.keySet();
    }
}
