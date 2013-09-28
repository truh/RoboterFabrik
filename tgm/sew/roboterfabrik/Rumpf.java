package tgm.sew.roboterfabrik;
/**
 * Ein Bauteil des Threadee
 */
public class Rumpf implements Teil {

	/** @see Teil#zahlen */
	public int [] zahlen;

	/**
	 * Füllt zahlen mit Zufallszahlen
	 */
	public Rumpf() {
        this.zahlen = new int[20];
        for(int i = 0;i<20;i++){
            zahlen[i] =(int)(Math.random() * (999 - 1+1)+1);
        }
	}

    @Override
    public String toCSV() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
