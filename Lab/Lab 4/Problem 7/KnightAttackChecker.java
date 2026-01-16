import java.io.*;
import java.util.*;

public class KnightAttackChecker 
{    
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //all moves
        
        int[][] moves = 
        {
            {-2, -1}, 
            {-2, 1}, 
            {-1, -2}, 
            {-1, 2}, 
            {1, -2}, 
            {1, 2},   
            {2, -1}, 
            {2, 1}    
        };

        ArrayList<int[]> knightPositions = new ArrayList<>(K); 
        HashSet<String> knightSet = new HashSet<>(K); 

        for (int i = 0; i < K; i++) 
        {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            knightPositions.add(new int[]{x, y});
            
            String key = x + "," + y;
            knightSet.add(key);
        }
        
        boolean attackFound = false;

        for (int i = 0; i < K; i++) 
        {
            int[] currentPos = knightPositions.get(i);
            int startX = currentPos[0];
            int startY = currentPos[1];

            for (int j = 0; j < moves.length; j++) 
            {
                int dx = moves[j][0];
                int dy = moves[j][1];
                
                int newX = startX + dx;
                int newY = startY + dy;

                // Boundary Check
                boolean xValid = ((newX >= 1) && (newX <= N));
                boolean yValid = ((newY >= 1) && (newY <= M));

                if (xValid && yValid) 
                {
                    // Occupancy Check
                    String targetKey = newX + "," + newY;

                    if (knightSet.contains(targetKey)) 
                    {
                        attackFound = true;
                        break; 
                    }
                }
            }
            
            if (attackFound) 
            {
                break;
            }
        }
        
        if (attackFound) 
        {
            System.out.println("YES");
        } 
        else 
        {
            System.out.println("NO");
        }
    }
}