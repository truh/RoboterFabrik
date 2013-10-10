package tgm.sew.roboterfabrik;

/**
 * Grundgeruest fuer einen Mitarbeiter
 */
public interface Mitarbeiter extends Runnable, Stoppable {

	public final String MID_PREFIX = "Mitarbeiter-ID";
    public int getId();

    public Sekretariat getSekretariat();

}
