package tgm.sew.roboterfabrik;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.util.logging.Logger;

/**
 * In der Fabrik werden die alle aktiven Objekte wie
 * Mitarbeiter erstellt und verwaltet.
 */
public class Fabrik {

    private LagerMitarbeiter [] lagerMitarbeiters;
    private MontageMitarbeiter [] montageMitarbeiters;
    private Lieferant [] lieferants;
    private Kunde [] kundes;

	private Sekretariat sekretariat;

	private Logger logger;

    private int laufzeit;
	
	/**
	 * Hier werden alle Mitarbeiter, Kunden, ect erstellt
     *
	 * @param laufzeit	Wie lang (in ms) die Threads laufen
	 * @param numLieferanten Wie viele Lieferanten Threads laufe
	 * @param numMonteure Wie viele MontageMitarbeiter Threads max laufen
	 * @param filePfad Der Filepath fuer den lagermitarbeiter
	 */
	public Fabrik(int laufzeit, int numLieferanten, int numMonteure, String filePfad) {
        logger = new LoggerFactory().getLogger(Fabrik.class);
        logger.info("Fabrik wird geoeffnet.");

        this.laufzeit = laufzeit;

        this.sekretariat = new Sekretariat();

        //LagerMitarbeiter
        lagerMitarbeiters = new LagerMitarbeiter[1];
        for(int i=0; i<1; i++){
            lagerMitarbeiters[i] = new LagerMitarbeiter(sekretariat.getUniqueMitarbeiterID(), filePfad);
            new Thread(lagerMitarbeiters[i]).start();
        }

        //MontageMitarbeiter
        montageMitarbeiters = new MontageMitarbeiter[numMonteure];
        for(int i=0; i<numMonteure; i++){
            montageMitarbeiters[i] = new MontageMitarbeiter(sekretariat.getUniqueMitarbeiterID(), sekretariat, lagerMitarbeiters[0]);
            new Thread(montageMitarbeiters[1]).start();
        }

        //Lieferanten
        lieferants = new Lieferant[numLieferanten];
        for(int i=0; i<numLieferanten; i++){
            lieferants[i] = new Lieferant(lagerMitarbeiters[0]);
            new Thread(lieferants[i]).start();
        }

        kundes = new Kunde[1];
        for(int i=0; i<1; i++){
            kundes[i] = new Kunde(lagerMitarbeiters[0]);
            new Thread(kundes[i]).start();
        }




        //Thread zum zeitgesteuerten beenden der Mitarbeiter/Kunden/Lieferanten
        Thread beender = new Thread(){
            @Override
            public void run()
            {
                super.run();
                try
                {
                    Thread.sleep(Fabrik.this.laufzeit);
                } catch (InterruptedException e)
                {
                    logger.throwing("Fabrik", "Fabrik", e);
                }

                for(Lieferant lieferant : lieferants) {
                    lieferant.stop();
                }

                for(Kunde kunde : kundes) {
                    kunde.stop();
                }

                for(MontageMitarbeiter montageMitarbeiter : montageMitarbeiters) {
                    montageMitarbeiter.stop();
                }

                for(LagerMitarbeiter lagerMitarbeiter : lagerMitarbeiters) {
                    lagerMitarbeiter.stop();
                }
            }
        };

        beender.start();

        logger.info("Fabrik wurde geschlossen");
    }
}
	
	

