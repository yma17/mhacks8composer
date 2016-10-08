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
	    //change harmonic structure? If so, alter the themeChords by employing diatonic substitution.
		//modulate to a minor? If so, call convertToMinorChords.
		double d = Math.random(); //det. change in harm. str.
		boolean major = (Math.random() < 0.7); //det. major or minor
		if(d < 0.7) {
			if(e < 0.7)
				variationChords = this.alterChordsInMajorKey(themeChords); //Major
			else {
				variationChords = this.convertToMinorChords(themeChords); //minor
				variationChords = this.alterChordsInMinorKey(variationChords);
			}
		}
		else {
			if(e < 0.3)
				variationChords = this.convertToMinorChords(themeChords); //minor
		}
		
		//change meter? If so, reposition the chords.
		
		//first note
		if(major)
			themePhrase.addNote(new Note());
		
		
		//loop between deciding the following note and rhythmic patterns in between
		for(int i = 0; i < variationChords.length; i++) {
			for(int j = 0; j < variationChords[0].length; j++) {
				
			}
		}
		
		//variation now completed
	}
	
	//decide the following note. Executes immediately after the previous note is composed.
	public Note decideNextNote(Note previous,int[] chordTones,int tempo,boolean major) {
		//previous = previous note.
		//chordTones = the chord tones, designated by distance by half steps from tonic upwards
		
		ArrayList<Note> options = new ArrayList<Note>(); //list of possible options for the next note
		
		int[] diatonicIntervals = {0,2,4,5,7,9,11}; //distance from each note to tonic in half steps [C Major scale - default]
		if(!major) { //A minor scale - change some numbers
			diatonicIntervals[2] = 3;
			diatonicIntervals[5] = 8;
			diatonicIntervals[6] = 10;
		}
	    
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
	
	//alter chords in variation using diatonic substitution. (for Major keys)
	public String[][] alterChordsInMajorKey(String[][] themePitches) { //for C Major.
		//IV can be subst. with ii.
		//V7 can be subst. with V.
		//I (not first or last note) can be subst. iii or vi.
		String[][] varChords = themePitches;
		
		for(int i = 0; i < themePitches.length; i++) {
			for(int j = 0; j < themePitches[0].length; j++) {
				String thisChord = themePitches[i][j];
				if(!thisChord.equals("N")) { //if there is in fact a chord change
					double d = Math.random();
					if(thisChord.equals("IV")) {
						if(d < 0.4)
							varChords[i][j] = "ii";
					}
					else if(thisChord.equals("V7")) {
						if(d < 0.4)
							varChords[i][j] = "V";
					}
					else if(thisChord.equals("I")) {
						if(!(i == 0 && j == 0) && !(i == themePitches.length-1 && j == themePitches[0].length-1)) { //does not apply to first and last note
							if(d < 0.3)
								varChords[i][j] = "iii";
							else if(d < 0.6)
								varChords[i][j] = "vi";
						}
					}
				}
			}
		}
		
		return varChords;
	}
	
	//convert chords to a minor key
	public String[][] convertToMinorChords(String[][] themePitches) { //for A minor.
		//Mandatory conversions: I to i, IV to iv, and V7 remains the same.
		//from there:
		//i to vi (not first/last note)
		//iv to dim ii or VI
		//V7 to V or dim vii
		String[][] varChords = themePitches;
		
		for(int i = 0; i < themePitches.length; i++) {
			for(int j = 0; j < themePitches[0].length; j++) {
				String thisChord = themePitches[i][j];
				
				//mandatory conversions
				if(thisChord.equals("I"))
					varChords[i][j] = "i";
				else if(thisChord.equals("IV"))
					varChords[i][j] = "iv";
			}
		}
		
		return varChords;
	}
	
	//alter chords using diatonic substitution (with minor keys)
	public String[][] alterChordsInMinorKey(String[][] converted) {
		//precondition: converted is a double array of chords that had just underwent the convertToMinorChords method.
		
        String[][] varChords = converted;
		
		for(int i = 0; i < converted.length; i++) {
			for(int j = 0; j < converted[0].length; j++) {
				String thisChord = converted[i][j];

				//substitutions
				if(!thisChord.equals("N")) { //if there is in fact a chord change
					double d = Math.random();
					if(thisChord.equals("i")) {
						if(!(i == 0 && j == 0) && !(i == converted.length-1 && j == converted[0].length-1)) { //does not apply to first and last note
							if(d < 0.4)
								varChords[i][j] = "VI";
						}
					}
					else if(thisChord.equals("iv")) {
						if(d < 0.3)
							varChords[i][j] = "dim ii";
						else if(d < 0.6)
							varChords[i][j] = "VI";
					}
					else if(thisChord.equals("V7")) {
						if(d < 0.3)
							varChords[i][j] = "V";
						else if(d < 0.6)
							varChords[i][j] = "dim vii";
					}
				}
			}
		}
        return varChords;
	}
	
	//determine rhythmic patterns between chord changes
	public String chooseRhythms(int interval,int length) {
		
		
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
	public String[][] getThemeChords() { return themeChords; };
}
