package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 1.2 (harder)
 *
 * 5 Marks
 */
public class Q1Ox {
  /**
   * Given an ox word, return the number of 'x's in the ox word, or return -1
   * if the word is not an ox word, and 0 if the word is an empty string.
   *
   * An ox word: a) is composed only of 'o's and 'x's, b) has more 'x's than
   * 'o's, and c) the leading N-1 letters of an ox word must also be an ox word.
   *
   * For example: x is an ox word, but o is not.  All words that start with o,
   * such as oxxx, are therefore not ox words.  xo is not an ox word because
   * it does not have more x's than o's.  All words that start with xo are
   * therefore not ox words.   Thus xox, xoo, oxx, oxo, oox,
   * and ooo are not ox words.  However, xxx and xxo are both ox words because
   * they have more x's than o's and the left N-1 letters are xx, which is an
   * ox word.
   *
   * @param word A word
   * @return the number of 'x's in the word if the word is an ox word (see
   * above), 0 if it is and empty string, and -1 if it is not an ox word.
   */
  public static int ox(String word) {
    return -1;  // FIXME complete this method
  }
}
