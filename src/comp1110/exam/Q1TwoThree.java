package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 1.2 (harder)
 * <p>
 * 5 Marks
 */
public class Q1TwoThree {
    /**
     * Given a string, return true if the string represents a twothree number,
     * or false otherwise.
     * <p>
     * A twothree number is recursively defined as an integer that is divisible
     * by either two or three AND the left n - 1 most digits of the number are
     * also a twothree number.
     * <p>
     * So for example, 6 is a twothree number, and 62 is a twothree number since
     * 62 is divisible by 2 and the left n - 1 digits are '6', which is also
     * a twothree number.  22222 is a twothree number.  267 is a
     * twothree number but 265 is not since 265 is not divisible by 2 or 3. 2652
     * is not a twothree number because 265 is not a twothree number.  0 is not
     * a two-three number.
     * <p>
     * An empty string or a string with anything other than a digit from 0-9 is
     * not a twothree number.
     *
     * @param number A number
     * @return true if number is a twothree number as defined above.
     */
    public static boolean twothree(String number) {
        if (number == null || number.isEmpty() || Integer.parseInt(number) == 0) {
            return false;
        }
        for (int i = 0; i < number.length(); i++) {
            String temp = number.substring(0, number.length() - i);
            int tempNumber = Integer.parseInt(temp);
            if (!(tempNumber % 2 == 0 || tempNumber % 3 == 0)) {
                return false;
            }
        }
        return true;
    }

//  private static boolean checkSubNum(String number) {
//    return true;
//  }
//
//  private static boolean checkNum(String number) {
//    return true;
//  }
}
