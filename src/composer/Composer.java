package composer;

import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import java.util.ArrayList;

public class Composer implements JMC {
	//variables rel. to original theme - Twinkle Twinkle Little Star
	public Chord[] themeChords = {new Chord("I",2),
									new Chord("N",2),
									new Chord("IV",2),
									new Chord("I",2),
									new Chord("IV",2),
									new Chord("I",2),
									new Chord("V7",2),
									new Chord("I",2),
									new Chord("I",2),
									new Chord("IV",2),
									new Chord("V7",2),
									new Chord("I",2),
									new Chord("I",2),
									new Chord("IV",2),
									new Chord("V7",2),
									new Chord("I",2),
									new Chord("I",2),
									new Chord("N",2),
									new Chord("IV",2),
									new Chord("I",2),
									new Chord("IV",2),
									new Chord("I",2),
									new Chord("V7",2),
									new Chord("I",2)}; //list of chords in the original theme
	
	public Phrase theme = new Phrase(0.0);
			
	//variables rel. to variation of the original theme
	public Part clarinetPart = new Part("Clarinet");
	public Phrase varPhrase = new Phrase(0.0);
	public Score score = new Score(clarinetPart,"Variation",108);
	
	
	public Composer() {
		//code to complete original theme
		int[] themePitches = {C4,C4,G4,G4,A4,A4,G4,F4,F4,E4,E4,D4,D4,C4,G4,G4,F4,F4,E4,E4,D4,G4,G4,F4,F4,E4,E4,D4,C4,C4,G4,G4,A4,A4,G4,F4,F4,E4,E4,D4,D4,C4};
		double[] rhythmPitches = {1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2};
		theme.addNoteList(themePitches, rhythmPitches);
		
		//code to begin building variation
		clarinetPart.add(varPhrase);	
	}
	
	public void composeVariation() {
		Chord[] variationChords = themeChords;
	    //change harmonic structure? If so, alter the themeChords by employing diatonic substitution.
		//modulate to a minor? If so, call convertToMinorChords.
		double d = Math.random(); //det. change in harm. str.
		boolean major = (Math.random() < 0.7); //det. major or minor
		if(d < 0.7) {
			if(major)
				variationChords = this.alterChordsInMajorKey(themeChords); //Major
			else {
				variationChords = this.convertToMinorChords(themeChords); //minor
				variationChords = this.alterChordsInMinorKey(variationChords);
			}
		}
		else {
			if(!major)
				variationChords = this.convertToMinorChords(themeChords); //minor
		}
		
		//change meter? If so, reposition the chords.
		double e = Math.random();
		if(d < 0.25) // to 2/4
			variationChords = this.redistributeLengths(themeChords, 2);
		else if(d < 0.5) //to 3/4
			variationChords = this.redistributeLengths(themeChords, 3);
		
		//change tempo?
		double f = Math.random();
		if(d < 0.3) //slow down
			varPhrase.setTempo(60); //to Adagio
		else if(d < 0.6) //speed up
			varPhrase.setTempo(132); //to Allegro
		
		//compose from first note to first chord change
		Chord firstChord = variationChords[0];
		Chord secondChord = variationChords[1];
		Note first = this.decideFirstNote(major);
		Note second = this.decideNextNote(first, this.getChordTones(secondChord.getName(), major), varPhrase.getTempo(), major);
		
		//loop between deciding the following note and rhythmic patterns in between
		for(int i = 1; i < variationChords.length-1; i++) {
			Chord former = variationChords[i];
			Chord latter = variationChords[i+1];
		}
		
		//code to be completed
	}
	
	public int[] getChordTones(String chordName,boolean major) {
		if(chordName.equals("V7")) {
			int[] chordTones = {2,5,7,11};
			return chordTones;
		}
		else if(chordName.equals("V")) {
			int[] chordTones = {2,7,11};
			return chordTones;
		}
		else if(chordName.equals("vi")) {
			int[] chordTones = {0,4,9};
			return chordTones;
		}
		
		if(major) {
			if(chordName.equals("I")) {
				int[] chordTones = {0,4,7};
				return chordTones;
			}
			else if(chordName.equals("IV")) {
				int[] chordTones = {0,5,9};
				return chordTones;
			}
			else if(chordName.equals("iii")) {
				int[] chordTones = {4,7,11};
				return chordTones;
			}
			else { //chordName.equals("ii"))
				int[] chordTones = {2,5,9};
				return chordTones;
			}
		}
		else { //minor
            if(chordName.equals("i")) {
            	int[] chordTones = {0,3,7};
            	return chordTones;
            }
            else if(chordName.equals("iv")) {
				int[] chordTones = {0,5,8};
				return chordTones;
			}
            else if(chordName.equals("VI")) {
				int[] chordTones = {0,3,8};
				return chordTones;
			}
            else if(chordName.equals("dim ii")) {
				int[] chordTones = {2,5,8};
				return chordTones;
			}
            else { //chordName.equals("dim vii"))
				int[] chordTones = {2,5,11};
				return chordTones;
			}
		}
	}

	//decide first note.
	public Note decideFirstNote(boolean major) {
		ArrayList<Note> options = new ArrayList<Note>();
		//for now, rhythm is 1.0 to satisfy constructor. Will alter later.
		if(major) {
			options.add(new Note(C3,1.0));
			options.add(new Note(E3,1.0));
			options.add(new Note(G3,1.0));
			options.add(new Note(C4,1.0));
			options.add(new Note(E4,1.0));
			options.add(new Note(G4,1.0));
			options.add(new Note(C5,1.0));
		}
		else { //minor
			options.add(new Note(A2,1.0));
			options.add(new Note(C3,1.0));
			options.add(new Note(E3,1.0));
			options.add(new Note(A3,1.0));
			options.add(new Note(C4,1.0));
			options.add(new Note(E4,1.0));
			options.add(new Note(A4,1.0));
		}
		
		//randomly picks a note from the list.
		int i = (int)(Math.random()*options.size());
		return options.get(i);
	}
	
	//decide the following note. Executes immediately after the previous note is composed.
	public Note decideNextNote(Note previous,int[] chordTones,double tempo,boolean major) {
		//previous = previous note. if first note is being composed, previous will be null.
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
	
	//reposition, redistribute lengths of chords to fit a new meter
	public Chord[] redistributeLengths(Chord[] original,int newBPM) {
		Chord[] redistributed = original;
		
		for(int i = 0; i < original.length; i += 2) {
			//precondition: firstLength+secondLength = 4, original.size() % 2 = 0.
			double firstLength = original[i].getLength();
			double secondLength = original[i+1].getLength();
			double d = Math.random();
			if(newBPM == 2) {
				if(d < 0.25) {
					firstLength = 0.5;
					secondLength = 1.5;
				}
				else if(d < 0.50) {
					firstLength = 1.5;
					secondLength = 0.5;
				}
				else {
					firstLength = 1;
					secondLength = 1;
				}
			}
			else { //newBPM == 3
				if(d < 0.4) {
					firstLength = 1;
					secondLength = 2;
				}
				else if(d < 0.8) {
					firstLength = 2;
					secondLength = 1;
				}
				else {
					firstLength = 1.5;
					secondLength = 1.5;
				}
			}
			
			//alter the lengths of the two chords
			original[i].setLength(firstLength);
			original[i+1].setLength(secondLength);
		}
		
		return redistributed;
	}
	
	//alter chords in variation using diatonic substitution. (for Major keys)
	public Chord[] alterChordsInMajorKey(Chord[] themePitches) { //for C Major.
		//IV can be subst. with ii.
		//V7 can be subst. with V.
		//I (not first or last note) can be subst. iii or vi.
		Chord[] varChords = themePitches;
		
		for(int i = 0; i < themePitches.length; i++) {
			String thisChord = themePitches[i].getName();
			if(!thisChord.equals("N")) { //if there is in fact a chord change
				double d = Math.random();
				if(thisChord.equals("IV")) {
					if(d < 0.4)
						varChords[i].setName("ii");
				}
				else if(thisChord.equals("V7")) {
					if(d < 0.4)
						varChords[i].setName("V");
				}
				else if(thisChord.equals("I")) {
					if(!(i == 0) && !(i == themePitches.length-1)) { //does not apply to first and last note
						if(d < 0.3)
							varChords[i].setName("iii");
						else if(d < 0.6)
							varChords[i].setName("vi");
					}
				}
			}
		}
		
		return varChords;
	}
	
	//convert chords to a minor key
	public Chord[] convertToMinorChords(Chord[] themePitches) { //for A minor.
		//Mandatory conversions: I to i, IV to iv, and V7 remains the same.
		//from there:
		//i to vi (not first/last note)
		//iv to dim ii or VI
		//V7 to V or dim vii
		Chord[] varChords = themePitches;
		
		for(int i = 0; i < themePitches.length; i++) {
			String thisChord = themePitches[i].getName();
			
			//mandatory conversions
			if(thisChord.equals("I"))
				varChords[i].setName("i");
			else if(thisChord.equals("IV"))
				varChords[i].setName("iv");
		}
		
		return varChords;
	}
	
	//alter chords using diatonic substitution (with minor keys)
	public Chord[] alterChordsInMinorKey(Chord[] converted) {
		//precondition: converted is a double array of chords that had just underwent the convertToMinorChords method.
		
        Chord[] varChords = converted;
		
		for(int i = 0; i < converted.length; i++) {
			String thisChord = converted[i].getName();
			
			//substitutions
			if(!thisChord.equals("N")) { //if there is in fact a chord change
				double d = Math.random();
				if(thisChord.equals("i")) {
					if(!(i == 0) && !(i == converted.length-1)) { //does not apply to first and last note
						if(d < 0.4)
							varChords[i].setName("VI");;
					}
				}
				else if(thisChord.equals("iv")) {
					if(d < 0.3)
						varChords[i].setName("dim ii");
					else if(d < 0.6)
						varChords[i].setName("VI");
				}
				else if(thisChord.equals("V7")) {
					if(d < 0.3)
						varChords[i].setName("V");
					else if(d < 0.6)
						varChords[i].setName("dim vii");
				}
			}
		}
        return varChords;
	}
	
	//determine rhythmic patterns between chord changes
	public void composeRhythms(Note first,Note second,double length) {
		//interval: difference between two notes in half steps.
		//length: how many beats?
		
		if(length)
		/*

		(1.5)
		dotted quarter
		three eighth notes
		two sixteenths, one eighth, two sixteenths

		rest + quarter, two eighths, three triplets, four sixteenths, etc. (+vice versa)

		(2)

		combination of any two listed under (1)
		half note
		two quarter notes
		four eighth notes
		eight sixteenth notes
		dotted quarter and eighth (+vice versa)

		(3)

		combination of any three listed under (1)
		combination of any two listed under (1.5)
		dotted half
		half note + quarter note (+vice versa)
		*/
		
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
	
	//composes the rhythm between two chords if length is 1 beat.
	public void composeOneBeat(Note first,Note second) {
		//there exist many options for rhythms, all indicated by the comments.
		//note: all notes below indicated are set initially to C4, as the pitch-deciding algorithm has not been implemented yet.
		double d = Math.random();
		if(d < 0.2) { //quarter note
			varPhrase.add(first);
		}
		else if(d < 0.35) { //two eighth notes
			first.setRhythmValue(0.5);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.5));
		}
		else if(d < 0.425) { //three triplets
			first.setRhythmValue(1.0/3.0);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,1.0/3.0));
			varPhrase.add(new Note(C4,1.0/3.0));
		}
		else if(d < 0.5) { //four sixteenths
			first.setRhythmValue(0.25);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.25));
			varPhrase.add(new Note(C4,0.25));
			varPhrase.add(new Note(C4,0.25));
		}
		else if(d < 0.575) { //eighth + two sixteenths
			first.setRhythmValue(0.5);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.25));
			varPhrase.add(new Note(C4,0.25));
		}
		else if(d < 0.65) { //two sixteenths + eighth
			first.setRhythmValue(0.25);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.25));
			varPhrase.add(new Note(C4,0.5));
		}
		else if(d < 0.725) { //dotted eighth + sixteenth
			first.setRhythmValue(0.75);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.25));
		}
		else if(d < 0.8) { //vice versa
			first.setRhythmValue(0.25);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.75));
		}
		else if(d < 0.825) { //rest + eighth note
			varPhrase.add(new Rest(0.5));
			first.setRhythmValue(0.5);
			varPhrase.add(first);
		}
		else if(d < 0.85) { //vice versa
			first.setRhythmValue(0.5);
			varPhrase.add(first);
			varPhrase.add(new Rest(0.5));
		}
		else if(d < 0.875) { //rest + two sixteenths
			varPhrase.add(new Rest(0.5));
			first.setRhythmValue(0.25);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.25));
		}
		else if(d < 0.9) { //vice versa
			first.setRhythmValue(0.25);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.25));
			varPhrase.add(new Rest(0.5));
		}
		else if(d < 0.925) { //rest + three sixteenths
			varPhrase.add(new Rest(0.25));
			first.setRhythmValue(0.25);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.25));
			varPhrase.add(new Note(C4,0.25));
		}
		else if(d < 0.95) { //vice versa
			first.setRhythmValue(0.25);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,0.25));
			varPhrase.add(new Note(C4,0.25));
			varPhrase.add(new Rest(0.25));
		}
		else if(d < 0.975) { //rest + two triplets
			varPhrase.add(new Rest(1.0/3.0));
			first.setRhythmValue(1.0/3.0);
			varPhrase.add(first);
			varPhrase.add(new Note(C4,1.0/3.0));
		}
		else { //rest + dotted eighth
			varPhrase.add(new Rest(0.25));
			first.setRhythmValue(0.75);
			varPhrase.add(first);
		}
	}
	
	//composes the rhythm between two chords if length is 1 1/2 beats.
	public void composeOneAndHalfBeat(int interval) {
		//insert code here
	}
	
	public Chord[] getThemeChords() { return themeChords; };
}
