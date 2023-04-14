#include <stdlib.h>
#include <stdio.h>
#include <main.h>


//display the list in from first to last
void displayForward(struct node *head){
   //start from the beginning
   struct node *ptr = head;

   while(ptr != NULL) {
      printf("%d, ",ptr->data);
      //printf("current: %p, next: %p, previous %p, data: %d\n",ptr,ptr->next,ptr->prev,ptr->data);
      ptr = ptr->next;
   }

   printf("\n");
}
// funkcija sausdinimui atgal
void displayBackward(struct node *head){
    //start from the beginning
    struct node *ptr = head;

    while(ptr->next != NULL) { //navigate to end of list
      //printf("%d, ",ptr->data);
      //printf("current: %p, next: %p, previous %p, data: %d\n",ptr,ptr->next,ptr->prev,ptr->data);
      ptr = ptr->next;
   }
    while(ptr->prev != NULL) { //print
      printf("%d, ",ptr->data);
      //printf("current: %p, next: %p, previous %p, data: %d\n",ptr,ptr->next,ptr->prev,ptr->data);
      ptr = ptr->prev;
   }
   printf("%d \n ",ptr->data);
}
// funkcija saraso istrinimui
void deleteList(struct node **head){
    struct node* current = *head;
    struct node* next;
    while(current != NULL){
        next = current->next;
        free(current);
        current = next;
    }
    *head=NULL;
}
//insert link at the first location
void insertFirst(int data,struct node **head){
    struct node *link = (struct node*) malloc(sizeof(struct node));
    link->data = data;

    if(*head!=NULL){
        (*head)->prev=link;
    }

    link->next = *head;
    *head = link;
    (*head)->prev = NULL;//arreikia
}

//delete a link with specific data
int delete(int data,struct node **head){
    struct node* current = *head;
    struct node* previous = NULL;
    struct node* next = NULL;
    int noElement = 1;
    int elementNotFound = 1;
    
    //if list is empty
    if(*head == NULL){
        return -1;
    }

    //navigate through list
    do{
        //printf("current: %p, next: %p, previous %p, data: %d\n",current,current->next,current->prev,current->data);
        noElement = 1;
        next = current->next;
        if(current->data == data){
            noElement= 0 ;
            elementNotFound = 0;
            if(current==*head){ //remove first element
                current->next->prev = NULL;
                *head=current->next;
            }
            else if(current->next == NULL){ // remove last element
                previous->next = NULL;
            }
            else{
                previous->next = current->next; // remove middle element
                current->next->prev = previous;
            }
            
            free(current);
        }
        
        if(noElement==1){
            //printf("previousL %p, next: %p\n",previous,next);
            //previous = next->prev;
            previous = current;
        }
        
    current=next;

    }while(current != NULL);
    
    if(elementNotFound == 1){
        //perror("Error: ");
        return -1;
        //printf("there is no element with %d value\n",data);
    }
    return 0;
}