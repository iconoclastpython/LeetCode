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

Implement strStr():
public class Solution {
    public int strStr(String haystack, String needle) 
    {
        int index = 0;
        int len1 = haystack.length();
        int len2 = needle.length();

        if(len1 < len2) return -1;
        else if(len2 == 0) return 0;

        while(index + len2 <= len1)
        {
            String cur = haystack.substring(index, index+len2);

            if(cur.equals(needle))
                return index;
            index++;
        }

        return -1;
    }
}
-------------------------------------------------

String to Integer (atoi):
public class Solution {
    public int myAtoi(String str) 
    {
        int index = 0, sign = 1, total = 0;
        
        //1. Empty string
        if(str.length() == 0) return 0;
        
        //2. Remove space
        str = str.trim();
        
        //3. Handle sign
        if(str.charAt(0) == '+' || str.charAt(0) == '-')
        {
            sign = str.charAt(0) == '-' ? -1 : 1;
            index++;
        }
        
        //4. Convert number and avoid overflow
        while(index < str.length())
        {
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;
            
            //check if total will be overflow after 10 times and add digit
            if((Integer.MAX_VALUE-digit)/10 < total)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            
            total = total * 10 + digit;
            index++;
        }
        
        return sign * total;
    }
}
-------------------------------------------------

Roman to Integer:
public class Solution {
    public int romanToInt(String s) 
    {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int res = map.get(s.charAt(len-1));
        int pivot = res;

        for(int i = len-2; i >= 0; i--)
        {
            int cur = map.get(s.charAt(i));

            if(cur >= pivot)
                res += cur;
            else
                res -= cur;

            pivot = cur;
        }

        return res;
    }
}
-------------------------------------------------

Implement strStr():
public class Solution {
    public int strStr(String haystack, String needle) 
    {
        int index = 0;
        int len1 = haystack.length();
        int len2 = needle.length();

        if(len1 < len2) return -1;
        else if(len2 == 0) return 0;

        while(index + len2 <= len1)
        {
            String cur = haystack.substring(index, index+len2);

            if(cur.equals(needle))
                return index;
            index++;
        }

        return -1;
    }
}
-------------------------------------------------
Dec 13:
Repeated Substring Pattern:
public class Solution {
    public boolean repeatedSubstringPattern(String str) {
        int len = str.length();
        for(int i = 1; i <= len/2; i++) {
            if(len % i == 0) {
                int mul = len/i;
                String subS = str.substring(0,i);
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < mul; j++) {
                    sb.append(subS);
                }
            if(sb.toString().equals(str)) 
                return true;
        }
    }
    return false;
    }
}
-------------------------------------------------

Ransom Note:
public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] check = new int[26];
        for(int i = 0; i < magazine.length(); i++) {
            check[magazine.charAt(i) - 'a']++;
        }
        for(int j = 0; j < ransomNote.length(); j++) {
            check[ransomNote.charAt(j) - 'a']--;
        }
        for(int k = 0; k < 26; k++) {
            if(check[k] < 0)
                return false;
        }
        return true;
    }
}
-------------------------------------------------

Integer to English Words:
public class Solution {
    
    private final String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) 
    {
        if(num == 0) return "Zero";

        int i = 0; //how many thousands
        String Words = "";

        //handle the thousands
        while(num > 0)
        {
            if(num % 1000 != 0)
                words = helper(num%1000) + " " + thousands[i] + " " + words;
            num /= 1000;
            i++;
        }

        return words.trim();
    }
    
    //handle the number less than thousand
    private String helper(int num)
    {
        if(num == 0)
            return "";
        else if(num < 20)
            return lessThan20[num] + " ";
        else if(num < 100)
            return tens[num/10] + " " + helper(num%10);
        else
            return lessThan20[num/100] + " Hundred " + helper(num%100);
    }
}
-------------------------------------------------

Multiply Strings:
public class Solution {
    public String multiply(String num1, String num2) 
    {
        int len1 = num1.length(), len2 = num2.length();
        int[] products = new int[len1+len2];

        for(int i = len1-1; i >= 0; i--)
        {
            for(int j = len2-1; j >= 0; j--)
            {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i+j+1] += d1 * d2;
            }
        }

        int carry = 0;
        for(int i = len1+len2-1; i >= 0; i--)
        {
            int temp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = temp;
        }

        StringBuilder sb = new StringBuilder();

        for(int num : products)
        {
            sb.append(num);
        }

        while(sb.charAt(0) == '0' && sb.length() != 0)
            sb.deleteCharAt(0);

        return sb.toString();
    }
}
-------------------------------------------------

Longest Substring Without Repeating Characters:
public class Solution {
    public int lengthOfLongestSubstring(String s) 
    {
        int len = s.length();
        if(len == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();

        int maxLen = 0;
        for(int start = 0, index = 0; index < len; index++)
        {
            char cur = s.charAt(index);
            if(map.containsKey(cur))
                start = Math.max(start, map.get(cur)+1);
            map.put(cur, index);
            maxLen = Math.max(maxLen, index-start+1);
        }

        return maxLen;
    }
}
-------------------------------------------------

Restore IP Addresses:
public class Solution {
    public boolean isPartValid(String s) {
        if(s.length() > 3 || s.length() < 1 || Integer.parseInt(s) > 255 || (s.charAt(0) == '0' && s.length() > 1))
            return false;
        return true;
    }
    
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        for(int i = 1; i < 4 && i < len-2; i++) {
            for(int j = i+1; j < i+4 && j < len-1; j++) {
                for (int k = j+1; k < j+4 && k < len; k++) {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, len);
                    if(isPartValid(s1) && isPartValid(s2) && isPartValid(s3) && isPartValid(s4)) {
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
            }
        }
        return res;
    }
}
-------------------------------------------------

Longest Palindromic Substring:
public class Solution {
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
    
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
}
-------------------------------------------------

Group Anagrams:
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) 
    {
        if(strs == null || strs.length() == 0)
            return new ArrayList<List<String>>();

        Map<String, List<String>> map = new HashMap<>();
        
        for(String str : strs)
        { 
            char[] cur = str.toCharArray();
            Arrays.sort(cur);
            String key = String.valueOf(cur);
            if(!map.containsKey(key))
                map.put(key, new ArrayList<String>());
            map.get(key).add(str);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
-------------------------------------------------

Dec 14:
Generate Parentheses:
public class Solution {
    public List<String> generateParenthesis(int n) 
    {
        List<String> res = new ArrayList<>();
        backtracking(res, "", 0, 0, n);
        return res;
    }
    
    private void backtracking(List res, String s, int open, int close, int max)
    {
        if(open == close && s.length() == 2*max)
        {
            res.add(s);
            return ;
        }
        
        if(open < max)
        {
            backtracking(res, s + "(", open+1, close, max);
        }

        if(close < open)
        {
            backtracking(res, s + ")", open, close+1, max);
        }
    }
}
-------------------------------------------------

Reverse Words in a String:
public class Solution {
    public String reverseWords(String s) 
    {
        String[] str = s.trim().split("\\s+");
        String r = reverse(str);
        return r;
    }
    
    private String reverse(String[] str)
    {
        StringBuilder res = new StringBuilder();
        
        for(int i = str.length - 1; i >= 0; i--)
        {
            res.append(str[i] + " ");
        }
        
        return res.toString().trim();
    }
}
-------------------------------------------------

Reverse word in string II (In Place)
public class Solution {
    public static void reverseWordII(char[] s)
    {
        reverseII(s, 0, s.length-1);
        int start = 0;
        int end = s.length-1;
        for(int i = 0; i < s.length; i++) //reverse each word
        {
            if(s[i] == ' ')
            {
                reverseII(s, start, i-1);
                start = i+1;
            }
        }
        reverseII(s, start, end); //reverse the last word
    }

    public static void reverseII(char[] s, int start, int end)
    {
        while(start < end)
        {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}
-------------------------------------------------

Simplify path:
public class Solution {
    public String simplifyPath(String path) 
    {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));

        for(String dir : path.split("/"))
        {
            if(dir.equals("..") && !stack.isEmpty())
            {
                stack.pop();
            }
            else if(!skip.contains(dir))
            {
                stack.push(dir);
            }
        }

        String res = "";

        for(String dir : stack)
            res = "/" + dir + res;

        return res.isEmpty() ? "/" : res;
    }
}
-------------------------------------------------

Decode Ways:
public class Solution {
    public int numDecodings(String s) 
    {
        if(s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len+1];
        dp[len] = 1;
        dp[len-1] = s.charAt(len-1) == '0' ? 0 : 1;

        for(int i = len-2; i >= 0; i--)
        {
            if(s.charAt(i) == '0')
                continue;
            else
            {
                int temp = Integer.parseInt(s.substring(i, i+2));
                dp[i] = temp <= 26 ? dp[i+1] + dp[i+2] : dp[i+1];
            }
        }

        return dp[0];
    }
}
-------------------------------------------------

Basic Calculator I:
ex: "(1+(4+5+2)-3)+(6+8)" = 23

public class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
            int result = 0;
            int number = 0;
            int sign = 1;
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(Character.isDigit(c)){
                    number = 10 * number + (int)(c - '0');
                }else if(c == '+'){
                    result += sign * number;
                    number = 0;
                    sign = 1;
                }else if(c == '-'){
                    result += sign * number;
                    number = 0;
                    sign = -1;
                }else if(c == '('){
                    //we push the result first, then sign;
                    stack.push(result);
                    stack.push(sign);
                    //reset the sign and result for the value in the parenthesis
                    sign = 1;   
                    result = 0;
                }else if(c == ')'){
                    result += sign * number;  
                    number = 0;
                    result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                    result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
                }
            }
            if(number != 0) result += sign * number;
            return result;
    }
}
-------------------------------------------------

public class Solution {
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0, num = 0;
        char sign = '+';

        for(int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if(Character.isDigit(cur)) {
                num = num*10 + (int)(cur - '0');
            }
            
            if(!Character.isDigit(cur) && cur != ' ' || i == s.length()-1) {
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                } else if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                }

                sign = cur;
                num = 0;
            }
        }
        for(Integer entry : stack) res += entry;
        return res;
    }
}
-------------------------------------------------

