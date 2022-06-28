package com.agharib.inversions;

import com.agharib.sort.MergeSort;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class NumInversions {
    private BigInteger numInversions(ArrayList<Integer> arr) {
        BigInteger inversions = BigInteger.ZERO;
        if (arr.size() > 1) {
            ArrayList<Integer> left = new ArrayList<>();
            ArrayList<Integer> right = new ArrayList<>();
            int middle = arr.size() / 2;
            for (int i = 0; i < middle; i++) {
                left.add(arr.get(i));
            }
            for (int i = middle; i < arr.size(); i++) {
                right.add(arr.get(i));
            }
            inversions = inversions.add(numInversions(left));
            inversions = inversions.add(numInversions(right));
            inversions = inversions.add(merge(left, right, arr));
        }
        return inversions;
    }

    private BigInteger merge(ArrayList<Integer> left, ArrayList<Integer> right, ArrayList<Integer> arr) {
        BigInteger inversions = BigInteger.ZERO;
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                arr.set(k, left.get(i));
                i++;
            } else {
                arr.set(k, right.get(j));
                j++;
                inversions = inversions.add(BigInteger.valueOf(left.size() - i));
            }
            k++;
        }
        while (i < left.size()) {
            arr.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < right.size()) {
            arr.set(k, right.get(j));
            j++;
            k++;
        }
        return inversions;
    }


    private ArrayList<Integer> readText(String fName) {
//        String fName = "src/main/resources/IntegerArray.txt";
        File f = new File(fName);
        ArrayList<Integer> array = new ArrayList<>();
        int i = 0;
        try {
            java.util.Scanner s = new java.util.Scanner(f);
            while (s.hasNextInt()) {
                int num = s.nextInt();
                array.add(num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }

    public static void main(String[] args) {
        NumInversions ni = new NumInversions();
        ArrayList<Integer> input = ni.readText("src/main/resources/IntegerArray.txt");
        BigInteger inversions = ni.numInversions(input);
        System.out.println(input.size());
        System.out.println(inversions);

    }
}

