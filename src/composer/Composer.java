package composer;

import jm.JMC;
import jm.music.data.*;
import jm.util.*;

public class Composer implements JMC{
	//information on original theme - Variations on Twinkle Twinkle Little Star
	public String[][] chords = {{"I","N"},
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
	public Phrase theme = new Phrase(0,0);
			
	public Part clarinetPart = new Part("Clarinet");
	

	public Phrase themePhrase = new Phrase(0.0);
	
	//variation on the original theme
	public Score score = new Score(clarinetPart,"variation",108);
	public Phrase phrase = new Phrase(0.0);
	public Composer() {
		clarinetPart.add(themePhrase);
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
