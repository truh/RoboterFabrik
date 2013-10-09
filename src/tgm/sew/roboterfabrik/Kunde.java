package tgm.sew.roboterfabrik;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.util.logging.Logger;

/**
 * Kunden geben Bestellungen an den LagerMitarbeiter ab.
 */
public class Kunde implements Stoppable {

	private LagerMitarbeiter lagerMitarbeiter;
	
	private SpielzeugRoboter spielzeugRoboter;
	
	private boolean stop;

    private Logger logger;

	/**
	 * @param lagerMitarbeiter LagerMitarbeiter an den Bestellungen gestellt werden sollen
	 */
	public Kunde(LagerMitarbeiter lagerMitarbeiter) {
		this.lagerMitarbeiter = lagerMitarbeiter;
        logger = new LoggerFactory().getLogger(Kunde.class);
	}

	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {
		stop = true;
        logger.info("Ein Kunde geht nachhause " + this.toString());
	}

	/**
	 * @see java.lang.Runnable#run()
	 * Der Kunde schickt anfragen fuer einen Roboter
	 */
	public void run()
	{
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            stop();
        }
        logger.info("Ein Kunde beginnt seinen Einkauf " + this.toString());
		while(!stop){
			lagerMitarbeiter.anfrage(SpielzeugRoboter.class);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				stop();
			}
		}
	}
}
