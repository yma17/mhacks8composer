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
		Note prev = new Note(C4,1.0);
		int[] chordTones = {2,7,11};
		int tempo = 60;
		Note next = c.decideNextNote(prev, chordTones, tempo);
		System.out.println(next.getNote());
		System.out.println(next.getPitch());
	}

}
