#include <iostream>
#include <algorithm>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/3649 (로봇 프로젝트)
 * @classification: two pointer
 * @문제 푼 날짜 (자력으로 풂?): 25.04.01 (O)
 **/
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

        int str = 0, end = cnt - 1;

        while(str < end)
        {
          int sum = stick[str] + stick[end];

          if(sum == y_to_nano)
          {
            cout << "yes " << stick[str] << " " <<  stick[end] << "\n";
            is_find = true;
            break;
          }

          if(sum < y_to_nano)
          {
            str++;
          }
          else
          {
            end--;
          }
        }

        if(!is_find)
        {
            cout << "danger\n";
        }
    }
}