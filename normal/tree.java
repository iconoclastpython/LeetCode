Balanced Binary Tree:

public class Solution {
    
    public int depth(TreeNode root)
    {
        if(root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
    
    public boolean isBalanced(TreeNode root) 
    {
        if(root == null) return true;
        
        int left = depth(root.left);
        int right = depth(root.right);
        
        return Math.abs(left-right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
}
---------------------------------------------------------

Same Tree:  

public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) 
    {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
---------------------------------------------------------

Binary Tree Level Order Traversal:

 public static List<List<Integer>> levelOrderI(TreeNode root)
    {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty())
        {
            int level = queue.size();
            List<Integer> curList = new ArrayList<>();

            for(int i = 0; i < level; i++)
            {
                TreeNode curNode = queue.peek();
                if(curNode.left != null) queue.add(curNode.left);
                if(curNode.right != null) queue.add(curNode.right);
                curList.add(queue.poll().val);
            }
            res.add(curList);
            //res.add(0, curList); // for bottom up order traversal
        }

        return res;
    }
---------------------------------------------------------