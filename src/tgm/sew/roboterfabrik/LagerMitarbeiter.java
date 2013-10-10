package tgm.sew.roboterfabrik;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Der LagerMitarbeiter sorgt dafuer das Teile und Threadees an der
 * richtigen Stelle abgelegt werden. Ausserdem versucht er sein
 * bestes um Bestellungen auszufuehren.
 */
public class LagerMitarbeiter implements Mitarbeiter {

	private Lager lager;

	private int id;

    private ArrayBlockingQueue <Stringifyable> einlagernQueue;

    private boolean stop;

    private Logger logger;

    /**
     * Erstellt einen LagerMitarbeiter welche an gegebenen filePfad ein Lager eröffnet
     *
     * @param filePfad Ort an dem gelagert werden soll
     */
    public LagerMitarbeiter(int id, String filePfad)
    {
        this.logger = new LoggerFactory().getLogger(LagerMitarbeiter.class);

        stop = false;
        this.id = id;
        try
        {
            this.lager = new Lager(filePfad);
        } catch (IOException e)
        {
            logger.log(Level.FINER, "LagerMitarbeiter#LagerMitarbeiter(int, String): Erzeugen des Lagers", e);
            e.printStackTrace();
        }
        einlagernQueue = new ArrayBlockingQueue<Stringifyable>(256);
    }

    /**
     * Gibt den LagerMitarbeiter die Anweisung im Lager nach einen bestimmten Artikel zu suchen
     *
     * @param type Typ des Artikels
     * @return Artikel oder null
     */
    public Stringifyable anfrage(Class<? extends Stringifyable> type) {
        logger.entering("LagerMitarbeiter", "anfrage", type);
        Stringifyable item = null;

        if(type == Arm.class)
        {
            item = lager.pollArm();
        } else
        if(type == Auge.class)
        {
            item = lager.pollAuge();
        } else
        if(type == Kettenantrieb.class)
        {
            item = lager.pollKettenantrieb();
        } else
        if(type == Rumpf.class)
        {
            item = lager.pollRumpf();
        } else
        if(type == Greifer.class)
        {
            item = lager.pollGreifer();
        } else
        if(type == Antenne.class)
        {
            item = lager.pollAntenne();
        } else
        if(type == SpielzeugRoboter.class)
        {
            item = lager.pollThreadee();
        }

        logger.exiting("LagerMitarbeiter", "anfrage", item);
		return item;
	}

    /**
     * Ein Artikel wird dem Lagermitarbeiter zurückgeliefert
     * Arbeitet asynchron, item wird erst zwischen gespeichert und später eingelagert
     *
     * @param item Artikel der eingeliefert werden soll
     * @return einlagern erfolgreich?
     *      scheiterungs Gründe währen wenn die buffer Queue voll ist oder
     *      der LagerMitarbeiter gestopt wurde.
     */
	public synchronized boolean einlagern(Stringifyable item) {
        if(this.stop){
            return false;
        }
        logger.entering("LagerMiterbeiter", "einlagern", item);
        return this.einlagernQueue.offer(item);
	}

	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {
        this.stop = true;
	}

    /**
     * @see Runnable#run()
     */
	public void run()
	{
        logger.info("Ein LagerMitarbeiter:" + getId() + " beginnt seinen Job");
	    while (!stop)
        {
            if(einlagernQueue.size() > 0)
            {
                Stringifyable item = einlagernQueue.poll();
                if(item instanceof Arm)
                {
                    lager.addArm((Arm)item);
                } else
                if(item instanceof Auge)
                {
                    lager.addAuge((Auge) item);
                } else
                if(item instanceof Kettenantrieb)
                {
                    lager.addKettenantrieb((Kettenantrieb) item);
                } else
                if(item instanceof Rumpf)
                {
                    lager.addRumpf((Rumpf) item);
                } else
                if(item instanceof Greifer)
                {
                    lager.addGreifer((Greifer) item);
                } else
                if(item instanceof Antenne)
                {
                    lager.addAntenne((Antenne) item);
                } else
                if(item instanceof SpielzeugRoboter)
                {
                    lager.addThreadee((SpielzeugRoboter) item);
                }
                logger.log(Level.INFO, "Artikel wurde ins Lager gelegt", item.toCSV());
            } else
                try
                {
                    Thread.sleep(50);
                } catch (InterruptedException e)
                {
                    stop();
                }
        }
	}

    /**
     * Für was aucher immer der LagerMitarbeiter die id brauchen soll
     * @return einmalige ID
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Nicht benötigt, nicht implementiert
     * @return null
     */
    @Override
    public Sekretariat getSekretariat() {
        return null;
    }
}
