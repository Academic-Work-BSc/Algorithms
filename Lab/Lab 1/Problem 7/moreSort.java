import java.util.Scanner;
public class moreSort
{
    public static void main (String [] args)
    {
        Scanner sc = new Scanner (System.in);
        int T = sc.nextInt();

        for (int t = 0; t < T; t++)
        {
            int n = sc.nextInt();
            sc.nextLine();
            String ids = sc.nextLine();
            String mark = sc.nextLine();

            String[] idlist = ids.split (" ");
            String[] marklist = mark.split (" ");

            int[] id = new int [idlist.length];
            int[] marks = new int [marklist.length];

            for (int i = 0; i < idlist.length; i++)
            {
                id[i] = Integer.parseInt(idlist[i]);
                marks[i] = Integer.parseInt(marklist[i]);
            }

            int swap = 0;
            int maxindex = 0;
            for (int i = 0; i < idlist.length - 1; i++)
            {
                maxindex = i;
                for (int j = i + 1; j < idlist.length; j++)
                {
                    if (marks[j] > marks[maxindex])
                    {
                       maxindex = j;
                    }
                    else if (marks[j] == marks[maxindex])
                    {
                        if (id[j] < id[maxindex])
                        {
                            maxindex = j;
                        }
                    }
                }
                if (maxindex != i)
                {
                  int temp = marks[maxindex];
                  int temp2 = id[maxindex];
                  marks[maxindex] = marks[i];
                  id[maxindex] = id[i];
                  marks[i] = temp;
                  id[i] = temp2;
                  swap++;
                 }
            }
            System.out.println ("Minimum swaps: " + swap);
            for (int i = 0; i <id.length; i++)
            {
              System.out.println ("ID: " + id[i] + " Mark: " + marks[i]);
            }
        }
    }
}