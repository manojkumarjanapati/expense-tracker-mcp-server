
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static int winningCnt(int[] arr){
        HashSet<Integer> sums = new HashSet<>();
        for(int i = 0; i<arr.length; i++){
            for(int j = i+1; j<arr.length; j++){
                sums.add(arr[i]+arr[j]);
            }
        }
        int cnt = 0;
        for(int x: arr){
            if(sums.contains(x)) cnt++;
        }
        return cnt;
    }

    static ArrayList <Integer> max_of_subarrays(int arr[], int n, int k)
    {
        // Your code here
        ArrayList<Integer> ans = new ArrayList<>();
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i<n; i++){
            while(!dq.isEmpty() && dq.getLast() <= arr[i]){
                dq.removeLast();
            }
            dq.addLast(arr[i]);
            while(dq.size()>k){
                dq.removeFirst();
            }
            ans.add(dq.getFirst());
        }

        return (ArrayList<Integer>) ans.subList(n-k-1,n);
    }

    public static void stringMatch(){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i = 0; i<t; i++){
            String str = sc.next();
            String pattern = sc.next();
            int j=0, k = 0;
            for(; j<str.length(); j++){
                if(k >= pattern.length()){
                    System.out.println("False");
                    break;
                }
                if(pattern.charAt(k) == '?'){
                    k++;
                } else if (pattern.charAt(k) == '*') {
                    if(k+1 < pattern.length() && str.charAt(j) == pattern.charAt(k+1)){
                        k += 2;
                    }
                } else if (str.charAt(j) != pattern.charAt(k)) {
                    System.out.println("False");
                    break;
                }else{
                    k++;
                }
            }
            if(j == str.length()) System.out.println("True");
        }
    }

    static class Node{
        int key;
        Node left;
        Node right;
        public Node(int key){
            this.key = key;
        }
    }

    public static void treeQ(){
        Scanner sc = new Scanner(System.in);
        String[] nums = sc.nextLine().split(" ");
        int x = Integer.valueOf(nums[0]);
        int y = Integer.valueOf(nums[1]);
        String[] nodes = sc.nextLine().split(" ");
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(Integer.valueOf(nodes[0]));
        q.add(root);
        int i = 1;
        while(i<nodes.length){
            Node curr = q.poll();
            String l = nodes[i++];
            if(!l.equals("null")){
                curr.left = new Node(Integer.valueOf(l));
                q.add(curr.left);
            }
            if(i<nodes.length){
                String r = nodes[i++];
                if(!r.equals("null")){
                    curr.right = new Node(Integer.valueOf(r));
                    q.add(curr.right);
                }
            }
        }
        Node ans = lowestCommonAncestor(root,new Node(x), new Node(y));
        System.out.println(ans.key);
    }

    public static Node lowestCommonAncestor(Node root, Node node1, Node node2){
        if(root == null) return null;
        if(root.key == node1.key || root.key == node2.key) return root;

        Node left = lowestCommonAncestor(root.left,node1,node2);
        Node right = lowestCommonAncestor(root.right,node1,node2);
        if(left != null && right != null) return root;

        return (left != null) ? left : right;
    }

    static class Building {
        int number;
        int distance;

        public Building(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }
    }

    public static void addEdge(Map<Integer, List<Building>> graph, int u, int v, int w) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Building(v, w));
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Building(u, w));
    }

    public static int dijkstra(Map<Integer, List<Building>> graph, int start, Set<Integer> hospitals) {
        PriorityQueue<Building> minHeap = new PriorityQueue<>(Comparator.comparingInt(b -> b.distance));
        Set<Integer> visited = new HashSet<>();

        minHeap.add(new Building(start, 0));

        while (!minHeap.isEmpty()) {
            Building current = minHeap.poll();
            int currentBuilding = current.number;
            int currentDistance = current.distance;

            if (visited.contains(currentBuilding)) {
                continue;
            }

            visited.add(currentBuilding);

            if (hospitals.contains(currentBuilding)) {
                return currentBuilding;
            }

            if (graph.containsKey(currentBuilding)) {
                for (Building neighbor : graph.get(currentBuilding)) {
                    if (!visited.contains(neighbor.number)) {
                        minHeap.add(new Building(neighbor.number, currentDistance + neighbor.distance));
                    }
                }
            }
        }

        return -1;
    }

    public static int nearestHospitalBuilding(int N, int M, int K, List<Integer> hospitals, List<int[]> routes) {
        Map<Integer, List<Building>> graph = new HashMap<>();

        for (int[] route : routes) {
            addEdge(graph, route[0], route[1], route[2]);
        }

        Set<Integer> hospitalSet = new HashSet<>(hospitals);
        int result = dijkstra(graph, 1, hospitalSet);

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0;i<n+1; i++){
            adj.add(new ArrayList<>());
        }
        int[] keys = new int[n];
        for(int i=0;i<n;i++) keys[i] = sc.nextInt();
        for(int i = 1; i<n+1; i++){
            for(int key: keys){
                if(key%i == 0 || i%key == 0){
                    adj.get(i).add(key);
                }
            }
        }
        System.out.println(adj);
        HashSet<Integer> permutation = new HashSet<>();
        solve(permutation,adj,1);
        System.out.println(cnt);
    }
    static int cnt = 0;
    public static void solve(HashSet<Integer> permutation, ArrayList<ArrayList<Integer>> adj, int currIdx){
        if(currIdx == adj.size()){
            cnt++;
            return;
        }
        for(int x: adj.get(currIdx)){
            if(!permutation.contains(x)){
                permutation.add(x);
                solve(permutation,adj,currIdx+1);
                permutation.remove(x);
            }
        }
    }

}
