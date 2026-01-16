import java.io.*;
import java.util.*;

public class MultiPersonTaskScheduler 
{
    static class Task 
    {
        int start;
        int end;

        public Task(int start, int end) 
        {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t = t + 1) 
        {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Task[] allTasks = new Task[N];

            for (int i = 0; i < N; i = i + 1) 
            {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                allTasks[i] = new Task(s, e);
            }

            // --- 1. Sort tasks by end time ---
            // Tie-breaker: if end times are equal, pick the one that starts LATER
            Arrays.sort(allTasks, new Comparator<Task>() 
            {
                public int compare(Task t1, Task t2) 
                {
                    if (t1.end != t2.end) 
                    {
                        return Integer.compare(t1.end, t2.end);
                    }
                    return Integer.compare(t2.start, t1.start); 
                }
            });

            // --- 2. Greedy assignment to M people ---
            
            int[] personLastEnd = new int[M];
            for (int i = 0; i < M; i = i + 1) 
            {
                personLastEnd[i] = -1;
            }

            int count = 0;

            for (int i = 0; i < N; i = i + 1) 
            {
                int bestPerson = -1;
                int minGap = Integer.MAX_VALUE;

                for (int j = 0; j < M; j = j + 1) 
                {
                    if (allTasks[i].start > personLastEnd[j]) 
                    {
                        int gap = allTasks[i].start - personLastEnd[j];
                        if (gap < minGap) 
                        {
                            minGap = gap;
                            bestPerson = j;
                        }
                    }
                }

                if (bestPerson != -1) 
                {
                    personLastEnd[bestPerson] = allTasks[i].end;
                    count = count + 1;
                }
            }

            System.out.println(count);
        }
    }
}