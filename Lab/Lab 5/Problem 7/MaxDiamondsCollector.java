import java.io.*;
import java.util.*;

public class MaxDiamondsCollector 
{

    public static void main(String[] args) throws IOException 
    {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        char[][] grid = new char[R][H];
        for (int i = 0; i < R; i++) 
        {
            String line = br.readLine();
            for (int j = 0; j < H; j++) 
            {
                grid[i][j] = line.charAt(j);
            }
        }

        boolean[][] visited = new boolean[R][H];
        int maxDiamonds = 0;
        
        // Directions for movement: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // --- Core Logic: Iterate through all cells to find Connected Components ---
        for (int r = 0; r < R; r++) 
        {
            for (int c = 0; c < H; c++) 
            {
                // If the cell is an open space (or diamond) and hasn't been visited, 
                // start a new BFS traversal for a new component.
                if (grid[r][c] != '#' && !visited[r][c]) 
                {
                    int currentDiamonds = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    
                    queue.add(new int[]{r, c});
                    visited[r][c] = true;
                    
                    if (grid[r][c] == 'D') 
                    {
                        currentDiamonds++;
                    }

                    while (!queue.isEmpty()) 
                    {
                        int[] current = queue.remove(); 
                        int row = current[0];
                        int col = current[1];
                        
                        // Explore neighbors
                        for (int i = 0; i < 4; i++) 
                        {
                            int nr = row + dr[i];
                            int nc = col + dc[i];
                            
                            // Check bounds
                            if ((nr >= 0) && (nr < R) && (nc >= 0) && (nc < H)) 
                            {
                                // Check if not obstacle and unvisited
                                if ((grid[nr][nc] != '#') && (!visited[nr][nc])) 
                                {
                                    visited[nr][nc] = true;
                                    queue.add(new int[]{nr, nc});
                                    
                                    // Count the diamond
                                    if (grid[nr][nc] == 'D') 
                                    {
                                        currentDiamonds++;
                                    }
                                }
                            }
                        }
                    }
                    if (currentDiamonds > maxDiamonds) 
                    {
                        maxDiamonds = currentDiamonds;
                    }
                }
            }
        }
        System.out.println(maxDiamonds);
    }
}