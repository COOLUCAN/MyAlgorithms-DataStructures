package com.algorithmica.problems.combinatorial;

import java.util.Arrays;

/**
 * Created by chch0316 on 7/2/2016.
 */
public class GenerateCharSequence {

    public static void main(String[] args) {
        String input = args[0];
        char[] in = input.toCharArray();
        System.out.println("Input String is : "+input);
        //all combinations
        System.out.println("All possible combinations are : ");
        displayAllCombinations(in);
        //all permutations
        System.out.println("All permutations of a String : ");
        charSeq(in);

    }

    //Displaying all permutations of length N using N given chracters
    //Backtracing = Recursion+ Branch Pruning (using valid/Invald)
    public static void charSeq(char[] in){
        char[] out = new char[in.length];
        auxcharSeq(0,out,in);
    }
    public static void auxcharSeq(int level,char[] out,char[] in){
        if(level+1==in.length+1){
            System.out.println(Arrays.toString(out));
            return;
        }
        for (int i = 0; i <in.length; i++) {
            if(isValid(in[i],out,level))
                out[level]=in[i];
            else
                continue;
            auxcharSeq(level+1,out,in);
        }
    }
    public static boolean isValid(char c,char[] out,int level){
        for (int i = 0; i < level ; i++) {
            if(out[i]==c)
                return false;
        }
        return true;
    }

    //Displaying all possible sequences of length N using N given chracters
    public static void displayAllCombinations(char[] in){
        char[] out = new char[in.length];
        auxdisplay2(0,out,in);
    }
    public static void auxdisplay2(int level,char[] out,char[] in){
        if(level+1==in.length+1){
            System.out.println(Arrays.toString(out));
            return;
        }

        for (int i = 0; i <in.length; i++) {
                out[level]=in[i];
            auxdisplay2(level+1,out,in);
        }
    }


}
