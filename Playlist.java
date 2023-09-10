/*
 * 	JAVA Assignment #2
 * 	Name: Kush Patel
 * 	Student Number: 501168852
 */

import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks) from the library
 */
public class Playlist
{
	private String title; // The name of the playlist
	private ArrayList<AudioContent> contents; // songs or books or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{

		for(int i = 0; i < contents.size(); i++) {

			if(contents.get(i).getType().equals(Song.TYPENAME)) {
				Song temp = (Song) contents.get(i);
				System.out.print((i+1) + ". ");
				temp.printInfo();
			} else if (contents.get(i).getType().equals(AudioBook.TYPENAME)) {
				AudioBook temp = (AudioBook) contents.get(i);
				System.out.print((i+1) + ". ");
				temp.printInfo();
			}

			System.out.println();
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for(int i = 0; i < contents.size(); i++) {
			contents.get(i).play();
			System.out.println();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		index--;
		if (index > -1 || index < contents.size()-1) {
			contents.get(index).play();
		} 
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		Playlist otherPlaylist = (Playlist) other;

		if(this.getTitle() == otherPlaylist.getTitle()) {
			return true;
		}
		return false;
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		//if (!contains(index)) return;
		//contents.remove(index-1);

		if(!(index > contents.size()-1 || index < 0)) {
			contents.remove(index);
		}
	}

	
	/*
	 * -----------------------------------------------------------------------------------------------------
	 * 											CUSTOM METHODS
	 * -----------------------------------------------------------------------------------------------------
	 */

	//Given a song remove it from the playlist
	public void deleteSong(Song other)
	{
		//Goes through the playlist
		for(int i = 0; i < contents.size(); i++) {
			//Checks that the content type is Song to avoid casting issues
			if(contents.get(i).getType().equals(Song.TYPENAME)) {
				//Checking if the two values are equal to remove the value
				if(contents.get(i).equals(other)) {
					//Removing the value from the playlists
					contents.remove(i);
				}
			}
		}
	}

	//Method to return the size of the playlist
	public int size() {
		return contents.size();
	}
	
	
}
