public class SpielzeugRoboter implements Orderable {

	private int id;

	private int mitarbeiterId;

	private Auge auge1;

	private Auge auge2;

	private Rumpf rumpf;

	private Kettenantrieb kettenantrieb;

	private Arm arm1;

	private Arm arm2;

	private Sekretariat sekretariat;

	public SpielzeugRoboter(int id, int mitarbeiterId, Auge auge1, Auge auge2, Rumpf rumpf, Kettenantrieb kettenantrieb, Arm arm1, Arm arm2) {

	}


	/**
	 * @see Orderable#toCSV()
	 */
	public String toCSV() {
		return null;
	}

}
