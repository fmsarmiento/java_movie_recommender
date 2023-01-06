
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings() {
            MovieDatabase MDB = new MovieDatabase();
            RaterDatabase RDB = new RaterDatabase();
            MDB.initialize("ratedmoviesfull.csv");
            RDB.initialize("ratings.csv");
            FourthRatings obj = new FourthRatings();
            int movieSize = MDB.size();
            int raterSize = RDB.size();
            System.out.println("Movie count is "+movieSize);
            System.out.println("Rater count is "+raterSize);
            int minimalRaters = 5;
            ArrayList<Rating> movieRatings = obj.getAverageRatings(minimalRaters);
            Collections.sort(movieRatings);
            for(Rating elt : movieRatings) {
                String movieID = elt.getItem();
                String movieTitle = MDB.getTitle(movieID);
                System.out.println(elt.getValue() + " " + movieTitle + " code is " + movieID);
            }
            System.out.println(movieRatings.size() +" movies have "+minimalRaters+" or more reviews.");
        }
        
    public void printAverageRatingsByYearAndGenre() {
            MovieDatabase MDB = new MovieDatabase();
            RaterDatabase RDB = new RaterDatabase();
            MDB.initialize("ratedmovies_short.csv");
            RDB.initialize("ratings_short.csv");
            FourthRatings obj = new FourthRatings();
            int movieSize = MDB.size();
            int raterSize = RDB.size();
            System.out.println("Movie count is "+movieSize);
            System.out.println("Rater count is "+raterSize);
            int minimalRaters = 8;
            int yearStart = 1990;
            String genre = "Drama";
            AllFilters maf = new AllFilters();
            Filter f1 = new YearAfterFilter(yearStart);
            Filter f2 = new GenreFilter(genre);
            maf.addFilter(f1);
            maf.addFilter(f2);
            ArrayList<Rating> filteredData = obj.getAverageRatingsByFilter(minimalRaters, maf);
            Collections.sort(filteredData);
            for(Rating elt : filteredData) {
                String movieID = elt.getItem();
                String movieTitle = MDB.getTitle(movieID);
                int year = MDB.getYear(movieID);
                String genreElt = MDB.getGenres(movieID);
                System.out.println(elt.getValue() + " " + year + " " + genreElt + " " + movieTitle);
            }
            System.out.println(filteredData.size() +" movies have "+minimalRaters+
            " or more reviews that are produced after the year "+yearStart+
            " which are of the genre "+genre);
    }
    
    void printSimilarRatings() {
        MovieDatabase MDB = new MovieDatabase();
        RaterDatabase RDB = new RaterDatabase();
        MDB.initialize("ratedmoviesfull.csv");
        RDB.initialize("ratings.csv");
        FourthRatings obj = new FourthRatings();
        int movieSize = MDB.size();
        int raterSize = RDB.size();
        System.out.println("Movie count is "+movieSize);
        System.out.println("Rater count is "+raterSize);
        String id = "71";
        int numSimilarRaters = 20;
        int minimalRaters =5;
        ArrayList<Rating> similarRatings = obj.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        int i = 1;
        for (Rating movie : similarRatings) {
            System.out.println(i+ ": " + MDB.getTitle(movie.getItem()) + " | " + movie.getValue());
            i++;
        }
    }
    
    void printSimilarRatingsByGenre() {
        MovieDatabase MDB = new MovieDatabase();
        RaterDatabase RDB = new RaterDatabase();
        MDB.initialize("ratedmoviesfull.csv");
        RDB.initialize("ratings.csv");
        FourthRatings obj = new FourthRatings();
        int movieSize = MDB.size();
        int raterSize = RDB.size();
        System.out.println("Movie count is "+movieSize);
        System.out.println("Rater count is "+raterSize);
        String id = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = "Mystery";
        Filter f = new GenreFilter(genre);
        ArrayList<Rating> similarRatings = obj.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);
        int i = 1;
        for (Rating movie : similarRatings) {
            System.out.println(i+ ": " + MDB.getTitle(movie.getItem()) + " | " + movie.getValue());
            i++;
        }
    }
    
    void printSimilarRatingsByDirector() {
        MovieDatabase MDB = new MovieDatabase();
        RaterDatabase RDB = new RaterDatabase();
        MDB.initialize("ratedmoviesfull.csv");
        RDB.initialize("ratings.csv");
        FourthRatings obj = new FourthRatings();
        int movieSize = MDB.size();
        int raterSize = RDB.size();
        System.out.println("Movie count is "+movieSize);
        System.out.println("Rater count is "+raterSize);
        String id = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        Filter f = new DirectorsFilter(director);
        ArrayList<Rating> similarRatings = obj.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);
        int i = 1;
        for (Rating movie : similarRatings) {
            System.out.println(i+ ": " + MDB.getTitle(movie.getItem()) + " | " + movie.getValue());
            i++;
        }
    }
    
    void printSimilarRatingsByGenreAndMinutes() {
        MovieDatabase MDB = new MovieDatabase();
        RaterDatabase RDB = new RaterDatabase();
        MDB.initialize("ratedmoviesfull.csv");
        RDB.initialize("ratings.csv");
        FourthRatings obj = new FourthRatings();
        int movieSize = MDB.size();
        int raterSize = RDB.size();
        System.out.println("Movie count is "+movieSize);
        System.out.println("Rater count is "+raterSize);
        String id = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        String genre = "Drama";
        int min = 80;
        int max = 160;
        AllFilters f = new AllFilters();
        Filter f1 = new GenreFilter(genre);
        Filter f2 = new MinutesFilter(min, max);
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> similarRatings = obj.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);
        int i = 1;
        for (Rating movie : similarRatings) {
            System.out.println(i+ ": " + MDB.getTitle(movie.getItem()) + " | " + movie.getValue());
            i++;
        }
    }
    
    void printSimilarRatingsByYearAfterAndMinutes() {
        MovieDatabase MDB = new MovieDatabase();
        RaterDatabase RDB = new RaterDatabase();
        MDB.initialize("ratedmoviesfull.csv");
        RDB.initialize("ratings.csv");
        FourthRatings obj = new FourthRatings();
        int movieSize = MDB.size();
        int raterSize = RDB.size();
        System.out.println("Movie count is "+movieSize);
        System.out.println("Rater count is "+raterSize);
        String id = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        int year = 1975;
        int min = 70;
        int max = 200;
        AllFilters f = new AllFilters();
        Filter f1 = new YearAfterFilter(year);
        Filter f2 = new MinutesFilter(min, max);
        f.addFilter(f1);
        f.addFilter(f2);
        ArrayList<Rating> similarRatings = obj.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);
        int i = 1;
        for (Rating movie : similarRatings) {
            System.out.println(i+ ": " + MDB.getTitle(movie.getItem()) + " | " + movie.getValue());
            i++;
        }
    }
}
