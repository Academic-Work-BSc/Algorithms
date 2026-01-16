import java.io.*;
import java.util.*;

public class ShortestPath
{
    // Class to represent an edge (neighbor and weight)
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
        int N = Integer.parseInt(st.nextToken()); // Number of vertices
        int M = Integer.parseInt(st.nextToken()); // Number of edges
        int S = Integer.parseInt(st.nextToken()); // Source
        int D = Integer.parseInt(st.nextToken()); // Destination

        // Read edges data (Input processing remains the same)
        int[] U = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) 
        {
            U[i] = Integer.parseInt(st.nextToken());
        }

        int[] V = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) 
        {
            V[i] = Integer.parseInt(st.nextToken());
        }

        int[] W = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) 
        {
            W[i] = Integer.parseInt(st.nextToken());
        }

        // --- 1. Build Adjacency List ---
        
        ArrayList<Edge>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) 
        {
            adj[U[i]].add(new Edge(V[i], W[i]));
        }

        // --- 2. Dijkstra Initialization ---
        
        long[] distance = new long[N + 1];
        int[] parent = new int[N + 1]; 
        
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[S] = 0;

        // PriorityQueue stores a long array: {distance, node_id}
        // Distance must be first for the Comparator to work on the 0th index.
        PriorityQueue<long[]> pq = new PriorityQueue<>
        (
            new Comparator<long[]>() 
            {
                public int compare(long[] a, long[] b) 
                {
                    // Compare based on the distance (index 0)
                    return Long.compare(a[0], b[0]);
                }
            }
        );
        
        // Add source: {distance=0, id=S}
        pq.add(new long[]{0, S});

        // --- 3. Dijkstra Execution ---
        
        while (!pq.isEmpty()) 
        {
            long[] current = pq.remove();
            long dist_u = current[0];
            int u = (int) current[1]; // Node ID is stored as long, cast back to int

            ArrayList<Edge> neighbors = adj[u];
            
            // Using the requested index-based for loop style
            for (int i = 0; i < neighbors.size(); i++) 
            {
                Edge edge = neighbors.get(i);
                int v = edge.to;
                int weight = edge.weight;

                long new_dist = dist_u + weight;

                if (new_dist < distance[v]) 
                {
                    distance[v] = new_dist;
                    parent[v] = u; 
                    
                    // Add neighbor: {new_dist, v}
                    pq.add(new long[]{new_dist, v});
                }
            }
        }

        // --- 4. Output Result and Path Reconstruction ---
        
        if (distance[D] == Long.MAX_VALUE) 
        {
            System.out.println("-1");
        } 
        else 
        {
            // Print shortest distance
            System.out.println(distance[D]);

            // Reconstruct path
            List<Integer> path = new LinkedList<>();
            int current = D;
            
            // Backtrack from D to S using the parent array
            while (current != 0) 
            {
                path.add(0, current); // Add to the start of the list to maintain correct order (S -> D)
                if (current == S) 
                    {
                        break;
                    }   
                current = parent[current];
            }
            
            // Print path
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) 
            {
                sb.append(path.get(i)).append(" ");
            }

            System.out.println(sb.toString());
        }
    }
}