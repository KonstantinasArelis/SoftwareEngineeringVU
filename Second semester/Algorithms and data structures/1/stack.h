#define STACK_SIZE 5

typedef struct node{
    int val;
    struct node *prev;
}stack;

//initializes stack, first element is 0
void initializeStack(stack **Stack);

//returns 0 if stack is not empty, 1 if stack is empty
int isStackEmpty(stack *Stack);

//returns 1 if stack is at least the size of STACK_SIZE. If size is less, returns 0
int isStackFull(stack *Stack);

//pushes given value to given stack
void push(stack **Stack, int val);

//returns and pop a value of given stack
int pop(stack **Stack);

//Deletes given stack
void deleteStack(stack **Stack);

//displays given stack top first
void displayStack(stack *Stack);