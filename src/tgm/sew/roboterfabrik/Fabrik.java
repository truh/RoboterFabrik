package tgm.sew.roboterfabrik;

import java.util.concurrent.ThreadPoolExecuter;

public class Fabrik {

	private ThreadPoolExecuter mitarbeiterPool;

	private ThreadPoolExecuter kundenPool;

	private ThreadPoolExecuter liferantenPool;

	private LagerMitarbeiter lagerMitarbeiter;

	private Sekretariat sekretariat;

	private Lager lager;

	public Fabrik(int laufzeit, int numLieferanten, int numMonteure, String filePfad) {

	}

}
