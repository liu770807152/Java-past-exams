package comp1110.exam;

import java.util.ArrayList;

/**
 * COMP1110 Final Exam, Question 1.3 (harder)
 *
 * 5 Marks
 */
public class Q1Number {
  /**
   * This question is about a number finding puzzle.
   *
   * You will be given a square grid of numbers, represented as a single
   * array of integers.   Each value in the grid is a number between 0 and 9.
   * You will also be given a target number.   Your task is to try to find
   * the target number within the grid.  If you find it, you return the
   * index of the grid element holding the first digit of the number.  If
   * you can't find it, return -1.
   *
   * Numbers can be 'hidden' in the grid by consecutive digits being in
   * horizontally or vertically adjacent elements (but not diagonally).
   * When creating numbers, digits in the grid may only be used ONCE.
   *
   * For example, given the 3 x 3 grid [2, 4, 4, 0, 5, 7, 7, 1, 4] and the
   * target 42, you would return 1.   Visualizing the grid:
   *
   *    2 4 4
   *    0 5 7
   *    7 1 4
   *
   *    2 4 _
   *    _ _ _
   *    _ _ _
   *
   * The number 42 is 'hidden' in the first two elements of the grid, starting
   * at offset 1 then going left, so the answer is 1.
   *
   * Given the 6 x 6 grid [0, 8, 0, 9, 1, 1, 6, 7, 6, 3, 2, 9, 2, 7, 7, 9, 2, 8, 4, 0, 9, 2, 9, 2, 0, 5, 1, 1, 5, 9, 8, 5, 7, 1, 1, 7],
   * and the target 91507, you would return 20.  Visualizing the grid:
   *
   *  0 8 0 9 1 1
   *  6 7 6 3 2 9
   *  2 7 7 9 2 8
   *  4 0 9 2 9 2
   *  0 5 1 1 5 9
   *  8 5 7 1 1 7
   *
   *  _ _ _ _ _ _
   *  _ _ _ _ _ _
   *  _ 7 _ _ _ _
   *  _ 0 9 _ _ _
   *  _ 5 1 _ _ _
   *  _ _ _ _ _ _
   *
   *  The number 91507 is 'hidden' in the grid starting at location 20,
   *  then going down, then left, then up twice.
   *
   * @param grid An array of integers between 0 and 9, where the size of the
   *             array is square (1, 4, 9, 16 etc).
   * @param target A target number which may be hidden in the grid.
   * @return If the target number is hidden in the grid, return the location
   * (index) within the grid of the first digit of the target number, or -1
   * if it is not in the grid.
   */
  public static int find(int[] grid, int target) {
    return traverseGrid(grid, target + "");
  }

  private static int traverseGrid(int[] grid, String target) {
    int length = (int) Math.sqrt(grid.length);
    // 求出所有边界
    ArrayList<ArrayList<Integer>> boundaries = new ArrayList<>();
    ArrayList<Integer> boundary = new ArrayList<>();
    // 上边界
    for (int j = 0; j < length; j++) {
      boundary.add(j);
    }
    boundaries.add((ArrayList<Integer>) boundary.clone());
    boundary.clear();
    // 下边界
    for (int j = 0; j < length; j++) {
      boundary.add((int) Math.pow(length, 2) - length + j);
    }
    boundaries.add((ArrayList<Integer>) boundary.clone());
    boundary.clear();
    // 左边界
    for (int j = 0; j < length; j++) {
      boundary.add(j * length);
    }
    boundaries.add((ArrayList<Integer>) boundary.clone());
    boundary.clear();
    // 右边界
    for (int j = 0; j < length; j++) {
      boundary.add(j * length + length - 1);
    }
    boundaries.add((ArrayList<Integer>) boundary.clone());
    boundary.clear();
    ArrayList<Integer> nextPosition = new ArrayList<>() {{
      add(-length);
      add(length);
      add(-1);
      add(1);
    }};

    boolean[] visited = new boolean[grid.length];
    for (int i = 0; i < grid.length; i++) {
      if (grid[i] == target.charAt(0) - '0' && travel(grid, target, boundaries, nextPosition, visited, i, "")) {
        return i;
      }
    }
    return -1;
  }

  private static boolean travel(int[] grid, String target,
                                  ArrayList<ArrayList<Integer>> boundaries, ArrayList<Integer> nextPosition, boolean[] visited, int position, String current) {
    if (position >= grid.length ||
            visited[position] ||
            !compareString(target, current + grid[position])) {
      return false;
    }
    current += grid[position];
    visited[position] = true;
    // 找到则返回起始位置
    if (current.equals(target)) {
      return true;
    }
    // 没找到，先判断当前位置是不是边界
    ArrayList<Boolean> isBoundary = new ArrayList<>();
    for (int i = 0; i < boundaries.size(); i++) {
      if (boundaries.get(i).contains(position)) {
        isBoundary.add(true);
      } else {
        isBoundary.add(false);
      }
    }
    // 再根据是否为边界决定探索方向
    boolean result;
    for (int i = 0; i < isBoundary.size(); i++) {
      if (!isBoundary.get(i)) {
        result = travel(grid, target, boundaries, nextPosition, visited, position + nextPosition.get(i), current);
        if (result) {
          return true;
        }
      }
    }
    visited[position] = false;
    return false;
  }

  private static boolean compareString(String target, String current) {
    if (current.length() > target.length()) {
      return false;
    } else if (current.length() < target.length()) {
      String sub = target.substring(0, current.length());
      return sub.equals(current);
    } else {
      return current.equals(target);
    }
  }
}
