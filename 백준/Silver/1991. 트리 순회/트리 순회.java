import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static int N;
    static Tree tree = new Tree();
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String data = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            tree.createNode(data, left, right);
        }

        tree.preOrder(tree.root);
        System.out.println();
        
        tree.inOrder(tree.root);
        System.out.println();
        
        tree.postOrder(tree.root);
    }
}

class Node {
    String data;
    Node left;
    Node right;

    public Node(String data) {
        this.data = data;
    }
}

class Tree {
    static Node root;
    
    public void createNode(String data, String left, String right) {
        if(root == null) {
            root = new Node(data);
            root.left = (left.equals(".") ? null : new Node(left));
            root.right = (right.equals(".") ? null : new Node(right));
        } 
        else findNode(root, data, left, right);
    }

    public void findNode(Node node, String data, String left, String right) {
        if(node == null) return;
        if(node.data.equals(data)) { 
            node.left = (left.equals(".") ? null : new Node(left));
            node.right = (right.equals(".") ? null : new Node(right));
        } else {
            findNode(node.left, data, left, right);
            findNode(node.right, data, left, right);
        }
    }

    public void preOrder(Node node) {
        if(node == null) return;
        
        System.out.print(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(Node node) {
        if(node == null) return;
        
        inOrder(node.left);
        System.out.print(node.data);
        inOrder(node.right);
    }

    public void postOrder(Node node) {
        if(node == null) return;
        
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data);
    }
}