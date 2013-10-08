package tgm.sew.roboterfabrik;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

public class Fabrik {

	private ThreadPoolExecutor montageMitarbeiterPool;
	
	private ThreadPoolExecutor lagerMitarbeiterPool;
	
	private ThreadPoolExecutor kundenPool;

	private ThreadPoolExecutor liferantenPool;

	private Sekretariat sekretariat;

	private Lager lager;

	private Logger logger;
	
	/**
	 * Hier werden die ThreadPools verwaltet
	 * @param laufzeit	Wie lang (in ms) die Threads laufen
	 * @param numLieferanten Wie viele Lieferanten Threads laufe
	 * @param numMonteure Wie viele MontageMitarbeiter Threads max laufen
	 * @param filePfad Der Filepath fuer den lagermitarbeiter
	 */
	public Fabrik(int laufzeit, int numLieferanten, int numMonteure, String filePfad) {
        this.sekretariat = new Sekretariat();
        try {
			this.logger = new LoggerFactory().getLogger(FileQueue.class);
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		}
        //LagerMitarbeiterThreadPools und darin LagerMitarbeiterThreads erzeugen
        LagerMitarbeiter lagerMitarbeiter = new LagerMitarbeiter(sekretariat.getUniqueID(), filePfad);
        lagerMitarbeiterPool = new ThreadPoolExecutor(1,1, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1));
        lagerMitarbeiterPool.execute(lagerMitarbeiter);

        //MontageMitarbeiterThreadPool und darin MontageMitarbeiterThreads erzeugen
		montageMitarbeiterPool = new ThreadPoolExecutor(numMonteure, numMonteure, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(numMonteure));
        for(int i=0; i<numMonteure; i++){
            montageMitarbeiterPool.execute(new MontageMitarbeiter(sekretariat.getUniqueMitarbeiterID(),sekretariat,lagerMitarbeiter));
        }

        //LieferantenPool und darin LieferantenThreads erzeugen
		liferantenPool = new ThreadPoolExecutor( numLieferanten, numLieferanten, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(numLieferanten));
        for(int i=0; i<numLieferanten; i++){
            liferantenPool.execute(new Lieferant(lagerMitarbeiter));
        }

        //KundenPool und darin KundenThreads erzeugen
		kundenPool = new ThreadPoolExecutor(1, 1, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1));
        kundenPool.execute(new Kunde(lagerMitarbeiter));
        
    }
}
	
	

