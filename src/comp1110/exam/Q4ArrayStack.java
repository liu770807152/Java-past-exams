package comp1110.exam;

import java.util.EmptyStackException;

/**
 * This class represents a stack, in which elements are added and removed in a
 * last in, first out (LIFO) order. Duplicate elements are permitted.
 * When a stack is first created, it contains no elements.
 * The stack can grow to fit new elements as required.
 * Attempting to pop() or peek() an empty stack throws an EmptyStackException,
 * and does not result in any modification to the stack.
 * The Stack is implemented using an array data structure (a regular Java array),
 * and does not use any of the Java Collection classes other than
 * java.util.EmptyStackException.
 */
public class Q4ArrayStack<T> {
    /**
     * 栈顶指针,-1代表空栈
     */
    private int top = -1;
    /**
     * 容量大小默认为2
     */
    private final int initialCapacity = 2;
    /**
     * 存放元素的数组
     */
    private T[] array;
    private int size;

    public Q4ArrayStack(int capacity) {
        array = (T[]) new Object[capacity];
    }
    public Q4ArrayStack() {
        array = (T[]) new Object[this.initialCapacity];
    }

    public int size() {
        return size;
    }

    /**
     * 扩容的方法
     * @param capacity
     */
    public void expandCapacity(int capacity) {
        //如果需要拓展的容量比现在数组的容量还小,则无需扩容
        if (capacity < size) {
            return;
        }
        T[] old = array;
        array = (T[]) new Object[capacity];
        //复制元素
        if (size >= 0) {
            System.arraycopy(old, 0, array, 0, size);
        }
    }

    /**
     * @return true if the stack is empty
     */
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * Add the given value to this stack,
     * placing it at the top of the stack.
     *
     * @param value the value to add to this stack
     */
    public void push(T value) {
        //判断容量是否充足
        if (array.length == size) {
            double GROWTH_RATE = 1.5;
            expandCapacity((int) (size * GROWTH_RATE));//扩容
        }
        if (array.length == 0) {
            array = (T[]) new Object[this.initialCapacity];
        }
        //从栈顶添加元素
        top = top + 1;
        array[top] = value;
        size++;
    }

    /**
     * Remove the value that is at the top of this stack, and return it.
     *
     * @return the value that was popped from the stack
     * @throws EmptyStackException if the stack is currently empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--;
        T data = array[top];
        top = top - 1;
//        T[] temp = (T[]) new Object[size];
//        if (size >= 0) {
//            System.arraycopy(array, 0, temp, 0, size);
//        }
//        this.array = temp;
        return data;
    }

    /**
     * Get the value that is currently at the top of this stack,
     * but do not remove it from the stack.
     *
     * @return the value at the top of the stack
     * @throws EmptyStackException if the stack is currently empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    /**
     * Check whether a given value is contained in this stack.
     * Specifically, returns true if value is not null and
     * an element e is contained in the stack such that e.equals(value).
     *
     * @param value the value to search for
     * @return true if the value is contained in this stack
     */
    public boolean contains(T value) {
        if (value == null || this.isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create a String representation of this stack.
     * Elements on the stack are listed in order from top to bottom,
     * separated by commas (without spaces).
     * If the stack is empty, an empty string is returned.
     * For example, a stack containing the elements (from top to bottom)
     * "a", "b", and "c" would be represented as "a,b,c".
     *
     * @return a String representation of this stack
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "";
        } else {
            StringBuilder result = new StringBuilder ();
            for (int i = this.size - 1; i >= 0; i--) {
                result.append(this.array[i]).append(" ");
            }
            return result.deleteCharAt(result.length() - 1).toString();
        }
    }
}
