package tgm.sew.roboterfabrik;
public class SpielzeugRoboter implements Stringifyable {

	private String id;

	private String mitarbeiterId;

	private Auge auge1;

	private Auge auge2;

	private Rumpf rumpf;

	private Kettenantrieb kettenantrieb;

	private Arm arm1;

	private Arm arm2;

	private Sekretariat sekretariat;

	public SpielzeugRoboter(String id, String mitarbeiterId, Auge auge1, Auge auge2, Rumpf rumpf, Kettenantrieb kettenantrieb, Arm arm1, Arm arm2) {
		this.id = id;
		this.mitarbeiterId = mitarbeiterId;
		this.auge1 = auge1;
		this.auge2 = auge2;
		this.rumpf = rumpf;
		this.kettenantrieb = kettenantrieb;
		this.arm1 = arm1;
		this.arm2 = arm2;
	}


	/**
	 * @see Stringifyable#toCSV()
	 */
	public String toCSV() {
		return null;
	}

    @Override
    public void fromCSV(String csv)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
