import java.io.Serializable;

/**
 * @author Samuele Urbano
 * @serial I288376LYT556127JK382PPL
 * 
 * Dynamic array LIFO queue implementation (Stack).
 * 
 * Implements all stack methods (push(), pop()), utility methods and 
 * permits all objects, including null. This class provides methods to 
 * manipulate the size of the array that is used internally to store the
 * stack.
 * 
 * Each Stack instance has a capacity. The capacity is the size of the array 
 * used to store the elements in the stack. As elements are added to a 
 * Stack, its capacity grows automatically.
 * 
 * @date 2021-07-17
 * @category Collection
 * @version 1.1
 */
public class Stack<E> implements Serializable {

    private static final long serialVersionUID = 983764775765243L;
    
    /**
     * The default Stack capacity.
     */
    public static final int DEFAULT_CAPACITY = 10;

    /**
     * The percent that define the size where increment
     * the Stack capacity.
     */
    public static final int PERCENT_FOR_INCREMENT = 70;

    /**
     * The percent that define the size to add to
     * the Stack capacity.
     */
    public static final int PERCENT_TO_INCREMENT = 50;

    /**
     * The actual Stack size.
     */
    private int size;

    /**
     * The Stack pointer. It points to the next position to fill
     * of the Stack.
     */
    private int pointer;

    /**
     * The new Stack capacity. It grows using the 
     * method grow()
     * 
     * @see this.grow()
     */
    private int newStackCapacity;

    /**
     * The array that stores the elements of the Stack.
     */
    private Object [] elementData;

    /**
     * Constructs the stack with a default capacity (10 elements).
     */
    public Stack() {
        this.elementData = (E[]) new Object [DEFAULT_CAPACITY];
        this.newStackCapacity = DEFAULT_CAPACITY;
        this.init();
    }

    /**
     * Constructs the stack with a specified size.
     * 
     * @param size - The dimension.
     * @throws IllegalArgumentException - If the size < 0.
     */
    public Stack(int size) {
        if (size >= 0 && size <= DEFAULT_CAPACITY) {
            this.elementData = (E[]) new Object [DEFAULT_CAPACITY];
            this.newStackCapacity = DEFAULT_CAPACITY;
            this.init();
        } else if (size > DEFAULT_CAPACITY) {
            this.elementData = (E[]) new Object [size];
            this.newStackCapacity = size;
            this.init();
        } else {
            throw new IllegalArgumentException("Illegal size: " + size);
        }
    }

    /**
     * Inserts the specified element at the top of the Stack.
     * If the pointer value >= 70% of the actual stack capacity, it calls
     * the method to increment the array capacity.
     * 
     * @param e - The element to be stored.
     * @return - true.
     */
    public boolean push(E e) {
        if (this.pointer >= ((PERCENT_FOR_INCREMENT * this.newStackCapacity) / 100)) {
            this.grow();
            this.elementData [this.pointer] = (E)e;
            this.increment();
        } else {
            this.elementData [this.pointer] = (E)e;
            this.increment();
        }
        return true;
    }

    /**
     * Returns and removes the element at the top of the Stack,
     * if the Stack is empty it'll returns null.
     * 
     * @return - The element E e or null.
     */
    public E pop() {
        this.decrement();
        if (this.pointer >= 0) {
            E e = (E)this.elementData [this.pointer];
            this.elementData [this.pointer] = null;
            return e;
        }
        this.init();
        return null;
    }

    /**
     * Clear the Stack, it removes all the elements from the Stack
     * (set all the array positions to null)
     */
    public void clear() {
        for (int i = 0; i < this.newStackCapacity; i++) {
            this.elementData [i] = null;
        }
        this.init();
    }

    /**
     * Returns true if the Stack is empty (has no Objects in it),
     * otherwise false.
     * 
     * @return - true if matches the condition.
     */
    public boolean isEmpty() {
        return this.pointer == 0;
    }

    /**
     * Returns the size of the stack (the number of elements
     * in the stack).
     * 
     * @return - The stack size.
     */
    public int size() {
        return this.size;
    }

    /**
     * Initialize the pointer and the size to 0 value.
     */
    private void init() {
        this.pointer = 0;
        this.size = this.pointer;
    }

    /**
     * Increments the pointer and the size value.
     */
    private void increment() {
        this.pointer++;
        this.size++;
    }

    /**
     * Decrements the pointer and the size value.
     */
    private void decrement() {
        this.pointer--;
        this.size--;
    }

    /**
     * Increments the Stack capacity. The stack grows by 50% compared
     * to the current size.
     */
    private void grow() {
        Object [] elementDataCopy = this.elementData.clone();
        this.newStackCapacity += ((PERCENT_TO_INCREMENT * this.size) / 100);
        this.elementData = new Object [newStackCapacity];

        for (int i = 0; i < elementDataCopy.length; i++) {
            this.elementData [i] = elementDataCopy [i];
        }
    }

    @Override
    public String toString() {
        String toString = "[ ";

        for (E e : this.elementData) {
            if (e != null) {
                toString += String.valueOf(e) + ", ";
            } else {
                break;
            }
        }
        return toString.substring(0, toString.length() - 2) + " ]";
    }
}
