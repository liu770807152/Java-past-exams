package comp1110.exam;

import java.io.*;
import java.util.ArrayList;

/**
 * COMP1110 Final Exam, Question 2
 *
 * 5 Marks
 */
public class Q2Caps {

  /**
   *
   * Q2 Part I (2 marks)
   *
   * Open the specified input file.  If the caps boolean is false, simply
   * copy all bytes of the input file to the specified output file.  Part II
   * covers the case where the caps boolean is true.
   *
   *
   * Q2 Part II (3 marks)
   *
   * Open the specified input file.  If the caps boolean is true then copy all
   * bytes of the input file directly to the output file, except any word of
   * twenty characters or less that is immediately followed by an exclamation
   * mark ('!').  Such words should be converted to all caps before copied to
   * the output file.  Words longer than 20 characters are copied directly,
   * without capitalizing.
   *
   * A word is defined as a sequence of alphabetical characters ('a' .. 'z' and
   * 'A' .. 'Z') preceded and followed by a non-alphabetical character.
   *
   * Examples (input file contents on left, output on right):
   *  "hi" -> "hi"
   *  "hi!" -> "HI!"
   *  "hi !" -> "hi !"
   *  "Hi there!" -> "Hi THERE!"
   *  "6hi!  Foo" -> "6HI!  Foo"
   *  "6hi4!  Foo" -> "6hi4!  Foo"
   *  "super!" -> "SUPER!"
   *  "supercalifragilisticexpialidocious!" -> "supercalifragilisticexpialidocious!"
   *
   * @param input The name of the input file
   * @param output The name of the output file
   * @param caps if true, capitalize words 20 characters or less if they
   *             are followed by an exclamation mark.
   */
  public static void capitalize(String input, String output, boolean caps) {
    // 第一步： 声明InputStream和OutputStream对象
    FileInputStream in = null;
    FileOutputStream out = null;
    // 第二步： 写try block
    try {
      // 第三步： 定义InputStream和OutputStream对象
      in = new FileInputStream(input);
      out = new FileOutputStream(output);

      if (!caps) {
        // 第四步： 开始处理读入数据
        int readIn;
        // 读取
        while ((readIn = in.read()) != -1) { // '\n'
          // 写入
          out.write(readIn);
        }
      } else {
        // 第四步
        int readIn;
        ArrayList<Integer> word = new ArrayList<>();
        int count = 0;
        // 读取
        while ((readIn = in.read()) != -1) {
          if (readIn == ' ') {
            writeFile(word, out, ' ');
            count = 0;
          } else if (readIn == '!' && count <= 20) {
            // fail fast
            if (word.size() == 0) {
              out.write('!');
              continue;
            }
            int temp = word.get(word.size() - 1);
            if ((temp >= 'a' && temp <= 'z') || (temp >= 'A' && temp <= 'Z')) {
              word = transform(word);
            }
            writeFile(word, out, '!');
          } else {
            word.add(readIn);
            count++;
          }
        }
        // 如果最后的单词不以' ' | '!'结尾，补入
        if (word.size() != 0) {
          writeFile(word, out, '$');
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 第五步： 关闭文件流
    finally {
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

  private static void writeFile(ArrayList<Integer> word, FileOutputStream out, char sep) {
    try {
      for (Integer current : word) {
        out.write(current);
      }
      if (sep != '$') {
        out.write(sep);
      }
      word.clear();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static ArrayList<Integer> transform(ArrayList<Integer> word) {
    if (word.isEmpty()) {
      return word;
    }
    ArrayList<Integer> result = new ArrayList<>();
    for (Integer current : word) {
      if (current >= 'a' && current <= 'z') {
        current = current - 32;
      }
      result.add(current);
    }
    return result;
  }
}
