package com.hemebiotech.analytics.SymptomReader;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Contains method to read symptoms list from a text file
 * 
 * @Since 23/03/2022
 * @Version 1.0
 * @Author Antoine
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;
	
	/**
	 * Class Constructor
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filepath) {
		this.filepath = filepath;
	}
	
	
	/*
	 * Returns a list of symptoms from a text file, duplicates are possible/probable
	 * 
	 * @return result - list of symptoms
	 */
	@Override
	public List<String> GetSymptoms() {

		List<String> result = null;
		Path path = (Paths.get(filepath));
		
		try {		
			if ((Files.exists(path)) && (Files.isReadable(path))) {
				// Read file into stream
				Stream<String> lines = Files.lines(path);
				//Get in result list all lines not empty
				result = lines.filter(s->s.matches("^(?!\s*$).+")). toList();
				lines.close();
			}else {
				System.out.println("File is not exist or not readable");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}


}