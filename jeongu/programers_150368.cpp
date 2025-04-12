#include <iostream>
#include <string>
#include <vector>

using namespace std;

int plusCount = 0, income = 0;

void calc(vector<int>& sales, const vector<vector<int>>& users, vector<int> emoticons)
{
    if (sales.size() == emoticons.size())
    {
        int tempPlus = 0, tempIncome = 0;
        for (int i = 0; i < emoticons.size(); i++)
        {
            emoticons[i] -= emoticons[i] / 100 * sales[i];
        }

        for (auto user : users)
        {
            int sum = 0;
            for (int i = 0; i < emoticons.size(); i++)
            {
                if (user[0] <= sales[i])
                    sum += emoticons[i];
            }

            if (user[1] <= sum) tempPlus++;
            else tempIncome += sum;
        }

        if (tempPlus == plusCount && tempIncome > income) income = tempIncome;
        else if (tempPlus > plusCount)
        {
            plusCount = tempPlus;
            income = tempIncome;
        }
    }
    else
    {
        for (int i = 10; i <= 40; i += 10)
        {
            sales.push_back(i);
            calc(sales, users, emoticons);
            sales.pop_back();
        }
    }
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {

    vector<int> sales;
    calc(sales, users, emoticons);

    vector<int> answer;

    answer.push_back(plusCount);
    answer.push_back(income);

    return answer;
}