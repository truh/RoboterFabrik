package tgm.sew.roboterfabrik;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ArmTest {
	private Arm arm = new Arm();
	
	
	@Test
	public void testToCSV() {
		int[] set = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		arm.setZahlen(set);
		String test = "Arm,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20";
		String output = arm.toCSV();
		assertEquals("Test und output sollten gleich sein",test,output);
	}

	@Test
	public void testFromCSV() {
		boolean fr = true;
		int[] set = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		arm.setZahlen(set);
		String output = arm.toCSV();
		int[] seta = new int[]{5,2,3,4,5,6,7,8,3,10,11,12,13,14,15,16,17,18,19,21};
		arm.setZahlen(seta);// zahlen im auge wurden jetzt ver�ndert
		arm.fromCSV(output);// Jetzt ist zahlen wieder wie vorher
		int[] get = arm.getZahlen();
		for(int i = 0;i<20;i++){
			if(get[i]!=set[i]){
				fr = false;
			}
		}
		assertTrue("zahlen[] in auge sollte jetzt gleich sein wie das erste set",fr);
	}

	@Test
	public void testGetZahlen() {
		boolean fr = true;
		int[] test = arm.getZahlen();
		for(int i = 0;i<20;i++){
			if((test[i]<1)||(test[i]>999)){
				fr = false;
			}
		}
		assertTrue("Ob die Zahlen zuf�llig zwischen 1 und 999 erstellt wurden",fr);
	}

	@Test
	public void testSetZahlen() {
		boolean fr = true;
		int[] set = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		arm.setZahlen(set);
		int[] get = arm.getZahlen();
		for(int i = 0;i<20;i++){
			if(get[i]!=set[i]){
				fr = false;
			}
		}
		assertTrue("Falls die set methode nicht funktioniert ist fr false",fr);
	}

}
