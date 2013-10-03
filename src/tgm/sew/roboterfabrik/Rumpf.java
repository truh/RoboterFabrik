package tgm.sew.roboterfabrik;
/**
 * Ein Bauteil des Threadee
 */
public class Rumpf implements Teil {

	public String name;
	private int [] zahlen;

	/**
	 * FÃ¼llt zahlen mit Zufallszahlen
	 */
	public Rumpf() {
		name = "Rumpf";
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
    	StringBuilder s = new StringBuilder(45);//Min anzahl der Zeichen im Stringbuilder(Rumpf = 5 Zeiche zahlen = 20 beistriche = 20
    	s.append("Rumpf");
    	for(int i = 0;i<20;i++){
    		s.append(",");
    		s.append(zahlen[i]);
    	}
    	
        return s.toString();
    }
    /**
     * Fügt die Zahlen im parameter String in das Attribut zahlen[];
     */
    @Override
    public void fromCSV(String csv){
    	String[] items = csv.replaceAll("Rumpf,","").split(",");// löscht das Wort raus und trennt mit den "," die Zahlen im String
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
        return zahlen;  //To change body of implemented methods use File | Settings | File Templates.
    }

	@Override
	public void setZahlen(int[] zahlen) {
		for(int i = 0;i<20;i++){
			this.zahlen[i] = zahlen[i];
    	}
		
	}
}
