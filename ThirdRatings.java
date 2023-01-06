
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings obj = new FirstRatings();
        myRaters = obj.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double result = 0.0;
        double totalrating = 0;
        double count = 0;
        for (Rater elt : myRaters) {
            if(elt.getItemsRated().contains(id)) {
                totalrating = totalrating + elt.getRating(id);
                count = count + 1;
            }
        }
        if(count >= minimalRaters) {
            result = totalrating/count;
        }
        //System.out.println(getTitle(id)+" has "+count+" ratings. ");
        return result;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for (Rater elt : myRaters) {
            for(String movie : elt.getItemsRated()) {
                if(!uniqueMovies.contains(movie)) {
                    uniqueMovies.add(movie);
                }
            }
        }
        for (String uniqueMovie : uniqueMovies) {
            double average = getAverageByID(uniqueMovie, minimalRaters);
            //System.out.println("Average of "+getTitle(uniqueMovie)+" is "+average);
            if (average != 0.0) {
                result.add(new Rating(uniqueMovie,average));
            }
        }
        return result;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rating> aveRatings = getAverageRatings(minimalRaters);
        ArrayList<String> filtered = MovieDatabase.filterBy(filterCriteria);
        for(Rating ar : aveRatings) {
            if (filterCriteria.satisfies(ar.getItem())) {
                result.add(ar);
            }
        }
        return result;
    }
}
