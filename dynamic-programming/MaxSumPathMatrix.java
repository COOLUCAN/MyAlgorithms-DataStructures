package com.algorithmica.dynamicprogramming;

/**
 * Created by chch0316 on 6/27/2016.
 */
public class MaxSumPathMatrix {

    public static void main(String[] args) {
        int[][] in = {{1,4,2},{4,3,2},{9,2,1}};

    }

    public static void findPath(int[][] in,int i,int j){

        findPath(in,i,j+1);
        findPath(in,i+1,j);
    }
}
