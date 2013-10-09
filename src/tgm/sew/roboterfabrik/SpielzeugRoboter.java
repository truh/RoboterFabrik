package tgm.sew.roboterfabrik;
public class SpielzeugRoboter implements Stringifyable {
	private static String TID_PREFIX= "Threadee-ID";

	private int id;

	private int mitarbeiterId;

	private Auge auge1;

	private Auge auge2;

	private Rumpf rumpf;

	private Kettenantrieb kettenantrieb;

	private Arm arm1;

	private Arm arm2;

	private Greifer greifer1;
	
	private Greifer greifer2;
	
	private Antenne antenne;

	private Sekretariat sekretariat;

	public SpielzeugRoboter(int id, int mitarbeiterId, Auge auge1, Auge auge2, Rumpf rumpf, Kettenantrieb kettenantrieb, Arm arm1, Arm arm2,Greifer greifer1,Greifer greifer2,Antenne antenne) {
		this.id = id;
		this.mitarbeiterId = mitarbeiterId;
		this.auge1 = auge1;
		this.auge2 = auge2;
		this.rumpf = rumpf;
		this.kettenantrieb = kettenantrieb;
		this.arm1 = arm1;
		this.arm2 = arm2;
		this.greifer1 = greifer1;
		this.greifer2 = greifer2;
		this.antenne = antenne;
	}


	/**
	 * Wandelt alles was in den Teilen ist in ein String
	 */
	public String toCSV() {
		StringBuilder s = new StringBuilder();
		s.append("Threadee-ID"+id+",");
		s.append("Mitarbeiter-ID"+mitarbeiterId+",");
    	s.append(auge1.toCSV()+",");
    	s.append(auge2.toCSV()+",");
    	s.append(rumpf.toCSV()+",");
    	s.append(kettenantrieb.toCSV()+",");
    	s.append(arm1.toCSV()+",");
    	s.append(arm2.toCSV()+",");
    	s.append(greifer1.toCSV()+",");
    	s.append(greifer2.toCSV()+",");
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
