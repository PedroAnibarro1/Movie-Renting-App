import java.util.ArrayList;


public class Customer{
	

	private Name customerName; //will containt the name of the customer
	private int[] customerId = new int[8]; //array of integers containing the customer ID
	private ArrayList<String> rentedMovies; //ArrayList of strings containing the movies rented by the customer


	/********************Constructors****************/

	//will be used when a new customer is added
	public Customer(Name customerName){

		this.customerName = customerName;
		setCustomerId();
		this.rentedMovies = new ArrayList<String>();

	}

	//will be used to store a customer from the database
	public Customer(Name customerName, int[] customerId, ArrayList<String> rentedMovies){

		this.customerName = customerName;
		this.customerId = customerId; //verificar si no conflije con la privacidad
		this.rentedMovies = rentedMovies;

	}





	/*****************Get Methods*****************/

	//returns customer name
	public String getCustomerName(){

		return this.customerName.toString();

	}


	//returns customer id
	public String getCustomerId(){

		String idCopy = "";

		for(int x = 0; x < this.customerId.length; x++){

			idCopy += this.customerId[x];

		}

		return idCopy;

	}


	//returns rented movies by the customer
	public String getRentedMovies(){

		String rentedMoviesStr = "";


		for(int x = 0; x < this.rentedMovies.size(); x++){

			rentedMoviesStr += (this.rentedMovies.get(x) + "\n"); //llama el ArrayList que estara compuesto de Movie y busca el titulo

		}

		return rentedMoviesStr;

	}




	/*****************Set Methods*****************/

	//set the customer name
	public void setCustomerName(Name customerName){

		this.customerName = new Name(customerName.getFirstName(), customerName.getLastName());

	}


	//set the customer id
	private void setCustomerId(){

		//need to add full functionality here

			for(int x = 0; x < 8; x++){

				this.customerId[x] = (int)(Math.random() * 10);

			}

		


	}


	//set rented movies by customer
	public void setRentedMovies(ArrayList<String> rentedMovies){

		for(int x = 0; x < rentedMovies.size(); x++){

			this.rentedMovies.add(rentedMovies.get(x));

		}

	}


	/*****************Other Methods*****************/


	//public void addMovie(Movie )


	//class to print the object
	public String toString(){

		String output = "Customer name: " + this.customerName.toString() + 
				"\nCustomer Id: " + this.getCustomerId() +
				"\nRented Movies: ";

		 for(int x = 0; x < this.rentedMovies.size(); x++){

		 	output += this.rentedMovies.get(x) + "\n\t\t";

		 }

		 return output;

	}



}