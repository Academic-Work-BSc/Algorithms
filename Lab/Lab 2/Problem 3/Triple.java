import java.io.*;
import java.util.*;

public class Triple 
{
    public static void main (String [] args) throws IOException
    {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        Dictionary <Integer, ArrayList<Integer>> table = new Hashtable<>();

        String i1 = br.readLine();
        String[] i1p = i1.split (" ");

        int size = Integer.parseInt(i1p[0]);
        int target = Integer.parseInt(i1p[1]);

        ArrayList <Integer> arr = new ArrayList<Integer>();

        String i2 = br.readLine();
        String[] i2p = i2.split(" ");
        
        for (int i =0; i<i2p.length; i++)
        {
            arr.add (Integer.parseInt(i2p[i]));
        }

        for (int i = 0; i<arr.size(); i++)
        {
            ArrayList<Integer> indexes = table.get(arr.get(i));
            if (indexes == null)
            {
                indexes = new ArrayList<Integer>();
            }
            indexes.add (i);
            table.put (arr.get(i), indexes);
        }
        Collections.sort(arr);




        int L = 0;
        int R = arr.size() - 1;

        for (int i = 0; i < arr.size(); i++)
        {
            L = i + 1;
            R = arr.size() - 1;

            while (L<R)
            {
                if (arr.get(i) + arr.get(L) + arr.get(R) == target)
                {
                    int idx1 = table.get(arr.get(i)).remove(0);
                    int idx2 = table.get(arr.get(L)).remove(0);
                    int idx3 = table.get(arr.get(R)).remove(0);
                    System.out.println((idx1 + 1) + " " + (idx2 + 1) + " " + (idx3 + 1));
                    return;
                }
                if (arr.get(i) + arr.get(L) + arr.get(R) < target)
                {
                    L = L + 1;
                }
                else
                {
                    R = R - 1;
                }
            }
        }
        System.out.println (-1);
        
    }
}
