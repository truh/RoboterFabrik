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
		ThreadPoolExecutor executorPoolmon = new ThreadPoolExecutor(0, numMonteure, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory);
		ThreadPoolExecutor executorPoollief = new ThreadPoolExecutor(0, numLieferanten, laufzeit, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory);
    }
 

			
	}
	
	

