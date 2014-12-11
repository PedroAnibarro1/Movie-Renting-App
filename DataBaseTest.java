/********************************************
*       Author: Pedro Anibarro     			*
*   Movie Database and Renting Application  *
*          December 11, 2014        			*
*********************************************/


import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class DataBaseTest{


	public static void main(String[] args)throws FileNotFoundException{
		Scanner input = new Scanner(System.in);
		Scanner moviesFile = new Scanner(new File("movies.txt"));
		Scanner customersFile = new Scanner(new File("customers.txt"));



		DataManager dataBase = new DataManager(moviesFile, customersFile);

		dataBase.sortMovies();
		dataBase.sortCustomers();

		int dummy = 0;

		do{
			do{
				System.out.println("\t\tMenu\n" +
									"1. Display full database" +
									"\n2. Display movies" +
									"\n3. Display customers" +
									"\n4. Add a Movie" +
									"\n5. Delete a Movie" + 
									"\n6. Find a Movie by title" + 
									"\n7. Find Movie by actor" +
									"\n8. Find Movie by year" +
									"\n9. Find Movie by time period" +
									"\n10. Find a Customer" + 
									"\n11. Return Movie" + 
									"\n12. Rent Movie" + 
									"\n13. Add Customer" + 
									"\n14. Remove Customer" + 
									"\n0. Exit");

				dummy = input.nextInt();

			}while(dummy < 0);


			switch(dummy){

				case 1:
					System.out.println(dataBase);
					break;
				case 2:
					dataBase.printMovies();
					break;
				case 3:
					dataBase.printCustomers();
					break;
				case 4:
					dataBase.addMovie();
					dataBase.sortMovies();
					break;
				case 5:
					dataBase.removeMovie();
					break;
				case 6:
					dataBase.findMovie(0);
					break;
				case 7:
					ArrayList<Movie> moviesOfActor = dataBase.findMovie("");

					System.out.println();
					System.out.println();


					System.out.println(moviesOfActor.size() + " movies found with the desired actor.");

					System.out.println();
					System.out.println();

					for(int x = 0; x < moviesOfActor.size(); x++){

						System.out.println(moviesOfActor.get(x));

					}

					break;
				case 8:
					ArrayList<Movie> moviesOfYear = dataBase.findMovie(0.2);

					System.out.println();
					System.out.println();


					System.out.println(moviesOfYear.size() + " movies found with the desired year.");

					System.out.println();
					System.out.println();

					for(int x = 0; x < moviesOfYear.size(); x++){

						System.out.println(moviesOfYear.get(x));

					}

					break;
				case 9:
					ArrayList<Movie> moviesOfPeriod = dataBase.findMovie(false);

					System.out.println();
					System.out.println();


					System.out.println(moviesOfPeriod.size() + " movies found with the desired period of years.");

					System.out.println();
					System.out.println();

					for(int x = 0; x < moviesOfPeriod.size(); x++){

						System.out.println(moviesOfPeriod.get(x));

					}

					break;
				case 10:
					dataBase.findCustomer();
					break;
				case 11:
					dataBase.returnMovie();
					break;
				case 12:
					dataBase.rentMovie();
					break;
				case 13:
					dataBase.addCustomer();
					dataBase.sortCustomers();
					break;
				case 14:
					dataBase.removeCustomer();
					break;

			}
		}while(dummy != 0);



		dataBase.save();


	}


}