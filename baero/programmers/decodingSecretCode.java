import java.util.*;

class Solution {
    static List<Set<Integer>> combinations = new ArrayList<>();

    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        fillCombinations(n);

        for (var combSet: combinations) {
            boolean possible = true;
            for(int i = 0; i < q.length; i++) {
                int hitCnt = 0;
                for (var j : q[i]) {
                    if (combSet.contains(j)) {
                        hitCnt++;
                    }
                }
                if (ans[i] != hitCnt) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                answer++;
            }
        }

        return answer;
    }


    static void fillCombinations(final int n) {
        boolean[] visited = new boolean[n + 1];
        combination(visited, n, 5, 1);
    }

    static void combination(boolean[] visited, int n, int r, int start) {
        if (r == 0) {
            Set<Integer> comb = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    comb.add(i);
                }
            }
            combinations.add(comb);
            return;
        }

        for (int i = start; i <= n; i++) {
            visited[i] = true;
            combination(visited, n, r - 1, i + 1);
            visited[i] = false;
        }
    }
}
