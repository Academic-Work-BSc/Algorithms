import java.io.*;
import java.util.*;

public class CoprimeGraphQuery 
{
    private static int gcd(int a, int b) 
    {
        while (b != 0) 
        {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] adjList = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) 
        {
            adjList[i] = new ArrayList<Integer>();
        }

        //build graph using gcd
        for (int i = 1; i <= N; i++) 
        {
            for (int j = 1; j <= N; j++) 
            {
                // Condition 1: i and j must be different nodes
                if (i == j)
                {
                    continue;
                }
                
                // Condition 2: gcd(i, j) must equal 1
                if (gcd(i, j) == 1) 
                {
                    adjList[i].add(j);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();

        for (int q = 0; q < Q; q++) 
        {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            ArrayList<Integer> neighbors = adjList[X];
            
            if (neighbors.size() < K) 
            {
                sb.append("-1").append("\n");
            } 
            else 
            {
                int result = neighbors.get(K - 1);
                sb.append(result).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}
