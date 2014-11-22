import java.util.ArrayList;


public class Customer{
	

	private Name customerName;
	private int[] customerId = new int[8];
	private ArrayList<String> rentedMovies;


	public Customer(Name customerName){

		this.customerName = customerName;
		setCustomerId();
		this.rentedMovies = new ArrayList<String>();

	}

	public Customer(Name customerName, int[] customerId, ArrayList<String> rentedMovies){

		this.customerName = customerName;
		this.customerId = customerId; //verificar si no conflije con la privacidad
		this.rentedMovies = rentedMovies;

	}





	/*****************Get Methods*****************/

	public String getCustomerName(){

		return this.customerName.toString();

	}

	public String getCustomerId(){

		String idCopy = "";

		for(int x = 0; x < this.customerId.length; x++){

			idCopy += this.customerId[x];

		}

		return idCopy;

	}

	public String getRentedMovies(){

		String rentedMoviesStr = "";


		for(int x = 0; x < this.rentedMovies.size(); x++){

			rentedMoviesStr += (this.rentedMovies.get(x) + "\n"); //llama el ArrayList que estara compuesto de Movie y busca el titulo

		}

		return rentedMoviesStr;

	}



	/*****************Set Methods*****************/

	public void setCustomerName(Name customerName){

		this.customerName = new Name(customerName.getFirstName(), customerName.getLastName());

	}


	private void setCustomerId(){

		//do{

			for(int x = 0; x < 8; x++){

				this.customerId[x] = (int)(Math.random() * 10);

			}

		//}while();



	}

	public void setRentedMovies(ArrayList<String> rentedMovies){

		for(int x = 0; x < rentedMovies.size(); x++){

			this.rentedMovies.add(rentedMovies.get(x));

		}

	}


	
	/*****************Other Methods*****************/

	//public void addMovie(Movie )


	public String toString(){

		String output = "Customer name: " + this.customerName.toString() + 
				"\nCustomer Id: " + this.getCustomerId() +
				"\nRented Movies: ";

		 for(int x = 0; x < this.rentedMovies.size(); x++){

		 	output += this.rentedMovies.get(x) + "\n\t\t"; //verificar lo de llamar el objeto

		 }

		 return output;

	}



}