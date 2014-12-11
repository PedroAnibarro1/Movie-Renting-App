/*************************************************
This class is used to create a new name object.
**************************************************/



public class Name{
	
	
	private String firstName;
	private String lastName;

	public Name(String firstName, String lastName){

		this.firstName = firstName;
		this.lastName = lastName;

	}
	



	/*****************Get Methods*****************/

	public String getFirstName(){

		return this.firstName;

	}


	public String getLastName(){

		return this.lastName;

	}



	/*****************Set Methods*****************/

	public void setFirstName(String firstName){

		this.firstName = firstName;

	}


	public void setLastName(String lastName){

		this.lastName = lastName;

	}



	/*****************Other Methods*****************/

	public String toString(){

		return (this.firstName + " " + this.lastName);

	}
	


}