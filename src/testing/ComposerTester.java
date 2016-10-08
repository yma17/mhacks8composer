package testing;

import jm.JMC;
import jm.music.data.*;
import jm.util.*;
import jm.audio.*;
import inst.SawtoothInst;

public class ComposerTester implements JMC {

	public static void main(String[] args) {
		Score score = new Score(new Part(new Phrase(new Note(C4, MINIM))));
		Write.midi(score);
		Instrument inst = new SawtoothInst(44100);
		Write.au(score, inst);
	}

}
