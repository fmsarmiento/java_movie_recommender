
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender {
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("1843866"); //Captain America: The Winter Soldier
        result.add("2245084"); //Big Hero 6
        result.add("2802144"); //Kingsman: The Secret Service
        result.add("1285016"); //The Social Network
        result.add("1259521"); //The Cabin in the Woods
        result.add("0478970"); //Ant-Man
        result.add("1772341"); //Wreck-It Ralph
        result.add("2293640"); //Minions
        result.add("2322441"); //Fifty Shades of Grey
        result.add("0071562"); //The Godfather: Part II
        return result;
    }
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings obj = new FourthRatings();
        MovieDatabase MDB = new MovieDatabase();
        RaterDatabase RDB = new RaterDatabase();
        MDB.initialize("ratedmoviesfull.csv");
        RDB.initialize("ratings.csv");
        int movieSize = MDB.size();
        int raterSize = RDB.size();
        System.out.println("Movie count is "+movieSize+"</br>");
        System.out.println("Rater count is "+raterSize+"</br></br>");
        String id = "71";
        int numSimilarRaters = 20;
        int minimalRaters =5;
        ArrayList<Rating> similarRatings = obj.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        int i = 1;
        for (Rating movie : similarRatings) {
            System.out.println(i+ ": " + MDB.getTitle(movie.getItem()) + " | Weight of the recommendation is " + movie.getValue()+"</br>");
            i++;
            if(i == 21) {
                break;
            }
        }
    }
}
