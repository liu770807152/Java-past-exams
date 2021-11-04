package comp1110.exam;

/**
 * COMP1110 Final Exam, Question 1.1
 *
 * 5 Marks
 */
public class Q1Floor {
    /**
     * Given an array of integers and a special value floor,
     * return the smallest value in the array that is greater than
     * floor.
     * If there is no value in the array that is greater than
     * floor, return (floor-1).
     *
     * @param in    An array of integers
     * @param floor a target value to search for in the array
     * @return the smallest value in the array that is larger than floor,
     * or floor-1 if there is no such value
     */
    public static int findGreater(int[] in, int floor) {
        // 第一步：类型是求差值  ->  定义临时差值
        int margin = Integer.MAX_VALUE;
        int value = 0;

        // 第二步：遍历给定数组，找到return the smallest value in the array that is greater than floor
        for (int i = 0; i < in.length; i++) {
            if (in[i] > floor && in[i] - floor < margin) {
                margin = in[i] - floor;
                value = in[i];
            }
        }

        // 第三步：如果找到，返回；如果没找到，考虑特殊情况
        if (margin == Integer.MAX_VALUE) {
            return floor - 1;
        } else {
            return value;
        }
    }
}
