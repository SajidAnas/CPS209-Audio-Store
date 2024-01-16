//Name: Anas Sajid
//Student Number: 501167312
//Import arraylist
import java.util.ArrayList;
//Make a Season class
public class Season{
    //Instance fields are 2 string arraylists (episodeFiles and episodeTitles), and one integer arraylist (episodeLengths)
    private ArrayList<String> episodeFiles;
    private ArrayList<String> episodeTitles;
    private ArrayList<Integer> episodeLengths;

    //Generic constructor for a season with no parameters, initializes all 3 instance fields as new arraylists
    public Season(){
        this.episodeFiles=new ArrayList<String>();
        this.episodeTitles=new ArrayList<String>();
        this.episodeLengths=new ArrayList<Integer>();
    }
    //Getter for episodeTitles
    public ArrayList<String> getTitles(){
        return episodeTitles;
    }
    //Getter for episodeFiles
    public ArrayList<String> getFiles(){
        return episodeFiles;
    }
    //Method to add to episodeFiles
    public void addFile(String file){
        this.episodeFiles.add(file);
    }
    //Method to add to episodeTitles
    public void addTitle(String title){
        this.episodeTitles.add(title);
    }
    //Method to add to episodeLengths
    public void addLength(Integer length){
        this.episodeLengths.add(length);
    }
}
