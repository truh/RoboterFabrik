package tgm.sew.roboterfabrik;
import java.io.File;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
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

    //Zum cachen der Anzahl der jeweiligen Bauteile um die Performance zu erhoehen
    private AtomicInteger cAntennen;
    private AtomicInteger cArme;
    private AtomicInteger cAugen;
    private AtomicInteger cGreifer;
    private AtomicInteger cKettenAntrieb;
    private AtomicInteger cRuempfe;
    private AtomicInteger cThreadees;

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

        //Anzahl an Bauteilen auf die aktuelle Anzahl setzen.
        this.cAntennen.set(this.antennen.size());
        this.cArme.set(this.arme.size());
        this.cAugen.set(this.augen.size());
        this.cGreifer.set(this.greifer.size());
        this.cKettenAntrieb.set(this.kettenAntriebe.size());
        this.cRuempfe.set(this.ruempfe.size());
        this.cThreadees.set(this.threadees.size());
    }

    /**
     * Ein Auge zum Lager hinzufügen
     *
     * @param auge Auge welches hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
	public boolean addAuge(Auge auge) {
        if(this.augen.offer(auge)) {
            this.cAugen.incrementAndGet();
            return false;
        }
		return false;
	}

    /**
     * Einen Rumpf zum Lager hinzufügen
     *
     * @param rumpf Rumpf welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
	public boolean addRumpf(Rumpf rumpf) {
        if(this.ruempfe.offer(rumpf)) {
            this.cRuempfe.incrementAndGet();
            return true;
        }
		return false;
	}

    /**
     * Einen Arm zum Lager hinzufügen
     *
     * @param arm Arm welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
	public boolean addArm(Arm arm) {
        if(this.arme.offer(arm)) {
            this.cArme.incrementAndGet();
            return true;
        }
		return false;
	}

    /**
     * Einen Greifer zum Lager hinzufügen
     *
     * @param kettenAntrieb KettenAntrieb welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
    public boolean addKettenantrieb(Kettenantrieb kettenAntrieb) {
        if(this.kettenAntriebe.offer(kettenAntrieb)) {
            this.cKettenAntrieb.incrementAndGet();
            return true;
        }
        return false;
    }

    /**
     * Eine Antenne zum Lager hinzufügen
     *
     * @param greifer KettenAntrieb welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
    public boolean addGreifer(Greifer greifer) {
        if(this.greifer.offer(greifer)) {
            this.cGreifer.incrementAndGet();
            return true;
        }
        return false;
    }

    /**
     * Einen Kettenantrieb zum Lager hinzufügen
     *
     * @param antenne KettenAntrieb welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
    public boolean addAntenne(Antenne antenne) {
        if(this.antennen.offer(antenne)) {
            this.cAntennen.incrementAndGet();
            return true;
        }
        return false;
    }

    /**
     * Einen Threadee zum Lager hinzufügen
     *
     * @param threadee Threade welcher hinzugefügt werden soll
     * @return true wenn hinzufügen möglich war
     */
    public boolean addThreadee(SpielzeugRoboter threadee) {
        if(this.threadees.offer(threadee)) {
            this.cThreadees.incrementAndGet();
            return true;
        }
        return false;
    }

    /**
     * Ein Auge dem Lager entnehmen
     *
     * @return Entnommenes Auge oder null wenn keines vorhanden war
     */
	public Auge pollAuge() {
        if(this.cAugen.get() == 0)
            return null;
        Auge auge = this.augen.poll();
        if(auge != null)
            this.cAugen.decrementAndGet();
		return auge;
	}

    /**
     * Einen Rumpf dem Lager entnehmen
     *
     * @return Entnommener Rumpf oder null wenn keiner vorhanden war
     */
	public Rumpf pollRumpf() {
        if(this.cRuempfe.get() == 0)
            return null;
        Rumpf rumpf = this.ruempfe.poll();
        if(rumpf != null)
            this.cRuempfe.decrementAndGet();
		return rumpf;
	}

    /**
     * Einen Arm dem Lager entnehmen
     *
     * @return Entnommener Arm oder null wenn keiner vorhanden war
     */
	public Arm pollArm() {
		if(this.cArme.get() == 0)
            return null;
        Arm arm = this.arme.poll();
        if(arm != null)
            this.cArme.decrementAndGet();
        return arm;
	}

    /**
     * Einen Kettenantrieb dem Lager entnehmen
     *
     * @return Entnommener KettenAntrieb oder null wenn keiner vorhanden war
     */
    public Kettenantrieb pollKettenantrieb() {
        if(this.cKettenAntrieb.get() == 0)
            return null;
        Kettenantrieb kettenantrieb = this.kettenAntriebe.poll();
        if(kettenantrieb != null)
            this.cKettenAntrieb.decrementAndGet();
        return kettenantrieb;
    }

    /**
     * Einen Greifer dem Lager entnehmen
     *
     * @return Entnommener Greifer oder null wenn keiner vorhanden war
     */
    public Greifer pollGreifer() {
        if(this.cGreifer.get() == 0)
            return null;
        Greifer greifer = this.greifer.poll();
        if(greifer != null)
            this.cGreifer.decrementAndGet();
        return greifer;
    }
    /**
     * Einen Antenne dem Lager entnehmen
     *
     * @return Entnommener Antenne oder null wenn keiner vorhanden war
     */
    public Antenne pollAntenne() {
        if(this.cAntennen.get() == 0)
            return null;
        Antenne antenne = this.antennen.poll();
        if(antenne != null)
            this.cAntennen.decrementAndGet();
        return antenne;
    }

    /**
     * Einen SpielzeugRoboter dem Lager entnehmen
     *
     * @return Entnommenes Threadee oder null wenn keiner vorhanden war
     */
    public SpielzeugRoboter pollThreadee() {
        if(this.cThreadees.get() == 0)
            return null;
        SpielzeugRoboter spielzeugRoboter = this.threadees.poll();
        if(spielzeugRoboter != null)
            this.cThreadees.decrementAndGet();
        return spielzeugRoboter;
    }
}
