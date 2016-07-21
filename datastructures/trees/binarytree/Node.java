package GitHub.trunk.datastructures.trees.binarytree;

/**
 * Created by chch0316 on 7/21/2016.
 */
public class Node{
    Object data;
    Node left,right;
    Node(){};
    Node(Object data){
        this.data=data;
        left=right=null;
    }
}
