
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings obj = new FirstRatings();
        myMovies = obj.loadMovies(moviefile);
        myRaters = obj.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
    
    public String getTitle(String id) {
        String result="ID NOT FOUND";
        for(Movie elt : myMovies) {
            if(elt.getID().equals(id)) {
                result = elt.getTitle();
            }
        }
        return result;
    }
    
    public String getID(String title) {
        String result = "NO SUCH TITLE";
        for(Movie elt : myMovies) {
            if(elt.getTitle().equals(title)) {
                result = elt.getID();
            }
        }
        return result;
    }
}
