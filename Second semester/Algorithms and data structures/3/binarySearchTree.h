#define BOOK_AMOUNT 1000
#define MAX_BOOK_NAME_LENGHT 255
#define MAX_EMPLOYEE_AMOUNT 100000
#define LIBARY_OPEN_TIME 8

int compareAmount;

struct node;

struct node* newNode(char item[MAX_BOOK_NAME_LENGHT]);

struct node* insert(struct node* node, char key[MAX_BOOK_NAME_LENGHT]);

void inorder(struct node* root);

struct node* search(struct node* root, char key[MAX_BOOK_NAME_LENGHT]);

void deleteTree(struct node* root);