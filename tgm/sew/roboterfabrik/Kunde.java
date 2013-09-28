package tgm.sew.roboterfabrik;
/**
 * Kunden geben Bestellungen an den LagerMitarbeiter ab.
 */
public class Kunde implements Stoppable {

	private LagerMitarbeiter lagerMitarbeiter;

	/**
	 * @param lagerMitarbeiter LagerMitarbeiter an den Bestellungen gestellt werden sollen
	 */
	public Kunde(LagerMitarbeiter lagerMitarbeiter) {
			this.lagerMitarbeiter = lagerMitarbeiter;
	}

	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {

	}

	/**
	 * @see java.lang.Runnable.run()
	 */
	public void run()
	{
		
	}
}
