import java.io.*;
import java.util.*;

public class matrixdrift
{

    public static void main(String[] args) throws IOException
    {
        long MOD = 1000000007;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a11 = Long.parseLong(st.nextToken());
            long a12 = Long.parseLong(st.nextToken());
            long a21 = Long.parseLong(st.nextToken());
            long a22 = Long.parseLong(st.nextToken());
            long X = Long.parseLong(br.readLine());

            long[] A = new long[]{a11, a12, a21, a22};

            long[] finalResult = matrixPower(A, X, MOD);

            long r11 = finalResult[0];
            long r12 = finalResult[1];
            long r21 = finalResult[2];
            long r22 = finalResult[3];
            
            System.out.println(((r11 % MOD) + MOD) % MOD + " " + ((r12 % MOD) + MOD) % MOD);
            System.out.println(((r21 % MOD) + MOD) % MOD + " " + ((r22 % MOD) + MOD) % MOD);
        }
    }

    public static long[] matrixPower(long[] A, long exp, long MOD)
    {
        if (exp == 0)
        {
            return new long[]{1, 0, 0, 1};
        }

        long[] half = matrixPower(A, exp / 2, MOD);

        long[] result = multiply(half, half, MOD);

        if (exp % 2 == 1)
        {
            result = multiply(result, A, MOD);
        }

        return result;
    }

    public static long[] multiply(long[] A, long[] B, long MOD)
    {
        long a11 = A[0], a12 = A[1], a21 = A[2], a22 = A[3];
        long b11 = B[0], b12 = B[1], b21 = B[2], b22 = B[3];

        long r11 = (a11 * b11 + a12 * b21) % MOD;
        long r12 = (a11 * b12 + a12 * b22) % MOD;
        long r21 = (a21 * b11 + a22 * b21) % MOD;
        long r22 = (a21 * b12 + a22 * b22) % MOD;

        return new long[]{r11, r12, r21, r22};
    }
}