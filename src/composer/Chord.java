package composer;

public class Chord {
	public String name; //name of chord. note: "N" indicates no change of chord in unimportant locations within the music.
	public int length; //number of beats
	public Chord(String name, int length) {
		this.name = name;
		this.length = length;
	}
	public String getName() { return name; }
	public int getLength() { return length; }
	public void setName(String name) { this.name = name; }
	public void setLength(int length) { this.length = length; }
}
