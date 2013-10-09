package tgm.sew.roboterfabrik;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.util.Random;
import java.util.logging.Logger;

public class Lieferant implements Stoppable {

	private LagerMitarbeiter lagerMitarbeiter;
    private Random random;
    private boolean stop;
    private Logger logger;

	public Lieferant(LagerMitarbeiter lagerMitarbeiter) {
        this.lagerMitarbeiter = lagerMitarbeiter;
        this.stop = false;
        this.random = new Random(); //sucht sich ohne Parameter einen Seed basierend auf der aktuellen Nanoskeunde aus
        logger = new LoggerFactory().getLogger(Lieferant.class);
        logger.info("Lieferant wurde angestellt");
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
        //bisserl schlafen um den LagerMitarbeiter eine Chance zu geben
        try
        {
            Thread.sleep(300);
        } catch (InterruptedException e)
        {
            stop();
        }
        while(!stop)
        {
            String item = "";
            switch (random.nextInt(11)) //0-10
            {
            //es werden mehr Arme und Augen als Kettenantriebe und Rümpfe gebraucht
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
            case 6:case 7://es besteht auch eine chance das nichts hergestellt wird
                //do nothing
                item = "nix";
                break;
            case 8:case 9:
                Greifer greifer = new Greifer();
                lagerMitarbeiter.einlagern(greifer);
                item = "Greifer";
                break;
            case 10:
                Antenne antenne = new Antenne();
                lagerMitarbeiter.einlagern(antenne);
                item = "Antenne";
            }
            logger.info(item + " wurde geliefert");

            try
            {
                Thread.sleep(random.nextInt(500) + 250);
            } catch (InterruptedException e)
            {
                stop();
            }
        }
	}
}
