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
            if(notPrime[i] == false)
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