Palindrome Linked List:
public class Solution {
    public boolean isPalindrome(ListNode head) 
    {
        if(head == null || head.next == null) return true;
            
        ListNode slow = head, fast = head;
        
        while(fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        slow.next = reverse(slow.next);
        slow = slow.next;
        ListNode check = head;
        
        while(slow != null)
        {
            if(check.val == slow.val)
            {
                check = check.next;
                slow = slow.next;
            }
            else
                return false;
        }
        
        return true;
    }

    private ListNode reverse(ListNode head)
    {
        ListNode temp = null;
        
        while(head != null)
        {
            ListNode next = head.next;
            head.next = temp;
            temp = head;
            head = next;
        }
        
        return temp;
    }
}
----------------------------------------------------

Reverse Linked List:
public class Solution {
    public ListNode reverseList(ListNode head) 
    {
        ListNode newhead = null;
        while(head != null)
        {
            ListNode next = head.next;
            head.next = newhead;
            newhead = head;
            head = next;
        }
        return newhead;
    }
}
----------------------------------------------------

Reverse Linked List II:
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) 
    {
        if(head == null) return null;
            
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i = 0; i < m-1; i++)
        	pre = pre.next;
        ListNode start = pre.next;
        ListNode then = start.next;

        for(int i = 0; i < n-m; i++)
        {
        	start.next = then.next;
        	then.next = pre.next;
        	pre.next = then;
        	then = start.next;
        }

        return dummy.next;
    }
}
----------------------------------------------------

Remove Nth Node From End of List:
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) 
    {
    	ListNode dummy = new ListNode(-1);
    	dummy.next = head;
    	ListNode slow = dummy;
    	ListNode fast = dummy;

    	for(int i = 0; i < n; i++)
    		fast = fast.next;

    	while(fast.next != null)
    	{
    		fast = fast.next;
    		slow = slow.next;
    	}    

    	slow.next = slow.next.next;
    	return dummy.next;
    }
}
----------------------------------------------------

Merge Two Sorted Lists:
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) 
    {
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        
        while(l1 != null && l2 != null)
        {
        	if(l1.val < l2.val)
        	{
        		temp.next = l1;
        		l1 = l1.next;
        	}
        	else
        	{
        		temp.next = l2;
        		l2 = l2.next;
        	}
        	temp = temp.next;
        }

        temp.next = (l1 == null) ? l2 : l1;

        return dummy.next;
    }
}
----------------------------------------------------

Odd Even Linked List: O(1) space
public class Solution {
    public ListNode oddEvenList(ListNode head) 
    {
        if(head == null) return head;
        
        ListNode odd = head, even = head.next, evenHead = even;

        while(even != null && even.next != null)
        {
        	odd.next = odd.next.next;
        	even.next = even.next.next;
        	odd = odd.next;
        	even = even.next;
        }

        odd.next = evenHead;

        retrun head;
    }
}
----------------------------------------------------

Remove Duplicates from Sorted List:
public class Solution {
    public ListNode deleteDuplicates(ListNode head) 
    {
        ListNode cur = head;
        
        while(cur != null)
        {
            while(cur.next != null && cur.val == cur.next.val)
                cur.next = cur.next.next;
        
            cur = cur.next;
        }
        
        return head;
    }
}
----------------------------------------------------

Intersection of Two Linked Lists:
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) 
    {
        if(headA == null || headB == null)  return null;
        
        ListNode a = headA;
        ListNode b = headB;
        
        while(a != b)
        {
            a = a.next;
            b = b.next;
            
            if(a == b) return a; // a == null if no intersection
            else if(a == null) a = headB;
            else if(b == null) b = headA;
        }
        
        return a;
    }
}
----------------------------------------------------

Remove Linked List Elements:
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur != null) {
            while(cur.next != null && cur.next.val == val)
                cur.next = cur.next.next;
            cur = cur.next;
        }
        return dummy.next;
    }
}
----------------------------------------------------

Delete Node in a Linked List:
public class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
----------------------------------------------------

Linked List Cycle:
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        if(head == null || head.next == null)
            return false;
            
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast.next != null && fast.next.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
            
            if(fast == slow)
                return true;
        }
        
        return false;
        
    }
}
----------------------------------------------------

Linked List Cycle II:
public class Solution {
    public ListNode detectCycle(ListNode head) {
        
        if(head == null || head.next == null)
            return null;
            
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast.next != null && fast.next.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
            
            if(fast == slow)
            {
                ListNode begin = head;
                while(begin != slow)
                {
                    begin = begin.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        
        return null;
        
    }
}
----------------------------------------------------

Add Two Numbers:
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode d = dummy;
        int sum = 0;
        
        while(c1 != null || c2 != null)
        {
        	if(c1 != null)
        	{
        		sum += c1.val;
        		c1 = c1.next;
        	}
        	if(c2 != null)
        	{
        		sum += c2.val;
        		c2 = c2.next;
        	}

        	d.next = new ListNode(sum%10);
        	d = d.next;
        	sum /= 10;
        }

        if(sum != 0)
        	d.next = new ListNode(sum);

        return dummy.next;
    }
}
----------------------------------------------------

Swap Nodes in Pairs:
public class Solution {
    public ListNode swapPairs(ListNode head) 
    {
        if(head == null || head.next == null)
            return head;
            
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        for(ListNode cur = dummy; 
            cur.next != null && cur.next.next != null; 
            cur = cur.next.next){
            cur.next = swap(cur.next, cur.next.next);
        }
        return dummy.next;
    }
    
    public ListNode swap(ListNode first, ListNode second)
    {
        first.next = second.next;
        second.next = first;
        return second;
    }
}
----------------------------------------------------

138. Copy List with Random Pointer
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pointer = dummy, node = head;
        RandomListNode newNode;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        while(node != null) {
            // map node
            if(map.containsKey(node)) {
                newNode = map.get(node);
            }
            else {
                newNode = new RandomListNode(node.label);
                map.put(node, newNode);
            }
            // pointer help to build dummy
            pointer.next = newNode;
            
            // map node.random
            if(node.random != null) {
                if(map.containsKey(node.random)) {
                    newNode.random = map.get(node.random);
                }
                else {
                    newNode.random = new RandomListNode(node.random.label);
                    map.put(node.random, newNode.random);
                }
            }
            
            pointer = pointer.next;
            node = node.next;
        }
        
        return dummy.next;
    }
}
----------------------------------------------------

25. Reverse Nodes in k-Group
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k < 0) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        while(node != null) {
            node = reverseNextK(node, k);
        }
        return dummy.next;
    }
    
    /**
     * pre -> n1 -> ... -> nk -> post
     * pre -> nk -> ... -> n1 -> post
     **/
    private ListNode reverseNextK(ListNode node, int k) {
        ListNode nk = node;
        ListNode n1 = node.next;
        for(int i = 0; i < k; i++) {
            nk = nk.next;
            if(nk == null)
                return null;
        }
        
        ListNode post = nk.next;
        ListNode newHead = null;
        ListNode cur = n1;
        while(cur != post) {
            ListNode Next = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = Next;
        }
        node.next = nk;
        n1.next = post;
        
        return n1;
    }
}
----------------------------------------------------

109. Convert Sorted List to Binary Search Tree
public class Solution {
    private ListNode current;
    public TreeNode sortedListToBST(ListNode head) {  
        int size;
        current = head;
        size = getLength(head);
        return helper(size);
    }
    
    private int getLength(ListNode head) {
        if(head == null) return 0;
        int len = 0;
        while(head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
    
    private TreeNode helper(int size) {
        if(size <= 0) return null;
        TreeNode left = helper(size/2);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = helper(size-1-size/2);
        
        root.left = left;
        root.right = right;
        
        return root;
    }
}
----------------------------------------------------


