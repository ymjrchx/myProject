package how2j.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxin
 * @date 2020/1/3 11:10
 */
public class LongSubString {


    public static int start = 0;
    public static int front = 0;
    public static int lengthOfLongestSubstring(String str){
        Map<Integer,Integer> map = new HashMap<>(256);
        int max_len = 0;
        for (int i = 0; i < str.length(); i++) {
            if(map.get((int)str.charAt(i)) != null && map.get((int)str.charAt(i)) >= front){
                front = map.get((int)str.charAt(i)) +1;
            }
            map.put((int)str.charAt(i), i);
            max_len = Math.max(max_len, i-front + 1);

            if(max_len == (i-front+i)){
                start = front ;
            }
        }
        return max_len;
    }


    public static void main(String[] args) {
        String str = "agegaer324gg4eeg34g";
        System.out.println(str +"  : " +  lengthOfLongestSubstring(str));
        System.out.println(start);
        System.out.println(str.substring(start,10));
    }





























}
