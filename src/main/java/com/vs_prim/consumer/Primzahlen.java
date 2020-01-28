package com.vs_prim.consumer;

import java.util.ArrayList;
import java.util.List;

public class Primzahlen {

    List<Integer> list;
    String prim_string;

    public Primzahlen(List<Integer> list, String prim_string) {
        this.list = list;
        this.prim_string = prim_string;
    }

    /**
     * Method to check if Number nr is prime
     *
     * @param nr
     * @return boolean
     */
    public static boolean checkPrime(int nr) {

        //check for one
        if (nr <= 1) {
            return false;
        }

        // Check if any number from 2 to n-1 can divide nr
        for (int i = 2; i < nr; i++) {
            if (nr % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to compute all prime numbers until value and return as int-array
     *
     * @param value
     * @return int[] arr
     */
    static List<Integer> compute_Prime(int value) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 2; i<value; i++) {
            if (checkPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public String toString() {
        return "Liste: "+this.list.toString()+"\nString: "+this.prim_string;
    }
}
