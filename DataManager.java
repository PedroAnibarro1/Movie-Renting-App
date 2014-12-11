/*************************************************
This class is used to manage all the data about 
the database. Movies and customers are stored here.
**************************************************/



import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;



public class DataManager{


	/********************Fields****************/
	
	private ArrayList<Movie> movies;;
	private ArrayList<Customer> customers;



	/********************Constructors****************/

	public DataManager(Scanner moviesFile, Scanner customersFile){

		Scanner eachLine;
		movies = new ArrayList<Movie>();
		customers = new ArrayList<Customer>();


		{

			String title;
			int releaseYear;
			double duration;
			int availableCopies;
			ArrayList<Name> actors;

			while(moviesFile.hasNextLine()){

				eachLine = new Scanner(moviesFile.nextLine());
				actors = new ArrayList<Name>();

				

				title = eachLine.next();
				releaseYear = eachLine.nextInt();
				duration = eachLine.nextDouble();
				availableCopies = eachLine.nextInt();


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




				movies.add(new Movie(title, releaseYear, duration, availableCopies, actors));


			}

		}




		/**************************************************/

		{

			Name customerName;
			int[] customerId;
			ArrayList<String> rentedMovies;
			String firstName = "";
			String lastName = "";


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


	public void printMovies(){

		String moviesOutput = "Movies:\n\n";


		for(int x = 0; x < this.movies.size(); x++){


			moviesOutput += movies.get(x).toString() + "\n\n\n\n";

		}

		System.out.println(moviesOutput);

	}


	public void printCustomers(){

		String customersOutput = "Customers:\n\n";


		for(int x = 0; x < this.customers.size(); x++){


			customersOutput += customers.get(x).toString() + "\n\n\n\n";

		}

		System.out.println(customersOutput);


	}



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



	public void sortCustomers(){

		ArrayList<String> customerNames = new ArrayList<String>();
		ArrayList<Customer> tempCustomers = new ArrayList<Customer>();

		for(int x = 0; x < movies.size(); x++){


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





}