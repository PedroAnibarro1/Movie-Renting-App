import java.util.ArrayList;

public class Movie{
	
	private String title;
	private int releaseYear;
	private double duration;
	private int availableCopies;
	private ArrayList<Name> actors;


	public Movie(String title, int releaseYear, double duration, int availableCopies, ArrayList<Name> actors){

		this.title = title;
		this.releaseYear = releaseYear;
		this.duration = duration;
		this.actors = actors;

	}



	/*****************Get Methods*****************/

	public String getTitle(){

		return title;

	}

	public int getReleaseYear(){

		return releaseYear;

	}

	public double getDuration(){

		return duration;

	}

	public int getAvailableCopies(){

		return availableCopies;

	}

	public ArrayList<Name> getActors(){

		return actors;

	}



	/*****************Set Methods*****************/

	public void setTitle(String title){

		this.title = title;

	}

	public void setReleaseYear(int releaseYear){

		this.releaseYear = releaseYear;

	}

	public void setDuration(double duration){

		this.duration = duration;

	}

	public void setAvailableCopies(int availableCopies){

		this.availableCopies = availableCopies;

	}

	public void setActors(ArrayList<Name> actors){

		this.actors = actors;

	}



	/*****************other Methods*****************/

	public void increaseAvailableCopies(){

		this.availableCopies++;

	}

	public void decreaseAvailableCopies(){

		if(this.availableCopies >= 0){

			this.availableCopies--;

		}
		
	}


	public String toString(){

		String output = "Movie title: " + this.title + 
				"\nRelease Year: " + this.releaseYear +
				"\nDuration: " + this.duration + " minutes" + 
				"\nActors: ";

		 for(int x = 0; x < this.actors.size(); x++){

		 	output += this.actors.get(x).toString() + "\n\t"; 

		 }

		 return output;

	}

}