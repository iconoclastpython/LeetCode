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
    public List<Integer> postorderTraversal(TreeNode root) 
    {
        List<Integer> res = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null) return res;
        stack.push(root);

        while(!stack.isEmpty())
        {
            TreeNode cur = stack.pop();
            res.addFirst(cur.val);

            if(cur.left != null)
                stack.push(cur.left);
            if(cur.right != null)
                stack.push(cur.right);
        }

        return res;
    }
}
-------------------------------------------------------------
Preorder Traversal:                                         -

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root)   -
    {
        List<Integer> res = new LinkedList<Integer>();      
        Stack<TreeNode> stack = new Stack<TreeNode>();      -
        TreeNode cur = root;
        
        while(!stack.isEmpty() || cur != null)              -
        {
            if(cur != null)
            {
                stack.push(cur);                            -
                res.add(cur.val);
                cur = cur.left;
            }                                               -
            else
            {
                TreeNode node = stack.pop();                -
                cur = node.right;
            }
        }                                                   -

        return res;
    }                                                       -
}

                                                            -
-------------------------------------------------------------
Inorder Traversal:

public class Solution {                                     -
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;                                -

        while(!stack.isEmpty() || cur != null)
        {
            if(cur != null)                                 -
            {
                stack.push(cur);
                cur = cur.left;
            }                                               -
            else
            {
                TreeNode node = stack.pop();
                res.add(node.val);                          -
                cur = node.right;
            } 
        }
                                                            -
        return res;
    }
}                                                           -
-------------------------------------------------------------

Binary Tree Postorder Traversal:                            -

public class Solution {
    public List<Integer> postorderTraversal(TreeNode root)  -
    {
        List<Integer> res = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;                                -

        while(!stack.isEmpty() || cur != null)
        {
            if(cur != null)                                 -
            {
                stack.push(cur);
                res.addFirst(cur.val);
                cur = cur.right;                            -
            }
            else
            {
                TreeNode node = stack.pop();                -
                cur = node.left;        
            }
        }                                                   -

        return res;
    }
}                                                           -
-------------------------------------------------------------

Kth Smallest Element in a BST:

public class Solution {
    public int kthSmallest(TreeNode root, int k) 
    {
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while(root != null)
        {
            stack.push(root);
            root = root.left;
        }
        
        while(!stack.isEmpty())
        {
            TreeNode cur = stack.pop();
            if(--k == 0) return cur.val;
            TreeNode right = cur.right;

            while(right != null)
            {
                stack.push(right);
                right = right.left;
            }
        }

        return -1;
    }
}
---------------------------------------------------------

Binary Tree Right Side View:

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
    public int sumNumbers(TreeNode root) 
    {
        if(root == null) return 0;
        return sumPath(root, 0);
    }
    
    private int sumPath(TreeNode root, int num)
    {
        if(root.left != null && root.right != null)
            return num*10 + root.val;
        int val = 0;
        if(root.left != null)
            val += sumPath(root.left, num*10 + root.val);
        if(root.right != null)
            val += sumPath(root.right, num*10 + root.val);
        return val;
    }
}
---------------------------------------------------------

Flatten Binary Tree to Linked List:

public class Solution {
    
    private TreeNode prev = null;
    
    public void flatten(TreeNode root) 
    {
        if(root == null) return ;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
---------------------------------------------------------

Serialize and Deserialize Binary Tree:

public class Codec {
    static final String spliter = ",";
    static final String NN = "null";

    String serialize(TreeNode root)
    {
        Stringbiulder sb = new Stringbiulder();
        buildString(root, sb);
        return sb.toString();
    }

    TreeNode deserialize(String data)
    {
        Deque<String> nodes = new LindedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    void buildString(TreeNode node, Stringbiulder sb)
    {
        if(node == null)
        {
            sb.append(NN+spliter);
        }
        else
        {
            sb.append(node.val+spliter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }    
        
    }

    TreeNode buildTree(Deque<String> nodes)
    {
        String val = nodes.remove();
        if(val.equals(NN)) return null;
        else
        {
            TreeNode node = new TreeNode(val);
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}
---------------------------------------------------------

Populating Next Right Pointers in Each Node:
Notes: Assume perfict binary tree, use constant space.
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    public void connect(TreeLinkNode root) 
    {
        /* Two pointers: 
        keep pre at the left node path,
        keep cur at the node in the same level,
        no need to set the most right next to null,
        since all next initially set to null already.*/
        if(root == null) return ;
        TreeLinkNode pre = root;
        TreeLinkNode cur = null;

        while(pre.left != null) //move down along left most path
        {
            cur = pre;

            while(cur != null) //deal with the same level nodes
            {
                cur.left.next = cur.right;
                if(cur.next != null)
                    cur.right.next = cur.next.left;
                cur = cur.next; //move right in the same level
            }

            pre = pre.left; //move down
        }
    }
}
---------------------------------------------------------

Populating Next Right Pointers in Each Node II:
Notes: No longer a perfect binary tree
runtime O(n), space O(1)
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    public void connectII(TreeLinkNode root) 
    {
        TreeLinkNode head = null; //head of next level
        TreeLinkNode pre = null;  //leading node of current chain(->) of next level
        TreeLinkNode cur = root;

        while(cur != null) //left path
        {
            while(cur != null) //current level
            {
                if(cur.left != null) //left child
                {
                    if(pre != null)
                        pre.next = cur.left;
                    else
                        head = cur.left;

                    pre = cur.left;
                }

                if(cur.right != null) //right child
                {
                    if(cur.right != null)
                        pre.next = cur.right;
                    else
                        head = cur.right;

                    pre = cur.right;
                }

                cur = cur.next; //move to the next node
            }

            cur = head; //move to the next level
            head = null;
            pre = null;
        }
    }
}
---------------------------------------------------------

Recover Binary Search Tree (Hard):

public class Solution {
    public void recoverTree(TreeNode root) 
    {
        List<TreeNode> errNodes = new LinkedList<>();
        TreeNode preNode = null;
        TreeNode pre = null;
        TreeNode cur = root;

        while(cur != null)
        {
            if(cur.left == null)
            {
                //print cur.val here
                if(preNode != null && preNode.val > cur.val)
                {
                    errNodes.add(preNode);
                    errNodes.add(cur);
                }
                preNode = cur;
                cur = cur.right;
            }
            else
            {
                pre = cur.left;

                while(pre.right != null && pre.right != cur)
                {
                    pre = pre.right;
                }

                if(pre.right == null)
                {
                    pre.right = cur;
                    cur = cur.left;
                }
                else    //pre point to cur
                {
                    pre.right = null;
                    //print cur.val here
                    if(preNode != null && preNode.val > cur.val)
                    {
                        errNodes.add(preNode);
                        errNodes.add(cur);
                    }
                    preNode = cur;
                    cur = cur.right;
                }
            }
        }

        if(errNodes.size() == 2)
        {
            pre = errNodes.get(0);
            cur = errNodes.get(1);
        }
        else if(errNodes.size() == 4)
        {
            pre = errNodes.get(0);
            cur = errNodes.get(3);
        }

        int temp = pre.val;
        pre.val = cur.val;
        cur.val = temp;
    }
}

-------------------------
Background Knowledge: 
Morris Traversal(pre-order): 
O(n) Time, O(1) Space
stack and iterative cost O(n) time and O(n) space

public class Solution {
    public void MorriTraversal(TreeNode root) 
    {
        TreeNode pre, = null;
        TreeNode cur = root;

        while(cur != null)
        {
            if(cur.left == null)
            {
                //print cur.val here
                cur = cur.right;
            }
            else
            {
                pre = cur.left;

                while(pre.right != null && pre.right != cur)
                {
                    pre = pre.right;
                }

                if(pre.right == null)
                {
                    pre.right = cur;
                    cur = cur.left;
                }
                else    //pre point to cur
                {
                    pre.right = null;
                    //print cur.val here
                    cur = cur.right;
                }
            }
        }
    }
}
---------------------------------------------------------

Reconstruct Itinerary:
[from, to]

public class Solution {
    public List<String> findItinerary(String[][] tickets) 
    {
        List<String> res = new ArrayList<>();
        if(tickets == null || tickets.length == 0) return res;
        Map<String, PriorityQueue<String>> map = new HashMap<>();

        for(int i = 0; i < tickets.length; i++)
        {
            if(!map.containsKey(tickets[i][0]))
                map.put(tickets[i][0], new PriorityQueue<String>());
            map.get(tickets[i][0]).add(tickets[i][1]);
        }

        String cur = "JFK";
        Stack<String> drawback = new Stack<>();

        for(int i = 0; i < tickets.length; i++)
        {
            while(!map.containsKey(cur) || map.get(cur).isEmpty())
            {
                drawback.push(cur);
                cur = res.remove(res.size()-1);
            }
            res.add(cur);
            cur = map.get(cur).poll();
        }

        res.add(cur);
        while(!drawback.isEmpty())
            res.add(drawback.pop());
        return res;
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

