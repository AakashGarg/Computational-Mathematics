void main()
{	float a = -4f;
	float b = 14f;
	printf("Enter two nos : ");
	scanf("%f %f",&a,&b);
	if(a>b)	
	{	float t =a;
		a = b;
		b = t;
	}
	getRoot(a,b);
	
}

float f(float x)
{	float y =  x*(x-9);		// replace the current function by the required function
	return( y );		
}

private void getRoot(float x1, float x2)
{	float y1 = f(x1);
	float y2 = f(x2);
				
	if( y1*y2 > 0 )
	{	printf("No Roots");
		printf("f(%f) = %f",x1,y1);
		printf("f(%f) = %f",x2,y2);
	}
	else
	{	float mid = (x1+x2)/2;
		float yMid = f(mid);

		if(yMid < 0.00001 && yMid > -0.00001)	printf("Root = %f having y cordinate = %f",mid,yMid);
		else if (y1*yMid > 0 ) 
		{	x1 = mid;
			getRoot(x1,x2);
		}
		else if (y2*yMid > 0)
		{	x2 = mid;
			getRoot(x1,x2);
		}
	}
}						
			 
