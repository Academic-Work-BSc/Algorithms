import java.io.*;
import java.util.*;

public class NumberTransformation 
{
    private static Set<Integer> getPrimeFactors(int A) 
    {
        Set<Integer> factors = new HashSet<>();
        int originalA = A; // Store the original value to check against
        
        // Handle factor 2
        if (A % 2 == 0) 
        {
            factors.add(2);

            while (A % 2 == 0) 
            {
                A = A / 2;
            }
        }
        
        // Handle odd factors
        for (int i = 3; i * i <= A; i = i + 2) 
        {
            if (A % i == 0) 
            {
                factors.add(i);
                while (A % i == 0) 
                {
                    A = A / i;
                }
            }
        }
        
        // If A is still greater than 1, it is the last prime factor
        if (A > 1) 
        {
            factors.add(A);
        }
        
        // --- CRITICAL CORRECTION ---
        // If the only factor found is the number itself (i.e., A is prime),
        // the problem rule says A is not considered a factor.
        if (factors.size() == 1)
        {
            // If the set contains only one element, and that element is the original number, remove it.
            // This happens if originalA is prime.
            if (factors.contains(originalA))
            {
                factors.clear();
            }
        }
        
        return factors;
    }
    
    private static int solve(int s, int t) 
    {
        // distance array: index is the number, value is the minimum transformations.
        // Size is t + 1 since numbers go up to t. Initialized to -1 (unvisited).

        int[] distance = new int[t + 1]; 
    
        // loop for initialization
        for (int i = 0; i < t + 1; i++) 
        {
            distance[i] = -1;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        distance[s] = 0;
        queue.add(s);
        
        while (!queue.isEmpty()) 
        {
            int A = queue.remove(); // Current number
            int dist = distance[A];
            
            // If we found the target, return the distance
            if (A == t)
            {
                return dist;
            }
            // Find all distinct prime factors of A
           // We only need to check numbers >= 2 
           
            if (A < 2)
            {
                continue; 
            }
            
            Set<Integer> primeFactorsSet = getPrimeFactors(A);
        
            // Convert the Set to an ArrayList for index-based access ---
            List<Integer> primeFactorsList = new ArrayList<>(primeFactorsSet);

            // Check all possible transformations (A -> A + x)
            for (int i = 0; i < primeFactorsList.size(); i++) 
            {
                // Access element by index
                int x = primeFactorsList.get(i); 
                int B = A + x; // Next possible number
            
                // If the transformation is valid:
                if (B <= t && distance[B] == -1) 
                {
                    distance[B] = dist + 1;
                    queue.add(B);
                }
            }
        }
        // If the queue empties and t was not reached
        return -1;
    }

    public static void main(String[] args) throws IOException 
    {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        // Use the requested index-based for loop style
        for (int i = 0; i < T; i++) 
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            
            int result = solve(s, t);
            sb.append(result).append("\n");
        }

        System.out.print(sb.toString());
    }
}