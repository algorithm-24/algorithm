package week3;

import java.io.*;
import java.util.*;

public class boj_4256 {
    static int preIdx;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] preorder = new int[n];
            int[] inorder = new int[n];

            Map<Integer, Integer> inorderMap = new HashMap<>();

            // 전위 순회 입력 처리
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }

            // 중위 순회 입력 처리
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
                // 값과 해당 인덱스를 HashMap에 저장
                inorderMap.put(inorder[i], i);
            }

            preIdx = 0;
            postorder(preorder, 0, n - 1, inorderMap);
            sb.append('\n');
        }

        // 최종 결과 출력
        System.out.print(sb);
    }
    
    static void postorder(int[] preorder, int start, int end, Map<Integer, Integer> inorderMap) {
        if (start > end) return;

        int rootVal = preorder[preIdx++];
        int rootIdx = inorderMap.get(rootVal);

        postorder(preorder, start, rootIdx - 1, inorderMap);
        postorder(preorder, rootIdx + 1, end, inorderMap);
        sb.append(rootVal).append(' ');
    }
}

