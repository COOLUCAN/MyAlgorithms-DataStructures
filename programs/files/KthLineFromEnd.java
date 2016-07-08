package GitHub.trunk.programs.files;

import org.apache.commons.io.input.ReversedLinesFileReader;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by chch0316 on 7/6/2016.
 *
 * Problem: Finding Kth Line from end in a file
 */
public class KthLineFromEnd {

    public static void main(String[] args) throws Exception {

        String file = args[0];
        int k= Integer.parseInt(args[1]);

        long start = System.currentTimeMillis();
        String s = getKthLineFromEnd1(file,k);
        long end = System.currentTimeMillis();
        System.out.println("line is : "+s + "time :"+(end-start));


        s = getKthLineFromEnd2(file,k);
        long end2 = System.currentTimeMillis();
        System.out.println("line is : "+s + "time :"+(end2-end));

        s = getKthLineFromEnd3(file,k);
        long end3 = System.currentTimeMillis();
        System.out.println("line is : "+s + "time :"+(end3-end2));
    }

    //Solution1: Two traversals
    //1. Get count from first traversal
    //2. Go to (count-n+1) line
    //TC-->O(N) SC-->O(1)
    public static String getKthLineFromEnd1(String path,int n) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));
        int count=0;
        while (br.readLine()!=null){
            count++;
        }
        br.close();

        br=new BufferedReader(new FileReader(path));
        String line;
        int i=0;
        while ((line=br.readLine())!=null){
            if(++i==(count-n+1)){
                br.close();
                return line;
            }
        }
        br.close();
        return null;
    }

    //Solution2: Datastructure based (1 traversal)
    //Always maintains n size list...adding at last..
    //TC--> O(N) and SC-->O(K)
    public static String getKthLineFromEnd2(String path,int n) throws Exception{
        LinkedList<String> list = new LinkedList<String>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        int count=0;
        String line;
        while ((line=br.readLine())!=null){
            if(list.size()==n){
                list.removeFirst();
            }
           list.addLast(line);
        }
        br.close();
        if(list.size() < n)
            return null;
        else
            return list.get(0);
    }

    //Solution 3: Using a apache library i.e. ReversedLinesFileReader.java (commons-io.jar)
    //we are able to read the file from end
    //TC--> O(K) Sc--> O(1)
    public static String getKthLineFromEnd3(String path,int n) throws Exception{
        ReversedLinesFileReader rlReader = new ReversedLinesFileReader(new File(path));
        String line;
        int count =0;
        while ((line=rlReader.readLine())!=null){
            if(++count==n){
                rlReader.close();
               return line;}
        }
        rlReader.close();
        return null;
    }
}
