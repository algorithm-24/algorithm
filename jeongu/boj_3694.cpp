#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int x;
	while (cin >> x)
	{
		vector<int> v;
		x *= 10000000;

		int n;
		cin >> n;
		for (int i = 0; i < n; i++)
		{
			int input;
			cin >> input;
			v.push_back(input);
		}

		sort(v.begin(), v.end());
		int start = 0;
		int end = v.size() - 1;
		int l1 = -1, l2 = -1;

		while (start < end)
		{
			int sum = v[start] + v[end];
			if (sum == x)
			{
				l1 = v[start];
				l2 = v[end];
				break;
			}
			else if (sum < x)
			{
				start++;
			}
			else
			{
				end--;
			}
		}

		if (l1 == -1)
		{
			cout << "danger\n";
		}
		else
		{
			cout << "yes " << l1 << " " << l2 << '\n';
		}

	}

	return 0;
}