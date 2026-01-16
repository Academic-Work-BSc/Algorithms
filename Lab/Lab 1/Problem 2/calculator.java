import java.util.Scanner;
public class calculator
{
  public static void main (String[] args)
  {
    Scanner sc = new Scanner (System.in);
    int T = sc.nextInt();
    sc.nextLine();
    
    for (int i = 0; i < T; i++)
    {
      String input = sc.nextLine();
      String[] arr = input.split (" ");
      
      int n1 = Integer.parseInt (arr[1]);
      double nn1 = (double) n1;
      String operator = arr[2];
      int n2 = Integer.parseInt (arr[3]);
      double nn2 = (double) n2;
      
      if (operator.equals ("+"))
      {
        System.out.println (nn1 + nn2);
      }
      else if (operator.equals ("-"))
      {
        System.out.println (nn1 - nn2);
      }
      else if (operator.equals ("*"))
      {
        System.out.println (nn1 * nn2);
      }
      else if (operator.equals ("/"))
      {
        System.out.println (nn1 / nn2);
      }
    }
  }
}
