package tgm.sew.roboterfabrik;
public interface Stringifyable {
	public String toCSV();
    public void fromCSV(String csv);
}
