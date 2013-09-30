package tgm.sew.roboterfabrik;

import java.util.concurrent.ThreadPoolExecutor;

public class Fabrik {

	private ThreadPoolExecutor mitarbeiterPool;

	private ThreadPoolExecutor kundenPool;

	private ThreadPoolExecutor liferantenPool;

	private LagerMitarbeiter lagerMitarbeiter;

	private Sekretariat sekretariat;

	private Lager lager;

	public Fabrik(int laufzeit, int numLieferanten, int numMonteure, String filePfad) {

	}

}
