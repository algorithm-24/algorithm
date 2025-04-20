#include <iostream>
#include <algorithm>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/9024 (두 수의 합)
 * @classification: two pinter, greedy
 * @문제 푼 날짜 (자력으로 풂?): 25.04.20 (O)
 **/
int main()
{
  ios_base::sync_with_stdio(false);
  cout.tie(NULL);
  cin.tie(NULL);

  int t;
  cin>>t;

  while(t--)
  {
    int cnt, target;

    cin>>cnt>>target;
    int nums[cnt];

    for(int i=0;i<cnt;i++)
    {
      cin>>nums[i];
    }

    sort(nums,nums+cnt);

    int str = 0, end = cnt -1;
    int min_abs = abs(nums[str] + nums[end] - target);
    int combination = 0;

    while(str < end)
    {
      int sum = nums[str] + nums[end];
      int _abs = abs(target - sum);

      if(_abs == min_abs)
      {
        combination++;
      }
      else if(_abs < min_abs)
      {
        min_abs = _abs;
        combination = 1;
      }

      if(str + 1 == end)
      {
        break;
      }

      int next_abs_1 = abs(nums[str + 1] + nums[end] - target);
      int next_abs_2 = abs(nums[str] + nums[end-1] - target);

      if(next_abs_1 < next_abs_2)
      {
        str++;
      }
      else
      {
        end--;
      }
    }

    cout<<combination<<'\n';
  }
}