/*
 * 	JAVA Assignment #2
 * 	Name: Kush Patel
 * 	Student Number: 501168852
 */

import java.util.Scanner;
import java.util.ArrayList;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{	try {
				String action = scanner.nextLine();

				if (action == null || action.equals("")) 
				{
					System.out.print("\n>");
					continue;
				}
				else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
					return;
				
				else if (action.equalsIgnoreCase("STORE"))	// List all songs
				{
					store.listAll(); 
				}
				else if (action.equalsIgnoreCase("SONGS"))	// List all songs
				{
					mylibrary.listAllSongs(); 
				}
				else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
				{
					mylibrary.listAllAudioBooks(); 
				}
				else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
				{
					mylibrary.listAllArtists(); 
				}
				else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
				{
					mylibrary.listAllPlaylists(); 
				}
				// Download audiocontent (song/audiobook) from the store sor
				// Specify the index of the content
				else if (action.equalsIgnoreCase("DOWNLOAD")) 
				{
					int index = 0;
					int index2 = 0;
					
					System.out.print("From Store Content #: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					System.out.print("To Store Content #: ");
					if (scanner.hasNextInt())
					{
						index2 = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					try {
						ArrayList<AudioContent> content = store.getContent(index, index2);
						mylibrary.download(content);
					} catch (IndexOutOfBoundsException e) {
						System.out.println(e.getMessage());
					}

										
				}
				// Get the *library* index (index of a song based on the songs list)
				// of a song from the keyboard and play the song 
				else if (action.equalsIgnoreCase("PLAYSONG")) 
				{
					int index = 0;
					System.out.print("Song Number #: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					mylibrary.playSong(index);
					// Print error message if the song doesn't exist in the library
				}
				// Print the table of contents (TOC) of an audiobook that
				// has been downloaded to the library. Get the desired book index
				// from the keyboard - the index is based on the list of books in the library
				else if (action.equalsIgnoreCase("BOOKTOC")) 
				{
					int index = 0;
					
					System.out.print("Audio Book Number #: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					mylibrary.printAudioBookTOC(index);

				// Print error message if the book doesn't exist in the library
				}
				// Similar to playsong above except for audio book
				// In addition to the book index, read the chapter 
				// number from the keyboard - see class Library
				else if (action.equalsIgnoreCase("PLAYBOOK")) 
				{
					int bookindex = 0;
					int chapindex = 0;

					System.out.print("Audio Book Number #: ");
					if (scanner.hasNextInt())
					{
						bookindex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					System.out.print("Audio Chapter Number #: ");
					if (scanner.hasNextInt())
					{
						chapindex = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					mylibrary.playAudioBook(bookindex, chapindex);
					
				}

				// Specify a playlist title (string) 
				// Play all the audio content (songs, audiobooks) of the playlist 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYALLPL")) 
				{
					String title = "";
					System.out.print("Playlist Title Name: ");
					if (scanner.hasNext())
					{
						title = scanner.nextLine();
					}

					mylibrary.playPlaylist(title);
					
				}
				// Specify a playlist title (string) 
				// Read the index of a song/audiobook in the playist from the keyboard 
				// Play all the audio content 
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("PLAYPL")) 
				{
					String title = "";
					int index = 0;
					System.out.print("Playlist Title Name: ");
					if (scanner.hasNext())
					{
						title = scanner.nextLine();
					}

					System.out.print("Playlist Content Number #: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}



					mylibrary.playPlaylist(title, index);
					
				}
				// Delete a song from the list of songs in mylibrary and any play lists it belongs to
				// Read a song index from the keyboard
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELSONG")) 
				{
					int index = 0;
					System.out.print("Song Number #: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					mylibrary.deleteSong(index);

				}
				// Read a title string from the keyboard and make a playlist
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("MAKEPL")) 
				{
					String title = "";
					System.out.print("Playlist Title Name: ");
					if (scanner.hasNext())
					{
						title = scanner.nextLine();
					}

					mylibrary.makePlaylist(title);
						
					
				}
				// Print the content information (songs, audiobooks) in the playlist
				// Read a playlist title string from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
				{
					String title = "";
					System.out.print("Playlist Title Name: ");
					if (scanner.hasNext())
					{
						title = scanner.nextLine();
					}

					mylibrary.printPlaylist(title);
					
				}
				// Add content (song, audiobook ) from mylibrary (via index) to a playlist
				// Read the playlist title, the type of content ("song" "audiobook")
				// and the index of the content (based on song list, audiobook list etc) from the keyboard
			// see class Library for the method to call
				else if (action.equalsIgnoreCase("ADDTOPL")) 
				{	
					int index = 0;
					String type = "";
					String playlistTitle = "";

					System.out.print("Enter the Playlist you want to add to: ");
					if (scanner.hasNext())
					{
						playlistTitle = scanner.nextLine();
					}

					System.out.print("Enter what type of content you wish to add (Song, Audiobook): ");
					if (scanner.hasNext())
					{
						type = scanner.nextLine();
					}

					System.out.print("Enter the index value for what you wish to add (1-indexed, writing 1 will give content from 0 spot): ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}
					
					mylibrary.addContentToPlaylist(type, index, playlistTitle);
				}
				// Delete content from play list based on index from the playlist
				// Read the playlist title string and the playlist index
				// see class Library for the method to call
				else if (action.equalsIgnoreCase("DELFROMPL")) 
				{
					String title = "";
					int index = 0;
					System.out.print("Playlist Title Name: ");
					if (scanner.hasNext())
					{
						title = scanner.nextLine();
					}

					System.out.print("Item in Playlist to be Deleted Number (1-indexed, writing 1 will give content from 0) #: ");
					if (scanner.hasNextInt())
					{
						index = scanner.nextInt();
						scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
					}

					mylibrary.delContentFromPlaylist(index, title);
				}
				
				else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
				{
					mylibrary.sortSongsByYear();
				}
				else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
				{
					mylibrary.sortSongsByName();
				}
				else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
				{
					mylibrary.sortSongsByLength();
				}




				else if (action.equalsIgnoreCase("SEARCH")) // sort songs by length
				{
					String title = "";
					System.out.print("Title: ");
					if (scanner.hasNext())
					{
						title = scanner.nextLine();
					}

					store.search(title);
				}

				else if (action.equalsIgnoreCase("SEARCHA")) // sort songs by length
				{
					String artist = "";
					System.out.print("Artist: ");
					if (scanner.hasNext())
					{
						artist = scanner.nextLine();
					}

					store.searchA(artist);
				}

				else if (action.equalsIgnoreCase("SEARCHG")) // sort songs by length
				{
					String genre = "";
					System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
					if (scanner.hasNext())
					{
						genre = scanner.nextLine();
					}

					store.searchG(genre);
				}

				else if (action.equalsIgnoreCase("SEARCHP")) // sort songs by length
				{
					String objecString = "";
					System.out.print("Write something thats in the audiocontent(s) your looking for: ");
					if (scanner.hasNext())
					{
						objecString = scanner.nextLine();
					}

					store.searchP(objecString);
				}

				else if (action.equalsIgnoreCase("DOWNLOADA")) // sort songs by length
				{
					String artist = "";
					System.out.print("Artist/Author: ");
					if (scanner.hasNext())
					{
						artist = scanner.nextLine();
					}

					ArrayList<AudioContent> artistDownloadList = store.getContentA(artist);
					mylibrary.download(artistDownloadList);
				}

				else if (action.equalsIgnoreCase("DOWNLOADG")) // sort songs by length
				{
					String genre = "";
					System.out.print("Genre: ");
					if (scanner.hasNext())
					{
						genre = scanner.nextLine();
					}

					ArrayList<AudioContent> genreDownloadList = store.getContentG(genre);
					mylibrary.download(genreDownloadList);
				}

				System.out.print("\n>");

			//Catch exceptions for each of the possible cases	
			} catch (ObjectNotFoundException e) { // Exception case if the index is out of range
				System.out.println(e.getMessage());
				System.out.print("\n>");
			} catch (ObjectAlreadyExists e) { //Exception case if the object already exists
				System.out.println(e.getMessage());
				System.out.print("\n>");
			} catch (SearchNoResults e) {
				System.out.println(e.getMessage()); //Exception case if the parameter returned no results
				System.out.print("\n>");
			}
		}
		scanner.close();
	}	
}
