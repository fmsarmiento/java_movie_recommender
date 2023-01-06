
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] director;
    public DirectorsFilter(String directorMovie) {
        director = directorMovie.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        int has=0;
        for( String direc : director ) {
            if(MovieDatabase.getDirector(id).contains(direc)) {
                has = 1;
            }
        }
        if (has == 1) {
            return true;
        }
        return false;
    }
}
