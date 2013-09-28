package tgm.sew.roboterfabrik;
import java.util.HashSet;

public class Sekretariat implements Runnable {

	private HashSet mitarbeiterSet;

	private HashSet kundenSet;

	private HashSet liferantenSet;

	private LagerMitarbeiter lagerMitarbeiter;

	private SpielzeugRoboter spielzeugRoboter;

	private Lieferant lieferant;

	private Kunde kunde;

	private Mitarbeiter mitarbeiter;

	public int getUniqueID() {
		return 0;
	}

	public Sekretariat(int laufzeit, int numLieferanten, int numMonteure, String filePfad) {

	}

	public void run()
	{
		
	}
}
