#include <cstdio>
#include <cstdlib>

/**
 * @source: https://www.acmicpc.net/problem/1377 (버블 소트)
 * @classification: sort
 * @문제 푼 날짜 (자력으로 풂?): 25.03.09 (X)
 **/
struct Point
{
    int num;
    int idx;
};

int compare(const void* a, const void* b)
{
    return ((Point*) a) -> num - ((Point*)b) -> num;
}

int main()
{
    int n;
    scanf("%d", &n);

    Point* points = (Point*) malloc(n * sizeof(Point));

    for (int i=0;i<n;i++)
    {
        scanf("%d", &points[i].num);
        points[i].idx = i;
    }

    qsort(points, n, sizeof(Point), compare);

    int maxShift = 0;
    for (int i=0;i<n;i++)
    {
        if (maxShift < points[i].idx - i)
        {
            maxShift = points[i].idx - i;
        }
    }

    printf("%d", maxShift+1);

    free(points);
}