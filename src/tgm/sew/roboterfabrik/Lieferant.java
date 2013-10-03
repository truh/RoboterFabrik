package tgm.sew.roboterfabrik;

import java.util.Random;

public class Lieferant implements Stoppable {

	private LagerMitarbeiter lagerMitarbeiter;
    private Random random;
    private boolean stop;

	public Lieferant(LagerMitarbeiter lagerMitarbeiter) {
        this.lagerMitarbeiter = lagerMitarbeiter;
        this.stop = false;
        this.random = new Random(); //sucht sich ohne Parameter einen Seed basierend auf der aktuellen Nanoskeunde aus
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
	    while(!stop)
        {
            switch (random.nextInt(8)) //0-7
            {   //es werden mehr Arme und Augen als Kettenantriebe und Rümpfe gebraucht
                case 0:case 4: //Arm
                    Arm arm = new Arm();
                    lagerMitarbeiter.einlagern(arm);
                    break;
                case 1:case 5: //Auge
                    Auge auge = new Auge();
                    lagerMitarbeiter.einlagern(auge);
                    break;
                case 2: //Kettenantrieb
                    Kettenantrieb kettenantrieb = new Kettenantrieb();
                    lagerMitarbeiter.einlagern(kettenantrieb);
                    break;
                case 3: //Rumpf
                    Rumpf rumpf = new Rumpf();
                    lagerMitarbeiter.einlagern(rumpf);
                    break;
                case 6:case 7://es besteht auch eine chance das nichts hergestellt wird
                    //do nothing
            }

            try
            {
                Thread.sleep(random.nextInt(500) + 250);
            } catch (InterruptedException e)
            {
                stop = true;
            }
        }
	}
}
