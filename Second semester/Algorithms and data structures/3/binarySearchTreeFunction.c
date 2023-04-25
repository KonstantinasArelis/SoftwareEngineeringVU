// C program to insert a node
// in a BST
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "binarySearchTree.h"


// Given Node
struct node {
    char key[MAX_BOOK_NAME_LENGHT];
    struct node *left, *right;
};
  
// Function to create a new BST node
struct node* newNode(char item[MAX_BOOK_NAME_LENGHT])
{
    struct node* temp
        = (struct node*)malloc(
            sizeof(struct node));
    //temp->key = item;
    memcpy(temp->key,item,MAX_BOOK_NAME_LENGHT);
    temp->left = temp->right = NULL;
    return temp;
}
  
// Function to insert a new node with
// given key in BST
struct node* insert(struct node* node, char key[MAX_BOOK_NAME_LENGHT])
{
    // If the tree is empty, return a new node
    if (node == NULL){
        return newNode(key);
    }
        
    
    // Otherwise, recur down the tree
    if (strcmp(key,node->key)<0) {
        node->left = insert(node->left, key);
    }
    else if (strcmp(key,node->key)>0) {
        node->right = insert(node->right, key);
    }
   
    // Return the node pointer
    return node;
}

// Function to do inorder traversal of BST
void inorder(struct node* root)
{
    if (root != NULL) {
        inorder(root->left);
        printf("%s ", root->key);
        inorder(root->right);
    }else{
        printf("Tree is empty\n");
    }
}

void deleteTree(struct node* root)
{
    if (root == NULL) return;
 
    /* first delete both subtrees */
    deleteTree(root->left);
    deleteTree(root->right);
     
    free(root);
}

struct node* search(struct node* root, char key[MAX_BOOK_NAME_LENGHT])
{
    compareAmount++;
    // Base Cases: root is null or key is present at root
    if (root == NULL || (strcmp(root->key,key)==0))
        return root;

    // Key is greater than root's key
    if (strcmp(root->key,key)<0)
        return search(root->right, key);
 
    // Key is smaller than root's key
    return search(root->left, key);
}

/*
// Driver Code
int main()
{

    struct node* root = NULL;
    
    // inserting value 50
    root = insert(root, "aa");

    // inserting value 30
    insert(root, "df");
  
    // inserting value 20
    insert(root, "gf");
  
    // inserting value 40
    insert(root, "af");
  
    // inserting value 70
    insert(root, "dj");
  
    // inserting value 60
    insert(root, "bf");
  
    // inserting value 80
    insert(root, "gv");
    
    // print the BST
    inorder(root);
    
    return 0;
}
*/