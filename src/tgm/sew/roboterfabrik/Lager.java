package tgm.sew.roboterfabrik;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.logging.Logger;

/**
 * Im Lager werden allerlei Teile gelagert
 */
public class Lager
{
	private Queue<Auge> augen;
	private Queue<Rumpf> ruempfe;
	private Queue<Arm> arme;
	private Queue<Kettenantrieb> kettenAntriebe;
	private Queue<SpielzeugRoboter> threadees;

    private Logger logger;

    /**
     * Erstellt ein Lager an einen gegebenen Ordner im Filesystem
     *
     * @param filePfad Pfad des Ordners in dem gelagert werden soll
     */
    public Lager(String filePfad) throws IOException
    {
        this.augen = new FileQueue<Auge>(filePfad + File.separator + "augen.csv", Auge.class);
        this.arme = new FileQueue<Arm>(filePfad + File.separator + "arme.csv", Arm.class);
        this.ruempfe = new FileQueue<Rumpf>(filePfad + File.separator + "ruempfe.csv", Rumpf.class);
        this.kettenAntriebe = new FileQueue<Kettenantrieb>(filePfad + File.separator + "kettenAntriebe.csv", Kettenantrieb.class);
        this.threadees = new FileQueue<SpielzeugRoboter>(filePfad + File.separator + "threadees.csv", SpielzeugRoboter.class);
    }

    /**
     * Ein Auge zum Lager hinzufügen
     *
     * @param auge Auge welches hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
	public boolean addAuge(Auge auge) {
		return this.augen.offer(auge);
	}

    /**
     * Einen Rumpf zum Lager hinzufügen
     *
     * @param rumpf Rumpf welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
	public boolean addRumpf(Rumpf rumpf) {
		return this.ruempfe.offer(rumpf);
	}

    /**
     * Einen Arm zum Lager hinzufügen
     *
     * @param arm Arm welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
	public boolean addArm(Arm arm) {
		return this.arme.offer(arm);
	}

    /**
     * Einen Kettenantrieb zum Lager hinzufügen
     *
     * @param kettenAntrieb KettenAntrieb welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
	public boolean addKettenantrieb(Kettenantrieb kettenAntrieb) {
		return this.kettenAntriebe.offer(kettenAntrieb);
	}

    /**
     * Einen Threadee zum Lager hinzufügen
     *
     * @param threadee Threade welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
    public boolean addThreadee(SpielzeugRoboter threadee) {
        return this.threadees.offer(threadee);
    }

    /**
     * Ein Auge dem Lager entnehmen
     *
     * @return Entnommenes Auge oder null wenn keines vorhanden war
     */
	public Auge pollAuge() {
		return this.augen.poll();
	}

    /**
     * Einen Rumpf dem Lager entnehmen
     *
     * @return Entnommener Rumpf oder null wenn keiner vorhanden war
     */
	public Rumpf pollRumpf() {
		return this.ruempfe.poll();
	}

    /**
     * Einen Arm dem Lager entnehmen
     *
     * @return Entnommener Arm oder null wenn keiner vorhanden war
     */
	public Arm pollArm() {
		return this.arme.poll();
	}

    /**
     * Einen Kettenantrieb dem Lager entnehmen
     *
     * @return Entnommener KettenAntrieb oder null wenn keiner vorhanden war
     */
	public Kettenantrieb pollKettenantrieb() {
		return this.kettenAntriebe.poll();
	}

    /**
     * Einen SpielzeugRoboter dem Lager entnehmen
     *
     * @return Entnommenes Threadee oder null wenn keiner vorhanden war
     */
    public SpielzeugRoboter pollThreadee() {
        return this.threadees.poll();
    }
}
