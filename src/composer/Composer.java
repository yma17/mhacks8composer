package composer;

import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import java.util.ArrayList;

public class Composer implements JMC {
	//variables rel. to original theme - Twinkle Twinkle Little Star
	public String[][] themeChords = {{"I","N"},
			                    {"IV","I"},
								{"IV","I"},
								{"V7","I"},
								{"I","IV"},
								{"V7","I"},
								{"I","IV"},
								{"V7","I"},
								{"I","N"},
			                    {"IV","I"},
								{"IV","I"},
								{"V7","I"}}; //list of chords in the original theme; N represents no change, two per measure, 1 per two beats
	public Phrase theme = new Phrase(0.0);
			
	//variables rel. to variation of the original theme
	public Part clarinetPart = new Part("Clarinet");
	public Phrase themePhrase = new Phrase(0.0);
	public Score score = new Score(clarinetPart,"Variation",108);
	
	
	public Composer() {
		//code to complete original theme
		int[] themePitches = {C4,C4,G4,G4,A4,A4,G4,F4,F4,E4,E4,D4,D4,C4,G4,G4,F4,F4,E4,E4,D4,G4,G4,F4,F4,E4,E4,D4,C4,C4,G4,G4,A4,A4,G4,F4,F4,E4,E4,D4,D4,C4};
		double[] rhythmPitches = {1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2};
		theme.addNoteList(themePitches, rhythmPitches);
		
		//code to begin building variation
		clarinetPart.add(themePhrase);	
	}
	
	public void composeVariation() {
		String[][] variationChords = themeChords;
	    //change rhythm? If also, alter the themeChords by employing diatonic substitution.
		double d = Math.random();
		if(d < 0.5)
			variationChords = this.alterChords(themeChords);
		
		//loop between deciding the following note and rhythmic patterns in between
		for(int i = 0; i < variationChords.length-1; i++) {
			
		}
		
		//variation now completed
	}
	
	//decide the following note. Executes immediately after the previous note is composed.
	public int decideNextNote(int previousPitch) {
		
	}
	
	//alter chords in variation
	public String[][] alterChords(String[][] themePitches) {
	
	}
	
	//determine rhythmic patterns between chord changes
	public String chooseRhythms() {
		
	}
	
	/*
	public static void main(String[] args){

		//create a middle C minim (half note)

		Note n = new Note(C4,MINIM);

		//create a phrase

		Phrase phr = new Phrase();

		//put the note inside the phrase

		phr.addNote(n);

		//pack the phrase into a part

		Part p = new Part();

		p.addPhrase(phr);

		//pack the part into a score titled 'Bing'

		Score s = new Score("Bing");

		s.addPart(p);

		//write the score as a MIDI file to disk

		Write.midi(s,"Bing.mid");

		}
	 */
}
