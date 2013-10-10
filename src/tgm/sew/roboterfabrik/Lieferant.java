package tgm.sew.roboterfabrik;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Der Lieferant liefert Teile an den LagerMitarbeiter
 */
public class Lieferant implements Stoppable {

	private LagerMitarbeiter lagerMitarbeiter;
    private Random random;
    private boolean stop;
    private Logger logger;

    /**
     * Konstruktor bei dem der LagerMitarbeiter angegeben werden kann an den geliefert werden soll
     *
     * @param lagerMitarbeiter an diesen LagerMitarbeiter wird geliefert
     */
	public Lieferant(LagerMitarbeiter lagerMitarbeiter) {
        this.lagerMitarbeiter = lagerMitarbeiter;
        this.stop = false;
        this.random = new Random(); //sucht sich ohne Parameter einen Seed basierend auf der aktuellen Nanoskeunde aus
        logger = new LoggerFactory().getLogger(Lieferant.class);
	}

	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {
        this.stop = true;
        logger.info("Lieferant wurde gestoppt " + this.toString());
	}

    /**
     * @see Runnable#run()
     */
	public void run()
	{
        //bisserl schlafen um den LagerMitarbeiter eine Chance zu geben
        try
        {
            Thread.sleep(300);
        } catch (InterruptedException e)
        {
            stop();
        }
        logger.info("Ein Lieferant beginnt seinen Job");
        while(!stop)
        {
            String item = "";
            switch (random.nextInt(9)) //0-8
            {
            //es werden mehr Arme und Augen als Kettenantriebe und Ruempfe gebraucht
            case 0:case 4: //Arm
                Arm arm = new Arm();
                lagerMitarbeiter.einlagern(arm);
                item = "Arm";
                break;
            case 1:case 5: //Auge
                Auge auge = new Auge();
                lagerMitarbeiter.einlagern(auge);
                item = "Auge";
                break;
            case 2: //Kettenantrieb
                Kettenantrieb kettenantrieb = new Kettenantrieb();
                lagerMitarbeiter.einlagern(kettenantrieb);
                item = "Kettenantrieb";
                break;
            case 3: //Rumpf
                Rumpf rumpf = new Rumpf();
                lagerMitarbeiter.einlagern(rumpf);
                item = "Rumpf";
                break;
            case 6:case 7: //Greifer
                Greifer greifer = new Greifer();
                lagerMitarbeiter.einlagern(greifer);
                item = "Greifer";
                break;
            case 8: //Antenne
                Antenne antenne = new Antenne();
                lagerMitarbeiter.einlagern(antenne);
                item = "Antenne";
            }
            logger.info(item + " wurde geliefert" + this.toString());

            try
            {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e)
            {
                stop();
            }
        }
	}
}
