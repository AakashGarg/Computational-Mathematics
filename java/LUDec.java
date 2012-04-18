class LUDec{
int order = 3;

double[][] A = {	3, 6, -9,
			2, 5, -3,
			-4, 1, 10};
double[][] L = new double[order][order], U = new double[order][order];
double[] D = new double[order], X = new double[order], Z = new double[order];

void findX();
void findZ();
void initialize()
{	//init X
/*	for(int i=0; i<order; i++)
	{	X[i] = 'a'+ (char)i;
	}
*/
	//init L
	for(int i=0; i<order; i++)
	{	for(int j=i+1; j<order; j++)
		{	L[i][j] = 0;
		}
	}

	//init U
	for(i=0; i<order; i++)
	{	for(int j=0; j<=i; j++)
		{	if(i==j)	U[i][j] = 1;
			else		U[i][j] = 0;
		}
	}
}

void valuesOfL(int j)		// j = column no
{	for(int row =j; row<order; row++)
	{	float coeff = 0.0;
		for(int col =0; col<j; col++)
		{	coeff += L[row][col]*U[col][row];
		}
		L[row][j] = A[row][j] - coeff;
	}
}

void valuesOfU(int i)		// i = row no
{	for(int col=i+1; col<order; col++)
	{	float coeff = 0.0;
		for(int j=0; j<i; j++)
		{	coeff += L[i][j]*U[j][col];
		}
		U[i][col] = (A[i][col] - coeff)/L[i][i];
	}
}

void showMatrix(double[][] M)
{
	for(int i = 0; i<order; i++)
	{	for(int j=0; j<order; j++)
			printf("%2.2f ",M[i][j]);
		printf("\n");
	}
}

void main()
{
	int i = 0;
	clrscr();
	
	for(i=0; i<order; i++)
	{	valuesOfL(i);
		valuesOfU(i);
	}
	
	printf("\nMatrix L : \n");
	showMatrix(L);
	printf("\nMatrix U : \n");
	showMatrix(U);
	findX();
	printf("\nMatrix X : \n");
	for(i=0; i<order; i++)
	{	printf("\n%c = %1.2f",'a'+(char)i,X[i]);
	}
		
	getch();
}

void findZ()
{	for(int row=0; row<order; row++)
	{	float coeff = 0.0;
		for(int i=0; i<row; i++)
		{	coeff += L[row][i]*Z[i];
		}
		Z[row] = (D[row] - coeff)/L[row][row];
	}
}

void findX()
{	findZ();
	for(int row=order-1; row>=0; row--)
	{	double coeff = 0.0;
		for(int j=row+1; j<order; j++)
		{	coeff += U[row][j]*X[j];
		}
		X[row] = (Z[row] - coeff);
	}
}

public static void main(String... s){
	new LUDec().main();
}
}