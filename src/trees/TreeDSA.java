package trees;

import java.util.*;

class Node<T>{
    T key;
    Node<T> left;
    Node<T> right;

    public Node(T key) {
        this.key = key;
    }
}

public class TreeDSA {

    public static void inorderTraversal(Node root){
        if(root != null){
            inorderTraversal(root.left);
            System.out.print(root.key + " ");
            inorderTraversal(root.right);
        }
    }

    public static void preOrderTraversal(Node root){
        if(root != null){
            System.out.print(root.key + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public static void postOrderTraversal(Node root){
        if(root != null){
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.key + " ");
        }
    }

    public static void testTreeTraversals(){
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(20);
        root.right = new Node<>(30);
        root.right.left = new Node<>(40);
        root.right.right = new Node<>(50);

        inorderTraversal(root);
        System.out.println();
        preOrderTraversal(root);
        System.out.println();
        postOrderTraversal(root);
        System.out.println();
        System.out.println("Height :" + heightOfBinaryTree(root));
        printNodesAtKDistance(root,2);
        System.out.println();
        System.out.print("Lever order Traversal/Breadth First Traversal : ");
        breadthFirstTraversalOfBT(root);
        System.out.println();
        System.out.println("Lever order Traversal/Breadth First Traversal (line by line) 1: ");
        levelOrderTraversalLineByLine1(root);
        System.out.println();
        System.out.println("Lever order Traversal/Breadth First Traversal (line by line) 2: ");
        levelOrderTraversalLineByLine2(root);
        System.out.println();
        System.out.println("Size of Binary Tree : " + sizeOfBT(root));
        System.out.println("Maximum in Binary Tree : " + maximumInBT(root));
        printLeftViewOfBT(root);
        System.out.println();
        System.out.print("Left View of Binary Tree recursion: ");
        printLeftViewOfBTRecursive(root,1);
        System.out.println();
        System.out.println("Max Width Of BT : " + maxWidthOfBT(root));
        System.out.println();
    }

    public static int heightOfBinaryTree(Node root){
        if(root == null) return 0;
        return Math.max(heightOfBinaryTree(root.left),heightOfBinaryTree(root.right)) + 1;
    }

    public static void printNodesAtKDistance(Node root, int k){
        if(root != null){
            if(k == 0) System.out.print(root.key + " ");
            else{
                printNodesAtKDistance(root.left,k-1);
                printNodesAtKDistance(root.right,k-1);
            }
        }
    }

    public static void breadthFirstTraversalOfBT(Node root){
        if(root == null) return;
        Queue<Node> q = new LinkedList<>(); // using linked list because i understand tree is also made of links
        q.add(root);
        while(q.isEmpty() == false){
            Node curr = q.poll();
            System.out.print(curr.key + " ");
            if(curr.left != null) q.add(curr.left);
            if(curr.right != null) q.add(curr.right);
        }
    }

    public static void levelOrderTraversalLineByLine1(Node root){
        if(root == null) return;
        // we should use linkedlist implementation of queue only,
        // arraydeque implementation is not accepting null value
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(q.size() > 1){
            Node curr = q.poll();
            if(curr == null){
                System.out.println();
                q.add(null);
                continue;
            }
            System.out.print(curr.key + " ");
            if(curr.left != null) q.add(curr.left);
            if(curr.right != null) q.add(curr.right);
        }
    }

    public static void levelOrderTraversalLineByLine2(Node root){
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(q.isEmpty() == false){
            int cnt = q.size();
            for(int i=0;i<cnt;i++){ // printing current level and adding their children
                Node curr = q.poll();
                System.out.print(curr.key + " ");
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
            System.out.println();
        }
    }

    public static int sizeOfBT(Node root){
        if(root == null) return 0;
        return sizeOfBT(root.left)+sizeOfBT(root.right)+1;
    }

    public static int maximumInBT(Node<Integer> root){
        if(root == null) return Integer.MIN_VALUE;
        return Math.max(root.key,Math.max(maximumInBT(root.left),maximumInBT(root.right)));
    }

    public static void printLeftViewOfBT(Node root){
        // using breadth first traversal
        if(root == null) return;
        System.out.print("Left View of Binary Tree : ");
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(q.isEmpty() == false){
            System.out.print(q.peek().key+" "); // printing left most node in current level
            int cnt = q.size();
            for(int i = 0; i<cnt; i++){
                Node curr = q.poll();
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
        }
    }

    static int completedLevel = 0;
    public static void printLeftViewOfBTRecursive(Node root, int currLevel){
        if(root != null){
            if(currLevel > completedLevel){
                System.out.print(root.key + " ");
                completedLevel = currLevel;
            }
            printLeftViewOfBTRecursive(root.left,currLevel+1);
            printLeftViewOfBTRecursive(root.right,currLevel+1);
        }
    }

    public static boolean childrenSumProperty(Node<Integer> root){
        if(root == null) return true;
        if(root.left == null && root.right == null) return true;
        int sum = 0;
        sum += (root.left != null) ? root.left.key : 0;
        sum += (root.right != null) ? root.right.key : 0;
        return (sum == root.key) && childrenSumProperty(root.left) && childrenSumProperty(root.right);
    }
    public static void testChildrenSumProperty(){
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(1);
        root.right = new Node<>(2);
        root.right.left = new Node<>(1);
        root.right.right = new Node<>(2);
        System.out.println(childrenSumProperty(root));
    }

    static boolean isBalanced = true;
    public static int checkForBalancedBinaryTree1(Node root){
        // i used static boolean variable
        if(root == null) return 0;
        int leftHeight = checkForBalancedBinaryTree1(root.left);
        int rightHeight = checkForBalancedBinaryTree1(root.right);
        isBalanced = Math.abs(leftHeight-rightHeight) <= 1;
        return Math.max(leftHeight,rightHeight) + 1;
    }

    public static int checkForBalancedBinaryTree2(Node root){
        // will return -1 in case of not balanced
        // if balanced it returns height of tree
        if(root == null) return 0;
        int leftHeight = checkForBalancedBinaryTree2(root.left);
        int rightHeight = checkForBalancedBinaryTree2(root.right);
        boolean isBalanced = (leftHeight != -1) && (rightHeight != -1) && Math.abs(leftHeight-rightHeight) <= 1;
        return (isBalanced) ? Math.max(leftHeight,rightHeight) + 1 : -1 ;
    }

    public static void testCheckForBalancedBinaryTree(){
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(1);
        root.right = new Node<>(2);
        root.right.left = new Node<>(1);
        root.right.right = new Node<>(2);
        checkForBalancedBinaryTree1(root);
        System.out.println(isBalanced);
        System.out.println(checkForBalancedBinaryTree2(root));
    }

    public static int maxWidthOfBT(Node root){
        if(root == null) return 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int maxWidth = 0;
        while(q.isEmpty() == false){
            int size = q.size(); // level size
            maxWidth = Math.max(maxWidth,size);
            for(int i = 0; i<size; i++){
                Node curr = q.poll();
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
        }
        return maxWidth;
    }

    public static Node insertNodeAtEndOfCDLL(Node head, Node node){
        if(head == null){
            node.left = node;
            node.right = node;
            return node;
        }

        node.right = head;
        node.left = head.left;

        head.left.right = node;
        head.left = node;

        return head;
    }

    public static Node mergeTwoCDLLs(Node head1, Node head2){
        if(head2 == null) return head1;
        if(head1 == null) return head2;

        Node tail1 = head1.left;
        Node tail2 = head2.left;

        tail2.right = head1;
        head1.left = tail2;

        tail1.right = head2;
        head2.left = tail1;

        return head1;
    }

    public static void printCDLL(Node head){
        Node curr = head;
        do{
            System.out.print(curr.key + " ");
            curr = curr.right;
        }while (curr != head);
        System.out.println();
    }

    public static void printDLL(Node head){
        for(Node curr = head; curr != null; curr = curr.right){
            System.out.print(curr.key+" ");
        }
        System.out.println();
    }

    public static Node convertBTtoCDLL(Node root){
        // I am trying to convert Binary Tree into Circular Doubly linked list.
        if(root == null) return null;
        Node leftHead = convertBTtoCDLL(root.left);
        Node rightHead = convertBTtoCDLL(root.right);

        leftHead = insertNodeAtEndOfCDLL(leftHead,root);
        leftHead = mergeTwoCDLLs(leftHead,rightHead);
        return leftHead;
    }

    static Node prev = null;
    public static Node convertBTtoDLL(Node root){
        if(root == null) return null;

        Node head = convertBTtoDLL(root.left);

        if(prev == null) head = root;
        else{
            root.left = prev;
            prev.right = root;
        }

        prev = root;

        convertBTtoDLL(root.right);

        return head;
    }

    public static void testConvertBTtoCDLL(){
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(1);
        root.right = new Node<>(2);
        root.right.left = new Node<>(1);
        root.right.right = new Node<>(2);

        Node head = convertBTtoCDLL(root);
        printCDLL(head);

    }

    public static void testConvertBTtoDLL(){
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(1);
        root.right = new Node<>(2);
        root.right.left = new Node<>(1);
        root.right.right = new Node<>(2);

        Node dllHead = convertBTtoDLL(root);
        printDLL(dllHead);

    }

    static int preRootIndex = 0;
    public static Node constructBTfromInorderNPreorder(int[] inorder, int[] preorder, int start, int end){
        if(start > end) return null;
        Node<Integer> root = new Node<>(preorder[preRootIndex++]);

        int inRootIndex=start;
        for(int i = start; i<=end; i++){
            if(inorder[i] == root.key){
                inRootIndex = i;
                break;
            }
        }

        root.left = constructBTfromInorderNPreorder(inorder,preorder,start,inRootIndex-1);
        root.right = constructBTfromInorderNPreorder(inorder,preorder,inRootIndex+1,end);

        return root;

    }

    public static void testConstructBTfromInorderNPreorder(){
        int[] inorder = {20,10,40,30,50};
        int[] preorder = {10,20,30,40,50};

        Node root = constructBTfromInorderNPreorder(inorder,preorder,0,inorder.length-1);
        inorderTraversal(root);
        System.out.println();
        preOrderTraversal(root);
        System.out.println();
    }

    public static void spiralTraversalOfBT(Node root){
        if(root == null) return;
        System.out.println("Spiral Traversal of Binary Tree : ");
        Deque<Node> dq = new ArrayDeque<>();
        dq.addLast(root);
        boolean leftToRight = true;
        while(dq.isEmpty() == false){
            int cnt = dq.size();
            for(int i=0;i<cnt;i++){ // printing current level and adding their children
                Node curr = (leftToRight)? dq.pollFirst() : dq.pollLast();
                System.out.print(curr.key + " ");
                if(leftToRight){
                    if(curr.left != null) dq.addLast(curr.left);
                    if(curr.right != null) dq.addLast(curr.right);
                }else{
                    if(curr.right != null) dq.addFirst(curr.right);
                    if(curr.left != null) dq.addFirst(curr.left);
                }
            }
            leftToRight = !leftToRight;
            System.out.println();
        }
    }

    public static void printSpiral(Node root){
        if(root==null)return;
        Queue<Node> q=new LinkedList<>();
        Stack<Integer> s=new Stack<>();
        boolean reverse=false;
        q.add(root);
        while(q.isEmpty()==false){
            int count=q.size();
            for(int i=0;i<count;i++){
                Node<Integer> curr=q.poll();
                if(reverse)
                    s.add(curr.key);
                else
                    System.out.print(curr.key+" ");
                if(curr.left!=null)
                    q.add(curr.left);
                if(curr.right!=null)
                    q.add(curr.right);
            }
            if(reverse){
                while(s.isEmpty()==false){
                    System.out.print(s.pop()+" ");
                }
            }
            reverse=!reverse;
            System.out.println();
        }
    }

    public static void printSpiral2(Node root){
        if (root == null) return;
        Stack<Node> s1 = new Stack<Node>();
        Stack<Node> s2 = new Stack<Node>();

        s1.add(root);

        while (!s1.isEmpty() || !s2.isEmpty()) {
            while (!s1.empty()) {
                Node temp = s1.pop();
                System.out.print(temp.key + " ");
                if (temp.left != null) s2.add(temp.left);
                if (temp.right != null) s2.add(temp.right);
            }
            System.out.println();
            while (!s2.empty()) {
                Node temp = s2.pop();
                System.out.print(temp.key + " ");
                if (temp.right != null) s1.add(temp.right);
                if (temp.left != null) s1.add(temp.left);
            }
            System.out.println();
        }
    }

    public static void testSpiralTraversalOfBT(){
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(20);
        root.right = new Node<>(30);
        root.right.left = new Node<>(40);
        root.right.right = new Node<>(50);

        spiralTraversalOfBT(root);
        System.out.println();
        printSpiral(root);
        System.out.println();
        printSpiral2(root);
    }

    static int diameter = 0;
    public static int heightOfBTtoFindDiameter(Node root){
        // using height of tree algorithm
        if(root == null) return 0;
        int leftDiameter = heightOfBTtoFindDiameter(root.left);
        int rightDiameter = heightOfBTtoFindDiameter(root.right);
        diameter = Math.max(diameter,leftDiameter+rightDiameter+1);
        return Math.max(leftDiameter,rightDiameter) + 1;
    }

    public static void testDiameterOfBT(){
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(20);
        root.right = new Node<>(30);
        root.right.left = new Node<>(40);
        root.right.right = new Node<>(50);
        root.right.left.left = new Node<>(60);

        heightOfBTtoFindDiameter(root);
        System.out.println(diameter);

    }

    public static boolean isAncestor(Node root, Node node, HashSet<Node> ancestors){
        if(root == null) return false;
        if(root == node){
            ancestors.add(root);
            return true;
        }
        boolean isAncestorFlag = isAncestor(root.left,node,ancestors) ||
                isAncestor(root.right,node,ancestors);

        if(isAncestorFlag) ancestors.add(root);
        return isAncestorFlag;
    }

    static Node lowestCommonAncestor = null;
    public static boolean[] isCommonAncestor(Node root, Node node1, Node node2){
        if (root == null) return new boolean[]{false,false};

        boolean[] leftRes = isCommonAncestor(root.left,node1,node2);
        boolean[] rightRes = isCommonAncestor(root.right,node1,node2);

        boolean[] res = new boolean[]{leftRes[0]||rightRes[0],leftRes[1]||rightRes[1]};

        if(root == node1) res[0] = true;
        if(root == node2) res[1] = true;

        if(res[0]&&res[1]){
            if(lowestCommonAncestor == null) lowestCommonAncestor = root;
        }

        return res;
    }

    public static Node lowestCommonAncestor(Node root, Node node1, Node node2){
        if(root == null) return null;
        if(root == node1 || root == node2) return root;

        Node left = lowestCommonAncestor(root.left,node1,node2);
        Node right = lowestCommonAncestor(root.right,node1,node2);
        if(left != null && right != null) return root;
        
        return (left != null) ? left : right;
    }

    public static void testLowestCommonAncestor(){
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(20);
        root.right = new Node<>(30);
        root.right.left = new Node<>(40);
        root.right.right = new Node<>(50);
        root.right.left.left = new Node<>(60);
        root.right.right.left = new Node<>(70);
        root.right.right.right = new Node<>(80);

        Node node1 = root.left;
        Node node2 = root.right.right.right;

        isCommonAncestor(root,node1,node2);
        System.out.println(lowestCommonAncestor.key);
        System.out.println(lowestCommonAncestor(root,node1,node2).key);
    }

    static int maxDistance = 0;
    static int rootDistance = 0;
    public static int[] timeToBurnBT(Node root,Node fireLeaf){
        // i am using int[] of size 2
        // 1st value is height and 2nd values is binary boolean 0or1 to differentiate fireLeaf side or not
        if(root == null) return new int[]{0,0};

        int[] leftRes = timeToBurnBT(root.left,fireLeaf);
        int[] rightRes = timeToBurnBT(root.right,fireLeaf);
        int[] res = new int[]{Math.max(leftRes[0],rightRes[0])+1,leftRes[1]|rightRes[1]};
        if(root == fireLeaf) res[1] = 1;

        if(res[1] == 1){
            // taking non fire side length into consideration
            if(leftRes[1] == 0){
                maxDistance = Math.max(maxDistance,leftRes[0]+rootDistance);
            }else{
                maxDistance = Math.max(maxDistance,rightRes[0]+rootDistance);
            }
            rootDistance++;
        }
        return res;
    }

    static class MaxDistance{
        int val;
        public MaxDistance(int val) { this.val = val; }
    }
    static class FireAncestorsHeight{
        int val;
        public FireAncestorsHeight(int val) { this.val = val; }
    }

    public static int burnTime(Node root, Node fireLeaf, MaxDistance res, FireAncestorsHeight fireAncestorsHeight){
        // this return height of tree while maxDistantNode value gets stored in res
        if(root == null) return 0;
        if(root == fireLeaf){
            fireAncestorsHeight.val = 0;
            return 1;
        }

        FireAncestorsHeight leftFireHeight = new FireAncestorsHeight(-1);
        FireAncestorsHeight rightFireHeight = new FireAncestorsHeight(-1);
        int lh = burnTime(root.left,fireLeaf,res,leftFireHeight);
        int rh = burnTime(root.right,fireLeaf,res,rightFireHeight);

        if(leftFireHeight.val != -1){
            fireAncestorsHeight.val = leftFireHeight.val+1;
            res.val = Math.max(res.val, rh + fireAncestorsHeight.val);
        } else if (rightFireHeight.val != -1) {
            fireAncestorsHeight.val = rightFireHeight.val+1;
            res.val = Math.max(res.val, lh + fireAncestorsHeight.val);
        }

        return Math.max(lh,rh)+1;
    }

    public static void testTimeToBurnBT(){
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(20);
        root.right = new Node<>(30);
        root.left.left = new Node<>(40);
        root.left.right = new Node<>(50);
        root.right.right = new Node<>(60);

        timeToBurnBT(root,root.left.right);
        System.out.println(maxDistance);

        MaxDistance distanceToFarthestNodeFromFireLeaf = new MaxDistance(0);
        FireAncestorsHeight fireAncestorsHeight = new FireAncestorsHeight(-1);
        burnTime(root,root.left.right,distanceToFarthestNodeFromFireLeaf,fireAncestorsHeight);
        System.out.println(distanceToFarthestNodeFromFireLeaf.val);

    }

    public static int countNodesInBT(Node root){
        if(root == null) return 0;
        int leftCnt = countNodesInBT(root.left);
        int rightCnt = countNodesInBT(root.right);
        return leftCnt+rightCnt+1;
    }

    public static int countNodesInFullBT(Node root){
        int lHeight = 0;
        Node tmp = root;
        while(tmp != null){
            lHeight++;
            tmp = tmp.left;
        }
        int rHeight = 0;
        tmp = root;
        while(tmp != null){
            rHeight++;
            tmp = tmp.right;
        }

        return (lHeight==rHeight)? (int) (Math.pow(2, lHeight) - 1) : -1;
    }

    public static int countNodesInCompleteBT(Node root){
        if (root == null) return 0;
        int lCnt = countNodesInFullBT(root.left);
        int rCnt = countNodesInFullBT(root.right);

        if(lCnt == -1) lCnt = countNodesInCompleteBT(root.left);
        if(rCnt == -1) rCnt = countNodesInCompleteBT(root.right);

        return lCnt + rCnt + 1;
    }

    public static int countNodesInCompleteBTAuthor(Node root){
        int lHeight = 0, rHeight = 0;
        Node tmp = root;
        while(tmp != null){
            lHeight++;
            tmp = tmp.left;
        }
        tmp = root;
        while(tmp != null){
            rHeight++;
            tmp = tmp.right;
        }

        if(lHeight==rHeight){
            return (int) (Math.pow(2, lHeight) - 1);
        }

        return 1 + countNodesInCompleteBTAuthor(root.left) + countNodesInCompleteBTAuthor(root.right);
    }

    public static void testCountNodesInCompleteBT(){
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(20);
        root.right = new Node<>(30);
        root.left.left = new Node<>(40);
        root.left.right = new Node<>(50);

        System.out.println(countNodesInBT(root));
        System.out.println(countNodesInCompleteBT(root));
        System.out.println(countNodesInCompleteBTAuthor(root));
    }

    public static void serializePreorder(Node<Integer> root, ArrayList<Integer> data){
        if(root == null){
            data.add(-1); // -1 to mark nulls
        }else{
            data.add(root.key);
            serializePreorder(root.left,data);
            serializePreorder(root.right,data);
        }
    }

    static int index = 0;
    public static Node deserializePreorder(ArrayList<Integer> data){
        int val = data.get(index++);
        if(val == -1) return null;
        Node curr = new Node(val);
        curr.left = deserializePreorder(data);
        curr.right = deserializePreorder(data);
        return curr;
    }

    public static void testSerializationNDeserialization(){
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(20);
        root.right = new Node<>(30);
        root.left.left = new Node<>(40);
        root.left.right = new Node<>(50);

        ArrayList<Integer> data = new ArrayList<>();
        serializePreorder(root,data);
        System.out.println(data);

        Node res = deserializePreorder(data);
        preOrderTraversal(res);
        System.out.println();
        inorderTraversal(res);
        System.out.println();
    }

    public static void iterativeInorderTraversal(Node root){
        if(root == null) return;
        Stack<Node> s = new Stack<>();
        s.add(root);
        while(!s.isEmpty()){
            Node curr = s.peek();
            while(curr.left != null){
                curr = curr.left;
                s.add(curr);
            }

            while(!s.isEmpty() && s.peek().right == null){
                System.out.print(s.pop().key+" ");
            }
            if(!s.isEmpty()){
                System.out.print(s.peek().key+" ");
                s.add(s.pop().right);
            }
        }
    }

    public static void iterativeInorderTraversalAuthor(Node root){
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while(curr != null || s.isEmpty() == false){
            while(curr != null){
                s.add(curr);
                curr = curr.left;
            }
            curr = s.pop();
            System.out.print(curr.key+" ");
            curr = curr.right;
        }
    }

    public static void iterativePreorderTraversal(Node root){
        if(root == null) return;
        Stack<Node> s = new Stack<>();
        s.add(root);
        while (!s.isEmpty()){
            Node curr = s.pop();
            System.out.print(curr.key+" ");
            if(curr.right!=null)s.add(curr.right);
            if(curr.left!=null)s.add(curr.left);
        }
    }

    public static void iterativePreorderTraversal2(Node root){
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while (curr != null || !s.isEmpty()){
            if(curr != null){
                System.out.print(curr.key+" ");
                s.add(curr);
                curr = curr.left;
            }
            else{
                curr = s.pop().right;
            }
        }
    }

    public static void iterativePreorderTraversalAuthor(Node root){
        if(root == null) return;
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while (curr != null || !s.isEmpty()){
            while (curr != null){
                System.out.print(curr.key+" ");
                if(curr.right != null) s.add(curr.right);
                curr = curr.left;
            }
            if(!s.isEmpty()) curr = s.pop();
        }
    }

    public static void testIterativeTraversals(){
        Node<Integer> root = new Node<>(10);
        root.left = new Node<>(20);
        root.right = new Node<>(30);
        root.right.left = new Node<>(60);
        root.left.left = new Node<>(40);
        root.left.left.left = new Node<>(70);
        root.left.left.right = new Node<>(80);
        root.left.right = new Node<>(50);
        root.left.right.right = new Node<>(90);

        System.out.println("inorder iterative traversal:");
        iterativeInorderTraversal(root);
        System.out.println();
        System.out.println("inorder iterative traversal author:");
        iterativeInorderTraversalAuthor(root);
        System.out.println();
        System.out.println("inorder recursive traversal:");
        inorderTraversal(root);
        System.out.println();
        System.out.println("Preorder iterative traversal:");
        iterativePreorderTraversal(root);
        System.out.println();
        System.out.println("Preorder iterative traversal2:");
        iterativePreorderTraversal2(root);
        System.out.println();
        System.out.println("Preorder iterative traversal Author:");
        iterativePreorderTraversalAuthor(root);
        System.out.println();
    }

    public static void leftViewOfBT(Node root){
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(q.isEmpty() == false){
            System.out.print(q.peek().key+" ");
            int size = q.size();
            for(int i = 0; i<size; i++){
                Node curr = q.poll();
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
        }
    }

    public static void rightViewOfBT(Node root){
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(q.isEmpty() == false){
            int size = q.size();
            Node curr = null;
            for(int i = 0; i<size; i++){
                curr = q.poll();
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
            System.out.print(curr.key+" ");
        }
    }

    public static void topViewOfBT(Node root){

    }

    public static void bottomViewOfBT(Node root){

    }

    public static void viewsOfBT(){
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right = new Node<>(3);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);

        System.out.println("Left view:");
        leftViewOfBT(root);
        System.out.println("\nRight view:");
        rightViewOfBT(root);
        System.out.println("\nTop view:");
        topViewOfBT(root);
    }

    public static Node insertInBT(Node root, int x){
        Node tmp = new Node(x);
        if(root == null) return tmp;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(q.isEmpty() == false){
            Node curr = q.poll();
            if(curr.left == null){
                curr.left = tmp;
                return root;
            }else q.add(curr.left);
            if(curr.right == null){
                curr.right = tmp;
                return root;
            }else q.add(curr.right);
        }

        return root;
    }

    public static void testInsertInBT(){
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
//        root.right = new Node<>(3);

        Node ans = insertInBT(root,3);
        System.out.println();
        inorderTraversal(ans);
        System.out.println();
    }

    public static void deleteInBT(Node root, int deleteKey){
        // we replace the node with deepestRightMost Nodes value and delete deepestRightMost
        // deepestRightMostNode = rightMostNode in Deep level (last level)
        if(root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node parentOfLastNode = null;
        Node<Integer> curr = null;
        Node delNode = null;
        while(q.isEmpty() == false){
            int size = q.size();
            for(int i = 0; i<size; i++){
                curr = q.poll();
                if(curr.key == deleteKey) delNode = curr;
                if(curr.left != null){
                    parentOfLastNode = curr;
                    q.add(curr.left);
                }
                if(curr.right != null){
                    parentOfLastNode = curr;
                    q.add(curr.right);
                }
            }
        }
        // by this time curr will be deepestRightMostNode
        // parentOfLastNode will be parent of deepestRightMostNode
        // delNode will contain deleteKey
        delNode.key = curr.key;

        // because deepestRightMostNode will be on either right or left references
        // so checking which is not null and making it null
        if(parentOfLastNode.right != null) parentOfLastNode.right = null;
        else parentOfLastNode.left = null;
    }

    public static void testDeleteInBT(){
        Node root = new Node(10);
        root.left = new Node(11);
        root.left.left = new Node(7);
        root.left.right = new Node(12);
        root.right = new Node(9);
        root.right.left = new Node(15);
        root.right.right = new Node(8);

        System.out.print("\nInorder traversal before Deletion:");
        inorderTraversal(root);
        int key = 11;
        deleteInBT(root,key);
        System.out.print("\nInorder traversal after Deletion:");
        inorderTraversal(root);
    }

    public static void mirrorBT(Node root){
        if(root != null){
            // swap
            Node tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            // recursive calls
            mirrorBT(root.left);
            mirrorBT(root.right);

            // we can first do recursive calls and then do swapping as well
        }
    }

    public static void testMirrorBT(){
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);

        System.out.println("\n Before Mirroring inorder traversal");
        inorderTraversal(root);
        mirrorBT(root);
        System.out.println("\n After Mirroring inorder traversal");
        inorderTraversal(root);
    }

    public static void main(String[] args) {
        testTreeTraversals();
        testChildrenSumProperty();
        testCheckForBalancedBinaryTree();
        testConvertBTtoCDLL();
        testConvertBTtoDLL();
        testConstructBTfromInorderNPreorder();
        testSpiralTraversalOfBT();
        testDiameterOfBT();
        testLowestCommonAncestor();
        testTimeToBurnBT();
        testCountNodesInCompleteBT();
        testSerializationNDeserialization();
        testIterativeTraversals();
        viewsOfBT();
        testInsertInBT();
        testDeleteInBT();
        testMirrorBT();
    }
}
