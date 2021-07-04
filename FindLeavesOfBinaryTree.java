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
     * 
     * Runtime: 1 ms, faster than 24.56% of Java online submissions.
	 * Memory Usage: 39.4 MB, less than 5.69% of Java online submissions.
     */
    static List<List<Integer>> findLeaves0(TreeNode root) {
        
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
     * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
     * 
     * o Collect all the leaf nodes.
     * o Remove all the leaf nodes.
     * o Repeat until the tree is empty.
     * 
     * Runtime: O(n)  Space: O(n)
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 37.3 MB, less than 79.87% of Java online submissions.
     */
    static List<List<Integer>> findLeaves(TreeNode root) {

        // **** initialization ****
        List<List<Integer>> lol = new ArrayList<>();

        // **** loop until we have no more nodes to process ****
        while (root != null) {

            // **** new list of leaves ****
            List<Integer> leaves = new ArrayList<>();

            // **** find leaf nodes on this pass ****
            root = findLeaves(root, leaves);

            // ???? ????
            // System.out.println("findLeaves <<< leaves: " + leaves.toString());

            // **** add list of leaf nodes to the list ****
            lol.add(leaves);
        }

        // **** return list of lists ****
        return lol;
    }


    /**
     * Recursive call.
     */
    static TreeNode findLeaves(TreeNode root, List<Integer> leaves) {

        // **** base condition(s) ****
        if (root == null) return root;

        // ???? ????
        // System.out.println("findLeaves <<< val: " + root.val);

        // **** found leaf node ****
        if (root.left == null && root.right == null) {

            // **** add it to list ****
            leaves.add(root.val);

            // **** ****
            return null;
        }

        // **** recursion on left and right sub-trees ****
        root.left = findLeaves(root.left, leaves);
        root.right = findLeaves(root.right, leaves);

        // **** return root ****
        return root;
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
        // System.out.println("main <<< findLeaves0: " + findLeaves0(bst.root));

        // **** compute and display result ****
        System.out.println("main <<< findLeaves: " + findLeaves(bst.root));
    }
}