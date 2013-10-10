package tgm.sew.roboterfabrik;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SpielzeugRoboter sind das Produkt der Fabrik
 * sie werden vom MontageMitarbeiter zusammengebaut
 */
public class SpielzeugRoboter implements Stringifyable {
	private static String TID_PREFIX= "Threadee-ID";

	private int id;
	private int mitarbeiterId;

    //Bauteile
    private Antenne antenne;
    private Arm arm1;
    private Arm arm2;
	private Auge auge1;
	private Auge auge2;
    private Greifer greifer1;
    private Greifer greifer2;
    private Kettenantrieb kettenantrieb;
    private Rumpf rumpf;

	private Sekretariat sekretariat;

	private Logger logger;

    /**
     * Defaultkonstuktor
     */
    public SpielzeugRoboter() {
        //Logger erzeugen
        this.logger = new LoggerFactory().getLogger(SpielzeugRoboter.class);

        //Bauteile
        this.antenne = new Antenne();
        this.arm1 = new Arm();
        this.arm2 = new Arm();
        this.auge1 = new Auge();
        this.auge2 = new Auge();
        this.greifer1 = new Greifer();
        this.greifer2 = new Greifer();
        this.kettenantrieb = new Kettenantrieb();
        this.rumpf = new Rumpf();
    }

    /**
     * Konstruktor für die Verwendung durch den MontageMitarbeiter
     *
     * @param id Id des Threadees
     * @param mitarbeiterId Id des MontageMitarbeiters der den Roboter montiert hat
     * @param auge1 Bauteil Auge
     * @param auge2 Bauteil Auge
     * @param rumpf Bauteil Rumpf
     * @param kettenantrieb Bauteil Kettenantrieb
     * @param arm1 Bauteil Arm
     * @param arm2 Bauteil Arm
     * @param greifer1 Bauteil Greifer
     * @param greifer2 Bauteil Greifer
     * @param antenne Bauteil Antenne
     */
	public SpielzeugRoboter(int id, int mitarbeiterId, Auge auge1, Auge auge2, Rumpf rumpf, Kettenantrieb kettenantrieb, Arm arm1, Arm arm2,Greifer greifer1,Greifer greifer2,Antenne antenne) {
		//Defaultkonstuktor
        this();

        //Id's
		this.id = id;
		this.mitarbeiterId = mitarbeiterId;

        //Bauteile
        this.antenne = antenne;
        this.arm1 = arm1;
        this.arm2 = arm2;
		this.auge1 = auge1;
		this.auge2 = auge2;
        this.greifer1 = greifer1;
        this.greifer2 = greifer2;
        this.kettenantrieb = kettenantrieb;
		this.rumpf = rumpf;

		logger.log(Level.INFO,"Threadee wurde erstellt");
	}


	/**
	 * Wandelt alles was im Roboter ist in ein String
	 */
	public String toCSV() {
		StringBuilder s = new StringBuilder();
		s.append("Threadee-ID").append(id).append(",");
		s.append("Mitarbeiter-ID").append(mitarbeiterId).append(",");
    	s.append(auge1.toCSV()).append(",");
    	s.append(auge2.toCSV()).append(",");
    	s.append(rumpf.toCSV()).append(",");
    	s.append(kettenantrieb.toCSV()).append(",");
    	s.append(arm1.toCSV()).append(",");
    	s.append(arm2.toCSV()).append(",");
    	s.append(greifer1.toCSV()).append(",");
    	s.append(greifer2.toCSV()).append(",");
    	s.append(antenne.toCSV());
    	
        return s.toString();
	}
	 /**
     * Fuegt die Zahlen im String von toCSV in das Attribut zahlen[] von den jeweiligen teil objekt hinzu;
     */
    @Override
    public void fromCSV(String csv){
    	String[] items = csv.replaceAll("Threadee-ID","").replaceAll("Mitarbeiter-ID","").replaceAll("Auge,","").replaceAll("Arm,","").replaceAll("Rumpf,","").replaceAll("Kettenantrieb,","").replaceAll("Greifer,","").replaceAll("Antenne,","").split(",");
    	int[] zahlen= new int[182];
    	int[] ein = new int[20];
    	for(int i = 0;i<zahlen.length;i++){
    		zahlen[i]=Integer.parseInt(items[i]);
    	}
    	int z = 2;
    	id = zahlen[0];
    	mitarbeiterId = zahlen[1];
    	for(int i=0;i<20;i++){
    		ein[i] = zahlen[z];
    		z++;
    	}
    	auge1.setZahlen(ein);
    	for(int i=0;i<20;i++){
    		ein[i] = zahlen[z];
    		z++;
    	}
    	auge2.setZahlen(ein);
    	for(int i=0;i<20;i++){
    		ein[i] = zahlen[z];
    		z++;
    	}
    	rumpf.setZahlen(ein);
    	for(int i=0;i<20;i++){
    		ein[i] = zahlen[z];
    		z++;
    	}
    	kettenantrieb.setZahlen(ein);
    	for(int i=0;i<20;i++){
    		ein[i] = zahlen[z];
    		z++;
    	}
    	arm1.setZahlen(ein);
    	for(int i=0;i<20;i++){
    		ein[i] = zahlen[z];
    		z++;
    	}
    	arm2.setZahlen(ein);
    	for(int i=0;i<20;i++){
    		ein[i] = zahlen[z];
    		z++;
    	}
    	greifer1.setZahlen(ein);
    	for(int i=0;i<20;i++){
    		ein[i] = zahlen[z];
    		z++;
    	}
    	greifer2.setZahlen(ein);
    	for(int i=0;i<20;i++){
    		ein[i] = zahlen[z];
    		z++;
    	}
    	antenne.setZahlen(ein);
    }

}
