import java.io.*;
import java.util.*;

public class MaximumReward 
{
    static class Task 
    {
        int duration;
        int deadline;

        public Task(int duration, int deadline) 
        {
            this.duration = duration;
            this.deadline = deadline;
        }
    }

    public static void main(String[] args) throws IOException 
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Task[] tasks = new Task[n];

        for (int i = 0; i < n; i = i + 1) 
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            tasks[i] = new Task(a, d);
        }

        // --- 1. Greedy Strategy: Sort by duration (shortest first) ---
        
        Arrays.sort(tasks, new Comparator<Task>() 
        {
            public int compare(Task t1, Task t2) 
            {
                return Integer.compare(t1.duration, t2.duration);
            }
        });

        // --- 2. Calculate finishing times and total reward ---
        
        long totalReward = 0;
        long currentTime = 0;

        for (int i = 0; i < n; i = i + 1) 
        {
            // Task finishes after its duration is added to current time
            currentTime = currentTime + (long) tasks[i].duration;
            
            // Reward = deadline - finishing time
            long currentReward = (long) tasks[i].deadline - currentTime;
            
            totalReward = totalReward + currentReward;
        }

        // --- 3. Output the result ---
        
        System.out.println(totalReward);
    }
}