import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 */
public class FindLeavesOfBinaryTree {


    /**
     * DFS approach using a modified depth for a binary tree.
     */
    static Integer dfs(TreeNode root, List<List<Integer>> al) {

        // **** base case ****
        if (root == null) return -1;


        // **** recursion - compute reverse depth ****
        int depth = Math.max(dfs(root.left, al), dfs(root.right, al)) + 1;

        // int ld = dfs(root.left, al);
        // int rd = dfs(root.right, al);

        // ???? ????
        // System.out.println("dfs <<<  ld: " + ld + " rd: " + rd);

        // **** reverse depth ****
        // int depth = Math.max(ld, rd) + 1; 

        // ???? ????
        // System.out.println("dfs <<< val: " + root.val + " depth: " + depth);

        
        // **** create new array list (if needed) ****
        if (al.size() <= depth)
            al.add(new ArrayList<>());

        // **** add node value to proper list ****
        al.get(depth).add(root.val);

        // ???? ????
        System.out.println("dfs <<< depth: " + depth + " al: " + al.toString());

        // **** return current reverse depth ****
        return depth;
    }


    /**
     * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
     * 
     * o Collect all the leaf nodes.
     * o Remove all the leaf nodes.
     * o Repeat until the tree is empty.
     */
    static List<List<Integer>> findLeaves(TreeNode root) {
        
        // **** initialization ****
        List<List<Integer>> lol = new ArrayList<>();

        // **** base case ****
        if (root == null) return lol;

        // **** recursive call to fill list of lists ****
        dfs(root, lol);

        // **** return list of lists ****
        return lol;
    }


    /**
     * Test scaffold
     * !!! NOT PART OF SOLUTION !!!
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** initialization ****
        // HashSet<Integer> hs = new HashSet<Integer>();
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** create String[] with values for the BST ****
        String[] strArr = br.readLine().trim().split(",");
 
        // **** close buffere reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< strArr.length: " + strArr.length);
        System.out.println("main <<< strArr: " + Arrays.toString(strArr));

        // **** generate an Integer[] to build the binary tree ****
        Integer[] arr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("null") || strArr[i].isEmpty())
                arr[i] = null;
            else
                arr[i] = Integer.parseInt(strArr[i]);
        }

        // ???? ????
        System.out.println("main <<< arr: " + Arrays.toString(arr));

        // // **** check and count duplicate values ****
        // int duplicateValues = 0;
        // for (int i = 0; i < arr.length; i++) {

        //     // **** skip null values ****
        //     if (arr[i] == null)
        //         continue;
  
        //     // **** ****
        //     if (hs.contains(arr[i]) == true)
        //         System.out.println("main <<< arr[" + i + "]: " + arr[i] 
        //             + " duplicateValues: " + ++duplicateValues);
        //     else
        //         hs.add(arr[i]);
        // }

        // **** create and populate the binary tree ****
        BST bst = new BST();
        bst.root = bst.populate(arr);

        // ???? ????
        System.out.println("main <<< bst.inOrder: " + bst.inOrder(bst.root));

        // ???? ????
        System.out.print("main <<< bst.levelOrderTraversal: ");
        System.out.println(bst.levelOrderTraversal(bst.root).toString());

        // **** compute and display result ****
        System.out.println("main <<< findLeaves: " + findLeaves(bst.root));
    }
}