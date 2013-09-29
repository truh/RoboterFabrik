package tgm.sew.roboterfabrik;
import java.util.HashSet;
// Id für Roboter und Mitarbeiter wird vergeben
public class Sekretariat implements Runnable {
	
	private int id=100;
	
	private int mitarbeiterId=100;
	
	private HashSet mitarbeiterSet;

	private HashSet kundenSet;

	private HashSet liferantenSet;

	private LagerMitarbeiter lagerMitarbeiter;

	private SpielzeugRoboter spielzeugRoboter;

	private Lieferant lieferant;

	private Kunde kunde;

	private Mitarbeiter mitarbeiter;
	
	public Sekretariat(){
		
	}
	/**
	 * eine eindeutige ID für den Threadee wird erstellt
	 * @return eindeutige ID für Threadee
	 */
	public String getUniqueID() {
		id++;
		return "Threadee-ID"+id;
	}
	/**
	 * eine eindeutige ID für den Mitarbeiter wird erstellt
	 * @return eindeutige ID für Mitarbeiter
	 */
	public String getUniqueMitarbeiterID() {
		mitarbeiterId++;
		return "Mitarbeiter-ID"+mitarbeiterId;
	}
	public Sekretariat(int laufzeit, int numLieferanten, int numMonteure, String filePfad) {

	}

	public void run()
	{
		
	}
}
