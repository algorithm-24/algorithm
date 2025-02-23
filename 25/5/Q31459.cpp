#include <iostream>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/31459 (초콜릿과 ㄱ나이트 게임 (Sweet))
 * @classification: greedy
 * @문제 푼 날짜 (자력으로 풂?): 25.02.19 (O)
 **/
int main()
{
  int t, h, w, x, y, knight;

  cin >> t;
  while (t--)
  {
    cin >> w >> h >> x >> y;
    knight = 0;
    bool choco[h][w];

    for(int i=0;i<h;i++)
    {
      for(int j=0;j<w;j++)
      {
        choco[i][j] = false;
      }
    }

    for(int i=0;i<h;i++)
    {
      for(int j=0;j<w;j++)
      {
        if( !choco[i][j] ) {
          ++knight;
          choco[i][j] = true;

          int i2 = i + y;
          int j2 = j + x;

          if(i2 >= 0 && i2 < h && j2 >= 0 && j2 < w)
          {
            choco[i2][j2] = true;
          }
        }
      }
    }

    cout << knight << endl;
  }
}