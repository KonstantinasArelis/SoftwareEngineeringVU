#define BOOK_AMOUNT 10000
#define MAX_BOOK_NAME_LENGHT 255
#define MAX_EMPLOYEE_AMOUNT 100000
#define LIBARY_OPEN_TIME 8
#define INPUT_FILE_ORDERED "bookListSorted2.txt"
#define INPUT_FILE_UNORDERED "bookListUnsorted2.txt"
// bookListSorted.txt - 211 books
// bookListSorted2.txt - 10000 books
// bookListSorted3.txt - 5000 books
// CHANGE BOOK_AMOUNT WHEN CHANGING INPUT FILES!!!

int compareAmount;

struct node;

struct node* newNode(char item[MAX_BOOK_NAME_LENGHT]);

struct node* insert(struct node* node, char key[MAX_BOOK_NAME_LENGHT]);

void inorder(struct node* root);

struct node* search(struct node* root, char key[MAX_BOOK_NAME_LENGHT]);

void deleteTree(struct node* root);