#include <stdlib.h>
#include <stdio.h>
#include "stack.h"

int main(){
    stack *stack1 = NULL;
    printf("Empty: %d\n",isStackEmpty(stack1));
    initializeStack(&stack1);
    push(&stack1,1);
    push(&stack1,2);
    push(&stack1,3);
    push(&stack1,4);
    push(&stack1,5);
    pop(&stack1);
    push(&stack1,6);
    printf("Empty: %d\n",isStackEmpty(stack1));

    displayStack(stack1);
    deleteStack(&stack1);
    printf("Empty: %d\n",isStackEmpty(stack1));
//gcc main.c stackfunc.c
    return 0;
}