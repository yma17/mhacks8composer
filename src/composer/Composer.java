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
			//variationChords = this.alterChords(themeChords);
		
		//loop between deciding the following note and rhythmic patterns in between
		for(int i = 0; i < variationChords.length-1; i++) {
			
		}
		
		//variation now completed
	}
	
	//decide the following note. Executes immediately after the previous note is composed.
	public Note decideNextNote(Note previous,int[] chordTones,int tempo) {
		//previous = previous note.
		//chordTones = the chord tones, designated by distance by half steps from tonic upwards
		
		ArrayList<Note> options = new ArrayList<Note>(); //list of possible options for the next note
		
		int[] diatonicIntervals = {0,2,4,5,7,9,11}; //C Major scale: distance from each note to tonic in half steps
	    
		ArrayList<Integer> matchingIndices = new ArrayList<Integer>(); //stores values of diatonicIntervals whose note names match those specified in chordTones
		for(int i = 0; i < diatonicIntervals.length; i++) {
			for(int j = 0; j < chordTones.length; j++) {
				if(diatonicIntervals[i] == chordTones[j])
					matchingIndices.add(diatonicIntervals[i]);
			}
		}
		
		for(int i = previous.getPitch() - 24; i <= previous.getPitch() + 24; i += 12) { //add to options all chord tones from two octaves below the previous pitch to two octaves above.
			for(Integer integer : matchingIndices) {
				//length of note 1.0 to satisfy constructor. Can alter later as needed.
				Note proposed = new Note(i + integer,1.0);
				options.add(proposed);
			}
		}
		
		for(int i = 0; i < options.size(); i++) { 
			//next step: the "filter". 
			//Notes that lead to larger intervals are more likely to be removed, to reduce stress and awkwardness in the music.

			double d; //d will be a value between 0 and 1
			if(tempo >= 108) { //faster sections
				//downward facing qudratic function (parabola)
				//stronger preference towards smaller intervals
				d = -(Math.pow(previous.getPitch()-options.get(i).getPitch(),2))/200 + 1;
			}
			else { //tempo < 108 - slower sections
				//downward facing absolute value function
				//linear correlation between d and size of interval
				d = -(Math.abs(previous.getPitch()-options.get(i).getPitch())/14.5) + 1;
			}
			
			double e = Math.random();
			
			//executes if options.size() = 1, to ensure that there is a single note to compose in the next step
			if(options.size() == 1) {
				return options.get(0);
			}
			
			if(d <= e) { //compare d to e
				options.remove(options.get(i));
				i--;
			}
		}
		
		//randomly picks a note from the remaining list.
		int f = (int)(Math.random()*options.size());
		return options.get(f);
	}
	
	//alter chords in variation
	//public String[][] alterChords(String[][] themePitches) {
	
	//}
	
	//determine rhythmic patterns between chord changes
	//public String chooseRhythms() {
		
	//}
	
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
