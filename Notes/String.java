string minWindow(string s, string t) {
    unordered_map<char, int> m;
    // Statistic for count of char in t
    for (auto c : t) m[c]++;
    // counter represents the number of chars of t to be found in s.
    size_t start = 0, end = 0, counter = t.size(), minStart = 0, minLen = INT_MAX;
    size_t size = s.size();

    // Move end to find a valid window.
    while (end < size) {
        // If char in s exists in t, decrease counter
        if (m[s[end]] > 0)
            counter--;
        // Decrease m[s[end]]. If char does not exist in t, m[s[end]] will be negative.
        m[s[end]]--;
        end++;
        // When we found a valid window, move start to find smaller window.
        while (counter == 0) {
            if (end - start < minLen) {
                minStart = start;
                minLen = end - start;
            }
            m[s[start]]++;
            // When char exists in t, increase counter.
            if (m[s[start]] > 0)
                counter++;
            start++;
        }
    }
    if (minLen != INT_MAX)
        return s.substr(minStart, minLen);
    return "";
}


String minWindow(String s, String t)
{
    int[] map = new int[128];
    for(char c : t) map[c]++;
    int counter = t.length(), 
    begin = 0, end = 0, head = 0,
    d = Integer.MAX_VALUE;

    while(end < s.length())
    {
        if(map[s[end++]]-- > 0)
            counter--;

        while(counter == 0)
        {
            if(end - begin < d)
                d = dend - (head-begin);

            if(map[s[begin++]]++ == 0)
                counter++;
        }
    }
    return d == Integer.MAX_VALUE ? "" : s.substring(head, d);
}
-------------------------------------------------

Longest Common Prefix:
public class Solution {
    public String longestCommonPrefix(String[] strs) 
    {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(strs);
        int len = strs.length;
        char[] head = strs[0].toCharArray();
        char[] tail = strs[len-1].toCharArray();

        for(int i = 0; i < head.length; i++)
        {
            if(head[i] == tail[i] && i < tail.length)
            {
                sb.append(head[i]);
            }
            else
            {
                return sb.toString();
            }
        }

        return sb.toString();
    }
}
-------------------------------------------------

Add Binary:
public class Solution {
    public String addBinary(String a, String b) 
    {
        StringBuilder sb = new StringBuilder();
        int temp = 0, i = a.length()-1, j = b.length()-1;
        while(i >= 0 || j >= 0 || temp == 1)
        {
            temp += (i>=0) ? a.charAt(i--)-'0' : 0;
            temp += (j>=0) ? b.charAt(j--)-'0' : 0;
            sb.insert(0, Integer.toString(temp%2));
            temp >>= 1;
        }
        return sb.toString();
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
        return s.trim().length() - 1 - s.trim().lastIndexOf(" ");
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
    public List<String> restoreIpAddresses(String s) 
    {
        List<String> res = new ArrayList<>();
        int len = s.length();

        for(int i = 1; i < 4 && i < len-2; i++)
        {
            for(int j = i+1; j < i+4 && j < len-1; j++)
            {
                for(int k = j+1; k < j+4 && k < len; k++)
                {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, len);

                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4))
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                }
            }
        }

        return res;
    }
    
    private boolean isValid(String s)
    {
        if(s.length() > 3 || s.length < 1 || Integer.parseInt(s) > 255 || 
            (s.charAt(0) == '0' && s.length() > 1))
            return false;

        return true;
    }
-------------------------------------------------

Longest Palindromic Substring:
public class Solution {

    private int low, maxlen;
    
    public String longestPalindrome(String s) 
    {
        int len = s.length();
        if(len < 2) return s;

        for(int i = 0; i < len; i++)
        {
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i+1);
        }

        return s.substring(low, low+maxlen);
    }
    
    private void extendPalindrome(String s, int p, int q)
    {
        while(p >= 0 && q < s.length() && s.charAt(p) == s.charAt(q))
        {
            p--;
            q++;
        }

        if(maxlen < q-p+1)
        {
            low = p + 1;
            maxlen = q - low;
        }
    }
}
-------------------------------------------------

Letter Combinations of a Phone Number:
Note: mentains a queue, deal with one digit each time.

public class Solution {
    public List<String> letterCombinations(String digits) 
    {
        List<String> res = new LinkedList<>();
        String[] map = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");

        for(int i = 0; i < digits.length(); i++)
        {
            int cur = digits.charAt(i) - '0';
            while(res.peek().length() == i) //update each time
            {
                String temp = res.remove(); //pick up from queue head
                for(char c : map[cur].toCharArray())
                {
                    res.add(temp + c); //add to queue tail
                }
            }
        }

        if(res.get(0).length() == 0)
            res.remove();

        return res;
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
        //Arrays.sort(strs);
        for(String str : strs)
        { 
            char[] cur = str.toCharArray();
            Arrays.sort(cur);
            String key = String.valueOf(cur);
            if(!map.containsKey(key))
                map.put(key, new ArrayList<String>());
            map.get(key).add(str);
        }

        for(String key: map.keySet()) 
            Collections.sort(map.get(key));

        return new ArrayList<List<String>>(map.values());
    }
}
-------------------------------------------------

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
        if(s.length() == 2*max)
        {
            res.add(s);
            return ;
        }
        
        if(open < max)
        {
            return backtracking(res, s + "(", open + 1, close, max);
        }

        if(close < open)
        {
            return backtracking(res, s + ")", open, close + 1, max);
        }
    }
}
-------------------------------------------------

Reverse word in string II (In Place)

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
-------------------------------------------------

Simplify Path:
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
    public int calculate(String s) 
    {
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0, res = 0, sign = 1;
        
        for(int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if(Character.isDigit(c))
            {
                num = num*10 + (c - '0');
            }
            else if(c == '+')
            {
                res += sign * num;
                sign = 1;
                num = 0;
            }
            else if(c == '-')
            {
                res += sign * num;
                sign = -1;
                num = 0;
            }
            else if(c == '(')
            {
                stack.push(res);
                stack.push(sign);
                sign = 1;
                num = 0;
            }
            else if(c == ')')
            {
                res += sign * num;
                res *= stack.pop(); //sign
                res += stack.pop(); //previous res
                num = 0;
            }
            else if(c == ' ')
            {
                continue;
            }
        }

        if(num != 0) res += sign * num;
        return res;
    }
}
-------------------------------------------------

Basic Calculator II:
ex: " 3+5 / 2 " = 5

public class Solution {
    public int calculate(String s) 
    {
        int res = 0, num = 0, len = s.length();
        if(s == null || len == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';

        for(int i = 0; i < len; i++)
        {
            char c = s.charAt(i);

            if(Character.isDigit(c))
            {
                num = num*10 + (c - '0');
            }

            if(!Character.isDigit(c) && c != ' ' || i == len-1)
            {
                if(sign == '+')
                {
                    stack.push(num);
                }
                else if(sign == '-')
                {
                    stack.push(-num);
                }
                else if(sign == '*')
                {
                    stack.push(stack.pop() * num);
                }
                else if(sign == '/')
                {
                    stack.push(stack.pop() / num);
                }

                sign = s.charAt(i);
                num = 0;
            }
        }

        for(Integer n : stack) res += n;
        return res;
    }
}
-------------------------------------------------

Word Ladder I (BFS):
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) 
    {
        if(dict.size() == 0) return 0;

        dict.add(end);
        LinkedList<String> wordsQ = new LinkedList<>();
        LinkedList<Integer> distQ = new LinkedList<>();

        wordQ.add(start);
        distQ.add(1);

        while(!wordsQ.isEmpty())
        {
            String curWord = wordsQ.pop();
            int curDist = distQ.pop();

            if(curWord.equals(end))
                return curDist;

            for(int i = 0; i < curWord.length(); i++)
            {
                char[] curChars = curWord.toCharArray();

                for(char c = 'a'; c <= 'z'; c++)
                {
                    curChars[i] = c;
                    String newWord = new String(curChars);

                    if(dict.contains(newWord))
                    {
                        wordsQ.add(newWord);
                        dist.add(curDist+1);
                        dict.remove(newWord);
                    }
                }
            }
        }

        return 0;
    }
}
-------------------------------------------------