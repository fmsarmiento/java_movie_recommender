
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public void printAverageRatings() {
            String ratingsfile = "data/ratings.csv";
            MovieDatabase MDB = new MovieDatabase();
            MDB.initialize("ratedmoviesfull.csv");
            ThirdRatings obj = new ThirdRatings(ratingsfile);
            int movieSize = MDB.size();
            int raterSize = obj.getRaterSize();
            System.out.println("Movie count is "+movieSize);
            System.out.println("Rater count is "+raterSize);
            int minimalRaters = 35;
            ArrayList<Rating> movieRatings = obj.getAverageRatings(minimalRaters);
            Collections.sort(movieRatings);
            for(Rating elt : movieRatings) {
                String movieID = elt.getItem();
                String movieTitle = MDB.getTitle(movieID);
                System.out.println(elt.getValue() + " " + movieTitle);
            }
            System.out.println(movieRatings.size() +" movies have "+minimalRaters+" or more reviews.");
        }
        
    public void printAverageRatingsByYear() {
            String ratingsfile = "data/ratings.csv";
            MovieDatabase MDB = new MovieDatabase();
            MDB.initialize("ratedmoviesfull.csv");
            ThirdRatings obj = new ThirdRatings(ratingsfile);
            int movieSize = MDB.size();
            int raterSize = obj.getRaterSize();
            System.out.println("Movie count is "+movieSize);
            System.out.println("Rater count is "+raterSize);
            int minimalRaters = 20;
            int yearStart = 2000;
            Filter f = new YearAfterFilter(yearStart);
            ArrayList<Rating> filteredData = obj.getAverageRatingsByFilter(minimalRaters, f);
            Collections.sort(filteredData);
            for(Rating elt : filteredData) {
                String movieID = elt.getItem();
                String movieTitle = MDB.getTitle(movieID);
                int year = MDB.getYear(movieID);
                System.out.println(elt.getValue() + " " + year + " " + movieTitle);
            }
            System.out.println(filteredData.size() +" movies have "+minimalRaters+" or more reviews that are produced after the year "+yearStart);
    }
    
    public void printAverageRatingsGenre() {
            String ratingsfile = "data/ratings.csv";
            MovieDatabase MDB = new MovieDatabase();
            MDB.initialize("ratedmoviesfull.csv");
            ThirdRatings obj = new ThirdRatings(ratingsfile);
            int movieSize = MDB.size();
            int raterSize = obj.getRaterSize();
            System.out.println("Movie count is "+movieSize);
            System.out.println("Rater count is "+raterSize);
            int minimalRaters = 20;
            String genre = "Comedy";
            Filter f = new GenreFilter(genre);
            ArrayList<Rating> filteredData = obj.getAverageRatingsByFilter(minimalRaters, f);
            Collections.sort(filteredData);
            for(Rating elt : filteredData) {
                String movieID = elt.getItem();
                String movieTitle = MDB.getTitle(movieID);
                String genreElt = MDB.getGenres(movieID);
                System.out.println(elt.getValue() + " " + movieTitle + "|\t\t" +genreElt);
            }
            System.out.println(filteredData.size() +" movies have "+minimalRaters+" or more reviews that have the genre "+genre);
    }
    
    public void printAverageRatingsByMinutes() {
            String ratingsfile = "data/ratings.csv";
            MovieDatabase MDB = new MovieDatabase();
            MDB.initialize("ratedmoviesfull.csv");
            ThirdRatings obj = new ThirdRatings(ratingsfile);
            int movieSize = MDB.size();
            int raterSize = obj.getRaterSize();
            System.out.println("Movie count is "+movieSize);
            System.out.println("Rater count is "+raterSize);
            int minimalRaters = 5;
            int min = 105;
            int max = 135;
            Filter f = new MinutesFilter(min, max);
            ArrayList<Rating> filteredData = obj.getAverageRatingsByFilter(minimalRaters, f);
            Collections.sort(filteredData);
            for(Rating elt : filteredData) {
                String movieID = elt.getItem();
                String movieTitle = MDB.getTitle(movieID);
                int minutes = MDB.getMinutes(movieID);
                System.out.println(elt.getValue() + " " + minutes + " minutes " + movieTitle);
            }
            System.out.println(filteredData.size() +" movies have "+minimalRaters+" or more reviews that have length between "+min+" and " +max);
    }
    
    public void printAverageRatingsByDirectors() {
            String ratingsfile = "data/ratings.csv";
            MovieDatabase MDB = new MovieDatabase();
            MDB.initialize("ratedmoviesfull.csv");
            ThirdRatings obj = new ThirdRatings(ratingsfile);
            int movieSize = MDB.size();
            int raterSize = obj.getRaterSize();
            System.out.println("Movie count is "+movieSize);
            System.out.println("Rater count is "+raterSize);
            int minimalRaters = 4;
            String director = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
            Filter f = new DirectorsFilter(director);
            ArrayList<Rating> filteredData = obj.getAverageRatingsByFilter(minimalRaters, f);
            Collections.sort(filteredData);
            for(Rating elt : filteredData) {
                String movieID = elt.getItem();
                String movieTitle = MDB.getTitle(movieID);
                String directorElt = MDB.getDirector(movieID);
                System.out.println(elt.getValue() + " " + movieTitle + "|\t\t" + directorElt);
            }
            System.out.println(filteredData.size() +" movies have "+minimalRaters+" or more reviews that are directed by "+director);
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
            String ratingsfile = "data/ratings.csv";
            MovieDatabase MDB = new MovieDatabase();
            MDB.initialize("ratedmoviesfull.csv");
            ThirdRatings obj = new ThirdRatings(ratingsfile);
            int movieSize = MDB.size();
            int raterSize = obj.getRaterSize();
            System.out.println("Movie count is "+movieSize);
            System.out.println("Rater count is "+raterSize);
            int minimalRaters = 3;
            int min = 90;
            int max = 180;
            String director = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
            AllFilters maf = new AllFilters();
            Filter f1 = new MinutesFilter(min,max);
            Filter f2 = new DirectorsFilter(director);
            maf.addFilter(f1);
            maf.addFilter(f2);
            ArrayList<Rating> filteredData = obj.getAverageRatingsByFilter(minimalRaters, maf);
            Collections.sort(filteredData);
            for(Rating elt : filteredData) {
                String movieID = elt.getItem();
                String movieTitle = MDB.getTitle(movieID);
                String directorElt = MDB.getDirector(movieID);
                int time = MDB.getMinutes(movieID);
                System.out.println(elt.getValue() + " " + movieTitle + " "+ time + "|\t\t" + directorElt);
            }
            System.out.println(filteredData.size() +" movies have "+minimalRaters+ 
                               "or more reviews that are directed by "+director +
                               "\nthat have lengths between "+min + " and " + max);
    }
    
    public void printAverageRatingsByYearAndGenre() {
            String ratingsfile = "data/ratings.csv";
            MovieDatabase MDB = new MovieDatabase();
            MDB.initialize("ratedmoviesfull.csv");
            ThirdRatings obj = new ThirdRatings(ratingsfile);
            int movieSize = MDB.size();
            int raterSize = obj.getRaterSize();
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
}
