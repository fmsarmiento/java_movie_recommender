
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> result = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord rec : parser) {
            Movie tmp = new Movie(rec.get("id"), rec.get("title"), rec.get("year")
                        ,rec.get("genre"), rec.get("director"), rec.get("country")
                        ,rec.get("poster"), Integer.parseInt(rec.get("minutes")));
            result.add(tmp);
        }
        System.out.println("Read data for "+result.size()+" movies.");
        return result;
    }
    
    public void PrintAllMovies() {
        ArrayList<Movie> result = loadMovies("data/ratedmovies_short.csv");
        for(Movie elt:result) {
            System.out.println(elt);
        }
    }
    
    private String[] CommaSplit(String item) {
        String[] elts = item.split(",");
        return elts;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> result = loadMovies("data/ratedmoviesfull.csv");
        HashMap<String,Integer> directors = new HashMap<String,Integer>();
        ArrayList<Movie> genreFilter = new ArrayList<Movie>();
        ArrayList<Movie> lengthFilter = new ArrayList<Movie>();
        String chosenGenre = "Comedy";
        int chosenLength = 150;
        for(Movie elt:result) {
            String genre = elt.getGenres();
            //System.out.println("Genre: "+genre);
            if (genre.contains(chosenGenre)) {
                genreFilter.add(elt);
            }
            int movieLen = elt.getMinutes();
            //System.out.println("Movie Length: "+movieLen);
            if (movieLen > chosenLength) {
                lengthFilter.add(elt);
            }
            String[] director = CommaSplit(elt.getDirector()); // directors can be 2 or more.
            for (String s2 : director) {
                System.out.println("Director: "+s2);
                if(directors.containsKey(s2)) {
                    directors.put(s2, directors.get(s2)+1);
                }
                else {
                    directors.put(s2, 1);
                }
            }
        }
        int maximum=0;
        for (String s : directors.keySet()) {
            int count = directors.get(s);
            if(count>maximum) {
                maximum=count;
            }
        }
        for (String s : directors.keySet()) {
            if(directors.get(s) == maximum) {
                System.out.println("Director "+s+" directed a maximum of "+maximum+" movies.");
            }
        }
        System.out.println("There are "+genreFilter.size()+" movies with the genre "+chosenGenre);
        System.out.println("There are "+lengthFilter.size()+" movies that are greater than "+chosenLength+" minutes.");
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> result = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        boolean inResult = false;
        for (CSVRecord rec : parser) {
            Double Rating = Double.parseDouble(rec.get("rating"));
            String MovieID = rec.get("movie_id");
            String RaterID = rec.get("rater_id");
            //System.out.println("|"+Rating+"|"+MovieID+"|"+RaterID+"|");
            for (Rater elt : result) {
                if (elt.getID().equals(RaterID)) {
                    elt.addRating(MovieID, Rating);
                    inResult = true;
                    break;
                }
                inResult = false;
            }
            if(inResult == false) {
                Rater tmp = new EfficientRater(RaterID);
                tmp.addRating(MovieID, Rating);
                result.add(tmp);
            }
        }
        System.out.println("Read data for "+result.size()+" raters.");
        return result;
    }
    
    private void printRaters(ArrayList<Rater> raters) {
        for(Rater elt : raters) {
            int totalRatings = elt.numRatings();
            String raterID = elt.getID();
            ArrayList<String> ratedMovies = elt.getItemsRated();
            System.out.println("-----");
            System.out.println("Rater "+raterID+" has rated a total of "+totalRatings+" movies.");
            for (String movie : ratedMovies) {
                System.out.println(movie+" rated at "+elt.getRating(movie));
            }
        }
    }
    
    private int findRater(ArrayList<Rater> raters, String raterID) {
        for(Rater elt : raters) {
            if (elt.getID().equals(raterID)) {
                return elt.numRatings();
            }
        }
        return -1;
    }
    
    private void maxRatings(ArrayList<Rater> raters) {
        int max = 0;
        for (Rater elt : raters ) {
            if(elt.numRatings() > max) {
                max = elt.numRatings();
            }
        }
        for (Rater elt : raters) {
            if(elt.numRatings() == max) {
                System.out.println("Rater "+elt.getID()+" rated at a maximum of "+max+" times.");
            }
        }
    }
    
    private void movieRating(ArrayList<Rater> raters, String movieID) {
        int count = 0;
        for (Rater elt : raters) {
            if(elt.getItemsRated().contains(movieID)) {
                count++;
            }
        }
        System.out.println("Movie "+movieID+" has been rated "+count+" times.");
    }
    
    private void numMovieRatings(ArrayList<Rater> raters) {
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for (Rater elt : raters) {
            for(String movie : elt.getItemsRated()) {
                if(!uniqueMovies.contains(movie)) {
                    uniqueMovies.add(movie);
                }
            }
        }
        System.out.println("Total movie rating count is "+uniqueMovies.size());
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        raters = loadRaters("data/ratings.csv");
        printRaters(raters);
        String raterID = "193";
        String movieID = "1798709";
        System.out.println(raterID + " has rated "+findRater(raters, raterID));
        maxRatings(raters);
        movieRating(raters,movieID);
        numMovieRatings(raters);
    }
}
