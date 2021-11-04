package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 1.1
 *
 * 5 Marks
 */
public class Q1Furthest {
    /**
     * Given an array of integers and a special value target,
     * return the value in the array that is numerically furthest from
     * target. If the array contains two different values equally
     * close to target, return the larger value.  If the array is
     * empty, return target.
     *
     * @param in    An array of integers
     * @param target a target value to search for in the array
     * @return the value in the array that is numerically furthest from
     * target, returning the larger of equally close values, and
     * returning target if the array
     * has no entries.
     */
    public static int findFurthest(int[] in, int target) {
        if (in.length == 0) {
            return target;
        }
        int margin = Integer.MIN_VALUE;
        int value = 0;
        for (int i = 0; i < in.length; i++) {
            if (Math.abs(in[i] - target) > margin) {
                margin = Math.abs(in[i] - target);
                value = in[i];
            } else if (Math.abs(in[i] - target) == margin && in[i] > value) {
                value = in[i];
            }
        }
        return value;
    }
}
