#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdlib.h>
//11 uzduotis
/*
this program simulates a libary operating on 3 organisational models:
1. unordered linear libary
2. ordered linear libary
3. binary tree libary

Starting data for simulation:
1. bookCompareTime: Time for 1 search operation - comparing if a book is the wanted book is considered a operation eg. 0.5 means 0.5 minutes for one book
2. visitorChance: Chance of a visitor - eg. 0.02 - 0.02 visitors a minute
3. bookExistanceChange: Change of a book existance - eg. 0.5 means 50% change of a book being in the libary
*/

#define BOOK_AMOUNT 211

void RandomInit(float *bookCompareTime, float visitorChance[], float *bookExistanceChange){
    *bookCompareTime = (rand()%9+1)/10.0;
    for(int i=0;i<8;i++){
        visitorChance[i] = (rand()%99+1)/100.0;
    }
    *bookExistanceChange = (rand()%9+1)/10.0;
}

int main(){
    float bookCompareTime;
    float visitorChance[8];
    float bookExistanceChange;
    char **unsortedBookList;
    float currentVisitors=0;
    int randomBookID;
    float newVisitorAmount;
    float temp=0;

    srand(time(NULL));   // Initialization, should only be called once.
    RandomInit(&bookCompareTime,visitorChance,&bookExistanceChange);
    printf("Starting values: \nbookCompareTime: %.2f\nbookExistanceChange: %.2f\nvisitorChance: ",bookCompareTime,bookExistanceChange);
    for(int i=0;i<8;i++){
        printf("%.2f ",visitorChance[i]);
    }

    printf("\n");
    for(int i=0;i<8;i++){
        newVisitorAmount = visitorChance[i]*60;
        currentVisitors += newVisitorAmount;
        //printf("test2: %f \n",currentVisitors);
        //unsorted list
        for(int j=0;j<newVisitorAmount;j++){
            //generation of book ID
            if((rand()%10)/10.0<bookExistanceChange){
                randomBookID = rand()%BOOK_AMOUNT;
            }else{
                randomBookID=BOOK_AMOUNT;
            }
            currentVisitors= currentVisitors-randomBookID*bookCompareTime/60;//linear search
            if(currentVisitors<0){
                currentVisitors=0;
            }
            temp +=randomBookID*bookCompareTime/60;
            
        }
        printf("test1: %f \n",temp);
        printf("visitors: %f \n",currentVisitors);

    }
    return 0;
}