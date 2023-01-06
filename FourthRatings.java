
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    
    private double getAverageByID(String id, int minimalRaters) {
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    
    private double dotProduct(Rater me, Rater r) {
        double result = 0.0;
        int count = 0;
        ArrayList<String> yourRatings = me.getItemsRated();
        ArrayList<String> rRatings = r.getItemsRated();
        for(String yourRating : yourRatings) {
            if(rRatings.contains(yourRating)) {
                double yourRate = me.getRating(yourRating) - 5;
                double rRate = r.getRating(yourRating) - 5;
                result = result + (yourRate * rRate);
            }
        }
        return result;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if(!id.equals(r.getID())) {
                //System.out.println("Size of you: "+me.numRatings()+" size of other: "+r.numRatings());
                double simWeight = dotProduct(me, r);
                if (simWeight > 0.0) {
                    list.add(new Rating(r.getID(),simWeight));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        // returns a list of Rating, id= rater ID, value = weighted similarity product
    return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> list = new ArrayList<Rating>(); // id = movie | weighted ave rating = value
        ArrayList<Rating> tmp = getSimilarities(id);
        //System.out.println("tmp size is "+tmp.size());
        ArrayList<Rating> raterSimilarity = new ArrayList<Rating>();
        for(int i=0;i<numSimilarRaters;i++) { //possible indexing error here.
            raterSimilarity.add(tmp.get(i));
        }
        TrueFilter f = new TrueFilter();
        for (String movieID : MovieDatabase.filterBy(f)) {
            double movieWeight = 0.0;
            int count = 0;
            for (Rating rater : raterSimilarity) {
                Rater raterDB = RaterDatabase.getRater(rater.getItem());
                double raterWeight = rater.getValue();
                //System.out.println("Rater is "+ raterDB.getID() +" and closeness is "+raterWeight);
                if( raterDB.hasRating(movieID) ) {
                    double raterRating = raterDB.getRating(movieID);
                    movieWeight = movieWeight + (raterWeight * raterRating);
                    count++;
                }
            }
            if (count >= minimalRaters) {
                list.add(new Rating(movieID, movieWeight/count));
                //System.out.println("Weight is "+(movieWeight/count));
                //System.out.println("Movie weight is "+movieWeight+" and count is "+ count + " for the movie " +MovieDatabase.getTitle(movieID));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> list = new ArrayList<Rating>(); // id = movie | weighted ave rating = value
        ArrayList<Rating> tmp = getSimilarities(id);
        //System.out.println("tmp size is "+tmp.size());
        ArrayList<Rating> raterSimilarity = new ArrayList<Rating>();
        for(int i=0;i<numSimilarRaters;i++) { //possible indexing error here.
            raterSimilarity.add(tmp.get(i));
        }
        for (String movieID : MovieDatabase.filterBy(filterCriteria)) {
            double movieWeight = 0.0;
            int count = 0;
            for (Rating rater : raterSimilarity) {
                Rater raterDB = RaterDatabase.getRater(rater.getItem());
                double raterWeight = rater.getValue();
                //System.out.println("Rater is "+ raterDB.getID() +" and closeness is "+raterWeight);
                if( raterDB.hasRating(movieID) ) {
                    double raterRating = raterDB.getRating(movieID);
                    movieWeight = movieWeight + (raterWeight * raterRating);
                    count++;
                }
            }
            if (count >= minimalRaters) {
                list.add(new Rating(movieID, movieWeight/count));
                //System.out.println("Weight is "+(movieWeight/count));
                //System.out.println("Movie weight is "+movieWeight+" and count is "+ count + " for the movie " +MovieDatabase.getTitle(movieID));
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    
}
