#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// 6. The program finds out if a person can get a loan from another person using DFS search.
// A person A can get a loan from B they did A did a favor for B, or if B did a favor for someone else and so on.

#define MAX_VERTICES 20
#define MAX_NAME_SIZE 255

void DFS(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], int vertex, int searchedVertex);
int findBorrower(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], char *source, char *destination);
void addVertex(char *name, char people[][MAX_NAME_SIZE]);
void addEdge(int graph[][MAX_VERTICES],char people[][MAX_NAME_SIZE], char *source, char *destination);
void testCase1();
void testCase2();

int result;
// Function to perform DFS
void DFS(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], int vertex, int searchedVertex)
{
    visited[vertex] = true;
    printf("%s -> ", people[vertex]);  // Print the visited vertex
    if(vertex == searchedVertex){
        result = 1;
        //exit(0);
    }
    for (int i = 0; i < MAX_VERTICES; i++) {
        if (graph[vertex][i] == 1 && !visited[i]) {
            DFS(graph, visited, people, i, searchedVertex);  // Recursively explore adjacent unvisited vertices
        }
    }
}

int findBorrower(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], char people[][MAX_NAME_SIZE], char *source, char *destination){
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
    DFS(graph, visited, people, startingVertex, searchedVertex);
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

    //example 1
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

    printf("DFS traversal from marijonas to raudonas:\n");
    if(findBorrower(graph,visited, people, "marijonas", "raudonas") == 1){
        printf("borrowing is allowed\n");
    }else{
        printf("borrowing is not allowed\n");
    }
}

void testCase2(){
    char people[MAX_VERTICES][MAX_NAME_SIZE] = {0};
    int graph[MAX_VERTICES][MAX_VERTICES] = {0};
    bool visited[MAX_VERTICES] = {false};

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

    printf("DFS traversal from marijonas to raudonas:\n");
    if(findBorrower(graph,visited, people, "marijonas", "raudonas") == 1){
        printf("borrowing is allowed\n");
    }else{
        printf("borrowing is not allowed\n");
    }
}