#include <stdlib.h>
#include <stdio.h>
#include "stack.h"

//initializes stack, first element is 0
void initializeStack(stack **Stack){
    *Stack = (stack *)malloc(sizeof(stack));
    if(*Stack==NULL){
        printf("Unsuccessful memory alloction\n");
        return;
    }
    (*Stack)->prev=NULL;
    (*Stack)->val=0;
}

//returns 0 if stack is not empty, 1 if stack is empty
int isStackEmpty(stack *Stack){
    if(Stack==NULL){
        return 1;
    }else{
        return 0;
    }
}

//returns 1 if stack is at least the size of STACK_SIZE. If size is less, returns 0
int isStackFull(stack *Stack){
    size_t stackSize=0;
    stack *current= Stack;
    while(current!=NULL){
        stackSize++;
        current = current->prev;
        if(stackSize==STACK_SIZE){
            return 1;
        }
    }
    return 0;
}

//pushes given value to given stack
void push(stack **Stack, int val){
    stack *previous = *Stack;
    *Stack = (stack *)malloc(sizeof(stack));
    if(*Stack==NULL){
        printf("Unsuccessful memory alloction\n");
        return;
    }
    (*Stack)->prev=previous;
    (*Stack)->val=val;
    //printf("pushed %d, at adress %p\n",(*Stack)->val, *Stack);
}

//returns and pop a value of given stack
int pop(stack **Stack){
    if(*Stack==NULL){
        printf("Undeflow: No item in stack to pop\n");
        return -1;
    }
    int returnValue = (*Stack)->val;
    stack *popedElement=*Stack;
    *Stack=(*Stack)->prev;
    free(popedElement);
    return returnValue;
}

//Deletes given stack
void deleteStack(stack **Stack){
    stack *current= *Stack;
    stack *previous;
    while(current!=NULL){
        previous = current;
        current = current->prev;
        free(previous);
    }
    *Stack = NULL;
}

//displays given stack top first
void displayStack(stack *Stack){
    stack *current= Stack;
    while(current!=NULL){
        printf("%d, ",current->val);
        current = current->prev;
    }
    printf("\n");
}
