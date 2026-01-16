import java.io.*;
import java.util.*;
 
public class SecondBestMST 
{
    static class Edge 
    {
        int u;
        int v;
        int weight;
 
        public Edge(int u, int v, int weight) 
        {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }
 
    static class Node 
    {
        int to;
        int weight;
 
        public Node(int to, int weight) 
        {
            this.to = to;
            this.weight = weight;
        }
    }
 
    static int[] parent;
 
    public static int find(int i) 
    {
        if (parent[i] == i) 
        {
            return i;
        }
        parent[i] = find(parent[i]);
        return parent[i];
    }
 
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
 
        Edge[] allEdges = new Edge[M];
        for (int i = 0; i < M; i = i + 1) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            allEdges[i] = new Edge(u, v, w);
        }
 
        // --- 1. Find Initial MST ---
        
        Arrays.sort(allEdges, new Comparator<Edge>() 
        {
            public int compare(Edge a, Edge b) 
            {
                return Integer.compare(a.weight, b.weight);
            }
        });
 
        parent = new int[N + 1];
        for (int i = 1; i <= N; i = i + 1) 
        {
            parent[i] = i;
        }
 
        ArrayList<Node>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i = i + 1) 
        {
            adj[i] = new ArrayList<>();
        }
 
        long mstWeight = 0;
        int edgesInMst = 0;
        boolean[] usedInMst = new boolean[M];
 
        for (int i = 0; i < M; i = i + 1) 
        {
            int rootU = find(allEdges[i].u);
            int rootV = find(allEdges[i].v);
 
            if (rootU != rootV) 
            {
                parent[rootU] = rootV;
                mstWeight = mstWeight + (long) allEdges[i].weight;
                usedInMst[i] = true;
                edgesInMst = edgesInMst + 1;
                
                adj[allEdges[i].u].add(new Node(allEdges[i].v, allEdges[i].weight));
                adj[allEdges[i].v].add(new Node(allEdges[i].u, allEdges[i].weight));
            }
        }
 
        if (edgesInMst != N - 1) 
        {
            System.out.println("-1");
            return;
        }
 
        // --- 2. Evaluate Every Possible Single-Edge Swap ---
        
        long secondBest = Long.MAX_VALUE;
 
        for (int i = 0; i < M; i = i + 1) 
        {
            // We only look at edges NOT in the MST
            if (usedInMst[i] == true) 
            {
                continue;
            }
 
            // BFS to find all edge weights on the path between u and v in the MST
            int startNode = allEdges[i].u;
            int targetNode = allEdges[i].v;
 
            int[] prevNode = new int[N + 1];
            int[] edgeWeightToPrev = new int[N + 1];
            boolean[] visited = new boolean[N + 1];
            
            Queue<Integer> q = new LinkedList<>();
            q.add(startNode);
            visited[startNode] = true;
 
            while (!q.isEmpty()) 
            {
                int curr = q.remove();
                if (curr == targetNode)
                {
                    break;
                }
 
                for (int j = 0; j < adj[curr].size(); j = j + 1) 
                {
                    Node neighbor = adj[curr].get(j);
                    
                    if (visited[neighbor.to] == false) 
                    {
                        visited[neighbor.to] = true;
                        prevNode[neighbor.to] = curr;
                        edgeWeightToPrev[neighbor.to] = neighbor.weight;
                        q.add(neighbor.to);
                    }
                }
            }
 
            // Backtrack from target to start to get all weights in the cycle
            int backtrack = targetNode;
            while (backtrack != startNode) 
            {
                int weightToRemove = edgeWeightToPrev[backtrack];
                long currentTotal = mstWeight - (long) weightToRemove + (long) allEdges[i].weight;
 
                // Check if this new tree is strictly greater than the original
                if (currentTotal > mstWeight) 
                {
                    if (currentTotal < secondBest) 
                    {
                        secondBest = currentTotal;
                    }
                }
                
                backtrack = prevNode[backtrack];
            }
        }
 
        // --- 3. Output Result ---
        
        if (secondBest == Long.MAX_VALUE) 
        {
            System.out.println("-1");
        } 
        else 
        {
            System.out.println(secondBest);
        }
    }
}