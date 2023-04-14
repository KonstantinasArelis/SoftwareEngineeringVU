#include <stdlib.h>
#include <stdio.h>

void printBoard(int board[8][8]);
int boardIsCovered(int board[8][8]);
void coverSquares(int boardCoverage[8][8], int xo, int yo);
void generateEvenSquares(int board[8][8], int depth);
void generateOddSquares(int board[8][8], int depth);

#define ANSWER_LIMIT 20

unsigned int actions=0;
unsigned int positions=0;
unsigned int answers=0;

//this program calculates ANSWER_LIMIT positions  8 bishops on a chess board in such a way,
//that every square is "seen" or covered by at least 1 rook. Only variantions with 4 black
//and 4 white bishops which are not on the edge of the board are considered.

void printBoard(int board[8][8]){
    for(size_t k=0;k<8;k++){
        printf("%d",k);
    }
    printf("\n");
    for(int i=7;i>=0;i--){
        for(size_t k=0;k<8;k++){
            if(board[k][i]==0){
                printf(" ");
            }else{
                printf("*");
            }
        }
        printf("%d\n",i);
    }
    for(size_t k=0;k<8;k++){
        printf("=");
    }
    printf("\n");
}

void printBoardToFile(int board[8][8]){
    FILE *fp=fopen("output.txt","a");
    fprintf(fp,"%d Board is correct after %d actions\n",positions, actions);
    char *abc={"abcdefgh"};
    for(size_t k=0;k<8;k++){
        fprintf(fp,"%c ",abc[k]);
    }
    fprintf(fp,"\n");
    for(int i=7;i>=0;i--){
        for(size_t k=0;k<8;k++){
            if(board[k][i]==0){
                fprintf(fp,"  ");
            }else{
                fprintf(fp,"* ");
            }
        }
            fprintf(fp,"%d\n",i+1);
    }
    for(size_t k=0;k<8;k++){
        fprintf(fp,"==");
    }
    fprintf(fp,"\n");
    fclose(fp);
}

//return 1 if board is fully covered, 0 otherwise
int boardIsCovered(int board[8][8]){
    int boardCoverage[8][8] = {0}; //1 means square is covered or is occupied by bishop
    for(size_t i=0;i<8;i++){
        for(size_t k=0;k<8;k++){
            if(board[i][k]==1){
                coverSquares(boardCoverage, i, k);
            }
        }
    }

    for(size_t i=0;i<8;i++){
        for(size_t k=0;k<8;k++){
            if(boardCoverage[i][k]==0){
                return 0;
            }
        }
    }

    return 1;
}

//alters given array in a way a bishops "sees" squares
//if a square is "seen" or occupied, it is 1, otherwise its value is 0
void coverSquares(int boardCoverage[8][8], int xo, int yo){
    int x=xo;
    int y=yo;
    while(x<8 && x>=0 && y<8 && y>=0){
        boardCoverage[x][y]=1;
        x++;
        y++;
    }
    x=xo;
    y=yo;
    while(x<8 && x>=0 && y<8 && y>=0){
        boardCoverage[x][y]=1;
        x++;
        y--;
    }
    x=xo;
    y=yo;
    while(x<8 && x>=0 && y<8 && y>=0){
        boardCoverage[x][y]=1;
        x--;
        y++;
    }
    x=xo;
    y=yo;
    while(x<8 && x>=0 && y<8 && y>=0){
        boardCoverage[x][y]=1;
        x--;
        y--;
    }
}

void tryBoardCombinations(int board[8][8]){
    generateOddSquares(board,0);
}

void generateOddSquares(int board[8][8], int depth){
    int x=-1,y=-1;
    //odd  - white
    for(size_t i=1;i<7;i++){
        for(size_t k=1;k<7;k++){
            actions++;
            if((i+k)%2==1 && board[i][k]==0){ //whites first
                if(x!=-1){
                    board[x][y]=0;
                }
                x=i;
                y=k;
                board[x][y]=1;
                //printBoardToFile(board);

                if(depth<3){//depth =4, 5 chess pieces
                    generateOddSquares(board, depth+1);
                }else{
                    generateEvenSquares(board, 0);
                    /*
                    if(boardIsCovered(board)==1){
                        printf("Success\n");
                        printBoard(board);
                        exit(EXIT_SUCCESS);
                    }
                    */
                    positions++;
                }
            }
        }
    }
    if(x!=-1){
        board[x][y]=0;
    }
}

void generateEvenSquares(int board[8][8], int depth){
    int x=-1,y=-1;

    for(size_t i=1;i<7;i++){
        for(size_t k=1;k<7;k++){
            actions++;
            if((i+k)%2==0 && board[i][k]==0){ //blacks second
                if(x!=-1){
                    board[x][y]=0;
                }
                x=i;
                y=k;
                board[x][y]=1;

                if(depth<3){ //depth =3, 4 chess pieces
                    generateEvenSquares(board, depth+1);
                }else{
                    if(boardIsCovered(board)==1){
                        //printf("Success\n");
                        printBoardToFile(board);
                        answers++;
                        if(answers==ANSWER_LIMIT){
                            printf("Answer cap reached\n");
                            exit(EXIT_SUCCESS);
                        }
                        //printBoard(board);
                        //exit(EXIT_SUCCESS);
                    }
                    positions++;
                }
            }
            
        }
    }
    if(x!=-1){
        board[x][y]=0;
    }
}

int main(){
    int board[8][8] = {0}; //empty board

    tryBoardCombinations(board);
    //printf("Actions: %d, Positions: %d\n",actions, positions);

    return 0;
}