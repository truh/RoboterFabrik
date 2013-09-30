public class LagerMitarbeiter implements Mitarbeiter {

	private Lager lager;

	private Lieferant lieferant;

	private SpielzeugRoboter spielzeugRoboter;

	private int id;

	public Orderable anfrage(Class type) {
		return null;
	}

	public void einlagern(Orderable item) {

	}

	public LagerMitarbeiter(String filePfad) {

	}
	
	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {

	}

	public void run()
	{
		
	}
}
