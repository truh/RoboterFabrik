package tgm.sew.roboterfabrik;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class SpielzeugRoboterTest {
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
	
	private boolean t = true;

	private Sekretariat sekretariat;
	private int[] auge1z = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,21};
	private int[] auge2z = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,22};
	private int[] rumpfz = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,23};
	private int[] kettenantriebz = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,24};
	private int[] arm1z = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,25};
	private int[] arm2z = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,26};
	private int[] greifer1z = new int []{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,27};
	private int[] greifer2z = new int []{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,28};
	private int[] antennez = new int []{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,29};
	private int[] test = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,21};
	private SpielzeugRoboter robo ;
	
// Wollte hier BeforeClass machen aber er ich krieg eine Fehlermeldung die ich nicht beheben kann
	@Test
	public void testToCSV() {
		id = 123;
		mitarbeiterId = 231;
		auge1 = new Auge();
		auge2 = new Auge();
		arm1 = new Arm();
		arm2 = new Arm();
		rumpf = new Rumpf();
		kettenantrieb = new Kettenantrieb();
		greifer1 = new Greifer();
		greifer2 = new Greifer();
		antenne = new Antenne();
		auge1.setZahlen(auge1z);
		auge2.setZahlen(auge2z);
		arm1.setZahlen(arm1z);
		arm2.setZahlen(arm2z);
		rumpf.setZahlen(rumpfz);
		kettenantrieb.setZahlen(kettenantriebz);
		greifer1.setZahlen(greifer1z);
		greifer2.setZahlen(greifer2z);
		antenne.setZahlen(antennez);
		robo = new SpielzeugRoboter(id, mitarbeiterId, auge1, auge2, rumpf, kettenantrieb, arm1, arm2, greifer1, greifer2, antenne);
		String csv = "Threadee-ID123,Mitarbeiter-ID231,Auge,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,21,Auge,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,22,Rumpf,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,23,Kettenantrieb,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,24,Arm,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,25,Arm,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,26,Greifer,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,27,Greifer,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,28,Antenne,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,29";
		assertEquals("die beiden Strings müssen gleich sein",csv,robo.toCSV());
	}

	@Test
	public void testFromCSV() {
		String csv = "Threadee-ID123,Mitarbeiter-ID231,Auge,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,21,Auge,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,22,Rumpf,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,23,Kettenantrieb,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,24,Arm,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,25,Arm,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,26,Greifer,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,27,Greifer,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,28,Antenne,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,29";
		id = 123;
		mitarbeiterId = 231;
		auge1 = new Auge();
		auge2 = new Auge();
		arm1 = new Arm();
		arm2 = new Arm();
		rumpf = new Rumpf();
		kettenantrieb = new Kettenantrieb();
		greifer1 = new Greifer();
		greifer2 = new Greifer();
		antenne = new Antenne();
		robo = new SpielzeugRoboter(id, mitarbeiterId, auge1, auge2, rumpf, kettenantrieb, arm1, arm2, greifer1, greifer2, antenne);
		robo.fromCSV(csv);
		int[] auge1t = auge1.getZahlen();
		int[] auge2t = auge2.getZahlen();
		int[] arm1t = arm1.getZahlen();
		int[] arm2t = arm2.getZahlen();
		int[] rumpft = rumpf.getZahlen();
		int[] kettenantriebt = kettenantrieb.getZahlen();
		int[] greifer1t = greifer1.getZahlen();
		int[] greifer2t = greifer2.getZahlen();
		int[] antennet = antenne.getZahlen();
		for(int i =0; i<20;i++){
			if(auge1t[i] != test[i]){
				t = false;
			}	
		}
		test[19]++;
		for(int i =0; i<20;i++){
			if(auge2t[i] != test[i]){
				t = false;
			}	
		}
		test[19]++;
		for(int i =0; i<20;i++){
			if(rumpft[i] != test[i]){
				t = false;
			}	
		}
		test[19]++;
		for(int i =0; i<20;i++){
			if(kettenantriebt[i] != test[i]){
				t = false;
			}	
		}
		test[19]++;
		for(int i =0; i<20;i++){
			if(arm1t[i] != test[i]){
				t = false;
			}	
		}
		test[19]++;
		for(int i =0; i<20;i++){
			if(arm2t[i] != test[i]){
				t = false;
			}	
		}
		test[19]++;
		for(int i =0; i<20;i++){
			if(greifer1t[i] != test[i]){
				t = false;
			}	
		}
		test[19]++;
		for(int i =0; i<20;i++){
			if(greifer2t[i] != test[i]){
				t = false;
			}	
		}
		test[19]++;
		for(int i =0; i<20;i++){
			if(antennet[i] != test[i]){
				t = false;
			}	
		}
		test[19]++;
		assertTrue(t);
	}
}

