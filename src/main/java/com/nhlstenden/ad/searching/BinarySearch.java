package com.nhlstenden.ad.searching;

import com.nhlstenden.ad.data.CustomCollection;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class BinarySearch<K, T extends Comparable<T>> implements Searcher<K, T> {
    /**
     * Gets the indices of the first and last element of the collection, and calls the binary search function.
     * @param key the key that is searched.
     * @param collection the collection that the binary search is used on.
     * @param keyExtractor the function that extracts a key from an element.
     * @return the results of the binarysearch.
     */
    @Override
    public Set<T> search(K key, CustomCollection<T> collection, Function<T, K> keyExtractor) {
        int firstElement = 0;
        int lastElement = collection.getSize() - 1;
        return binarySearch(key, collection, keyExtractor, firstElement, lastElement);
    }

    /**
     * Performs a binary search on a collection.
     * @param key the key that is searched.
     * @param collection the collection that the binary search is used on.
     * @param keyExtractor the function that extracts a key from an element.
     * @param firstIndex the first index of the collection.
     * @param lastIndex the last index of the collection.
     * @return a set containing the results of the search.
     */
    public Set<T> binarySearch(K key, CustomCollection<T> collection, Function<T, K> keyExtractor, int firstIndex, int lastIndex) {
        // If the lowest index is higher than the highest index, return an empty set.
        if (firstIndex > lastIndex) {
            return Collections.emptySet();
        }
        Set<T> results = new HashSet<>();
        // Get the index of the middle element.
        int middleIndex = (firstIndex + lastIndex) / 2;
        T[] array = collection.getArray();
        Arrays.sort(array);
        // Get the middle element of the array.
        T middleElement = array[middleIndex];
        // Get the key of the middle element.
        K middleKey = keyExtractor.apply(middleElement);
        /* Compare the key of the middle element to the key that is searched.
           If the key of the middle element is equal to the key that needs to be searched, add the ley to the results.
         */
        int compare = ((Comparable<? super K>) middleKey).compareTo(key);
        if (middleKey.equals(key)) {
            results.add(middleElement);
        }
        /* If the key of the middle element is smaller than the key that is searched, perform the binary search with
           the index after the middle index as the first index and the last index as the last index.
         */
        else if (compare < 0) {
            return binarySearch(key, collection, keyExtractor, middleIndex + 1, lastIndex);
        }
        /* If the key of the middle element is greater than the key that is searched, perform the binary search with
           the first index as the first index and the index before the middle index as the last index.
         */
        else {
            return binarySearch(key, collection, keyExtractor, firstIndex, middleIndex - 1);
        }
        // Return the set with the results.
        return results;
    }
}
