#include <iostream>
#include <vector>
using namespace std;

vector<pair<int,int>> v;
vector<int> ans;
int N, K;
int main() {
    cin.tie(nullptr);cout.tie(nullptr); ios_base::sync_with_stdio(false);
    int weight, value, m = 0;
    cin >> N >> K; v.resize(N); ans.resize(K+1);
    for(int i = 0; i < N; i++) {
        cin >> v[i].first >> v[i].second;
    }

    for(int i = 0; i < N; i++){
        weight = v[i].first; value = v[i].second;
        for(int j = K; j >= weight; j--){
            if(ans[j-weight]+value > ans[j]) ans[j] = ans[j-weight]+value;
            if(ans[j] > m) m = ans[j];
        }
    }
    cout << m << "\n";

    return 0;
}
