package jaewook;

import java.util.ArrayList;
import java.util.List;

/**
 * @source: https://school.programmers.co.kr/learn/courses/30/lessons/388352 (비밀 코드 해독)
 * @classification: brute force
 * @문제 푼 날짜 (자력으로 풂?): 25.03.23 (O)
 **/
public class Q388352 {
    static List<int[]> list = new ArrayList<>();

    public int solution(int n, int[][] q, int[] ans) {
        bt(new int[5], 5, n, 0, 1);

        for(int i=0;i<q.length;i++) {
            int[] query = q[i];
            int answer = ans[i];

            for(int j=0;j<list.size();j++) {
                int[] hubo = list.get(j);
                int match = 0;

                for(int a=0;a<5;a++) {
                    for(int b=0;b<5;b++) {
                        if(hubo[a] == query[b]) {
                            ++match;
                            break;
                        }
                    }
                }

                if(match != answer) {
                    list.remove(j);
                    j--;
                }
            }
        }

        return list.size();
    }

    private static void bt(int[] tmp, int size, int n, int index, int num) {
        if(index == size) {
            int[] copy = new int[5];

            for(int i=0;i<5;i++) {
                copy[i] = tmp[i];
            }

            list.add(copy);

            return;
        }

        for(int i=num;i<=n;i++) {
            tmp[index] = i;
            bt(tmp, size, n, index+1, i+1);
        }
    }
}
