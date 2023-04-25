#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
#include "binarySearchTree.h"

//11 uzduotis
/*
this program simulates a libary operating on 3 organisational models:
1. unordered linear libary
2. ordered linear libary
3. binary tree libary

Starting data for simulation:
1. bookCompareTime: Time for 1 search operation - comparing if a book is the wanted book is considered a operation eg. 0.5 means 0.5 minutes for one book
2. visitorChance: Chance of a visitor - eg. 0.02 - 0.02 visitors a minute
3. bookExistanceChance: Change of a book existance - eg. 0.5 means 50% change of a book being in the libary
*/

#define BOOK_AMOUNT 211
#define MAX_BOOK_NAME_LENGHT 255
#define MAX_EMPLOYEE_AMOUNT 1000
#define LIBARY_OPEN_TIME 8

void InitializeStartingValues(float *bookCompareTime, float visitorChance[], float *bookExistanceChance);
void UpdateEmployeeOccupancy(int employeeOccupancy[]);
void PrintEmployeeOccupancy(int employeeOccupancy[]);
void PrintStartingValues(float visitorChance[],float bookCompareTime,float bookExistanceChance);
void UpdateEmployeeRecord(int *employeeRecord,int employeeOccupancy[]);
void InitializeTimetable(int visitorTimeTable[][60], float visitorChance[]);
void GenerateRandomBookID(float bookExistanceChance, int *randomBookID);
void InitializeBookArray(char UnorderedBookArray[][MAX_BOOK_NAME_LENGHT], char OrderedBookArray[][MAX_BOOK_NAME_LENGHT], struct node** bookBinaryTree);
float GetOrderedSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT], char OrderedBookArray[BOOK_AMOUNT][MAX_BOOK_NAME_LENGHT]);
float GetUnorderedSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT], char UnorderedBookArray[BOOK_AMOUNT][MAX_BOOK_NAME_LENGHT]);
float GetBinaryTreeSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT], struct node* bookBinaryTree);

// initializes the starting values to the according specifications:
// bookCompareTime time 0.1 - 1
// visitorChance 0.01 - 1
// bookExistanceChance 0.1 - 0.9
void InitializeStartingValues(float *bookCompareTime, float visitorChance[], float *bookExistanceChance){
    *bookCompareTime = (rand()%9+1)/10.0;
    for(int i=0;i<LIBARY_OPEN_TIME;i++){
        visitorChance[i] = (rand()%99+1)/100.0;
    }
    *bookExistanceChance = (rand()%9+1)/10.0;
    //*bookCompareTime=0.1;
    //*bookExistanceChance=0.1;
}

//subtracks 1 hour of every employee occupancy (to be called every hour change of simulation)
void UpdateEmployeeOccupancy(int employeeOccupancy[]){
    //PrintEmployeeOccupancy(employeeOccupancy);
    for(size_t i=0;i<MAX_EMPLOYEE_AMOUNT;i++){
        employeeOccupancy[i]-=1;
        if(employeeOccupancy[i]<0){
            employeeOccupancy[i]=0;
        }
    }
}

// prints the occupancy status of employees
void PrintEmployeeOccupancy(int employeeOccupancy[]){
    for(size_t i=0;i<MAX_EMPLOYEE_AMOUNT;i++){
        if(employeeOccupancy[i]!=0){
            printf("%lu: %d||",i,employeeOccupancy[i]);
        }
        
    }
    printf("\n");
}

// prints the randomly initialized starting values 
void PrintStartingValues(float visitorChance[],float bookCompareTime,float bookExistanceChance){
    printf("Starting values: \nbookCompareTime: %.2f\nbookExistanceChance: %.2f\nvisitorChance: ",bookCompareTime,bookExistanceChance);
    for(size_t i =0;i<LIBARY_OPEN_TIME;i++){
        printf("%.2f ",visitorChance[i]);
    }
    printf("\n");
    //printf("When no book is found, search time %.2f\n",BOOK_AMOUNT*bookCompareTime/60);
}

// updates the maximum amount of employees currently working
void UpdateEmployeeRecord(int *employeeRecord,int employeeOccupancy[]){
    int temp=0;
    for(size_t i=0;i<MAX_EMPLOYEE_AMOUNT;i++){
        if(employeeOccupancy[i]>0){
            temp++;
        }
    }
    if(temp>(*employeeRecord)){
        (*employeeRecord)=temp;
    }
}

void InitializeTimetable(int visitorTimeTable[][60], float visitorChance[]){
    int newVisitorAmount;
    for(size_t i=0;i<LIBARY_OPEN_TIME;i++){
        newVisitorAmount = visitorChance[i]*60;
        for(size_t j=0;j<newVisitorAmount;j++){
            visitorTimeTable[i][rand()%60]++;
        }
    }
}

void PrintTimetable(int visitorTimeTable[][60], int setting){
    if(setting == 0){
        for(size_t i=0;i<LIBARY_OPEN_TIME;i++){
            printf("%lu hour: \n",i);
            for(size_t j=0;j<60;j++){
                printf("%d ",visitorTimeTable[i][j]);
            }
        printf("\n");
        }
        return;
    }
    if(setting == 1){
        for(size_t i=0;i<LIBARY_OPEN_TIME;i++){
            printf("%lu hour: ",i);
            for(size_t j=0;j<60;j++){
                for(size_t k=0;k<visitorTimeTable[i][j];k++){
                    printf("*");
                }
            }
        printf("\n");
        }
        return;
    }
}

void GenerateRandomBookID(float bookExistanceChance, int *randomBookID){
    if((rand()%10)/10.0<bookExistanceChance){
        *randomBookID = rand()%BOOK_AMOUNT;
    }else{
        *randomBookID = BOOK_AMOUNT;
    }
}

void InitializeBookArray(char UnorderedBookArray[][MAX_BOOK_NAME_LENGHT], char OrderedBookArray[][MAX_BOOK_NAME_LENGHT], struct node** bookBinaryTree){
    FILE *UnorderedBookPtr = fopen("bookListUnsorted.txt","r");
    FILE *OrderedBookPtr = fopen("bookListSorted.txt","r");
    if(UnorderedBookPtr == NULL || OrderedBookPtr == NULL){
        printf("Could not open input files\n");
        exit(0);
    }

    for(size_t i=0;i<BOOK_AMOUNT;i++){
        fgets(UnorderedBookArray[i], MAX_BOOK_NAME_LENGHT, UnorderedBookPtr);
    }

    for(size_t i=0;i<BOOK_AMOUNT;i++){
        fgets(OrderedBookArray[i], MAX_BOOK_NAME_LENGHT, OrderedBookPtr);
    }

    *bookBinaryTree = insert(*bookBinaryTree, OrderedBookArray[0]);
    for(size_t i=1;i<BOOK_AMOUNT;i++){
        insert(*bookBinaryTree, OrderedBookArray[i]);
    }

    fclose(UnorderedBookPtr);
    fclose(OrderedBookPtr);
}

float GetOrderedSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT], char OrderedBookArray[BOOK_AMOUNT][MAX_BOOK_NAME_LENGHT]){
    float searchTime=0;
    for(size_t i=0;i<BOOK_AMOUNT;i++){
        searchTime+=bookCompareTime;
        if(bookName[0]<OrderedBookArray[i][0] || memcmp(bookName,OrderedBookArray[i],MAX_BOOK_NAME_LENGHT)==0){
            return searchTime;
        }
    }
    return searchTime;
}

// In other words, it returns bookCompareTime*randomBookID
float GetUnorderedSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT], char UnorderedBookArray[BOOK_AMOUNT][MAX_BOOK_NAME_LENGHT]){
    float searchTime=0;
    for(size_t i=0;i<BOOK_AMOUNT;i++){
        searchTime+=bookCompareTime;
        if(memcmp(bookName,UnorderedBookArray[i],MAX_BOOK_NAME_LENGHT)==0){
            return searchTime;
        }
    }
    return searchTime;
}

float GetBinaryTreeSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT], struct node* bookBinaryTree){
    compareAmount=0;
    search(bookBinaryTree, bookName);
    return compareAmount*bookCompareTime;
}

int main(){
    float bookCompareTime;
    float visitorChance[LIBARY_OPEN_TIME];
    float bookExistanceChance;
    int randomBookID;
    int visitorTimeTable[LIBARY_OPEN_TIME][60]={0};
    int newVisitorAmount;
    char UnorderedBookArray[BOOK_AMOUNT][MAX_BOOK_NAME_LENGHT];
    char OrderedBookArray[BOOK_AMOUNT][MAX_BOOK_NAME_LENGHT];
    char bookName[MAX_BOOK_NAME_LENGHT];
    struct node* bookBinaryTree = NULL;
    
    int employeeOccupancyOrderedSearch[MAX_EMPLOYEE_AMOUNT]={0};
    int employeeOccupancyUnorderedSearch[MAX_EMPLOYEE_AMOUNT]={0};
    int employeeOccupancyBinarySearch[MAX_EMPLOYEE_AMOUNT]={0};

    int employeeRecordOrderedSearch=0;
    int employeeRecordUnorderedSearch=0;
    int employeeRecordBinarySearch=0;

    // Initialization
    srand(time(NULL));
    InitializeStartingValues(&bookCompareTime,visitorChance,&bookExistanceChance);
    PrintStartingValues(visitorChance, bookCompareTime, bookExistanceChance);
    InitializeTimetable(visitorTimeTable,visitorChance);
    InitializeBookArray(UnorderedBookArray, OrderedBookArray, &bookBinaryTree);
    // PrintTimetable(visitorTimeTable,1);
    //inorder(bookBinaryTree); //print binary tree

    /*
    visitorChance[0]=1;
    visitorChance[1]=0;
    visitorChance[2]=0;
    visitorChance[3]=0;
    visitorChance[4]=0;
    visitorChance[5]=0;
    visitorChance[6]=0;
    visitorChance[7]=0;
    */

    // simulation
    for(size_t i=0;i<LIBARY_OPEN_TIME;i++){ // every hour
        for(size_t j=0;j<60;j++){ // every minute

            newVisitorAmount=visitorTimeTable[i][j];
            for(size_t k=0;k<newVisitorAmount;k++){
                GenerateRandomBookID(bookExistanceChance, &randomBookID);
                memcpy(bookName,OrderedBookArray[randomBookID],MAX_BOOK_NAME_LENGHT);

                // unordered linear search
                // update how much employee works
                for(size_t k=0;k<MAX_EMPLOYEE_AMOUNT;k++){
                    if(employeeOccupancyUnorderedSearch[k]==0){
                        employeeOccupancyUnorderedSearch[k]=GetUnorderedSearchTime(bookCompareTime, bookName, OrderedBookArray);
                        break;
                    }
                }

                // ordered linear search
                // update how much employee works
                for(size_t k=0;k<MAX_EMPLOYEE_AMOUNT;k++){
                    if(employeeOccupancyOrderedSearch[k]==0){
                        // update how much employee works
                        employeeOccupancyOrderedSearch[k]= GetOrderedSearchTime(bookCompareTime, bookName, OrderedBookArray);
                        break;
                    }
                }

                // Binary tree search
                // update how much employee works
                for(size_t k=0;k<MAX_EMPLOYEE_AMOUNT;k++){
                    if(employeeOccupancyOrderedSearch[k]==0){
                        //compareAmount=0;
                        //search(bookBinaryTree, bookName);
                        employeeOccupancyBinarySearch[k]=GetBinaryTreeSearchTime(bookCompareTime, bookName, bookBinaryTree);
                        break;
                    }
                }
                
            }
            UpdateEmployeeRecord(&employeeRecordUnorderedSearch,employeeOccupancyUnorderedSearch);
            UpdateEmployeeRecord(&employeeRecordOrderedSearch,employeeOccupancyOrderedSearch);
            UpdateEmployeeRecord(&employeeRecordBinarySearch,employeeOccupancyBinarySearch);

            UpdateEmployeeOccupancy(employeeOccupancyUnorderedSearch);
            UpdateEmployeeOccupancy(employeeOccupancyOrderedSearch);
            UpdateEmployeeOccupancy(employeeOccupancyBinarySearch);
        }
    }

    printf("Unordered search employee record: %d\n",employeeRecordUnorderedSearch);
    printf("Ordered search employee record: %d\n",employeeRecordOrderedSearch);
    printf("Binary tree search employee record: %d\n",employeeRecordBinarySearch);
    deleteTree(bookBinaryTree);
    return 0;
}