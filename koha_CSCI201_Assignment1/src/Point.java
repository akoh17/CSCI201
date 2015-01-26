public class Point extends mathObject
{
	public Point(String s, double x, double y, double z)
	{
		super(s, x, y, z);
	
	}
	public String toString()
	{
		String s= "Point:<" +coordinates[0] + "," + coordinates[1]+ "," +coordinates[2] +">";
		return s;
	}
	public String getType()
	{
		//type is a protected variable from the mathObject class that gets set
		//to either POINT or VECTOR.  Since it is a protected variable and Point
		//and mathVector inherit from mathObject, can return the variable type
		//to get whether the mathObject is a point or vector
		return type;
	}

}