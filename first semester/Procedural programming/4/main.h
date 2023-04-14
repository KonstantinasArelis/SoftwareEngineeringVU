
struct node {
   int data;
	
   struct node *next;
   struct node *prev;
};

//display the list in from first to last
void displayForward(struct node *head);

//display the list in from last to first
void displayBackward(struct node *head);

//insert link at the first location
void insertFirst(int data, struct node **head);

//delete link with specific data
int delete(int data, struct node **head);

//free memory asociated with list
void deleteList(struct node **head);

