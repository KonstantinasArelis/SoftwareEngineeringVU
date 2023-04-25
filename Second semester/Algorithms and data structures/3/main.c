#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
#include "binarySearchTree.h"

// 11 uzduotis
// Konstantinas Arelis 3gr
/*
this program simulates a libary operating on 3 organisational models:
1. unordered linear libary
2. ordered linear libary
3. binary tree libary

Starting data for simulation:
1. bookCompareTime: Time for 1 search operation - comparing if a book is the wanted book is considered a operation eg. 0.5 means 0.5 minutes for one book
2. visitorChance: Chance of a visitor - eg. 0.02 - 0.02 visitors a minute. Chances are unique to every hour
3. bookExistanceChance: Change of a book existance - eg. 0.5 means 50% change of a book being in the libary
*/

void InitializeStartingValues(float *bookCompareTime, float visitorChance[], float *bookExistanceChance);
void UpdateEmployeeOccupancy(int employeeOccupancy[]);
void PrintEmployeeOccupancy(int employeeOccupancy[]);
void PrintStartingValues(float visitorChance[],float bookCompareTime,float bookExistanceChance);
void UpdateEmployeeRecord(int *employeeRecord,int employeeOccupancy[]);
void InitializeTimetable(int visitorTimeTable[][60], float visitorChance[]);
void GenerateRandomBookID(float bookExistanceChance, int *randomBookID);
void InitializeBookBinaryTree(struct node** bookBinaryTree);
float GetOrderedSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT]);
float GetUnorderedSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT]);
float GetBinaryTreeSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT], struct node* bookBinaryTree);
void UpdateBookName(int randomBookID, char bookName[MAX_BOOK_NAME_LENGHT]);



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
    //*bookCompareTime=1;
    //*bookExistanceChance=0.1;
}

// subtracks 1 hour of every employee occupancy (to be called every hour change of simulation)
void UpdateEmployeeOccupancy(int employeeOccupancy[]){
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
    // printf("When no book is found, search time %.2f\n",BOOK_AMOUNT*bookCompareTime/60);
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

// initializes the amount of visitors for each hour and minute
void InitializeTimetable(int visitorTimeTable[][60], float visitorChance[]){
    int newVisitorAmount;
    for(size_t i=0;i<LIBARY_OPEN_TIME;i++){
        newVisitorAmount = visitorChance[i]*60;
        for(size_t j=0;j<newVisitorAmount;j++){
            visitorTimeTable[i][rand()%60]++;
        }
    }
}

// prints the timetable of visitors to libary
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

// generates a random book id 0 - BOOK_AMOUNT
void GenerateRandomBookID(float bookExistanceChance, int *randomBookID){
    if((rand()%10)/10.0<bookExistanceChance){
        *randomBookID = rand()%BOOK_AMOUNT;
    }else{
        *randomBookID = BOOK_AMOUNT;
    }
}

// initializes arrays with book title data
void InitializeBookBinaryTree(struct node** bookBinaryTree){
    FILE *OrderedBookPtr = fopen(INPUT_FILE_ORDERED,"r");
    char temp[MAX_BOOK_NAME_LENGHT];
    if(OrderedBookPtr == NULL){
        printf("Could not open input files\n");
        exit(0);
    }

    fgets(temp,MAX_BOOK_NAME_LENGHT,OrderedBookPtr);
    *bookBinaryTree = insert(*bookBinaryTree, temp);
    for(size_t i=1;i<BOOK_AMOUNT;i++){
        fgets(temp,MAX_BOOK_NAME_LENGHT,OrderedBookPtr);
        insert(*bookBinaryTree, temp);
    }

    fclose(OrderedBookPtr);
}

// returns amount of minutes to find book in ordered search
float GetOrderedSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT]){
    FILE *orderedBookPtr = fopen(INPUT_FILE_ORDERED,"r");
    char temp[MAX_BOOK_NAME_LENGHT];
    float searchTime=0;
    for(size_t i=0;i<BOOK_AMOUNT;i++){
        searchTime+=bookCompareTime;
        fgets(temp,MAX_BOOK_NAME_LENGHT,orderedBookPtr);
        if(bookName[0]<temp[0] || memcmp(bookName,temp,MAX_BOOK_NAME_LENGHT)==0){
            fclose(orderedBookPtr);
            return searchTime;
        }
    }
    fclose(orderedBookPtr);
    return searchTime;
}

// returns amount of minutes to find book in unordered search
// In other words, it returns bookCompareTime*randomBookID
float GetUnorderedSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT]){
    FILE *UnorderedBookPtr = fopen(INPUT_FILE_UNORDERED,"r");
    float searchTime=0;
    char temp[MAX_BOOK_NAME_LENGHT];
    for(size_t i=0;i<BOOK_AMOUNT;i++){
        searchTime+=bookCompareTime;
        fgets(temp,MAX_BOOK_NAME_LENGHT,UnorderedBookPtr);
        if(memcmp(bookName,temp,MAX_BOOK_NAME_LENGHT)==0){
            fclose(UnorderedBookPtr);
            return searchTime;
        }
    }
    fclose(UnorderedBookPtr);
    return searchTime;
}

// returns amount of minutes to find book in binary tree search
float GetBinaryTreeSearchTime(float bookCompareTime, char bookName[MAX_BOOK_NAME_LENGHT], struct node* bookBinaryTree){
    compareAmount=0;
    search(bookBinaryTree, bookName);
    return compareAmount*bookCompareTime;
}

// updates the book name according to book ID
void UpdateBookName(int randomBookID, char bookName[MAX_BOOK_NAME_LENGHT]){
    FILE *UnorderedBookPtr = fopen(INPUT_FILE_ORDERED,"r");
    if(UnorderedBookPtr == NULL){
        printf("Could not open input files\n");
        exit(0);
    }

    for(size_t i=0;i<BOOK_AMOUNT;i++){
        if(fgets(bookName, MAX_BOOK_NAME_LENGHT, UnorderedBookPtr)!= NULL & i==randomBookID){
            return;
        }
    }


    fclose(UnorderedBookPtr);
}

int main(){
    float bookCompareTime;
    float visitorChance[LIBARY_OPEN_TIME];
    float bookExistanceChance;
    int randomBookID;
    int visitorTimeTable[LIBARY_OPEN_TIME][60]={0};
    int newVisitorAmount;
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
    InitializeBookBinaryTree(&bookBinaryTree);
    PrintTimetable(visitorTimeTable,1);
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
                UpdateBookName(randomBookID, bookName);

                // unordered linear search
                // update how much employee works
                for(size_t k=0;k<MAX_EMPLOYEE_AMOUNT;k++){
                    if(employeeOccupancyUnorderedSearch[k]==0){
                        employeeOccupancyUnorderedSearch[k]=GetUnorderedSearchTime(bookCompareTime, bookName);
                        break;
                    }
                }

                // ordered linear search
                // update how much employee works
                for(size_t k=0;k<MAX_EMPLOYEE_AMOUNT;k++){
                    if(employeeOccupancyOrderedSearch[k]==0){
                        employeeOccupancyOrderedSearch[k]= GetOrderedSearchTime(bookCompareTime, bookName);
                        break;
                    }
                }

                // Binary tree search
                // update how much employee works
                for(size_t k=0;k<MAX_EMPLOYEE_AMOUNT;k++){
                    if(employeeOccupancyOrderedSearch[k]==0){
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