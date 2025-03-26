#include <iostream>
#include <queue>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/13335 (트럭)
 * @classification: implementation
 * @문제 푼 날짜 (자력으로 풂?): 25.03.25 (X)
 **/
int main()
{
  queue<int> bridge_trucks;
  int truck_cnt, limit_cnt, limit_weight;
  cin >> truck_cnt >> limit_cnt >> limit_weight;

  int trucks[truck_cnt];

  for (int i=0;i<limit_cnt;i++)
  {
    bridge_trucks.push(0);
  }

  for(int i = 0; i < truck_cnt; i++)
  {
    cin >> trucks[i];
  }

  int time = 0, tmp_weight = 0, t_index = 0;

  while(bridge_trucks.size() > 0)
  {
    ++time;
    tmp_weight -= bridge_trucks.front();
    bridge_trucks.pop();

    if (t_index < truck_cnt && tmp_weight + trucks[t_index] <= limit_weight)
    {
      tmp_weight += trucks[t_index];
      bridge_trucks.push(trucks[t_index]);
      ++t_index;
    }
    else if (t_index < truck_cnt)
    {
      bridge_trucks.push(0);
    }
  }

  cout << time;
}