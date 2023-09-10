/*
 * 	JAVA Assignment #2
 * 	Name: Kush Patel
 * 	Student Number: 501168852
 */

//Imported Java utiliy
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Simulation of audio content in an online store
// The songs, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
	
	private ArrayList<AudioContent> contents; //An arraylist holding the information that was downlaoded
	private Map<String, ArrayList<Integer>> artists; //A map holding the artists for each song or audiobook (Artists could be the same)
	private Map<String, Integer> titles; // A map holding the title of each song or audiobook
	private Map<String, ArrayList<Integer>> genres; // A map holding the genre of every song (Songs can have the same genre)

	// A incrementing system to get the song or audiobook number
	private int artistCount = 1;
	private int titleCount = 1;
	private int genreCount = 1;
	
	//Constructor that initializes arraylists and maps. Also calls downloadsongs() to download all the content from the store.txt file
	public AudioContentStore()
	{
		contents = new ArrayList<AudioContent>();
		artists = new HashMap<String, ArrayList<Integer>>();
		titles = new HashMap<String, Integer>();
		genres = new HashMap<String, ArrayList<Integer>>();

		//Method that stores song and audiobook infromation from the store.txt file
		downloadSongs();
	}
	
	//A method that return all the audiocontent that must be downloaded in a range
	public ArrayList<AudioContent> getContent(int fromValue, int toValue)
	{

		//Decrement due to 1-indexing
		toValue--;
		fromValue--;

		//Checks if the values are out of the range
		if(fromValue < 0 || fromValue >= contents.size() || toValue < 0 || toValue >= contents.size()) {
			throw new IndexOutOfBoundsException("Indexes are out of store range");
		}

		//Arraylist that saves all the objects that need to be downloaded
		ArrayList<AudioContent> thingsToDownload = new ArrayList<AudioContent>();

		//Adds each of the objects to the audiocontent arraylist
		for(int i = fromValue; i <= toValue; i++) {
			thingsToDownload.add(contents.get(i));
		}

		return thingsToDownload;


	}
	
	//Lists all the audiostore items
	public void listAll()
	{
		for (int i = 0; i < contents.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	//Searches all the titles for a mathcing audio content
	public void search(String title) 
	{

		//Makes the title uppercase
		String tempTitle = title;
		title = title.toUpperCase();

		//Variable that terminates the loop if the object is found
		boolean test = true;

		//A set that holds all of the keys in the titles map
		Set<String> keys = titles.keySet();

		//A for loop to iterate through the keys
		for(String key: keys) {
			if(title.equals(key.toUpperCase())) {

				//Prints out the audiocontent index and its contents
				System.out.print(titles.get(key) + ". ");
				(contents.get(titles.get(key)-1)).printInfo();
				test = false;
				break;
			}
		}

		//Throws an exception if the program dosen't find any title that matches
		if(test) {
			throw new SearchNoResults("No matches for " + tempTitle);
		}

		System.out.println();
	}

	//Searches all the artists and prints which audiocontent that matches the artist
	public void searchA(String artist) 
	{
		//Makes the artist upercase
		String tempArtist = artist;
		artist = artist.toUpperCase();

		//Variable that terminates the loop if the object is found
		boolean test = true;

		//A set that holds all of the keys in the artists map
		Set<String> keys = artists.keySet();

		//A for loop to iterate through the keys
		for(String key: keys) {
			if(artist.equals(key.toUpperCase())) {

				//A for loop to iterate through the arraylist associated with the key
				for(int i = 0; i < artists.get(key).size(); i++) {

					//Prints out the audiocontent index and its contents
					System.out.print(artists.get(key).get(i) + ". ");
					(contents.get(artists.get(key).get(i)-1)).printInfo();
					System.out.println();
				}
				test = false;
			}
		}

		//Throws an exception if the program dosen't find any artist that matches
		if(test) {
			throw new SearchNoResults("No matches for " + tempArtist);
		}
	}

	//Searches all the genres and prints which audiocontent that matches the genre
	public void searchG(String genre) 
	{
		//Makes the genre upercase
		String tempGenre = genre;
		genre = genre.toUpperCase();

		//Variable that terminates the loop if the object is found
		boolean test = true;

		//A set that holds all of the keys in the genres map
		Set<String> keys = genres.keySet();

		//A for loop to iterate through the keys
		for(String key: keys) {

			if(genre.equals(key.toUpperCase())) {

				//A for loop to iterate through the arraylist associated with the key
				for(int i = 0; i < genres.get(key).size(); i++) {
					System.out.print(genres.get(key).get(i) + ". ");
					(contents.get(genres.get(key).get(i)-1)).printInfo();
					System.out.println();
				}
				test = false;
			}
		}

		//Throws an exception if the program dosen't find any genre that matches
		if(test) {
			throw new SearchNoResults("No matches for " + tempGenre);
		}
	}

	//Downloads content using the artist instead of a index
	public ArrayList<AudioContent> getContentA(String artist) {

		//Makes the artist uppercase
		String tempArtist = artist;
		artist = artist.toUpperCase();

		//An arraylist that stores all the audiocontent that has the same artist
		ArrayList<AudioContent> artistsDownload = new ArrayList<AudioContent>();

		//Key to terminate if the artist if found
		boolean test = true;

		//Sey that holds all the key values
		Set<String> keys = artists.keySet();

		//For loop to iterate through the map
		for(String key: keys) {
			if(artist.equals(key.toUpperCase())) {

				//For loop to add each index associated to the artist to the artistsdownlaoded arrraylist
				for(int i = 0; i < artists.get(key).size(); i++) {
					artistsDownload.add(contents.get(artists.get(key).get(i)-1));
				}
				test = false;
			}
		}
		
		//Throws new exception if the artist isn't found
		if(test) {
			throw new SearchNoResults("No matches for " + tempArtist);
		}

		return artistsDownload;
	}

	//Downloads content using the genre instead of a index
	public ArrayList<AudioContent> getContentG(String genre) {

		//Makes the genre uppercase
		String tempGenre = genre;
		genre = genre.toUpperCase();

		//An arraylist that stores all the audiocontent that has the same genre
		ArrayList<AudioContent> genreDownload = new ArrayList<AudioContent>();

		//Key to terminate if the genre if found
		boolean test = true;

		//Sey that holds all the key values
		Set<String> keys = genres.keySet();

		//For loop to iterate through the map
		for(String key: keys) {
			if(genre.equals(key.toUpperCase())) {

				//For loop to add each index associated to the genre to the genreDownloaded arrraylist
				for(int i = 0; i < genres.get(key).size(); i++) {
					genreDownload.add(contents.get(genres.get(key).get(i)-1));
				}
				test = false;
			}
		}
		
		//Throws new exception if the genre isn't found
		if(test) {
			throw new SearchNoResults("No matches for " + tempGenre);
		}

		return genreDownload;
	}
	
	//Searches every variable in the Audiocontent objects to see if there is any relation to the given string
	public void searchP(String findString) {

		//Key to throw exception if no items found
		boolean key = true;

		//Loop to go through all the audiocontent objects
		for(int i = 0; i < contents.size(); i++) {
			if(contents.get(i).contains(findString)) {

				//Prints the information of the audiocontent if it is related
				contents.get(i).printInfo();
				System.out.println();

				key = false;
			}
		}

		//Throw exception if whatever is searched dosen't return anything
		if(key) {
			throw new SearchNoResults("Nothing found for " + findString);
		}
	}
	
	//Method that stores song and audiobook infromation from the store.txt file
	private void downloadSongs() 
	{
		//Checking for the file
		try {

			//Gets the store.txt file and uses a scanner to iterate through it
			String store = "store.txt";
			Scanner audioStore = new Scanner(new File(store));


			//Checks if there are more lines in the store.txt file
			while(audioStore.hasNextLine()) {

				//Default variables that both audiocontent types use
				String audioType = audioStore.nextLine();
				String id = "";
				String name = "";
				int year = 0;
				int length = 0;
				String artistorAuthor = "";

				//An arraylist that updates whenever song or audiobook objects have similar artists or genres
				ArrayList<Integer> tempArtistsList = new ArrayList<Integer>();
				ArrayList<Integer> tempGenreList = new ArrayList<Integer>();

				//The store.txt layout indicates the type of audiocontent on the first line, so we check the type of audiocontent
				if(audioType.equals(Song.TYPENAME)) {

					//Grab the song values using previously initialized variables and new variables specific for the Song class
					id = audioStore.nextLine();
					name = audioStore.nextLine();
					year = Integer.parseInt(audioStore.nextLine());
					length = Integer.parseInt(audioStore.nextLine());
					artistorAuthor = audioStore.nextLine();
					String composer = audioStore.nextLine();
					String genre = audioStore.nextLine().toUpperCase();
					Song.Genre genre2 = Song.Genre.valueOf(genre); //Converts the string value of the genre into a Genre object
					int lyricAmount = Integer.parseInt(audioStore.nextLine());
					
					//Stores all the lyrics in the audio into one variable
					String lyrics = "";
					for(int i = 0; i < lyricAmount; i++) {
						lyrics += audioStore.nextLine() + "\n";
					}


					//Adds the song into the audiocontent store
					contents.add(new Song(name, year, id, Song.TYPENAME, lyrics, length, artistorAuthor, composer, genre2, lyrics));
					System.out.println("Loading " + Song.TYPENAME);
					
					//Puts the title into the map
					titles.put(name, titleCount);

					//We attempt to get the arraylist associated with the artist key and then update the value
					//We need to check if the value associated with the key is null as arraylists cannot equal null.
					try {
						if(!artists.get(artistorAuthor).equals(null)) {
							tempArtistsList = artists.get(artistorAuthor);
							tempArtistsList.add(artistCount);
							artists.put(artistorAuthor, tempArtistsList);
						}

					//Catches an empty value
					} catch (NullPointerException e) { 
						
						//Adds a new arraylist for the specific artist
						tempArtistsList.add(artistCount);
						artists.put(artistorAuthor, tempArtistsList);
					}

					//We attempt to get the arraylist associated with the genre key and then update the value
					//We need to check if the value associated with the key is null as arraylists cannot equal null.
					try {
						if(!genres.get(genre).equals(null)) {
							tempGenreList = genres.get(genre);
							tempGenreList.add(genreCount);
							genres.put(genre, tempGenreList);
						}

					//Catches an empty value
					} catch (NullPointerException e) {
						//Adds a new arraylist for the specific genre
						tempGenreList.add(genreCount);
						genres.put(genre, tempGenreList);
					}
					
					
				} else if (audioType.equals(AudioBook.TYPENAME)) {

					//Grab the song values using previously initialized variables and new variables specific for the Song class
					id = audioStore.nextLine();
					name = audioStore.nextLine();
					year = Integer.parseInt(audioStore.nextLine());
					length = Integer.parseInt(audioStore.nextLine());
					artistorAuthor = audioStore.nextLine();
					String narrator = audioStore.nextLine();
					int chapters = Integer.parseInt(audioStore.nextLine());
					
					//An arraylist that stores the chatper titles to pass onto the audiobook class
					ArrayList<String> chapterTitlles = new ArrayList<String>();
					for(int i = 0; i < chapters; i++) {
						chapterTitlles.add(audioStore.nextLine());
					}
					
					//An arraylist that stores the contents of each chapter to pass onto the audiobook class
					ArrayList<String> chapterContents = new ArrayList<String>();
					for(int i = 0; i < chapters; i++) {
						int chapterContentLines = Integer.parseInt(audioStore.nextLine());
						String temp = "";
						for(int j = 0; j < chapterContentLines; j++) {
							temp += audioStore.nextLine() + "\n";
						}
						chapterContents.add(temp);
					}
					
					
					String audioFile = ""; // A default audiofile string as it is a parameter in the audiobook class
					System.out.println("Loading " + AudioBook.TYPENAME);

					//Makes the audiobook object and adds it to the audio store
					AudioBook newBook = new AudioBook(name, year, id, audioType, audioFile, length, artistorAuthor, narrator, chapterTitlles, chapterContents);
					contents.add(newBook);

					//Puts the title into the map
					titles.put(name, titleCount);

					//We attempt to get the arraylist associated with the artist key and then update the value
					//We need to check if the value associated with the key is null as arraylists cannot equal null.
					try {
						if(!artists.get(artistorAuthor).equals(null)) {
							tempArtistsList = artists.get(artistorAuthor);
							tempArtistsList.add(artistCount);
							artists.put(artistorAuthor, tempArtistsList);
						}
					
					//Catches an empty value
					} catch (NullPointerException e) {
						tempArtistsList.add(artistCount);
						artists.put(artistorAuthor, tempArtistsList);
					}

					

				}

				//Iteratres one more for all the maps
				titleCount++;
				artistCount++;
				genreCount++;

			}

		//Catches the exception if the file is missing
		} catch (IOException e) {
			System.out.println("Error: File not Found, the program will not have a store, please rename or store the file in the correct location.");
		} 
	}

}

// Error runtime class for a custom error where an error happens if the searched item isn't found
class SearchNoResults extends RuntimeException {

	// Defauly constructors. One dosen't do anything while the other takes a error string and outputs the error.
	public SearchNoResults() {}

	public SearchNoResults(String error) {
		super(error);
	}

}