#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define MAX_VERTICES 20

// Function to perform DFS
void DFS(int graph[MAX_VERTICES][MAX_VERTICES], bool visited[MAX_VERTICES], int vertex, int searchedVertex)
{
    visited[vertex] = true;
    printf("%c ", 'A' + vertex);  // Print the visited vertex
    if(vertex == searchedVertex){
        printf("OK\n");
        //exit(0);
    }
    for (int i = 0; i < MAX_VERTICES; i++) {
        if (graph[vertex][i] == 1 && !visited[i]) {
            DFS(graph, visited, i, searchedVertex);  // Recursively explore adjacent unvisited vertices
        }
    }
}

int main()
{
    //char people[][255] = {"one","two","three","four","five","six","seven","eight"};

    /*
 = { //rodo i x is y
     A  B  C  D  E
 A  {0, 1, 0, 0, 0},
 B  {0, 0, 1, 1, 0},
 C  {0, 0, 0, 0, 1},
 D  {0, 0, 0, 0, 0},
 E  {0, 0, 0, 0, 0}
    };
    */

   // A, B, C, D, E, F, G, H

    int graph[MAX_VERTICES][MAX_VERTICES] = {0};
    //graph[source][destination]
    graph[0][1] = 1; // A-->B
    graph[1][2] = 1; // A-->C
    graph[2][6] = 1; // C-->G
    graph[1][7] = 1; // B-->H
    graph[6][7] = 1; // G-->H
    graph[7][5] = 1; // H-->F
    graph[7][5] = 1; // F-->H
    graph[7][3] = 1; // F-->D
    graph[7][1] = 1; // F-->B
    graph[1][7] = 1; // B-->F
    graph[1][3] = 1; // B-->D
    graph[3][1] = 1; // D-->B

    bool visited[MAX_VERTICES] = {false};

    printf("DFS traversal starting from vertex A: ");
    DFS(graph, visited, 0, 3);  // Start DFS from vertex A

    return 0;
}
