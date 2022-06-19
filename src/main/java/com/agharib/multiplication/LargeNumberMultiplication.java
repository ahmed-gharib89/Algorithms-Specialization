package com.agharib.multiplication;

import java.math.BigInteger;

public class LargeNumberMultiplication {
    public BigInteger multiplication(BigInteger a, BigInteger b) {
        if (a.compareTo(new BigInteger("10")) < 0 || b.compareTo(new BigInteger("10")) < 0) {
            return a.multiply(b);
        }
        int n = Math.max(String.valueOf(a).length(), String.valueOf(b).length());
        int m = n / 2;
        BigInteger a1 = a.divide(new BigInteger("10").pow(m));
        BigInteger a2 = a.mod(new BigInteger("10").pow(m));
        BigInteger b1 = b.divide(new BigInteger("10").pow(m));
        BigInteger b2 = b.mod(new BigInteger("10").pow(m));
        BigInteger z0 = multiplication(a1, b1);
        BigInteger z1 = multiplication(a2.add(a1), b2.add(b1));
        BigInteger z2 = multiplication(a2, b2);
        BigInteger z3 = z1.subtract(z0).subtract(z2);
        return z0.multiply(new BigInteger("10").pow(2*m)).add(z3.multiply(new BigInteger("10").pow(m))).add(z2);
    }

    public static void main(String[] args) {
        int[] testXs = {1234, 5678, 9101112, 2030, 1, 0, 22, 13, 50, 99, 1456, 957632, 987654321};
        int[] testYs = {5678, 20, 2563, 600, 5, 25630, 3256, 22, 10, 567, 987654321, 987654321, 987654321};
        LargeNumberMultiplication lnm = new LargeNumberMultiplication();
        for (int i = 0; i < testXs.length; i++) {
            BigInteger x = new BigInteger(String.valueOf(testXs[i]));
            BigInteger y = new BigInteger(String.valueOf(testYs[i]));
            BigInteger expected = x.multiply(y);
            BigInteger actual = lnm.multiplication(x, y);
            System.out.println("Expected: " + expected + " Actual: " + actual + " " + (expected.equals(actual) ? "OK" : "FAIL"));
        }
        System.out.println("\n\n");
        BigInteger x = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger y = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
        System.out.println(lnm.multiplication(x, y));
    }
}
