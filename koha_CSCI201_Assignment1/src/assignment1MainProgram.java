import java.util.Scanner;
import java.lang.Math;

public class assignment1MainProgram
{
	public static void main(String [] args)
	{
		mathObject m1=null;
		mathObject m2=null;
		int input=0;
		System.out.println("Object 1 has not been assigned yet");
		System.out.println("Object 2 has not been assigned yet");
		
		Scanner sc= new Scanner(System.in);
		
		do
		{
			String inputString="";
			System.out.println("1: Change value of "+m1);
			System.out.println("2: Change value of "+m2);
			System.out.println("3: Add the objects");
			System.out.println("4: Subtract the objects");
			System.out.println("5: Find the angle betewen the objects");
			System.out.println("6: Quit");
			
			inputString= sc.nextLine();		
			try
			{
				input= Integer.parseInt(inputString);
				if(input <=0 || input >=7)
				{
					System.out.println("Please enter an int from 1-6 \n");
					continue;
				}
				
				if(input ==1)
				{
					 m1=changeValueOfObject(m1, sc);
				}
				else if(input==2)
				{
					m2=changeValueOfObject(m2, sc);
				}
				else if(input ==3 || input ==4 ||input ==5)
				{
					if(m1==null || m2==null)
					{
						System.out.println("Sorry, one or more objects have not been set");
						continue;
					}
					if(input ==3)
					{
						addObjects(m1, m2);
					}
					else if(input ==4)
					{
						subtractObjects(m1,  m2);
					}
					else
					{
						findAngle(m1, m2);
					}
				}
			}
			catch(NumberFormatException ex)
			{
				//goes back to the top of the do-while loop
				System.out.println("Please enter an int from 1-6 \n");
				continue;
			}
		}while(input !=6);
	}
	
	public static mathObject changeValueOfObject(mathObject m, Scanner sc)
	{
		double xValue;
		double yValue;
		double zValue;
		while(true)
		{
			xValue=0;
			yValue=0;
			zValue=0;
			System.out.println("Enter values in the following format: <x,y,z>");
			String data= sc.nextLine();
			
			//making sure user inputs something and doesn't just hit the return key
			//or that the user input is not just <>
			if(data.length()==0 || data.length()==2)
			{
				continue;
			}
			
			if(data.charAt(0) != '<' || data.charAt(data.length()-1) != '>')
			{
				System.out.println("Please enter values correctly ");
				continue;
			}
			
			int indexOfFirstComma=0;
			for(int ii=0; ii<data.length(); ii++)
			{
				if (data.charAt(ii)==',')
				{
					indexOfFirstComma=ii;
					break;
				}
	
			}
			//no comma found, so indexOfFirstCOmma will still be 0
			//either this is an error or the user just inputed an X coordinate
			//so xString will be everything within the brackets  
			//therefore just go to the '>' character
			String xString;
			if(indexOfFirstComma==0)
			{
				//data.length()-1 represents the '>' character. since the end of
				//the substring method is exclusive, want to get the string up to
				//but not including that end bracket
				xString=data.substring(1,data.length()-1);

			}
			else
			{
				 xString= data.substring(1, indexOfFirstComma);
			}
			
			try
			{
				xValue= Double.parseDouble(xString);
			}
			catch(NumberFormatException ex)
			{
				System.out.println("X coordinate invalid");
				continue;
			}
			
			//only x coordinate so no more parsing is needed
			if(indexOfFirstComma==0)
			{
				break;
			}
			
			//there was a comma, so there is at least a y coordinate
			int indexOfSecondComma=0;
			
			//need to start 1 index past first comma
			for(int jj=indexOfFirstComma+1; jj<data.length(); jj++ )
			{	
				if(data.charAt(jj)==',')
				{
					indexOfSecondComma=jj;
					break;
				}
			}

			String yString;
			//no z value so there is no second comma.  therefore just go to the '>' character
			if(indexOfSecondComma ==0)
			{
				yString= data.substring(indexOfFirstComma+1, data.length()-1);	
			}
			else
			{
				yString=data.substring(indexOfFirstComma+1, indexOfSecondComma);
			}

			try
			{
				yValue= Double.parseDouble(yString);			
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Y coordinate invalid");
				continue;
			}
			
			//no second comma so just have x and y coordinates, with no z input
			if(indexOfSecondComma ==0)
			{
				break;
			}
			
			//if the user inputs a z value, the zvalue will range from the second comma's
			//index +1 to the '>' character
			String zString = data.substring(indexOfSecondComma+1, data.length()-1);
			
			try
			{
				zValue= Double.parseDouble(zString);
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Z Coordinate Invalid");
				continue;
			}
			
			//at this point, you got all of the data for the x,y,and z coordinates
			//if the user didn't enter y or z coordinates, then there values are set to 0
			//at the top of the while loop.  now that the input is validated break out of the while loop
			break;	
		}

		while(true)
		{
			System.out.println("Is this a Vector or a Point");
			String typeOfMathObject= sc.nextLine();
			if(typeOfMathObject.equalsIgnoreCase("Point") || typeOfMathObject.equalsIgnoreCase("Vector"))
			{
				if(typeOfMathObject.equalsIgnoreCase("Point"))
				{
					m= new Point("POINT", xValue, yValue, zValue);
				}
				else
				{
					m= new mathVector("VECTOR", xValue, yValue, zValue);
				}
				return m;
				
			}
			//otherwise the loop repeats to make sure the user inputs either point or vector
		}
	}
	public static void addObjects(mathObject m1, mathObject m2)
	{	
		double[] m1CoordinateArray=m1.getCoordinates();
		double x1= m1CoordinateArray[0];
		double y1= m1CoordinateArray[1];
		double z1= m1CoordinateArray[2];
		
		double[] m2CoordinateArray=m2.getCoordinates();
		double x2= m2CoordinateArray[0];
		double y2= m2CoordinateArray[1];
		double z2= m2CoordinateArray[2];
		
		if(m1.getType()=="VECTOR" && m2.getType()=="VECTOR")
		{
			mathVector result=new mathVector("VECTOR", x1+x2, y1+y2, z1+z2);
			System.out.println("The Result is "+ result +"\n");
		}
		else if(m1.getType() =="POINT" && m2.getType()== "POINT")
		{
			System.out.println("Cannot add a Point to a Point\n");
		}
		else if(m1.getType()=="POINT" && m2.getType()=="VECTOR")
		{
			Point result= new Point("POINT", x1+x2, y1+y2, z1+z2);
			System.out.println("The Result is "+ result +"\n");
			
		}
		else //(m1.getType()=="VECTOR" && m2.getType()=="Point")
		{
			System.out.println("Cannot add a point to a vector\n");
		}
		
	}
	public static void subtractObjects(mathObject m1, mathObject m2)
	{
		double[] m1CoordinateArray=m1.getCoordinates();
		double x1= m1CoordinateArray[0];
		double y1= m1CoordinateArray[1];
		double z1= m1CoordinateArray[2];
		
		double[] m2CoordinateArray=m2.getCoordinates();
		double x2= m2CoordinateArray[0];
		double y2= m2CoordinateArray[1];
		double z2= m2CoordinateArray[2];
		
		if(m1.getType()=="VECTOR" && m2.getType()=="VECTOR")
		{
			mathVector result=new mathVector("VECTOR", x1-x2, y1-y2, z1-z2);
			System.out.println("The Result is "+ result +"\n");
		}
		else if(m1.getType() =="POINT" && m2.getType()== "POINT")
		{
			mathVector result=new mathVector("VECTOR", x1-x2, y1-y2, z1-z2);
			System.out.println("The Result is "+ result +"\n");
		}
		else if(m1.getType()=="POINT" && m2.getType()=="VECTOR")
		{
			Point result=new Point("POINT", x1-x2, y1-y2, z1-z2);
			System.out.println("The Result is "+ result +"\n");
		}
		else //(m1.getType()=="VECTOR" && m2.getType()=="POINT")
		{
			System.out.println("Cannot subtract a point from a vector \n" );
		}
	}
	
	public static void findAngle(mathObject m1, mathObject m2)
	{
		double[] m1CoordinateArray=m1.getCoordinates();
		double x1= m1CoordinateArray[0];
		double y1= m1CoordinateArray[1];
		double z1= m1CoordinateArray[2];
		
		double[] m2CoordinateArray=m2.getCoordinates();
		double x2= m2CoordinateArray[0];
		double y2= m2CoordinateArray[1];
		double z2= m2CoordinateArray[2];
		
		if(m1.getType()=="VECTOR" && m2.getType()=="VECTOR")
		{
			double length1= Math.sqrt((Math.pow(x1, 2) + Math.pow(y1, 2) + Math.pow(z1, 2)));
			double length2= Math.sqrt((Math.pow(x2, 2) + Math.pow(y2, 2) + Math.pow(z2, 2)));
			
			if(length1==0 || length2==0)
			{
				System.out.println("Cannot find angle because one or more vector has a magnitude of 0 \n");
			}
			else
			{
				double radian= Math.acos((x1*x2 +y1*y2 +z1*z2)/ (length1 * length2));
				System.out.println("The angle between them is " +Math.toDegrees(radian) +"\n");
			}
		}
		else if(m1.getType() =="POINT" && m2.getType()== "POINT")
		{
			System.out.println("Cannot get an angle between a Point and a Point \n");
		}
		else if(m1.getType()=="POINT" && m2.getType()=="VECTOR")
		{
			System.out.println("Cannot get an angle between a Point and a Vector\n");
		}
		else //(m1.getType()=="VECTOR" && m2.getType()=="Point")
		{
			System.out.println("Cannot get an angle between a Vector and a Point\n");
		}
	}
	
}