public class GaussMethod{
	static double[][] a = {	{1, -4, 4, 7	},
			{0, 2, -1, 0	},
			{2, 1, 1, 4	},
			{2, -3, 2, -5}
		              };
	static double[] d = {	4,
			5,
			2,
			9
		            };
	static String variables[] = {"x1", "x2", "x3", "x4"};
	static double[] solution = new double[variables.length];

	public static void displayEquations()
	{	System.out.println("\nThe equations are : ");
		for(int i = 0; i<a.length; i++)
		{
			for(int j=0; j<a[i].length; j++)
			{	
				System.out.print(String.format("%2.2f",a[i][j])+variables[j]+" " );
			}
			System.out.println(" = "+String.format("%2.2f",d[i]) );
		}
	}


	public static void main(String... s){
		displayEquations();
		for(int k =0; k< a.length-1; k++)
		{	
			double max = a[k][k];
			int ri=k, rj=k;
			for(int i=k; i< a.length; i++)
			{	for(int j=k; j<a[i].length; j++)
				{	if(Math.abs(a[i][j]) > Math.abs(max))
					{	max = a[i][j];
						ri = i;
						rj = j;
					}
				}
			}
	
			swapRowAndColumn(ri, rj, k);

			for(int i = k+1; i<a.length; i++)
			{	if(a[i][k]!=0)
				{	double coeff = a[i][k]/a[k][k];
					d[i] = d[i] - ( coeff *d[k]);
					for(int j=k; j<a[i].length; j++)
					{	a[i][j] = a[i][j] - coeff *a[k][j];
					}
				}
			}
		}
		System.out.println("Final Equations");
		displayEquations();										
		backSubstitution();
		System.out.println();
		for(int i=0; i<variables.length; i++)
		{	System.out.println(variables[i]+" = "+String.format("%2.2f",solution[i]) );
		}
	}

	public static void swapRowAndColumn(int ri, int rj, int k)
	{	double t=0;
		//swapping rows
		for(int j=0; j<a.length; j++)
		{	t = a[k][j];
			a[k][j] = a[ri][j];
			a[ri][j] = t;
		}
		t = d[k];
		d[k] = d[ri];
		d[ri] = t;
		
		//swapping columns
		for(int i=0; i< a.length; i++)
		{	t = a[i][k];
			a[i][k] = a[i][rj];
			a[i][rj] = t;
		}
		String temp = variables[k];
		variables[k] = variables[rj];
		variables[rj] = temp;
	}

	public static void backSubstitution()
	{
		for(int i=a.length-1; i>=0; i--)
		{	double diff= 0;
			for(int j=i+1; j<a.length; j++)
			{	diff += a[i][j]*solution[j];
			}
			solution[i] = (d[i]-diff)/a[i][i];
		}
	}
}
	