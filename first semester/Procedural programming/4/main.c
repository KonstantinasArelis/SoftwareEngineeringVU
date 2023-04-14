//Konstantinas Arelis
//Programu sistemos 1 kursas, 3 grupe, 1 pogrupis
//4 uzduotis
//n11. Sudaryti dvipusį sąrašą. Parašyti procedūrą, kuri išmeta reikšme nurodytą elementą. Jeigu tokio elemento nėra, turi būti išvestas atitinkamas pranešimas.
#include <stdio.h>
#include <main.h>


//11 uzd
int main() {
    //first link
    struct node *head = NULL;

    insertFirst(100,&head);
    insertFirst(2,&head);
    insertFirst(100,&head);
    insertFirst(4,&head);
    insertFirst(5,&head);
    insertFirst(100,&head);

    printf("\n");  
    printf("\nList: ");  
    displayForward(head);

    if(delete(100,&head) == -1){
        printf("\nvalue was not found in list");
    }

    printf("\nList: ");  
    displayForward(head);
    printf("\nList backwards: ");  
    displayBackward(head);
    deleteList(&head);

	return 0;
}