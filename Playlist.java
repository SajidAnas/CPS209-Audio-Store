//Name: Anas Sajid
//Student Number: 501167312
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	//Instance fields
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	//Constructor for playlist class
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	//Getter for title
	public String getTitle()
	{
		return title;
	}
	//Setter for title
	public void setTitle(String title)
	{
		this.title = title;
	}
	//Method to add content
	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	//Getter for content list
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}
	//Setter for content list
	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	//Iterate through every piece of content in the playlist and print its information
	public void printContents()
	{
		for (int i = 0; i < contents.size(); i++)
		{
			int index = i + 1;
			System.out.print("" + index + ". ");
			contents.get(i).printInfo();
			System.out.println();	
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for(int i=0;i<contents.size();i++){
			contents.get(i).play();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if (this.contains(index)){
			contents.get(index-1).play();
		}
	}
	
	//Check if index is valid in a playlist
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		Playlist other1 = (Playlist) other;
		if (this.title.equals(other1.title)){
			return true;
		}
		else{
			return false;
		}
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return;
		contents.remove(index-1);
	}
	
	
}
