
#include<bits/stdc++.h>

#define X first
#define Y second

using namespace std;

int arr[51][51];
int vit[51][51];

int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    int cnt;
    cin >> cnt;

    while(cnt--){
        int n, m, num;
        cin >> n >> m >> num;

        fill(&arr[0][0], &arr[n-1][m], 0);
        fill(&vit[0][0], &vit[n-1][m], 0);

        int x, y;
        while(num--){ 
            cin >> x >> y;
            
            arr[x][y] = 1;
        }

        queue<pair<int,int>> Q;
        int result = 0;

        for(int i=0; i < n; i++){
            for(int j=0; j < m; j++){
                if(arr[i][j] == 1 && vit[i][j] == 0){
                    Q.push({i,j});
                    vit[i][j] = 1;
                    result++;
                }

                while(!Q.empty()){
                    pair<int,int> pos = Q.front(); Q.pop();
                
                    for(int k=0; k < 4; k++){
                        int move_x = pos.X + dx[k];
                        int move_y = pos.Y + dy[k];

                        if(move_x < 0 || move_y < 0 ||  move_x >= n || move_y >= m) continue;
                        if(vit[move_x][move_y] != 0 || arr[move_x][move_y] == 0) continue;
                        
                        vit[move_x][move_y] = 1;
                        Q.push({move_x, move_y});
                    }
                }
            }
        }
        cout << result << "\n";
    }
}
