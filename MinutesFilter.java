
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int min;
    private int max;
    
    public MinutesFilter(int minutesMin, int minutesMax) {
        min = minutesMin;
        max = minutesMax;
    }
    
    @Override
	public boolean satisfies(String id) {
	    return ((MovieDatabase.getMinutes(id) >= min) && (MovieDatabase.getMinutes(id) <= max));
	}
}
