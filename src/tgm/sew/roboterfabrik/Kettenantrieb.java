package tgm.sew.roboterfabrik;
/**
 * Ein Bauteil des Threadee
 */
public class Kettenantrieb implements Teil {

	public String name;
	private int [] zahlen;

	/**
	 * Fuellt zahlen mit Zufallszahlen
	 */
	public Kettenantrieb() {
		name = "Kettenantrieb";
        this.zahlen = new int[20];
        for(int i = 0;i<20;i++){
            zahlen[i] =(int)(Math.random() * (999 - 1+1)+1);
        }
	}
	/**
	 * Wandelt alle Inhalte des Threadee in ein String um
	 */
    @Override
    public String toCSV() {
    	StringBuilder s = new StringBuilder(53);//Min anzahl der Zeichen im Stringbuilder(Kettenantrieb = 13 Zeiche zahlen = 20 beistriche = 20
    	s.append("Kettenantrieb");
    	for(int i = 0;i<20;i++){
    		s.append(",");
    		s.append(zahlen[i]);
    	}
    	
        return s.toString();
    }
    /**
     * Fuegt die Zahlen im String von toCSV in das Attribut zahlen[];
     */
    @Override
    public void fromCSV(String csv){
    	String[] items = csv.replaceAll("Kettenantrieb,","").split(",");// loescht das Wort raus und trennt mit den "," die Zahlen im String
    	for(int i = 0 ;i<zahlen.length;i++){
    		zahlen[i]=Integer.parseInt(items[i]);
    	}
    }

    /**
     * Zuffalszahlen um dem Montagemitarbeiter Arbeit zu bereiten
     */
    @Override
    public int[] getZahlen()
    {
        return zahlen;
    }

    @Override
    public void setZahlen(int[]zahlen)
    {
    	for(int i = 0;i<20;i++){
			this.zahlen[i] = zahlen[i];
    	}
    }
}
