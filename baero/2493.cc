#include <iostream>
#include <vector>
#include <stack>
using namespace std;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    int N;
    cin >> N;
    stack<pair<int,int>> s;
    vector<int> tower(N),ans(N,0);
    for(int i = 0; i < N; i++){
        cin >> tower[i];
        pair<int,int> tmp;
        while(!s.empty()){
            tmp = s.top();
            if(tmp.second < tower[i]) s.pop();
            else{
                ans[i] = tmp.first+1;
                break;
            }
        }
        s.push({i,tower[i]});
    }
    for(int i : ans)
        cout << i << " ";

    return 0;
}

