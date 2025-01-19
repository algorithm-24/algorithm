#include <iostream>
#include <vector>
#include <array>
#include <queue>
#include <cstring>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/2931 (가스관)
 * @classification: implementation
 * @문제 푼 날짜 (자력으로 풂?): 25.01.20 (O, 하루 날림)
 **/
char** matrix;

vector<vector<array<int, 2>>> pipes = {
    {{-1, 0}, {1, 0}}, // |
    {{0, -1}, {0, 1}}, // -
    {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}, // +
    {{1, 0}, {0, 1}}, // 아래-오른쪽 (1)
    {{-1, 0}, {0, 1}}, // 위-오른쪽 (2)
    {{0, -1}, {-1, 0}}, // 왼쪽-위 (3)
    {{0, -1}, {1, 0}}, // 왼쪽-아래 (4)
};
char BLANK = '.';
int adjacency[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

vector<array<int, 2>> get_pipe(int x, int y)
{
    switch (matrix[x][y])
    {
    case '|':
        return pipes[0];
    case '-':
        return pipes[1];
    case '+':
        return pipes[2];
    case '1':
        return pipes[3];
    case '2':
        return pipes[4];
    case '3':
        return pipes[5];
    case '4':
        return pipes[6];
    case 'M':
    case 'Z':
       return {};  // 빈 벡터 반환
    default:
        throw invalid_argument("Invalid pipe character");
    }
}

char get_pipe_char(int pipe_index)
{
    switch (pipe_index)
    {
    case 0:
        return '|';
    case 1:
        return '-';
    case 2:
        return '+';
    case 3:
        return '1';
    case 4:
        return '2';
    case 5:
        return '3';
    case 6:
        return '4';
    default:
        throw invalid_argument("Invalid pipe index");
    }
}

void find_pipe(int r, int c, int x, int y, int& found_x, int& found_y, char& found_pipe, bool& is_found_pipe)
{
    for (int i = 0; i < pipes.size(); i++)
    {
        bool leak = false;
        vector<array<int, 2>> test_pipe = pipes[i];

        for (int j = 0; j < test_pipe.size(); j++)
        {
            int pass_x = x + test_pipe[j][0], pass_y = y + test_pipe[j][1];

            if (pass_x < 0 || pass_x >= r || pass_y < 0 || pass_y >= c || matrix[pass_x][pass_y] == BLANK)
            {
                leak = true;
                break;
            }

            if (matrix[pass_x][pass_y] == 'M' || matrix[pass_x][pass_y] == 'Z') continue;

            vector<array<int, 2>> next_pipe = get_pipe(pass_x, pass_y);
            bool is_connect = false;

            for (int k = 0; k < next_pipe.size(); k++)
            {
                // 역추적
                int reverse_pass_x = pass_x + next_pipe[k][0], reverse_pass_y = pass_y + next_pipe[k][1];

                if (reverse_pass_x == x && reverse_pass_y == y)
                {
                    is_connect = true;
                    break;
                }
            }

            if ( !is_connect )
            {
                leak = true;
            }
        }

        for (int j = 0; j < 4; j++)
        {
            int pass_x = x + adjacency[j][0], pass_y = y + adjacency[j][1];

            if (pass_x < 0 || pass_x >= r || pass_y < 0 || pass_y >= c || matrix[pass_x][pass_y] == BLANK)
            {
                continue;
            }

            if (matrix[pass_x][pass_y] == 'M' || matrix[pass_x][pass_y] == 'Z') continue;

            vector<array<int, 2>> next_pipe = get_pipe(pass_x, pass_y);

            for (int k = 0; k < next_pipe.size(); k++)
            {
                // 역추적
                int reverse_pass_x = pass_x + next_pipe[k][0], reverse_pass_y = pass_y + next_pipe[k][1];

                if (reverse_pass_x == x && reverse_pass_y == y)
                {
                    bool is_connect2 = false;

                    for (int l=0;l<test_pipe.size();l++)
                    {
                        if (x+test_pipe[l][0] == pass_x && y+test_pipe[l][1] == pass_y)
                        {
                            is_connect2 = true;
                            break;
                        }
                    }

                    if ( !is_connect2 )
                    {
                        leak = true;
                        break;
                    }
                }
            }
        }

        if (!leak)
        {
            found_x = x, found_y = y, found_pipe = get_pipe_char(i);
            is_found_pipe = true;
            break;
        }
    }
}

int main()
{
    int r, c, found_x, found_y, init_x, init_y;
    char found_pipe;
    cin >> r >> c;
    matrix = new char*[r];
    bool visited[r][c];
    memset(visited, false, sizeof(visited));

    for (int i = 0; i < r; i++)
    {
        matrix[i] = new char[c];
    }

    queue<array<int, 2>> q;
    char temp_char;
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            cin >> temp_char;
            matrix[i][j] = temp_char;

            if (temp_char == 'M')
            {
                visited[i][j] = true;
                init_x = i;
                init_y = j;
            }
        }
    }

    bool is_found_pipe = false;
    // edge case: 시작점에 연결된 파이프가 없을 경우
    bool is_connected = false;
    for (int i=0;i<4;i++)
    {
        int x = init_x + adjacency[i][0], y = init_y + adjacency[i][1];

        if (x < 0 || x >= r || y < 0 || y >= c)
        {
            continue;
        }

        if (matrix[x][y] != BLANK)
        {
            is_connected = true;
            visited[x][y] = true;
            q.push({x, y});
        }
    }

    if ( !is_connected )
    {
        for (int i = 0; i < 4; i++)
        {
            array<int, 2> poll = q.front();
            int x2 = poll[0] + adjacency[i][0], y2 = poll[1] + adjacency[i][1];

            if (x2 < 0 || y2 < 0 || x2 >= r || y2 >= c) continue;

            find_pipe(r, c, x2, y2, found_x, found_y, found_pipe, is_found_pipe);
        }
    }

    while (!is_found_pipe && !q.empty())
    {
        array<int, 2> poll = q.front();
        int x1 = poll[0], y1 = poll[1];
        q.pop();

        if (matrix[x1][y1] == BLANK)
        {
            find_pipe(r, c, x1, y1, found_x, found_y, found_pipe, is_found_pipe);
            break;
        }

        if (is_found_pipe)
        {
            break;
        }

        vector<array<int, 2>> pipe = get_pipe(x1, y1);

        for (int i = 0; i < pipe.size(); i++)
        {
            int x2 = x1 + pipe[i][0], y2 = y1 + pipe[i][1];
            if (visited[x2][y2]) continue;

            visited[x2][y2] = true;
            q.push({x2, y2});
        }
    }

    cout << found_x+1 << " " << found_y+1 << " " << found_pipe << '\n';

    for (int i = 0; i < r; i++)
    {
        delete[] matrix[i];
    }
    delete[] matrix;
}
