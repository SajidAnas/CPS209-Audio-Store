//Name: Anas Sajid
//Student Number: 501167312
import java.util.ArrayList;
public class Podcast extends AudioContent{
    public static final String TYPENAME =	"PODCAST";
    private String host;
    private ArrayList<Season> seasonlist;

    public Podcast(String title, int year, String id, String type, String audioFile, int length, String host, ArrayList<Season> seasons){
        super(title, year, id, type, audioFile, length);
        this.host=host;
        seasonlist=seasons;
    }

    //Series of getters for type, host, seasonlist, and podcast title
    public String getType(){
        return TYPENAME;
    }
    
    public String getHost(){
        return host;
    }
    public ArrayList<Season> getSeasons(){
        return seasonlist;
    }
    public String getTitle(){
        return super.getTitle();
    }
    //Play a specific episode of a season of an episode by setting the audioFile to the audioFile with the index of the episode from the season with the index of 'Season'
    public void play(int Season, int Episode){
        this.setAudioFile(this.getSeasons().get(Season-1).getFiles().get(Episode-1));
        super.play();
        System.out.println();
    }
    //Loop through every season and audioFile of a podcast and play them all
    public void play(){
        for (int i=0;i<this.getSeasons().size();i++){
            for (int j=0;j<this.getSeasons().get(i).getFiles().size();j++){
                this.play(i+1,j+1);
                System.out.println("\n");
            }
        }
    }
    //Method to print info for the podcast. Use printInfo from AudioContent but also prints host name and number of seasons.
    public void printInfo()
	{
		super.printInfo();
		System.out.println("Host: "+this.host);
        System.out.println("Seasons: "+this.getSeasons().size());
	}
}
