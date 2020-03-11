package week6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Problem Set 6
 *
 * @author Josh Ciffer
 * @version 03/05/2020
 */
@SuppressWarnings("javadoc")
public class AllHomophones {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		String word = userInput.next().toUpperCase(), pronunciation = "";
		UALDictionary<String, Pronunciation> homophones = new UALDictionary<String, Pronunciation>();
		File file = new File("D:\\Programming\\Projects\\CS114 - Intro to CS II\\src\\week6\\cmudict.0.7a.txt"); // "../resource/asnlib/publicdata/cmudict.0.7a.txt"

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {		// Locates target word in dictionary text file
				String line = scanner.nextLine();
				if (line.substring(0, 3).equals(";;;"))
					continue; // skip comment lines
				Pronunciation p = new Pronunciation(line);
				if (p.getWord().toUpperCase().equals(word)) {
					pronunciation = p.getPhonemes().toUpperCase();
				}
			}
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {		// Searches for words that match the pronunciation of the target word
				String line = scanner.nextLine();
				if (line.substring(0, 3).equals(";;;"))
					continue;
				Pronunciation p = new Pronunciation(line);
				if (p.getPhonemes().toUpperCase().equals(pronunciation)) {
					homophones.insert(p.getWord().toUpperCase(), p);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (Pronunciation p : homophones.values()) {
			System.out.println(p.getWord().toUpperCase());
		}
		userInput.close();
	}

}