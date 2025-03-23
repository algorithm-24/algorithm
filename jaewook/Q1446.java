package jaewook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *@source: https://www.acmicpc.net/problem/1446 (지름길)
 *@classification: brute-force
 **/
// todo: dp를 이용한 o(n) 풀이 존재
public class Q1446 {
    static int[] dists;
    static HashMap<Integer, ArrayList<int[]>> shortCuts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        final int INF = 10_001;

        dists = new int[d+1];
        Arrays.fill(dists, INF);
        dists[0] = 0;

        shortCuts = new HashMap<>();

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());;

            if(shortCuts.containsKey(a)) {
                shortCuts.get(a).add(new int[]{b, dist});
            } else {
                ArrayList<int[]> al = new ArrayList<>();
                al.add(new int[]{b, dist});
                shortCuts.put(a, al);
            }
        }

        br.close();

        bt(0, d);

        System.out.print(dists[d]);
    }

    private static void bt(int a, int target) {
        if(shortCuts.containsKey(a)) {
            ArrayList<int[]> shortCut = shortCuts.get(a);
            for(int[] arr : shortCut) {
                int b = arr[0];
                int dist = arr[1];

                if(b <= target && dists[b] > dists[a] + dist) {
                    dists[b] = dists[a] + dist;
                    bt(b, target);
                }
            }
        }

        if(a+1 <= target && dists[a+1] > dists[a] + 1) {
            dists[a+1] = dists[a] + 1;
            bt(a+1, target);
        }
    }
}
