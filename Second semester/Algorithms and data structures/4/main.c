#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// 6. The program finds out if a person can get a loan from another person using DFS search.
// Far lender: person A can get a loan from B they did A did a favor for B, or if B did a favor for someone else and so on.
// Neaer lender: person A canget loan from  B they did A did a favor for B, or if B did a favor for C, then A can get loan from C.
#define MAX_VERTICES 20
#define MAX_NAME_SIZE 255

// Linked list node structure
typedef struct ListNode {
    int vertex;
    struct ListNode* next;
} ListNode;

void DFS(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], ListNode* neighbors[],  int vertex, int searchedVertex);
int FindFarLender(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], ListNode* neighbors[], char *source, char *destination);
int FindNearLender(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], ListNode* neighbors[], char *source, char *destination);
void addVertex(char *name, char people[][MAX_NAME_SIZE]);
void addEdge(int graph[][MAX_VERTICES],char people[][MAX_NAME_SIZE], char *source, char *destination);
void testCase1();
void testCase2();
ListNode* createNode(int vertex);
void insertNode(ListNode** head, int vertex);

int result;

// Function to create a new linked list node
ListNode* createNode(int vertex)
{
    ListNode* newNode = (ListNode*)malloc(sizeof(ListNode));
    newNode->vertex = vertex;
    newNode->next = NULL;
    return newNode;
}

// Function to insert a node at the beginning of a linked list
void insertNode(ListNode** head, int vertex)
{
    ListNode* newNode = createNode(vertex);
    newNode->next = *head;
    *head = newNode;
}

// Function to perform DFS
void DFS(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], ListNode* neighbors[],  int vertex, int searchedVertex)
{
    visited[vertex] = true;
    printf("%s -> ", people[vertex]);  // Print the visited vertex
    if(vertex == searchedVertex){
        result = 1;
    }
    for (int i = 0; i < MAX_VERTICES; i++) {
        if (graph[vertex][i] == 1) {
            insertNode(&neighbors[vertex], i);
        }
        if (graph[vertex][i] == 1 && !visited[i]) {
            DFS(graph, visited, people, neighbors, i, searchedVertex);  // Recursively explore adjacent unvisited vertices
        }
    }
}

int FindFarLender(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], ListNode* neighbors[], char *source, char *destination){
    int startingVertex = -1;
    int searchedVertex = -1;
    result = -1;
    for(size_t i=0;i<MAX_VERTICES;i++){
        if(strcmp(people[i], source) == 0){
            startingVertex = i; 
        }
    }
    for(size_t i=0;i<MAX_VERTICES;i++){
        if(strcmp(people[i], destination) == 0){
            searchedVertex = i; 
        }
    }
    if(startingVertex == -1 || searchedVertex == -1){
        return -1;
    }
    DFS(graph, visited, people, neighbors, startingVertex, searchedVertex);
    printf("\n");

    return result;
}

int FindNearLender(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], ListNode* neighbors[], char *source, char *destination){
    int startingVertex = -1;
    int searchedVertex = -1;
    result = -1;
    for(size_t i=0;i<MAX_VERTICES;i++){
        if(strcmp(people[i], source) == 0){
            startingVertex = i; 
        }
    }
    for(size_t i=0;i<MAX_VERTICES;i++){
        if(strcmp(people[i], destination) == 0){
            searchedVertex = i; 
        }
    }
    if(startingVertex == -1 || searchedVertex == -1){
        return -1;
    }
    DFS(graph, visited, people, neighbors, startingVertex, searchedVertex);
    printf("\n");

    result = -1;
    printf("Neighbors of %s: \n", people[startingVertex]);
    ListNode* curr = neighbors[startingVertex];
    while (curr != NULL) {
        ListNode* curr2 = neighbors[curr->vertex];
        if(curr->vertex == searchedVertex){
            result = 1;
        }
        printf("\t%s: \n", people[curr->vertex]);
        while (curr2 != NULL) {
            if(curr2->vertex == searchedVertex){
                result = 1;
            }
            printf("\t\t%s \n", people[curr2->vertex]);
            curr2 = curr2->next;
        }

        curr = curr->next;
    }
    printf("\n");

    return result;
}

void addVertex(char *name, char people[][MAX_NAME_SIZE]){
    for(size_t i=0;i<MAX_VERTICES;i++){
        if(strcmp(people[i], "") == 0){
            strcpy(people[i], name);
            return;
        }
    }
    printf("Cant add vertex: max vertix amount reached\n");
}

void addEdge(int graph[][MAX_VERTICES],char people[][MAX_NAME_SIZE], char *source, char *destination){
    for(size_t i=0;i<MAX_VERTICES;i++){
        if(strcmp(people[i],source)==0){
            for(size_t k=0;k<MAX_VERTICES;k++){
                if(strcmp(people[k],destination)==0){
                    graph[i][k] = 1; // i-->k
                    //printf("%d-->%d\n",i,k);
                    return;
                }
            }
            printf("Cant add edge: destination vertex not found\n");
            return;
        }
    }
    printf("Cant add edge: source vertex not found\n");
    return;
}

int main()
{
    printf("Testcase 1:\n");
    testCase1();

    printf("\nTestcase 2:\n");
    testCase2();
    return 0;
}


void testCase1(){
    char people[MAX_VERTICES][MAX_NAME_SIZE] = {0};
    int graph[MAX_VERTICES][MAX_VERTICES] = {0};
    bool visited[MAX_VERTICES] = {false};
    ListNode* neighbors[MAX_VERTICES] = {NULL};

    addVertex("jonas", people);
    addVertex("marijonas", people);
    addVertex("kalafijoras", people);
    addVertex("valerijonas", people);
    addVertex("balionas", people);
    addVertex("saliamonas", people);
    addVertex("raudonas", people);
    addVertex("rajonas", people);
    
    addEdge(graph, people, "jonas","marijonas");
    addEdge(graph, people, "marijonas","jonas");
    addEdge(graph, people, "jonas","kalafijoras");
    addEdge(graph, people, "jonas","valerijonas");
    addEdge(graph, people, "marijonas","saliamonas");
    addEdge(graph, people, "kalafijoras","saliamonas");
    addEdge(graph, people, "kalafijoras","raudonas");
    addEdge(graph, people, "raudonas","balionas");
    addEdge(graph, people, "balionas","raudonas");
    addEdge(graph, people, "raudonas","rajonas");

    printf("\tDFS traversal from marijonas to raudonas:\n\t");
    if(FindFarLender(graph,visited, people, neighbors, "marijonas", "raudonas") == 1){
        printf("borrowing from far lender is allowed\n");
    }else{
        printf("borrowing from far lender is not allowed\n");
    }

    /*
    for(int i=0;i<8;i++){
        printf("Neighbors of %d: ", i);
        ListNode* curr = neighbors[i];
        while (curr != NULL) {
            printf("%d ", curr->vertex);
            curr = curr->next;
        }
        printf("\n");
    }
    */

   for (int j = 0; j < MAX_VERTICES; j++) {
            visited[j] = false;
            ListNode* temp = neighbors[j];
            while (temp != NULL) {
                ListNode* next = temp->next;
                free(temp);
                temp = next;
            }
            neighbors[j] = NULL;
    }

    printf("\tDFS traversal from marijonas to raudonas:\n\t");
    if(FindNearLender(graph,visited, people, neighbors, "marijonas", "raudonas") == 1){
        printf("borrowing from near lender is allowed\n");
    }else{
        printf("borrowing from near lender is not allowed\n");
    }
}  

void testCase2(){
    char people[MAX_VERTICES][MAX_NAME_SIZE] = {0};
    int graph[MAX_VERTICES][MAX_VERTICES] = {0};
    bool visited[MAX_VERTICES] = {false};
    ListNode* neighbors[MAX_VERTICES] = {NULL};
    
    //example 2
    addVertex("jonas", people);
    addVertex("marijonas", people);
    addVertex("kalafijoras", people);
    addVertex("valerijonas", people);
    addVertex("balionas", people);
    addVertex("saliamonas", people);
    addVertex("raudonas", people);
    addVertex("rajonas", people);
    
    addEdge(graph, people, "jonas","kalafijoras");
    addEdge(graph, people, "kalafijoras","jonas");
    addEdge(graph, people, "marijonas","jonas");
    addEdge(graph, people, "marijonas","kalafijoras");
    addEdge(graph, people, "kalafijoras","balionas");
    addEdge(graph, people, "jonas","balionas");
    addEdge(graph, people, "balionas","raudonas");
    addEdge(graph, people, "raudonas","balionas");
    addEdge(graph, people, "rajonas","raudonas");
    addEdge(graph, people, "rajonas","saliamonas");
    addEdge(graph, people, "saliamonas","raudonas");
    addEdge(graph, people, "saliamonas","valerijonas");
    addEdge(graph, people, "valerijonas","marijonas");
    addEdge(graph, people, "marijonas","valerijonas");
    addEdge(graph, people, "marijonas","raudonas");

    printf("\tjonas to raudonas:\n\t");
    if(FindFarLender(graph,visited, people, neighbors, "jonas", "raudonas") == 1){
        printf("borrowing from far lender is allowed\n");
    }else{
        printf("borrowing from far lender is not allowed\n");
    }

    /*
    for(int i=0;i<8;i++){
        printf("Neighbors of %d: ", i);
        ListNode* curr = neighbors[i];
        while (curr != NULL) {
            printf("%d ", curr->vertex);
            curr = curr->next;
        }
        printf("\n");
    }
    */

   for (int j = 0; j < MAX_VERTICES; j++) {
            visited[j] = false;
            ListNode* temp = neighbors[j];
            while (temp != NULL) {
                ListNode* next = temp->next;
                free(temp);
                temp = next;
            }
            neighbors[j] = NULL;
    }

    printf("\tDFS traversal from jonas to raudonas:\n\t");
    if(FindNearLender(graph,visited, people, neighbors, "jonas", "raudonas") == 1){
        printf("borrowing from near lender is allowed\n");
    }else{
        printf("borrowing from near lender is not allowed\n");
    }
}
