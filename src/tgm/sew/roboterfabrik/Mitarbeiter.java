package tgm.sew.roboterfabrik;
public interface Mitarbeiter extends Runnable, Stoppable {


     public int getId();

    public Sekretariat getSekretariat();

}