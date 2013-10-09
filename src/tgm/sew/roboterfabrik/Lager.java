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
    private Queue<Greifer> greifer;
    private Queue<Antenne> antennen;
	private Queue<SpielzeugRoboter> threadees;

    private Logger logger;

    /**
     * Erstellt ein Lager an einen gegebenen Ordner im Filesystem
     *
     * @param filePfad Pfad des Ordners in dem gelagert werden soll
     */
    public Lager(String filePfad) throws IOException
    {
        this.augen = new FileQueue<Auge>(filePfad + File.separator, Auge.class);
        this.arme = new FileQueue<Arm>(filePfad + File.separator, Arm.class);
        this.ruempfe = new FileQueue<Rumpf>(filePfad + File.separator, Rumpf.class);
        this.kettenAntriebe = new FileQueue<Kettenantrieb>(filePfad + File.separator, Kettenantrieb.class);
        this.greifer = new FileQueue<Greifer>(filePfad + File.separator, Greifer.class);
        this.antennen = new FileQueue<Antenne>(filePfad + File.separator, Antenne.class);
        this.threadees = new FileQueue<SpielzeugRoboter>(filePfad + File.separator, SpielzeugRoboter.class);
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
     * Einen Greifer zum Lager hinzufügen
     *
     * @param kettenAntrieb KettenAntrieb welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
    public boolean addKettenantrieb(Kettenantrieb kettenAntrieb) {
        return this.kettenAntriebe.offer(kettenAntrieb);
    }

    /**
     * Eine Antenne zum Lager hinzufügen
     *
     * @param greifer KettenAntrieb welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
    public boolean addGreifer(Greifer greifer) {
        return this.greifer.offer(greifer);
    }

    /**
     * Einen Kettenantrieb zum Lager hinzufügen
     *
     * @param antenne KettenAntrieb welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
    public boolean addAntenne(Antenne antenne) {
        return this.antennen.offer(antenne);
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
     * Einen Greifer dem Lager entnehmen
     *
     * @return Entnommener Greifer oder null wenn keiner vorhanden war
     */
    public Greifer pollGreifer() {
        return this.greifer.poll();
    }
    /**
     * Einen Antenne dem Lager entnehmen
     *
     * @return Entnommener Antenne oder null wenn keiner vorhanden war
     */
    public Antenne pollAntenne() {
        return this.antennen.poll();
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
