
import java.io.*;
import java.util.*;
 
public class GeometricSeriesSum_DivideAndConquer {

    static long MOD;
    static long A;
 
 
    public static long[] solve(long n) 
    {
        if (n == 0) 
        {
            return new long[]{0, 1};
        }
 
        long k = n / 2;
        long[] temp = solve(k);
        long sum_k = temp[0];
        long power_k = temp[1];
 
        long sum_n = (sum_k * (1 + power_k)) % MOD;
 
        long power_n = (power_k * power_k) % MOD;

        if (n % 2 == 1)
        {
            power_n = (power_n * A) % MOD;
            sum_n = (sum_n + power_n) % MOD;
        }

        return new long[]{sum_n, power_n};
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());
 
            A = a;
            MOD = m;
 
            long[] result = solve(n);
            
            long finalSum = result[0];
 
            if (finalSum < 0) {
                finalSum += m;
            }
 
            System.out.println(finalSum);
        }
    }
}
