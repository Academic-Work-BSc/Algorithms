import java.io.*;
import java.util.*;

public class ShortestPathLexicographical 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read N, M, S, D
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) 
        {
            adj[i] = new ArrayList<>();
        }
        
        
        StringTokenizer st_u = new StringTokenizer(br.readLine());
        StringTokenizer st_v = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < M; i++) 
        {
            
            int u = Integer.parseInt(st_u.nextToken());
            int v = Integer.parseInt(st_v.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        
        for (int i = 1; i < N + 1; i++) 
        {
            Collections.sort(adj[i]); 
        }
        
        int[] distance = new int[N + 1];
        int[] parent = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        
        Queue<Integer> queue = new LinkedList<>();
        
        visited[S] = true;
        distance[S] = 0;
        queue.add(S);
        
        boolean found = false;
        
        while (!queue.isEmpty()) 
        {
            int u = queue.remove(); 
            
            if (u == D) 
            {
                found = true;
                break;
            }

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
        
        if (!found) 
        {
            System.out.println(-1);
        } 
        
        else 
        {
            System.out.println(distance[D]);
            
            LinkedList<Integer> path = new LinkedList<>();
            int curr = D;
            
            while (curr != 0) 
            {
                path.addFirst(curr);
                curr = parent[curr]; 
            }
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < path.size(); i++) 
            {
                sb.append(path.get(i));
                if (i < path.size() - 1)
                {
                    sb.append(" ");
                }
            }
            System.out.println(sb.toString());
        }
    }
}