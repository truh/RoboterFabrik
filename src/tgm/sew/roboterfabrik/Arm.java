package tgm.sew.roboterfabrik;
/**
 * Ein Bauteil des Threadee
 */
public class Arm implements Teil {
	public String name;
	private int [] zahlen;

	/**
	 * Fuellt zahlen[] mit Zufallszahlen
	 */
	public Arm() {
		name = "Arm";
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
    	StringBuilder s = new StringBuilder(43);//Min anzahl der Zeichen im Stringbuilder(arm = 3 Zeiche zahlen = 20 beistriche = 20
    	s.append("Arm");
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
    	String[] items = csv.replaceAll("Arm,","").split(",");// loescht das Wort raus und trennt mit den "," die Zahlen im String
    	for(int i = 0 ;i<zahlen.length;i++){
    		zahlen[i]=Integer.parseInt(items[i]);
    	}
        
    }
    /**
     * getter und setter methoden erklaeren sich von selbst
     */
    @Override
    public int[] getZahlen()
    {
        return zahlen; 
    }

    /**
     * 
     */
	@Override
	public void setZahlen(int[] zahlen) {
		for(int i = 0;i<20;i++){
			this.zahlen[i] = zahlen[i];
		}
		
	}
}
