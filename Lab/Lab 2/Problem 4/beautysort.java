import java.io.*;

public class beautysort
{
    public static void main (String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

        int N1 = Integer.parseInt (br.readLine());

        String nums1 = br.readLine();
        String[] nums1p = nums1.split(" ");

        int[] arr1 = new int[N1];

        for (int i = 0; i<N1; i++)
        {
            arr1[i] = Integer.parseInt (nums1p[i]);
        }

        int N2 = Integer.parseInt (br.readLine());

        String nums2 = br.readLine();
        String[] nums2p = nums2.split(" ");

        int[] arr2 = new int[N2];

        for (int i = 0; i<N2; i++)
        {
            arr2[i] = Integer.parseInt (nums2p[i]);
        }

        int l1 = 0;
        int l2 = 0;

        int[] arr = new int[N1+N2];
        for (int i = 0; i < (N1 + N2); i++)
        {
            if ((l1<N1)&&(l2<N2))
            {
                if (arr1[l1]<arr2[l2])
                {
                    arr[i] = arr1[l1];
                    l1++;
                }
                else
                {
                    arr[i] = arr2[l2];
                    l2++;
                }
            }
            else if (l1==N1)
            {
                arr[i] = arr2[l2];
                l2++;
            }
            else if (l2 == N2)
            {
                arr[i] = arr1[l1];
                l1++; 
            }
        }
        for (int i = 0; i<arr.length; i++)
        {
            System.out.print (arr[i] + " ");
        }
        System.out.println();
    }
}