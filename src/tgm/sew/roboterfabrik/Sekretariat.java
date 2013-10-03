package tgm.sew.roboterfabrik;
<<<<<<< HEAD
import java.util.HashSet;
// Id fuer Roboter und Mitarbeiter wird vergeben
=======

/**
 * Id fuer Roboter und Mitarbeiter wird vergeben
 */
>>>>>>> fc51f361b5c4e0f5c25fd1d367a4d4facf0b0eb8
public class Sekretariat {
	
	private int id=100;
	
	private int mitarbeiterId=100;
	
	public Sekretariat(){
		
	}
	/**
	 * eine eindeutige ID fuer den Threadee wird erstellt
	 * @return eindeutige ID fuer Threadee
	 */
	public int getUniqueID() {
		id++;
		return id;
	}
	/**
	 * eine eindeutige ID fuer den Mitarbeiter wird erstellt
	 * @return eindeutige ID fuer Mitarbeiter
	 */
	public int getUniqueMitarbeiterID() {
		mitarbeiterId++;
		return mitarbeiterId;
	}

}
