import java.io.*;
import java.util.*;

public class AdjacencyList 
{
    static class Edge 
    {
        int destination;
        int weight;

        public Edge(int destination, int weight) 
        {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // Read all M source nodes (u)
        int[] uNodes = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) 
        {
            uNodes[i] = Integer.parseInt(st.nextToken());
        }

        // Read all M destination nodes (v)
        int[] vNodes = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) 
        {
            vNodes[i] = Integer.parseInt(st.nextToken());
        }

        // Read all M weights (w)
        int[] wWeights = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) 
        {
            wWeights[i] = Integer.parseInt(st.nextToken());
        }

        // --- 1. Adjacency List Initialization ---
        
        // The Adjacency List is an array of LinkedLists.

        // Size N+1 is used for 1-indexed nodes.

        LinkedList<Edge> [] adjList = new LinkedList[N + 1];

        // Initialize each list in the array

        for (int i = 1; i <= N; i++) 
        {
            adjList[i] = new LinkedList<Edge>();
        }

        // --- 2. Populate the Adjacency List ---
        
        // Iterate M times, processing one edge per iteration

        for (int i = 0; i < M; i++) 
        {
            // Get the components of the i-th edge
            int u = uNodes[i];
            int v = vNodes[i];
            int w = wWeights[i];

            // Add the directed edge (v, w) to the list of node u

            adjList[u].add(new Edge(v, w));
        }


        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) 
        {
            sb.append(i).append(":");

            LinkedList<Edge> edges = adjList[i];
            
            for (int j = 0; j < edges.size(); j++) 
            {
                Edge currentEdge = edges.get(j);
                sb.append(" (").append(currentEdge.destination).append(",").append(currentEdge.weight).append(")");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}