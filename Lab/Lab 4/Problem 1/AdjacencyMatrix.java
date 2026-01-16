import java.io.*;
import java.util.*;

public class AdjacencyMatrix 
{

    public static void main(String[] args) throws IOException 
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] adjMatrix = new int[N][N];

        for (int i = 0; i < M; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            int uIndex = u - 1;
            int vIndex = v - 1;
            adjMatrix[uIndex][vIndex] = w;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            
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