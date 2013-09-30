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
	 * Der Kunde schickt anfragen für einen Roboter
	 */
	public void run()
	{
		while(true){
			// Anfrage an den Lagermitarbeiter
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
