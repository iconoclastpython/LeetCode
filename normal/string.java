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


