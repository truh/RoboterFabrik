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

	private LagerMitarbeiter lagerMitarbeiter;

	private Sekretariat sekretariat;

	private Lager lager;
	
	
	public Fabrik(int laufzeit, int numLieferanten, int numMonteure, String filePfad) {
		
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		montageMitarbeiterPool = new ThreadPoolExecutor(numMonteure, numMonteure, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(numMonteure));
		liferantenPool = new ThreadPoolExecutor( numLieferanten, numLieferanten, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>( numLieferanten));
		kundenPool = new ThreadPoolExecutor(1, 1, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1));
		lagerMitarbeiterPool = new ThreadPoolExecutor(1,1, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1));
		for(int i=0; i<numMonteure; i++){
			montageMitarbeiterPool.execute(new MontageMitarbeiter(sekretariat.getUniqueMitarbeiterID(),sekretariat,lagerMitarbeiter));
        }
		for(int i=0; i<numLieferanten; i++){
			liferantenPool.execute(new Lieferant(lagerMitarbeiter));
        }
		kundenPool.execute(new Kunde(lagerMitarbeiter));
		lagerMitarbeiterPool.execute(new LagerMitarbeiter(filePfad));
    }
 

			
	}
	
	

