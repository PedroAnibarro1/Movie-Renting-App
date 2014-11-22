import java.util.Scanner;
import java.util.ArrayList;



public class DataManager{
	
	private ArrayList<Movie> movies;;
	private ArrayList<Customer> customers;


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

		String moviesOutput = "";
		String customersOutput = "";


		for(int x = 0; x < this.movies.size(); x++){


			moviesOutput += movies.get(x).toString() + "\n\n\n\n";

		}


		for(int x = 0; x < this.customers.size(); x++){


			customersOutput += customers.get(x).toString() + "\n\n\n\n";

		}

		return moviesOutput + "\n\n\n" + customersOutput;

	}







}