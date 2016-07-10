package com.algorithmica.problems.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ADMIN-COOLUCAN on 7/8/2016.
 */
class HeapNode{
    int fileIndex;
    int data;
    HeapNode(){};
    HeapNode(int data,int fileIndex){
        this.data= data;
        this.fileIndex=fileIndex;
    }
    HeapNode(int data){
        this.data = data;
    }
}
class MinHeap {
    HeapNode [] heapArray;
    int size;

    MinHeap(int capacity){
        heapArray = new HeapNode[capacity];
        size=0;
    }
    public void add(HeapNode hNode) {
        if (size == 0) {
            heapArray[size++] = hNode;
            return;
        }
        heapArray[size++]= hNode;
        int current = size - 1;
        while(current >=1) {
            int parent = (current - 1) / 2;
            boolean compare = compare(heapArray[current], heapArray[parent]);
            if (compare) {
                HeapNode tmp = heapArray[current];
                heapArray[current] = heapArray[parent];
                heapArray[parent] = tmp;
                current = parent;
            } else
                break;
        }
    }

    public HeapNode getMin(){
        return heapArray[0];
    }

    public void replaceMin(HeapNode newNode){
       heapArray[0]= newNode;
       int current =0;
        while(current < size) {
            int left = 2 * current + 1;
            int right = 2 * current + 2;
            int small = current;
            if (left < size && compare(heapArray[left], heapArray[current]))
                small = left;
            if (right < size && compare(heapArray[right], heapArray[small]))
                small = right;
            if (small != current) {
                HeapNode tmp = heapArray[current];
                heapArray[current] = heapArray[small];
                heapArray[small] = tmp;
                current = small;
            } else break;
        }
    }

    public  boolean compare(HeapNode x,HeapNode y){
        return (x.data < y.data);
    }

    public void display(){
        for(int i=0;i< heapArray.length;i++)
            System.out.print(heapArray[i].data+"\t");
        System.out.println();
    }
}
public class ExternalMergeSort {

    public static void main(String[] args) throws Exception {
        String inputfile= args[0];
        String outFile = args[1];

        generateTestData(inputfile,200);
        int availRAM = 30;
        sortInputFile(inputfile,outFile,availRAM);

        /*MinHeap hp = new MinHeap(10);
        Random r= new Random(23);
        for (int i = 0; i < 10; i++) {
           HeapNode x = new HeapNode(r.nextInt(100)+1);
           hp.add(x);
        }
        hp.display();
        System.out.println(hp.getMin().data);
        HeapNode x = new HeapNode(200);
        hp.replaceMin(x);
        hp.display();
        x = new HeapNode(10);
        hp.replaceMin(x);
        hp.display();*/
    }

    public static void sortInputFile(String file,String outFile,int availRAM) throws Exception{
        ArrayList<String> chunks= createRuns(file,availRAM);
        System.out.println("Available RAM : "+availRAM);
        System.out.println("No of chunks: "+ chunks.size());
        int chunkSize = findChunkSize(chunks,availRAM);
        System.out.println("Each chunk size : "+chunkSize);
        mergeFiles(outFile,chunks,chunkSize);
    }

    public static void mergeFiles(String outFile, ArrayList<String> chunks,int chunkSize) throws Exception{
        BufferedReader [] files = new BufferedReader[chunks.size()];
        for (int i = 0; i < chunks.size() ; i++) {
            files[i]= new BufferedReader(new FileReader(chunks.get(i)));
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));
        int heapSize = chunks.size();

        MinHeap heap = new MinHeap(heapSize);

        //filling heap
        String line;
        for(int i=0;i<heapSize;i++){
            if((line=files[i].readLine())!=null){
                int s = Integer.parseInt(line);
                HeapNode node = new HeapNode(s,i);
                heap.add(node);
            }
            else{
                HeapNode s= getApproriateNode(files);
                heap.add(s);
            }
        }
        heap.display();
        while (true){
            HeapNode root = heap.getMin();
            if(root.data==Integer.MAX_VALUE)
                break;
            bw.write(String.valueOf(root.data));
            bw.newLine();

            int fileIndex= root.fileIndex;

            if((line=files[fileIndex].readLine())!=null){
                int s = Integer.parseInt(line);
                HeapNode node = new HeapNode(s,fileIndex);
                heap.replaceMin(node);
            }else{
                HeapNode s= getApproriateNode(files);
                heap.replaceMin(s);
            }
        }
        //closing all chunks
        for (int i = 0; i <files.length  ; i++) {
            files[i].close();
        }
        //closing output file
        bw.close();
    }

    public static HeapNode getApproriateNode ( BufferedReader [] files) throws Exception{

        int filesSize = files.length;
        String line;
        for (int i = 0; i < filesSize ; i++) {
            if((line=files[i].readLine())!=null){
                return new HeapNode(Integer.parseInt(line),i);
            }
        }
        return new HeapNode(Integer.MAX_VALUE,Integer.MAX_VALUE);
    }

    public static int findChunkSize(ArrayList<String> chunks,int availRAM){
        int chunkSize = chunks.size();
        //Calculating for N files merge at a single time
        int auxSize = availRAM/(chunkSize+1); // Considering for heap
        int finalSize = (availRAM-auxSize-2)/chunkSize;
        return finalSize;
    }


    public static void twoWayPassMerging(ArrayList<String> chunks){

    }

    public static void generateTestData(String inputFile, int noOfRecs) throws Exception{
        BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile));
        Random rand = new Random(75);
        for (int i = 0; i < noOfRecs; i++) {
            int s= rand.nextInt(10000)+1;
            bw.write(String.valueOf(s));
            bw.newLine();
        }
        bw.close();
    }


    public static ArrayList<String> createRuns(String inputFile,int availRAM) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String directory=inputFile.substring(0,inputFile.lastIndexOf("\\"));
        ArrayList<String> chunks = new ArrayList<String>();
        int[] in= new int[availRAM];
        int count=0;
        String line;
        while ((line = br.readLine())!=null){
            in[count++]= Integer.parseInt(line);
            if(count == availRAM){
                count=0;
                doMergeSort(in);
                saveDataInChunk(in,directory,chunks);
            }
        }
        if(count!=0){
            int [] rem = new int[count];
            for (int i = 0; i < count ; i++) {
                rem[i]= in[i];
            }
            doMergeSort(rem);
            saveDataInChunk(rem,directory,chunks);
        }
        br.close();
        return chunks;
    }

    public static void saveDataInChunk(int[] in,String path,ArrayList<String> chunks) throws Exception{
        path += "\\chunk_"+(chunks.size())+".txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        for (int i = 0; i < in.length; i++) {
            bw.write(String.valueOf(in[i]));
            bw.newLine();
        }
        bw.close();
        chunks.add(path);
    }

    public static void doMergeSort(int[] input){
        int size = input.length;
        if(size < 2)
            return;
        int mid= size/2;
        int[] left = new int[mid];
        int[] right = new int[size-mid];
        int i=0;
        while (i < mid){
            left[i]= input[i];
            i++;
        }
        while (i<size){
            right[i-mid]= input[i];
            i++;
        }
        doMergeSort(left);
        doMergeSort(right);
        merge(left,right,input);
    }

    public static void merge(int[] left,int[] right,int [] input){
        int i=0,j=0,k=0;
        while (i<left.length&&j< right.length){
            if(left[i]<right[j])
                input[k++] = left[i++];
            else
                input[k++]= right[j++];
        }
        while (i<left.length)
            input[k++]= left[i++];

        while (j<right.length)
            input[k++]= right[j++];
    }

}


