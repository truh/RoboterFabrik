package tgm.sew.roboterfabrik;
public class MontageMitarbeiter implements Mitarbeiter {
	private int id;

	private Auge auge1;
	private Auge auge2;

	private Rumpf rumpf;

	private Kettenantrieb kettenantrieb;

	private Arm arm1;
	private Arm arm2;

	private Sekretariat sekretariat;

	private LagerMitarbeiter lagerMitarbeiter;
	
	private int mitarbeiterId;
	
	private int speicher;
	
	private SpielzeugRoboter spielzeugRoboter;
	
	/**
	 * 
	 * @param id
	 * @param sekretariat
	 * @param lagerMitarbeiter
	 */
	public MontageMitarbeiter(int id, Sekretariat sekretariat, LagerMitarbeiter lagerMitarbeiter) {
		this.id = id;
		this.sekretariat=sekretariat;
		this.lagerMitarbeiter=lagerMitarbeiter;
		

	}
	
	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {
		
	}
	/**
	 * Hier werden die zahlen in den Einzelnen Teilen sortiert und danach der Roboter erstellt und vom lagermitarbeiter ins Lager
	 * gestellt
	 */
	public void run(){
		while(true){
			this.auge1=(Auge)lagerMitarbeiter.anfrage(Auge.class);
			this.auge2=(Auge)lagerMitarbeiter.anfrage(Auge.class);
			this.arm1=(Arm)lagerMitarbeiter.anfrage(Arm.class);
			this.arm2=(Arm)lagerMitarbeiter.anfrage(Arm.class);
			this.rumpf=(Rumpf)lagerMitarbeiter.anfrage(Rumpf.class);
			this.kettenantrieb=(Kettenantrieb)lagerMitarbeiter.anfrage(Kettenantrieb.class);
			int[] auge1zahlen = new int[20];
			auge1zahlen = auge1.getZahlen();
			int[] auge2zahlen = new int[20];
			auge2zahlen = auge2.getZahlen();
			int[] arm1zahlen = new int[20];
			arm1zahlen = arm1.getZahlen();
			int[] arm2zahlen = new int[20];
			arm2zahlen = arm2.getZahlen();
			int[] rumpfzahlen = new int[20];
			rumpfzahlen = rumpf.getZahlen();
			int[] kettenantriebzahlen = new int[20];
			kettenantriebzahlen = kettenantrieb.getZahlen();
			if((auge1zahlen == null)||(auge2zahlen == null)||(arm1zahlen == null)||(arm2zahlen==null)||(rumpfzahlen==null)||(kettenantriebzahlen==null)){
				lagerMitarbeiter.einlagern(arm1);
				lagerMitarbeiter.einlagern(arm2);
				lagerMitarbeiter.einlagern(auge1);
				lagerMitarbeiter.einlagern(auge2);
				lagerMitarbeiter.einlagern(rumpf);
				lagerMitarbeiter.einlagern(kettenantrieb);
			}else{
				auge1zahlen=sortieren(auge1zahlen);
				auge2zahlen=sortieren(auge2zahlen);
				arm1zahlen=sortieren(arm1zahlen);
				arm2zahlen=sortieren(arm2zahlen);
				rumpfzahlen=sortieren(rumpfzahlen);
				kettenantriebzahlen=sortieren(kettenantriebzahlen);
				auge1.setZahlen(auge1zahlen);
				auge2.setZahlen(auge2zahlen);
				arm1.setZahlen(arm1zahlen);
				arm2.setZahlen(arm2zahlen);
				rumpf.setZahlen(rumpfzahlen);
				kettenantrieb.setZahlen(kettenantriebzahlen);
				
			}
			id = sekretariat.getUniqueID();
			mitarbeiterId = sekretariat.getUniqueMitarbeiterID();
			spielzeugRoboter = new SpielzeugRoboter(id, mitarbeiterId ,auge1, auge2,rumpf,kettenantrieb,arm1, arm2);
			lagerMitarbeiter.einlagern(spielzeugRoboter);
			}
        }

    @Override
    public int getId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Sekretariat getSekretariat() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    public static int[] sortieren(int[] zahlen){
    	int speicher ;
    	for(int i = 0;i<zahlen.length;i++){
    		for(int ii = 0;ii<zahlen.length;ii++){      
    			if(zahlen[i]<zahlen[ii]){
    				speicher = zahlen[i];
    				zahlen[i]=zahlen[ii];
    				zahlen[ii]=speicher;
    			}
    		}
    	}
		return zahlen;
    }
}
