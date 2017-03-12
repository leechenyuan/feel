package test.me.feelwith.business.service.gossip;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/23.
 */
public class L5 {
    public String longestPalindrome(String s) {
        if ((s == null) || (s.length() <= 1)) {
            return s;
        }
        List<Integer> charIndexs = new ArrayList<>();
        Map<Integer, List<Integer>> charIndexMapping = new HashMap<>();
        int max = 0;
        boolean isSingle = false;
        int minIndex = 0;
        int length = s.length();
        boolean skipLoop = false;
        for (int i = 0; i < length; i++) {
            if(skipLoop){
                break;
            }
            int charIndex = s.charAt(i);
            List<Integer> indexs = charIndexMapping.get(charIndex);
            if (indexs == null) {
                indexs = new ArrayList<>();
                charIndexMapping.put(charIndex, indexs);
            } else {
                for (int j = indexs.size() - 1; j >= 0; j--) {
                    int left = indexs.get(j);
                    int diff = i - left;
                    if ((diff == 1) || (diff == 2)) {
                        int tmpMax = findMax(s, left, i);
                        if (tmpMax > max) {
                            max = tmpMax;
                            minIndex = left;
                            if (diff == 1) {
                                isSingle = false;
                            } else {
                                isSingle = true;
                            }

                        }
                        if (max == length) {
                            skipLoop = true;
                        }
                        if(max > (2*(length-left)+1)){
                            skipLoop = true;
                        }
                    } else {
                        break;
                    }
                }
            }
            indexs.add(i);
        }
        int realDoubleLength = (max - (isSingle ? 1 : 0)) / 2;
        int begin = minIndex - realDoubleLength + 1;
        int end = begin + max;
        return s.substring(begin, end);
    }

    private int findMax(String s, int l, int r) {
        int max = r - l - 1;
        int length = s.length();
        for (int i = l, j = r;
             (i >= 0) && (j < length)
                ; i--, j++) {
            if (s.charAt(i) != s.charAt(j)) {
                return max;
            }
            max += 2;
        }
        return max;
    }


    @Test
    public void t() {
        String text =
                "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"
//                "fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"
//                "cccc"
//                "cc"
//                "ccc"
//                "fffffggggggg"
//                "caaac"
//                "x"
//    			"abavab"
//    			repeat("a", 10000)
//    			"abac"
                ;
//    	System.out.println(repeat("a", 100));
//        System.out.println(text.charAt(602));
        long begin = System.currentTimeMillis();
//        System.out.println(text.charAt(602));
//        System.out.println(text.lastIndexOf("g")+":"+text.indexOf("g"));
        System.out.println(longestPalindrome(text));
        System.out.println(System.currentTimeMillis() - begin);
    }

    private String repeat(String s, int num) {
        StringBuffer b = new StringBuffer();
        for (int i = 0; i < num; i++) {
            b.append(s);
        }
        return b.toString();
    }
}
