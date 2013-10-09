package tgm.sew.roboterfabrik;

import java.util.logging.Logger;

import tgm.sew.roboterfabrik.logging.LoggerFactory;
import java.util.logging.Level;

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
        this.lagerMitarbeiter = lagerMitarbeiter;
        this.stop = false;
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
			spielzeugRoboter =(SpielzeugRoboter) lagerMitarbeiter.anfrage(SpielzeugRoboter.class);
			if(spielzeugRoboter == null){
				logger.log(Level.INFO,"Kunde erh�lt kein Roboter(Kein Roboter vorhanden)");
			}else{
				logger.log(Level.INFO,"Kunde hat Roboter erhalten");
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				stop();
			}
		}
	}
}
