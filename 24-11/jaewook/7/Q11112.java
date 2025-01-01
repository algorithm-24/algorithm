import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @source: https://www.acmicpc.net/problem/11112 (Eight puzzle)
 * @classification: A*
 * @문제 푼 날짜 (자력으로 풂?): 24.01.01 (X)
 * @reference: https://blog.naver.com/PostView.naver?blogId=mario002&logNo=221958671537
 **/
// todo: 9! = 362880 이기 때문에 BFS로도 가능하다고 하네요
public class Main {
    static final String GOAL = "12345678#";
    static final int[] moves = {-3, -1, 1, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        StringBuilder puzzle;
        HashSet<String> visited;
        PriorityQueue<Node> pq;
        HashSet<String> impossible = new HashSet<>();

        while(n-- > 0) {
            br.readLine();
            puzzle = new StringBuilder();
            visited = new HashSet<>();
            pq = new PriorityQueue<>((p1, p2) -> {
                int fx1 = p1.diff + p1.count;
                int fx2 = p2.diff + p2.count;

                if(fx1 == fx2) {
                    return p1.count - p2.count;
                } else {
                    return fx1 - fx2;
                }
            });

            int blank = -1;
            for(int i=0;i<3;i++) {
                String str = br.readLine();

                for(int j=0;j<3;j++) {
                    if(str.charAt(j) == '#') {
                        blank = i * 3 + j;
                    }
                }

                puzzle.append(str);
            }

            String puzzleStr = puzzle.toString();

            if(impossible.contains(puzzleStr)) {
                result.append("impossible").append('\n');
                continue;
            }


            if(puzzleStr.equals(GOAL)) {
                result.append(0).append('\n');
                continue;
            }

            pq.offer(new Node(puzzleStr, 0, blank));
            visited.add(puzzleStr);
            boolean isPossible = false;

            L1: while( !pq.isEmpty() ) {
                Node node1 = pq.poll();
                String puzzle1 = node1.puzzle;
                int diff1 = node1.diff;
                int count1 = node1.count;
                int blank1 = node1.blank;

                for(int move : moves) {
                    int blank2 = blank1 + move;
                    int count2 = count1 + 1;

                    if(blank2 < 0 || blank2 > 8) {
                        continue;
                    }

                    if(blank1 == 0 || blank1 == 3 || blank1 == 6) {
                        if(move == -1) {
                            continue;
                        }
                    }

                    if(blank1 == 2 || blank1 == 5 || blank1 == 8) {
                        if(move == 1) {
                            continue;
                        }
                    }

                    String puzzle2 = changeTile(puzzle1, blank1, blank2);

                    if(impossible.contains(puzzle2)) {
                        break L1;
                    }

                    if(visited.contains(puzzle2)) {
                        continue;
                    }

                    if(puzzle2.equals(GOAL)) {
                        result.append(count2).append('\n');
                        isPossible = true;
                        break L1;
                    }

                    visited.add(puzzle2);
                    pq.offer(new Node(puzzle2, count2, blank2));
                }
            }

            if( !isPossible ) {
                result.append("impossible").append('\n');
                impossible.addAll(visited);
            }
        }

        br.close();

        System.out.print(result);
    }

    private static String changeTile(String puzzle1, int blank1, int blank2) {
        char c1 = puzzle1.charAt(blank1);
        char c2 = puzzle1.charAt(blank2);

        StringBuilder puzzle2 = new StringBuilder(puzzle1);

        puzzle2.setCharAt(blank1, c2);
        puzzle2.setCharAt(blank2, c1);

        return puzzle2.toString();
    }

    static class Node {
        String puzzle;
        int diff;
        int count;
        int blank;

        Node(String puzzle, int count, int blank) {
            this.puzzle = puzzle;
            this.count = count;
            this.diff = diff(puzzle);
            this.blank = blank;
        }

        private static int diff(String puzzle) {
            int diff = 0;

            for(int i=0;i<9;i++) {
                if(GOAL.charAt(i) != puzzle.charAt(i)) {
                    ++diff;
                }
            }

            return diff;
        }
    }
}
