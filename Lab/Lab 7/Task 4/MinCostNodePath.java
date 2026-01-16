import java.io.*;
import java.util.*;

public class MinCostNodePath 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read N, M, S, D
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // Number of vertices
        int M = Integer.parseInt(st.nextToken()); // Number of edges
        int S = Integer.parseInt(st.nextToken()); // Source
        int D = Integer.parseInt(st.nextToken()); // Destination
        
        // Use a large constant for "infinity"
        long INF = Long.MAX_VALUE; 

        // --- 1. Read Node Weights ---
        long[] nodeWeights = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) 
        {
            nodeWeights[i] = Long.parseLong(st.nextToken());
        }

        // --- 2. Build Adjacency List ---
        
        // adj[u] -> list of neighbors v
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // Read M edges (u -> v)
        for (int i = 0; i < M; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            adj[u].add(v);
        }

        // --- 3. Modified Dijkstra Initialization ---
        
        // minCost[i] stores the minimum path cost (sum of node weights) from S to i
        long[] minCost = new long[N + 1];
        Arrays.fill(minCost, INF); 
        
        // Cost to reach the source node S is its own weight
        minCost[S] = nodeWeights[S]; 

        // PriorityQueue stores long array: {cost, node_id}
        // Comparator is defined inline to sort by the cost (index 0).
        PriorityQueue<long[]> pq = new PriorityQueue<>
        (
            new Comparator<long[]>() 
            {
                public int compare(long[] a, long[] b) 
                {
                    // Compare based on the cost (index 0)
                    return Long.compare(a[0], b[0]);
                }
            }
        );
        
        // Add source: {cost=W[S], id=S}
        pq.add(new long[]{nodeWeights[S], S});

        // --- 4. Modified Dijkstra Execution ---
        
        while (!pq.isEmpty()) 
        {
            long[] current = pq.remove();
            long cost_u = current[0];
            int u = (int) current[1];

            ArrayList<Integer> neighbors = adj[u];
            
            for (int i = 0; i < neighbors.size(); i++) 
            {
                int v = neighbors.get(i);
                
                // The "weight" of the edge u -> v is the weight of the destination node, W[v]
                long cost_v_node = nodeWeights[v];

                // New path cost: (min cost to u) + (weight of node v)
                long new_cost = cost_u + cost_v_node;

                if (new_cost < minCost[v]) 
                {
                    minCost[v] = new_cost;
                    // Add new entry for neighbor: {new_cost, v}
                    pq.add(new long[]{new_cost, v});
                }
            }
        }

        // --- 5. Output Result ---
        
        if (minCost[D] == INF) 
        {
            System.out.println("-1");
        } 
        else 
        {
            System.out.println(minCost[D]);
        }
    }
}