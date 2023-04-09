package interviewbit.arrays;

/*
Given two strings s and t, both consisting of lowercase English letters and digits, your task is to calculate how many ways exactly one digit could be removed from one of the strings so that s is lexicographically smaller than t after the removal. Note that we are removing only a single instance of a single digit, rather than all instances (eg: removing 1 from the string a11b1c could result in a1b1c or a11bc, but not abc).

Also note that digits are considered lexicographically smaller than letters.
 */
public class CompareStrings {

    int solution(String s, String t) {

        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                int val = removeNextDigit(s, t, i);
                System.out.println("val1:" + val);
                if (val < 0) {
                    count++;
                }
            }
        }

        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) <= '9' && t.charAt(i) >= '0') {
                int val = removeNextDigit(t, s, i);
                System.out.println("val:" + val);
                if (val > 0) {
                    count++;
                }
            }
        }

        return count;
    }

    private static int removeNextDigit(String s, String t, int index) {
        StringBuilder sb = new StringBuilder(s);
        sb = sb.replace(index, index+1, "");
        return compare(sb.toString(), t);
    }

    private static int compare(String s, String t) {
        return s.compareTo(t);
    }

}
