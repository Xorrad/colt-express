

#include <stdio.h>
#include <stdlib.h>

/*************************************************/
/*                                               */
/*                sucre syntaxique               */
/*                                               */
/*************************************************/

#define AND &&
#define OR ||
#define ISNOT !=
#define NOT !
#define then 

typedef enum { false, true} bool;

/*************************************************/
/*                                               */
/*            factorielle                        */
/*                                               */
/*************************************************/

long fact (int n) 
{ if (n==0) return 1 ;
  else return n * fact(n-1) ; 
}

// itou avec un arg out => passage par adresse

void bisfact(int n, long * r) 
{ if (n==0) 
         *r=1.0 ;
  else { bisfact(n-1,r) ;
         *r = *r *n ;
       }
}

long fact2 (int n)
{ long r ;
  bisfact(n,&r) ;
  return r ;
}

/*************************************************/
/*                                               */
/*            Calcul de e                        */
/*                                               */
/*************************************************/

  // d'après google,
  // e = 2,7182818284 5904523536 0287471352 6624977572 4709369995 
  //       9574966967 6277240766 3035354759 4571382178 5251664274

     // Il est proposé de faire 3 versions pour observer des phénomènes de précision.
     // C'est le même code, seul le type change.

/*************************************************/

float Efloat () {
	int fac = 1;
	float res = 1.;
	for (int i=2; i<12; i++){
		res+=1./fac;
		fac=fac*i;
	}
	
	return res ;
	}

/*************************************************/

double Edouble () {
	int fac = 1;
	double res = 1.;
	for (int i=2; i<35; i++){
		res+=1./fac;
		fac=fac*i;
	}
	
	return res ;
	}
  
/*************************************************/

long double Elongdouble () {
	int fac = 1;
	long double res = 1.;
	for (int i=2; i<35; i++){
		res+=1./fac;
		fac=fac*i;
	}
	
	return res ;
	}

/*************************************************/
/*                                               */
/*            Suite Y                            */
/*                                               */
/*************************************************/
 void afficheYfloat (int j)  {
	float res = Efloat()-1;
	printf("Y0 = %f \n",res);
	for (int n=1; n<j;n++){//j=31 semble idéal
		res=n*res-1.;
		printf("Y %d = %f \n",n,res);
	}
}

/*************************************************/
void afficheYdouble (int j) {
	float res = Edouble()-1;
	printf("Y0 = %f \n",res);
	for (int n=1; n<j;n++){
		res=n*res-1.;
		printf("Y %d = %f \n",n,res);
	}
}
/*************************************************/
void afficheYlongdouble (int j) {
	long double res = Elongdouble()-1;
	printf("Y0 = %Lf \n",res);
	for (int n=1; n<j;n++){
		res=n*res-1.;
		printf("Y %d = %Lf \n",n,res);
	}
}

/* lorsque j'execute ces fonctions, je constate que SuiteYFloat devient
 * croissante à partir de Y9, SuiteYDouble devient croissante à partir de
 * Y10 et SuiteYLongDouble devient croissante à partir de Y11 */






/*************************************************/
/*                                               */
/*            Puissance                          */
/*                                               */
/*************************************************/
double power1(double x, int n){
	if (n==0) then return 1;
	if (n<0) then return 1./power1(x, n*(-1));
	return  x*power1(x, n-1);
}
/*************************************************/
double power2a(double x, int n){
	float res = 1;
	
	for (int i=1; i<=abs(n); i++){
		res=res*x;
	}
	if (n>=0) then return res;
	return 1./res;
}
/*************************************************/
double power2b(double x, int n){
	float res = 1;
	
	while (n>0){
		res=res*x;
		n--;
	}
	return res;
}
/*************************************************/
void Pow(double x, int n, float *r){
	if (n!=0){
		*r=*r*x;
		Pow(x,n-1,r);
	}
}
/*************************************************/
double power3(double x, int n){
	float r=1;
	Pow(x,n,&r);
	return  r;
}
	/*float power3(int x, int n){
	if (n==0) then return 1;
	if (n<0) then return 1./power3(x, n*(-1));
	return  x*power3(x, n-1);
	*/


double power4 (double x, long n){return power3(x,n);} //placeholder pour les tests

/*************************************************/



     //   Observation (1+1/10^k)^(10^k) : rame : 8 en 1/2s, 9 en qqs s, 10 en 1m

double power5 (double x, long n)
{
    if (n == 0)
         return 1 ;
    else if (n % 2 == 0)
              return (power5(x,n/2)*power5(x,n/2)   ) ;
         else return (power5(x,n/2)*power5(x,n/2) *x) ;   
}

/*************************************************/


double power6(double x, int n){
	if (n==0) then return 1;
	float y=power6(x, n/2);
	if (n%2==0) then return y*y;
	return y*y*x;
}
/*************************************************/
double power7(double x, int n){
	if (n==0) then return 1;
	if (n%2==0) then return power7(x*x, n/2);
	return power7(x*x, n/2)*x;
}
/*************************************************/
double Pow8(double x, int n, int r){
	if (n==0) then return r;
	if ((n%2)==0){
		return Pow8(x*x,n/2,r);
	}
	else{return Pow8(x*x,n/2,r*x);}
}

float power8(double x, int n){
	return Pow8(x,n,1);
}
/*************************************************/
void Pow9(double x, int n, float *r){
	if (n!=0){
		if (n%2==1) then *r=(*r)*x;
		Pow9(x*x,n/2,r);
	}
}

double power9(double x, int n){
	float r=1;
	Pow9(x,n,  &r);
	return r;
}
/*************************************************/
/*float power10(int x, int n){
	float r = 1;
	
	while (n!=0){
		if (n%2==1) then r=r*x;
		n=n/2;
		x=x*x;
	}
	return r;
}
*/

/*************************************************/

     //   Observation (1+1/10^k)^(10^k) : calcul immédiat

double power10 (double x, long n)
{
    double r = 1.0 ;
    while (n ISNOT 0) 
      { if (n % 2 == 1) then r = r*x ; // no else
        n = n / 2 ; 
        x = x * x ;
       }
    return r ;
}



/*************************************************/

double power (double x, long n, int i)
{ switch (i)  
   {
   case 1 : return power1(x,n) ; break ;
   case 2 : return power2a(x,n) ; break ;  // 2 pour 2a
   case 0 : return power2b(x,n) ; break ; // 0 pour 2b
   case 3 : return power3(x,n) ; break ;
   case 4 : return power4(x,n) ; break ;
   case 5 : return power5(x,n) ; break ;
   case 6 : return power6(x,n) ; break ;
   case 7 : return power7(x,n) ; break ;
   case 8 : return power8(x,n) ; break ;
   case 9 : return power9(x,n) ; break ;
  case 10 : return power10(x,n) ; break ;
     default : return 0 ; 
   }
}

   // remarque : les break sont ici inutiles car les returns font déjà des break

/*************************************************/

    // memes versions que la 10 mais avec des long double, puis des floats
    // but du jeu : observer des variations de precision

   // observation :
   //   imprécision à partir de k~5 (float), k~12 (double), k~14 (long double)
   //   rend 1 à partir de k=8 (float), k=16 (double)
   //   rend 1 non observé sur les long double, maxint atteint avant

long double power11 (long double x, long n)
{
    long double r = 1.0 ;
    while (n ISNOT 0) 
      { if (n % 2 == 1) then r = r*x ; // no else
        n = n / 2 ; 
        x = x * x ;
       }
    return r ;
}

/*************************************************/

float power12 (float x, long n)
{
    float r = 1.0 ;
    while (n ISNOT 0) 
      { if (n % 2 == 1) then r = r*x ; // no else
        n = n / 2 ; 
        x = x * x ;
       }
    return r ;
}


/*************************************************/
/*                                               */
/*             Ackermann                         */
/*                                               */
/*************************************************/


int A1(int m, int n) {
	if (m==0) then return n+1;
	if (n==0) then return A1(m-1,1);
	else then return A1(m-1,A1(m,n-1));
	}

int Ackermann1 (int m) {
	return A1(m,0);
	}

/*************************************************/

int A2(int m, int n) {
	if (m==0) then return n+1;
	int r=1;
	for (int i=1;i<=n+1;i++){
		r=A2(m-1,r);
	}
	return(r);
	}

int Ackermann2 (int m) {
	return A2(m,0);
	}

/*************************************************/

int Ackermann3 (int m) {
	return 0;
	}

/*************************************************/

int Ackermann4 (int m) {
	return 0;
	}

/*************************************************/

int Ackermann5 (int m) { return 0 ; }

/*************************************************/
  
int Ackermann (int m, int i)
{ switch (i)  
   {
   case 1 : return Ackermann1 (m) ; // break ;
   case 2 : return Ackermann2 (m) ; // break ;
   case 3 : return Ackermann3 (m) ; // break ;
   case 4 : return Ackermann4 (m) ; // break ;
   case 5 : return Ackermann5 (m) ; // break ;
   default : return 0 ; 
   }
}



/*************************************************/
/*                                               */
/*               Suite X                         */
/*                                               */
/*************************************************/


int ln2(int n){
	int cpt=0;
	while (n>1){cpt++;n=n/2;}
	return cpt;
}

double SuiteX1(int n){//version itérative
	double r=2;
	for (int i=0; i<n; i++){
		r= r+ln2(r);
	}
	return r;
}

double SuiteX2(int n){
	if (n==0) then return 2;
	return (SuiteX2(n-1)+ln2(SuiteX2(n-1)));
}

/*************************************************/
/*                                               */
/*               main                            */
/*                                               */
/*************************************************/


int main(int argc, char** argv)
{

       double x ;
       long double y ;
       float z ;
       
       int cptx ;
  
       long nx ;
       
       int i,j ; 
       
/******************************************************************************/
 
    // mettre "if true" vs "if false" selon que vous voulez les tests ou non
  
/****************  petit test sur le fonctionnement du switch  ************/

if (true) {

 switch (2)  
   {
   case 1 : printf("toc\n") ;  break ;
   case 2 : printf("pata") ; 
   case 3 : printf("pouf\n") ;  break ;
   case 4 : printf("youpla") ; 
   default : printf("boum\n") ; 
   }


 switch (4)  
   {
   case 1 : printf("toc\n") ;  break ;
   case 2 : printf("pata") ; 
   case 3 : printf("pouf\n") ;  break ;
   case 4 : printf("youpla") ; 
   default : printf("boum\n") ; 
   }

   printf("\n") ; 

}
      
      
/************************  taille des nombres  *************************/
      
if (true) {     
       
       printf("ce programme suppose que les long font 8 octets\n") ;
       printf("S'ils n'en font que 4, il faudra utiliser long long\n") ;

       printf("short : %d octets\n", (int) sizeof(short));
       printf("int : %d octets\n", (int) sizeof(int));
       printf("long : %d octets\n", (int) sizeof(long));
       printf("long long : %d octets\n", (int) sizeof(long long));
       printf("float : %d octets\n", (int) sizeof(float));
       printf("double : %d octets\n", (int) sizeof(double));
       printf("long double : %d octets\n", (int) sizeof(long double));
       printf("\n") ;
 
       x = 1.0 ;
       cptx = 0 ;
       while ( (1.0 + x) - 1.0 != 0 )
          { x = x/10 ; cptx ++ ; }
       printf("1+1/10^c devient 1 a partir de c=%d pour les double\n", cptx) ; 
       printf("et 1+1/10^%d vaut en fait 1+%lf\n", cptx-1, (1.0 + 10*x) - 1.0) ;
        
       printf("ce programme suppose que les doubles font au moins 8 octets\n") ;
       printf("et que ((double) 1+1/10^-15) n'est pas 1 \n") ;       
       printf("Si ce n'est pas le cas, utiliser des long double \n") ;
       
       printf("\n") ;      
}

/************************  factorielle  *************************/

if (false) {

     printf("%ld \n",fact(5)) ;
     printf("%ld \n",fact2(5)) ;
     printf("\n") ;
}


/******************    Autour de e      *******************************/

  // d'après google,
  // e = 2,7182818284 5904523536 0287471352 6624977572 4709369995 
  //       9574966967 6277240766 3035354759 4571382178 5251664274

if (false) {  
       

        printf(" e1 = %.20f \n", Efloat()) ;
        printf(" e3 = %.30lf \n", Edouble()) ; 
        printf(" e3 = %.40Lf \n", Elongdouble()) ; 
        
}

if (false) {  
            afficheYfloat(30) ;
            afficheYdouble(30) ;
            afficheYlongdouble(30) ;
}
        
/******************    power     *******************************/

if (false) {  

        //   test simplet, vérifie le B.A. BA, test de 2^10 pour toutes les versions
        
        for(i=0 ; i<=10 ; i++)
        printf("Power %d dit que power(2,10) vaut %.0lf \n", i, power(2,10,i) ) ;
        
        printf("\n") ;
      
}

        
if (false) {  

        i=9 ;  // numéro de la version que l'on teste

        // test de la résistance de power version i, calcul de (1+1/N)^N
        // pour des N puissances de 10 de plus en plus grosses
     
        x = 1.0 ;
        nx = 1 ;
        for(j=0 ; j<=15 ; j++)
        {
        printf("power %d ((1+10^-%2d)^(10^%2d)) rend %.12lf\n", i, j, j, power(1+x,nx,i)) ;
        x = x/10 ;
        nx = nx * 10 ;
        }
        printf("\n") ;
       
}
     
if (false) {

        // tests sur la précision que l'on obtient suivant que
        // l'on utilise float, double, long double

        printf("VERSION 12 avec float \n") ;
        z = 1.0 ;
        nx = 1 ;
        for(j=0 ; j<=18 ; j++)
        {
        printf("power((1+10^-%2d)^(10^%2d)) rend %.12f\n", j, j, power12(1+z,nx)) ;
        z = z/10 ;
        nx = nx * 10 ;
        }
        printf("\n") ;


        printf("VERSION 10 avec double \n") ;
        x = 1.0 ;
        nx = 1 ;
        for(j=0 ; j<=18 ; j++)
        {
        printf("power((1+10^-%2d)^(10^%2d)) rend %.12lf\n", j, j, power10(1+x,nx)) ;
        x = x/10 ;
        nx = nx * 10 ;
        }
        printf("\n") ;


        printf("VERSION 11 avec long double \n") ;
        y = 1.0 ;
        nx = 1 ;
        for(j=0 ; j<=18 ; j++)
        {
        printf("power((1+10^-%2d)^(10^%2d)) rend %.12LF\n", j, j, power11(1+y,nx)) ;
        y = y/10 ;
        nx = nx * 10 ;
        }
        printf("\n") ;



}

/******************    Ackermann    *******************************/
                
if (false) { 
 
        for(i=1 ; i<=5 ; i++)  // numéro de version
        
        for(j=0 ; j<=5 ; j++)  // test de A(j,0) pour j de 0 à 5
        
        printf("Ack%d(%d) = %d \n", i, j, Ackermann(j,i)) ;
}

/***********************************************************************/


/*********************   Test Suite X   ********************************/
if (true){
	for (int x=0; x<10; x++){
			if (true) { /*printf("test numero %d",x);*/
				printf ("X%d = %f \n",x,SuiteX1(x));
				}
			else {SuiteX1(x);}
		}
}
if (true){
	for (int x=0; x<10; x++){
			if (true) { /*printf("test numero %d",x);*/
				printf ("X%d = %f \n",x,SuiteX2(x));
				}
			else {SuiteX2(x);}
		}
}

printf("test X100 version 1 %d",SuiteX1(100));
printf("test X100 version 2 %d",SuiteX2(100));



/************************   Test Power   ***********************************/
/*
 if (false){
	for (int n=-5; n<6;n++){
		for (int x=-5; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power1(x,n));}
			else {Power1(x,n);}
		}
	}
}
if (false){
	for (int n=-5; n<6;n++){
		for (int x=-5; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power2a(x,n));}
			else {Power2a(x,n);}
		}
	}
}
if (false){
	for (int n=0; n<6;n++){
		for (int x=0; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power2b(x,n));}
			else {Power2b(x,n);}
		}
	}
}

if (false){
	for (int n=0; n<6;n++){
		for (int x=0; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power3(x,n));}
			else {Power3(x,n);}
		}
	}
}

if (false){
	for (int n=0; n<6;n++){
		for (int x=0; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power5(x,n));}
			else {Power5(x,n);}
		}
	}
}
if (false){
	for (int n=0; n<6;n++){
		for (int x=0; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power6(x,n));}
			else {Power6(x,n);}
		}
	}
}

if (false){
	for (int n=0; n<6;n++){
		for (int x=0; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power7(x,n));}
			else {Power7(x,n);}
		}
	}
}

if (false){
	for (int n=0; n<6;n++){
		for (int x=0; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power8(x,n));}
			else {Power8(x,n);}
		}
	}
}

if (false){
	for (int n=0; n<6;n++){
		for (int x=0; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power9(x,n));}
			else {Power9(x,n);}
		}
	}
}

if (false){
	for (int n=0; n<6;n++){
		for (int x=0; x<6; x++){
			if (true) { printf ("%d^%d = %f \n",x,n,Power10(x,n));}
			else {Power10(x,n);}
		}
	}
}
*/

/***********************************************************************/




/***********************************************************************/

    return 0;
}






