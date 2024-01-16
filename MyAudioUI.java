//Name: Anas Sajid
//Student Number: 501167312
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))	//Type Q to quit
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
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			//Use methods from library to download song with user giving store content index
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

				System.out.print("To store content #: ");
				if (scanner.hasNextInt())
				{
					index2 = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				for (int i=index;i<=index2;i++){
					AudioContent content = store.getContent(i);
					if (content == null)
						System.out.println("Content Not Found in Store");
					else{
						try{
							mylibrary.download(content);
							System.out.println(content.getType()+" "+content.getTitle()+" added to library");
						}
						catch(ContentAlreadyDownloadedException e){
							System.out.println(e.getMessage());
						}
					}
				}

			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index = 0;
				System.out.print("Song Number: ");
				{
					index = scanner.nextInt();
					scanner.nextLine(); 
				}
				try{
					mylibrary.playSong(index);
				}
				
				catch (AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}
				// Print error message if the song doesn't exist in the library
			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index = 0;
				System.out.print("Audio Book Number: ");
				{
					index = scanner.nextInt();
					scanner.nextLine(); 
				}
				try{
					mylibrary.printAudioBookTOC(index);
				}
				catch (AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}
			// Print error message if the book doesn't exist in the library
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index = 0;
				System.out.print("Audio Book Number: ");
				{
					index = scanner.nextInt();
					scanner.nextLine(); 
				}
				int chap = 0;
				System.out.print("Chapter: ");
				{
					chap = scanner.nextInt();
					scanner.nextLine(); 
				}
				try {
					mylibrary.playAudioBook(index, chap);
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}
				//Print error message if invalid input
			}

			//IRRELEVANT PODCAST CODE FROM A1

			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			/* 
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				int index = 0;
				System.out.print("Podcast Number: ");
				{
					index = scanner.nextInt();
					scanner.nextLine(); 
				}
				int season = 0;
				System.out.print("Season: ");
				{
					season = scanner.nextInt();
					scanner.nextLine(); 
				}
				if (!mylibrary.printPodcastEpisodes(index, season)){
					System.out.println(mylibrary.getErrorMessage());
				}
				//Print error message if invalid input
			}
			*/
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			/* 
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				int index = 0;
				System.out.print("Podcast Number: ");
				{
					index = scanner.nextInt();
					scanner.nextLine(); 
				}
				int season = 0;
				System.out.print("Season: ");
				{
					season = scanner.nextInt();
					scanner.nextLine(); 
				}
				int episode = 0;
				System.out.print("Episode: ");
				{
					episode = scanner.nextInt();
					scanner.nextLine(); 
				}
				if (!mylibrary.playPodcast(index, season, episode)){
					System.out.println(mylibrary.getErrorMessage());
				}
				//Print error message if invalid input
			}
			*/
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
					String name = "";
					System.out.print("Playlist Title: ");
					{
						name = scanner.next();
						scanner.nextLine(); 
					}
					try{
						mylibrary.playPlaylist(name);
					}
					catch (PlayListNotFoundException e){
						System.out.println(e.getMessage());
					}
					//Print error message if invalid input
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String pLName = "";
				System.out.print("Playlist Title: ");
				{
					pLName = scanner.next();
					scanner.nextLine(); 
				}
				int pLInd = 0;
				System.out.print("Content Number: ");
				{
					pLInd = scanner.nextInt();
					scanner.nextLine(); 
				}
				try{
					mylibrary.playPlaylist(pLName, pLInd);
				}
				catch (PlayListNotFoundException e){
					System.out.println(e.getMessage());
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}
				//Print error message if invalid input
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to callt name
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int index = 0;
				System.out.print("Library Song #: ");
				{
					index = scanner.nextInt();
					scanner.nextLine(); 
				}
				try{
					mylibrary.deleteSong(index);
				} 
				
				catch (AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}
				//Print error message if invalid input
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String pLName = "";
				System.out.print("Playlist Title: ");
				{
					pLName = scanner.next();
					scanner.nextLine(); 
				}
				try{
					mylibrary.makePlaylist(pLName);
				}
				catch (PlayListAlreadyExistsException e){
					System.out.println(e.getMessage());
				}
				//Print error message if invalid input
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String pLName = "";
				System.out.print("Playlist Title: ");
				{
					pLName = scanner.next();
					scanner.nextLine(); 
				}
				try{
					mylibrary.printPlaylist(pLName);
				}
				catch (PlayListNotFoundException e){
					System.out.println(e.getMessage());
				}
				//Print error message if invalid input
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				String pLName = "";
				System.out.print("Playlist Title: ");
				{
					pLName = scanner.next();
					scanner.nextLine(); 
				}
				String type = "";
				System.out.print("Content type [SONG, AUDIOBOOK]: ");
				{
					type = scanner.next();
					scanner.nextLine(); 
				}
				int index = 0;
				System.out.print("Library Content #: ");
				{
					index = scanner.nextInt();
					scanner.nextLine(); 
				}
				try{
					mylibrary.addContentToPlaylist(type, index, pLName);
				} 
				
				catch (PlayListNotFoundException e){
					System.out.println(e.getMessage());
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}
				catch(InvalidTypeException e){
					System.out.println(e.getMessage());
				}
				//Print error message if invalid input
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				String pLName = "";
				System.out.print("Playlist Title: ");
				{
					pLName = scanner.next();
					scanner.nextLine(); 
				}
				int index = 0;
				System.out.print("Playlist Content #: ");
				{
					index = scanner.nextInt();
					scanner.nextLine(); 
				}
				try{
					mylibrary.delContentFromPlaylist(index, pLName);
				}
				catch (PlayListNotFoundException e){
					System.out.println(e.getMessage());
				}
				catch (AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}
				//Print error message if invalid input
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
			else if (action.equalsIgnoreCase("SEARCH")) // search the store for an audio content based on title, take user input for content name and use method from AudioContentStore.
			{
				String aName = "";
				System.out.print("Audio content name: ");
				{
					aName = scanner.nextLine();
				}
				store.searchTitle(aName);
			}


			else if (action.equalsIgnoreCase("SEARCHA")) 	//	search the store for content made by an artist. Collect user input for artist name and use the method in AudioContentStore.
			{
				String aName = "";
				System.out.print("Artist: ");
				{
					aName = scanner.nextLine();
				}
				store.searchArtist(aName);
			}
			
			else if (action.equalsIgnoreCase("SEARCHG")) //	search the store for a song of a specific genre. Collect user input for artist name and use the method in AudioContentStore.
			{
				String gName = "";
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				{
					gName = scanner.nextLine();
				}
				store.searchGenre(gName);
			}


			else if (action.equalsIgnoreCase("DOWNLOADA"))	//	Download all content made by a specific author/artist. Take user input for author/artist name and use the function in AudioContentStore to download every piece of content made by them.
			{
				String aName = "";
				System.out.print("Artist Name: ");
				{
					aName = scanner.nextLine();
				}
				try{
					ArrayList<Integer> artistContents = store.returnArtistInd(aName);
					for (int i=0;i<artistContents.size();i++){
						AudioContent content = store.getContent(artistContents.get(i)+1);
						try{
							mylibrary.download(content);
						}
						catch(ContentAlreadyDownloadedException e){
							System.out.println(e.getMessage());
						}
						
					}
				}
				catch (ArtistNotFoundException e){
					System.out.println(e.getMessage());
				}
			}

			else if (action.equalsIgnoreCase("DOWNLOADG")) //	Download all content of a specific genre. Take user input for genre name and use the function in AudioContentStore to download every piece of content of that genre.
			{
				String gName = "";
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				{
					gName = scanner.nextLine();
				}
				try{
					ArrayList<Integer> genreContents = store.returnGenreInd(gName);
					for (int i=0;i<genreContents.size();i++){
						AudioContent content = store.getContent(genreContents.get(i)+1);
						try{
							mylibrary.download(content);
						}
						catch(ContentAlreadyDownloadedException e){
							System.out.println(e.getMessage());
						}
						
					}
				}
				catch (GenreNotFoundException e){
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SEARCHP"))	//	Search all audio content for a target string inputted by the user. Use the method in library class. Print information for all matching content.
			{
				String search = "";
				System.out.print("Target string: ");
				{
					search = scanner.nextLine();
				}
				try{
					ArrayList<AudioContent> results = mylibrary.searchp(search, store);
					for (int i=0;i<results.size();i++){
						store.searchTitle(results.get(i).getTitle());
						System.out.println("");
					}
				}
				catch(AudioContentNotFoundException e){
					System.out.println(e.getMessage());
				}
			}

			System.out.print("\n>");
		}
		
	}
}
