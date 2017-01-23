HashTable:

Word Pattern:
public class Solution {
    public boolean wordPattern(String pattern, String str) 
    {
        String[] words = str.split(" ");
        HashMap<Character, String> map = new HashMap<>();
        if(pattern.length() != words.length) return false;

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
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++)
        {
        	if(map.containsKey(nums[i]))
        	{
        		if(i - map.get(nums[i]) <= k)
        			return true;
        	}
        	map.put(nums[i], i);
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

        for(int i = 0; i < s.length(); i++)
        {
        	mark[s.charAt(i) - 'a']++;
        }

        for(int j = 0; j < t.length(); j++)
        {
        	mark[t.charAt(i) - 'a']--;
        }

        for(int k = 0; k < mark.length; k++)
        {
        	if(mark[k] != 0)
        		return false;
        }

        return true;
    }
}
-------------------------------------------------

Valid Sudoku:
public class Solution {
    public boolean isValidSudoku(char[][] board) 
    {
        int[][] used1 = new int[9][9];
        int[][] used2 = new int[9][9];
        int[][] used3 = new int[9][9];
        
        for(int i = 0; i < board.length; i++)
        {
        	for(int j = 0; j < board[0].length; j++)
        	{
        		if(board[i][j] != '.')
        		{
        			int num = board[i][j] - '0' - 1;
        			int k = i / 3 * 3 + j / 3;
        			if(used1[i][num] != 0 || used2[j][num] != 0 || used3[k][num] != 0)
        				return false;
        			used1[i][num] = used2[j][num] = used3[k][num] = 1;
        		}
        	}
        }

        return true;
    }
}
-------------------------------------------------

Happy Number:
class Solution {
    private int helper(int n)
    {
        int sum = 0;
        int temp;
        for(int i = n; i > 0; i/=10)
        {
        	temp = i % 10;
        	sum += temp * temp;
        }
        return sum;
    }

    public boolean isHappy(int n)
    {
    	int slow = n, fast = n;
    	while(slow > 1)
    	{
    		slow = helper(slow);
    		if(slow == 1) return true;
    		fast = helper(helper(fast));
    		if(fast == 1) return true;

    		if(slow == fast) return false;
    	}
    	return true;
    }
};
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

Isomorphic Strings:
public class Solution {
    public boolean isIsomorphic(String s, String t) 
    {
        int[] m = new int[512];

        for(int i = 0; i < s.length(); i++)
        {
        	if(m[s.charAt(i)] != m[t.charAt(i) + 256]) return false;
        	m[s.charAt(i)] = m[t.charAt(i) + 256] = i+1;
        }
        
        return true;
    }
}
-------------------------------------------------

Two Sum:
public class Solution {
    public int[] twoSum(int[] nums, int target) 
    {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];

        for(int i = 0; i < nums.length; i++)
        {
        	if(map.containsKey(target-nums[i]))
        	{
        		res[0] = map.get(target-nums[i]);
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
    public String fractionToDecimal(int numerator, int denominator) 
    {
    	if(numerator == 0) return "0";

    	StringBuilder res = new StringBuilder();
    	res.append((numerator>0) ^ (denominator>0) ? "-" : "");
    	long num = Math.abs((long)numerator);
    	long den = Math.abd((Long)denominator);
    	res.append(num/den);    
    	if(num % den == 0) return res.toString();

    	res.append(".");
    	num %= den;
    	Map<Long, Integer> map = new HashMap<>();
    	map.put(num, res.length());

    	while(num != 0)
    	{
    		num *= 10;
    		res.append(num / den);
    		num %= den;

    		if(map.containsKey(num))
    		{
    			int index = map.get(num);
    			res.insert(index, "(");
    			res.append(")");
    			break;
    		}
    		else
    		{
    			map.put(num, res.length());
    		}
    	}

    	return res.toString();
    }
}