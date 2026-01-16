import java.io.*;
import java.util.*;

public class AdjacencyListToMatrix {

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] adjMatrix = new int[N][N];

        for (int i = 0; i < N; i++) 
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            // The first token is k, the count of adjacent nodes
            int k = Integer.parseInt(st.nextToken());
            
            // Process the next k tokens (the adjacent nodes)
            for (int j = 0; j < k; j++) {
                
                // Read the destination node
                int destinationNode = Integer.parseInt(st.nextToken());
                adjMatrix[i][destinationNode] = 1;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) 
        {
            for (int j = 0; j < N; j++) 
            {
                sb.append(adjMatrix[i][j]);
                if (j < N - 1) 
                {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
