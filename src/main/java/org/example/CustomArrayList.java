package org.example;

import java.util.Collection;
import java.util.Comparator;


public class CustomArrayList<E> {

    private E[] array;
    private int size;

    public CustomArrayList() {
        array = (E[]) new Object[10];
        size = 0;
    }

    private void resize() {
        E[] newArray = (E[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public void add(E e) {
        add(size, e);
    }

    public void add(int index, E element) {
        if (size == array.length) {
            resize();
        }
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = element;
        size++;
    }

    public void addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
    }

    public void clear() {
        array = (E[]) new Object[array.length];
        size = 0;
    }

    public E get(int index) {
        return array[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }

    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) {
                remove(i);
                return;
            }
        }
    }

    /**
     * QuickSort
     */
    public void sort(Comparator<? super E> comparator) {
        quickSort(array, 0, size - 1, comparator);
    }

    private void quickSort(E[] array, int startIndex, int endIndex, Comparator<? super E> comparator) {
        if (startIndex < endIndex) {
            int pivotIndex = partition(array, startIndex, endIndex, comparator);

            quickSort(array, startIndex, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, endIndex, comparator);
        }


    }

    private int partition(E[] array, int startIndex, int endIndex, Comparator<? super E> comparator) {
        E pivot = array[startIndex];
        int nextLargerInsertionIndex = endIndex + 1;
        for (int i = endIndex; i > startIndex; i--) {
            if (comparator.compare(array[i], pivot) > 0) {
                nextLargerInsertionIndex--;
                swap(i, nextLargerInsertionIndex);
            }
        }
        swap(--nextLargerInsertionIndex, startIndex);
        return nextLargerInsertionIndex;
    }

    private void swap(int firstIndex, int lastIndex) {
        E temp = array[firstIndex];
        array[firstIndex] = array[lastIndex];
        array[lastIndex] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(array[i]).append(" ");
        }
        return sb.toString();
    }
}
