import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;


public class DataBaseTest{

	public static void main(String[] args)throws FileNotFoundException{
		Scanner input = new Scanner(System.in);
		Scanner moviesFile = new Scanner(new File("movies.txt"));
		Scanner customersFile = new Scanner(new File("customers.txt"));


		DataManager dataBase = new DataManager(moviesFile, customersFile);


		System.out.println("\t\tMenu\n" +
							"1. Display database");

		int dummy = input.nextInt();

		System.out.println(dataBase);

		//System.out.println(dataBase.getCustomers().get(1));

		//System.out.println(dataBase.getMovies().get(1));


	}


}