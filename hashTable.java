HashTable:

Word Pattern:
public class Solution {
    public boolean wordPattern(String pattern, String str) 
    {
        String[] words = str.split(" ");
        if(pattern.length() != words.length) return false;
        HashMap<Character, String> map = new HashMap<>();
        
        for(int i = 0; i < words.length; i++)
        {
        	char c = pattern.charAt(i);

        	if(map.containsKey(c))
        	{
        		if(!map.get(c).equals(words[i]) 
        			return false;
        	}
        	else
        	{
        		if(map.containsValue(words[i])) 
        			return false;
        		map.put(c, words[i]);
        	}
        }

        return true;
    }
}
-------------------------------------------------

Contains Duplicate:
public class Solution {
    public boolean containsDuplicate(int[] nums) 
    {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++)
        {
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
}
-------------------------------------------------

Contains Duplicate II:
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) 
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if(map.containsKey(cur)) {
                if(i - map.get(cur) <= k)
                    return true;
            }
            map.put(cur, i);
        }
        return false;
    }
}
-------------------------------------------------

Valid Anagram:
public class Solution {
    public boolean isAnagram(String s, String t) 
    {
        if(s.length() != t.length()) return false;
        int[] mark = new int[26];
        for(int i = 0; i < s.length(); i++) {
            mark[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < t.length(); i++) {
            mark[t.charAt(i) - 'a']--;
        }
        for(int i = 0; i < 26; i++) {
            if(mark[i] != 0)
                return false;
        }
        return true;
    }
}
-------------------------------------------------

Count Primes:
public class Solution {
    public int countPrimes(int n) 
    {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for(int i = 2; i < n; i++)
        {
            if(!notPrime[i])
            {
                count++;
                for(int j = 2; i*j < n; j++)
                    notPrime[i*j] = true;
            }
        }
        return count;
    }
}
-------------------------------------------------

Valid Sudoku:
public boolean isValidSudoku(char[][] board) {
    int[][] usedRow = new int[9][9];
    int[][] usedCol = new int[9][9];
    int[][] usedBlk = new int[9][9];

    for(int row = 0; row < board.length; row++) {
        for(int col = 0; col < board[0].length; col++) {
            if(board[row][col] != '.') {
                int num = board[row][col] - '0' - 1;
                int blk = row/3*3 + col/3;
                if(usedRow[row][num] != 0 || usedCol[col][num] != 0 || usedBlk[blk][num] != 0)
                    return false;
                usedRow[row][num] = usedCol[col][num] = usedBlk[blk][num] = 1;
            }
        }
    }
    return true;
}
-------------------------------------------------

Happy Number:
public class Solution {
    private static int happyHelper(int n) {
        int tmp, sum = 0;
        while(n != 0) {
            tmp = n % 10;
            sum += tmp * tmp;
            n /= 10;
        }
        return sum;
    }
    
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        while(slow != 1) {
            slow = happyHelper(slow);
            if(slow == 1) return true;
            fast = happyHelper(happyHelper(fast));
            if(fast == 1) return true;
            if(slow == fast) return false;
        }
        return true;
    }
}
-------------------------------------------------

Isomorphic Strings:
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] map = new int[512];
        for(int i = 0; i < s.length(); i++) {
            if(map[s.charAt(i)] != map[t.charAt(i) + 256])
                return false;
            map[s.charAt(i)] = map[t.charAt(i) + 256] = i+1;
        }
        return true;
    }
}

-------------------------------------------------

Two Sum:
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])) {
                res[0] =  map.get(target-nums[i]);;
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
-------------------------------------------------

Fraction to Recurring Decimal:
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // case 0
        if(numerator == 0) return "0";
        StringBuilder res = new StringBuilder();

        // case minus
        res.append((numerator > 0) ^ (denominator > 0) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // case int
        res.append(num / den);
        if(num % den == 0) return res.toString();

        // case decimal
        res.append(".");
        num %= den;
        Map<Long, Integer> restToPos = new HashMap<>();
        restToPos.put(num, res.length());

        while(num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;

            // case repeat
            if(restToPos.containsKey(num)) {
                int index = restToPos.get(num);
                res.insert(index, "(");
                res.append(")");
                return res.toString();
            }
            else {
                restToPos.put(num, res.length());
            }
        }
        return res.toString();
    }
}
-------------------------------------------------

EASY:
Palindrome Permutation:
public class Solution {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(set.contains(c))
                set.remove(c);
            else
                set.add(c);
        }
        return set.size() == 0 || set.size() == 1;
    }
}
-------------------------------------------------

460. LFU Cache
public class LFUCache {
    private Node head = null;
    private int cap = 0;
    private HashMap<Integer, Integer> valueHash = null;
    private HashMap<Integer, Node> nodeHash = null;
    
    public LFUCache(int capacity) {
        this.cap = capacity;
        valueHash = new HashMap<Integer, Integer>();
        nodeHash = new HashMap<Integer, Node>();
    }
    
    public int get(int key) {
        if (valueHash.containsKey(key)) {
            increaseCount(key);
            return valueHash.get(key);
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if ( cap == 0 ) return;
        if (valueHash.containsKey(key)) {
            valueHash.put(key, value);
        } else {
            if (valueHash.size() < cap) {
                valueHash.put(key, value);
            } else {
                removeOld();
                valueHash.put(key, value);
            }
            addToHead(key);
        }
        increaseCount(key);
    }
    
    private void addToHead(int key) {
        if (head == null) {
            head = new Node(0);
            head.keys.add(key);
        } else if (head.count > 0) {
            Node node = new Node(0);
            node.keys.add(key);
            node.next = head;
            head.prev = node;
            head = node;
        } else {
            head.keys.add(key);
        }
        nodeHash.put(key, head);      
    }
    
    private void increaseCount(int key) {
        Node node = nodeHash.get(key);
        node.keys.remove(key);
        
        if (node.next == null) {
            node.next = new Node(node.count+1);
            node.next.prev = node;
            node.next.keys.add(key);
        } else if (node.next.count == node.count+1) {
            node.next.keys.add(key);
        } else {
            Node tmp = new Node(node.count+1);
            tmp.keys.add(key);
            tmp.prev = node;
            tmp.next = node.next;
            node.next.prev = tmp;
            node.next = tmp;
        }

        nodeHash.put(key, node.next);
        if (node.keys.size() == 0) remove(node);
    }
    
    private void removeOld() {
        if (head == null) return;
        int old = 0;
        for (int n: head.keys) {
            old = n;
            break;
        }
        head.keys.remove(old);
        if (head.keys.size() == 0) remove(head);
        nodeHash.remove(old);
        valueHash.remove(old);
    }
    
    private void remove(Node node) {
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        } 
        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }
    
    class Node {
        public int count = 0;
        public LinkedHashSet<Integer> keys = null;
        public Node prev = null, next = null;
        
        public Node(int count) {
            this.count = count;
            keys = new LinkedHashSet<Integer>();
            prev = next = null;
        }
    }
}
