Morris Traversal:
space complexity O(1), time complexity O(n);
much betrer than recursive and iterative method,
which use stack, costs O(n) space, O(n) time

public class Solution {
    public void MorriTraversal(TreeNode root) 
    {
        TreeNode pre, = null;
        TreeNode cur = root;

        while(cur != null){
            if(cur.left == null){
                //print cur.val here
                cur = cur.right;
            }
            else{
                pre = cur.left;

                while(pre.right != null && pre.right != cur){
                    pre = pre.right;
                }

                if(pre.right == null){
                    pre.right = cur;
                    cur = cur.left;
                }
                else    //pre point to cur{
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
Collections.sort(listObject, (Object a1, Object a2) -> (a1.val - a2.val));


// Sort HashMap by value(Yelp)
List<Integer> searchTopTen(Map<Integer, Integer> data) {
    List<Map.Entry<Integer, Integer>> dataSort = new ArrayList<>(data.entrySet());

    Collections.sort(dataSort, new Comparator<Map.Entry<Integer, Integer>>(){
        @Override
        public int compare(Map.Entry<Integer, Integer> d1, Map.Entry<Integer, Integer> d2) {
            return d2.getValue().compareTo(d1.getValue());
        }
    });

    List<Integer> topStores = new ArrayList<>();
    for(int i = 0; i < 10; i++) {
        topStores.add(dataSort.get(i).getKey());
    }
    return topStores
}


Iterator iter = collect.iterator();
while(iter.hasNext()) {
    int num = iter.next();
}


Quick Sort:
public void quickSort(int[] nums, int left, int right) {
    if(left >= right) return;
    int partitionIndex = partition(nums, left, right);
    quickSort(nums, left, partitionIndex-1);
    quickSort(nums, partitionIndex+1, right);
}

Partition Select:
// Average O(n) by random select
// return the index of the pivot element after partition
public int partition(int[] nums, int left, int right) {
    //int pivot = nums[left];
    Random rand = new Random();
    int pivotIndex = rand.nextInt(right-left) + left;
    int pivot = nums[pivotIndex];

    int tmp = nums[pivotIndex];
    nums[pivotIndex] = nums[left];
    nums[left] = tmp;

    while(left < right) {
        while(left < right && nums[right] >= pivot) right--;
        nums[left] = nums[right];
        while(left < right && nums[left] <= pivot) left++;
        nums[right] = nums[left];
    }
    nums[left] = pivot;
    return left;
}


Bucket Sort:
1. Map all the elements(key) with their frequency(value);
2. Use map value(frequency) as index, map key(elements) as content, build bucket;
3. The largest existed index contains most frequent element;


Structure a singly Linked List from tail to head(LC445):
// node is given, using while loop
ListNode tmpHead = new ListNode(value);
tmpHead.next = node;
node = tmpHead;

LinkedList dummy node: When first node need to change, use dummy.

------------------------------------------------------
Boyer-Moore Majority Vote Algorithm:
http://www.cs.utexas.edu/~moore/best-ideas/mjrty/
------------------------------------------------------
char -> String: Character.toString(char c);
char[] -> String: String.valueOf(char[] chars);
char[] -> String: new String(chars);
String can not delete element;
StringBuilder can delete element by:
	delete(int start, int end);
	deleteCharAt(int index);
StringBuilder properties:
    insert(int index, String s);
String properties:
    startsWith(String prefix, int index);
String cannot reverse in its index;
String replace char at index:
    char[] chars = s.toCharArray();
    chars[i] = 'x';
    return new String(chars);
String -> int: Integer.parseInt(String s);
int -> String: String.valueOf(int num);
char -> int: Character.getNumericValue(char c);



Java "break" statement only jump out from the inter loop;
------------------------------------------------------
For Trees:

int height(TreeNode root) {
    if(root == null) return 0;
    return Math.max(height(root.left), height(root.right)) + 1;
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


------------------------------------------------------
Substring Problem Model:

type findSubstring(String str, String sub) {
    int[] map = new int[128];
    int count = sub.length(), int start = 0, end = 0;
    for(char c : sub.toCharArray()) map[c]++;
    while(end < str.length()) {
        if(map[str.charAt(end++)]-- > 0 /* condition */) count--; /* Modify count */
        while(count == 0/* count condition */) {
            /* Do job here */
            if(map[sub.charAt(start++)]++ >=0 /* condition */) count++; /* Modify count */

        }
        /* update count if reach maximum */
    }
    return anything
}
------------------------------------------------------

OOD:
1. Use Case;
2. Entities;
3. Interface;
4. Class for Objects;
5. Inheritance for multi-class;
6. Abstract;
7. Map relationship between entities;
8. is-a and has-a relationship;







