package pkg;

public class User {
	
	private String name;
	private String score;
	
	public User(String name, String score)
	{
		this.name = name;
		this.score = score;
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getScore()
	{
		return score;
	}
	
	public void setScore (String score)
	{
		this.score = score;
	}
	
	public String toString()
	{
		return name + "   " + score;
	}
}
