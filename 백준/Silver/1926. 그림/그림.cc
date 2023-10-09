#include <bits/stdc++.h>

using namespace std;;

#define X first;
#define Y second;

int board[502][502] = {};
int vit[502][502] = {0};

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    pair<int,int> pos;
    int n, m;

    cin >> n >> m;
    for(int i=0; i < n; i++){
        for(int j=0; j < m; j++){
            cin >> board[i][j];
        }
    }

    int cnt, board_cnt=0, max = 0;
    for(int i=0; i < n; i++){
        for(int j=0; j < m; j++){
            if(vit[i][j] == 0 && board[i][j] == 1){
                queue<pair<int, int>> Q;
                vit[i][j] = 1;
                Q.push({i,j});
                cnt = 1;
                board_cnt++;

                while(!Q.empty()){
                    pos = Q.front();
                    Q.pop();

                    for(int k=0; k < 4; k++){;
                        int xx = pos.first + dx[k];
                        int yy = pos.second + dy[k];

                        if(xx < 0 || yy < 0 || xx >= n || yy >= m) continue;
                        if(vit[xx][yy] == 1) continue;
                        if(board[xx][yy] == 1){
                            Q.push({xx,yy});
                            vit[xx][yy] = 1;
                            cnt++;
                        }
                        
                    }
                }
                if(max < cnt) max = cnt;  // max = max(max, cnt); 
            }
        }
    }
    cout << board_cnt << "\n" << max << "\n";
}