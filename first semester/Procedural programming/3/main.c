#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define EILUTES_ILGIS 255

void pertvarkymas(char **eilute){ // funkcija gauna eilute ir buvusia eilute nusakanti kintamaji
        int perkelimo_indekas;
        char * perkelimas_ptr;
        
        perkelimas_ptr = strrchr(*eilute,'-'); //randama paskutinio char - vieta eiluteje
        perkelimo_indekas= perkelimas_ptr-(*eilute); //randama paskutinio char - indeksas

        if(perkelimas_ptr!= NULL && (*eilute)[perkelimo_indekas+1] != ' ' && (*eilute)[perkelimo_indekas+1] != '\n'){
            for(int i=perkelimo_indekas;i<EILUTES_ILGIS-1;i++){
            (*eilute)[i]=(*eilute)[i+1];
        }
        }

        /*
        if(*ar_eilute_be_perkelimo == 0){
            if((*eilute)[0]==' '){
                for(int i=EILUTES_ILGIS;i>0;i--){
                    (*eilute)[i]=(*eilute)[i-2];
                }
                (*eilute)[0]='-';
                (*eilute)[1]='\n';
                (*eilute)[2]=' ';
            }
            else if((*eilute)[0]=='\n'){
                for(int i=EILUTES_ILGIS;i>0;i--){
                    (*eilute)[i]=(*eilute)[i-2];
                }
                (*eilute)[0]='-';
                (*eilute)[1]='\n';
                }
                for(int i=0;i<EILUTES_ILGIS;i++){
                    if((*eilute)[i]==' '){
                        (*eilute)[i]='\n';
                        break;
                    }
                }
        }

        if(perkelimas_ptr!= NULL && (*eilute)[perkelimo_indekas+1] == '\n'){ // jei yra perkelimas, eiluteje istrinama '-' ir '\n'
            (*eilute)[perkelimo_indekas]='\0';
            (*eilute)[perkelimo_indekas+1]='\0';
            *ar_eilute_be_perkelimo=0;
        } else{
            *ar_eilute_be_perkelimo=1;
        }
        */
}

void spausdinti(char * eilute, FILE * output_file_ptr){
    fputs(eilute,output_file_ptr);
}

int main(int argc, char *argv[])
{
    FILE * input_file_ptr;
    FILE * output_file_ptr;
    char * eilute = (char*)malloc(EILUTES_ILGIS);

    if(argc !=3){
        printf("neteisingi komandines eilutes argumentai\n");
        return 0;
    }
    
    char *input= argv[1];
    char *output= argv[2];
    input_file_ptr = fopen(input,"r");
    output_file_ptr = fopen(output,"w");

    if(input_file_ptr==NULL || output_file_ptr== NULL){
        printf("klaida atidarant failus\n");
        return 0;
    }
    
    while(fgets(eilute,EILUTES_ILGIS,input_file_ptr)!=NULL){
        pertvarkymas(&eilute);
        spausdinti(eilute,output_file_ptr);
        //pertvarkyti burksnius(eilute)
        //spausdinti
    }

    free(eilute);
    fclose(input_file_ptr);
    fclose(output_file_ptr);
    return 0;
}
