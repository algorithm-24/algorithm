#include <iostream>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/30804 (과일 탕후루)
 * @classification: two-poionter
 * @문제 푼 날짜 (자력으로 풂?): 25.01.23 (X)
 **/
int get_count(int* arr)
{
  int count = 0;
  for (int i=1;i<10;i++)
  {
    if (arr[i] > 0) {
      ++count;
    }
  }

  return count;
}

int main()
{
  ios_base::sync_with_stdio(false);
  cout.tie(NULL);
  cin.tie(NULL);

  int n, max_len = 1, str = 0, end = 0;
  cin >> n;
  int fruits[n];
  int fruit_count[10];

  for (int i = 0; i < n; i++)
  {
    cin >> fruits[i];
  }

  for (int i = 1; i < 10; i++)
  {
    fruit_count[i] = 0;
  }

  ++fruit_count[fruits[0]];

  while (str < n)
  {
    while (end < n)
    {
      ++end;

      if (end >= n)
      {
        break;
      }
      fruit_count[fruits[end]]++;

      if (get_count(fruit_count) > 2)
      {
        --fruit_count[fruits[end]];
        --end;

        max_len = max(max_len, end - str + 1);
        break;
      }

      max_len = max(max_len, end - str + 1);
    }

    --fruit_count[fruits[str]];
    ++str;
  }

  cout << max_len;
}