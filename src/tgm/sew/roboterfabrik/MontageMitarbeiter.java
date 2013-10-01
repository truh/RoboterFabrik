package tgm.sew.roboterfabrik;
public class MontageMitarbeiter implements Mitarbeiter {
	private String id;

	private Auge auge1;
	private Auge auge2;

	private Rumpf rumpf;

	private Kettenantrieb kettenantrieb;

	private Arm arm1;
	private Arm arm2;

	private Sekretariat sekretariat;

	private LagerMitarbeiter lagerMitarbeiter;
	
	private String mitarbeiterId;
	
	private int speicher;
	
	private SpielzeugRoboter spielzeugRoboter;
	
	/**
	 * 
	 * @param id
	 * @param sekretariat
	 * @param lagerMitarbeiter
	 */
	public MontageMitarbeiter(String id, Sekretariat sekretariat, LagerMitarbeiter lagerMitarbeiter) {
		this.id = id;
		this.sekretariat=sekretariat;
		this.lagerMitarbeiter=lagerMitarbeiter;
		

	}
	
	/**
	 * @see Stoppable#stop()
	 */
	public void stop() {

	}
	/**
	 * Hier werden die zahlen in den Einzelnen Teilen sortiert und danach der Roboter erstellt und vom lagermitarbeiter ins Lager
	 * gestellt
	 */
	public void run(){
		while(true){
			//toDO: Holt sich die Teile vom Lagermitarbeiter
			if((auge1 == null)||(auge2 == null)||(arm1 == null)||(arm2==null)||(rumpf==null)||(kettenantrieb==null)){
				
			}else{
			for(int i = 0;i<20;i++){
				for(int ii = 1;ii<21;ii++){      /*todo
					if(arm1.zahlen[i]>arm1.zahlen[ii]){
						speicher = arm1.zahlen[i];
						arm1.zahlen[i]=arm1.zahlen[ii];
						arm1.zahlen[ii]=speicher;
					}
					if(arm2.zahlen[i]>arm2.zahlen[ii]){
						speicher = arm2.zahlen[i];
						arm2.zahlen[i]=arm2.zahlen[ii];
						arm2.zahlen[ii]=speicher;
					}
					if(auge1.zahlen[i]>auge1.zahlen[ii]){
						speicher = auge1.zahlen[i];
						auge1.zahlen[i]=auge1.zahlen[ii];
						auge1.zahlen[ii]=speicher;
					}
					if(auge2.zahlen[i]>auge2.zahlen[ii]){
						speicher = auge2.zahlen[i];
						auge2.zahlen[i]=auge2.zahlen[ii];
						auge2.zahlen[ii]=speicher;
					}
					if(kettenantrieb.zahlen[i]>kettenantrieb.zahlen[ii]){
						speicher = kettenantrieb.zahlen[i];
						kettenantrieb.zahlen[i]=kettenantrieb.zahlen[ii];
						kettenantrieb.zahlen[ii]=speicher;
					}
					if(rumpf.zahlen[i]>rumpf.zahlen[ii]){
						speicher = rumpf.zahlen[i];
						rumpf.zahlen[i]=rumpf.zahlen[ii];
						rumpf.zahlen[ii]=speicher;
					}*/
				}
			}
			id = sekretariat.getUniqueID();
			mitarbeiterId = sekretariat.getUniqueMitarbeiterID();
			spielzeugRoboter = new SpielzeugRoboter(id, mitarbeiterId ,auge1, auge2,rumpf,kettenantrieb,arm1, arm2);
			// Hier kommt ne Methode rein wo der Lagermitarbeiter den Spielzeugroboter in das Lager gibt
			}
        }
	}

    @Override
    public int getId() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Sekretariat getSekretariat() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
