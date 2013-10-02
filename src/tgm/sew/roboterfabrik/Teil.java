package tgm.sew.roboterfabrik;

public interface Teil extends Stringifyable {

	/** Zuffalszahlen um dem Montagemitarbeiter Arbeit zu bereiten */
	public int[] getZahlen();
	public void setZahlen(int[]zahlen);

}
