
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    
    public void printAverageRatings() {
        String ratingsfile = "data/ratings.csv";
        String moviesfile = "data/ratedmoviesfull.csv";
        SecondRatings obj = new SecondRatings(moviesfile, ratingsfile);
        int movieSize = obj.getMovieSize();
        int raterSize = obj.getRaterSize();
        System.out.println("Movie count is "+movieSize);
        System.out.println("Rater count is "+raterSize);
        int minimalRaters = 12;
        ArrayList<Rating> movieRatings = obj.getAverageRatings(minimalRaters);
        Collections.sort(movieRatings);
        for(Rating elt : movieRatings) {
            String movieID = elt.getItem();
            String movieTitle = obj.getTitle(movieID);
            System.out.println(elt.getValue() + " " + movieTitle);
        }
        System.out.println(movieRatings.size() +"movies have "+minimalRaters+" or more reviews.");
    }
    
    public void getAverageRatingOneMovie() {
        String ratingsfile = "data/ratings.csv";
        String moviesfile = "data/ratedmoviesfull.csv";
        String movieTitle = "Vacation";
        SecondRatings obj = new SecondRatings(moviesfile, ratingsfile);
        int minimalRaters = 3;
        ArrayList<Rating> movieRatings = obj.getAverageRatings(minimalRaters);
        Collections.sort(movieRatings);
        for(Rating elt : movieRatings) {
            String eltMovieID = elt.getItem();
            String eltTitle = obj.getTitle(eltMovieID);
            if(eltTitle.equals(movieTitle)) {
                System.out.println(elt.getValue() + " " + movieTitle);
            }
        }
    }
}
