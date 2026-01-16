import java.io.*;
import java.util.*;

public class DirectedCycleDetector
{
    private static ArrayList<Integer>[] adj;
    
    // State array: 0 = Unvisited, 1 = Visiting (in recursion stack), 2 = Visited
    private static int[] state; 

    private static boolean checkCycle(int u) 
    {
        state[u] = 1;

        ArrayList<Integer> neighbors = adj[u];
        for (int i = 0; i < neighbors.size(); i++) 
        {
            int v = neighbors.get(i);

            // Case 1: Cycle found, If we hit a node that is currently in the recursion stack.
            if (state[v] == 1) 
            {
                return true;
            }
            
            // Case 2: Node is unvisited, start a new DFS branch.
            if (state[v] == 0) 
            {
                if (checkCycle(v)) 
                {
                    return true;
                }
            }
        }

        // If no cycle was found through this node's path, mark it as fully visited
        state[u] = 2;
        return false;
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N + 1];
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
        
        state = new int[N + 1];
        boolean hasCycle = false;

        for (int i = 1; i < N + 1; i++) 
        {
            if (state[i] == 0) 
            {
                if (checkCycle(i)) 
                {
                    hasCycle = true;
                    break;
                }
            }
        }
        
        if (hasCycle) 
        {
            System.out.println("YES");
        } 
        else 
        {
            System.out.println("NO");
        }
    }
}