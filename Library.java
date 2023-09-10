/*
 * 	JAVA Assignment #2
 * 	Name: Kush Patel
 * 	Student Number: 501168852
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	

	//Special variable to hold the index of a certain playlist. Custom method uses this index
	private int playlistIndex = 0;

	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(ArrayList<AudioContent> content)
	{
		System.out.println();
		for(int i = 0; i < content.size(); i++) {
			if(content.get(i).getType().equals(Song.TYPENAME)) {
				Song temp = (Song) content.get(i);
				if(!(songs.contains(temp))) {
					songs.add(temp);
					System.out.println(Song.TYPENAME + " " + content.get(i).getTitle() + " Added to Library");
				} else {
					System.out.println( Song.TYPENAME + " " + content.get(i).getTitle() + " already downloaded");
				}
			} else if (content.get(i).getType().equals(AudioBook.TYPENAME)){
				AudioBook temp = (AudioBook) content.get(i);
				if(!(audiobooks.contains(temp))) {
					audiobooks.add(temp);
					System.out.println(AudioBook.TYPENAME + " " + content.get(i).getTitle() + " Added to Library");
				} else {
					System.out.println(AudioBook.TYPENAME + " " + content.get(i).getTitle() + " already downloaded");
				}
			}

		}

	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		//Use a for loop to get every song in the arraylist
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo(); //Use the song print method to print the value
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		//Use a for loop to look at every audiobook in the arraylist
		for(int i = 0; i < audiobooks.size(); i++) {
			System.out.print(i+1 + ". ");
			audiobooks.get(i).printInfo(); //Use the audiobook print method to print the values
			System.out.println();	
		}
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for(int i = 0; i < playlists.size(); i++) {
			System.out.println((i+1) + ". " + playlists.get(i).getTitle());
		}

	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names


		//Create a String arraylist to hold all the artists
		ArrayList<String> Artists = new ArrayList<String>(); 

		for(int i = 0; i < songs.size(); i++) {
			//Checks if a artists is already in the list before adding them
			if(!Artists.contains(songs.get(i).getArtist())) {
				Artists.add(songs.get(i).getArtist());
			}
		}

		//Printing each artist
		for(int i = 0; i < Artists.size(); i++) {
			System.out.println((i+1) + ". " + Artists.get(i));
		}
		
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{

		//Decrement the index
		index--;

		if(index < 0 || index >= songs.size()) {
			throw new ObjectNotFoundException("Song not Found");
		}

		Song temp = songs.get(index); //Creating a variable to hold the Song values
		songs.remove(index); // Remove the Song value from the arraylist

		//Going through every playlist to delete the song (If it is found in the playlist)
		for(int i = 0; i < playlists.size(); i++) {
			playlists.get(i).deleteSong(temp);
		}



	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		//Create a new object of the private class
		SongYearComparator yearsort = new SongYearComparator();

		// Use Collections.sort()
		Collections.sort(songs, yearsort);
	
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year

	private class SongYearComparator implements Comparator<Song>
	{	
		//Implement the compare method from the Comparator interface
		public int compare(Song number1, Song number2) {
			//Return if one number is greater than the other
			return Integer.compare(number1.getYear(), number2.getYear());
		}
	
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
		// Use a new object of a private class
		SongLengthComparator lengthsort = new SongLengthComparator();

		// Use Collections.sort()

		Collections.sort(songs, lengthsort);

	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		//Implement the compare method from the comparator interface
		public int compare(Song number1, Song number2) {

			//Return -1,0,1 based on the two lengths
			if(number1.getLength() < number2.getLength()) {
				return -1;
			} else if (number1.getLength() > number2.getLength()) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs); //Implements the sorting algorithm found in the Song Class (Which is by name)

	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		//Decrement index
		index--;

		if(index < 0 || index >= songs.size()) {
			throw new ObjectNotFoundException("Song not Found");
		}

		songs.get(index).play();

	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{
		//Decrementing and checking if the index is valid
		index--;

		//Decrementing and checking if the index is valid
		index--;

		if(index < 0 || index >= audiobooks.size()) {
			throw new ObjectNotFoundException("Audiobook not Found");
		}
		
		//Checking if the chapter is valid
		if(chapter < 0 || chapter >= audiobooks.size()) {
			throw new ObjectNotFoundException("Chapter not Found");
		}

		//Setting the chapter value and then playing the specific chapter
		audiobooks.get(index).selectChapter(chapter);
		audiobooks.get(index).play();
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{
		//Decrementing and validating the index
		index--;

		if(index < 0 || index >= audiobooks.size()) {
			throw new ObjectNotFoundException("Audiobook not Found");
		}

		//Uses the audiobook method to print a table of contents
		audiobooks.get(index).printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		//Goes through the playlist arraylist to see if the playlist already exists

		for(int i = 0; i < playlists.size(); i++) {
			if(playlists.get(i).getTitle().equals(title)) {
				throw new ObjectNotFoundException("Playlist Already Exists");
			}
		}

		//Creating a new playlist object and then adding it to the arraylist
		Playlist newPlaylist = new Playlist(title);
		playlists.add(newPlaylist);
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{
		if(!titleInside(title)) {
			throw new ObjectNotFoundException("Playlist Not Found");
		}
		playlists.get(playlistIndex).printContents(); //Using the playlists built-in method to print the contents

	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		//Custom method to see if the playlist title exists

		if(!titleInside(playlistTitle)) {
			throw new ObjectNotFoundException("Playlist Not Found");
		}

		playlists.get(playlistIndex).playAll(); //Plays all the values in the playlists

	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		if(!titleInside(playlistTitle)) {
			throw new ObjectNotFoundException("Playlist Not Found");
		} else if (!playlists.get(playlistIndex).contains(indexInPL)) {
			throw new ObjectNotFoundException("Playlist Not Found");
		}

		playlists.get(playlistIndex).play(indexInPL); //Playing the value in the index

	}
	
	// Add a song/audiobook from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{	
		index--; //Decrementing the index
		type = type.toUpperCase(); //Changing the type to uppercase

		if(!titleInside(playlistTitle)) {
			throw new ObjectNotFoundException("Playlist Not Found");
		} else if (type.equals(Song.TYPENAME) && (index < 0 || index >= songs.size())) {
			throw new ObjectNotFoundException("Song Not Found");
		} else if (type.equals(AudioBook.TYPENAME) && (index < 0 || index >= audiobooks.size())) {
			throw new ObjectNotFoundException("Audiobook Not Found");
		} else if (!type.equals(Song.TYPENAME) && !type.equals(AudioBook.TYPENAME)) {
			throw new ObjectNotFoundException("Content Type Not Found");
		}


		if(type.equals(Song.TYPENAME)) {
			playlists.get(playlistIndex).addContent(songs.get(index)); //Adding the song to the playlist
		} else {
			playlists.get(playlistIndex).addContent(audiobooks.get(index)); //Adding the audiobook to the playlist
		}
	}

  // Delete a song/audiobook from a playlist with the given title
	// Make sure the given index of the song/audiobook in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{
		//Decrement the index
		index--;

		if(index < 0 || index > playlists.size() || !titleInside(title)) {
			throw new ObjectNotFoundException("Playlist Not Found");
		}

		playlists.get(playlistIndex).deleteContent(index); //Deleting the content
		
	}


	/*
	 * ------------------------------------------------------------------------------------
	 * 									CUSTOM METHODS
	 * ------------------------------------------------------------------------------------
	 */

	// Special method to check if the title of a playlist is in the playlists arraylist
	public boolean titleInside(String title) {
		//For loop to check every playlist
		for(int i = 0; i < playlists.size(); i++) {
			//Check if the playlist title has the same name as the title passed through
			if(playlists.get(i).getTitle().equals(title)) {
				playlistIndex = i; //Returns the index of the playlist with the title that is desired
				return true;
			}
		}
		return false;
	}
	
}

/*
* ------------------------------------------------------------------------------------
* 									CUSTOM ClASSES
* ------------------------------------------------------------------------------------
*/

//Class for objects that are not found
class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException() {}

	public ObjectNotFoundException(String errorObject) {
		super(errorObject);
		return;
	}

}


//Class for objects that are already in the array
class ObjectAlreadyExists extends RuntimeException {

	public ObjectAlreadyExists() {}

	public ObjectAlreadyExists(String errorObject) {
		super(errorObject);
		return;
	}

}



