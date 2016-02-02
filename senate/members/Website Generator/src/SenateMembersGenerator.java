import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class SenateMembersGenerator {

	
	public static void main(String[] args){
		File template = new File("data/template.txt");
		File result = new File("index.html");
		Scanner input;
		try {
			input = new Scanner(template);
		} catch (FileNotFoundException e) {
			System.err.println("HTML TEMPLATE NOT FOUND.");
			return;
		}
		PrintWriter output;
		try {
			output = new PrintWriter(result);
		} catch (FileNotFoundException e) {
			System.err.println("ERROR WITH THE INDEX.HTML OUTPUT FILE.");
			input.close();
			return;
		}
		
		while (input.hasNextLine()){
			String line = input.nextLine();
			if (line.contains("[PROFILE_BOXES]")){
				output.println(getAllProfileBoxes());
			} else {
				output.println(line);
			}
		}
		input.close();
		output.close();	
	}
	
	public static String getAllProfileBoxes(){
		Scanner identifiers;
		try {
			identifiers = new Scanner(new File("data/identifiers.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("IDENTIFIERS DOCUMENT NOT FOUND.");
			return null;
		}
		
		String result = "";
		while (identifiers.hasNextLine()){
			result += getProfileBox(identifiers.nextLine());
		}
		identifiers.close();
		return result;
	}
	
	public static String getProfileBox(String identifier){
		File f = new File("data/profiles/"+identifier+".txt");
		Scanner scan;
		try {
			scan = new Scanner(f);
		} catch (FileNotFoundException e1) {
			System.err.println("PROFILE WITH IDENTIFIER ["+identifier+"] NOT FOUND.");
			return null;
		}
		String title = scan.nextLine();
		String job = scan.nextLine();
		String name = scan.nextLine();
		String email = scan.nextLine();
		String personal = scan.nextLine();
		scan.close();
		
		File g = new File("data/profile-box-template.txt");
		String result = "";
		try {
			scan = new Scanner(g);
		} catch (FileNotFoundException e) {
			System.err.println("PROFILE BOX TEMPLATE NOT FOUND.");
		}
		while (scan.hasNextLine()){
			String line = scan.nextLine();
			if (line.contains("[NAME]")){
				line = line.replace("[NAME]", name);
			}
			if (line.contains("[IDENTIFIER]")){
				line = line.replace("[IDENTIFIER]", identifier);
			}
			if (line.contains("[TITLE]")){
				line = line.replace("[TITLE]", title);
			}
			if (line.contains("[EMAIL]")){
				line = line.replace("[EMAIL]", email);
			}
			if (line.contains("[PERSONAL_STATEMENT]")){
				line = line.replace("[PERSONAL_STATEMENT]", personal);
			}
			if (line.contains("[JOB_DESCRIPTION]")){
				line = line.replace("[JOB_DESCRIPTION]", job);
			}
			result += "\n" + line;
		}
		scan.close();
		return result;
	}
	
}
