import java.io.*;
import java.util.*;

public class SubtreeSize 
{
    private static int[] subtreeSize; 
    private static ArrayList<Integer>[] adj;

    private static int dfs(int u, int parent) 
    {
        int currentSize = 1;
        ArrayList<Integer> neighbors = adj[u];
        
        for (int i = 0; i < neighbors.size(); i++) 
        {
            int v = neighbors.get(i);
            
            if (v != parent) 
            {
                currentSize += dfs(v, u);
            }
        }
        subtreeSize[u] = currentSize;
        return currentSize;
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()); 
        int Q = Integer.parseInt(br.readLine());

        subtreeSize = new int[N + 1];
        
        adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // Read N-1 edges
        for (int i = 0; i < N - 1; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
    
        dfs(R, 0);
        
        for (int i = 0; i < Q; i++) 
        {
            int X = Integer.parseInt(br.readLine());
            sb.append(subtreeSize[X]).append("\n");
        }

        System.out.print(sb.toString());
    }
}