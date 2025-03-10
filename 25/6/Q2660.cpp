#include <iostream>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/2660 (회장뽑기)
 * @classification: floyd-warshall
 * @문제 푼 날짜 (자력으로 풂?): 25.03.08 (X, 로직은 맞았는데 입력 부분 오류 때문에 틀림)
 **/
int main()
{
  int n, a, b, INF = 51, president = 0;
  int min_score = INF;

  cin>>n;

  int dp[n][n], score[n];

  for (int i=0;i<n;i++)
  {
    for (int j=0;j<n;j++)
    {
      if (i == j)
      {
        dp[i][j] = 0;
      }
      else
      {
        dp[i][j] = INF;
      }
    }
  }

  while (1)
  {
    cin >> a >> b;

    if(a == -1 && b == -1)
      break;

    a--;
    b--;

    dp[a][b] = 1;
    dp[b][a] = 1;
  }

  for (int i=0;i<n;i++)
  {
    for (int j=0;j<n;j++)
    {
      for (int k=0;k<n;k++)
      {
        dp[j][k] = min(dp[j][k], dp[j][i] + dp[i][k]);
      }
    }
  }

  for (int i=0;i<n;i++)
  {
    int _max = 0;
    for (int j=0;j<n;j++)
    {
      _max = max(_max, dp[i][j]);
    }

    score[i] = _max;
    min_score = min(min_score, score[i]);
  }

  for (int i=0;i<n;i++)
  {
    if (score[i] == min_score)
    {
      ++president;
    }
  }

  cout << min_score << ' ' << president << '\n';

  for (int i=0;i<n;i++)
  {
    if (score[i] == min_score)
    {
      cout << i+1 << ' ';
    }
  }
}