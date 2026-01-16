import java.io.*;
import java.util.*;

public class KingMoves 
{
    static class Move 
    {
        int r;
        int c; 

        public Move(int r, int c) 
        {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()); // row
        int y = Integer.parseInt(st.nextToken()); // column

        //all moves in sorted order
        int[][] directions = 
        {
            {-1, -1},  // Row x-1, Col y-1, top Left
            {-1, 0},   // Row x-1, Col y, top
            {-1, 1},   // Row x-1, Col y+1, top Right
            {0, -1},   // Row x, Col y-1, Left
            {0, 1},    // Row x, Col y+1, Right
            {1, -1},   // Row x+1, Col y-1, bottom Left
            {1, 0},    // Row x+1, Col y, bottom
            {1, 1}     // Row x+1, Col y+1, bottom Right
        };

        ArrayList<Move> validMoves = new ArrayList<>();

        for (int i = 0; i < directions.length; i++) 
        {
            int newX = x + directions[i][0];
            int newY = y + directions[i][1];

            // Check if the new position is within the board boundaries (1 to N)
            boolean xValid = ((newX >= 1) && (newX <= N));
            boolean yValid = ((newY >= 1) && (newY <= N));

            if (xValid && yValid) 
            {
                validMoves.add(new Move(newX, newY));
            }
        }

        int K = validMoves.size();
        System.out.println(K);

        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < K; i++) {
            Move move = validMoves.get(i);
            sb.append(move.r).append(" ").append(move.c).append("\n");
        }
        
        System.out.print(sb.toString());
    }
}

