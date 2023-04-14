#include <stdio.h>
#include <stdlib.h>

struct data{
    int a;
    int b[10];
    int *c;
};

void square(struct data data){
    for(int i=0;i<10;i++){
        data.b[i]= data.b[i]*data.b[i];
    }

    for(int i=0;i<data.a;i++){
        data.c[i]= data.c[i]*data.c[i];
    }
}

int main(){
    struct data data;
    data.a = 15;//size of dynamic array
    data.c = (int*)malloc(data.a*sizeof(int));

    //assign array values
    for(int i=0;i<10;i++){
        data.b[i]= i;
    }
    for(int i=0;i<data.a;i++){
        data.c[i]= i;
    }

    square(data);

    //print array values
    for(int i=0;i<10;i++){
        printf("%d\n",data.b[i]);
    }
    printf("\n");
    for(int i=0;i<data.a;i++){
        printf("%d\n",data.c[i]);
    }

    return 0;
}