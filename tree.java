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
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty())
        {
            int level = queue.size();
            List<Integer> curList = new ArrayList<>();

            for(int i = 0; i < level; i++)
            {
                TreeNode curNode = queue.poll();
                if(curNode.left != null) queue.add(curNode.left);
                if(curNode.right != null) queue.add(curNode.right);
                curList.add(curNode.val);
            }
            res.add(curList);
            //res.add(0, curList); // for bottom up order traversal
        }

        return res;
    }
---------------------------------------------------------
Dec 16:

Binary Tree Zigzag Level Order Traversal:

// My Iteration Solution
public class Solution {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        while(!queue.isEmpty()) {
            int sizeQ = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < sizeQ; i++) {
                TreeNode cur = queue.poll();
                
                if(level % 2 == 1) list.add(cur.val);
                else list.add(0, cur.val);
                    
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);
            }
            res.add(list);
            level++;
        }
        return res;
    }
}

// Traverse
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

    pathSum(root.left, sum-root.val, cur, res);
    pathSum(root.right, sum-root.val, cur, res);

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

public static List<Integer> preorder(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while(!stack.empty()) {
        TreeNode node = stack.pop();
        res.add(node.val);
        if(node.right != null) {
            stack.push(node.right);
        }
        if(node.left != null) {
            stack.push(node.left);
        }
    }
    return res;
}

// Traversal
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        return res;
    }

    private void traverse(TreeNode root, List<Integer> res) {
        if(root == null) return;

        res.add(root.val);
        traverse(root.left, res);
        traverse(root.right, res);
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

// Traversal
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        return res;
    }

    private void traverse(TreeNode root, List<Integer> res) {
        if(root == null) return;

        traverse(root.left, res);
        res.add(root.val);
        traverse(root.right, res);
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

// Traversal
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, res);
        return res;
    }

    private void traverse(TreeNode root, List<Integer> res) {
        if(root == null) return;

        traverse(root.left, res);
        traverse(root.right, res);
        res.add(root.val);
    }
}
---------------------------------------------------------

Dec 19:
Delete Node in a BST:
public class Solution {
    private TreeNode findMin(TreeNode root) {
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val < key) root.right = deleteNode(root.right, key);
        else if(root.val > key) root.left = deleteNode(root.left, key);
        else {
            if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            else {
                TreeNode minVal = findMin(root.right);
                root.val = minVal.val;
                root.right = deleteNode(root.right, minVal.val);
            }
        }
        return root;
    }
}
---------------------------------------------------------

Kth Smallest Element in a BST:
// Use inorder traversal, find k th in the list
public int kthSmallest(TreeNode root, int k) {
    List<Integer> res = inorderTraversal(root);
    return res.get(k-1);
}
---------------------------------------------------------

Binary Tree Right Side View:
// My iterative solution:
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                if(level == res.size()) res.add(curNode.val);
                if(curNode.right != null) queue.add(curNode.right);
                if(curNode.left != null) queue.add(curNode.left);
            }
            level++;
        }
        return res;
    }
}

// Recursive solution:
public class Solution {
    public List<Integer> rightSideView(TreeNode root) 
    {
        List<Integer> res = new LinkedList<>();
        reightSideView(root, res, 0);
        return res;
    }

    public void rightSideView(TreeNode root, List<Integer> res, int curDepth)
    {
        if(root == null) return ;
        if(curDepth = res.size()) res.add(root.val);

        rightSideView(root.right, res, curDepth+1);
        rightSideView(root.left, res, curDepth+1);
    }
}
---------------------------------------------------------

Validate Binary Search Tree:

public class Solution {
    public boolean isValidBST(TreeNode root) 
    {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBST(TreeNode root, long minVal, long maxVal)
    {
        if(root == null) return true;
        if(root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}
---------------------------------------------------------

Convert Sorted Array to Binary Search Tree:

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) 
    {
        if(nums.length == 0) return null;
        TreeNode root = buildBST(nums, 0, nums.length-1);
        return root;
    }
    
    private TreeNode buildBST(int[] nums, int low, int high)
    {
        if(low > high) return null;
        int mid = low + (high-low)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildBST(nums, low, mid-1);
        node.right = buildBST(nums, mid+1, high);
        return node;
    }
}
---------------------------------------------------------

Count Complete Tree Nodes:
public class Solution {
    
    public int countNodes(TreeNode root)
    {
        int h = height(root);
        
        return h < 0 ? 0 : (height(root.right) == h-1 ? 
                                (1 << h) + countNodes(root.right) : 
                                (1 << h-1) + countNodes(root.left));
    }

    private int height(TreeNode root)
    {
        return root == null ? -1 : height(root.left) + 1;
    }
}
---------------------------------------------------------

Sum Root to Leaf Numbers:
public class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        return sumNumbers(root, 0);
    }
    private int sumNumbers(TreeNode root, int num) {
        if(root.left == null && root.right == null) 
            return num*10 + root.val;
        int sum = 0;
        if(root.left != null) 
            sum += sumNumbers(root.left, num*10 + root.val);
        if(root.right != null) 
            sum += sumNumbers(root.right, num*10 + root.val);
        return sum;
    }
}
---------------------------------------------------------

Flatten Binary Tree to Linked List：
public class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return ;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if(cur.right != null) stack.push(cur.right);
            if(cur.left != null) stack.push(cur.left);
            if(!stack.isEmpty()) cur.right = stack.peek();
            cur.left = null;
        }
    }
}
---------------------------------------------------------

Closest Binary Search Tree Value:
public class Solution {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while(root != null) {
            if(Math.abs(closest - target) > Math.abs(root.val - target)) {
                closest = root.val;
            }
            root = root.val < target ? root.right : root.left;
        }
        return closest;
    }
    
}
---------------------------------------------------------

Closest Binary Search Tree Value II:
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        inorder(root, target, false, s1);
        inorder(root, target, true, s2);
        while(k-- > 0) {
            if(s1.isEmpty())
                res.add(s2.pop());
            else if(s2.isEmpty())
                res.add(s1.pop());
            else if(Math.abs(s1.peek()-target) < Math.abs(s2.peek()-target))
                res.add(s1.pop());
            else if(Math.abs(s1.peek()-target) >= Math.abs(s2.peek()-target))
                res.add(s2.pop());
        }
        return res;
    }
    
    private void inorder(TreeNode root, double target, boolean reversed, Stack<Integer> stack) {
        if(root == null) return;
        inorder(reversed ? root.right : root.left, target, reversed, stack);
        if((reversed && root.val <= target) || (!reversed && root.val > target)) return ;
        stack.push(root.val);
        inorder(reversed ? root.left : root.right, target, reversed, stack);
    }
}
---------------------------------------------------------

Sum of Left Leaves:
public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 0;
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur.right != null) queue.add(cur.right);
            while(cur != null && cur.left != null) {
                cur = cur.left;
                if(cur.right != null) queue.add(cur.right);
                if(cur.left == null && cur.right == null) {
                    sum += cur.val;
                }
            }
        }
        return sum;
    }
}
---------------------------------------------------------

Closest Binary Search Tree Value II:
public class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        inorder(root, target, false, s1);
        inorder(root, target, true, s2);
        while(k-- > 0) {
            if(s1.isEmpty())
                res.add(s2.pop());
            else if(s2.isEmpty())
                res.add(s1.pop());
            else if(Math.abs(s1.peek()-target) < Math.abs(s2.peek()-target))
                res.add(s1.pop());
            else if(Math.abs(s1.peek()-target) >= Math.abs(s2.peek()-target))
                res.add(s2.pop());
        }
        return res;
    }
    
    private void inorder(TreeNode root, double target, boolean reversed, Stack<Integer> stack) {
        if(root == null) return;
        inorder(reversed ? root.right : root.left, target, reversed, stack);
        if((reversed && root.val <= target) || (!reversed && root.val > target)) return ;
        stack.push(root.val);
        inorder(reversed ? root.left : root.right, target, reversed, stack);
    }
}

---------------------------------------------------------

Inorder Successor in BST:
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while(root != null) {
            if(p.val < root.val) {
                res = root;
                root = root.left;
            }
            else
                root = root.right;
        }
        return res;
    }
}
---------------------------------------------------------

Binary Tree Longest Consecutive Sequence:
public class Solution {
    int maxVal = 0;
    
    public int longestConsecutive(TreeNode root) {
        if(root == null) return 0;
        helper(root, 0, root.val);
        return maxVal;
    }
    
    private void helper(TreeNode root, int curVal, int target) {
        if(root == null) return;
        if(root.val == target) curVal++;
        else curVal = 1;
        maxVal = Math.max(curVal, maxVal);
        helper(root.left, curVal, root.val+1);
        helper(root.right, curVal, root.val+1);
    }
}
---------------------------------------------------------

Find Leaves of Binary Tree:
public class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }
    
    private int height(TreeNode node, List<List<Integer>> res) {
        if(node == null) return -1;
        int level = 1 + Math.max(height(node.left, res), height(node.right, res));
        if(res.size() < level+1) res.add(new ArrayList<>());
        res.get(level).add(node.val);
        return level;
    }
}
---------------------------------------------------------

Verify Preorder Sequence in Binary Search Tree:
// Using stack to present a tree preorder trversal
public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int p : preorder) {
            if(p < low) return false;
            while(!stack.isEmpty() && p > stack.peek()) {
                low = stack.pop();
            }
            stack.push(p);
        }
        return true;
    }
}
---------------------------------------------------------

Largest BST Subtree:
public class Solution {
    int count = 0;
    
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(cur.left != null) queue.add(cur.left);
            if(cur.right != null) queue.add(cur.right);
            if(isValidBST(cur, Long.MIN_VALUE, Long.MAX_VALUE)) {
                count = Math.max(count, countNodes(cur));
            }
        }
        return count;
    }
    
    private boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if(root == null) return true;
        if(root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
    
    private int countNodes(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int curSize = 1;
        while(!queue.isEmpty()) {
             TreeNode cur = queue.poll();
             if(cur.left != null) {
                 queue.add(cur.left);
                 curSize++;
             }
             if(cur.right != null) {
                 queue.add(cur.right);
                 curSize++;
             }
        }
        return curSize;
    }
}
---------------------------------------------------------
