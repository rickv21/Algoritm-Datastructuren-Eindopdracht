package com.nhlstenden.ad;

import java.util.NoSuchElementException;

public class CircularBuffer<T> {
    private final int capacity;
    private final Object[] buffer;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        buffer = new Object[capacity];
    }

    /**
     * Adds the given item to the buffer.
     *
     * @param item The item to bt added to the buffer.
     */
    public void add(T item) {
        if (size == capacity) {
            tail = (tail + 1) % capacity;
        } else {
            size++;
        }
        buffer[head] = item;
        head = (head + 1) % capacity;
    }

    /**
     * Remove the last item from the buffer.
     *
     * @return The item that was removed.
     */
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("The buffer is empty!");
        }
        T item = (T) buffer[tail];
        tail = (tail + 1) % capacity;
        size--;
        return item;
    }

    public int getSize() {
        return size;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
