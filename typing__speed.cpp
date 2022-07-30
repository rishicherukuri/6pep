#include<bits/stdc++.h>
#include<fstream>
#include<time.h>

using namespace std;

int find_score(int t){

    if(t >= 3)
        return 0;
    else if (t >= 2) return 1;
    else return 2;

}

string userin(){
    string input;

    cin >> input;

    return input;
}

int new_word(){

    vector<string> words = {"rock", "paper" , "scissor", "cricket", "ball", "college", "laptop" , "bottle" , "book" , "board", "remote", "chair", "flower", "mobile",
                            "light", "rotate", "india", "desk" , "browser", "upper", "lower"};
    int index = rand()%words.size();

    cout<<"Type: \t" <<  words[index] << endl;
    clock_t t = clock();
    string typed_word = userin();
    t = clock() - t;

    int duration = ((int)t)/CLOCKS_PER_SEC;
    int score = 0;

    if(typed_word != words[index]){
        cout<< "Typed a wrong word. Try another word" << endl;
        return -1;
    }
    else{
        cout<< "You took " << duration << " Seconds" << endl;
        score = find_score(duration);
    }

    return score;
}


int main()
{   

    ifstream in;
    in.open("highscore.txt");
    int highscore;
    in >> highscore;

    srand(time(0));
    int score = 0;

    while(1){

        int player_choice = 0;
        cout << "1.New word  2.Score 3.Highscore 4.Save&Quit" << endl;
        cout << "Player choice is : ";
        cin >> player_choice;

        int word_score = 0;
        switch(player_choice){
        case 1:
            word_score += new_word();
            //cout << "Score is : "<< word_score << endl;
            score += word_score;
            break;
        case 2:
            cout<< "Your Score is "<< score<< endl;
            break;
        case 3:
        {
            cout<< "Highest Score is "<< highscore<< endl;
            break;
        }
        case 4:
            {   
                cout << "your score is " << score <<endl;
                if(score > highscore){

                    ofstream out;
                    out.open("highscore.txt", ofstream::out | ofstream::trunc);
                    highscore = score;
                    out << score;
                    score = 0;
                }
                cout<< "Saving your score...."<< endl;

                return 0;

            }
            break;
        }
    }
    return 0;
}
