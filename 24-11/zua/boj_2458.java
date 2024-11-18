package com.sparta.codingtest.Nov24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q_2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄에서 학생 수(N)와 키 비교 횟수(M) 읽기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 학생의 수
        int m = Integer.parseInt(st.nextToken()); // 키 비교 횟수

        // 각 학생 간의 키 비교 관계를 저장할 2차원 배열 초기화
        // reachability[i][j]가 true이면 i번 학생이 j번 학생보다 키가 작음을 의미
        boolean[][] reachability = new boolean[n + 1][n + 1];

        // 키 비교 관계를 배열에 저장
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int smaller = Integer.parseInt(st.nextToken());
            int taller = Integer.parseInt(st.nextToken());
            reachability[smaller][taller] = true;
        }

        // 플로이드-워셜 알고리즘 -> 모든 학생 간의 도달 가능 여부 게산
        // 모든 중간 학생인 k를 거침
        // i -> j 로 가는 경로가 있는지 검사하여 도달 가능성 갱신
        // 중간 노드 k, 출발 노드 i, 도착 노드 j
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    // i -> k 그리고 k -> j 경로가 있을 경우, i -> j 도달 가능 설정
                    if (reachability[i][k] && reachability[k][j]) {
                        reachability[i][j] =  true;
                    }
                }
            }
        }

        // 순위를 정확히 알 수 있는 학생의 수를 세기 위한 변수 초기화
        int count = 0;

        //  각 학생마다 자신보다 키가 큰 학생과 작은 학생이 모두 있는지 확인
        for (int i = 1; i <= n; i++) {
            boolean canDetermineRank = true; // i번 학생의 순위를 결정할 수 있는지 여부 초기화
            for (int j = 0; j <= n; j++) {
                // i번 학생과 j번 학생이 서로 도달 가능하지 않다면
                if (i != j && reachability[i][j] && !reachability[j][i]) {
                    canDetermineRank = false;
                    break;
                }
            }
            // 순위를 결정할 수 있는 경우 count 증가
            if (canDetermineRank) {
                count++;
            }
        }

        // 순위를 정확히 알 수 있는 학생의 수 출력
        System.out.println(count);
    }
}