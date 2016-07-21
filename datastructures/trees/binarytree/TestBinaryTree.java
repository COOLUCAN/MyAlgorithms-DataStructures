package GitHub.trunk.datastructures.trees.binarytree;

import java.util.Random;

/**
 * Created by chch0316 on 7/21/2016.
 */
public class TestBinaryTree {

    public static Node prepareBT(int size){
        Random random = new Random(100);
        Node root= new Node(random.nextInt(10000)+1);
        Node current = root;
        for (int i = 0; i <size-1 ; i++) {
            int s= random.nextInt(10000)+1;
            Node x= new Node(s);
            while (true){
             if(Math.random()<0.5)
                if(current.left==null) {
                    current.left = x;
                    break;
                }
                else
                    current=current.left;
             else
                 if(current.right==null) {
                     current.right = x;
                     break;
                 }
                else
                     current=current.right;
            }
            current = root;
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = prepareBT(10);
        TreePrinter.print(root);
        System.out.println("In-Order recursion: ");
        TreeTraversal.inOrderRecursion(root);
        System.out.println();
        System.out.println("In-Order Iterative :");
        TreeTraversal.inOrderIterative(root);

        System.out.println();
        System.out.println("Post-Order recursion: ");
        TreeTraversal.postOrderRecursion(root);
        System.out.println();
        System.out.println("Post-Order Iterative :");
        TreeTraversal.postOrderIterative(root);

    }
}
