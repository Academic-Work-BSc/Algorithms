import java.io.*;
import java.util.*;

public class fastmaxdrift
{
    static long power(long base, long exp, long m)
    {
        long res = 1;
        base = base % m;

        while(exp > 0)
        {
            if((exp % 2) == 1)
            {
                res = (res * base) % m;
            }
            exp = exp / 2;
            base = (base * base) % m;
        }

        return res;
    }

    static long[] solve(long a, long n, long m)
    {
        if (n == 0)
        {
            long[] result = new long[2];
            result[0] = 0;
            result[1] = 1;
            return result;
        }

        if ((n % 2) == 0)
        {
            long k = n / 2;
            
            long[] tempResult = solve(a, k, m);
            long sum_k = tempResult[0];
            long power_k = tempResult[1];
            

            long factor = (1 + power_k) % m;
            long sum_n = (sum_k * factor) % m;

            long power_n = (power_k * power_k) % m;

            long[] result = new long[2];
            result[0] = sum_n;
            result[1] = power_n;
            return result;
        }
        
        else
        {
            long[] tempResult = solve(a, n - 1, m);
            long sum_n_minus_1 = tempResult[0];
            long power_n_minus_1 = tempResult[1];

            long power_n = (power_n_minus_1 * a) % m;
            
            long sum_n = (sum_n_minus_1 + power_n) % m;
            
            long[] result = new long[2];
            result[0] = sum_n;
            result[1] = power_n;
            return result;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());

            long[] answer = solve(a, n, m);
            long finalResult = answer[0];
            
            finalResult = (finalResult + m) % m;

            System.out.println(finalResult);
        }
    }
}