/*************************************************
This class is used to manage all the data about 
the database. Movies and customers are stored here.
**************************************************/



import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;



public class DataManager{


	/********************Fields****************/
	
	private ArrayList<Movie> movies; //will contain all the movies of the database
	private ArrayList<Customer> customers; //will contain all the customers of the database



	/********************Constructors****************/

	public DataManager(Scanner moviesFile, Scanner customersFile){

		Scanner eachLine;
		movies = new ArrayList<Movie>();
		customers = new ArrayList<Customer>();


		/*This part of the constructor will read the movies*/

		{

			String title; //title of movie
			int releaseYear; //release year of movie
			double duration; //duration of movie
			int availableCopies; //available copies of movie
			ArrayList<Name> actors; //actor list of movie


			//from here it will read and create the database from the file

			while(moviesFile.hasNextLine()){ //while it has a new movie on file

				eachLine = new Scanner(moviesFile.nextLine());
				actors = new ArrayList<Name>();

				
				//read all the line knowing the order of what is reading

				title = eachLine.next();
				releaseYear = eachLine.nextInt();
				duration = eachLine.nextDouble();
				availableCopies = eachLine.nextInt();


				//this loop will read all the actors

				while(eachLine.hasNext()){

					String fullName = eachLine.next();
					String firstName = "";
					String lastName = "";

					{

						boolean flag = false;

						for(int x = 0; x < fullName.length(); x++){

							if(fullName.charAt(x) == '-'){

								flag = true;

							}else if(!flag){

								firstName += fullName.charAt(x);

							}else{

								lastName += fullName.charAt(x);

							}

						}

					}

					actors.add(new Name(firstName, lastName));

				}



				//add movie to database with all the information
				movies.add(new Movie(title, releaseYear, duration, availableCopies, actors));


			}

		}




		/*This part of the constructor will read the movies*/

		{

			Name customerName; //stores the customer name
			int[] customerId; //stores the id
			ArrayList<String> rentedMovies; //stores the rented movies
			String firstName = ""; //first name of customer
			String lastName = ""; //last name of customer


			//read all the lines of the customer file and stores all values 

			while(customersFile.hasNextLine()){

				eachLine = new Scanner(customersFile.nextLine());
				rentedMovies = new ArrayList<String>();
				firstName = "";
				lastName = "";
				customerId = new int[8];

				{

					String fullName = eachLine.next();
					boolean flag = false;

					for(int x = 0; x < fullName.length(); x++){

						if(fullName.charAt(x) == '-'){

							flag = true;

						}else if(!flag){

							firstName += fullName.charAt(x);

						}else{

							lastName += fullName.charAt(x);

						}

					}

				}



				{

					String fullId = eachLine.next();

					for(int x = 0; x < fullId.length(); x++){

						customerId[x] = Character.getNumericValue(fullId.charAt(x));

					}

				}



				while(eachLine.hasNext()){

					String movieTitle = eachLine.next();
					String movieTitleWithout = "";

					for(int x = 0; x < movieTitle.length(); x++){

						if(movieTitle.charAt(x) != '-'){

							movieTitleWithout += movieTitle.charAt(x);

						}else{

							movieTitleWithout += " ";

						}

					}

					rentedMovies.add(movieTitleWithout); 

				}


				customers.add(new Customer(new Name(firstName, lastName), customerId, rentedMovies));


			}

		}


	}



	/*****************Get Methods*****************/

	public ArrayList<Movie> getMovies(){

		return	this.movies;

	}


	public ArrayList<Customer> getCustomers(){

		return	this.customers;

	}


	/*****************Other Methods*****************/

	//print all the database
	public String toString(){

		String moviesOutput = "Movies:\n\n";
		String customersOutput = "Customers:\n\n";


		for(int x = 0; x < this.movies.size(); x++){


			moviesOutput += movies.get(x).toString() + "\n\n\n\n";

		}


		for(int x = 0; x < this.customers.size(); x++){


			customersOutput += customers.get(x).toString() + "\n\n\n\n";

		}

		return moviesOutput + "\n\n\n" + customersOutput;

	}


	//print only the movies on database
	public void printMovies(){

		String moviesOutput = "Movies:\n\n";


		for(int x = 0; x < this.movies.size(); x++){


			moviesOutput += movies.get(x).toString() + "\n\n\n\n";

		}

		System.out.println(moviesOutput);

	}


	//print only the customers on database
	public void printCustomers(){

		String customersOutput = "Customers:\n\n";


		for(int x = 0; x < this.customers.size(); x++){


			customersOutput += customers.get(x).toString() + "\n\n\n\n";

		}

		System.out.println(customersOutput);


	}


	// add a movie
	public void addMovie(){

		//scanner object for the inputs
		Scanner input = new Scanner(System.in);


		String title = ""; //stores the title of the movie
		int releaseYear = 0; //stores the release year of the movie
		double duration = 0; //stores the duration in minutes of the movie
		int availableCopies = 0; //stores the available copies of the movie
		int numberOfActors = 0; //stores hoy many actors have the movie

		// Title of the movie
		do{ 
			System.out.println("Enter the title of the movie:"); 
			title = input.nextLine();
		}while(title == "");
		

		// Release Year
		do{
			System.out.println("Enter the release year:");
			releaseYear = input.nextInt();
		}while(releaseYear < 1 || releaseYear > 2014);
		

		// Duration 
		do{
			System.out.println("Enter the duration in minutes:");
			duration = input.nextDouble();
		}while(duration < 1);


		// Copies Available
		do{
			System.out.println("Enter the copies available:");
			availableCopies = input.nextInt();
		}while(availableCopies < 0);


		// Number of Actors
		do{
			System.out.println("How many actors the movie has?");
			numberOfActors = input.nextInt();
		}while(numberOfActors < 1);

		
		input.nextLine(); // input to get a next line that was causing problems


		ArrayList<Name> actors = new ArrayList<Name>(); //Array List to store all the actors of the movie
		String firstName = ""; //stores the first name of the actor
		String lastName = ""; //stores the last name of the actor

		//For Loop to iterate and store all the actors with first and last name
		for(int x = 0; x < numberOfActors; x++){

			System.out.println("Enter first name of actor " + (x + 1));
			firstName = input.nextLine();


			System.out.println("Enter last name of actor " + (x + 1));
			lastName = input.nextLine();


			actors.add(new Name(firstName, lastName)); //creates and add a Name object to the actors arraylist


		}
		
		//creates a object of Movie type and stores it on the Array List of the database
		movies.add(new Movie(title, releaseYear, duration, availableCopies, actors));

		System.out.println("Movie has been added.");



	}


	//remove movie
	public void removeMovie(){


		if(!movies.isEmpty()){

			//scanner object for the inputs
			Scanner input = new Scanner(System.in);

			String title;

			//asks for the title of the movie to be deleted
			System.out.println("Enter the title of the movie to delete:");
			title = input.nextLine();



			//moves through the ArrayList of movies searching for the movie
			for(int x = 0; x < movies.size(); x++){

				//if it exists in the database, delete it
				if(movies.get(x).getTitle().toUpperCase().trim().contentEquals(title.toUpperCase().trim())){

					movies.remove(x);
					System.out.println("Movie has been deleted.");
					return;
					

				}

			}

			//Movie not found
			System.out.println("Movie not found.");

		}else{

			//Database is empty
			System.out.println("There are no movies on database.");
			return;

		}


		return;

		
		
	}


	// add a customer
	public void addCustomer(){

		//scanner object for the inputs
		Scanner input = new Scanner(System.in);


		String firstName; //stores the user first name
		String lastName; //stores the user last name


		System.out.println("Enter user first name:");
		firstName = input.nextLine();

		System.out.println("Enter user last name:");
		lastName = input.nextLine();

		customers.add(new Customer(new Name(firstName, lastName)));


		boolean exists;


		do{

			exists = false;

			for(int x = 0; x < (customers.size() - 1); x++){

				if(customers.get((customers.size() - 1)).getCustomerId().contentEquals(customers.get(x).getCustomerId())){

					exists = true;
					customers.get((customers.size() - 1)).setCustomerId();


				}

			}

		}while(exists);

		
		System.out.println();
		System.out.println();
		System.out.println("Customer has been added.");
		System.out.println();
		System.out.println();

	}


	//remove a customer
	public void removeCustomer(){


		if(!customers.isEmpty()){

			//scanner object for the inputs
			Scanner input = new Scanner(System.in);

			String user;

			//asks for the title of the movie to be deleted
			System.out.println("Enter the full name of the user to be deleted:");
			user = input.nextLine();
			System.out.println();



			//moves through the ArrayList of movies searching for the movie
			for(int x = 0; x < customers.size(); x++){

				//if it exists in the database, delete it
				if(customers.get(x).getCustomerName().toUpperCase().trim().contentEquals(user.toUpperCase().trim())){

					customers.remove(x);

					System.out.println();
					System.out.println();
					System.out.println("User has been deleted.");
					System.out.println();
					System.out.println();
					return;
					

				}

			}

			//Movie not found
			System.out.println("User not found.");
			System.out.println();

		}else{

			//Database is empty
			System.out.println("There are no users on database.");
			System.out.println();
			return;

		}


		return;

		
		
	}





	//sort all the database of movies
	public void sortMovies(){

		ArrayList<String> movieTitles = new ArrayList<String>();
		ArrayList<Movie> tempMovies = new ArrayList<Movie>();

		for(int x = 0; x < movies.size(); x++){


			movieTitles.add(movies.get(x).getTitle());


		}

		Collections.sort(movieTitles);


		for(int x = 0; x < movieTitles.size(); x++){

			for(int y = 0; y < movies.size(); y++){

				if(movies.get(y).getTitle().contentEquals(movieTitles.get(x))){

					tempMovies.add(movies.get(y));

				}

			}

		}

		movies = tempMovies;
		
	}



	//sort all the customers on the database
	public void sortCustomers(){

		ArrayList<String> customerNames = new ArrayList<String>();
		ArrayList<Customer> tempCustomers = new ArrayList<Customer>();

		for(int x = 0; x < customers.size(); x++){


			customerNames.add(customers.get(x).getCustomerName());


		}

		Collections.sort(customerNames);


		for(int x = 0; x < customerNames.size(); x++){

			for(int y = 0; y < customers.size(); y++){

				if(customers.get(y).getCustomerName().contentEquals(customerNames.get(x))){

					tempCustomers.add(customers.get(y));

				}

			}

		}

		customers = tempCustomers;

	}



	public void findCustomer(){


		if(!customers.isEmpty()){

			//scanner object for the inputs
			Scanner input = new Scanner(System.in);

			//asks for the name of the customer
			System.out.println("What customer are you searching for?");
			String name = input.nextLine();



			//moves through the ArrayList of customers searching for the customer
			for(int x = 0; x < customers.size(); x++){

				//if it exists in the database, display it
				if(customers.get(x).getCustomerName().toUpperCase().trim().contentEquals(name.toUpperCase().trim())){

					System.out.println(customers.get(x));
					return;
					

				}

			}

			//Customer not found
			System.out.println("Customer not found.");

		}else{

			//Database is empty
			System.out.println("There are no customers on database.");
			return;

		}


	}



	public void findMovie(int z){


		if(!movies.isEmpty()){

			//scanner object for the inputs
			Scanner input = new Scanner(System.in);

			//asks for the title of the movie
			System.out.println("What movie are you searching for?");
			String title = input.nextLine();



			//moves through the ArrayList of moovies searching for the movie
			for(int x = 0; x < movies.size(); x++){

				//if it exists in the database, display it
				if(movies.get(x).getTitle().toUpperCase().trim().contentEquals(title.toUpperCase().trim())){

					System.out.println(movies.get(x));
					return;
					

				}

			}

			//Movie not found
			System.out.println("Movie not found.");

		}else{

			//Database is empty
			System.out.println("There are no movies on database.");
			return;

		}


	}


	public ArrayList<Movie> findMovie(String z){


		if(!movies.isEmpty()){

			//scanner object for the inputs
			Scanner input = new Scanner(System.in);

			ArrayList<Movie> tempMovies = new ArrayList<Movie>(0);

			//asks for the name of the actor
			System.out.println("Of which actor (full name) you want to find movies?");
			String actor = input.nextLine();


			for(int movie = 0; movie < movies.size(); movie++){

				//moves through the ArrayList of movies searching for the actor
				for(int actors = 0; actors < movies.get(movie).getActors().size(); actors++){

					//if it actors exists in the database, display it
					if(movies.get(movie).getActors().get(actors).toString().toUpperCase().trim().contentEquals(actor.toUpperCase().trim())){

						tempMovies.add(movies.get(movie));
						

					}

				}


			}


			return tempMovies;


		}else{

			//Database is empty
			System.out.println("There are no movies on database.");
			return new ArrayList<Movie>(0);

		}



	}



	public ArrayList<Movie> findMovie(double z){


		if(!movies.isEmpty()){

			//scanner object for the inputs
			Scanner input = new Scanner(System.in);

			ArrayList<Movie> tempMovies = new ArrayList<Movie>(0);

			//asks for year
			System.out.println("Of which year you want to find movies?");
			int year = input.nextInt();


			//moves through the ArrayList of moovies searching for the movie
			for(int movie = 0; movie < movies.size(); movie++){

				//if it exists in the database, display it
				if(movies.get(movie).getReleaseYear() == year){

					tempMovies.add(movies.get(movie));
					

				}

			}


			return tempMovies;


		}else{

			//Database is empty
			System.out.println("There are no movies on database.");
			return new ArrayList<Movie>(0);

		}



	}



	public ArrayList<Movie> findMovie(boolean z){


		if(!movies.isEmpty()){

			//scanner object for the inputs
			Scanner input = new Scanner(System.in);

			ArrayList<Movie> tempMovies = new ArrayList<Movie>(0);
			int yearMin = 0;
			int yearMax = 0;

			//asks for the period of time 
			do{
				System.out.println("Enter the first year between the period of time:");
				yearMin = input.nextInt();
			}while(yearMin < 1);

			do{
				System.out.println("Enter the second year between the period of time:");
				yearMax = input.nextInt();
			}while(yearMax <= yearMin);


			//moves through the ArrayList of movies searching for the movie
			for(int movie = 0; movie < movies.size(); movie++){

				//if it exists in the database, display it
				if(movies.get(movie).getReleaseYear() >= yearMin && movies.get(movie).getReleaseYear() <= yearMax){

					tempMovies.add(movies.get(movie));
					

				}

			}


			return tempMovies;


		}else{

			//Database is empty
			System.out.println("There are no movies on database.");
			return new ArrayList<Movie>(0);

		}



	}



	public void returnMovie(){


		if(!customers.isEmpty()){

			//scanner object for the inputs
			Scanner input = new Scanner(System.in);


			boolean customerExists = false;
			boolean movieExists = false;

			int customerIndex = -1;
			int movieIndex = -1;


			//asks for the user name
			System.out.println("User name: (full name)");
			String user = input.nextLine();
			System.out.println();
			System.out.println();

			for(int x = 0; x < customers.size(); x++){

				if(customers.get(x).getCustomerName().toUpperCase().trim().contentEquals(user.toUpperCase().trim())){

					customerExists = true;
					customerIndex = x;
					break;

				}

			}

			if(customerExists){


				//asks for the movie name
				System.out.println("Movie title: (full name)");
				String title = input.nextLine();
				System.out.println();
				System.out.println();

				for(int x = 0; x < movies.size(); x++){

					if(movies.get(x).getTitle().toUpperCase().trim().contentEquals(title.toUpperCase().trim())){

						movieExists = true;
						movieIndex = x;

					}


				}


				if(movieExists){


					for(int x = 0; x < customers.get(customerIndex).getRentedMovies().size(); x++){

						if(customers.get(customerIndex).getRentedMovies().get(x).toUpperCase().trim().contentEquals(title.toUpperCase().trim())){


							customers.get(customerIndex).getRentedMovies().remove(x);
							movies.get(movieIndex).increaseAvailableCopies();


							System.out.println("Movie has been delivered.");
							System.out.println();
							System.out.println();


							return;

						}

					}


					System.out.println("User don't have that movie rented.");
					System.out.println();
					System.out.println();



				}else{

					System.out.println("Movie dosen't exist.");
					System.out.println();
					System.out.println();

				}



			}else{


				System.out.println("User dosen't exist.");
				System.out.println();
				System.out.println();


			}


			return;


		}


	}



	public void rentMovie(){


		if(!customers.isEmpty() || !movies.isEmpty()){

			//scanner object for the inputs
			Scanner input = new Scanner(System.in);


			boolean customerExists = false;
			boolean movieExists = false;

			int customerIndex = -1;
			int movieIndex = -1;


			//asks for the user name
			System.out.println("User name: (full name)");
			String user = input.nextLine();
			System.out.println();
			System.out.println();

			for(int x = 0; x < customers.size(); x++){

				if(customers.get(x).getCustomerName().toUpperCase().trim().contentEquals(user.toUpperCase().trim())){

					customerExists = true;
					customerIndex = x;
					break;

				}

			}

			if(customerExists){


				//asks for the movie name
				System.out.println("Movie title: (full name)");
				String title = input.nextLine();
				System.out.println();
				System.out.println();

				for(int x = 0; x < movies.size(); x++){

					if(movies.get(x).getTitle().toUpperCase().trim().contentEquals(title.toUpperCase().trim())){

						movieExists = true;
						movieIndex = x;

					}


				}


				if(movieExists){


					if(movies.get(movieIndex).getAvailableCopies() > 0){

						customers.get(customerIndex).getRentedMovies().add(movies.get(movieIndex).getTitle());
						movies.get(movieIndex).decreaseAvailableCopies();

						System.out.println("Movie has been rented.");
						System.out.println();
						System.out.println();


					}else{

						System.out.println("No enought copies available.");
						System.out.println();
						System.out.println();
						return;

					}



				}else{

					System.out.println("Movie doesn't exist.");
					System.out.println();
					System.out.println();

				}



			}else{


				System.out.println("User or movie doesn't exist.");
				System.out.println();
				System.out.println();


			}


			return;


		}


	}


	//this method is used before closing the program to save all the changes
	public void save()throws FileNotFoundException{

		//creates object to write on file
		PrintStream output = new PrintStream(new File("customers.txt"));
		String titleWith = "";
		String titleWithout = "";


		for(int x = 0; x < customers.size(); x++){


			output.print(customers.get(x).getCustomerNameO().getFirstName() + "-" + customers.get(x).getCustomerNameO().getLastName() + "\t");
			output.print(customers.get(x).getCustomerId() + "\t");


			for(int y = 0; y < customers.get(x).getRentedMovies().size(); y++){


				titleWithout = customers.get(x).getRentedMovies().get(y);
				titleWith = "";

				for(int z = 0; z < titleWithout.length(); z++){

					if(titleWithout.charAt(z) != ' '){

						titleWith += titleWithout.charAt(z);

					}else{

						titleWith += "-";

					}

				}


				output.print(titleWith + "   ");

			}

			output.println();

		}

		output.close();




		output = new PrintStream(new File("movies.txt"));


		for(int x = 0; x < movies.size(); x++){


			output.print(movies.get(x).getTitle() + "\t");
			output.print(movies.get(x).getReleaseYear() + "\t");
			output.print(movies.get(x).getDuration() + "\t");
			output.print(movies.get(x).getAvailableCopies() + "\t");


			for(int y = 0; y < movies.get(x).getActors().size(); y++){


				output.print(movies.get(x).getActors().get(y).getFirstName() + "-" + movies.get(x).getActors().get(y).getLastName() + "   ");

			}

			output.println();

		}

		output.close();


	}


}