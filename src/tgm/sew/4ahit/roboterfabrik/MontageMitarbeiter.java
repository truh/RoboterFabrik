public class MontageMitarbeiter implements Mitarbeiter {

	private Auge auge1;
	private Auge auge2;

	private Rumpf rumpf;

	private Kettenantrieb kettenantrieb;

	private Arm arm1;
	private Arm arm2;

	private Sekretariat sekretariat;

	private LagerMitarbeiter lagerMitarbeiter;

	public MontageMitarbeiter(int id, Sekretariat sekretariat, LagerMitarbeiter lagerMitarbeiter) {

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
