//Name: Anas Sajid
//Student Number: 501167312
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	//Instance fields
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  	private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	//String errorMsg = "";
	//Error message getter
	/* 
	public String getErrorMessage()
	{
		return errorMsg;
	}
	*/
	//Constructor for library class
	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); 
		playlists   = new ArrayList<Playlist>();
	  	podcasts		= new ArrayList<Podcast>(); 
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	//Check to see what datatype is being downloaded, check if it is already downloaded and if not, download it to the appropriate arraylist.
	//Set appropriate error message in case of already downloaded content or invalid content
	public void download(AudioContent content)
	{
		if ((content.getType()).equals("SONG")){
			if (songs.size()==0){
				Song content1 = (Song) content;
				songs.add(content1);
				return;
			}
			for (int i=0;i<songs.size();i++){
				if (songs.get(i).equals(content)){
					throw new ContentAlreadyDownloadedException("Song "+content.getTitle()+" already downloaded.");
				}
			}
			Song content1 = (Song) content;
			songs.add(content1);
			return;
		}
		else if (content.getType().equals("AUDIOBOOK")){
			if (audiobooks.size()==0){
				AudioBook content1 = (AudioBook) content;
				audiobooks.add(content1);
				System.out.println(content1.getTitle()+" added to Library");
				return;
			}
			for (int i=0;i<audiobooks.size();i++){
				if (audiobooks.get(i).equals(content)){
					throw new ContentAlreadyDownloadedException("AudioBook "+content.getTitle()+" already downloaded.");
				}
			}
			AudioBook content1 = (AudioBook) content;
			audiobooks.add(content1);
			return;
		}
	}
	
	// Print Information (printInfo()) about all songs in the array list
	//Iterate through all songs and display information
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	//Iterate through all audiobooks and display information
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();	
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
  //Iterate through all podcasts and display information
	public void listAllPodcasts()
	{
		for (int i = 0; i < podcasts.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			podcasts.get(i).printInfo();
			System.out.println();	
		}
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	//Iterate through all playlists and display playlist titles
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			System.out.print(playlists.get(i).getTitle());
			System.out.println();	
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arraylist is complete, print the artists names
		ArrayList<String> aName = new ArrayList<String>();
		boolean add;
		for (int i=0;i<songs.size();i++){
			add=true;
			for (int j=0;j<aName.size();j++){
				if (aName.get(j).equals(songs.get(i).getArtist())){
					add=false;
				}
			}
			if (add==true){
				aName.add(songs.get(i).getArtist());
			}
		}

		for (int i = 0; i < aName.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			System.out.println(aName.get(i));
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	//If the song index is invalid, return an appropriate error message
	public void deleteSong(int index)
	{
		if (index<1||index>songs.size()){
			throw new AudioContentNotFoundException("Song not found");
		}
		Song removing = songs.get(index-1);
		songs.remove(index-1);
		//Iterate through each playlist and check if the removed song is in there, if it is then remove it
		for (int i=0;i<playlists.size();i++){
			if (playlists.get(i).getContent().contains(removing)){
				playlists.get(i).deleteContent(playlists.get(i).getContent().indexOf(removing)+1);
				return;
			}
		}
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song song, Song song1){
			return song.getYear()-song1.getYear();
		}
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 
	 Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song song, Song song1){
			return song.getLength()-song1.getLength();
		}
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs);
	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{
		if (index < 1 || index > songs.size())
		{
			throw new AudioContentNotFoundException("Song not found");
		}
		songs.get(index-1).play();
	}
	
//Irrelevant code from A1	

	// Play podcast from list (specify season and episode)
	// Bonus
	//Validate all 3 inputs and display appropriate error messages if they are invalid
	//Play the specific episode of the podcast
	/* 
	public boolean playPodcast(int index, int season, int episode)
	{
		if (index < 1 || index > podcasts.size())
		{
			errorMsg = "Podcast not found";
			return false;
		}
		if (season<1||season>podcasts.get(index-1).getSeasons().size()){
			errorMsg="Season not found";
			return false;
		}
		if (episode<1||episode>podcasts.get(index-1).getSeasons().get(season-1).getFiles().size()){
			errorMsg="Episode not found";
			return false;
		}
		podcasts.get(index-1).play(season,episode);
		return true;
	}
	*/
	// Print the episode titles of a specified season
	// Bonus 
	//Validate inputs, return appropriate error messages if invalid
	//Loop through the episodeTitles of a season and display each title
	/* 
	public boolean printPodcastEpisodes(int index, int season)
	{	
		if (index<1||index>podcasts.size()){
			errorMsg="Podcast not found";
			return false;
		}
		if (season>podcasts.get(index-1).getSeasons().size()||season<1){
			errorMsg="Season not found";
			return false;
		}
		for (int i = 0; i < podcasts.get(index-1).getSeasons().get(season-1).getTitles().size(); i++)
		{
			int indexp = i + 1;
			System.out.print("Episode " + indexp + ". ");
			System.out.println(podcasts.get(index-1).getSeasons().get(season-1).getTitles().get(i));
			System.out.println();
		}
		return true;
	}
	*/
	// Play a chapter of an audio book from list of audiobooks
	//Validate inputs, return appropriate error messages if invalid
	//Find the book in audiobooks arraylist and play the chapter
	public void playAudioBook(int index, int chapter)
	{
		if (index>audiobooks.size()||index<1){
			throw new AudioContentNotFoundException("Audiobook not found");
		}
		if (chapter>audiobooks.get(index-1).getChapters().size()||chapter<1){
			throw new AudioContentNotFoundException("Chapter not found");
		}
		audiobooks.get(index-1).selectChapter(chapter);
		audiobooks.get(index-1).play();
		
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	//Validate inputs, return appropriate error messages if invalid
	public void printAudioBookTOC(int index)
	{
		if (index<1||index>audiobooks.size()){
			throw new AudioContentNotFoundException("Audiobook not found");
		}
		audiobooks.get(index-1).printTOC();
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{
		for (int i=0;i<playlists.size();i++){
			if(playlists.get(i).getTitle().equals(title)){
				throw new PlayListAlreadyExistsException("Playlist "+title+" already exists");
			}
		}
		playlists.add(new Playlist(title));
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	//Validate input, return appropriate error message if invalid
	//Loop through the playlist, check the data type of each piece of content, call the appropriate method to display the information
	public void printPlaylist(String title)
	{
		for (int i=0;i<playlists.size();i++){
			if (playlists.get(i).getTitle().equals(title)){
				for (int j=0;j<playlists.get(i).getContent().size();j++){
					int index = j+1;
					if(playlists.get(i).getContent().get(j).getType().equals("SONG")){
						Song tempSong = (Song) playlists.get(i).getContent().get(j);
						System.out.print("" + index + ". ");
						tempSong.printInfo();
						System.out.println();	
					}
					else if (playlists.get(i).getContent().get(j).getType().equals("AUDIOBOOK")){
						AudioBook tempBook = (AudioBook) playlists.get(i).getContent().get(j);
						System.out.print("" + index + ". ");
						tempBook.printInfo();
						System.out.println();
					}
					else if (playlists.get(i).getContent().get(j).getType().equals("PODCAST")){
						Podcast tempPod = (Podcast) playlists.get(i).getContent().get(j);
						System.out.print("" + index + ". ");
						tempPod.printInfo();
						System.out.println();
					}
				}
				return;
			}
		}
		throw new PlayListNotFoundException("Playlist not found");
	}
	
	// Play all content in a playlist
	//Validate input, return appropriate error message if invalid
	//Loop through the playlist, check the data type of each piece of content, call the appropriate method to play each content
	public void playPlaylist(String playlistTitle)
	{
		for (int i=0;i<playlists.size();i++){
			if (playlists.get(i).getTitle().equals(playlistTitle)){
				for (int j=0;j<playlists.get(i).getContent().size();j++){
					if (playlists.get(i).getContent().get(j).getType().equals("SONG")){
						Song tempSong = (Song) playlists.get(i).getContent().get(j);
						tempSong.play();
						System.out.println();
					}
					else if (playlists.get(i).getContent().get(j).getType().equals("AUDIOBOOK")){
						AudioBook tempBook = (AudioBook) playlists.get(i).getContent().get(j);
						tempBook.play();
						System.out.println();
					}
					else if (playlists.get(i).getContent().get(j).getType().equals("PODCAST")){
						Podcast tempPod = (Podcast) playlists.get(i).getContent().get(j);
						tempPod.play();
						System.out.println();
					}
				}
				return;
			}
		}
		throw new PlayListNotFoundException("Playlist not found");
	}
	
	// Play a specific song/audiobook in a playlist
	//Validate inputs, return appropriate error message if invalid
	//Loop through the playlist, check the data type of each piece of content, call the appropriate method to play the content
	//Also allow to play podcast but just play the whole podcast for simplicity
	public void playPlaylist(String playlistTitle, int indexInPL)
	{
		for (int i=0;i<playlists.size();i++){
			if (playlists.get(i).getTitle().equals(playlistTitle)){
				System.out.println(playlistTitle);
				if (indexInPL>playlists.get(i).getContent().size()||indexInPL<1){
					throw new AudioContentNotFoundException("Content not found");
				}
				if (playlists.get(i).getContent().get(indexInPL-1).getType().equals("SONG")){
					Song tempSong = (Song) playlists.get(i).getContent().get(indexInPL-1);
					tempSong.play();
					return;
				}
				else if (playlists.get(i).getContent().get(indexInPL-1).getType().equals("AUDIOBOOK")){
					AudioBook tempBook = (AudioBook) playlists.get(i).getContent().get(indexInPL-1);
					tempBook.play();
					return;
				}
				else if (playlists.get(i).getContent().get(indexInPL-1).getType().equals("PODCAST")){
					Podcast tempPod = (Podcast) playlists.get(i).getContent().get(indexInPL-1);
					tempPod.play();
					return;
				}
			}
		}
		throw new PlayListNotFoundException("Playlist not found");
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	//Validate input, return appropriate error message if invalid
	//Add the content to the appropriate list by checking the data type
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{	
		int pLInd=0;
		boolean found = false;
		for (int i=0;i<playlists.size();i++){
			if(playlists.get(i).getTitle().equals(playlistTitle)){
				found = true;
				pLInd=i;
			}
		}

		if (!found){
			throw new PlayListNotFoundException("Playlist not found");
		}

		if (type.equalsIgnoreCase("SONG")){
			if (index>songs.size()||index<1){
				throw new AudioContentNotFoundException("Song not found");
			}
			playlists.get(pLInd).addContent(songs.get(index-1));
			return;
		}

		else if (type.equalsIgnoreCase("AUDIOBOOK")){
			if (index>audiobooks.size()||index<1){
				throw new AudioContentNotFoundException("AudioBook not found");
			}
			playlists.get(pLInd).addContent(audiobooks.get(index-1));
			return;
		}
		else if (type.equalsIgnoreCase("PODCAST")){
			if (index>podcasts.size()||index<1){
				throw new AudioContentNotFoundException("Podcast not found");
			}
			playlists.get(pLInd).addContent(podcasts.get(index-1));
			return;
		}

		else{
			throw new InvalidTypeException("Invalid type");
		}
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	//Validate input, return appropriate error message if invalid
	//Loop through the playlists, get the correct playlist, delete the content at the index
	public void delContentFromPlaylist(int index, String title)
	{
		int pLInd=0;
		boolean found = false;
		for (int i=0;i<playlists.size();i++){
			if(playlists.get(i).getTitle().equals(title)){
				found = true;
				pLInd=i;
			}
		}
		if (!found){
			throw new PlayListNotFoundException("Playlist not found");
		}
		if (index>playlists.get(pLInd).getContent().size()||index<1){
			throw new AudioContentNotFoundException("Content not found");
		}
		else{
			playlists.get(pLInd).deleteContent(index);
			return;
		}
	}

	//Search a target string in each audiocontent and return the ones that contain the target string.
	//Take user input for target string and check every aspect of every audiocontent in the store.
	//Return an arraylist of audiocontents containing the target string.
	//Display appropriate error message if no matches are found.
	public ArrayList<AudioContent> searchp(String target, AudioContentStore store){
		ArrayList<AudioContent> results = new ArrayList<AudioContent>();
		for (int i=0;i<store.getContents().size();i++){
			if(store.getContents().get(i).getType().equalsIgnoreCase("SONG")){
				Song tempSong = (Song) store.getContents().get(i);
				if (tempSong.getArtist().contains(target)||tempSong.getAudioFile().contains(target)||String.valueOf(tempSong.getClass()).contains(target)||tempSong.getComposer().contains(target)||String.valueOf(tempSong.getGenre()).contains(target)||tempSong.getId().contains(target)||String.valueOf(tempSong.getLength()).contains(target)||tempSong.getLyrics().contains(target)||tempSong.getTitle().contains(target)||tempSong.getType().contains(target)||String.valueOf(tempSong.getYear()).contains(target)){
					results.add(store.getContents().get(i));
				}
			}
			else if (store.getContents().get(i).getType().equalsIgnoreCase("AUDIOBOOK")){
				AudioBook tempBook = (AudioBook) store.getContents().get(i);
				if (tempBook.getAudioFile().contains(target)||tempBook.getAuthor().contains(target)||tempBook.getChapterTitles().contains(target)||tempBook.getChapters().contains(target)||String.valueOf(tempBook.getClass()).contains(target)||tempBook.getId().contains(target)||String.valueOf(tempBook.getLength()).contains(target)||tempBook.getNarrator().contains(target)||tempBook.getTitle().contains(target)||tempBook.getType().contains(target)||String.valueOf(tempBook.getYear()).contains(target)){
					results.add(store.getContents().get(i));
				}
				else{
					for (int j=0;j<tempBook.getChapters().size();j++){
						if (tempBook.getChapters().get(j).contains(target)){
							results.add(store.getContents().get(i));
							break;
						}
					}
				}
			}
		}
		if (results.size()==0){
			throw new AudioContentNotFoundException("Target string not found");
		}
		return results;
	}
	
	
}

//Custom exception for if audio content is not found.
class AudioContentNotFoundException extends RuntimeException{
	public AudioContentNotFoundException(){

	}
	public AudioContentNotFoundException(String msg){
		super(msg);
	}
}
//Custom exception for if audio content is already downloaded.
class ContentAlreadyDownloadedException extends RuntimeException{
	public ContentAlreadyDownloadedException(){

	}
	public ContentAlreadyDownloadedException(String msg){
		super(msg);
	}
}

//Custom exception for if playlist already exists.
class PlayListAlreadyExistsException extends RuntimeException{
	public PlayListAlreadyExistsException(){

	}
	public PlayListAlreadyExistsException(String msg){
		super(msg);
	}
}

//Custom exception for if playlist is not found.
class PlayListNotFoundException extends RuntimeException{
	public PlayListNotFoundException(){

	}
	public PlayListNotFoundException(String msg){
		super(msg);
	}
}

//Custom exception for if an invalid content type is entered.
class InvalidTypeException extends RuntimeException{
	public InvalidTypeException(){

	}
	public InvalidTypeException(String msg){
		super(msg);
	}
}