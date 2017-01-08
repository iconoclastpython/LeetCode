Morris Traversal:
space complexity O(1), time complexity O(n);
much betrer than recursive and iterative method,
which use stack, costs O(n) space, O(n) time

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
------------------------------------------------------
Lambda Expression:

External Iteration:
1. process one by one;
2. cannot efficiently use multicore cpu;
3. not good for compiler optimization;

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
for(Integer num : numbers) System.out.print(num);


Internal Interation:
1. efficiently using multicore cpu;
2. concurrent execution;
3. good for JIT compiler optimization;

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.forEach((Integer val) -> System.out.print(val));

//Comparator lambda
Collections.sort(listObject, (Object a1, Object a2) -> a1.val.compareTo(a2.val));

------------------------------------------------------
Boyer-Moore Majority Vote Algorithm:
http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
------------------------------------------------------
char -> String: Character.toString(char c);
char[] -> String: String.valueOf(char[] chars);
String can not delete element;
StringBuilder can delete element by:
	delete(int start, int end);
	deleteCharAt(int index);
String cannot reverse in its index;
String -> int: Integer.parseInt(String s);
int -> String: String.valueOf(int num);
char -> int: Character.getNumericValue(char c);
------------------------------------------------------
For Trees:

int height(TreeNode) {
    return node == null ? -1 : Math.max(height(node.left), height(node.right));
}

int countNodes(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int conut = 1;
    while(!queue.isEmpty()) {
        TreeNode cur = queue.poll();
        if(cur.left != null) {
            queue.add(cur.left);
            count++;
        }
        if(cur.right != null) {
            queue.add(cur.right);
            count++;
        }
    }
    return count;
}

boolean isValidBST(TreeNode root) {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

boolean isValidBST(TreeNode root, long minVal, long maxVal) {
    if(root == null) return true;
    if(root.val >= maxVal || root.val <= minVal) return false;
    return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
}

void BFS(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while(!queue.isEmpty()) {
        TreeNode cur = queue.poll();
        // Do something here
        if(cur.left != null) queue.add(cur.left);
        if(cur.right != null) queue.add(cur.right);
        // Do somethign here
    }
}

// Preorder Traversal:
void DFS(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();
    //stack.push(root);
    TreeNode cur = root;
    while(!stack.isEmpty()) {
    //    TreeNode cur = stack.pop();
        res.add(cur.val);
        if(cur.right != null)
            stack.push(cur.right);
        cur = cur.left;
        if(cur == null && !stack.isEmpty())
            cur = stack.pop();
    }
    return res;
}












