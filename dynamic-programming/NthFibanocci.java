package com.algorithmica.dynamicprogramming;

/**
 * Created by chch0316 on 6/27/2016.
 */
public class NthFibanocci {

    //1. Using recursion
    //TC-- O(2^n)  SC-- O(n) (max no of stack frames used)
    public static long nthFibanocci1(long n){
        if(n <1)
            return 0;
        if(n<=2)
            return 1;
        return nthFibanocci1(n-1)+nthFibanocci1(n-2);
    }

    //2. Using Memoization(recursion+memory)
    // Top-down approach i.e. core problem divided into sub problems
    //TC-- O(n) and SC-- O(n)
    public static long nthFibanocci2(long n){
        long[] mem = new long[(int)n+1];
        for (int i = 0; i < (n+1); i++) {
            mem[i]=-1;
        }
        return auxnthFibannoci2(n,mem);
    }
    private static long auxnthFibannoci2(long n,long [] mem){
        int no= (int) n;
        if(n <1)
            return 0;
        if(n<=2)
            return 1;
       if(mem[no]!=-1)
           return mem[no];
        else
           mem[no] = auxnthFibannoci2(n-1,mem)+auxnthFibannoci2(n-2,mem);
        return mem[no];
    }

    //3. using Dynamic programming (non-recursion+memory)
    // Bottom-top approach i.e. from sub problem to main problem
    // Guaranteed that no repetition of solvig sub problems
    //TC-- O(n) and SC-- O(n)
    public static long nthFibanocci3(long n){
        long [] mem = new long[(int)n+1];
        mem[0]=0;
        mem[1]=1;
        for(int i=2;i<(n+1);++i){
            mem[i] = mem[i-1]+mem[i-2];
        }
        return mem[(int)n];
    }

    //4.Using variables
    //TC-- O(n) and SC-- O(1) constant time
    public static long nthFibanocci4(long n){
        long a=1;
        long b =1;
        long sum=1;

        if(n==1||n==2)
            return 1;
        for (int i = 3; i <= n ; i++) {
            sum = a+b;
            b=a;
            a=sum;
        }
        return sum;
    }

    //5. Using matrix  M={{1,1},{1,0}}
    // Fn+1 = M^n = {{Fn+1, Fn},{Fn,Fn-1}}
	// TC -- >O(log n) sc--> O(n)
    public static long nthFibanocci5(long n){
        long f[][] = {{1,1},{1,0}};
        int pow = ((int)n -1);
        power(f,pow);
        return f[0][0];
    }
    private static void power(long[][] f,int pow){
        if(pow==1)
            return;
        long m[][]={{1,1},{1,0}};
        power(f,pow/2);
        multiply(f,f);
        if(pow%2!=0)
            multiply(f,m);
    }

    public static void multiply(long[][] f,long m[][]){
        long x= f[0][0]*m[0][0]+f[0][1]*m[1][0];
        long y= f[0][0]*m[0][1]+f[0][1]*m[1][1];
        long z= f[1][0]*m[0][0]+f[1][1]*m[1][0];
        long w= f[1][0]*m[0][1]+f[1][1]*m[1][1];

        f[0][0] = x;
        f[0][1]=y;
        f[1][0] = z;
        f[1][1] = w;
    }

    public static void main(String[] args) {
        System.out.println(nthFibanocci1(20));
        System.out.println(nthFibanocci2(20));
        System.out.println(nthFibanocci3(100));
        System.out.println(nthFibanocci4(100));
        System.out.println(nthFibanocci5(20));
    }
}
