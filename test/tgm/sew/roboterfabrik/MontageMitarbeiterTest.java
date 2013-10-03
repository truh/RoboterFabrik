package tgm.sew.roboterfabrik;

import static org.junit.Assert.*;

import org.junit.Test;

public class MontageMitarbeiterTest {

    @Test
    public void testMontageMitarbeiter() {
        fail("Not yet implemented");
    }

    @Test
    public void testStop() {
        fail("Not yet implemented");
    }

    @Test
    public void testRun() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetId() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetSekretariat() {
        fail("Not yet implemented");
    }

    @Test
    public void testSortieren() {
        int[] vorher = new int[]{11,24,3,4,25,6,8,8,9,10,11,12,13,14,15,16,17,18,195,5};
        int[] soll = new int[]{3,4,5,6,8,8,9,10,11,11,12,13,14,15,16,17,18,24,25,195};
        vorher = MontageMitarbeiter.sortieren(vorher);
        boolean korrekt = true;
        for(int i = 0; i<vorher.length;i++){
            if(soll[i]==vorher[i]){

            }else{
                korrekt = false;
            }
        }
        System.out.println(korrekt);
        assertTrue("Falls soll und nacher gleich funktioniert sortieren",korrekt);
    }

}