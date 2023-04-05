package com.nhlstenden.ad;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class CircularBuffer<T extends Comparable<T>> {
    private final T[] buffer;
    private int head;
    private int tail;
    private int capacity;
    private int size;
    private Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public CircularBuffer(int capacity, Comparator<T> comparator) {
        this.buffer = (T[]) new Comparable[capacity];
        this.head = 0;
        this.tail = -1;
        this.capacity = capacity;
        this.size = 0;
        this.comparator = comparator;
    }

    public void add(T element) {
        // Calculating the index to add the element
        int index = (tail + 1) % capacity;

        // Size of the array increases as elements are added
        size++;

        // Checking if the array is full
        if (size == capacity) {
           // throw new Exception("Buffer Overflow");
            return;
        }

        // Storing the element in the array
        buffer[index] = element;

        // Incrementing the tail pointer to point
        // to the element added currently
        tail++;
    }

    public void bubbleSort(Function<T, Comparable> keyExtractor) {
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int i = head; i < tail - 1 || (tail == head && i == tail - 1); i++) {
                T current = buffer[i];
                T next = buffer[(i + 1) % capacity];

                keyExtractor.apply(current);
                keyExtractor.apply(next);

                if (comparator.compare(current, next) > 0) {
                    buffer[i] = next;
                    buffer[(i + 1) % capacity] = current;
                    swapped = true;
                }
            }

            tail = (tail - 1 + capacity) % capacity;

            if (!swapped) {
                break;
            }

            swapped = false;

            for (int i = tail; i > head || (tail == head && i == head); i--) {
                T current = buffer[i];
                T prev = buffer[(i - 1 + capacity) % capacity];

                keyExtractor.apply(current);
                keyExtractor.apply(prev);

                if (comparator.compare(current, prev) < 0) {
                    buffer[i] = prev;
                    buffer[(i - 1 + capacity) % capacity] = current;
                    swapped = true;
                }
            }

            head = (head + 1) % capacity;
        }
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
}