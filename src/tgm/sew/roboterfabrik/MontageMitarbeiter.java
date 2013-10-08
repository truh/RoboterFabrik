package tgm.sew.roboterfabrik;

import java.io.IOException;
import java.util.logging.Logger;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

public class MontageMitarbeiter implements Mitarbeiter {
	private int id;

	private Auge auge1;
	private Auge auge2;

	private Rumpf rumpf;

	private Kettenantrieb kettenantrieb;

	private Arm arm1;
	private Arm arm2;
	
	private Greifer greifer1;
	private Greifer greifer2;
	
	private Antenne antenne;

	private Sekretariat sekretariat;

	private LagerMitarbeiter lagerMitarbeiter;
	
	private int mitarbeiterId;
	
	private int speicher;
	
	private SpielzeugRoboter spielzeugRoboter;
	
	private boolean stop;

	private Logger logger;
	
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
		stop =false;
		try {
			this.logger = new LoggerFactory().getLogger(FileQueue.class);
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}

	}
	
	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {
		stop = true;
	}
	/**
	 * Hier werden die zahlen in den Einzelnen Teilen sortiert und danach der Roboter erstellt und vom lagermitarbeiter ins Lager
	 * gestellt
	 * Falls nicht alle notwendigen Teile vorhanden sind gibt der Montagemitarbeiter die Teile zur�ck
	 */
	public void run(){
		while(!stop){
			// Die Objekte f�r den Roboter werden von dem lagermitarbeiter angefordert
			this.auge1=(Auge)lagerMitarbeiter.anfrage(Auge.class);
			this.auge2=(Auge)lagerMitarbeiter.anfrage(Auge.class);
			this.arm1=(Arm)lagerMitarbeiter.anfrage(Arm.class);
			this.arm2=(Arm)lagerMitarbeiter.anfrage(Arm.class);
			this.rumpf=(Rumpf)lagerMitarbeiter.anfrage(Rumpf.class);
			this.kettenantrieb=(Kettenantrieb)lagerMitarbeiter.anfrage(Kettenantrieb.class);
			this.greifer1=(Greifer)lagerMitarbeiter.anfrage(Greifer.class);
			this.greifer2=(Greifer)lagerMitarbeiter.anfrage(Greifer.class);
			this.antenne=(Antenne)lagerMitarbeiter.anfrage(Antenne.class);
			int[] auge1zahlen ;
			auge1zahlen = auge1.getZahlen();
			int[] auge2zahlen ;
			auge2zahlen = auge2.getZahlen();
			int[] arm1zahlen ;
			arm1zahlen = arm1.getZahlen();
			int[] arm2zahlen ;
			arm2zahlen = arm2.getZahlen();
			int[] rumpfzahlen ;
			rumpfzahlen = rumpf.getZahlen();
			int[] kettenantriebzahlen ;
			kettenantriebzahlen = kettenantrieb.getZahlen();
			int[] greifer1zahlen ;
			greifer1zahlen = greifer1.getZahlen();
			int[] greifer2zahlen ;
			greifer2zahlen = greifer2.getZahlen();
			int[] antennezahlen ;
			antennezahlen = antenne.getZahlen();
			// Falls Montagemitarbeiter ein Objekt nicht erh�lt legt gibt er alle seine Teile zur�ck
			if((auge1zahlen == null)||(auge2zahlen == null)||(arm1zahlen == null)||(arm2zahlen==null)||(rumpfzahlen==null)||(kettenantriebzahlen==null)||(greifer1zahlen==null)||(greifer2zahlen==null)||(antennezahlen==null)){
				lagerMitarbeiter.einlagern(arm1);
				lagerMitarbeiter.einlagern(arm2);
				lagerMitarbeiter.einlagern(auge1);
				lagerMitarbeiter.einlagern(auge2);
				lagerMitarbeiter.einlagern(rumpf);
				lagerMitarbeiter.einlagern(kettenantrieb);
				lagerMitarbeiter.einlagern(greifer1);
				lagerMitarbeiter.einlagern(greifer2);
				lagerMitarbeiter.einlagern(antenne);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.log(null);
			}else{
				// Falls er alle Teile hat Sortiert er zahlen[] im jeweiligen Objekt und erstellt mit den sortieren Objekten den
				// Threadee
				auge1zahlen=sortieren(auge1zahlen);
				auge2zahlen=sortieren(auge2zahlen);
				arm1zahlen=sortieren(arm1zahlen);
				arm2zahlen=sortieren(arm2zahlen);
				rumpfzahlen=sortieren(rumpfzahlen);
				kettenantriebzahlen=sortieren(kettenantriebzahlen);
				greifer1zahlen=sortieren(greifer1zahlen);
				greifer2zahlen=sortieren(greifer2zahlen);
				antennezahlen=sortieren(antennezahlen);
				auge1.setZahlen(auge1zahlen);
				auge2.setZahlen(auge2zahlen);
				arm1.setZahlen(arm1zahlen);
				arm2.setZahlen(arm2zahlen);
				rumpf.setZahlen(rumpfzahlen);
				greifer1.setZahlen(greifer1zahlen);
				greifer2.setZahlen(greifer1zahlen);
				antenne.setZahlen(greifer1zahlen);
				kettenantrieb.setZahlen(kettenantriebzahlen);
				id = sekretariat.getUniqueID();
				mitarbeiterId = sekretariat.getUniqueMitarbeiterID();
				spielzeugRoboter = new SpielzeugRoboter(id, mitarbeiterId ,auge1, auge2,rumpf,kettenantrieb,arm1, arm2,greifer1,greifer2,antenne);
				lagerMitarbeiter.einlagern(spielzeugRoboter);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

    @Override
    public int getId() {
        return mitarbeiterId; 
    }

    @Override
    public Sekretariat getSekretariat() {
        return sekretariat;
    }
    /**
     * Sortiert die zahlen in zahlen[] von klein nach gro� und gibt dieses dann wieder aus
     * @param zahlen
     * @return das sortierte zahlen array
     */
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
