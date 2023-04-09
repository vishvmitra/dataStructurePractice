package interviewbit.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LargestContinuousSubSequenceZeroSum {
    public ArrayList<Integer> lszero(List<Integer> A) {

        // cumulative array
        int[] cArray = getCumulativeArray(A);

        HashMap<Integer, Range> map = new HashMap<Integer, Range>();
        HashSet<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < A.size(); i++) {
            if (map.containsKey(cArray[i])) {
                Range r = map.get(cArray[i]);
                int len = i - r.startInd;
                r.length = len;
                map.put(cArray[i], r);
                set.add(cArray[i]);
            }
            else if (cArray[i] == 0) {
                Range r = new Range(-1, i + 1);
                map.put(cArray[i], r);
                set.add(cArray[i]);
            }
            else {
                Range r = new Range(i, 0);
                map.put(cArray[i], r);
            }
        }

        int maxStartInd = Integer.MAX_VALUE, maxlen = 0;
        for (int sum : set) {
            Range r = map.get(sum);

            if (r.length >= maxlen && r.startInd < maxStartInd) {
                maxStartInd = r.startInd;
                maxlen = r.length;
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = maxStartInd+1; i <= (maxStartInd + maxlen); i++) {
            result.add(A.get(i));
        }


        return result;

    }

    class Range {

        int startInd;
        int length;

        public Range(int startInd, int length) {
            this.startInd = startInd;
            this.length = length;
        }
    }

    private int[] getCumulativeArray(List<Integer> A) {
        int[] cArray = new int[A.size()];

        cArray[0] = A.get(0);
        for (int i = 1; i < A.size(); i++) {
            cArray[i] = cArray[i - 1] + A.get(i);
        }

        return cArray;
    }
}
