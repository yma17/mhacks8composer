package testing;

import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.audio.*;
import composer.Chord;
import composer.Composer;
import inst.SawtoothInst;

public class ComposerTester implements JMC {

	public static void main(String[] args) {
		/*
		Score score = new Score(new Part(new Phrase(new Note(C4, MINIM))));
		Write.midi(score);
		Instrument inst = new SawtoothInst(44100);
		Write.au(score, inst);
		*/
		
		/*
		Composer c = new Composer();
		Chord[] altered = c.convertToMinorChords(c.getThemeChords());
		altered = c.alterChordsInMinorKey(altered);
		altered = c.redistributeLengths(c.getThemeChords(),3);
		for(int i = 0; i < altered.length; i++) {
			System.out.print(altered[i].getLength() + "\n");
		}
		*/
		
		
	}

}
