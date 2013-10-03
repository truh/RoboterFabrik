package tgm.sew.roboterfabrik;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Fabrik {

	private ThreadPoolExecutor montageMitarbeiterPool;
	
	private ThreadPoolExecutor lagerMitarbeiterPool;
	
	private ThreadPoolExecutor kundenPool;

	private ThreadPoolExecutor liferantenPool;

<<<<<<< HEAD
	private LagerMitarbeiter lagerMitarbeiter;

	private Sekretariat sekretariat = new Sekretariat();
=======
	private Sekretariat sekretariat;
>>>>>>> fc51f361b5c4e0f5c25fd1d367a4d4facf0b0eb8

	private Lager lager;
	
	/**
	 * Hier werden die ThreadPools verwaltet
	 * @param laufzeit	Wie lang (in ms) die Threads laufen
	 * @param numLieferanten Wie viele Lieferanten Threads laufe
	 * @param numMonteure Wie viele MontageMitarbeiter Threads max laufen
	 * @param filePfad Der Filepath für den lagermitarbeiter
	 */
	public Fabrik(int laufzeit, int numLieferanten, int numMonteure, String filePfad) {
        this.sekretariat = new Sekretariat();

        //LagerMitarbeiter erzeugen
        LagerMitarbeiter lagerMitarbeiter = new LagerMitarbeiter(sekretariat.getUniqueID(), filePfad);
        lagerMitarbeiterPool = new ThreadPoolExecutor(1,1, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1));
        lagerMitarbeiterPool.execute(lagerMitarbeiter);

        //MontageMitarbeiter erzeugen
		montageMitarbeiterPool = new ThreadPoolExecutor(numMonteure, numMonteure, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(numMonteure));
        for(int i=0; i<numMonteure; i++){
            montageMitarbeiterPool.execute(new MontageMitarbeiter(sekretariat.getUniqueMitarbeiterID(),sekretariat,lagerMitarbeiter));
        }

        //Lieferanten erzeugen
		liferantenPool = new ThreadPoolExecutor( numLieferanten, numLieferanten, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(numLieferanten));
        for(int i=0; i<numLieferanten; i++){
            liferantenPool.execute(new Lieferant(lagerMitarbeiter));
        }

        //Kunden erzeugen
		kundenPool = new ThreadPoolExecutor(1, 1, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1));
        kundenPool.execute(new Kunde(lagerMitarbeiter));
    }
}
	
	

