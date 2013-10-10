package tgm.sew.roboterfabrik;

/**
 * AbstractTeil dient dazu eine redundante Implementierung fuer jeden Teil-Typ unnoetig
 * zu machen.
 */
public abstract class AbstractTeil implements Teil {
	private String name;
	private int[] zahlen;
	/**
	 * Fuellt zahlen[] mit Zufallszahlen
	 */
	public AbstractTeil(String name) {
		this.name = name;
        this.zahlen = new int[LAENGE];
        for(int i = 0;i<LAENGE;i++){
            zahlen[i] =(int)(Math.random() * (999 - 1+1)+1);
        	
        }
	}
	/**
	 * Wandelt alle Inhalte des Threadee in ein String um
	 */
    @Override
    public String toCSV() {
    	StringBuilder s = new StringBuilder(43);//Min anzahl der Zeichen im Stringbuilder(Arm = 3 Zeiche zahlen = 20 beistriche = 20
    	s.append(name);
    	for(int i = 0;i<LAENGE;i++){
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
    	String[] items = csv.replaceAll(name+",","").split(",");// loescht das Wort raus und trennt mit den "," die Zahlen im String
    	for(int i = 0 ;i<zahlen.length;i++){
    		zahlen[i]=Integer.parseInt(items[i]);
    	}
        
    }
    /**
     * Gibt das Zahlenarray zurueck
     * Das Zahlenarray dient dazu dem  MontageMitarbeiter Arbeit zu verschaffen
     */
    @Override
    public int[] getZahlen()
    {
        return zahlen; 
    }

    /**
     * Setzt das Zahlenarray,
     * Aufruf z.B. durch den MontageMitarbeiter nachdem die Zahlen sortiert wurden
     *
     * @param zahlen Zahlenarray
     */
	@Override
	public void setZahlen(int[] zahlen) {
		for(int i = 0;i<LAENGE;i++){
			this.zahlen[i] = zahlen[i];
		}
		
	}
}

