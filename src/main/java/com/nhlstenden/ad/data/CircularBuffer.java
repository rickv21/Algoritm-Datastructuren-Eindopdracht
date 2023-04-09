package com.nhlstenden.ad.data;

import java.util.NoSuchElementException;

public class CircularBuffer<T extends Comparable<T>> implements CustomCollection<T> {
    private T[] buffer;
    private int head;
    private int tail;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public CircularBuffer(int capacity) {
        this.buffer = (T[]) new Comparable[capacity];
        this.head = 0;
        this.tail = -1;
        this.capacity = capacity;
        this.size = 0;
    }

    /**
     * Adds the given element to the buffer.
     * If the buffer is full, then the oldest element is replaced.
     *
     * @param element The element to be added.
     */
    public boolean add(T element) {
        // Calculating the index to add the element
        int index = (tail + 1) % capacity;

        // Checking if the array is full
        if (size == capacity) {
            // Replacing the oldest value in the buffer with the new element
            buffer[head] = element;
            // Moving the head pointer to the next oldest value
            head = (head + 1) % capacity;
        } else {
            // Size of the array increases as elements are added
            size++;
            // Storing the element in the array
            buffer[index] = element;
        }

        // Incrementing the tail pointer to point
        // to the element added currently
        tail = index;
        return true;
    }

    /**
     * Remove the last item from the buffer.
     *
     * @return The item that was removed.
     */
    public T removeLast() {
        if (capacity == 0) {
            throw new NoSuchElementException("The buffer is empty!");
        }
        T item = (T) buffer[tail];
        tail = (tail + 1) % capacity;
        capacity--;
        return item;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < capacity; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(buffer[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.buffer = (T[]) new Comparable[capacity];
        this.head = 0;
        this.tail = -1;
        this.size = 0;
    }

    @Override
    public T[] getArray() {
        return buffer;
    }

    @Override
    public void setArray(Comparable<T>[] arr) {
        if (capacity >= 0) System.arraycopy(arr, 0, buffer, 0, capacity);
    }

    @Override
    public String[] getStringArray() {
        String[] stringArray = new String[size];
        for(int i = 0; i < size; i++){
            stringArray[i] = buffer[i].toString();
        }
        return stringArray;
    }
}