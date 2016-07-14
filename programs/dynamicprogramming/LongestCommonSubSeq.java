package GitHub.trunk.programs.dynamicprogramming;

/**
 * Created by chch0316 on 7/14/2016.
 */
public class LongestCommonSubSeq {

    public static void main(String[] args) {
       String s1= "ABCDGH";
       String s2= "AEDFHR";

       // String s1= "AGGTAB";
        //String s2= "GXTXAYB";
        System.out.println(longCommonSubSeq1(s1,s2));
        System.out.println(longCommonSubSeq2(s1,s2));
        System.out.println(longCommonSubSeq3(s1,s2));
    }

    //Solution1: BruteForce
    //TC--> O(2^n)
    public static int longCommonSubSeq1(String s1,String s2){
        return auxLongCommonSubSeq1(s1,s2,s1.length()-1,s2.length()-1);
    }
    private static int auxLongCommonSubSeq1(String s1,String s2,int i,int j){
        if(i<0||j<0)
            return 0;
        if(s1.charAt(i)==s2.charAt(j))
            return 1+auxLongCommonSubSeq1(s1,s2,i-1,j-1);
        else
            return Math.max(auxLongCommonSubSeq1(s1,s2,i-1,j),auxLongCommonSubSeq1(s1,s2,i,j-1));
    }

    //Solution2: USing memoization (Top-down approach)
    // TC--reduced using memory and SC--> O(mn)
    public static int longCommonSubSeq2(String s1,String s2){
        int[][] mem= new int[s1.length()+1][s2.length()+1];

        return auxLongCommonSubSeq2(s1,s2,s1.length()-1,s2.length()-1,mem);
    }
    private static int auxLongCommonSubSeq2(String s1,String s2,int i,int j,int[][] mem){
        if(i<0||j<0)
            return 0;
        if(mem[i][j] !=0)
            return mem[i][j];
        if(s1.charAt(i)==s2.charAt(j))
            return mem[i][j]=1+auxLongCommonSubSeq2(s1,s2,i-1,j-1,mem);
        else
            return mem[i][j]=Math.max(auxLongCommonSubSeq2(s1,s2,i-1,j,mem),auxLongCommonSubSeq2(s1,s2,i,j-1,mem));
    }

    //Solution3: USing Dynamic Programming (Bottom to Top)
    //TC--> O(mn) and SC--> O(mn)
    private static int longCommonSubSeq3(String s1,String s2){
        //Adding one additional row and columns for emprt Strings
        int[][] mem = new int[s1.length()+1][s2.length()+1];

        for (int i = 0; i <=s1.length() ; ++i) {
            for (int j = 0; j <=s2.length() ;++j ) {
                if(i==0||j==0)
                    mem[i][j]=0;
                else if(s1.charAt(i-1)==s2.charAt(j-1)){
                    mem[i][j]= 1+mem[i-1][j-1];
                }
                else
                    mem[i][j]= Math.max(mem[i-1][j],mem[i][j-1]);
            }
        }

        //To print common sub sequence
        String s="";
        int i=s1.length(),j=s2.length();
        while (i>0 && j>0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                s = s1.charAt(i - 1) + s;
                i--;
                j--;
            } else if (mem[i - 1][j] < mem[i][j - 1])
                j--;
            else
                i--;
        }
        System.out.println(s);
        return mem[s1.length()][s2.length()];
    }
}
