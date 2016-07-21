package GitHub.trunk.datastructures.trees.binarytree;

import java.util.Stack;

/**
 * Created by chch0316 on 7/21/2016.
 */

public class TreeTraversal {

    //1. In-Order Traversal (left-node-right)
    public static void inOrderRecursion(Node node){
        if(node == null)
            return;
        inOrderRecursion(node.left);
        System.out.print(node.data+"\t");
        inOrderRecursion(node.right);
    }
    public static void inOrderIterative(Node node){
        if(node == null)
            return;
        Stack<Node> st = new Stack<Node>();
        Node current = node;
        while(true){
            if(current!=null){
                st.push(current);
                current=current.left;
            }
            else{
                if(st.isEmpty())
                    break;
                Node x = st.pop();
                System.out.print(x.data+"\t");
                current = x.right;
            }
        }
    }

    //2. Pre-Order Traversal ( node -left -right)
    public static void preOrderRecursion(Node node){
        if(node == null)
            return;
        System.out.print(node.data+"\t");
        inOrderRecursion(node.left);
        inOrderRecursion(node.right);
    }
    public static void preOrderIterative(Node node){
        if(node == null)
            return;
        Stack<Node> st = new Stack<Node>();
        Node current = node;
        st.push(current);
        while (!st.isEmpty()){
            current = st.pop();
            System.out.print(current.data+"\t");
            if(current.right!=null)
                st.push(current.right);
            if(current.left!=null)
                st.push(current.left);
        }
    }

    //3. Post-Order Traversal (left -right-node)
    public static void postOrderRecursion(Node node){
        if(node == null)
            return;
        postOrderRecursion(node.left);
        postOrderRecursion(node.right);
        System.out.print(node.data+"\t");
    }
    public static void postOrderIterative(Node node){
        if(node == null)
            return;
        Stack<Node> st1 = new Stack<Node>();
        Stack<Node> st2 = new Stack<Node>();
        st1.push(node);
        Node current = node;
        while (!st1.isEmpty()){
            current=st1.pop();
            st2.push(current);
            if(current.left!=null)
                st1.push(current.left);
            if(current.right!=null)
                st1.push(current.right);
        }

        while (!st2.isEmpty()){
            System.out.print(st2.pop().data+"\t");
        }
    }


}
