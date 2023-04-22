#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdlib.h>
#include <math.h>

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
#define MAX_EMPLOYEE_AMOUNT 1000

void InitializeStartingValues(float *bookCompareTime, float visitorChance[], float *bookExistanceChance);
void UpdateEmployeeOccupancy(float employeeOccupancy[]);
void PrintEmployeeOccupancy(float employeeOccupancy[]);
void PrintStartingValues(float visitorChance[],float bookCompareTime,float bookExistanceChance);
void UpdateEmployeeRecord(int *employeeRecord,float employeeOccupancy[]);
void ResetParamaters(float employeeOccupancy[]);

// initializes the starting values to the according specifications:
// bookCompareTime time 0.1 - 1
// visitorChance 0.01 - 1
// bookExistanceChance 0.5 - 0.9
void InitializeStartingValues(float *bookCompareTime, float visitorChance[], float *bookExistanceChance){
    *bookCompareTime = (rand()%9+1)/10.0;
    for(int i=0;i<8;i++){
        visitorChance[i] = (rand()%99+1)/100.0;
    }
    *bookExistanceChance = (rand()%4+5)/10.0;
    //*bookCompareTime=0.1;
    //*bookExistanceChance=1;
}

//subtracks 1 hour of every employee occupancy (to be called every hour change of simulation)
void UpdateEmployeeOccupancy(float employeeOccupancy[]){
    //PrintEmployeeOccupancy(employeeOccupancy);
    for(size_t i=0;i<MAX_EMPLOYEE_AMOUNT;i++){
        employeeOccupancy[i]-=1;
        if(employeeOccupancy[i]<0){
            employeeOccupancy[i]=0;
        }
    }
}

// prints the occupancy status of employees
void PrintEmployeeOccupancy(float employeeOccupancy[]){
    for(size_t i=0;i<MAX_EMPLOYEE_AMOUNT;i++){
        if(employeeOccupancy[i]!=0){
            printf("%lu: %.2f\n",i,employeeOccupancy[i]);
        }
    }
}

// prints the randomly initialized starting values 
void PrintStartingValues(float visitorChance[],float bookCompareTime,float bookExistanceChance){
    printf("Starting values: \nbookCompareTime: %.2f\nbookExistanceChance: %.2f\nvisitorChance: ",bookCompareTime,bookExistanceChance);
    for(size_t i =0;i<8;i++){
        printf("%.2f ",visitorChance[i]);
    }
    printf("\n");
    printf("When no book is found, search time %f\n",BOOK_AMOUNT*bookCompareTime/60);
}

// updates the maximum amount of employees currently working
void UpdateEmployeeRecord(int *employeeRecord,float employeeOccupancy[]){
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

// 
void ResetParamaters(float employeeOccupancy[]){
    for(size_t i=0;i<MAX_EMPLOYEE_AMOUNT;i++){
        employeeOccupancy[i]=0;
    }
}


int main(){
    float bookCompareTime;
    float visitorChance[8];
    float bookExistanceChance;
    char **unsortedBookList;
    int randomBookID;
    float newVisitorAmount;
    float employeeOccupancy[MAX_EMPLOYEE_AMOUNT]={0};
    int employeeRecord=0;
    // Initialization
    srand(time(NULL));
    InitializeStartingValues(&bookCompareTime,visitorChance,&bookExistanceChance);
    PrintStartingValues(visitorChance, bookCompareTime, bookExistanceChance);

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
    // 8 hour unsorted search simulation
    for(size_t i=0;i<8;i++){
        newVisitorAmount = floor(visitorChance[i]*60);
        
        //unsorted list
        for(size_t j=0;j<newVisitorAmount;j++){
            //generation of book ID
            if((rand()%10)/10.0<bookExistanceChance){
                randomBookID = rand()%BOOK_AMOUNT;
            }else{
                randomBookID=BOOK_AMOUNT;
            }
            for(size_t k=0;k<MAX_EMPLOYEE_AMOUNT;k++){
                if(employeeOccupancy[k]==0){
                    employeeOccupancy[k]=randomBookID*bookCompareTime/60;
                    break;
                }
            }
        }

        //printf("new visitors: %.2f\n",newVisitorAmount);
        //printf("before %lu hour:\n",i);
        //PrintEmployeeOccupancy(employeeOccupancy);

        UpdateEmployeeRecord(&employeeRecord,employeeOccupancy);
        UpdateEmployeeOccupancy(employeeOccupancy);
    }
    printf("Employee record: %d\n",employeeRecord);
    ResetParamaters(employeeOccupancy);


    return 0;
}