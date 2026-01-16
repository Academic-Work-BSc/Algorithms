import java.io.*;
import java.util.*;

public class DFS_Traversal 
{
    private static ArrayList<Integer>[] adj;
    private static boolean[] visited;
    private static ArrayList<Integer> traversalOrder = new ArrayList<>();

    private static void dfs(int u) 
    {
        visited[u] = true;
        traversalOrder.add(u);

        ArrayList<Integer> neighbors = adj[u];

        for (int i = 0; i < neighbors.size(); i++) 
        { 
            int v = neighbors.get(i); 
            
            if (!visited[v]) 
            {
                dfs(v);
            }
        }
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        
        st = new StringTokenizer(br.readLine());
        int[] u_nodes = new int[M];

        for (int i = 0; i < M; i++) 
        {
            u_nodes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] v_nodes = new int[M];

        for (int i = 0; i < M; i++) 
        {
            v_nodes[i] = Integer.parseInt(st.nextToken());
        }
        
        adj = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) 
        {
            int u = u_nodes[i];
            int v = v_nodes[i];
            
            adj[u].add(v);
            adj[v].add(u);
        }

        for (int i = 1; i <= N; i++) 
        {
            Collections.sort(adj[i]);
        }
        
        visited = new boolean[N + 1];

        dfs(1);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < traversalOrder.size(); i++) 
        {
            sb.append(traversalOrder.get(i));
            
            if (i < traversalOrder.size() - 1) 
            {
                sb.append(" ");
            }
        }

        System.out.println(sb.toString());
    }
}