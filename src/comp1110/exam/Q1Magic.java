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
  // Recursion (省去了很多控制流) == Iteration
  public static boolean magic(String number) {
    try {
      if (number.isEmpty() || Integer.parseInt(number) == 0) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
      // 4567 -> (n - 1 = 3)456  -> (n - 1 = 2)45  -> (n - 1 = 1)4
      int sum;
      String subNum;
      for (int i = 0; i < number.length(); i++) {
        subNum = number.substring(0, number.length() - i);
        sum = getSum(subNum);
        // fail fast
        if (!(sum % 2 == 0 || sum % 3 == 0)) {
          return false;
        }
      }
      return true;
  }

  private static int getSum(String number) {
    int sum = 0;
    for (int i = 0; i < number.length(); i++) {
      sum += number.charAt(i) - 48;
    }
    return sum;
  }
}
