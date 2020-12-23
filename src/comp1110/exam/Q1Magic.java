package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 1.2 (harder)
 *
 * 5 Marks
 */
public class Q1Magic {
  /**
   * Given a string, return true if the string represents a magic number,
   * or false otherwise.
   *
   * A magic number is recursively defined as an integer where the sum of
   * the integer's digits are divisible by either three or two, AND the left
   * n - 1 most digits of the number are also a magic number.
   *
   * So for example, 6 is a magic number, and 62 is a magic number since
   * 6+2 = 8, which is divisible by 2 and the left n - 1 digits are '6', which
   * is also a magic number.  22222 and 22224 are both magic numbers.  267 is a
   * magic number since 26 is a magic number and 2+6+7 = 15 which is divisible
   * by three.  Although 267 is a magic number, 2672 is not since 2+6+7+2 = 17
   * which is not divisible by 3 or 2.
   *
   * Zero, an empty string or a string with anything other than a digit from 0-9 is
   * not a magic number.
   *
   * @param number A number
   * @return true if number is a magic number as defined above.
   */
  public static boolean magic(String number) {
    // FIXME complete this method
    return true;
  }
}
