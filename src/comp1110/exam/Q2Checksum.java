package comp1110.exam;

import java.io.*;
import java.util.ArrayList;

/**
 * COMP1110 Final Exam, Question 2
 *
 * 5 Marks
 */
public class Q2Checksum {

  /**
   * Q2 Part I (2 marks)
   *
   * Open the specified input file.  If the checksum boolean is false, simply
   * copy all bytes of the input file to the specified output file.  Part II
   * covers the case where the checksum boolean is true.
   *
   *
   * Q2 Part II (3 marks)
   *
   * Open the specified input file.  If the checksum boolean is true and there
   * is at least 1 byte read in then copy all bytes of the input file to the
   * output file, inserting checksum letters before every ten (10) bytes, or
   * less if the end of file is reached. Otherwise simply copy all bytes of the
   * input file to the specified output file.
   *
   * The checksum is a letter from 'a' to 'z' which represents the integer
   * remainder of the sum of the next ten bytes (or all remaining bytes if
   * there are less than ten).
   *
   * So, if the file contained one byte, which had the value 78 ('N'), then
   * the checksum would 78 % 26 = 0, so the checksum character would be 'a',
   * and the output file would contain two bytes: 'a' followed by 'N'.
   *
   * If the input file contained two bytes which had the values 78 and 104
   * ('Nh'), then the checksum would be (78 + 104) % 26 = 25, so the checksum
   * character would be 'z', and the output file would contain three bytes: 'z'
   * followed by 'Nh'.
   *
   *
   * @param input The name of the input file
   * @param output The name of the output file
   * @param checksum if true, include checksums in the output file
   */
  public static void checksum(String input, String output, boolean checksum) {
    // 第一步： 声明InputStream和OutputStream对象
    InputStream in = null;
    OutputStream out = null;
    // 第二步： 写try block
    try {
      // 第三步： 定义InputStream和OutputStream对象
      in = new FileInputStream(input);
      out = new FileOutputStream(output);
      // byte[] buffer = new byte[10];
      // 第四步： 开始处理读入数据
      if (!checksum) {
        int readIn;
        while ((readIn = in.read()) != -1) {
          out.write(readIn);
        }
      } else {
        // 第四步
        int readIn;
        // 因为需要在每10个字节前插入一个checksum，所以需要暂存这10个字节
        ArrayList<Integer> word = new ArrayList<>();
        int count = 0;
        while ((readIn = in.read()) != -1) {
          word.add(readIn);
          count++;
          if (count == 10) {
            writeFile(word, out, calChecksum(word));
            // 切记清空！
            word.clear();
            count = 0;
          }
        }
        // 把最后不足10个字节的数据也写进去
        if (!word.isEmpty()) {
          writeFile(word, out, calChecksum(word));
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // 第五步： 关闭文件流
      try {
        if (in != null) {
          in.close();
        }
        if (out != null) {
          out.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void writeFile(ArrayList<Integer> word, OutputStream out, int checksum) {
    try {
      // 依据题意，checksum插入在前面
      out.write(checksum);
      for (Integer current : word) {
        out.write(current);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static int calChecksum(ArrayList<Integer> word) {
    int sum = 0;
    for (Integer current : word) {
      sum += current;
    }
    return sum % 26 + 'a';
  }
}
