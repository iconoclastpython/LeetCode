218. The Skyline Problem
public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1],  b[2]});
        }
        
        Collections.sort(height, new Comparator<int[]>(){
            @Override
            public int compare(int[] b1, int[] b2) {
                if(b1[0] == b2[0]) {
                    return b1[1] - b2[1];
                }
                else {
                    return b1[0] - b2[0];
                }
            }
        });
        
        // From large to small
        Queue<Integer> pq = new PriorityQueue<>((Integer a, Integer b) -> (b - a));
        pq.offer(0);
        int preHeight = 0;
        for(int[] h : height) {
            // Negtive is start, Positive is end
            if(h[1] < 0) {
                pq.offer(-h[1]);
            }
            else {
                pq.remove(h[1]);
            }
            int curHeight = pq.peek();
            if(curHeight != preHeight) {
                res.add(new int[]{h[0], curHeight});
                preHeight = curHeight;
            }
        }
        return res;
    }
}
------------------------------------------------

208. Implement Trie (Prefix Tree)
public class Trie {
    
    public class TrieNode {
        public boolean isWord;
        public TrieNode[] children = new TrieNode[26];
        public TrieNode() {};
    }
    
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode pointer = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(pointer.children[c-'a'] == null) {
                pointer.children[c-'a'] = new TrieNode();
            }
            pointer = pointer.children[c-'a'];
        }
        pointer.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(node.children[c-'a'] != null)
                node = node.children[c-'a'];
            else
                return false;
        }
        return node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(node.children[c-'a'] != null)
                node = node.children[c-'a'];
            else
                return false;
        }
        return true;
    }
}
------------------------------------------------

239. Sliding Window Maximum
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        if(nums.length == 0) return new int[0];
        for(int i = 0; i < k-1; i++) {
            inQueue(deque, nums[i]);
        }
        
        for(int i = k-1; i < nums.length; i++) {
            inQueue(deque, nums[i]);
            res.add(deque.peekFirst());
            outQueue(deque, nums[i-k+1]);
        }
        
        int[] result = new int[nums.length-k+1];
        for(int i = 0; i < res.size(); i++)
            result[i] = res.get(i);
        return result;
    }
    
    private void inQueue(Deque<Integer> deque, int num) {
        while(!deque.isEmpty() && deque.peekLast() < num)
            deque.pollLast();
        deque.offer(num);
    }
    
    private void outQueue(Deque<Integer> deque, int num) {
        if(deque.peekFirst() == num) {
            deque.pollFirst();
        }
    }
}
------------------------------------------------

