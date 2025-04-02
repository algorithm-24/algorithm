#include <iostream>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/9466 (텀 프로젝트)
 * @classification: dfs
 * @문제 푼 날짜 (자력으로 풂?): 25.04.02 (X)
 **/
int dfs(bool* visited, bool* finished, int* students, int curr_index)
{
  int answer = 0;
  visited[curr_index] = true;
  int next_index = students[curr_index];

  if (!visited[next_index])
  {
    answer = dfs(visited, finished, students, next_index);
  }
  else if ( !finished[next_index] )
  {
    for (int i=next_index;i != curr_index;i = students[i])
    {
      answer++;
    }

    answer++;
  }

  finished[curr_index] = true;

  return answer;
}

int main()
{
  ios_base::sync_with_stdio(false);
  cout.tie(NULL);
  cin.tie(NULL);

  int t, cnt;
  cin >> t;
  while(t--)
  {
    cin >> cnt;
    int students[cnt+1];
    bool visited[cnt+1] = {false};
    bool finished[cnt+1] = {false};
    int no_team = cnt;

    for(int i=1;i<=cnt;i++)
    {
      cin >> students[i];
    }

    for(int i=1;i<=cnt;i++)
    {
      if (!visited[i]) {
        no_team -= dfs(visited, finished, students, i);
      }
    }

    cout << no_team << "\n";
  }
}