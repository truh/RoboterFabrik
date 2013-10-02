package tgm.sew.roboterfabrik;
public class LagerMitarbeiter implements Mitarbeiter {

	private Lager lager;

	private Lieferant lieferant;

	private SpielzeugRoboter spielzeugRoboter;

	private int id;

	public Stringifyable anfrage(Class<? extends Stringifyable> type) {
		return null;
	}

	public void einlagern(Stringifyable item) {

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

    @Override
    public int getId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Sekretariat getSekretariat() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
