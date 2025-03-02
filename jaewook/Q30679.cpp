#include <iostream>
#include <vector>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/30679 (별 가두기)
 * @classification: implementation
 * @문제 푼 날짜 (자력으로 풂?): 25.02.24 (X)
 **/
int main()
{
  int n, m;
  cin >> n >> m;
  int board[n][m];
  vector<int> vec;

  for(int i=0;i<n;i++)
  {
    for(int j=0;j<m;j++)
    {
      cin >> board[i][j];
    }
  }

  int moves[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  for(int i=0;i<n;i++)
  {
      int move_index = 0; // 우, 하, 좌, 상
      int x = i, y = 0;
      int count = 0;

      while(1)
      {
        int x2 = x + board[x][y] * moves[move_index][0], y2 = y + board[x][y] * moves[move_index][1];

        if(x2 < 0 || x2 >= n || y2 < 0 || y2 >= m) break;

        if(count == 10001)
        {
          vec.push_back(i+1);
          break;
        }

        move_index++;
        move_index %= 4;
        x = x2;
        y = y2;
        count++;
      }
  }

  cout << vec.size() << '\n';

  for(int i=0;i<vec.size();i++)
  {
    cout << vec[i] << ' ';
  }
}