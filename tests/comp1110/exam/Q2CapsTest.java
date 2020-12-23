package comp1110.exam;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Q2CapsTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    static final String INPUT_FILENAME_BASE = "assets/Q2Caps";
    static final String OUTPUT_FILENAME = "assets/Q2output";

    @Before
    public void setup() {
        try {
            Files.deleteIfExists(Paths.get(OUTPUT_FILENAME));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Test
    public void empty() {
        test("A", false, "", "");
    }

    @Test
    public void small() {
        test("B", false, "hi! hi ! hi", "hi! hi ! hi");
    }

    @Test
    public void smallChecksum() {
        test("B", true, "HI! hi ! hi", "hi! hi ! hi");
    }

    @Test
    public void medChecksum() {
        test("C", true, "Lorem Ipsum is not simply random TEXT!  It is over 2000 years OLD!!", "Lorem Ipsum is not simply random text!  It is over 2000 years old!!");
    }

    @Test
    public void largeChecksum() {
        test("D", true, "Contrary to popular belief,   Lorem Ipsum is not simply random TEXT!   It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years OLD!! More?!? Richard McClintock", "Contrary to popular belief,   Lorem Ipsum is not simply random text!   It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old!! More?!? Richard McClintock");
    }

    private void test(String insuf, boolean cs, String expected, String content) {
        Q2Caps.capitalize(INPUT_FILENAME_BASE+insuf, OUTPUT_FILENAME, cs);
        try {
            assertTrue("Called Q2Caps.capitalize("+INPUT_FILENAME_BASE+insuf+", "+OUTPUT_FILENAME+", "+cs+"). Expected file \""+OUTPUT_FILENAME+"\", but no file was found", Files.exists(Paths.get(OUTPUT_FILENAME)));
            String actual = Files.readString(Paths.get(OUTPUT_FILENAME));
            assertTrue("Called Q2Caps.capitalize("+INPUT_FILENAME_BASE+insuf+", "+OUTPUT_FILENAME+", "+cs+"). Expected file to contain \""+expected+"\" but got \""+actual+"\" when file contained \""+content+"\".", actual.equals(expected));
        } catch (IOException e) {
            System.out.println(e);
            fail(e.getMessage());
        }
    }
}
