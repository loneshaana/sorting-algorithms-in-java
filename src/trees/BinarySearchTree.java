package trees;

import Queue.Queue;
import Stack.Stack;

class BSTNode{
    private int data;
    private BSTNode left;
    private BSTNode right;

    BSTNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    int getData() {
        return data;
    }

    void setData(int data) {
        this.data = data;
    }

    BSTNode getLeft() {
        return left;
    }

    void setLeft(BSTNode left) {
        this.left = left;
    }

    BSTNode getRight() {
        return right;
    }

    void setRight(BSTNode right) {
        this.right = right;
    }
}
public class BinarySearchTree {
    // operations
    // Find Minimum // Find Maximum // Insert Element // Delete Element // Find kth element

    // since root is always between the left subtree and right substree
    // performing the inorder will produce the sorted elements

    // minumum will be always on the left of the root

    //recursive approach
    private BSTNode findMin(BSTNode root){
        if(root.getLeft() == null) return root;
        else return findMin(root.getLeft());
    }

    private BSTNode findMax(BSTNode root){
        if(root.getRight() == null) return root;
        else return findMax(root.getRight());
    }

    private BSTNode searchNode(BSTNode root,int data){
        if(root == null) return  null;
        if(root.getData() == data) return root;
        if(data > root.getData()){
            return searchNode(root.getRight(),data);
        }else{
            return searchNode(root.getLeft(), data);
        }
    }

    private void insertNode(BSTNode root,BSTNode insertNode){
        if(root == null){
            root = insertNode;
        }
        else{
            if(insertNode.getData() > root.getData()){
                // insert in right
                if(root.getRight() == null){
                    root.setRight(insertNode);
                }else{
                    insertNode(root.getRight(),insertNode);
                }
            }else{
                if(root.getLeft() == null){
                    root.setLeft(insertNode);
                }else {
                    insertNode(root.getLeft(),insertNode);
                }
            }
        }
    }
    // non Recursive Approach

    private BSTNode findMin_NonRec(BSTNode root){
        if(root == null) return null;
        if(root.getLeft() == null) return root;
        Stack<BSTNode> stack = new Stack<BSTNode>();
        stack.push(root);
        BSTNode node = root;

        while (!stack.isEmpty() && node != null){
            if(node != null){
                stack.push(node);
                node = node.getLeft();
            }else{
                stack.pop();
                break;
            }
        }
        return stack.peek();
    }

    private void deleteNode(BSTNode root,int data){
        deleteRec(root,data);
    }

    private BSTNode deleteRec(BSTNode root,int data){
        if(root == null) return root;
        if(data < root.getData()){
            // delete from left
            return deleteRec(root.getLeft(),data);
        }else if(data > root.getData()){
            // delete from right
            return deleteRec(root.getRight(),data);
        }else{
            // we got the node to delete
            if(root.getLeft() == null){
                // root.getRight();
                return root.getRight();
            }else if(root.getRight() == null){
                // root.getLeft();
                return root.getLeft();
            }

            // else root has both left and right childs
            int min = minValue(root.getRight());

            root.setData(min);
            root.setRight(deleteRec(root.getRight(),root.getData()));
        }
        return root;
    }

    private int minValue(BSTNode root){
        int minV = root.getData();
        while (root.getLeft() != null){
            minV = root.getLeft().getData();
            root = root.getLeft();
        }
        return minV;
    }

    private void levelOrderTraversal(BSTNode root){
        if(root == null){
            System.out.println("No elements in the tree");
        }else{
            System.out.println("Level Order Traversal :: ");
            Queue<BSTNode> queue = new Queue<BSTNode>();
            queue.enqueue(root);
            while (!queue.isEmpty()){
                BSTNode cNode = queue.dequeue();
                System.out.print(cNode.getData()+" "); // deque
                if(cNode.getLeft() != null){
                    queue.enqueue(cNode.getLeft());
                }
                if(cNode.getRight() != null){
                    queue.enqueue(cNode.getRight());
                }
            }
        }
    }

    private BSTNode findLca(BSTNode root,int alpha,int beta,BSTNode lcaNode){
        if(root == null) return lcaNode;
        if((alpha < root.getData() && beta > root.getData()) || (alpha > root.getData() && beta <root.getData())) {
            lcaNode = root;
        }else{
             BSTNode left =  findLca(root.getLeft(),alpha,beta,lcaNode);
             BSTNode right = findLca(root.getRight(),alpha,beta,lcaNode);
             if(left.getData() < right.getData()) return  left;
             return right;
        }
        return lcaNode;
    }

    public static void main(String[] args) {

        /*
         * Constructing the Binary Search tree
         */
        BSTNode root = new BSTNode(10);
        root.setLeft(new BSTNode(6));
        root.setRight(new BSTNode(16));
        root.getLeft().setLeft(new BSTNode(4));
        root.getLeft().setRight(new BSTNode(9));
        root.getRight().setLeft(new BSTNode(13));
        root.getLeft().getRight().setLeft(new BSTNode(7));


        BinarySearchTree bst = new BinarySearchTree();
        System.out.println("\nFinding Lca "+bst.findLca(root,16,6,root).getData());

        BSTNode min = bst.findMin(root);

        System.out.println("Minimum node is :: "+min.getData());
        BSTNode mini = bst.findMin_NonRec(root);

        System.out.println("Non Recursive Minimum node is :: "+mini.getData());
        BSTNode searchNode = bst.searchNode(root,10);

        System.out.println("Searching node 10 Found ::  "+searchNode.getData());

        System.out.println("Finding Maximum Node "+bst.findMax(root).getData());

        bst.insertNode(root,new BSTNode(23));
        System.out.println("Finding Maximum Node "+bst.findMax(root).getData());

        bst.levelOrderTraversal(root);
        bst.deleteNode(root,16);
        bst.levelOrderTraversal(root);
    }

}
