import java.io.*;
import java.util.*;
 
public class preorder
{
    static int[] inOrder;
    static int[] postOrder;
    
    static Map<Integer, Integer> inOrderMap;
    static LinkedList<Integer> preOrderResult;
    static int postOrderIndex;
 
    public static void buildPreOrder(int inStart, int inEnd)
    {
        if (inStart > inEnd)
        {
            return;
        }
 
        int rootValue = postOrder[postOrderIndex];
        postOrderIndex--;
 
        int inRootIndex = inOrderMap.get(rootValue);
 
        buildPreOrder(inRootIndex + 1, inEnd);
        buildPreOrder(inStart, inRootIndex - 1);
 
        preOrderResult.addFirst(rootValue);
    }
 
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        inOrder = new int[N];
        postOrder = new int[N];
        postOrderIndex = N - 1;
        inOrderMap = new HashMap<>();
        preOrderResult = new LinkedList<>();
        
        StringTokenizer stIn = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
        {
            inOrder[i] = Integer.parseInt(stIn.nextToken());
            inOrderMap.put(inOrder[i], i);
        }
 
        StringTokenizer stPost = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
        {
            postOrder[i] = Integer.parseInt(stPost.nextToken());
        }
 
        buildPreOrder(0, N - 1);
 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < preOrderResult.size(); i++)
        {
            sb.append(preOrderResult.get(i));
            if (i < preOrderResult.size() - 1)
            {
                sb.append(" ");
            }
        }
        
        System.out.println(sb.toString());
    }
}