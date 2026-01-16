import java.io.*;
import java.util.*;

public class MinimaxDangerPathArrays 
{
    // Class to represent an edge (still necessary for Adjacency List structure)
    static class Edge 
    {
        int to;
        int danger; // Weight of the road

        public Edge(int to, int danger) 
        {
            this.to = to;
            this.danger = danger;
        }
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Use a large constant for "infinity" (max danger is 10^6)
        final int INF = 1000001; 
        
        // Read N (cities), M (roads)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = 1; // Start city is always 1

        // --- 1. Build Adjacency List (Bi-directional/Undirected) ---
        
        ArrayList<Edge>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // Read M roads (u, v, w)
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

        // --- 2. Modified Dijkstra Initialization ---
        
        // maxDanger[i] stores the minimum maximum danger level of the path from 1 to i
        int[] maxDanger = new int[N + 1];
        Arrays.fill(maxDanger, INF); 
        maxDanger[S] = 0; 

        // PriorityQueue stores an int array: {maxDanger, node_id}
        // Comparator defined inline to sort by maxDanger (index 0).
        PriorityQueue<int[]> pq = new PriorityQueue<>
        (
            new Comparator<int[]>() 
            {
                public int compare(int[] a, int[] b) 
                {
                    return Integer.compare(a[0], b[0]);
                }
            }
        );
        
        pq.add(new int[]{0, S}); // {maxDanger=0, id=S}

        // --- 3. Modified Dijkstra Execution ---
        
        while (!pq.isEmpty()) 
        {
            int[] current = pq.remove();
            int danger_u = current[0];
            int u = current[1];

            ArrayList<Edge> neighbors = adj[u];
            
            for (int i = 0; i < neighbors.size(); i++) 
            {
                Edge edge = neighbors.get(i);
                int v = edge.to;
                int weight = edge.danger; // Danger of the current road

                // Relaxation: New path danger is the MAX of previous path danger and current edge danger
                int new_danger = Math.max(danger_u, weight);

                // Condition: We are minimizing the bottleneck (new_danger must be less than current maxDanger[v])
                if (new_danger < maxDanger[v]) 
                {
                    maxDanger[v] = new_danger;
                    // Add new entry for neighbor: {new_danger, v}
                    pq.add(new int[]{new_danger, v});
                }
            }
        }

        // --- 4. Output Result ---
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) 
        {
            int result = maxDanger[i];
            
            // Check if city is unreachable
            if (result == INF) 
            {
                sb.append("-1").append (" ");
            } 
            else 
            {
                sb.append(result).append (" ");
            }
        }
        System.out.println(sb.toString());
    }
}