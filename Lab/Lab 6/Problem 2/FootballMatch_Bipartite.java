import java.io.*;
import java.util.*;

public class FootballMatch_Bipartite 
{

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read N and M
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // --- 1. Build Undirected Adjacency List ---
        
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // Read M tackles (undirected edges)
        for (int i = 0; i < M; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            // Undirected edge (u and v must be different types)
            adj[u].add(v);
            adj[v].add(u);
        }

        // --- 2. Bipartite Check and Component Summation ---
        
        // team[i]: 0=Unassigned, 1=Set A (e.g., Robot), 2=Set B (e.g., Human)

        int[] team = new int[N + 1]; 
        long maxTotalType = 0; // Use long for sum, although int should suffice given N constraint.

        // Iterate through all players to find all Connected Components
        for (int i = 1; i < N + 1; i++) 
        {
            if (team[i] == 0) //found unvisited component
            {
                // Counters for the two sets in the current component
                long countA = 0;
                long countB = 0;
                
                Queue<Integer> queue = new LinkedList<>();
                
                // Start BFS, arbitrarily assigning the starting node 'i' to Set A
                queue.add(i);
                team[i] = 1; // Assign to Set A
                countA++;

                while (!queue.isEmpty()) 
                {
                    int u = queue.remove(); 
                    int u_team = team[u]; // The set (1 or 2) of the current node u
                    
                    ArrayList<Integer> neighbors = adj[u];
                    
                    for (int j = 0; j < neighbors.size(); j++) 
                    {
                        int v = neighbors.get(j);
                        
                        if (team[v] == 0) 
                        { // Neighbor is unassigned
                            
                            // Assign v to the opposite set
                            
                            if (u_team == 1) 
                            {
                                team[v] = 2;
                                countB++;
                            } 
                            else 
                            {
                                team[v] = 1;
                                countA++;
                            }
                            
                            queue.add(v);
                        }
                    }
                }

                // Maximize within the current component: 
                // The max possible number of Robots (or Humans) is max(Set A size, Set B size)
                maxTotalType += Math.max(countA, countB);
            }
        }

        System.out.println(maxTotalType);
    }
}