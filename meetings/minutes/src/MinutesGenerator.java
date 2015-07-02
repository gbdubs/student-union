import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MinutesGenerator {

	public static void main(String[] args){
		File input = new File("data/template.txt");
		Scanner scan;
		try {
			scan = new Scanner(input);
		} catch (FileNotFoundException e1) {
			System.err.println("GENERAL HTML TEMPLATE NOT FOUND");
			return;
		}
		
		File output = new File("index.html");
		try {
			PrintWriter pw = new PrintWriter(output);
			
			while (scan.hasNextLine()){
				String line = scan.nextLine();
				if (line.contains("[MINUTES]")){
					pw.println(getAllLinksAndHeadings());
				} else {
					pw.println(line);
				}
			}
			scan.close();
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR IN PRINTING INDEX.HTML");
		}
	}
	
	public static String getAllLinksAndHeadings(){
		File headings = new File("data/headings.txt");
		Scanner headingInput;
		try {
			headingInput = new Scanner(headings);
		} catch (FileNotFoundException e) {
			System.out.println("HEADINGS FILE NOT FOUND.");
			return null;
		}
		
		String result = "";
		while (headingInput.hasNextLine()){
			String heading = headingInput.nextLine();
			result += "\n" + createHeadingAndFiles(heading);
		}
		headingInput.close();
		return result;
	}
	
	public static String createHeadingAndFiles(String heading){
		File template = new File("data/headings-template.txt");
		Scanner scan;
		try {
			scan = new Scanner(template);
		} catch (FileNotFoundException e) {
			System.err.println("HEADINGS TEMPLATE NOT FOUND.");
			return null;
		}
		String result = "";
		
		
		while (scan.hasNextLine()){
			String line = scan.nextLine();
			if (line.contains("[HEADING]")){
				line = line.replace("[HEADING]", heading);
			}
			if (line.contains("[URL]")){
				String fileTemplate = line;
				for (String name : getAllFilePathsInFolder("data/" + heading)){
					result += "\n" + fileTemplate.replace("[URL]", heading + "/" + name).replace("[TITLE]", name);
				}
			} else {
				result += "\n" + line;
			}
		}
		scan.close();
		return result;
	}
	
	public static List<String> getAllFilePathsInFolder(String folderName){	
		
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();

		List<String> paths = new ArrayList<String>();
		
	    for (int i = 0; i < listOfFiles.length; i++) {
	        if (listOfFiles[i].isFile()) {
	        	if (!listOfFiles[i].isHidden()){
	        		paths.add(listOfFiles[i].getName());
	        	}
	        }
	    }
	    
	    return paths;
	}
}
