#include <iostream>
#include <algorithm>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/3649 (로봇 프로젝트)
 * @classification: binary search
 * @문제 푼 날짜 (자력으로 풂?): 25.04.01 (O)
 **/
int binarySearch(int arr[], int skip_index, int arr_length, int target)
{
  int answer = -1, str = 0, end = arr_length - 1;

  while(str <= end)
  {
    int mid = (end + str) / 2;

    if (mid == skip_index)
    {
      str++;
      continue;
    }

    if(arr[mid] == target)
    {
      answer = mid;
      break;
    }
    else if(arr[mid] < target)
    {
      str = mid + 1;
    }
    else
    {
      end = mid - 1;
    }
  }

  return answer;
}

int main()
{
  int y, cnt;

  while(cin >> y)
  {
    int y_to_nano = y * 10'000'000;
    bool is_find = false;

    cin >> cnt;

    int stick[cnt];

    for(int i = 0; i < cnt; i++)
    {
      cin >> stick[i];
    }

    sort(stick, stick + cnt);

    for(int i = 0; i < cnt; i++)
    {
      int target = y_to_nano - stick[i];
      int answer = binarySearch(stick, i, cnt, target);
      if(answer > 0)
      {
        int _min, _max;

        if (stick[i] < stick[answer])
        {
          _min = stick[i];
          _max = stick[answer];
        }
        else
        {
          _min = stick[answer];
          _max = stick[i];
        }

        cout << "yes " << _min << " " <<  _max << "\n";
        is_find = true;
        break;
      }
    }

    if(!is_find)
      cout << "danger\n";
  }
}