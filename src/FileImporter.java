import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileImporter {

	private Map<String, List<String>> words = new HashMap<String, List<String>>();
	private String[] lineValuesArray;
	private ArrayList<String> jobNames = new ArrayList<String>();
	private ArrayList<String> lineArray = new ArrayList<String>();
	private String line = null;
	private String jobName;
	private File selectedFile;
	

	public FileImporter(File selectedFile) throws IOException{
		this.selectedFile = selectedFile;
		readFile();
	}

	public void readFile() throws IOException{
		//Get the file
		//File file = new File("C:\\Users\\naomi.owens\\Downloads\\USPresidents.csv");
		//Give the file to the reader
		BufferedReader bufRdr  = new BufferedReader(new FileReader(selectedFile));
		//Read through each line of the file
		while((line = bufRdr.readLine()) != null){
			//Store comma delimiter
			String delimiter = "\\,";
			//Delimit line and store values in an array
			lineValuesArray = line.split(delimiter);
			//store first value of line as jobName
			jobName = lineValuesArray[0]; 
			//add this jobName to the jobNames Array
			jobNames.add(jobName);
			//Create a list with the full list of line values
			List<String> line = new ArrayList<String>(Arrays.asList(lineValuesArray));
			//Remove the ID that is already the hashmap key
			line.remove(0);
			//add the key and the array to the hashmap
			words.put(jobName, line);
		}
		/*
		 * THIS BLOCK PRINTS THE KEYS AND VALUES IN THE HASHMAP
		 */
		for (String jobName: words.keySet()){
			String key =jobName.toString();
			String value = words.get(jobName).toString();
			System.out.println(key + ":" + value);

		} 
	}
	
	public Map<String, List<String>> getWords(){
		return words;
		
	}
}


