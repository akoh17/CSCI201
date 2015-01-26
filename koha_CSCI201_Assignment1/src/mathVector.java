public class mathVector extends mathObject
{
	
	public mathVector(String s, double x, double y, double z)
	{
		super(s, x, y, z);
		
	}
	
	//don't call the toString method on the object.  when System.out.println on the object
	//automatically invokes the toString method, so this overrides it
	public String toString()
	{ 
		String s= "Vector:<" +coordinates[0] + "," + coordinates[1]+ "," +coordinates[2] +">";
		return s;
	}
	public String getType()
	{
		return type;
	}
	
}