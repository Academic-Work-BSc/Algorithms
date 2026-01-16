import java.util.*;
import java.io.*;

public class ShortestPathThroughK 
{
    //helper function to run bfs and return distance array and parent array
    private static int[][] runBFS(int N, ArrayList<Integer>[] adj, int startNode) 
    {
        int[] distance = new int[N + 1];
        int[] parent = new int[N + 1];
        boolean[] visited = new boolean[N + 1]; 
        
        for (int i = 0; i < N + 1; i++) 
        {
            distance[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();

        distance[startNode] = 0;
        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) 
        {
            int u = queue.remove();
            
            ArrayList<Integer> neighbors = adj[u];
            
            for (int i = 0; i < neighbors.size(); i++) 
            {
                int v = neighbors.get(i);

                if (!visited[v]) 
                {
                    visited[v] = true;
                    distance[v] = distance[u] + 1;
                    parent[v] = u;
                    queue.add(v);
                }
            }
        }
        
        return new int[][]{distance, parent};
    }

    public static void main(String[] args) throws IOException 
    {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken()); // Source
        int D = Integer.parseInt(st.nextToken()); // Destination
        int K = Integer.parseInt(st.nextToken()); // Mandatory node
        
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
        }
        
        // BFS 1: S to K
        int[][] result_S_to_K = runBFS(N, adj, S);
        int[] dist_S = result_S_to_K[0];
        int[] parent_S = result_S_to_K[1];
        
        // BFS 2: K to D
        int[][] result_K_to_D = runBFS(N, adj, K);
        int[] dist_K = result_K_to_D[0];
        int[] parent_K = result_K_to_D[1];

        if (dist_S[K] == -1 || dist_K[D] == -1) 
        {
            System.out.println("-1");
            return;
        }


        int total_distance = dist_S[K] + dist_K[D];
        System.out.println(total_distance);
        
        LinkedList<Integer> path_S_to_K = new LinkedList<>();
        int curr = K;

        while (curr != 0) 
        {
            path_S_to_K.addFirst(curr);
            if (curr == S) 
            {
                break;
            }
            curr = parent_S[curr];
        }

        LinkedList<Integer> path_K_to_D = new LinkedList<>();
        curr = D;

        while (curr != 0) 
        {
            path_K_to_D.addFirst(curr);
            if (curr == K)
            {
                break;
            }
            curr = parent_K[curr];
        }

        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < path_S_to_K.size() - 1; i++) 
        {
            sb.append(path_S_to_K.get(i)).append(" ");
        }
        
        for (int i = 0; i < path_K_to_D.size(); i++) 
        {
            sb.append(path_K_to_D.get(i));
            if (i < path_K_to_D.size() - 1) 
            {
                sb.append(" ");
            }
        }
        
        System.out.println(sb.toString());
    }
}