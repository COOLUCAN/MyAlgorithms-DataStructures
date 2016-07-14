package GitHub.trunk.programs.dynamicprogramming;

/**
 * Created by chch0316 on 7/13/2016.
 */

/*
    Problem: Find the edit distance between two Strings S1 and S2?

    Edit Distance : Edit Operations--> Insert, Remove, Replace
                    Distance --> Minimum no of operations are required to transform String S1 to Other String S2.
 */
public class EditDistance {

    public static void main(String[] args) {
        String s1= args[0];
        String s2= args[1];
        System.out.println("Strings are : "+s1+"\t"+s2);
        System.out.println("Edit Distance is Memoization: " +findEditDistance2(s1,s2));
        System.out.println("Edit Distance is Recursion : " +findEditDistance1(s1,s2));
        System.out.println("Edit Distance is DP : " +findEditDistance3(s1,s2));
    }

    //Solution1: Brute force ( recursion tree)
    // Start comparing each character from start
    // If matches == goto next char comparision in both Strings
    // if not matches == do all the 3 operations at each char
    // In Worst case,
    //      TC--> Exponential i.e.  O(3^m)  m--> length of S1
    public static int findEditDistance1(String s1, String s2){
        return auxFindDistance1(s1,s2,0,0);
    }
    public static int auxFindDistance1(String s1, String s2, int i,int j){
        if(i==s1.length())
            return s2.length()-j;
        if(j==s2.length())
            return s1.length()-i;
       if(s1.charAt(i)==s2.charAt(j))
           return auxFindDistance1(s1,s2,i+1,j+1);
        else{
           int insert = 1+ auxFindDistance1(s1,s2,i,j+1);
           int replace = 1+ auxFindDistance1(s1,s2,i+1,j+1);
           int remove = 1+ auxFindDistance1(s1,s2,i+1,j);
           int res = Math.min(Math.min(insert,replace),remove);
           return res;
       }
    }

    //Solution2: Memoization ( Recursion+memory) i.e. top-down approach
    //Avoiding solving the same sub problem using memory

    public static int findEditDistance2(String s1, String s2){
        int[][] mem = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                mem[i][j]=-1;
            }
        }
        return auxFindDistance2(s1,s2,0,0,mem);
    }
    public static int auxFindDistance2(String s1, String s2, int i,int j,int[][] mem){
        if(i==s1.length())
            return s2.length()-j;
        if(j==s2.length())
            return s1.length()-i;
        if(mem[i][j]!=-1)
            return mem[i][j];
        if(s1.charAt(i)==s2.charAt(j))
            return mem[i][j]= auxFindDistance2(s1,s2,i+1,j+1,mem);
        else{
            int insert = 1+ auxFindDistance2(s1,s2,i,j+1,mem);
            int replace = 1+ auxFindDistance2(s1,s2,i+1,j+1,mem);
            int remove = 1+ auxFindDistance2(s1,s2,i+1,j,mem);
            int res = Math.min(Math.min(insert,replace),remove);
            return mem[i][j]=res;
        }
    }

    //Solution3: Bottom-top approach i.e. Non-recursion+memory = Dynamic Programming
    public static int findEditDistance3(String s1, String s2){
        int[][] mem = new int[s1.length()+1][s2.length()+1];

        for (int i = 0; i<= s1.length() ;++i) {
            for (int j = 0;j<=s2.length(); ++j) {
                if(i==0)
                    mem[i][j]= j;
                else if(j==0)
                    mem[i][j]=i;
                else if(s1.charAt(i-1)==s2.charAt(j-1))
                    mem[i][j] = mem[i-1][j-1];
                else {
                    mem[i][j]= 1+ Math.min(Math.min(mem[i-1][j],
                                    mem[i-1][j-1]),
                                    mem[i][j-1]);
                }
            }
        }

        for (int i = 0; i <= s1.length() ; i++) {
            for (int j = 0; j <=s2.length() ; j++) {
                System.out.print(mem[i][j]+"\t");
            }
            System.out.println();
        }

        //Printing Edit Operations
       /* String s="";
        int i=s1.length(),j= s2.length();
        while (i>0 && j>0){
            if(s1.charAt(i-1)==s2.charAt(j-1)){
                System.out.println("Replace "+s1.charAt(i-2)+ " with "+s2.charAt(j-2));
                i--;
                j--;
            }
            else if(mem[i-1][j] > mem[i][j-1]){
                j--;
                System.out.println("Adding "+s2.charAt(j-1));
            }
            else{
                i--;
                System.out.println("deleting "+s2.charAt(i-1));
            }
        }*/

        return mem[s1.length()][s2.length()];
    }

}
