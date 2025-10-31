package binarysearchtree;

import java.util.*;

class Node<T>{
    T key;
    Node<T> left;
    Node<T> right;

    int leftCnt; // for KthSmallest

    public Node(T key) {
        this.key = key;
    }
}

class Pair<T,U>{
    T key;
    U value;

    public Pair(T key, U value) {
        this.key = key;
        this.value = value;
    }
}

public class BSTDSA {

    public static void inorderTraversal(Node root){
        if(root != null){
            inorderTraversal(root.left);
            System.out.print(root.key + " ");
            inorderTraversal(root.right);
        }
    }

    public static boolean searchInBST(Node<Integer> root, int key){
        if(root == null) return false;
        if(root.key == key) return true;
        if(key < root.key) return searchInBST(root.left,key);
        else return searchInBST(root.right,key);
    }

    public static boolean searchInBSTIterative(Node<Integer> root, int key){
        while(root != null){
            if(root.key == key) return true;
            if(key < root.key) root = root.left;
            else root = root.right;
        }
        return false;
    }

    public static void testSearchInBST(){
        Node root = new Node(15);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(20);
        root.right.left = new Node(18);
        root.right.right = new Node(80);
        root.right.left.left = new Node(16);

        System.out.println(searchInBST(root,18));
        System.out.println(searchInBSTIterative(root,18));
    }

    public static Node insertInBST(Node<Integer> root, int key){
        if(root == null){
            root = new Node(key);
            return root;
        }
        if(root.key == key) return root;
        if(root.key < key){
            if(root.right == null){
                root.right = new Node(key);
            }
            else insertInBST(root.right,key);
        }else{
            if(root.left == null){
                root.left = new Node(key);
            }
            else insertInBST(root.left,key);
        }
        return root;
    }

    public static Node insertInBSTIterative(Node<Integer> root, int key){
        if(root == null) return new Node(key);

        Node mainRoot = root;

        while(root.key != key){
            if(root.key < key){
                if(root.right == null) root.right = new Node(key);
                root = root.right;
            }else{
                if(root.left == null) root.left = new Node(key);
                root = root.left;
            }
        }

        return mainRoot;
    }

    public static Node insertInBSTIterativeAuthor(Node<Integer> root, int key){
        Node<Integer> tmp = new Node(key);
        Node<Integer> parent = null, curr = root;

        while(curr != null){
            parent = curr;
            if(key < curr.key) curr = curr.left;
            else if(curr.key < key) curr = curr.right;
            else return root; // key already present in BST
        }

        if(parent == null) return tmp;
        if(key < parent.key) parent.left = tmp;
        else parent.right = tmp;
        return root;
    }

    static Node<Integer> parent = null;
    public static void searchForInsertInBST(Node<Integer> root, int key){
        if(root == null) return;
        parent = root;
        if(root.key == key) return;
        if(root.key < key) searchForInsertInBST(root.right,key);
        else searchForInsertInBST(root.left,key);
    }

    public static Node insertInBST2(Node<Integer> root, int key){
        searchForInsertInBST(root,key);
        if(parent == null) return new Node(key);
        if(parent.key == key) return root;
        if(parent.key < key) parent.right = new Node<>(key);
        else parent.left = new Node<>(key);
        parent = null; // making parent null so it won't affect next call of this method
        return root;
    }

    public static Node insertInBSTAuthor(Node<Integer> root, int key){
        if(root == null) return new Node(key);
        if(root.key < key){
            root.right = insertInBSTAuthor(root.right,key);
        }
        else if(root.key > key){
            root.left = insertInBSTAuthor(root.left,key);
        }
        return root;
    }

    public static void testInsertInBST(){
        Node<Integer> root = null;
        root = insertInBST(root,4);
        inorderTraversal(root);
        System.out.println();
        root = insertInBSTIterative(root,2);
        inorderTraversal(root);
        System.out.println();
        root = insertInBST(root,6);
        inorderTraversal(root);
        System.out.println();
        root = insertInBSTIterative(root,1);
        inorderTraversal(root);
        System.out.println();
        root = insertInBST(root,3);
        inorderTraversal(root);
        System.out.println();
        root = insertInBSTIterative(root,5);
        inorderTraversal(root);
        System.out.println();
        root = insertInBST(root,7);
        inorderTraversal(root);
        System.out.println();
        root = insertInBSTIterative(root,9);
        inorderTraversal(root);
        System.out.println();
        root = insertInBST2(root,8);
        inorderTraversal(root);
        System.out.println();
        root = insertInBST2(root,8); // does nothing because already present in BST
        inorderTraversal(root);
        System.out.println();
        root = insertInBSTAuthor(root,15);
        root = insertInBSTAuthor(root,10);
        root = insertInBSTAuthor(root,12);
        inorderTraversal(root);
        System.out.println();
    }

    public static Node getInorderSuccessorInBST(Node root){
        // Gives InorderSuccessorInBST of root in the SUBTREE with this root
        if(root == null) return null;
        Node successor = root.right;
        while (successor != null && successor.left != null){
            successor = successor.left;
        }
        return successor;
    }

    public static Node deleteInBST(Node<Integer> root, int key){
        if(root == null) return null;
        if(key < root.key){
            root.left = deleteInBST(root.left,key);
        }
        else if(root.key < key){
            root.right = deleteInBST(root.right,key);
        }
        else{
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;
            Node<Integer> successor = getInorderSuccessorInBST(root);
            root.key = successor.key;
            root.right = deleteInBST(root.right, successor.key);
        }
        return root;
    }

    public static void testDeleteInBST(){
        Node root = new Node(50);
        root.left = new Node(30);
        root.left.right = new Node(40);
        root.right = new Node(70);
        root.right.left = new Node(60);
        root.right.right = new Node(80);

        inorderTraversal(root);
        System.out.println();
        Node rootAfterDelete = deleteInBST(root,30);
        inorderTraversal(rootAfterDelete);
        System.out.println();
    }

    public static Node floorInBST(Node<Integer> root, int key){
        Node floor = null;
        while(root != null){
            if(root.key == key) return root;

            if(root.key < key){
                floor = root;
                root = root.right;
            }
            else root = root.left;
        }
        return floor;
    }

    public static Node ceilInBST(Node<Integer> root, int key){
        Node ceil = null;
        while(root != null){
            if(root.key == key) return root;

            if(key < root.key){
                ceil = root;
                root = root.left;
            }
            else root = root.right;
        }
        return ceil;
    }

    public static void testFloorNCeilInBST(){
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.right.left = new Node(12);
        root.right.right = new Node(30);

        Node floor = floorInBST(root,14);
        System.out.println(floor.key);
        Node ceil = ceilInBST(root,14);
        System.out.println(ceil.key);
    }

    public static Node insertInBSTAndFindCeil(Node<Integer> root, int key, int[] ceil){
        if(root == null) return new Node(key);
        if(root.key < key){
            root.right = insertInBSTAndFindCeil(root.right,key,ceil);
        }
        else if(root.key > key){
            ceil[0] = root.key;
            root.left = insertInBSTAndFindCeil(root.left,key,ceil);
        }
        return root;
    }

    public static void printCeilingOnLeftSide(int[] arr){
        int n = arr.length;
        TreeSet<Integer> ts = new TreeSet<>();
        for(int i = 0; i<n; i++){
            Integer ceil = ts.ceiling(arr[i]);
            System.out.print(((ceil != null)?ceil:-1) + " ");
            ts.add(arr[i]);
        }
        System.out.println();
    }

    public static void testCeilingOnLeftSide(){
        int[] arr = {2,8,30,15,25,12};
        int[] ans1 = new int[arr.length];
        Node root = null;
        for(int i = 0; i<arr.length; i++){
            int[] ceil = {-1}; // wrapper for ceil reference
            root = insertInBSTAndFindCeil(root,arr[i],ceil);
            ans1[i] = ceil[0];
        }
        for(int x: ans1) System.out.print(x+" ");
        System.out.println();
        printCeilingOnLeftSide(arr);
    }

    public static Node insertInBSTWithLeftCnt(Node<Integer> root, int key){
        if(root == null) return new Node(key);
        if(root.key < key){
            root.right = insertInBSTWithLeftCnt(root.right,key);
        }
        else if(root.key > key){
            root.leftCnt++;
            root.left = insertInBSTWithLeftCnt(root.left,key);
        }
        return root;
    }

    public static Node deleteInBSTWithLeftCnt(Node<Integer> root, int key){
        if(root == null) return null;
        if(key < root.key){
            root.leftCnt--;
            root.left = deleteInBSTWithLeftCnt(root.left,key);
        }
        else if(root.key < key){
            root.right = deleteInBSTWithLeftCnt(root.right,key);
        }
        else{
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;
            Node<Integer> successor = getInorderSuccessorInBST(root);
            root.key = successor.key;
            root.right = deleteInBSTWithLeftCnt(root.right, successor.key);
        }
        return root;
    }

    public static void printKthElement(Node<Integer> root,int k){
        if(root != null){
            int diff = k - root.leftCnt - 1;
            if(diff == 0){
                System.out.println(root.key);
                return;
            }
            if(diff < 0) printKthElement(root.left,k);
            else printKthElement(root.right,diff);
        }
    }

    public static void testPrintKthElement(){
        Node<Integer> root = null;
        root = insertInBSTWithLeftCnt(root,50);
        root = insertInBSTWithLeftCnt(root,20);
        root = insertInBSTWithLeftCnt(root,100);
        root = insertInBSTWithLeftCnt(root,10);
        root = insertInBSTWithLeftCnt(root,40);
        root = insertInBSTWithLeftCnt(root,70);
        root = insertInBSTWithLeftCnt(root,120);
        root = insertInBSTWithLeftCnt(root,4);
        root = insertInBSTWithLeftCnt(root,35);
        root = insertInBSTWithLeftCnt(root,60);
        root = insertInBSTWithLeftCnt(root,80);

        printKthElement(root,8);
    }

    public static boolean checkForBST(Node<Integer> root, int floor, int ceil){
        if(root == null) return true;
        return (floor < root.key && root.key < ceil)
                && checkForBST(root.left,floor,root.key)
                && checkForBST(root.right,root.key,ceil);
    }

    public static boolean checkForBST2(Node<Integer> root, int[] prev){
        if(root == null) return true;
        if(checkForBST2(root.left,prev) == false) return false;
        if(root.key <= prev[0]) return false;
        prev[0] = root.key;
        return checkForBST2(root.right,prev);
    }

    public static void testCheckForBST(){
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(8);
        root.right = new Node<>(20);
        root.right.left = new Node<>(13);
        root.right.right = new Node<>(24);
        System.out.println(checkForBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE));
        int[] prev = {Integer.MIN_VALUE};
        System.out.println(checkForBST2(root,prev));
    }

    public static void findSwaps(Node<Integer> root, Node<Integer>[] prev, Node<Integer>[] swaps){
        // doing inorder traversal and finding sort violations. Inorder traversal of BST is sorted
        if(root == null) return;
        findSwaps(root.left,prev,swaps);
        if(prev[0] != null && root.key <= prev[0].key){
            // violation
            if(swaps[0] == null) swaps[0] = prev[0];
            swaps[1] = root;
        }
        prev[0] = root;
        findSwaps(root.right,prev,swaps);
    }

    public static void fixBST2NodesSwapped(Node<Integer> root){
        Node<Integer>[] prev = new Node[1]; //{null}
        Node<Integer>[] swaps = new Node[2]; //{null,null}
        findSwaps(root,prev,swaps);
        int tmp = swaps[0].key;
        swaps[0].key = swaps[1].key;
        swaps[1].key = tmp;
    }

    public static void testFixBST2NodesSwapped(){
        Node root = new Node(60);
        root.left = new Node(8);
        root.left.left = new Node(4);
        root.left.right = new Node(10);
        root.right = new Node(80);
        root.right.left = new Node(20);
        root.right.right = new Node(100);
        inorderTraversal(root);
        System.out.println();
        fixBST2NodesSwapped(root);
        inorderTraversal(root);
        System.out.println();
    }

    public static boolean pairExistsWithGivenSum(Node<Integer> root, int sum, HashSet<Integer> hs){
        if(root == null) return false;
        if(pairExistsWithGivenSum(root.left,sum,hs) == true) return true;
        int x = sum - root.key;
        if(hs.contains(x)) return true; // checking for the pair element
        hs.add(root.key); // adding current element
        return pairExistsWithGivenSum(root.right,sum,hs);
    }

    public static void testPairExistsWithGivenSum(){
        Node root = new Node(10);
        root.left = new Node(8);
        root.left.left = new Node(4);
        root.left.right = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(11);
        root.right.right = new Node(30);
        root.right.right.left = new Node(25);

        System.out.println(pairExistsWithGivenSum(root,33,new HashSet<>()));

    }

    public static void verticalSumOfBST(Node<Integer> root, int index, TreeMap<Integer,Integer> sums){
        if(root != null){
            sums.put(index,sums.getOrDefault(index,0)+root.key);
            verticalSumOfBST(root.left,index-1,sums);
            verticalSumOfBST(root.right,index+1,sums);
        }
    }

    public static void testVerticalSumOfBST(){
        Node root = new Node(10);
        root.left = new Node(20);
        root.left.left = new Node(5);
        root.left.right = new Node(15);
        root.right = new Node(30);

        TreeMap<Integer,Integer> sums = new TreeMap<>();
        verticalSumOfBST(root,0,sums);
        System.out.println(sums);

    }

    public static void verticalTraversalOfBST(Node<Integer> root,int index,TreeMap<Integer,ArrayList<Integer>> groups){
        if(root != null){
            if(groups.get(index) == null){
                groups.put(index,new ArrayList<>());
            }
            groups.get(index).add(root.key);

            verticalTraversalOfBST(root.left,index-1,groups);
            verticalTraversalOfBST(root.right,index+1,groups);
        }
    }

    public static void verticalTraversalOfBST2(Node<Integer> root,TreeMap<Integer,ArrayList<Integer>> groups){
        Queue<Pair<Node,Integer>> q = new LinkedList<>();
        q.add(new Pair<>(root,0));
        while(!q.isEmpty()){
            Pair<Node,Integer> p = q.poll();
            Node<Integer> curr = p.key;
            int index = p.value;

            if(groups.get(index) == null){
                groups.put(index,new ArrayList<>());
            }
            groups.get(index).add(curr.key);

            if(curr.left != null) q.add(new Pair<>(curr.left,index-1));
            if(curr.right != null) q.add(new Pair<>(curr.right,index+1));
        }
    }

    public static void testVerticalTraversalOfBST(){
        Node root = new Node(10);
        root.left = new Node(20);
        root.left.left = new Node(5);
        root.left.right = new Node(15);
        root.right = new Node(30);

        TreeMap<Integer,ArrayList<Integer>> groups = new TreeMap<>();
//        verticalTraversalOfBST(root,0,groups);
        verticalTraversalOfBST2(root,groups);
        System.out.println(groups);

    }

    public static void topViewOfBST(Node<Integer> root,int index, TreeMap<Integer,Integer> ans){
        // preorder traversal only works
        // current node should be processed first so that it can not be overridden by sub tree nodes
        // so preorder for topview
        if(root != null){
            if(ans.get(index) == null){ // unvisited index
                ans.put(index,root.key);
            }
            topViewOfBST(root.left,index-1,ans);
            topViewOfBST(root.right,index+1,ans);
        }
    }

    public static void bottomViewOfBST(Node<Integer> root,int index, TreeMap<Integer,Integer> ans){
        // preorder traversal only works
        // current node should be processed first so that it can be overridden by sub tree nodes
        // if we do inorder it will create problem for same level and same index nodex
        // example: problem with root.left.right and root.right.left
        // so preorder
        if(root != null){
            ans.put(index,root.key); // latest update
            bottomViewOfBST(root.left,index-1,ans);
            bottomViewOfBST(root.right,index+1,ans);
        }
    }



    public static void testTopBottomViewsOfBST(){
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(6);
        root.right.left = new Node(5);
        root.right.right = new Node(7);

        TreeMap<Integer,Integer> topView = new TreeMap<>();
        topViewOfBST(root,0,topView);
        System.out.println(topView);

        TreeMap<Integer,Integer> bottomView = new TreeMap<>();
        bottomViewOfBST(root,0,bottomView);
        System.out.println(bottomView);

    }

    public static void main(String[] args) {
        testSearchInBST();
        testInsertInBST();
        testDeleteInBST();
        testFloorNCeilInBST();
        testCeilingOnLeftSide();
        testPrintKthElement();
        testCheckForBST();
        testFixBST2NodesSwapped();
        testPairExistsWithGivenSum();
        testVerticalSumOfBST();
        testVerticalTraversalOfBST();
        testTopBottomViewsOfBST();
    }
}
