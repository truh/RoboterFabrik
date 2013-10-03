package tgm.sew.roboterfabrik;
public interface Stoppable extends Runnable {
    /**
     * Teilt einen Thread mit das er sicher beenden soll
     */
	public void stop();
}
