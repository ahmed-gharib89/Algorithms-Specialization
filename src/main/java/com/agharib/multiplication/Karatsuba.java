package com.agharib.multiplication;

public class Karatsuba {
    public int multiplication(int a, int b) {
        if (a < 10 || b < 10) {
            return a * b;
        }
        int n = Math.max(String.valueOf(a).length(), String.valueOf(b).length());
        int m = n / 2;
        int a1 = a / (int) Math.pow(10, m);
        int a2 = a % (int) Math.pow(10, m);
        int b1 = b / (int) Math.pow(10, m);
        int b2 = b % (int) Math.pow(10, m);
        int z0 = multiplication(a1, b1);
        int z1 = multiplication(a2 + a1, b2 + b1);
        int z2 = multiplication(a2, b2);
        return z0 * (int) Math.pow(10, 2 * m) + (z1 - z0 - z2) * (int) Math.pow(10, m) + z2;
    }

    public static void main(String[] args) {
        int[] testXs = {1234, 5678, 9101112, 2030, 1, 0, 22, 13, 50, 99, 1456, 957632, 987654321};
        int[] testYs = {5678, 20, 2563, 600, 5, 25630, 3256, 22, 10, 567, 987654321, 987654321, 987654321};
        Karatsuba k = new Karatsuba();
        for (int i = 0; i < testXs.length; i++) {
            int expected = testXs[i] * testYs[i];
            int actual = k.multiplication(testXs[i], testYs[i]);
            System.out.println("Expected: " + expected + " Actual: " + actual + " " + (expected == actual ? "OK" : "FAIL"));
        }
    }
}
