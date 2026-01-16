import java.io.*;
import java.util.*;

public class TaskScheduler
{
    // Class to represent a task interval
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

        int N = Integer.parseInt(st.nextToken());
        Task[] allTasks = new Task[N];

        for (int i = 0; i < N; i = i + 1) 
        {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            allTasks[i] = new Task(s, e);
        }

        // --- 1. Sort tasks by End Time ---
        // If end times are equal, sorting by start time doesn't change the max count.
        Arrays.sort(allTasks, new Comparator<Task>() 
        {
            public int compare(Task t1, Task t2) 
            {
                return Integer.compare(t1.end, t2.end);
            }
        });

        // --- 2. Greedy Selection ---
        ArrayList<Task> selectedTasks = new ArrayList<>();
        int lastEndTime = -1;

        for (int i = 0; i < N; i = i + 1) 
        {
            // If the start time is > the end time of the previous task
            if (allTasks[i].start > lastEndTime) 
            {
                selectedTasks.add(allTasks[i]);
                lastEndTime = allTasks[i].end;
            }
        }

        // --- 3. Output Results ---
        System.out.println(selectedTasks.size());
        
        for (int i = 0; i < selectedTasks.size(); i = i + 1) 
        {
            Task current = selectedTasks.get(i);
            System.out.println(current.start + " " + current.end);
        }
    }
}