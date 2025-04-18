package jaewook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * @source: https://www.acmicpc.net/problem/20056 (마법사 상어와 파이어볼)
 * @classification: implementation
 * @문제 푼 날짜 (혼자 힘으로 풂?): 25.04.18 (O)
 **/
public class Q20056 {
    final static int[][] moves = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    static FireballFactory fireballFactory;
    static int board_cnt;
    static int total_weight = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        board_cnt = Integer.parseInt(st.nextToken());
        int fireball_cnt = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        fireballFactory = new FireballFactory();

        for(int i=0;i<fireball_cnt;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            total_weight += w;
            fireballFactory.add(r, c, w, s, d);
        }

        while(t-->0) {
            fireballFactory.moveAll();
//            System.out.println("=========================");
//            System.out.println("총합 무게: " + total_weight);
//            for(Map.Entry<String, ArrayList<int[]>> m : fireballFactory.fireballs.entrySet()) {
//                for(int[] arr : m.getValue()) {
//                    System.out.println("위치: " + m.getKey() + ", 무게 : " + arr[0] + ", 속도: " + arr[1] + ", 방향: " + arr[2]);
//                }
//            }
        }

        br.close();

        System.out.print(total_weight);
    }

    public static class FireballFactory {
        HashMap<String, ArrayList<int[]>> fireballs = new HashMap();

        public void add(int r, int c, int w, int s, int d) {
            String key = cvt_c_to_str(r, c);
            if(fireballs.containsKey(key)) {
                fireballs.get(key).add(new int[] {w,s,d});
            } else {
                ArrayList<int[]> list = new ArrayList<>();
                list.add(new int[] {w,s,d});

                fireballs.put(key, list);
            }
        }

        public void moveAll() {
            HashMap<String, ArrayList<int[]>> tmp_fireballs = new HashMap();
            HashSet<String> duplications_fireball_keys = new HashSet();

            for(Map.Entry<String, ArrayList<int[]>> entry : fireballs.entrySet()) {
                int[] coordinate1 = cvt_str_to_c(entry.getKey());

                for(int[] fireball : entry.getValue()) {
                    int w = fireball[0];
                    int s = fireball[1];
                    int d = fireball[2];
                    int[] m = moves[d];

                    int[] coordinate2 = {
                            move_coordinate(coordinate1[0] + (s % board_cnt * m[0])),
                            move_coordinate(coordinate1[1] + (s % board_cnt * m[1]))
                    };

                    String key = cvt_c_to_str(coordinate2[0], coordinate2[1]);

                    if(tmp_fireballs.containsKey(key)) {
                        duplications_fireball_keys.add(key);
                        tmp_fireballs.get(key).add(new int[] {w,s,d});
                    } else {
                        ArrayList<int[]> f = new ArrayList<>();
                        f.add(new int[] {w,s,d});
                        tmp_fireballs.put(key, f);
                    }
                }
            }

            // total_weight 값 수정
            for(String key : duplications_fireball_keys) {
                ArrayList<int[]> fireballs2 = tmp_fireballs.get(key);
                tmp_fireballs.remove(key);

                boolean is_odd = fireballs2.get(0)[2] % 2 == 0;
                boolean is_same = true; // 올 짝/홀수이지 판별
                int sum_w = 0;
                int sum_s = 0;

                for(int[] fireball2 : fireballs2) {
                    int w2 = fireball2[0];
                    int s2 = fireball2[1];
                    int d2 = fireball2[2];
                    total_weight -= w2;

                    if(is_same) {
                        if(is_odd && d2 % 2 != 0) {
                            is_same = false;
                        } else if(!is_odd && d2 % 2 == 0) {
                            is_same = false;
                        }
                    }

                    sum_w += w2;
                    sum_s += s2;
                }

                /*
                나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
                질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
                속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
                합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
                */
                int new_w = get_new_w(sum_w);
                if(new_w == 0){
                    continue;
                }

                int new_s = get_new_s(sum_s, fireballs2.size());

                total_weight += 4 * new_w;

                if(total_weight == 0) { // todo: 음수면 throw Exception
                    System.out.print(total_weight);
                    System.exit(0);
                }

                tmp_fireballs.put(key, new ArrayList<>());
                for(int new_d : new_directions(is_same)) {
                    tmp_fireballs.get(key).add(new int[] {new_w, new_s, new_d});
                }
            }

            fireballs = tmp_fireballs;
        }

        private int get_new_w(int sum_w) {
            return sum_w / 5;
        }

        private int get_new_s(int sum_s, int cnt) {
            return sum_s / cnt;
        }

        private int[] new_directions(boolean is_same) {
            if(is_same) {
                return new int[] {0, 2, 4, 6};
            } else {
                return new int[] {1, 3, 5, 7};
            }
        }

        private int move_coordinate(int x) {
            if(x <= 0) {
                return board_cnt + x;
            } else if(x > board_cnt) {
                return x % board_cnt;
            }

            return x;
        }

        // coordinate -> string
        private static String cvt_c_to_str(int r, int c) {
            return r + "_" + c;
        }

        private int[] cvt_str_to_c(String key) {
            return Arrays.stream(key.split("_"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }
}
