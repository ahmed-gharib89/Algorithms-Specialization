package com.agharib.sort;

public class MergeSort {
    public int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];
        System.arraycopy(array, 0, left, 0, mid);
        if (array.length - mid >= 0) System.arraycopy(array, mid, right, 0, array.length - mid);
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] unsorted = {8, 6, 7, 5, 3, 0, 9, 2, 1, 4};
        MergeSort ms = new MergeSort();
        int[] sorted = ms.sort(unsorted);
        for (int j : sorted) {
            System.out.print(j + " ");
        }
    }
}
