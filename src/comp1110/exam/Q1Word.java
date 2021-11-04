package comp1110.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * COMP1110 Final Exam, Question 1.3 (harder)
 *
 * 5 Marks
 */
public class Q1Word {
  /**
   * This question is about a word finding puzzle.
   *
   * You will be given a square grid of upper case characters ('A' to 'Z'),
   * represented as a single array of chars.   Each value in the grid is a
   * letter between 'A' and 'Z'. You will also be given a target word.
   * Your task is to try to find the target word within the grid.  If you find
   * it, you return the index of the grid element holding the first letter of
   * the word.  If you can't find it, return -1.
   *
   * Words can be 'hidden' in the grid by consecutive letters being in
   * horizontally or vertically adjacent elements (but not diagonally).
   * When creating words, letters in the grid may only be used ONCE.
   *
   *
   *         test(new char[]{'W','L','O','A','S','G','C','N','H'}, "LOGS", 1);
   *
   * For example, given the 3 x 3 gird [W, L, O, A, S, G, C, N, H] and the
   * target "LOGS", you would return 1.   Visualizing the grid:
   *
   *    W L O
   *    A S G
   *    C N H
   *
   *    _ L O
   *    _ S G
   *    _ _ _
   *
   * The word LOGS is 'hidden' in the upper right four elements of the grid,
   * starting at offset 1 then going left, then down, then right is 1.
   *
   *
   *
   *         test(new char[]{'G','S','R','Y','I','C','O','I','E','S','K','C','Q','H','A','C','R','A','X','P','X','A','N','X','X','N','M','B','K','Z','R','O','T','G','B','M'}, "CRACKS", 15);

   * Given the 6 x 6 grid [G, S, R, Y, I, C, O, I, E, S, K, C, Q, H, A, C, R, A, X, P, X, A, N, X, X, N, M, B, K, Z, R, O, T, G, B, M],
   * and the target CRACKS, you would return 15.  Visualizing the grid:
   *
   *  G S R Y I C
   *  O I E S K C
   *  Q H A C R A
   *  X P X A N X
   *  X N M B K Z
   *  R O T G B M
   *
   *  _ _ _ _ _ _
   *  _ _ _ S K C
   *  _ _ _ C R A
   *  _ _ _ _ _ _
   *  _ _ _ _ _ _
   *  _ _ _ _ _ _
   *
   *  The word CRACKS is 'hidden' in the grid starting at location 15,
   *  then going right, then right, the up, then left, then left.
   *
   * @param grid An array of characters between A and Z, where the size of the
   *             array is square (1, 4, 9, 16 etc).
   * @param target A target word which may be hidden in the grid.
   * @return If the target word is hidden in the grid, return the location
   * (index) within the grid of the first letter of the target word, or -1
   * if it is not in the grid.
   */
  public static int find(char[] grid, String target) {
    return traverseGrid(grid, target);
  }

  private static int traverseGrid(char[] grid, String target) {
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
      if (grid[i] == target.charAt(0) && travel(grid, target, boundaries, nextPosition, visited, i, "")) {
        return i;
      }
    }
    return -1;
  }

  private static boolean travel(char[] grid, String target,
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
