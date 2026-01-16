import java.io.*;
import java.util.*;

public class TreeDiameter 
{
    private static int[] runBFS(int N, ArrayList<Integer>[] adj, int startNode, int[] distance) 
    {
        // Reset distance array: -1 means unvisited/unreachable
        for (int i = 0; i < N + 1; i++) 
        {
            distance[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();

        distance[startNode] = 0;
        queue.add(startNode);
        
        int farthestNode = startNode;
        int maxDistance = 0;

        while (!queue.isEmpty()) 
        {
            int u = queue.remove();
            int dist = distance[u];
            
            // Update farthest node seen so far
            if (dist > maxDistance) 
            {
                maxDistance = dist;
                farthestNode = u;
            }
            
            ArrayList<Integer> neighbors = adj[u];
            
            for (int i = 0; i < neighbors.size(); i++) 
            {
                int v = neighbors.get(i);

                // If v is unvisited
                if (distance[v] == -1) 
                {
                    distance[v] = dist + 1;
                    queue.add(v);
                }
            }
        }
        
        return new int[]{farthestNode, maxDistance};
    }

    public static void main(String[] args) throws IOException 
    {   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        // --- 1. Initialize and Build Adjacency List (Undirected) ---
        
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // Read N-1 edges
        for (int i = 0; i < N - 1; i++) 
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            adj[u].add(v);
            adj[v].add(u);
        }

        // Arrays used across both BFS passes
        int[] distance = new int[N + 1];

        // --- 2. First Pass BFS: Find Node A (One endpoint of the diameter) ---
        
        // Start from node 1 (arbitrary).

        int[] result1 = runBFS(N, adj, 1, distance);
        int nodeA = result1[0]; // Farthest node from 1

        // --- 3. Second Pass BFS: Find Node B and the Diameter Length ---
        
        // Start from Node A
        int[] result2 = runBFS(N, adj, nodeA, distance);
        int diameterLength = result2[1];
        int nodeB = result2[0]; // Farthest node from Node A

        // --- 4. Output Result ---
        
        System.out.println(diameterLength);
        
        // The two endpoints of the longest path are Node A and Node B.
        System.out.println(nodeA + " " + nodeB);
    }
}