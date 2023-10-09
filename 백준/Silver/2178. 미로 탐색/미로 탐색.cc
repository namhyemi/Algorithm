#include<bits/stdc++.h>

#define X first
#define Y second

using namespace std;

int maze[100][100];
int cnt[100][100];
int vit[100][100] = {0};

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    string str;
    int n,m;
    cin >> n >> m;

    fill(&cnt[0][0], &cnt[n][m], 10001);

    for(int i=0; i < n ; i++){ // 미로 입력값 담기
        cin >> str;
        for(int j=0; j < m; j++){
            maze[i][j] = str[j] - '0';
        }
    }

    queue<pair<int,int>> Q;
    pair<int, int> pos;
    
    Q.push({0,0});
    vit[0][0] = 1;
    cnt[0][0] = 1;

    while(!Q.empty()){
        pos = Q.front(); 
        Q.pop();
 
        for(int k=0; k < 4; k++){
            int move_x = pos.X + dx[k];
            int move_y = pos.Y + dy[k];

            if(move_x < 0 || move_x >= n || move_y < 0 || move_y >= m) continue;
            if(vit[move_x][move_y] == 1 || maze[move_x][move_y] == 0) continue;
            vit[move_x][move_y] = 1;
            Q.push({move_x,move_y});
            

            if(cnt[move_x][move_y] > cnt[pos.X][pos.Y]+1) // 다른 곳을 통해서 들렸을때보다 값이 작다면
                // cout << "원래 값/ 새로운 값" << vit[move_x][move_y] << " " << vit[pos.X][pos.Y]+1 << "\n"; 
                cnt[move_x][move_y] = cnt[pos.X][pos.Y]+1; 
        }       
    }
    cout << cnt[n-1][m-1] << "\n";
}