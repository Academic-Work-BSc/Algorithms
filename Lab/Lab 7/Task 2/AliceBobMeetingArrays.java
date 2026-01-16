import java.io.*;
import java.util.*;

public class AliceBobMeetingArrays
{
    // Class to represent an edge (necessary for the adjacency list structure)
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

    /*
     * Runs Dijkstra's algorithm from the specified source node.
     * Returns an array of shortest distances from the source to all other nodes.
     */
    private static long[] runDijkstra(int N, ArrayList<Edge>[] adj, int source) 
    {
        long[] distance = new long[N + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[source] = 0;

        // PriorityQueue stores long array: {distance, node_id}
        // Comparator is defined inline to sort by the distance (index 0).
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
        
        pq.add(new long[]{0, source}); // {distance=0, id=source}

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
                long weight = edge.weight;

                long new_dist = dist_u + weight;

                if (new_dist < distance[v]) 
                {
                    distance[v] = new_dist;
                    // Add new entry for neighbor: {new_dist, v}
                    pq.add(new long[]{new_dist, v});
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read N, M, S, T
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 
        int S = Integer.parseInt(st.nextToken()); // Alice's Start
        int T = Integer.parseInt(st.nextToken()); // Bob's Start

        // --- 1. Build Adjacency List ---
        
        ArrayList<Edge>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // Read M edges (u -> v with weight w)
        for (int i = 0; i < M; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            adj[u].add(new Edge(v, w));
        }

        // --- 2. Run Dijkstra for Alice (S) and Bob (T) ---
        
        // D_A[X] = shortest time for Alice to reach X
        long[] distAlice = runDijkstra(N, adj, S);
        
        // D_B[X] = shortest time for Bob to reach X
        long[] distBob = runDijkstra(N, adj, T);

        // --- 3. Find Minimum Meeting Time and Node ---
        
        long minMeetingTime = Long.MAX_VALUE;
        int meetingNode = -1;

        // Iterate through all possible meeting nodes X from 1 to N
        for (int X = 1; X <= N; X++) 
        {
            long timeAlice = distAlice[X];
            long timeBob = distBob[X];
            
            // Check if both can reach the node (distance is not infinity)
            if (timeAlice != Long.MAX_VALUE && timeBob != Long.MAX_VALUE) 
            {
                // The time they meet is limited by the slower person's arrival time
                long currentMeetingTime = Math.max(timeAlice, timeBob);

                if (currentMeetingTime < minMeetingTime) 
                {
                    minMeetingTime = currentMeetingTime;
                    meetingNode = X;
                } 
            }
        }

        // --- 4. Output Result ---

        if (meetingNode == -1) 
        {
            System.out.println("-1");
        } 
        else 
        {
            System.out.println(minMeetingTime + " " + meetingNode);
        }
    }
}