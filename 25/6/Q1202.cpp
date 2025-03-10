#include <cstdio>
#include <cstdlib>
#include <queue>

using namespace std;

/**
 * @source: https://www.acmicpc.net/problem/1202 (보석 도둑)
 * @classification: greedy
 * @문제 푼 날짜 (자력으로 풂?): 25.03.10 (X)
 **/
struct Jewel
{
  int weight;
  int value;
};

int compare(const void* a, const void* b)
{
  return ((Jewel*) a) -> weight - ((Jewel*) b) -> weight;
}

int compareInt(const void* a, const void* b)
{
  return (*(int*)a) - (*(int*)b);
}

int main()
{
  int j_cnt, b_cnt;
  long long max_value = 0;
  scanf("%d %d", &j_cnt, &b_cnt);

  Jewel* jewels = (Jewel*) malloc(j_cnt * sizeof(Jewel));

  int bags[b_cnt];
  priority_queue<int, vector<int>, less<int>> pq;

  for(int i=0;i<j_cnt;i++)
  {
    scanf("%d %d", &jewels[i].weight, &jewels[i].value);
  }

  for(int i=0;i<b_cnt;i++)
  {
    scanf("%d", &bags[i]);
  }

  qsort(jewels, j_cnt, sizeof(Jewel), compare);
  qsort(bags, b_cnt, sizeof(int), compareInt);

  int j_index = 0;
  for(const int bag : bags)
  {
    while (j_index < j_cnt)
    {
      if(jewels[j_index].weight <= bag)
      {
        pq.push(jewels[j_index++].value);
      }
      else
      {
        break;
      }
    }

    if(pq.size() > 0)
    {
      max_value += pq.top();
      pq.pop();
    }
  }

  printf("%lld", max_value);

  free(jewels);
}