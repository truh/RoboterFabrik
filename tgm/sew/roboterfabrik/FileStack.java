package tgm.sew.roboterfabrik;

/**
 * FileStack implementiert ein Stack wobei die
 * Elemente direkt in einer Datei gespeichert sind.
 * Funktioniert nur mit Elementen des Typ Orderable
 */
public class FileStack extends java.util.Stack<Orderable> {

	private String filePfad;

	/**
	 * @param filePfad Pfad an dem FileStack seine Elemente speichern soll.
	 */
	public FileStack(String filePfad) {

	}
}
