//Gauss Elimination Method : Complete Pivoting

#include<stdio.h>
#include<conio.h>	
#include<math.h>

#define order 4
	double a[order][order] = {	1, -4, 4, 7,
								0, 2, -1, 0,
								2, 1, 1, 4 ,
								2, -3, 2, -5
							};

	double d[order] = {	4,
						5,
						2,
						9
					};
	char variables[order] = {'a','b', 'c', 'd'};
	double solution[order];

	void swapRowAndColumn(int, int, int);
	void backSubstitution();
	void displayEquations();

	void main()
	{
		clrscr();
		displayEquations();
		for(int k =0; k< order-1; k++)
		{
			double max = a[k][k];
			int ri=k, rj=k;
			for(int i=k; i< order; i++)
			{	for(int j=k; j<order; j++)
				{	if(fabs(a[i][j]) > fabs(max))
					{	max = a[i][j];
						ri = i;
						rj = j;
					}
				}
			}

			swapRowAndColumn(ri, rj, k);

			for(i = k+1; i<order; i++)
			{	if(a[i][k]!=0)
				{	double coeff = a[i][k]/a[k][k];
					d[i] = d[i] - ( coeff *d[k]);
					for(int j=k; j<order; j++)
					{	a[i][j] = a[i][j] - coeff *a[k][j];
					}
				}
			}
		}
		printf("\nFinal Equations\n");
		displayEquations();										
		backSubstitution();
		printf("\n");
		for(int i=0; i<order; i++)
		{	printf("\n %c = %2.2f",variables[i],solution[i] );
		}
		getch();
	}

	void swapRowAndColumn(int ri, int rj, int k)
	{	double t=0;
		//swapping rows
		for(int j=0; j<order; j++)
		{	t = a[k][j];
			a[k][j] = a[ri][j];
			a[ri][j] = t;
		}
		t = d[k];
		d[k] = d[ri];
		d[ri] = t;
		
		//swapping columns
		for(int i=0; i< order; i++)
		{	t = a[i][k];
			a[i][k] = a[i][rj];
			a[i][rj] = t;
		}
		char temp = variables[k];
		variables[k] = variables[rj];
		variables[rj] = temp;
	}

	void backSubstitution()
	{
		for(int i=order-1; i>=0; i--)
		{	double diff= 0;
			for(int j=i+1; j<order; j++)
			{	diff += a[i][j]*solution[j];
			}
			solution[i] = (d[i]-diff)/a[i][i];
		}
	}

	void displayEquations()
	{	printf("\n\nThe equations are : \n");
		for(int i = 0; i<order; i++)
		{
			for(int j=0; j<order; j++)
			{	
				printf("%2.2f%c ",a[i][j],variables[j] );
			}
			printf(" = %2.2f\n",d[i] );
		}
	}
