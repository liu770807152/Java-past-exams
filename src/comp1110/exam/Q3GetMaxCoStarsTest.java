package comp1110.exam;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * COMP1110 Exam, Question 3.2
 */
public class Q3GetMaxCoStarsTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    String[] names = new String[]{
            "Malcolm X",
            "Boyz n da Hood",
            "Higher Learning"
    };
    int[] years = new int[]{
            1992,
            1991,
            1995
    };
    String[] directors = new String[]{
            "Spike Lee",
            "John Singleton",
            "John Singleton"
    };

    String[][] actors = new String[][]{
            new String[]{"Denzel Washington", "Angela Bassett", "Spike Lee"},
            new String[]{"Ice Cube", "Cuba Gooding, Jr.", "Angela Bassett", "Laurence Fishburne"},
            new String[]{"Jennifer Connolly", "Ice Cube", "Laurence Fishburne"}
    };

    // FIXME add one or more JUnit unit tests for the getMaxCoStars() method of the Q3Hollywood class
    @Test
    public void testGetMaxCoStars() {
        Q3Hollywood library = new Q3Hollywood();
        for (int i = 0; i < names.length; i++) {
            library.addFilm(names[i], years[i], directors[i], Set.of(actors[i]));
        }
        assertEquals("getMaxCoStars() returned incorrect value", 5, library.getMaxCoStars());
    }
}
