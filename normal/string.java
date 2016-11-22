Longest Common Prefix:
public class Solution {
    public String longestCommonPrefix(String[] strs) 
    {
        if(strs == null) return null;
        if(strs.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        Arrays.sort(strs);
        int len = strs.length;
        char[] head = strs[0].toCharArray();
        char[] tail = strs[len-1].toCharArray();

        for(int i = 0; i < head.length; i++)
        {
            if(head[i] == tail[i] && i < tail.length)
                sb.append(head[i]);
            else
                return sb.toString();
        }

        return sb.toString();
    }
}
-------------------------------------------------

Valid Palindrome:
public class Solution {
    public boolean isPalindrome(String s) 
    {
        if(s.length() == 0) return true;

        int p1 = 0, p2 = s.length()-1;

        while(p1 < p2)
        {
            char head = s.charAt(p1);
            char tail = s.charAt(p2);

            if(!Character.isLetterOrDigit(head)) p1++;
            else if(!Character.isLetterOrDigit(tail)) p2--;
            else
            {
                if(Character.toLowerCase(head) != Character.toLowerCase(tail))
                    return false;
                p1++;
                p2--;
            }
        }

        return true;
    }
}
-------------------------------------------------

Valid Parentheses:
public class Solution {
    public boolean isValid(String s) 
    {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); i++)
        {
            char temp = s.charAt(i);
            if(temp == ')' || temp == ']' || temp == '}')
            {
                if(stack.isEmpty() || temp != map.get(stack.peek()))
                    return false;
                else
                    stack.pop();
            }
            else
                stack.push(temp);
        }
        
        if(stack.isEmpty())
            return true;
        else
            return false;
    }
-------------------------------------------------

Length of Last Word:
public class Solution {
    public int lengthOfLastWord(String s) 
    {
	// Approach I
        return s.trim().length() - 1 - s.trim().lastIndexOf(" ");
	// Approach II
        String[] words = s.trim().split(" ");
        return words[words.length - 1].length();
    }
}
-------------------------------------------------


