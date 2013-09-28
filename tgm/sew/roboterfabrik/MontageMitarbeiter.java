package tgm.sew.roboterfabrik;
public class MontageMitarbeiter implements Mitarbeiter {
	private int id;

	private Auge auge1;
	private Auge auge2;

	private Rumpf rumpf;

	private Kettenantrieb kettenantrieb;

	private Arm arm1;
	private Arm arm2;

	private Sekretariat sekretariat;

	private LagerMitarbeiter lagerMitarbeiter;

	public MontageMitarbeiter(int id, Sekretariat sekretariat, LagerMitarbeiter lagerMitarbeiter) {
		this.id = id;
		this.sekretariat=sekretariat;
		this.lagerMitarbeiter=lagerMitarbeiter;
		

	}
	
	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {

	}

	public void run(){
		while(true){
			//toDO: Holt sich die Teile vom Lagermitarbeiter
        }
	}

    @Override
    public int getId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Sekretariat getSekretariat() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
