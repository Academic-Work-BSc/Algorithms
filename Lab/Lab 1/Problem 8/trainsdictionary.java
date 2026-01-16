import java.util.*;

public class trainsdictionary
{
    public static void main (String [] args)
    {
        Scanner sc = new Scanner (System.in);
        int t = sc.nextInt();
        sc.nextLine();

        Dictionary <String, List<String[]>> trains = new Hashtable <>();

        for (int i = 0; i < t; i++)
        {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            String name = parts [0];
            String time = parts[6];
            String[] timeparts = time.split (":");
            int timemins = ( Integer.parseInt(timeparts[0]) * 60 ) + (Integer.parseInt(timeparts[1]));

            List <String[]> list = trains.get(name);

            if (list == null)
            {
                list = new ArrayList<>();
            }

            list.add(new String[] { String.valueOf(timemins), line });
            trains.put (name, list);
        }

        List <String> trainnames = Collections.list (trains.keys());
        Collections.sort (trainnames);

        for (int i = 0; i < trainnames.size(); i++)
        {
            String trainname = trainnames.get(i);
            List <String[]> info = trains.get (trainname);

              Collections.sort (info, new Comparator<String[]>() {
                public int compare(String[] a, String[] b) 
                {return Integer.parseInt(b[0]) - Integer.parseInt(a[0]);}});
            for (int j = 0; j < info.size(); j++) 
            {
                System.out.println(info.get(j)[1]);
            }
        }


    }
}