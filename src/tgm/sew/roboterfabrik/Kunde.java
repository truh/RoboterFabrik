package tgm.sew.roboterfabrik;

import java.util.logging.Level;
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
			this.stop = false;
	}

	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {
		stop = true;
	}

	/**
	 * @see java.lang.Runnable#run()
	 * Der Kunde schickt anfragen fuer einen Roboter
	 */
	public void run()
	{
		while(!stop){
			spielzeugRoboter =(SpielzeugRoboter) lagerMitarbeiter.anfrage(SpielzeugRoboter.class);
			if(spielzeugRoboter == null){
				logger.log(Level.INFO,"Kunde erhält kein Roboter(Kein Roboter vorhanden)");
			}else{
				logger.log(Level.INFO,"Kunde hat Roboter erhalten");
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
