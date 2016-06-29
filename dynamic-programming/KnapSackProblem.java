package com.algorithmica.dynamicprogramming;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by chch0316 on 6/28/2016.
 */
/* A thief robbing a store and can carry a maximal weight of W into their knapsack.
        There are n items and ith  item weigh wi and is worth vi dollars. What items should thief take?*/

public class KnapSackProblem {

    public static int maxValue = 0;
    public static String maxValuePath="";


    public static void auxMaxValue(int item,int weightLeft,int totalValue,int[] weight,int[] value,String path){

        if(item >= weight.length){
            if(totalValue > maxValue){
                maxValue = totalValue;
                maxValuePath = path;
            }
            System.out.println(path+" : "+totalValue);
            return;
        }
        if (weightLeft-weight[item] < 0){
            auxMaxValue(item+1,weightLeft,totalValue,weight,value,path);
        }else{
        auxMaxValue(item+1,(weightLeft-weight[item]),(totalValue+value[item]),weight,value,(path+item+"-->"));
        auxMaxValue(item+1,weightLeft,totalValue,weight,value,path);
        }
    }

    //Solution1 : Brute-Force
    // Choosing all possible combinations among items (Nc0+Nc1+Nc2+Nc3--= 2^n)
    //TC--> O(2^n)
    public static void findMaxValue1(int[] weight,int[] value,int weightAllowed){
        int weightLeft = weightAllowed;
        int totalValue =0;
        auxMaxValue(0,weightLeft,totalValue,weight,value,"");
    }

    public static void main(String[] args) {
        Integer weightAllowed = Integer.parseInt(args[0]);
        Integer noOfItems = Integer.parseInt(args[1]);

        int[] value= new int[noOfItems];
        int[] weight = new int[noOfItems];

        Random r = new Random(50);
        for (int i = 0; i < noOfItems; i++) {
            weight[i] = r.nextInt(11)+1;
            value[i] = r.nextInt(30)+1;
        }
        System.out.println("Weight Allowed : "+weightAllowed);
        System.out.println("Weights : "+Arrays.toString(weight));
        System.out.println("Values : "+Arrays.toString(value));
        findMaxValue1(weight,value,weightAllowed);

        System.out.println("Max value is : "+maxValue+" and Items collected : "+maxValuePath);
    }
}
