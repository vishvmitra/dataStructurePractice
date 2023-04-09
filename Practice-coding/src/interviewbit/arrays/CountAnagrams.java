package interviewbit.arrays;

import java.util.Arrays;

public class CountAnagrams {

    long solution(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int[] str = new int[a.length];

        long count = 0;

        for (int i = 0; i < a.length; i++) {
            char[] val = String.valueOf(a[i]).toCharArray();
            Arrays.sort(val);
            str[i] = Integer.parseInt(new String(val));
        }

        Arrays.sort(str);

        int i = 1;
        while (i < str.length) {
            if (str[i - 1] == str[i]) {
                count += 2;
                i += 2;
            }
            else {
                i++;
            }
        }

        return count;
    }

}
