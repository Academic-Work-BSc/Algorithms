import java.io.*;
import java.util.*;

public class SecondShortestPath 
{
    static class Edge 
    {
        int to;
        int weight;

        public Edge(int to, int weight) 
        {
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read N, M, S, D
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken()); // Source
        int D = Integer.parseInt(st.nextToken()); // Destination
        
        long INF = Long.MAX_VALUE; 

        // --- 1. Build Adjacency List (Bi-directional) ---
        
        ArrayList<Edge>[] adj = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // Read M edges (u, v, w)
        for (int i = 0; i < M; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            // Road is bi-directional
            adj[u].add(new Edge(v, w));
            adj[v].add(new Edge(u, w));
        }

        // --- 2. Dijkstra Initialization ---
        
        // distance[u][0]: shortest path (dist1)
        // distance[u][1]: strictly second shortest path (dist2)
        long[][] distance = new long[N + 1][2];
        for (int i = 1; i <= N; i++) 
        {
            Arrays.fill(distance[i], INF);
        }

        // PriorityQueue stores long array: {cost, node_id}
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
        
        // Start: Cost to reach S is 0 for the shortest path
        distance[S][0] = 0; 
        pq.add(new long[]{0, S}); // {cost, id}

        // --- 3. Modified Dijkstra Execution ---
        
        while (!pq.isEmpty())
        {
            long[] current = pq.remove();
            long cost_u = current[0]; // Cost to reach u
            int u = (int) current[1];

            ArrayList<Edge> neighbors = adj[u];
            for (int i = 0; i < neighbors.size(); i++) 
            {
                Edge edge = neighbors.get(i);
                int v = edge.to;
                int w = edge.weight;

                long new_cost = cost_u + w;
                
                // Case 1: New path is shorter than the current shortest path (dist1[v])
                if (new_cost < distance[v][0]) 
                {
                    // Old dist1[v] becomes the new dist2[v]
                    distance[v][1] = distance[v][0];
                    // New cost becomes the new dist1[v]
                    distance[v][0] = new_cost;
                    
                    // Add new dist1 and the former dist1 (now dist2) to PQ
                    pq.add(new long[]{distance[v][0], v});
                    
                    if (distance[v][1] != INF) 
                    {
                        pq.add(new long[]{distance[v][1], v});
                    }
                }
                
                // Case 2: New path is strictly between dist1[v] and dist2[v]
                else if (new_cost < distance[v][1]) 
                {
                    // New cost becomes the new dist2[v]
                    distance[v][1] = new_cost;
                    pq.add(new long[]{distance[v][1], v});
                }
            }
        }

        // --- 4. Output Result ---
        
        if (distance[D][1] == INF) 
        {
            System.out.println("-1");
        } 
        else 
        {
            System.out.println(distance[D][1]);
        }
    }
}
