abstract public class mathObject
{
	
	protected String type;
	protected double[] coordinates;
	
	public mathObject(String s, double x, double y, double z)
	{
		type=s;
		coordinates= new double[3];
		coordinates[0]=x;
		coordinates[1]=y;
		coordinates[2]=z;

	}
	
	//make this abstract so both point and vector have to implement it.  
	//but leave this abstract because can't call toString method on this 
	//abstract class anyways. 
	public abstract String toString();
	public abstract String getType();
	
	//because Point and mathVector are mathObjects, and getCoordinates returns
	//the same coordinate array regardless of the type of object (unlike getType() and toString())
	//which are unique to the point and mathVector class, you don't need to make
	//get Coordinates an abstract class here and implement it in both the Point
	//and mathVector class.  Instead, you can implement it here, so when either
	//a point or vector object call it, because they are math objects they should
	//get the coordinates array that was set when the point or vector objects were created
	public double[] getCoordinates()
	{
		return coordinates;
	}
}