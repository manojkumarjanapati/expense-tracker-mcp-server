package graph;

import java.util.*;

class Edge{
    int to;
    int weight;
    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class GraphEdge{
    int from;
    int to;
    int weight;

    public GraphEdge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class GraphAdjList{
    ArrayList<ArrayList<Integer>> adj;

    public GraphAdjList(int capacity){
        adj = new ArrayList<>(capacity);
        for(int i = 0; i<capacity; i++){
            adj.add(new ArrayList<>());
        }
    }

    public GraphAdjList(ArrayList<ArrayList<Integer>> adj) {
        this.adj = adj;
    }

    void addEdge(int x, int y){
        adj.get(x).add(y);
        adj.get(y).add(x);
    }

    void printGraph(){
        for(int i = 0; i<adj.size(); i++){
            System.out.println(i+" : "+adj.get(i));
        }
    }

    void bfs(int sourceIdx, boolean[] visited){
        // source given and assumed all vertices connected
        Queue<Integer> q = new LinkedList<>();
        q.add(sourceIdx);
        visited[sourceIdx] = true;
        while(!q.isEmpty()){
            int curr = q.poll();
            System.out.print(curr + " ");
            for(int x: adj.get(curr)){
                if(!visited[x]){
                    q.add(x);
                    visited[x] = true;
                }
            }
        }

    }

    void bfs(){
        // source not given and may have few disconnected vertices
        // logic: source vertices will come automatically here, because all non-source vertices gets
        // added to q in bfs(source,visited)
        boolean[] visited = new boolean[adj.size()];
        int connectedComponents = 0;
        for(int source = 0; source<adj.size(); source++){
            if(!visited[source]){
                bfs(source,visited);
                connectedComponents++;
            }
        }
        System.out.println("Connected Componenets [individaul sub graphs]:" + connectedComponents);
    }

    void dfs(int sourceIdx, boolean[] visited){
        System.out.print(sourceIdx+" ");
        visited[sourceIdx] = true;
        for(int x: adj.get(sourceIdx)){
            if(!visited[x]) dfs(x,visited);
        }
    }

    void dfs(){
        boolean[] visited = new boolean[adj.size()];
        int connectedComponents = 0;
        for(int i=0; i<adj.size(); i++){
            if(!visited[i]){
                dfs(i,visited);
                connectedComponents++;
            }
        }
        System.out.println("Connected Componenets [individaul sub graphs]:" + connectedComponents);
    }
}
public class GraphDSA {


    public static void testGraphAdjList(){
        GraphAdjList graph = new GraphAdjList(6);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(4,5); // individual vertices with edge
        graph.printGraph();

        graph.bfs();
        graph.dfs();

        GraphAdjList graph2 = new GraphAdjList(7);
        graph2.addEdge(0,1);
        graph2.addEdge(1,2);
        graph2.addEdge(2,3);
//        graph2.addEdge(0,4);
        graph2.addEdge(4,5);
        graph2.addEdge(4,6);
        graph2.addEdge(5,6);
        graph2.bfs();
        graph2.dfs();
    }

    public static void addUndirectedEdge(ArrayList<ArrayList<Integer>> adj, int vertex1, int vertex2){
        adj.get(vertex1).add(vertex2);
        adj.get(vertex2).add(vertex1);
    }

    public static void addUndirectedWeightedEdge(ArrayList<ArrayList<Edge>> adj, int vertex1, int vertex2, int weight){
        adj.get(vertex1).add(new Edge(vertex2,weight));
        adj.get(vertex2).add(new Edge(vertex1,weight));
    }

    public static void addUndirectedWeightedEdge(int[][] adj, int vertex1, int vertex2, int weight){
        adj[vertex1][vertex2] = weight;
        adj[vertex2][vertex1] = weight;
    }

    public static void addDirectedEdge(ArrayList<ArrayList<Integer>> adj, int from, int to){
        adj.get(from).add(to);
    }

    public static void addDirectedWeightedEdge(ArrayList<ArrayList<Edge>> adj, int from, int to, int weight){
        adj.get(from).add(new Edge(to,weight));
    }

    public static void addDirectedWeightedEdge(int[][] adj, int from, int to, int weight){
        adj[from][to] = weight;
    }

    public static ArrayList<ArrayList<Integer>> getGraph(int size){
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i<size; i++) adj.add(new ArrayList<>());
        return adj;
    }

    public static ArrayList<ArrayList<Edge>> getWeightedGraph(int size){
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
        for(int i = 0; i<size; i++) adj.add(new ArrayList<>());
        return adj;
    }

    public static void shortestPathInUndirectedGraph(ArrayList<ArrayList<Integer>> adj, int source){
        // bfs
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.size()];
        int[] paths = new int[adj.size()];
        Arrays.fill(paths,-1); // -1 for unreachable vertices from source
        q.add(source);
        visited[source] = true;
        paths[source] = 0;
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int x: adj.get(curr)){
                if(!visited[x]){
                    q.add(x);
                    visited[x] = true;
                    paths[x] = paths[curr] + 1; // x is adjacent of curr so 1 edge more distance from source
                }
            }
        }
        System.out.println(Arrays.toString(paths));
    }


    public static void shortestPathInUndirectedGraph2(ArrayList<ArrayList<Integer>> adj, int source){
        // levelOrderTraversal Line by Line
        // bfs line by line
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.size()];
        int[] paths = new int[adj.size()];
        Arrays.fill(paths,-1); // -1 for unreachable vertices from source
        q.add(source);
        visited[source] = true;
        int path = 0;
        while(!q.isEmpty()){
            int qSize = q.size();
            for(int i = 0;i < qSize; i++){
                int curr = q.poll();
                paths[curr] = path;
                for(int x: adj.get(curr)){
                    if(!visited[x]){
                        q.add(x);
                        visited[x] = true;
                    }
                }
            }
            path++;
        }
        System.out.println(Arrays.toString(paths));
    }

    public static void testShortestPathInUndirectedGraph(){
        ArrayList<ArrayList<Integer>> adj = getGraph(6);
        addUndirectedEdge(adj,0,1);
        addUndirectedEdge(adj,0,2);
        addUndirectedEdge(adj,0,4);
        addUndirectedEdge(adj,1,3);
        addUndirectedEdge(adj,2,3);
        addUndirectedEdge(adj,2,4);
        addUndirectedEdge(adj,3,5);
        addUndirectedEdge(adj,4,5);

        shortestPathInUndirectedGraph(adj,0);
        shortestPathInUndirectedGraph2(adj,0);
    }

    public static boolean cycleDetectionDFS(ArrayList<ArrayList<Integer>> adj,int source,
                                         int[] pathOccurrence, int travelled){
        if(pathOccurrence[source] != -1){
            // already occurred, so possibility of cycle.
            // cycle should at least contain 3 vertices
            // so distance travelled in path should be more than 2
            return travelled - pathOccurrence[source] > 2;
        }
        pathOccurrence[source] = travelled;
        for(int next: adj.get(source)){
            // short circuit
            if(cycleDetectionDFS(adj,next,pathOccurrence,travelled+1)) return true;
        }
        return false;
    }

    public static boolean detectCycleInUndirectedGraph(ArrayList<ArrayList<Integer>> adj){
        int[] pathOccurrence = new int[adj.size()];
        Arrays.fill(pathOccurrence,-1); // -1 for not occurred yet
        for(int source = 0; source<adj.size(); source++){
            if(pathOccurrence[source] == -1){
                // source of sub graph
                // path travelled is 0 for new source
                if(cycleDetectionDFS(adj,source,pathOccurrence,0)){
                    return true; // short circuit
                }
            }
        }
        return false;
    }

    public static boolean isCycleUtil(ArrayList<ArrayList<Integer>> adj, int source, boolean[] visited, int from){
        visited[source] = true;
        for(int next: adj.get(source)){
            if(!visited[next]){
                if(isCycleUtil(adj,next,visited,source)) return true;
            }
            else if (next != from) return true;
        }
        return false;
    }

    public static boolean detectCycleInUndirectedGraphAuthor(ArrayList<ArrayList<Integer>> adj){
        // In undirected graph, A component will be traversed in a single path as component means connected vertices
        // so visited boolean array holds path info as well, but it's not the case in directed graph
        boolean[] visitedPath = new boolean[adj.size()];
        for(int source = 0; source < adj.size(); source++){
            if(!visitedPath[source]){
                // searching cycle from source, so source is not called from anything. so from is -1 for source
                if(isCycleUtil(adj, source, visitedPath, -1)) return true;
            }
        }
        return false;
    }

    public static void testDetectCycleInUndirectedGraph(){
        ArrayList<ArrayList<Integer>> adj = getGraph(5);
        addUndirectedEdge(adj,0,1);
        addUndirectedEdge(adj,1,2);
        addUndirectedEdge(adj,1,4);
        addUndirectedEdge(adj,2,3);

        System.out.println(detectCycleInUndirectedGraph(adj));
        System.out.println(detectCycleInUndirectedGraphAuthor(adj));
    }

    public static boolean isCycleUtil(ArrayList<ArrayList<Integer>> adj, int source, boolean[] visited, boolean[] inCurrentPath){
        visited[source] = true;
        inCurrentPath[source] = true; // entering path
        for(int next: adj.get(source)){
            if(!visited[next]){
                if(isCycleUtil(adj,next,visited,inCurrentPath)) return true;
            }else if(inCurrentPath[next]){ // already in current path, BACK EDGE detected
                System.out.println("Back EDGE from %d to %d".formatted(source,next));
                return true;
            }else{
                // visited but not in currPath
                // so it's an CROSS EDGE, we can't decide yet
                System.out.println("Cross EDGE from %d to %d".formatted(source,next));
            }
        }
        inCurrentPath[source] = false; // leaving path
        return false;
    }

    public static boolean detectCycleInDirectedGraph(ArrayList<ArrayList<Integer>> adj){
        // in directed graph, visited and currentPath are different,
        // because in directed graph, when we backtrack visited vertices will not be in currentPath
        boolean[] visited = new boolean[adj.size()];
        boolean[] inCurrentPath = new boolean[adj.size()];
        for(int source = 0; source<adj.size(); source++){
            if(!visited[source]){
                if(isCycleUtil(adj,source,visited,inCurrentPath)) return true;
            }
        }
        return false;
    }

    public static void testDetectCycleInDirectedGraph(){
        ArrayList<ArrayList<Integer>> adj = getGraph(6);
        addDirectedEdge(adj,0,1);
        addDirectedEdge(adj,2,1);
        addDirectedEdge(adj,2,3);
        addDirectedEdge(adj,3,4);
        addDirectedEdge(adj,4,5);
        addDirectedEdge(adj,5,3);

        System.out.println(detectCycleInDirectedGraph(adj));
    }


    public static void calcInDegreesBFS(ArrayList<ArrayList<Integer>> adj, int[] inDegrees){
        // bfs
        boolean[] visited = new boolean[adj.size()];
        for(int source = 0; source<adj.size(); source++){
            if(!visited[source]){
                Queue<Integer> q = new LinkedList<>();
                q.add(source);
                visited[source] = true;
                while(!q.isEmpty()){
                    int curr = q.poll();
                    for(int neighbour : adj.get(curr)){
                        if(!visited[neighbour]){
                            q.add(neighbour);
                            visited[neighbour] = true;
                        }
                        inDegrees[neighbour]++;
                    }
                }
            }
        }
    }

    public static void calcInDegrees(ArrayList<ArrayList<Integer>> adj, int[] inDegrees){
        for(int source = 0; source<adj.size(); source++){
            for(int destination: adj.get(source)){
                inDegrees[destination]++;
            }
        }
    }
    public static void topologicalSortingBFS(ArrayList<ArrayList<Integer>> adj){
        ArrayList<Integer> topologicalSorting = new ArrayList<>();

        // calculating in-degrees initially
        int[] inDegrees = new int[adj.size()];
//        calcInDegreesBFS(adj,inDegrees);
        calcInDegrees(adj,inDegrees);
        System.out.println(Arrays.toString(inDegrees));

        Queue<Integer> q = new LinkedList<>();
        for(int source = 0; source<adj.size(); source++){
            if(inDegrees[source] == 0) q.add(source);
        }

        while(!q.isEmpty()){
            int curr = q.poll();
            topologicalSorting.add(curr);
            for(int neighbour: adj.get(curr)){
                // curr is one of the dependency, curr is processed, so 1 decrement
                inDegrees[neighbour]--;
                if(inDegrees[neighbour] == 0) q.add(neighbour); // 0 in-degree means no more dependency
            }
        }
        if(topologicalSorting.size() == adj.size()){
            // Topological sorting works only for acyclic graphs
            // so all vertices of acyclic graph will be processed in topological sorting
            System.out.println(topologicalSorting);
        }
        else{
            // all vertices are not processed so there is a cycle in graph
            System.out.println("cycle detected in graph");
        }
    }

    public static void dfsPostOrderTopological(ArrayList<ArrayList<Integer>> adj, int source, Stack<Integer> topologicalSorting, boolean[] visited){
        // postOrder
        visited[source] = true;
        for(int neighbour: adj.get(source)){
            if(!visited[neighbour]) dfsPostOrderTopological(adj,neighbour,topologicalSorting,visited);
        }
        topologicalSorting.add(source);
    }

    public static void topologicalSortingDFS(ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[adj.size()];
        Stack<Integer> topologicalSorting = new Stack<>();
        for(int source = 0; source<adj.size(); source++){
            if(!visited[source]) dfsPostOrderTopological(adj,source,topologicalSorting,visited);
        }
        while(!topologicalSorting.isEmpty()){
            System.out.print(topologicalSorting.pop()+" ");
        }
        System.out.println();
    }

    public static void testTopologicalSorting(){
        ArrayList<ArrayList<Integer>> adj = getGraph(5);
        addDirectedEdge(adj,0,2);
        addDirectedEdge(adj,0,3);
        addDirectedEdge(adj,2,3);
        addDirectedEdge(adj,1,3);
        addDirectedEdge(adj,1,4);

        topologicalSortingBFS(adj);
        topologicalSortingDFS(adj);
    }

    public static void dfsPostOrderTopologicalWeighted(ArrayList<ArrayList<Edge>> adj, int source, Stack<Integer> topologicalSorting, boolean[] visited){
        // postOrder
        visited[source] = true;
        for(Edge neighbour: adj.get(source)){
            if(!visited[neighbour.to]) dfsPostOrderTopologicalWeighted(adj,neighbour.to,topologicalSorting,visited);
        }
        topologicalSorting.add(source);
    }

    public static void shortestPathInWeightedDAG(ArrayList<ArrayList<Edge>> adj, int source){
        int[] dist = new int[adj.size()];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;
        boolean[] visited = new boolean[adj.size()];
        Stack<Integer> topologicalSorting = new Stack<>();
        for(int i = 0; i<adj.size(); i++){
            if(!visited[i]) dfsPostOrderTopologicalWeighted(adj,i,topologicalSorting,visited);
        }
        while(!topologicalSorting.isEmpty()){
            int currNode = topologicalSorting.pop();
            for(Edge neighbour: adj.get(currNode)){
                if(dist[neighbour.to] > dist[currNode] + neighbour.weight){
                    dist[neighbour.to] = dist[currNode] + neighbour.weight;
                }
            }
        }
        System.out.println(Arrays.toString(dist));
    }

    public static void bellmanFordShortestPathInWeightedDAG(ArrayList<ArrayList<Edge>> adj, int source){
        int v = adj.size();
        int[] dist = new int[v];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source] = 0;
        // relax all edges for v-1 times |v| is no of vertices
        for(int i = 0; i<v-1; i++){
            // going throuhg all edges
            for(int from = 0; from<v; from++){
                if(dist[from] == Integer.MAX_VALUE) continue;
                for(Edge going: adj.get(from)){
                    if(dist[going.to] > dist[from]+going.weight){
                        // relaxing
                        dist[going.to] = dist[from]+going.weight;
                    }
                }
            }
        }

        // by this, all vertices should have the shortest path from source
        // but we do one more traversal to confirm, but if any edge is again relaxing[updating]
        // then there is a negative cycle
        for(int from = 0; from<v; from++){
            if(dist[from] == Integer.MAX_VALUE) continue;
            for(Edge going: adj.get(from)){
                if(dist[going.to] > dist[from]+going.weight){
                    // negative cycle detected
                    System.out.println("Graph contains negative weight cycle");
                }
            }
        }
        System.out.println(Arrays.toString(dist));
    }

    public static void testShortestPathInWeightedDAG(){
        ArrayList<ArrayList<Edge>> adj = getWeightedGraph(4);
        addDirectedWeightedEdge(adj,0,1,1);
        addDirectedWeightedEdge(adj,1,2,3);
        addDirectedWeightedEdge(adj,2,3,4);
        addDirectedWeightedEdge(adj,1,3,2);

        shortestPathInWeightedDAG(adj,0);
        bellmanFordShortestPathInWeightedDAG(adj,0);
    }

    static class VertexDistance{
        int vertex;
        int distance;

        public VertexDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VertexDistance that = (VertexDistance) o;
            return vertex == that.vertex && distance == that.distance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex, distance);
        }
    }

    public static void shortestPathInUndirectedWeightedGraphDijkstra2(ArrayList<ArrayList<Edge>> adj, int source){
        // Dijkstra Algo [for All Shortest Path related Qs]
        // using adjacency list and TreeSet [ removes duplicate entries occured in priorityQueue algo below ]
        TreeSet<VertexDistance> ts = new TreeSet<>(Comparator.comparingInt(vd -> vd.distance));
        int[] shortestPathDistance = new int[adj.size()];
        Arrays.fill(shortestPathDistance,Integer.MAX_VALUE);
        shortestPathDistance[source] = 0; // 0 distance from source to source
        ts.add(new VertexDistance(source,shortestPathDistance[source]));
        while(!ts.isEmpty()){
            VertexDistance curr = ts.pollFirst();
            for(Edge neighbour: adj.get(curr.vertex)){
                if(curr.distance + neighbour.weight < shortestPathDistance[neighbour.to]){
                    if(shortestPathDistance[neighbour.to] < Integer.MAX_VALUE){
                        // already in treeSet once so removing
                        ts.remove(new VertexDistance(neighbour.to,shortestPathDistance[neighbour.to]));
                    }
                    shortestPathDistance[neighbour.to] = curr.distance + neighbour.weight;
                    ts.add(new VertexDistance(neighbour.to,shortestPathDistance[neighbour.to]));
                }
            }
        }
        System.out.println(Arrays.toString(shortestPathDistance));
    }

    /**
     * Dijkstra Algo is for all shortestPath Qs [ DIRECTED, UNDIRECTED, WEIGHTED, CYCLIC]
     * but it will not work if negative weights are there
     * @param adj adjacency list of GRAPH
     * @param source source vertex
     */
    public static void shortestPathInUndirectedWeightedGraphDijkstra(ArrayList<ArrayList<Edge>> adj, int source){
        // Dijkstra Algo [for All Shortest Path related Qs]
        // using adjacency list and min heap [priority queue]
        // minHeap here, allows duplicates which can be solved in treeset algo above
        PriorityQueue<VertexDistance> mnh = new PriorityQueue<>(Comparator.comparingInt(vd -> vd.distance)); // min heap
        int[] shortestPathDistance = new int[adj.size()];
        Arrays.fill(shortestPathDistance,Integer.MAX_VALUE);
        shortestPathDistance[source] = 0; // 0 distance from source to source
        mnh.add(new VertexDistance(source,shortestPathDistance[source]));
        while(!mnh.isEmpty()){
            VertexDistance curr = mnh.poll(); //vertex with min distance from source
            if(curr.distance > shortestPathDistance[curr.vertex]){
                // duplicate & greater distance
                // we already calculated shortestPathDistance for this vertex
                // so skipping waste iterations
                continue;
            }
            for(Edge neighbour: adj.get(curr.vertex)){
                if(curr.distance + neighbour.weight < shortestPathDistance[neighbour.to]){
                    shortestPathDistance[neighbour.to] = curr.distance + neighbour.weight;
                    mnh.add(new VertexDistance(neighbour.to,shortestPathDistance[neighbour.to]));
                }
            }
        }
        System.out.println(Arrays.toString(shortestPathDistance));
    }

    public static int minimumCostPathDijkstra(int[][] grid, int[] sourcePos, int[] targetPos){
        // Dijkstra Algo [for All Shortest Path related Qs]
        // using adjacency list and min heap [priority queue]
        // minHeap here, allows duplicates which can be solved in treeset algo
        // here List<Integer> will hold row,column,cost. minHeap on cost value
        int n = grid.length;
        int m = grid[0].length;
        // up down right left offsets
        int[][] udrl = {{-1,0},{1,0},{0,1},{0,-1}};
        PriorityQueue<List<Integer>> mnh = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.get(2)));
        int[][] minCost = new int[n][m];
        Arrays.stream(minCost).forEach(arr -> Arrays.fill(arr,Integer.MAX_VALUE));
        minCost[sourcePos[0]][sourcePos[1]] = grid[sourcePos[0]][sourcePos[1]];
        mnh.add(List.of(sourcePos[0],sourcePos[1],minCost[sourcePos[0]][sourcePos[1]]));
        while(!mnh.isEmpty()){
            List<Integer> currCell = mnh.poll();
            int r = currCell.get(0);
            int c = currCell.get(1);
            int cost = currCell.get(2);

            // we can immediately stop once we get r,c equal to targetPos
            // because 1st outing entry of this cell from minheap will be minimum cost
            if(r == targetPos[0] && c == targetPos[1]) return cost;

            if(cost > minCost[r][c]){
                // duplicate & greater cost
                // we already calculated minCost for this cell
                // so skipping waste iterations
                continue;
            }

            for(int[] offSet: udrl){
                int r2 = r+offSet[0], c2 = c+offSet[1];
                // handling out of bound cases
                if(r2 < 0 || r2 >= n || c2 < 0 || c2 >= m) continue;
                if(cost+grid[r2][c2] < minCost[r2][c2]){
                    minCost[r2][c2] = cost+grid[r2][c2];
                    mnh.add(List.of(r2,c2,minCost[r2][c2]));
                }
            }
        }
        return minCost[targetPos[0]][targetPos[1]];
    }

    public static void testShortestPathInUndirectedWeightedGraph(){
        ArrayList<ArrayList<Edge>> adj = getWeightedGraph(4);
        addUndirectedWeightedEdge(adj,0,1,50);
        addUndirectedWeightedEdge(adj,0,2,100);
        addUndirectedWeightedEdge(adj,1,2,30);
        addUndirectedWeightedEdge(adj,1,3,200);
        addUndirectedWeightedEdge(adj,2,3,20);

        shortestPathInUndirectedWeightedGraphDijkstra(adj,0);
        shortestPathInUndirectedWeightedGraphDijkstra2(adj,0);

        int[][] grid = {{9,4,9,9},{6,7,6,4},
                {8,3,3,7},{7,4,9,10}};
        int n = grid.length, m = grid[0].length;
        int[] sourcePos = {0,0};
        int[] targetPos = {n-1,m-1};
        System.out.println(minimumCostPathDijkstra(grid,sourcePos,targetPos));
        System.out.println();
    }

    public static int primsMST(int[][] adjMatrix){
        int v = adjMatrix.length;
        int[] minWeights = new int[v]; // min weights connecting each vertex, INFINITY means not yet reached
        Arrays.fill(minWeights,Integer.MAX_VALUE);
        minWeights[0] = 0;
        boolean[] inMST = new boolean[v]; // initially no vertex is in MST
        int ans = 0; // sum of MST edges
        for(int i = 0; i<v; i++){
            int minWeightIdx = -1;
            for(int j = 0; j<v; j++){
                if(!inMST[j] && (minWeightIdx == -1 || minWeights[j] < minWeights[minWeightIdx]))
                    minWeightIdx = j;
            }

            inMST[minWeightIdx] = true;
            ans += minWeights[minWeightIdx];
            for(int k = 0; k<v; k++){
                if(!inMST[k] && adjMatrix[minWeightIdx][k] != 0 && adjMatrix[minWeightIdx][k] < minWeights[k]){
                    minWeights[k] = adjMatrix[minWeightIdx][k];
                }
            }
        }
        return ans;
    }

    public static int primsMST(ArrayList<ArrayList<Edge>> adj){
        // Prims Algo [for All Minimum spanning tree related Qs]
        // using adjacency list and min heap [priority queue]
        // minHeap here, allows duplicates which can be solved using treeset
        int v = adj.size();
        PriorityQueue<Edge> mnh = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        int[] minWeight = new int[v]; // min weights connecting each vertex, INFINITY means not yet reached
        Arrays.fill(minWeight,Integer.MAX_VALUE);
        int source = 0; // we can take any vertex
        minWeight[source] = 0; // source to source weight(distance) is 0

        int[] parent = new int[v];
        Arrays.fill(parent,-1); // just to store info of edges in MST, we leave -1 as parent for source

        boolean[] inMST = new boolean[v]; // initially no vertex is in MST
        int ans = 0; // sum of MST edges

        mnh.add(new Edge(source,minWeight[source])); // source to source edge. not there practically
        while(!mnh.isEmpty()){
            // for each vertex, minimum incoming edge will be in MST
            // we get such minimum incoming edge to a vertex from minHeap(mnh)
            Edge curr = mnh.poll();

            if(inMST[curr.to]){
                // already in MST, so this is duplicate & greater weight edge,
                // we already included min-weight edge for this vertex in MST
                // so skipping this unnecessary iterations
                continue;
            }

            inMST[curr.to] = true; // vertex included in MST
            ans += curr.weight;

            for(Edge link: adj.get(curr.to)){
                if(!inMST[link.to] && link.weight < minWeight[link.to]){
                    minWeight[link.to] = link.weight;
                    parent[link.to] = curr.to;
                    mnh.add(link);
                }
            }
        }

        System.out.println("Printing edges of MST");
        for(int i = 1; i<v; i++){
            System.out.println(parent[i] + " to " + i);
        }
        return ans;
    }

    public static int kruskalsMST(ArrayList<GraphEdge> graphEdges, int v){
        graphEdges.sort(Comparator.comparingInt(edge -> edge.weight));
        boolean[] inMST = new boolean[v];
        int edgeCnt = 0; // we need v-1 edges for a spanning tree
        int sum = 0;
        ArrayList<GraphEdge> mstEdges = new ArrayList<>();
        for(GraphEdge edge: graphEdges){
            if(inMST[edge.from] && inMST[edge.to]){
                // both vertices already connected in MST so no need to consider this edge
                // this will create a cycle, and it's definitely unnecessary
                continue;
            }
            // so from or to or both not in MST, so taking this edge as it's minimum as it's sorted
            inMST[edge.from] = true;
            inMST[edge.to] = true;
            sum += edge.weight;
            mstEdges.add(edge);
            edgeCnt++;
            if(edgeCnt == v-1) break; // no need to take any further edges
        }

        System.out.println("Printing edges of MST");
        for(GraphEdge edge: mstEdges){
            System.out.println(edge.from +" --- "+ edge.to +" : " + edge.weight);
        }

        return sum;
    }

    /**
     * Spanning Tree is a graph made from given undirected weighted graph.
     * Spanning Tree should connect all the |v| vertices using |v|-1 edges without cycles. TREE = NO CYCLE
     * Minimum Spanning Tree is one among all possible spanning trees which has sum of edges minimum
     */
    public static void testMinimumSpanningTree(){
        int v = 4; // number of vertices in adjMatrix
        int[][] adjMatrix = new int[v][v]; // v x v adjMatrix
        addUndirectedWeightedEdge(adjMatrix,0,1,5);
        addUndirectedWeightedEdge(adjMatrix,0,2,8);
        addUndirectedWeightedEdge(adjMatrix,1,2,10);
        addUndirectedWeightedEdge(adjMatrix,1,3,15);
        addUndirectedWeightedEdge(adjMatrix,2,3,20);
        for(int i = 0; i<v; i++) System.out.println(Arrays.toString(adjMatrix[i]));
        System.out.println(primsMST(adjMatrix));

        ArrayList<ArrayList<Edge>> adj = getWeightedGraph(v);
        addUndirectedWeightedEdge(adj,0,1,5);
        addUndirectedWeightedEdge(adj,0,2,8);
        addUndirectedWeightedEdge(adj,1,2,10);
        addUndirectedWeightedEdge(adj,1,3,15);
        addUndirectedWeightedEdge(adj,2,3,20);
        System.out.println(primsMST(adj));

        // KRUSHKALS ALGO FOR MST
        ArrayList<GraphEdge> graphEdges = new ArrayList<>(); // graphEdges is list of all edges
        int n = 5;
        graphEdges.add(new GraphEdge(0,1,6));
        graphEdges.add(new GraphEdge(0,2,5));
        graphEdges.add(new GraphEdge(1,2,3));
        graphEdges.add(new GraphEdge(2,3,7));
        graphEdges.add(new GraphEdge(1,3,8));
        graphEdges.add(new GraphEdge(3,4,10));
        graphEdges.add(new GraphEdge(2,4,12));

        System.out.println(kruskalsMST(graphEdges,n));



    }

    public static void postOrderDFS(ArrayList<ArrayList<Integer>> adj, int source, Stack<Integer> finishingOrder, boolean[] visited){
        // post order dfs so source will be processed after all its adjacent are processed
        visited[source] = true;
        for(int neighbour: adj.get(source)){
            if(!visited[neighbour]) postOrderDFS(adj,neighbour,finishingOrder,visited);
        }
        finishingOrder.add(source);
    }

    public static ArrayList<ArrayList<Integer>> reverseEdges(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> reverseAdj = getGraph(adj.size());
        for(int i = 0; i<adj.size(); i++){
            for(int j: adj.get(i)){
                // means we have i to j edge
                reverseAdj.get(j).add(i); // making j to i edge
            }
        }

        return reverseAdj;
    }

    public static void preOrderDFS(ArrayList<ArrayList<Integer>> adj, int source, boolean[] visited){
        // pre-order dfs so source will be processed before all its adjacent are processed
        visited[source] = true;
        System.out.print(source+" ");
        for(int neighbour: adj.get(source)){
            if(!visited[neighbour]) preOrderDFS(adj,neighbour,visited);
        }
    }

    public static void stronglyConnectedComponentsKosarajuAlgo(ArrayList<ArrayList<Integer>> adj){
        // we come from sink component to source component
        // topological sorting of directed graph DFS
        boolean[] visited = new boolean[adj.size()];
        Stack<Integer> topologicalSorting = new Stack<>(); // vertices with decreasing order of finish time
        for(int source = 0; source<adj.size(); source++){
            if(!visited[source]) postOrderDFS(adj,source,topologicalSorting,visited);
        }
//        System.out.println(topologicalSorting);
        // strongly connected components will stay strongly connected even after reversingEdges of graph
        ArrayList<ArrayList<Integer>> reverseAdj = reverseEdges(adj);
//        System.out.println(reverseAdj);
        Arrays.fill(visited,false); // re-initiating to false
        while(!topologicalSorting.isEmpty()){
            int currSource = topologicalSorting.pop();
            if(!visited[currSource]){
                preOrderDFS(reverseAdj,currSource,visited);
                System.out.println(); // new line to differentiate strongly connected components
            }
        }
    }

    public static void testStronglyConnectedComponentsKosarajuAlgo(){
        ArrayList<ArrayList<Integer>> adj = getGraph(6);
        addDirectedEdge(adj,0,1);
        addDirectedEdge(adj,1,2);
        addDirectedEdge(adj,2,3);
        addDirectedEdge(adj,3,4);
        addDirectedEdge(adj,4,5);
        addDirectedEdge(adj,5,4);
        addDirectedEdge(adj,3,0);
        stronglyConnectedComponentsKosarajuAlgo(adj);
    }

    /**
     * Manoj's logic to check if there is backEdge parent children of source to any of source ancestors in pathOrder
     * @param adj adjacency list representation of GRAPH
     * @param source current vertex being processed
     * @param parent parent vertex that called source
     * @param visited visited array to store info of so far visited
     * @param pathOrder indexed path of current path vertices
     * @return ancestor vertex of maximum backEdge -> out of all backEdge which connects to the earliest ancestor
     */
    public static int getBackEdge(ArrayList<ArrayList<Integer>> adj, int source, int parent, boolean[] visited, int[] pathOrder){
        visited[source] = true; // will have info union of all paths so far visited
        if(parent == -1){
            // source is root
            pathOrder[source] = 0; // starting index is 0, entering path
        }else{
            pathOrder[source] = pathOrder[parent] + 1; // entering path
        }
        int maxBackEdgeTo = -1; // -1 means no backedge
        boolean articulationPoint = false; // will become true in case source is articulation point
        for(int next: adj.get(source)){
            if(pathOrder[next] == -1){
                // next is not in current path
                int backEdge = getBackEdge(adj,next,source,visited,pathOrder);
                if(backEdge != -1 && pathOrder[backEdge] < pathOrder[source]){
                    // backEdge from children of SOURCE to SOURCE ANCESTORS
                    if(maxBackEdgeTo == -1) maxBackEdgeTo = backEdge;
                    else maxBackEdgeTo = (pathOrder[maxBackEdgeTo] < pathOrder[backEdge]) ? maxBackEdgeTo : backEdge;
                }
                else if(parent != -1){
                    articulationPoint = true;
                }
            }
            else if(next != parent){
                // BACK EDGE FROM SOURCE to one of the ancestors of PARENT
                // next is there in current path and next != parent
                /*
                *       ---------------------------------
                *      |                                 |
                *   ANCESTOR  -----  PARENT  -----  SOURCE
                * */
                if(maxBackEdgeTo == -1) maxBackEdgeTo = next;
                else maxBackEdgeTo = (pathOrder[maxBackEdgeTo] < pathOrder[next]) ? maxBackEdgeTo : next;
            }
        }
        if(articulationPoint) System.out.print(source+" ");
        return maxBackEdgeTo;
    }


    public static void printArticulationPoints(ArrayList<ArrayList<Integer>> adj){
        System.out.println("Articulation Points: ");
        boolean[] visited = new boolean[adj.size()];
        // pathOrder will store currentPathNodes and order of Nodes in path with their increasing visiting index
        int[] pathOrder = new int[adj.size()];
        Arrays.fill(pathOrder,-1); // -1 means nothing in path currently
        for(int source = 0; source < adj.size(); source++){
            if(!visited[source]){
                // if graph is multiple components,
                // we print articulation points of each component that starts with current source
                getBackEdge(adj,source,-1,visited,pathOrder);
            }
        }
    }

    public static void articulationPointsTarjanDFS(ArrayList<ArrayList<Integer>> adj, int source, int parent, int[] visitingOrder, int[] reachableMaxBackEdge, int[] rank){
        // Even though GRAPH is undirected[bidirectional], we choose direction of edge based on DFS TREE
        int childrenCnt = 0;
        boolean articulationPoint = false;
        visitingOrder[source] = rank[0];
        reachableMaxBackEdge[source] = rank[0]; // source can reach itself
        rank[0]++; // increasing index to assign to next unvisited node
        for(int child: adj.get(source)){
            // iterating over children of SOURCE
            if(child == parent){
                // since undirected[Bidirectional] source can reach parent with same edge,
                // but in dfs we should go in depth, should not go up in this way, so neglecting
                continue;
            }
            if(visitingOrder[child] == 0){
                childrenCnt++; // unvisited children count is required for root
                // unvisited node, our starting rank is 1, so 0 means unvisited
                articulationPointsTarjanDFS(adj,child,source,visitingOrder,reachableMaxBackEdge,rank);
                if(reachableMaxBackEdge[child] < reachableMaxBackEdge[source]){
                    // child has backEdge, so source has inDirectBackEdge throught this child
                    // so source can reach same lowest rank through child [dfs -> going through depth only]
                    reachableMaxBackEdge[source] = reachableMaxBackEdge[child];
                }

                if(parent != -1 && reachableMaxBackEdge[child] >= visitingOrder[source]){
                    // so child can not reach back any of SOURCE ANCESTORS in DFS
                    // so source is an articulation point
                    articulationPoint = true;
                }
            }
            else{
                // child is already visited which is not parent, so BACKEDGE
                if(visitingOrder[child] < reachableMaxBackEdge[source]){
                    // already visited child which has lower visiting rank than reachableMaxBackEdge[source]
                    // but we should not consider reachableMaxBackEdge[child] as reachableMaxBackEdge[source]
                    // why because source can not reach reachableMaxBackEdge[child] if child is removed
                    reachableMaxBackEdge[source] = visitingOrder[child];
                }
            }
        }

        if(parent == -1 && childrenCnt > 1){
            // source is root of this DFS TREE,
            // so if it has multiple unvisited children it is an articulation point
            articulationPoint = true;
        }

        if(articulationPoint) System.out.print(source+" ");
    }

    public static void articulationPointsInUndirectedGraphTarjansAlgo(ArrayList<ArrayList<Integer>> adj){
        System.out.println("\nArticulation Points: ");
        int v = adj.size();
        int[] rank = {1}; // starting rank 1, we can use static variable also, but i am using reference
        // visitingOrder is not currentPath, it's overall path, we give increasing rank to unvisited node
        int[] visitingOrder = new int[v]; // 0 means unvisited
        int[] reachableMaxBackEdge = new int[v];

        // if graph is connected, all vertices will be visited once we start with any vertex as source
        // articulationPointsTarjanDFS(adj,0,-1,visitingOrder,reachableMaxBackEdge,rank);

        for(int source = 0; source < v; source++){
            // works for both disconnected and connected graph
            if(visitingOrder[source] == 0){
                articulationPointsTarjanDFS(adj,source,-1,visitingOrder,reachableMaxBackEdge,rank);
            }
        }

    }

    public static void testArticulationPoints(){
        ArrayList<ArrayList<Integer>> adj = getGraph(7);
        addUndirectedEdge(adj,0,1);
        addUndirectedEdge(adj,0,3);
        addUndirectedEdge(adj,1,2);
        addUndirectedEdge(adj,1,4);
        addUndirectedEdge(adj,2,3);
        addUndirectedEdge(adj,4,5);
        addUndirectedEdge(adj,4,6);
        addUndirectedEdge(adj,5,6);

        printArticulationPoints(adj);
        articulationPointsInUndirectedGraphTarjansAlgo(adj);
    }

    public static void bridgesTarjanDFS(ArrayList<ArrayList<Integer>> adj, int source, int parent, int[] visitingOrder, int[] reachableLowestRank, int[] rank){
        // Even though GRAPH is undirected[bidirectional], we choose direction of edge based on DFS TREE
        visitingOrder[source] = rank[0];
        reachableLowestRank[source] = rank[0]; // source can reach itself
        rank[0]++; // increasing index to assign to next unvisited node
        for(int child: adj.get(source)){
            // iterating over children of SOURCE
            if(child == parent){
                // since undirected[Bidirectional] source can reach parent with same edge,
                // but in dfs we should go in depth, should not go up in this way, so neglecting
                continue;
            }
            if(visitingOrder[child] == 0){
                // unvisited node, our starting rank is 1, so 0 means unvisited
                bridgesTarjanDFS(adj,child,source,visitingOrder,reachableLowestRank,rank);
                if(reachableLowestRank[child] < reachableLowestRank[source]){
                    // child is able to reach the lowest rank,
                    // so source can reach same lowest rank through child [dfs -> going through depth only]
                    reachableLowestRank[source] = reachableLowestRank[child];
                }

                if(reachableLowestRank[child] > visitingOrder[source]){
                    // so child can not reach back source in DFS
                    // so source to child is a bridge
                    System.out.println(source + " --- " + child);
                }
            }
            else{
                // child is already visited
                if(reachableLowestRank[child] < reachableLowestRank[source]){
                    // child is able to reach the lowest rank,
                    // so source can reach same lowest rank through child [dfs -> going through depth only]
                    reachableLowestRank[source] = reachableLowestRank[child];
                }
            }
        }
    }

    public static void bridgesInUndirectedGraphTarjansAlgo(ArrayList<ArrayList<Integer>> adj){
        int v = adj.size();
        int[] rank = {1}; // starting rank 1, we can use static variable also, but i am using reference
        // visitingOrder is not currentPath, it's overall path, we give increasing rank to unvisited node
        int[] visitingOrder = new int[v]; // 0 means unvisited
        int[] reachableLowestRank = new int[v];

        // if graph is connected, all vertices will be visited once we start with any vertex as source
        //bridgesTarjanDFS(adj,0,-1,visitingOrder,reachableLowestRank,rank);

        for(int source = 0; source < v; source++){
            // works for both disconnected and connected graph
            if(visitingOrder[source] == 0){
                bridgesTarjanDFS(adj,source,-1,visitingOrder,reachableLowestRank,rank);
            }
        }
    }

    public static void testBridgesInUndirectedGraph(){
        ArrayList<ArrayList<Integer>> adj = getGraph(5);
        addUndirectedEdge(adj,1,0);
        addUndirectedEdge(adj,0,2);
        addUndirectedEdge(adj,2,1);
        addUndirectedEdge(adj,0,3);
        addUndirectedEdge(adj,3,4);

        bridgesInUndirectedGraphTarjansAlgo(adj);
    }

    public static void SCCTarjanDFS(ArrayList<ArrayList<Integer>> adj, int source, int[] visitingOrder, int[] reachableLowestRankInSCC, int[] rank, boolean[] inSCC, Stack<Integer> stackSCC){
        visitingOrder[source] = rank[0];
        reachableLowestRankInSCC[source] = rank[0];
        rank[0]++;

        inSCC[source] = true;
        stackSCC.push(source);

        for(int friend: adj.get(source)){
            if(visitingOrder[friend] == 0){
                // unvisited node
                SCCTarjanDFS(adj,friend,visitingOrder,reachableLowestRankInSCC,rank,inSCC,stackSCC);

                if(reachableLowestRankInSCC[friend] < reachableLowestRankInSCC[source]){
                    // source can reach lowest rank in the current SCC through this friend
                    reachableLowestRankInSCC[source] = reachableLowestRankInSCC[friend];
                }
            }
            else if(inSCC[friend] == true){
                // friend is already visited in current SCC
                if(reachableLowestRankInSCC[friend] < reachableLowestRankInSCC[source]){
                    // source can reach lowest rank in the current SCC through this friend
                    reachableLowestRankInSCC[source] = reachableLowestRankInSCC[friend];
                }
            }
        }

        if(reachableLowestRankInSCC[source] == visitingOrder[source]){
            // source is the root[starting node in this DFS] of current SCC
            // printing scc component that starts with this source
            int curr;
            do{
                curr = stackSCC.pop();
                System.out.print(curr+" ");
                inSCC[curr] = false;
            }while(curr != source);
            System.out.println();
        }

    }

    public static void stronglyConnectedComponentsInDirectedGraphTarjansAlgo(ArrayList<ArrayList<Integer>> adj){
        int v = adj.size();
        int[] rank = {1}; // starting rank 1, we can use static variable also, but i am using reference
        // visitingOrder is not currentPath, it's overall path, we give increasing rank to unvisited node
        int[] visitingOrder = new int[v]; // 0 means unvisited
        int[] reachableLowestRankInSCC = new int[v];
        boolean[] inSCC = new boolean[v];
        Stack<Integer> stackSCC = new Stack<>();

        for(int source = 0; source < v; source++){
            if(visitingOrder[source] == 0){
                // not visited
                SCCTarjanDFS(adj,source,visitingOrder,reachableLowestRankInSCC,rank,inSCC,stackSCC);
            }
        }
    }

    public static void testStronglyConnectedComponentsInDirectedGraphTarjansAlgo(){
        System.out.println("\nStrongly Connected Components:");
        ArrayList<ArrayList<Integer>> adj = getGraph(5);
        addDirectedEdge(adj,0,2);
        addDirectedEdge(adj,0,1);
        addDirectedEdge(adj,1,2);
        addDirectedEdge(adj,1,3);
        addDirectedEdge(adj,3,4);
        addDirectedEdge(adj,4,1);

        stronglyConnectedComponentsInDirectedGraphTarjansAlgo(adj);
    }

    public static void islandDFS(int[][] matrix, int r, int c, boolean[][] visited){
        visited[r][c] = true;
        int n = matrix.length;
        int m = matrix[0].length;
        // adjacent 1s in surrounding 8 indexes are connecting to this matrix[r][c] element
        // rOff = row offset, cOff = column offset
        for(int rOff = -1; rOff <= 1; rOff++){
            if(r+rOff < 0 || r+rOff >= n){
                // out of range
                continue;
            }
            for(int cOff = -1; cOff <= 1; cOff++){
                if(rOff == 0 && cOff == 0){
                    // current element only : r+rOff = r, c+cOff = c
                    continue;
                }
                if(c+cOff < 0 || c+cOff >= m){
                    // out of range
                    continue;
                }
                if(matrix[r+rOff][c+cOff] == 1 && !visited[r+rOff][c+cOff]){
                    islandDFS(matrix,r+rOff,c+cOff,visited);
                }
            }
        }
    }

    public static int countIslands(int[][] matrix){
        // group of connected 1s is an island
        int cnt = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] visited = new boolean[n][m];
        for(int r = 0; r<n; r++){
            for(int c = 0; c<m; c++){
                if(matrix[r][c] == 1 && !visited[r][c]){
                    islandDFS(matrix,r,c,visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void testCountIslands(){
        int[][] matrix = {{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}};
        System.out.println(countIslands(matrix));
    }

    public static void main(String[] args) {
        testGraphAdjList();
        testShortestPathInUndirectedGraph();
        testDetectCycleInUndirectedGraph();
        testDetectCycleInDirectedGraph();
        testTopologicalSorting();
        testShortestPathInWeightedDAG();
        testMinimumSpanningTree();
        testShortestPathInUndirectedWeightedGraph();
        testStronglyConnectedComponentsKosarajuAlgo();
        testBridgesInUndirectedGraph();
        testArticulationPoints();
        testStronglyConnectedComponentsInDirectedGraphTarjansAlgo();
        testCountIslands();
    }
}
