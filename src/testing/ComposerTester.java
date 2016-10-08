package testing;

import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.audio.*;
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
		
		Composer c = new Composer();
		String[][] altered = c.alterChords(c.getThemeChords());
		for(int i = 0; i < altered.length; i++) {
			for(int j = 0; j < altered[0].length; j++) {
				System.out.print(altered[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

}
