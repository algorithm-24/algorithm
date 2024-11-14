package grd_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * 그래프
 */
public class 키순서_2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<ArrayList<Integer>> reverseGraph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            graph.get(a).add(b);
            reverseGraph.get(b).add(a);
        }

        int res = 0;
        int[] cnt = new int[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            int[] ch = new int[n + 1];
            int tmp = 0;
            ch[i] = 1;
            q.offer(i);
            while (!q.isEmpty()) {
                Integer p = q.poll();
                for (Integer x : graph.get(p)) {
                    if (ch[x] == 0) {
                        tmp++;
                        ch[x] = 1;
                        q.offer(x);
                    }
                }
            }
            q.clear();
            q.offer(i);
            while (!q.isEmpty()) {
                Integer p = q.poll();
                for (Integer x : reverseGraph.get(p)) {
                    if (ch[x] == 0) {
                        tmp++;
                        ch[x] = 1;
                        q.offer(x);
                    }
                }
            }
            cnt[i] = tmp;
        }

        for (int x : cnt) {
            if (x == n - 1) res++;
        }
        System.out.println(res);
    }
}
