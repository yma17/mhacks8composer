package composer;

public class Chord {
	public String name; //name of chord. note: "N" indicates no change of chord in unimportant locations within the music.
	public double length; //number of beats
	public Chord(String name, double length) {
		this.name = name;
		this.length = length;
	}
	public String getName() { return name; }
	public double getLength() { return length; }
	public void setName(String name) { this.name = name; }
	public void setLength(double length) { this.length = length; }
}
