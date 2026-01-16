import java.io.*;
import java.util.*;
 
public class postorder
{
    static int[] inOrder;
    static int[] preOrder;
    
    static Map<Integer, Integer> inOrderMap = new HashMap<>();;
    static List<Integer> postOrderResult = new ArrayList<>();
    static int preOrderIndex = 0;
 
 
    public static void buildPostOrder(int inStart, int inEnd)
    {
        if (inStart > inEnd)
        {
            return;
        }
 
        int rootValue = preOrder[preOrderIndex];
        preOrderIndex++;
 
        int inRootIndex = inOrderMap.get(rootValue);
 
        buildPostOrder(inStart, inRootIndex - 1);
        buildPostOrder(inRootIndex + 1, inEnd);
 
        postOrderResult.add(rootValue);
    }
    
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        inOrder = new int[N];
        preOrder = new int[N];
        
        StringTokenizer stIn = new StringTokenizer(br.readLine());
        int x = 0;
        while (stIn.hasMoreTokens())
        {
            inOrder[x] = Integer.parseInt(stIn.nextToken());
            inOrderMap.put(inOrder[x], x++);
        }
 
        x = 0;
        StringTokenizer stPre = new StringTokenizer(br.readLine());
        while (stPre.hasMoreTokens())
        {
            preOrder[x++] = Integer.parseInt(stPre.nextToken());
        }
 
        buildPostOrder(0, N - 1);
 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < postOrderResult.size(); i++)
        {
            sb.append(postOrderResult.get(i));
            if (i < postOrderResult.size() - 1)
            {
                sb.append(" ");
            }
        }
        
        System.out.println(sb.toString());
    }
}
