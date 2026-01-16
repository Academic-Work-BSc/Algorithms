import java.io.*;
import java.util.*;

public class FriendCircleTracker 
{
    static int[] parent;
    static int[] size;

    // Find the representative of the set
    public static int find(int i) 
    {
        if (parent[i] == i) 
        {
            return i;
        }

        // Path compression
        parent[i] = find(parent[i]);
        return parent[i];
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        size = new int[N + 1];

        // Initialization loop
        for (int i = 1; i <= N; i++) 
        {
            parent[i] = i;
            size[i] = 1;
        }

        // Process K friendships
        for (int k = 0; k < K; k++) 
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int rootA = find(a);
            int rootB = find(b);
            
            if (rootA != rootB)
            {
                // Always make rootA the parent of rootB
                parent[rootB] = rootA;
                // We still need to update the size of the new root
                // so we can print the correct size of the circle.
                size[rootA] = size[rootA] + size[rootB];
                System.out.println(size[rootA]);
            } 
            else 
            {
                // Already in the same circle
                System.out.println(size[rootA]);
            }
        }
    }
}