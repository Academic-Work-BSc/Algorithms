import java.io.*;
import java.util.*;

public class KingMaintenance 
{
    // Edge class to store the road details
    static class Road 
    {
        int u;
        int v;
        int weight;

        public Road(int u, int v, int weight) 
        {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static int[] parent;

    // Find with path compression to keep the tree flat
    public static int find(int i) 
    {
        if (parent[i] == i) 
        {
            return i;
        }
        
        parent[i] = find(parent[i]);
        return parent[i];
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Road[] roads = new Road[M];

        // --- 1. Read all roads ---
        
        for (int i = 0; i < M; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            roads[i] = new Road(u, v, w);
        }

        // --- 2. Sort roads by maintenance cost ---
        
        Arrays.sort(roads, new Comparator<Road>() 
        {
            public int compare(Road r1, Road r2) 
            {
                return Integer.compare(r1.weight, r2.weight);
            }
        });

        // --- 3. Initialize Union-Find ---
        
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) 
        {
            parent[i] = i;
        }

        long minTotalCost = 0;

        // --- 4. Kruskal's Algorithm loop ---
        
        for (int i = 0; i < M; i++) 
        {
            Road currentRoad = roads[i];
            
            int rootU = find(currentRoad.u);
            int rootV = find(currentRoad.v);

            // If they are not connected, this road is needed for MST
            if (rootU != rootV) 
            {
                parent[rootU] = rootV;
                minTotalCost = minTotalCost + currentRoad.weight;
            }
        }

        // --- 5. Output the result ---
        
        System.out.println(minTotalCost);
    }
}