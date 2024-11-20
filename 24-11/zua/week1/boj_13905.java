package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class boj_13905 {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, s, e;
    static List<Edge>[] edges;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 노드 개수
        M = Integer.parseInt(st.nextToken()); // 간선 개수

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 시작 노드
        e = Integer.parseInt(st.nextToken()); // 도착 노드

        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            edges[h1].add(new Edge(h2, k));
            edges[h2].add(new Edge(h1, k));
        }

        // 최단 거리 배열 초기화
        distance = new int[N + 1];
        Arrays.fill(distance, 0);

        // 우선순위 큐 초기화 (최대 가중치를 최대화하는 경로를 찾기 위해 최대 힙 사용)
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> -o.weight));
        pq.offer(new Edge(s, INF));
        distance[s] = INF;

        // 다익스트라 알고리즘 변형
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int dist = current.weight;
            int now = current.to;

            if (distance[now] > dist) {
                continue;
            }

            for (Edge edge : edges[now]) {
                int cost = Math.min(dist, edge.weight);
                if (cost > distance[edge.to]) {
                    distance[edge.to] = cost;
                    pq.offer(new Edge(edge.to, cost));
                }
            }
        }

        System.out.println(distance[e]);
    }

    // 간선 클래스 정의
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}