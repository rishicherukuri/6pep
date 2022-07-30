#include<bits/stdc++.h>
using namespace std;

int find_max_profit(int k, vector<int> &price, int days){
    
    if(k == 0 || days <= 1){ // conditioin for no days = 1 and remaining  transactinos = 0
        return 0;
    }

    if(2*k > days){ // conditon when no transactions > 2*k unlimited transactions
        int ans = 0;

        for(int i = 1; i < days; i++){
            if(price[i] > price[i-1]){
                ans += (price[i] - price[i-1]);
            }
        }

        return ans;
    }


    int state[2*k+1];       //dp array to store maximum till the ith day
    state[0] += price[0];       

    for(int i = 1; i < 2*k; i++){  // state initializaion
        if(i%2 == 0){
            state[i] == INT_MIN;
        }
        else{
            state[i] = 0;
        }
    }

    for(int i = 0; i < days; i++){
        for(int j = 0; j < 2*k; j++){
            if(j == 0)
                state[j] = max(state[j], -price[i]);
            else if(j%2 == 0)
                state[j] = max(state[j],state[j-1]-price[i]);
            else
                state[j] = max(state[j],state[j-1]+price[i]);
        }
    }

    return state[2*k-1];
}

int main()
{
    vector<int> price = {100,30,15,10,8,25,80};
    int days = price.size();
    
    cout <<  "enter the number of transactions :";
    int k = 0;
    cin >> k;

    cout<< "Maximum profit that we can get using k transactions: "<< find_max_profit(k, price, days);

    return 0;
}