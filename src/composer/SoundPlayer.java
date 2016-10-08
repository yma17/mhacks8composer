package composer;

import jm.JMC;
import jm.music.data.*;
import jm.util.Play;

public class SoundPlayer implements JMC {
	public static void main(String[] args) {
		Phrase phrase = new Phrase(0.0)
        Play.midi(new Note(C4, QN));
    }
}
