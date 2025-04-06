#include<iostream>
#include<vector>
#define UNVISITED 111111
using namespace std;

int T, n, a;
vector<int> v, visited, mem, S, num;
int numCounter;

void dfs(int u){
    int next = v[u];
    mem[u] = num[u] = numCounter++;
    S.push_back(u);
    visited[u] = 1;
    if(mem[next] == UNVISITED) dfs(next);
    if(visited[next]) mem[u] = min(mem[u], mem[next]);
    if(num[u] == mem[u]){
        if(mem[next] != mem[u]) return;
        while(1){
            int tmp = S.back();
            S.pop_back(); a--;
            if(u == tmp) break;
        }
    }
}



int main(){
    cin.tie(0); ios_base::sync_with_stdio(0);

    cin >> T;
    while(T--){
        cin >> n; a = n;numCounter = 0;
        v.clear(); visited.clear(); mem.clear(); S.clear(); num.clear();
        v.resize(n+1); visited.resize(n+1); mem.resize(n+1,UNVISITED); num.resize(n+1);
        for(int i = 1; i <= n; i++) cin >> v[i];

        for(int i = 1; i <= n; i++){
            if(!visited[i]) dfs(i);
        }
        cout << a << endl;
    }
    return 0;
}
