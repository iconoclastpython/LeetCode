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
Dec 16:

Binary Tree Zigzag Level Order Traversal:

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) 
    {
        List<List<Integer>> res = new LinkedList<>();
        travel(root, res, 0);
        return res;
    }

    private void travel(TreeNode cur, List<List<Integer>> res, int level)
    {
        if(cur == null) return ;

        if(res.size() <= level)
            res.add(new LinkedList<>());

        List<Integer> curVals = res.get(level); //important
        
        if(level % 2 == 0) curVals.add(cur.val);
        else curVals.add(0, cur.val);

        travel(root.left, res, level+1);
        travel(root.right, res, level+1);
    }
}
---------------------------------------------------------

Path Sum:

public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) 
    {
        if(root == null) return false;
        if(root.val == sum && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
}
---------------------------------------------------------

Path Sum II:

public List<List<Integer>> pathSum(TreeNode root, int sum)
{
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> cur = new LinkedList<>();
    pathSum(root, sum, cur, res);
    return res;
}

public void pathSum(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> res)
{
    if(root == null) return ;
    cur.add(root.val);
    if(root.left == null && root.right == null && root.val == sum)
    {
        res.add(cur);
        cur.removeLast();
        return ;
    }
    else
    {
        pathSum(root.left, sum-root.val, cur, res);
        pathSum(root.right, sum-root.val, cur, res);
    }
    cur.removeLast();
}
---------------------------------------------------------

Binary Tree Maximum Path Sum (Hard):

public class Solution {
    
    int maxVal;
    
    public int maxPathSum(TreeNode root) 
    {
        maxVal = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxVal;
    }
    
    private int maxPathDown(TreeNode node)
    {
        if(node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxVal = Math.max(maxVal, left + right + node.val);
        return Math.max(left, right) + node.val; 
    }
}
---------------------------------------------------------

Symmetric Tree:

public class Solution {
    public boolean isSymmetric(TreeNode root) 
    {
        if(root == null) return true;
        return helper(root.left, root.right);
    }
    
    public boolean helper(TreeNode left, TreeNode right)
    {
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}
---------------------------------------------------------

Lowest Common Ancestor of a Binary Search Tree:

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) 
    {
        if(p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        else if(p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        else return root;
    }
}
---------------------------------------------------------
Lowest Common Ancestor of a Binary Tree:

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) 
    {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        else if(right == null) return left;
        else return root;
    }
}
---------------------------------------------------------

Maximum Depth of Binary Tree:

public class Solution {
    public int maxDepth(TreeNode root) 
    {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
---------------------------------------------------------

Minimum Depth of Binary Tree:

public class Solution {
    public int minDepth(TreeNode root) 
    {
        if(root == null) return 0;
        
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}
---------------------------------------------------------

Dec 18:
Binary Tree Paths:

public class Solution {
    public List<String> binaryTreePaths(TreeNode root) 
    {
        List<String> res = new ArrayList<>();
        if(root != null) helper(root, "", res);
        return res;
    }
    
    public void helper(TreeNode root, String path, List<String> res)
    {
        if(root.left == null && root.right == null) res.add(path + root.val);
        if(root.left != null) helper(root.left, path + root.val + "->", res);
        if(root.right != null) helper(root.right, path + root.val + "->", res);
    }
}
---------------------------------------------------------

Invert Binary Tree:

// First
public class Solution {
    public TreeNode invertTree(TreeNode root) 
    {
        if(root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
}

// Second
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            TreeNode tmp = curNode.left;
            curNode.left = curNode.right;
            curNode.right = tmp;
            if(curNode.left != null) queue.add(curNode.left);
            if(curNode.right != null) queue.add(curNode.right);
        }
        return root;
    }
}
---------------------------------------------------------

Binary Tree Preorder Traversal:

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) 
    {
        List<Integer> res = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        while(root != null)
        {
            res.add(root.val);
            
            if(root.right != null)
                stack.push(root.right);
            
            root = root.left;
            
            if(root == null && !stack.isEmpty())
                root = stack.pop();
        }

        return res;
    }
}
---------------------------------------------------------

Binary Tree Inorder Traversal:

public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;

        while(!stack.isEmpty() || cur != null)
        {
            while(cur != null)
            {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }

        return res;
    }
}

---------------------------------------------------------

Binary Tree Postorder Traversal:  
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        while(root != null) {
            res.add(0, root.val);
            if(root.left != null) stack.push(root.left);
            root = root.right;
            if(root == null && !stack.isEmpty()) {
                root = stack.pop();
            }
        }
        return res;
    }
}
---------------------------------------------------------