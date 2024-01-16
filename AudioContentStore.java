//Name: Anas Sajid
//Student Number: 501167312
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		//Make variables for hashmaps
		private ArrayList<AudioContent> contents; 
		private HashMap <String, Integer> titleMapInt;
		private HashMap <String, ArrayList<Integer>> artistMapInt;
		private HashMap <String, ArrayList<Integer>> genreMapInt;

		//Constructor for AudioContentStore
		public AudioContentStore()
		{
		/* 
			contents = new ArrayList<AudioContent>();
			
		  // Create some songs audiobooks and podcasts and to store
			String file = "Yesterday, all my troubles";
			contents.add(new Song("Yesterday", 1965, "123", Song.TYPENAME, file, 2, "The Beatles", "Paul McCartney", Song.Genre.POP, file));
			
			file = "I'm sorry if I seem uninterested\r\n"
					+ "Or I'm not listenin' or I'm indifferent\r\n"
					+ "Truly, I ain't got no business here\r\n"
					+ "But since my friends are here, I just came to kick it\r\n"
					+ "But really I would rather be at home all by myself not in this room\r\n"
					+ "With people who don't even care about my well being";
			contents.add(new Song("Here", 2015, "391", Song.TYPENAME, file, 3, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
			
			file = "Yo, Big Shaq, the one and only\r\n"
					+ "Man's not hot, never hot\r\n"
					+ "Skrrat (GottiOnEm), skidi-kat-kat\r\n"
					+ "Boom\r\n"
					+ "Two plus two is four\r\n"
					+ "Minus one that's three, quick maths\r\n"
					+ "Everyday man's on the block\r\n"
					+ "Smoke trees (Ah)";
			contents.add(new Song("Man's Not Hot", 2017, "374", Song.TYPENAME, file, 2, "Michael Dapaah", "Michael Dapaah", Song.Genre.RAP, file));
			
			file = "The world was on fire and no one could save me but you\r\n"
					+ "It's strange what desire will make foolish people do\r\n"
					+ "I never dreamed that I'd meet somebody like you\r\n"
					+ "And I never dreamed that I'd lose somebody like you";
			contents.add(new Song("Wicked Game", 1989, "185", Song.TYPENAME, file, 4, "Chris Isaak", "Chris Isaak", Song.Genre.ROCK, file));
			
			file = "The lights go out and I can't be saved\r\n"
					+ "Tides that I tried to swim against\r\n"
					+ "Have brought me down upon my knees\r\n"
					+ "Oh, I beg, I beg and plead\r\n"
					+ "Singin' come out of things un said";
			contents.add(new Song("Clocks", 2002, "875", Song.TYPENAME, file, 5, "Coldplay", "Guy Berryman, Chris Martin", Song.Genre.ROCK, file));
			
			file = "I'm waking up to ash and dust\r\n"
					+ "I wipe my brow and I sweat my rust\r\n"
					+ "I'm breathing in the chemicals";
			contents.add(new Song("Radioactive", 2012, "823", Song.TYPENAME, file, 3, "Imagine Dragons", "Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee", Song.Genre.ROCK, file));
			
			file = "Birds flying high\r\n"
					+ "You know how I feel\r\n"
					+ "Sun in the sky\r\n"
					+ "You know how I feel\r\n"
					+ "Breeze driftin' on by\r\n"
					+ "You know how I feel\r\n"
					+ "It's a new dawn\r\n"
					+ "It's a new day\r\n"
					+ "It's a new life\r\n"
					+ "For me";
			contents.add(new Song("Feelin' Good", 1965, "875", Song.TYPENAME, file, 3, "Nina Simone", 
					"Anthony Newley, Leslie Bricusse",Song.Genre.JAZZ, file));
			
			file = "Find table spaces, say your social graces\n"
					+ "Bow your head, they're pious here\n"
					+ "But you and I, we're pioneers, we make our own rules\n"
					+ "Our own room, no bias here";
			contents.add(new Song("Wild Things", 2015, "443", Song.TYPENAME, file, 4, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
			
			AudioBook book = new AudioBook("Harry Potter and the Goblet of Fire", 2015, "894", AudioBook.TYPENAME,  "", 1236,
					"J.K. Rowling", "Jim Dale", makeHPChapterTitles(), makeHPChapters());
			contents.add(book);
			
			book = new AudioBook("Moby Dick", 2018, "376", AudioBook.TYPENAME,  "", 1422,
					"Herman Melville", "Pete Cross", makeMDChapterTitles(), makeMDChapters());
			contents.add(book);
			
			book = new AudioBook("Shogun", 2018, "284", AudioBook.TYPENAME,  "", 3213,
					"James Clavel", "Ralph Lister", makeSHChapterTitles(), makeSHChapters());
			contents.add(book);
			
			// Create a podcast object if you are doing the bonus see the makeSeasons() method below
			// It is currently commented out. It makes use of a class Season you may want to also create
			// or change it to something else
			//Create a new podcast object called pod using the information given in the assignment log and add it to contents arraylist
			Podcast pod = new Podcast("The Secret Life of Canada", 2021, "865", "PODCAST", "", 0, "Leah-Simone Bowen, Falen Johnson", makeSeasons());
			contents.add(pod);
			*/
			//Try to read the store from the store file, print error message if IOException is caught
			try{
			contents=readStore();
			}
			catch (IOException e){
				System.out.println(e.getMessage());
			}
			//Make titleMapInt hashmap, pair every title with store index
			this.titleMapInt = new HashMap<String, Integer>();	
			for (int i=0; i<contents.size();i++){
				titleMapInt.put(contents.get(i).getTitle(), i);
			}
			//Make artistMapInt hashmap, pair every artist with store indexes
			this.artistMapInt = new HashMap<String,ArrayList<Integer>>();
			for (int i=0; i<contents.size(); i++){
				if(String.valueOf(contents.get(i).getType()).equalsIgnoreCase("SONG")){
					Song tempSong = (Song) contents.get(i);
					if (artistMapInt.containsKey(tempSong.getArtist())){
						ArrayList<Integer> tempList = artistMapInt.get(tempSong.getArtist());
						tempList.add(i);
						artistMapInt.put(tempSong.getArtist(), tempList);
					}
					else{
						ArrayList<Integer> tempList = new ArrayList<Integer>();
						tempList.add(i);
						artistMapInt.put(tempSong.getArtist(), tempList);
					}
				}

				else if(String.valueOf(contents.get(i).getType()).equalsIgnoreCase("AUDIOBOOK")){
					AudioBook tempBook = (AudioBook) contents.get(i);
					if (artistMapInt.containsKey(tempBook.getAuthor())){
						ArrayList<Integer> tempList = artistMapInt.get(tempBook.getAuthor());
						tempList.add(i);
						artistMapInt.put(tempBook.getAuthor(), tempList);
					}
					else{
						ArrayList<Integer> tempList = new ArrayList<Integer>();
						tempList.add(i);
						artistMapInt.put(tempBook.getAuthor(), tempList);
					}
				}
			}
			//Make genreMapInt hashmap, pair every genre with store indexes
			this.genreMapInt = new HashMap<String,ArrayList<Integer>>();
			for (int i=0; i<contents.size(); i++){
				if(String.valueOf(contents.get(i).getType()).equalsIgnoreCase("SONG")){
					Song tempSong = (Song) contents.get(i);
					if (genreMapInt.containsKey(String.valueOf(tempSong.getGenre()))){
						ArrayList<Integer> tempList = genreMapInt.get(String.valueOf(tempSong.getGenre()));
						tempList.add(i);
						genreMapInt.put(String.valueOf(tempSong.getGenre()), tempList);
					}
					else{
						ArrayList<Integer> tempList = new ArrayList<Integer>();
						tempList.add(i);
						genreMapInt.put(String.valueOf(tempSong.getGenre()), tempList);
					}
				}
			}
		}

		//Method to read file and create a store. Separate case for audiobooks and songs but reads the data from the file lines as instructed in the assignment document and constructs objects accordingly.
		//Then adds them to an arraylist of contents for the store.
		private ArrayList<AudioContent> readStore() throws IOException{
			ArrayList <AudioContent> content = new ArrayList<AudioContent>();
			File sFile = new File("store.txt");
			Scanner fScanner = new Scanner(sFile);
			String type;
			while (fScanner.hasNextLine()){
				type=fScanner.nextLine();
				String id = fScanner.nextLine();
				String title = fScanner.nextLine();
				int year = Integer.parseInt(fScanner.nextLine());
				int length = Integer.parseInt(fScanner.nextLine());
				if (type.equals("SONG")){
					String artist = fScanner.nextLine();
					String composer = fScanner.nextLine();
					String genre = fScanner.nextLine();
					String lyrics = "";
					int numLines = Integer.parseInt(fScanner.nextLine());
					for (int i=0;i<numLines;i++){
						lyrics+=fScanner.nextLine()+"\n";
					}
					content.add(new Song(title, year, id, type, lyrics, length, artist, composer, Song.Genre.valueOf(genre), lyrics));
					System.out.println("Loading SONG");
				}
				else if (type.equals("AUDIOBOOK")){
					String author = fScanner.nextLine();
					String narrator = fScanner.nextLine();
					int numChap=Integer.parseInt(fScanner.nextLine());
					ArrayList<String> chapTitles = new ArrayList<String>();
					ArrayList<String> chapters = new ArrayList<String>();
					for (int i=0;i<numChap;i++){
						chapTitles.add(fScanner.nextLine());
					}
					for (int i=0;i<numChap;i++){
						int numLines = Integer.parseInt(fScanner.nextLine());
						String chap = "";
						for (int j=0;j<numLines;j++){
							chap+=fScanner.nextLine()+"\n";
						}
						chapters.add(chap);
					}
					content.add(new AudioBook(title, year, id, type, "", length, author, narrator, chapTitles, chapters));
					System.out.println("Loading AUDIOBOOK");
				}
			}
			fScanner.close();
			return content; 
		}

		//Method to use hashmap correlating titles and store indexes of songs to quickly search for and return info about audiocontent with a sepcific title.
		public void searchTitle(String title){
			if (titleMapInt.containsKey(title)){
				if (String.valueOf(contents.get(titleMapInt.get(title)).getType()).equalsIgnoreCase("SONG")){
					Song temps = (Song) contents.get(titleMapInt.get(title));
					System.out.print(titleMapInt.get(title)+1+". ");
					temps.printInfo();
				}
				else if (String.valueOf(contents.get(titleMapInt.get(title)).getType()).equalsIgnoreCase("AUDIOBOOK")){
					AudioBook tempb = (AudioBook) contents.get(titleMapInt.get(title));
					System.out.print(titleMapInt.get(title)+1+". ");
					tempb.printInfo();
				}
			}
			else{
				System.out.println("No matches for "+title);
			}
		}

		//Method to use hashmap correlating artist name and indexes of their works to return information about all content created by said artist.
		public void searchArtist(String artist){
			if (artistMapInt.containsKey(artist)){
				for (int i=0;i<artistMapInt.get(artist).size();i++){
					Song temps = (Song) contents.get(artistMapInt.get(artist).get(i));
					System.out.print(artistMapInt.get(artist).get(i)+1+". ");
					temps.printInfo();
					System.out.println("");
				}
			}
			else{
				System.out.println("No matches for "+artist);
			}
		}

		//Method to return indexes of content created by this artist
		public ArrayList<Integer> returnArtistInd(String artist){
			if (artistMapInt.containsKey(artist)){
				return artistMapInt.get(artist);
			}
			else{
				throw new ArtistNotFoundException();
			}
		}
		//Method to print songs of a certain genre with indexes using hashmap.
		public void searchGenre(String genre){
			if (genreMapInt.containsKey(genre)){
				for (int i=0;i<genreMapInt.get(genre).size();i++){
					Song temps = (Song) contents.get(genreMapInt.get(genre).get(i));
					System.out.print(genreMapInt.get(genre).get(i)+1+". ");
					temps.printInfo();
					System.out.println("");
				}
			}
			else{
				System.out.println("No matches for "+genre);
			}
		}

		//Method to return index of songs of a specific genre using hashmap.
		public ArrayList<Integer> returnGenreInd(String genre){
			if (genreMapInt.containsKey(genre)){
				return genreMapInt.get(genre);
			}
			else{
				throw new GenreNotFoundException();
			}
		}
		//Getter method for contents of a store
		public ArrayList<AudioContent> getContents(){
			return this.contents;
		}
		
		//Built in with skeleton code
		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		//Built in with skeleton code
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
		
		//Code from A1
		/* 
		private ArrayList<String> makeHPChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("The Riddle House");
			titles.add("The Scar");
			titles.add("The Invitation");
			titles.add("Back to The Burrow");
			return titles;
		}
		
		private ArrayList<String> makeHPChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("In which we learn of the mysterious murders\r\n"
					+ " in the Riddle House fifty years ago, \r\n"
					+ "how Frank Bryce was accused but released for lack of evidence, \r\n"
					+ "and how the Riddle House fell into disrepair. ");
			chapters.add("In which Harry awakens from a bad dream, \r\n"
					+ "his scar burning, we recap Harry's previous adventures, \r\n"
					+ "and he writes a letter to his godfather.");
			chapters.add("In which Dudley and the rest of the Dursleys are on a diet,\r\n"
					+ " and the Dursleys get letter from Mrs. Weasley inviting Harry to stay\r\n"
					+ " with her family and attend the World Quidditch Cup finals.");
			chapters.add("In which Harry awaits the arrival of the Weasleys, \r\n"
					+ "who come by Floo Powder and get trapped in the blocked-off fireplace\r\n"
					+ ", blast it open, send Fred and George after Harry's trunk,\r\n"
					+ " then Floo back to the Burrow. Just as Harry is about to leave, \r\n"
					+ "Dudley eats a magical toffee dropped by Fred and grows a huge purple tongue. ");
			return chapters;
		}
		
		private ArrayList<String> makeMDChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Loomings.");
			titles.add("The Carpet-Bag.");
			titles.add("The Spouter-Inn.");
			return titles;
		}
		private ArrayList<String> makeMDChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("Call me Ishmael. Some years ago never mind how long precisely having little\r\n"
					+ " or no money in my purse, and nothing particular to interest me on shore,\r\n"
					+ " I thought I would sail about a little and see the watery part of the world.");
			chapters.add("stuffed a shirt or two into my old carpet-bag, tucked it under my arm, \r\n"
					+ "and started for Cape Horn and the Pacific. Quitting the good city of old Manhatto, \r\n"
					+ "I duly arrived in New Bedford. It was a Saturday night in December.");
			chapters.add("Entering that gable-ended Spouter-Inn, you found yourself in a wide, \r\n"
					+ "low, straggling entry with old-fashioned wainscots, \r\n"
					+ "reminding one of the bulwarks of some condemned old craft.");
			return chapters;
		}
		
		private ArrayList<String> makeSHChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Prologue");
			titles.add("Chapter 1");
			titles.add("Chapter 2");
			titles.add("Chapter 3");
			return titles;
		}
		
		private ArrayList<String> makeSHChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("The gale tore at him and he felt its bite deep within\r\n"
					+ "and he knew that if they did not make landfall in three days they would all be dead");
			chapters.add("Blackthorne was suddenly awake. For a moment he thought he was dreaming\r\n"
					+ "because he was ashore and the room unbelieveable");
			chapters.add("The daimyo, Kasigi Yabu, Lord of Izu, wants to know who you are,\r\n"
					+ "where you come from, how ou got here, and what acts of piracy you have committed.");
			chapters.add("Yabu lay in the hot bath, more content, more confident than he had ever been in his life.");
			return chapters;
		}
		
		// Podcast Seasons
		//Change code to use the add methods in season, leave the input alone
		private ArrayList<Season> makeSeasons()
		{
			ArrayList<Season> seasons = new ArrayList<Season>();
		  Season s1 = new Season();
		  s1.addTitle("Bay Blanket");
		  s1.addTitle("You Don't Want to Sleep Here");
		  s1.addTitle("The Gold Rush");
		  s1.addFile("The Bay Blanket. These warm blankets are as iconic as Mariah Carey's \r\n"
		  		+ "lip-syncing, but some people believe they were used to spread\r\n"
		  		+ " smallpox and decimate entire Indigenous communities. \r\n"
		  		+ "We dive into the history of The Hudson's Bay Company and unpack the\r\n"
		  		+ " very complicated story of the iconic striped blanket.");
		  s1.addFile("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.addFile("here is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.addLength(31);
		  s1.addLength(32);
		  s1.addLength(45);
		  seasons.add(s1);
		  Season s2 = new Season();
		  s2.addTitle("Toronto vs Everyone");
		  s2.addTitle("Water");
		  s2.addFile("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s2.addFile("Can the foundation of Canada be traced back to Indigenous trade routes?\r\n"
		  		+ " In this episode Falen and Leah take a trip across the Great Lakes, they talk corn\r\n"
		  		+ " and vampires, and discuss some big concerns currently facing Canada's water."); 
		  s2.addLength(45);
		  s2.addLength(50);
		 
		  seasons.add(s2);
		  return seasons;
		}
		*/
}

//Custom exception for if artist is not found
class ArtistNotFoundException extends RuntimeException{
	public ArtistNotFoundException(){
		super("Artist not found");
	}
}

//Custom exception for if genre is not found.
class GenreNotFoundException extends RuntimeException{
	public GenreNotFoundException(){
		super("Genre not found");
	}
}
