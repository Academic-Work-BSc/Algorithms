import java.io.*;
import java.util.*;

public class AncientOrdering 
{
    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        String[] words = new String[N];
        for (int i = 0; i < N; i++) 
        {
            words[i] = br.readLine();
        }

        // --- 1. Initialize Graph, In-Degree, and Edge Tracker ---
        
        // Tracks whether an edge from u to v has already been added (CRITICAL FIX)
        boolean[][] edgeExists = new boolean[26][26]; 
        
        // Adjacency list for all 26 characters ('a' maps to index 0, 'z' to 25)
        ArrayList<Integer>[] adj = new ArrayList[26];
        int[] inDegree = new int[26];
        boolean[] seenChar = new boolean[26];

        for (int i = 0; i < 26; i++) 
        {
            adj[i] = new ArrayList<>();
        }

        // --- UNIFIED FIX: Mark all seen characters immediately ---
        for (int i = 0; i < N; i++) 
        {
            char[] currentWordChars = words[i].toCharArray();
            for (int j = 0; j < currentWordChars.length; j++) 
            {
                seenChar[currentWordChars[j] - 'a'] = true;
            }
        }
        // --- End of UNIFIED FIX ---


        // --- 2. Build Dependencies (Edges) ---
        
        for (int i = 0; i < N - 1; i++) 
        {
            String w1 = words[i];
            String w2 = words[i + 1];
            
            int len1 = w1.length();
            int len2 = w2.length();
            int minLen = Math.min(len1, len2);
            boolean foundDiff = false;

            // Find the first differing character
            for (int j = 0; j < minLen; j++)
            {
                int c1 = w1.charAt(j) - 'a';
                int c2 = w2.charAt(j) - 'a';
                
                if (c1 != c2) 
                {
                    // Dependency: c1 must come before c2 (c1 -> c2)
                    
                    if (!edgeExists[c1][c2]) 
                    {
                        adj[c1].add(c2);
                        inDegree[c2]++;
                        edgeExists[c1][c2] = true; // Mark the edge as existing
                    }
                    
                    foundDiff = true;
                    break;
                }
            }
            
            // --- Prefix Rule Check ---
            // If the second word (w2) is a prefix of the first word (w1), it's invalid.
            if (!foundDiff && len1 > len2) 
            {
                System.out.println("-1");
                return;
            }
        }
        
        // --- 3. Topological Sort with Priority Queue (Kahn's Algorithm) ---
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int uniqueCharCount = 0;

        // Initialization: Add all characters with in-degree 0
        for (int i = 0; i < 26; i++) 
        {
            if (seenChar[i]) 
            {
                uniqueCharCount++; 
                if (inDegree[i] == 0) 
                {
                    pq.add(i);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        int sortedCount = 0;

        while (!pq.isEmpty()) 
        {
            int u = pq.remove(); // Get the smallest available char
            sortedCount++;

            result.append((char) ('a' + u));
            
            ArrayList<Integer> neighbors = adj[u];
            
            // Use the requested index-based for loop style
            for (int i = 0; i < neighbors.size(); i++) 
            {
                int v = neighbors.get(i);
                inDegree[v]--;
                
                // If v now has no prerequisites, add it to the priority queue
                if (inDegree[v] == 0) 
                {
                    pq.add(v);
                }
            }
        }

        // --- 4. Final Cycle and Output Check ---
        
        if (sortedCount < uniqueCharCount) 
        {
            // Cycle detected among the used characters
            System.out.println("-1");
        } 
        else 
        {
            // Print ONLY the characters that were successfully sorted.
            System.out.println(result.toString());
        }
    }
}