package tgm.sew.roboterfabrik;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.util.Random;
import java.util.logging.Logger;

public class MontageMitarbeiter implements Mitarbeiter {
	private int id;

	private Sekretariat sekretariat;

	private LagerMitarbeiter lagerMitarbeiter;
	
	private boolean stop;

	private Logger logger;

    private Random random;
	
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
			this.logger = new LoggerFactory().getLogger(MontageMitarbeiter.class);
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}
        this.random = new Random();
	}
	
	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {
		stop = true;
        logger.info("MontageMitarbeiter:" + getId() + " wurde gestoppt");
	}
	/**
	 * Hier werden die zahlen in den Einzelnen Teilen sortiert und danach der Roboter erstellt und vom lagermitarbeiter ins Lager
	 * gestellt
	 * Falls nicht alle notwendigen Teile vorhanden sind gibt der Montagemitarbeiter die Teile zurueck
	 */
	public void run(){
        Auge auge1;
        Auge auge2;
        Rumpf rumpf;
        Kettenantrieb kettenantrieb;
        Arm arm1;
        Arm arm2;
        Greifer greifer1;
        Greifer greifer2;
        Antenne antenne;

        try
        {
            Thread.sleep(random.nextInt(500) + 500);
        } catch (InterruptedException e)
        {
            stop();
        }

        logger.info("MontageMitarbeiter:" + getId() + " beginnt seinen Taetigkeit");

        while(!stop){
			// Die Objekte fuer den Roboter werden von dem lagermitarbeiter angefordert
			auge1=(Auge)lagerMitarbeiter.anfrage(Auge.class);
			auge2=(Auge)lagerMitarbeiter.anfrage(Auge.class);
			arm1=(Arm)lagerMitarbeiter.anfrage(Arm.class);
			arm2=(Arm)lagerMitarbeiter.anfrage(Arm.class);
			rumpf=(Rumpf)lagerMitarbeiter.anfrage(Rumpf.class);
			kettenantrieb=(Kettenantrieb)lagerMitarbeiter.anfrage(Kettenantrieb.class);
			greifer1=(Greifer)lagerMitarbeiter.anfrage(Greifer.class);
			greifer2=(Greifer)lagerMitarbeiter.anfrage(Greifer.class);
			antenne=(Antenne)lagerMitarbeiter.anfrage(Antenne.class);
			// Falls Montagemitarbeiter ein Objekt nicht erhaelt legt gibt er alle seine Teile zurueck
			if((auge1 == null)||(auge2 == null)||(arm1 == null)||(arm2==null)||(rumpf==null)||(kettenantrieb==null)||(greifer1==null)||(greifer2==null)||(antenne==null)) {
				StringBuilder sb = new StringBuilder("mID:"+ getId() +" Der Versuch einen Threadee zu bauen schlug fehl, folgende Teile wurden zurueck gegeben: ");
                if(arm1 != null){
                    lagerMitarbeiter.einlagern(arm1);
                    sb.append("Arm 1 ");
                }
                if(arm2 != null){
                    lagerMitarbeiter.einlagern(arm2);
                    sb.append("Arm 2 ");
                }
                if(auge1 != null){
                    lagerMitarbeiter.einlagern(auge1);
                    sb.append("Auge 1 ");
                }
                if(auge2 != null){
                    lagerMitarbeiter.einlagern(auge2);
                    sb.append("Auge 2 ");
                }
                if(rumpf != null){
                    lagerMitarbeiter.einlagern(rumpf);
                    sb.append("Rumpf ");
                }
                if(kettenantrieb != null){
                    lagerMitarbeiter.einlagern(kettenantrieb);
                    sb.append("KettenAntrieb ");
                }
                if(greifer1 != null){
                    lagerMitarbeiter.einlagern(greifer1);
                    sb.append("Greifer 1 ");
                }
                if(greifer2 != null){
                    lagerMitarbeiter.einlagern(greifer2);
                    sb.append("Greifer 2 ");
                }
                if(antenne != null){
                    lagerMitarbeiter.einlagern(antenne);
                    sb.append("Antenne ");
                }
                logger.info(sb.toString());
				try {
					Thread.sleep(random.nextInt(250) + 250);
				} catch (InterruptedException e) {
					logger.throwing("MontageMitarbeiter", "run", e);
				}
			}else{
                //Sortiere die Zahlen
                int[] auge1zahlen  = auge1.getZahlen();
                auge1zahlen = sortieren(auge1zahlen);
                int[] auge2zahlen  = auge2.getZahlen();
                auge2zahlen = sortieren(auge2zahlen);

                int[] arm1zahlen = arm1.getZahlen();
                arm1zahlen = sortieren(arm1zahlen);
                int[] arm2zahlen = arm2.getZahlen();
                arm2zahlen = sortieren(arm2zahlen);

                int[] rumpfzahlen = rumpf.getZahlen();
                rumpfzahlen = sortieren(rumpfzahlen);

                int[] kettenantriebzahlen = kettenantrieb.getZahlen();
                kettenantriebzahlen = sortieren(kettenantriebzahlen);

                int[] greifer1zahlen = greifer1.getZahlen();
                greifer1zahlen = sortieren(greifer1zahlen);
                int[] greifer2zahlen = greifer2.getZahlen();
                greifer2zahlen = sortieren(greifer2zahlen);

                int[] antennezahlen = antenne.getZahlen();
                antennezahlen = sortieren(antennezahlen);

                //Sortierte Zahlen an die Bauteile zurückgeben
				auge1.setZahlen(auge1zahlen);
				auge2.setZahlen(auge2zahlen);
				arm1.setZahlen(arm1zahlen);
				arm2.setZahlen(arm2zahlen);
				rumpf.setZahlen(rumpfzahlen);
				greifer1.setZahlen(greifer1zahlen);
				greifer2.setZahlen(greifer2zahlen);
				antenne.setZahlen(antennezahlen);
				kettenantrieb.setZahlen(kettenantriebzahlen);

				SpielzeugRoboter spielzeugRoboter = new SpielzeugRoboter(sekretariat.getUniqueID(), getId(),
                        auge1, auge2,
                        rumpf,
                        kettenantrieb,
                        arm1, arm2,
                        greifer1, greifer2,
                        antenne
                );

                logger.info("MontageMitarbeiter:"+ getId() +" gibt Threadee" + spielzeugRoboter.toCSV() + " an LagerMitarbeiter weiter");

				//Einlagern des Roboters
                lagerMitarbeiter.einlagern(spielzeugRoboter);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					stop();
				}
			}
		}
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Sekretariat getSekretariat() {
        return sekretariat;
    }
    /**
     * Sortiert die zahlen in zahlen[] von klein nach gross und gibt dieses dann wieder aus
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
