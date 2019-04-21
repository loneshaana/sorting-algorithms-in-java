package trees;

import java.util.*;

class BinaryTreeNode{
    private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private int depth;

     BinaryTreeNode(int data){
         this.data = data;
         this.left = null;
         this.right = null;
         this.depth = 0;
     }

     int getData() {
        return data;
     }

     void setData(int data) {
        this.data = data;
     }

    BinaryTreeNode getLeft() {
        return left;
    }

    void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    BinaryTreeNode getRight() {
        return right;
    }

    void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    int getDepth() {
        return depth;
    }

    void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

class Stack<T>{
    private List<T> stack = new ArrayList<>();
    void push(T node){
        if(!stack.contains(node))
            stack.add(node);
    }

    T peek(){
        return stack.get(stack.size() -1);
    }

    T pop(){
        return stack.remove(stack.size()-1);
    }

    boolean isEmpty(){
        if(stack.size() >0) return false;
        return  true;
    }
}
class Queue<T>{
        private List<T> list = new ArrayList<>();

        void enqueue(T node){
            list.add(node);
        }

        T dequeue(){
           return list.remove(0);
        }

        boolean isEmpty(){
            if(list.size()  >0) return false;
            return true;
        }
}

public class BinaryTree {
     private BinaryTreeNode root;

     private void insert(int data){
         if(root == null) {
             root = new BinaryTreeNode(data);
         }else{
             Queue<BinaryTreeNode> queue = new Queue<>();
             queue.enqueue(root);

             while (!queue.isEmpty()){
                 BinaryTreeNode cNode = queue.dequeue();

                 if(cNode.getLeft() != null){
                     queue.enqueue(cNode.getLeft());
                 }else {
                     cNode.setLeft(new BinaryTreeNode(data));
                     return;
                 }
                 if(cNode.getRight() != null){
                     queue.enqueue(cNode.getRight());
                 }else {
                     cNode.setRight(new BinaryTreeNode(data));
                     return;
                 }
             }
         }
     }

     private void levelOrderTraversal(){
         if(root == null){
             System.out.println("No elements in the tree");
         }else{
             System.out.println("Level Order Traversal :: ");
             Queue<BinaryTreeNode> queue = new Queue<>();
             queue.enqueue(root);
             while (!queue.isEmpty()){
                 BinaryTreeNode cNode = queue.dequeue();
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

    private void levelOrderTraversal(BinaryTreeNode root){
        if(root == null){
            System.out.println("No elements in the tree");
        }else{
            System.out.println("Level Order Traversal :: ");
            Queue<BinaryTreeNode> queue = new Queue<>();
            queue.enqueue(root);
            while (!queue.isEmpty()){
                BinaryTreeNode cNode = queue.dequeue();
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

     private void inOrderTraversal(){
         System.out.println("\nIn Order Traversal");
         // left // root // right
         if(root == null){
             System.out.println("No Elements In The tree");
         }else{
             Stack<BinaryTreeNode> stack = new Stack<>();
             BinaryTreeNode node = root;
             while (!stack.isEmpty() || node != null){
                 if(node != null){
                     stack.push(node);
                     node = node.getLeft();
                 }else{
                        BinaryTreeNode pop = stack.pop();
                        System.out.print(pop.getData()+" ");
                        node = pop.getRight();
                 }
             }
         }
     }

     private void preOrderTraversal(){
         System.out.println("\nPre Order Traversal");
         if(root == null){
             System.out.println("Tree is empty ...");
         }else{
             Stack<BinaryTreeNode> stack = new Stack<>();
             BinaryTreeNode node = root;
                stack.push(node);
             while (!stack.isEmpty()){
                 node = stack.pop();
                 System.out.print(node.getData()+" ");

                 if(node.getRight() != null){
                     stack.push(node.getRight());
                 }

                 if(node.getLeft() != null){
                     stack.push(node.getLeft());
                 }
             }
         }
     }

     private void  postOrderTraversal(){
         System.out.println("\nPost Order Traversal ");
         if(root == null){
             System.out.println("Tree is Empty");
         }else{
            Stack<BinaryTreeNode> stack = new Stack<>();
            Set<BinaryTreeNode>  visited = new HashSet<>();

            BinaryTreeNode node = root;
            while (node != null || !stack.isEmpty()){
                if(node != null){
                    stack.push(node);
                    node = node.getLeft();
                }else{
                    node = stack.pop();
                    if(node.getRight() != null && !visited.contains(node.getRight())){
                        stack.push(node);
                        node = node.getRight();
                    }
                    else {
                        visited.add(node);
                        System.out.print(node.getData()+" ");
                        node = null;
                    }
                }
            }
         }
     }

    /*
       searching the element using level order traversal
    */
     private int searchElement(int data){
         if(null == root ) return -1;
         if(root.getData() == data) return 1;
         Queue<BinaryTreeNode> queue = new Queue<>();
         queue.enqueue(root);

         while (!queue.isEmpty()){
             BinaryTreeNode node = queue.dequeue();
             if(node.getData() == data) return 1;
             if(node.getLeft() != null) queue.enqueue(node.getLeft());
             if(node.getRight() != null) queue.enqueue(node.getRight());
         }
         return 0;
     }

     private BinaryTreeNode deepestNode() throws Error{
         if(root == null){
             throw new Error("Tree is empty");
//             return  null;
         }
         Queue<BinaryTreeNode> queue = new Queue<>();
         BinaryTreeNode node = root;
         queue.enqueue(root);
         while (!queue.isEmpty()){
             node = queue.dequeue();
             if(node.getLeft() != null) queue.enqueue(node.getLeft());
             if(node.getRight() != null) queue.enqueue(node.getRight());
         }
         return node;
     }

     private void deleteNode(int data){
         if(root == null){
             System.out.println("\nTree is empty...");
         }else{
             System.out.println("\nDelete Node Of Data "+data);
             if(root.getData() == data){
                 //delete the root
                 root = null;// null the root , and others will be collected by GC
             }else{
                 // find the node to delete
                 Queue<BinaryTreeNode> queue = new Queue<>();
                 queue.enqueue(root);
                 while (!queue.isEmpty()){
                     BinaryTreeNode node = queue.dequeue();
                     if(node.getData() == data){
                         BinaryTreeNode deepNode = this.deepestNode();
                         node.setData(deepNode.getData());
                         System.out.println("\nDelete the deepest Node");
                     }else{
                         if(node.getLeft() != null) queue.enqueue(node.getLeft());
                         if(node.getRight() != null) queue.enqueue(node.getRight());
                     }
                 }

             }
         }
     }

     private void reverseLevelOrder(){
         if(root == null) {
             System.out.println("\nTree is empty");
         }else{
             System.out.println("\nReversal Order Traversal");
             Queue<BinaryTreeNode> queue = new Queue<>();
             Stack<BinaryTreeNode> stack = new Stack<>();
             queue.enqueue(root);
             while (!queue.isEmpty()){
                BinaryTreeNode cNode = queue.dequeue();
                if(cNode.getRight() != null) queue.enqueue(cNode.getRight());
                if(cNode.getLeft() != null) queue.enqueue(cNode.getLeft());
                stack.push(cNode);
             }

             while (!stack.isEmpty()){
                 System.out.print(stack.pop().getData()+" ");
             }
         }
     }

     private int maxDepth(){
         if(root == null){
             return 0;
         }

         Queue<BinaryTreeNode> queue = new Queue<>();
         root.setDepth(1);
         queue.enqueue(root);
         int temp = 0;
         int depth =0;

         while (!queue.isEmpty()){
             BinaryTreeNode node = queue.dequeue();
             depth = Math.max(temp ,node.getDepth());
             if(node.getLeft() != null){
                 BinaryTreeNode leftNode = node.getLeft();
                 leftNode.setDepth(depth+1);
                 queue.enqueue(leftNode);
             }

             if(node.getRight() != null){
                 BinaryTreeNode rightNode = node.getRight();
                 rightNode.setDepth(depth+1);
                 queue.enqueue(rightNode);
             }
         }
        return depth;
     }

     private void findLevelWithMaximumSum() throws Error{
         if(root == null) throw new Error("Tree Is Empty");

         Queue<BinaryTreeNode> queue = new Queue<>();
         queue.enqueue(root);
         queue.enqueue(null);
         int maxSum = 0 ;
         int levelSum = 0;
         int level = 0;
         BinaryTreeNode node = root;
         while (!queue.isEmpty()){
             node = queue.dequeue();
             if(node == null){
                 // end of level
                 if(levelSum > maxSum){
                     maxSum = levelSum;
                 }
                 if(!queue.isEmpty()){
                     queue.enqueue(null);
                     level +=1;
                 }
                 levelSum = 0;
             }else{
                 if(node.getLeft() != null) queue.enqueue(node.getLeft());
                 if(node.getRight() != null) queue.enqueue(node.getRight());
                 levelSum += node.getData();

             }
         }
         System.out.println("\nMaximum sum of level in tree is "+maxSum+" :: Number of levels :: "+level);
     }

     private BinaryTreeNode mirrorOfBinaryTree(BinaryTreeNode root){
         if(root != null){
             mirrorOfBinaryTree(root.getLeft());
             mirrorOfBinaryTree(root.getRight());
             BinaryTreeNode temp  = root.getRight();
             root.setRight(root.getLeft());
             root.setLeft(temp);
         }
         return root;
     }

     private boolean areMirros(BinaryTreeNode tree1,BinaryTreeNode tree2){
         if(tree1 == null && tree2 == null){
             return true;
         }
         if(tree1.getData() != tree2.getData()) return false;

         if(tree1 == null || tree2 == null) return false;
         return areMirros(tree1.getLeft(),tree2.getRight()) && areMirros(tree1.getRight(),tree2.getLeft());
     }

     private BinaryTreeNode findLCA(BinaryTreeNode root,int alpha,int beta){
        if(null == root) return root;
        if(root.getData() == alpha || root.getData() == beta) return root;
         BinaryTreeNode left = findLCA(root.getLeft(),alpha,beta);
         BinaryTreeNode right = findLCA(root.getRight(),alpha,beta);
         if(left != null && right != null) return root;
         if(left == null) return  right;
         else return  left;
     }


    /**
        Construct binary tree from inorder and preorder traversals
     */

    private static int preIndex = 0;

    /*
    * Finding Ancestors Recursively
    * */
    private boolean printAllAncestors(BinaryTreeNode root,int data){
//        if(root == null) return  false;
//        if(root.getLeft().getData() == data || root.getRight().getData() == data || printAllAncestors(root.getLeft(),data) || printAllAncestors(root.getRight(),data)){
//            System.out.print(root.getData()+" ");
//            return true;
//        }
        return false;
    }

    private Stack<BinaryTreeNode> printAllAncestors(int data) throws  Error{
        if(root == null) throw new Error("No Data in the tree");
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        BinaryTreeNode node = root;
        Set<BinaryTreeNode> processed = new HashSet<>();

        while (!stack.isEmpty() || node != null){
            if(node != null){
                if(node.getData() == data) {
                    break;
                }
                stack.push(node);
                processed.add(node);
                node = node.getLeft();
            }else{
                BinaryTreeNode peek = stack.peek();
                if(peek.getLeft() != null && peek.getRight() != null){
                    if(processed.contains(peek.getLeft()) && processed.contains(peek.getRight())){
                        peek = stack.pop();
                    }
                }else if(peek.getRight() != null && processed.contains(peek.getRight())){
                    peek = stack.pop();
                }
                else if(peek.getLeft() != null && processed.contains(peek.getLeft())){
                    peek = stack.pop();
                }else{
                    peek  =stack.pop();
                }
                node = peek.getRight();
            }
        }
        return stack;
    }

    private BinaryTreeNode constructBinaryTree(List inOrder,List preOrder,int inStart,int inEnd){

        // inorder 7 3 6 1 5 2 4 8
        // preorder 1 3 7 6 2 5 4 8 -> First Element in the preorder is the root -> find the root element in the inorder

        // left side of the root in inorder creates the left subtree and so on right or root creates right sub tree

        // so root is 1

        if(inStart > inEnd) return  null;

        BinaryTreeNode node = new BinaryTreeNode( (Integer) preOrder.get(preIndex++));

        if(inStart == inEnd) return node;
        int rootPos = inOrder.indexOf(node.getData());

        node.setLeft(constructBinaryTree(inOrder,preOrder,inStart,rootPos-1));
        // construct left subtree start from 0 to rootPos -1

        node.setRight(constructBinaryTree(inOrder,preOrder,rootPos+1,inEnd));
        // construct Right subtree start from rootPos to end

        return node;

    }

    public static void main(String[] args) {

        List<Integer> inOrder = new ArrayList<>(Arrays.asList(7,3,6,1,5,2,4,8));
        List<Integer> preOrder = new ArrayList<>(Arrays.asList(1,3,7,6,2,5,4,8));

         BinaryTree bt = new BinaryTree();
        bt.insert(1);
        bt.insert(2);
        bt.insert(3);
        bt.insert(4);
        bt.insert(5);
        bt.insert(6);
        bt.insert(7);
        bt.insert(8);
//        System.out.println("\nPrint All Ancestors Of 7");
//        bt.printAllAncestors(bt.root,7);
        Stack<BinaryTreeNode> stack = bt.printAllAncestors(8);
        System.out.println("\nPrint All Ancestors Of 8 Non Recursively\n");

        while (!stack.isEmpty()){
            System.out.print(stack.pop().getData()+" ");
        }
        System.out.println();
        BinaryTree tree = new BinaryTree();

        BinaryTreeNode rootNode = bt.constructBinaryTree(inOrder,preOrder,0,inOrder.size()-1);
            bt.levelOrderTraversal(rootNode);
//         bt.root = new BinaryTreeNode(1);
//         bt.root.setLeft(new BinaryTreeNode(2));
//         bt.root.setRight(new BinaryTreeNode(3));
//         bt.root.getLeft().setLeft(new BinaryTreeNode(4));
//         bt.root.getLeft().setRight(new BinaryTreeNode(5));
//         bt.root.getRight().setLeft(new BinaryTreeNode(6));
//         bt.root.getRight().setRight(new BinaryTreeNode(7));
//         bt.root.getLeft().getLeft().setRight(new BinaryTreeNode(8));
        bt.findLevelWithMaximumSum();
        System.out.println("\nFind Lca  of 5 & 6 = "+bt.findLCA(bt.root,5,6).getData());
        System.out.println("\n Are Mirros "+bt.areMirros(bt.root,bt.root));
        bt.mirrorOfBinaryTree(bt.root);
        bt.levelOrderTraversal();
        bt.inOrderTraversal();
        bt.preOrderTraversal();
        bt.postOrderTraversal();
        bt.deleteNode(2);
        bt.reverseLevelOrder();

        System.out.println("\nSearching Element 6 "+bt.searchElement(6));
        System.out.println("Depth of tree "+bt.maxDepth());

    }
}