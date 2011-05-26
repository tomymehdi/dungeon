package loadAndSave;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import parser.BoardParser;
import parser.CorruptedFileException;

public class LoadGame extends BoardParser {
	
	BufferedReader inputBoard;
	
	public LoadGame(File placeToLoad) {
		super(placeToLoad);
		try {
			inputBoard = new BufferedReader(new FileReader(placeToLoad));
			fileParser();
		} catch (IOException e) {
			// TODO NO OLVIDAR AGARRARLA DSPS
			throw new CorruptedFileException();
		}
	}

	private void load() throws IOException {
		boolean dimensionFlag = false;
		boolean nameFlag = false;
		boolean playerFlag = false;
		String line;
		
		while ((line = inputBoard.readLine()) != null) {
			
			line = line.replace(" ", "").replace("\t", "").replace("\n", "")
			.split("#")[0];
			
			
		}
		
	}

}
