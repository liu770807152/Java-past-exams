package comp1110.exam;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q1OxTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    @Test
    public void testEmpty() {
        test("", 0);
    }

    @Test
    public void testOne() {
        test("a", -1);
        test("x", 1);
        test("o", -1);
    }

    @Test
    public void testTwo() {
        test("xx", 2);
        test("xo", -1);
        test("oo", -1);
        test("aa", -1);
        test("ab", -1);
    }

    @Test
    public void testThree() {
        test("xxx", 3);
        test("xxo", 2);
        test("xxs", -1);
        test("xoo", -1);
        test("xox", -1);
        test("oox", -1);
        test("oxo", -1);
        test("oxx", -1);
    }

    @Test
    public void testFive() {
        test("xxxxx", 5);
        test("xxxxo", 4);
        test("xxoxo", 3);
        test("xxxoo", 3);
        test("xoxxx", -1);
    }

    private void test(String s, int e) {
        int r = Q1Ox.ox(s);
        assertTrue("For string \""+s+"\", expected '"+e+"' but got '"+r+"'", e == r);
    }
}
