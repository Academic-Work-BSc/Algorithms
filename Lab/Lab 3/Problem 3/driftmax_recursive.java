import java.io.*;
import java.util.*;

public class driftmax_recursive
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long mod = 107;

        long finalResult = power(a, b, mod);

        System.out.println(finalResult);
    }

    public static long power(long base, long exp, long mod)
    {
        if (exp == 0)
        {
            return 1;
        }

        long half = power(base, exp / 2, mod);
        long result = (half * half) % mod;
        if (exp % 2 == 1)
        {
            result = (result * base) % mod;
        }

        return result;
    }
}
