package tgm.sew.roboterfabrik;

/**
 * Ein Bauteil des Threadee
 */
public class Auge implements Teil {
	public String name;
	private int [] zahlen;

	/**
	 * Füllt zahlen mit Zufallszahlen
	 */
	public Auge() {
		name = "Auge";
        this.zahlen = new int[20];
        for(int i = 0;i<20;i++){
            zahlen[i] =(int)(Math.random() * (999 - 1+1)+1);
        }
	}

    @Override
    public String toCSV() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fromCSV(String csv)
    {
        //To change body of implemented methods use File | Settings | File Templates.
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
