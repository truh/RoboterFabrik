package tgm.sew.roboterfabrik;
public class MontageMitarbeiter implements Mitarbeiter {
	private String id;

	private Auge auge1;
	private Auge auge2;

	private Rumpf rumpf;

	private Kettenantrieb kettenantrieb;

	private Arm arm1;
	private Arm arm2;

	private Sekretariat sekretariat;

	private LagerMitarbeiter lagerMitarbeiter;
	
	private String mitarbeiterId;
	
	private int speicher;
	
	private SpielzeugRoboter spielzeugRoboter;
	
	/**
	 * 
	 * @param id
	 * @param sekretariat
	 * @param lagerMitarbeiter
	 */
	public MontageMitarbeiter(String id, Sekretariat sekretariat, LagerMitarbeiter lagerMitarbeiter) {
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
			//toDO: Holt sich die Teile vom Lagermitarbeiter
			if((auge1zahlen == null)||(auge2zahlen == null)||(arm1zahlen == null)||(arm2zahlen==null)||(rumpfzahlen==null)||(kettenantriebzahlen==null)){
				
			}else{
			for(int i = 0;i<20;i++){
				for(int ii = 1;ii<21;ii++){      
					if(arm1zahlen[i]>arm1zahlen[ii]){
						speicher = arm1zahlen[i];
						arm1zahlen[i]=arm1zahlen[ii];
						arm1zahlen[ii]=speicher;
					}
					if(arm2zahlen[i]>arm2zahlen[ii]){
						speicher = arm2zahlen[i];
						arm2zahlen[i]=arm2zahlen[ii];
						arm2zahlen[ii]=speicher;
					}
					if(auge1zahlen[i]>auge1zahlen[ii]){
						speicher = auge1zahlen[i];
						auge1zahlen[i]=auge1zahlen[ii];
						auge1zahlen[ii]=speicher;
					}
					if(auge2zahlen[i]>auge2zahlen[ii]){
						speicher = auge2zahlen[i];
						auge2zahlen[i]=auge2zahlen[ii];
						auge2zahlen[ii]=speicher;
					}
					if(kettenantriebzahlen[i]>kettenantriebzahlen[ii]){
						speicher = kettenantriebzahlen[i];
						kettenantriebzahlen[i]=kettenantriebzahlen[ii];
						kettenantriebzahlen[ii]=speicher;
					}
					if(rumpfzahlen[i]>rumpfzahlen[ii]){
						speicher = rumpfzahlen[i];
						rumpfzahlen[i]=rumpfzahlen[ii];
						rumpfzahlen[ii]=speicher;
					}
				}
			}
			id = sekretariat.getUniqueID();
			mitarbeiterId = sekretariat.getUniqueMitarbeiterID();
			spielzeugRoboter = new SpielzeugRoboter(id, mitarbeiterId ,auge1, auge2,rumpf,kettenantrieb,arm1, arm2);
			// Hier kommt ne Methode rein wo der Lagermitarbeiter den Spielzeugroboter in das Lager gibt
			}
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
