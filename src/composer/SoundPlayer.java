package composer;

import jm.JMC;
import jm.music.data.*;
import jm.util.Play;

public class SoundPlayer implements JMC {
	public static void main(String[] args) {
		Phrase phrase = new Phrase(0.0);
		int[] pitches = {C4,D4,E4,F4,G4};
		double[] rhythms = {1.0,2.0,3.0,4.0,5.0};
		phrase.addNoteList(pitches, rhythms);
		Note tonic = new Note(C4,1.0);
		phrase.addNote(tonic);
		
		phrase.setTempo(108);
		
        Play.midi(phrase);
    }
}
