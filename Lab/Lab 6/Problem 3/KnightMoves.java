import java.io.*;
import java.util.*;

public class KnightMoves {

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Read N (Board size)
        int N = Integer.parseInt(br.readLine());

        // Read start and target positions (x1, y1) to (x2, y2)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        // Since the coordinates are 1-based, we'll convert to 0-based for array indexing
        int startX = x1 - 1;
        int startY = y1 - 1;
        int targetX = x2 - 1;
        int targetY = y2 - 1;

        // Handle the trivial case: start equals target
        if (startX == targetX && startY == targetY) 
        {
            System.out.println(0);
            return;
        }

        // --- Knight Move Deltas ---
        
        // 8 possible changes in row (dx) and column (dy)
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

        // --- BFS Setup ---
        
        // distance[i][j] stores the minimum moves to reach (i, j). 
        // Initialized to -1 (unvisited/unreachable).
        int[][] distance = new int[N][N];
        
        for (int i = 0; i < N; i++) 
        {
            for (int j = 0; j < N; j++) 
            {
                distance[i][j] = -1;
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        // Start BFS from (startX, startY)
        distance[startY][startX] = 0;
        queue.add(new int[]{startX, startY});
        
        int minMoves = -1;

        // --- BFS Traversal ---
        
        while (!queue.isEmpty()) 
        {
            int[] current = queue.remove();
            int c = current[0]; // Current col, x
            int r = current[1]; // Current row, y
            int dist = distance[r][c];
            
            //If we reached the target, we found the minimum distance
            if (c == targetX && r == targetY) 
            {
                minMoves = dist;
                break; 
            }

            // Check 8 possible knight moves
            for (int i = 0; i < 8; i++) 
            {
                int nr = r + dy[i]; // New row
                int nc = c + dx[i]; // New column

                // Check 1: Must be within board boundaries (0 to N-1)
                if (nr >= 0 && nr < N && nc >= 0 && nc < N) 
                {
                    // Check 2: Must be unvisited (-1 distance)
                    if (distance[nr][nc] == -1) 
                    {
                        distance[nr][nc] = dist + 1;
                        queue.add(new int[]{nc, nr});
                    }
                }
            }
        }

        System.out.println(minMoves);
    }
}
