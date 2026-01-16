import java.io.*;
import java.util.*;
 
public class ShortestPathWithParityConstraint 
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
        
        // Read N (nodes), M (edges)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = 1; // Source
        int D = N; // Destination
        
        long INF = Long.MAX_VALUE; 
 
        // Read edge data (u, v, w arrays)
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
 
        // --- 1. Build Adjacency List for the 2N Node Transformed Graph ---
        
        // New graph size is 2*N nodes (index 1 to 2N). We use 2N+1 array size.
        ArrayList<Edge>[] adj = new ArrayList[2 * N + 1];
        for (int i = 1; i <= 2 * N; i++) 
        {
            adj[i] = new ArrayList<>();
        }
 
        for (int i = 0; i < M; i++) 
        {
            int u_orig = U[i];
            int v_orig = V[i];
            int w = W[i];
            int parity = w % 2; // 1 for Odd, 0 for Even
            
            if (parity == 1) // Odd edge (w is odd)
            {
                // Must transition from an EVEN previous edge to this ODD edge
                // Original u_EVEN (index 2*u_orig) -> Original v_ODD (index 2*v_orig - 1)
                int u_even_state = 2 * u_orig;
                int v_odd_state = 2 * v_orig - 1;
                adj[u_even_state].add(new Edge(v_odd_state, w));
            } 
            else // Even edge (w is even)
            {
                // Must transition from an ODD previous edge to this EVEN edge
                // Original u_ODD (index 2*u_orig - 1) -> Original v_EVEN (index 2*v_orig)
                int u_odd_state = 2 * u_orig - 1;
                int v_even_state = 2 * v_orig;
                adj[u_odd_state].add(new Edge(v_even_state, w));
            }
        }
 
        // --- 2. Dijkstra Initialization ---
        
        long[] distance = new long[2 * N + 1];
        Arrays.fill(distance, INF);
 
        // PriorityQueue stores long array: {distance, node_id}
        PriorityQueue<long[]> pq = new PriorityQueue<>
        (
            new Comparator<long[]>() 
            {
                public int compare(long[] a, long[] b) 
                {
                    return Long.compare(a[0], b[0]);
                }
            }
        );
        
        pq.add (new long[] {0, 1});
        pq.add (new long[] {0, 2});
 
        // --- 3. Dijkstra Execution ---
        
        while (!pq.isEmpty()) 
        {
            long[] current = pq.remove();
            long dist_u = current[0];
            int u = (int) current[1];
 
            ArrayList<Edge> neighbors = adj[u];
            
            for (int i = 0; i < neighbors.size(); i++) 
            {
                Edge edge = neighbors.get(i);
                int v = edge.to;
                int weight = edge.weight;
 
                long new_dist = dist_u + weight;
 
                if (new_dist < distance[v]) 
                {
                    distance[v] = new_dist;
                    pq.add(new long[]{new_dist, v});
                }
            }
        }
 
        // --- 4. Output Result ---
        
        // Final destination N is reachable via N_ODD (index 2*N-1) or N_EVEN (index 2*N)
        long resultOdd = distance[2 * D - 1];
        long resultEven = distance[2 * D];
        
        long minResult = Math.min(resultOdd, resultEven);
 
        if (minResult == INF) 
        {
            System.out.println("-1");
        } 
        else 
        {
            System.out.println(minResult);
        }
    }
}