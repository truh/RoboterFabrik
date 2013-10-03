package tgm.sew.roboterfabrik;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

public class FileQueueTest
{
    FileQueue <Arm> fq;
    @Test
    public void testConstructor() throws IOException {
        Assert.assertNull("Die Variable sollte noch null sein.", fq);
        if(new File("build/test").exists()){
            Files.delete(FileSystems.getDefault().getPath("build", "test"));
        }
        fq = new FileQueue<Arm>("./build/test", Arm.class);
        Assert.assertNotNull("Sollte nicht mehr null sein", fq);
    }
    @Test
    public void testSize() {
        Assert.assertSame("Sollte noch keine Elemente enthalten", fq.size(), 0);
        fq.offer(new Arm());
        Assert.assertSame("Sollte ein Elemente enthalten", fq.size(), 1);
    }

    @Test
    public void testIsEmpty() {
        Assert.assertFalse("Sollte ein Element enthalten", fq.isEmpty());
        fq.clear();
        Assert.assertTrue("Sollte keine Elemente enthalten", fq.isEmpty());
    }

    @Test
    public void testContains() {
        Arm arm = new Arm();
        Assert.assertFalse("Sollte diesen Arm nicht enthalten", fq.contains(arm));
        fq.offer(arm);
        Assert.assertTrue("Sollte diesen Arm enthalten", fq.contains(arm));
    }

    @Test
    public void testIterator() {
        Iterator<Arm> iterator = fq.iterator();
        Assert.assertNotNull("Iterator sollte nicht null sein", iterator);
        Assert.assertTrue("Iterator sollte ein Element haben", iterator.hasNext());
    }

    @Test
    public void testToArray() {
        Object [] objs = fq.toArray();
        Assert.assertSame("Sollte ein Element enthalten", objs.length, 1);
        Assert.assertSame("Sollte vom gleichen Typ sein", objs[1].getClass(), FileQueue.class);
    }

    @Test
    public void testAdd() {
        Assert.assertTrue("Hinzufuegen eines Elementes sollte funktioniere", fq.add(new Arm()));
    }

    @Test
    public void testRemove() {
        Arm arm = new Arm();
        Assert.assertFalse("Arm noch nicht enthalten, kann nicht entfernt werden", fq.remove(arm));
        fq.offer(arm);
        Assert.assertTrue("Arm enthalten, kann entfernt werden", fq.remove(arm));
    }

    @Test
    public void testContainsAll() {
        Arm arm1 = new Arm();
        Arm arm2 = new Arm();

        ArrayList <Arm> arme = new ArrayList<Arm>();
        arme.add(arm1);
        arme.add(arm2);

        Assert.assertFalse("Sollten noch nicht enthalten sein", fq.containsAll(arme));
        fq.offer(arm1);
        fq.offer(arm2);
        Assert.assertTrue("Sollten enthalten sein", fq.containsAll(arme));
    }

    @Test
    public void testAddAll() {
        Arm arm1 = new Arm();
        Arm arm2 = new Arm();

        Assert.assertFalse("Keines der beiden Elemente sollte enthalten",fq.contains(arm1) || fq.contains(arm2));

        ArrayList <Arm> arme = new ArrayList<Arm>();
        arme.add(arm1);
        arme.add(arm2);

        Assert.assertTrue("Beiden Elemente sollte enthalten sein",fq.contains(arm1) && fq.contains(arm2));
    }

    @Test
    public void testRemoveAll() {
        Arm arm1 = new Arm();
        Arm arm2 = new Arm();
        ArrayList <Arm> arme = new ArrayList<Arm>();
        arme.add(arm1);
        arme.add(arm2);
        fq.addAll(arme);
        Assert.assertTrue("Elemente sollten enthalten sein", fq.containsAll(arme));
        Assert.assertTrue("Elemente sollten entfernt werden koennen",fq.removeAll(arme));
        Assert.assertFalse("Elemente sollten nicht mehr enthalten sein", fq.containsAll(arme));
    }

    @Test
    public void testRetainAll() {
        Arm arm1 = new Arm();
        Arm arm2 = new Arm();
        Arm arm3 = new Arm();
        ArrayList <Arm> arme = new ArrayList<Arm>();
        arme.add(arm1);
        arme.add(arm2);
        fq.addAll(arme);
        fq.add(arm3);
        Assert.assertTrue("Arm3 sollte enthalten sein", fq.contains(arm3));
        fq.retainAll(arme);
        Assert.assertFalse("Arm3 sollte nicht mehr enthalten sein", fq.contains(arm3));
    }

    @Test
    public void testClear() {
        Assert.assertTrue("Sollte Elemente enthalten", fq.size() > 0);
        fq.clear();
        Assert.assertSame("Sollte leer sein", fq.size(), 0);
    }

    @Test
    public void testOffer() {
        Assert.assertTrue("Sollte moeglich sein ein Element hinzuzufuegen", fq.offer(new Arm()));
    }

    @Test
    public void testRemoveHead() {
        fq.add(new Arm());
        int size = fq.size();
        fq.remove();
        Assert.assertTrue("Sollte moeglich sein das head Element zu entfernen", size > fq.size());
    }

    @Test
    public void testPoll() {
        fq.add(new Arm());
        int size = fq.size();
        Assert.assertNotNull("Sollte ein Element gepollt haben", fq.poll());
        Assert.assertTrue("Sollte ein Element weniger enthalten", fq.size() == size -1);
    }

    @Test
    public void testElement() {
        fq.add(new Arm());
        int size = fq.size();
        Assert.assertNotNull("Sollte ein Element geholt haben", fq.element());
        Assert.assertTrue("Sollte die gleiche Anzahl an Elementen enthalten", fq.size() == size);
    }

    @Test
    public void testPeek() {
        fq.add(new Arm());
        int size = fq.size();
        Assert.assertNotNull("Sollte ein Element geholt haben", fq.peek());
        Assert.assertTrue("Sollte die gleiche Anzahl an Elementen enthalten", fq.size() == size);
    }
}