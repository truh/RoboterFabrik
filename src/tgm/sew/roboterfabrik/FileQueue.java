package tgm.sew.roboterfabrik;

import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.io.*;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

/**
 * @author jakob
 * @version 2013-10-08
 */
public class FileQueue<E extends Stringifyable> extends AbstractQueue<E>
{
    private String filePfad;
    private Class<E> genericType;
    private Logger logger;
    private String fullFilePath;

    /**
     * @param filePfad Pfad an dem FileStack seine Elemente speichern soll.
     * @throws java.io.IOException
     * @throws NullPointerException wenn genericType == null
     */
    public FileQueue(String filePfad, Class<E> genericType) throws IOException
    {
        this.logger = new LoggerFactory().getLogger(FileQueue.class);
        this.filePfad = filePfad;
        fullFilePath = filePfad + File.separator + genericType.getName() + ".csv";

        if(!new File(filePfad).exists()){
            logger.info("Verzeichnis: " + filePfad + " wird von FileQueue erstell.");
            logger.info("Verzeichnis erstellen erfolgreich? " + new File(filePfad).mkdir());
        }

        if(!new File(filePfad).exists()){
            logger.info("Datei " + fullFilePath + " wird von FileQueue erstellt.");
            new File(fullFilePath).createNewFile();
        }

        if(genericType == null) {
            throw new NullPointerException();
        }

        this.genericType = genericType;
    }

    /**
     * Liest Textdatei in Queue (LinkedList) und gibt Queue zurück
     *
     * @return Liste mit Elementen
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private synchronized Queue<E> readQueue() throws IOException, IllegalAccessException, InstantiationException
    {
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(fullFilePath));
        }
        catch (FileNotFoundException fnfe){
            return new LinkedList<E>();
        }
        String line;
        LinkedList<E> elemente = new LinkedList<E>();
        while((line=br.readLine())!=null)
        {
            E element = null;
            boolean success = false;

            while(!success){ //Diese Schleife produziert, fuer SpielzeugRoboter (fuer Teile nicht) eine endlosschleife
                success = true;

                try {
                    element = this.genericType.newInstance();
                }
                catch (InstantiationException ie) {
                    success = false;
                }
            }

            element.fromCSV(line);
            if(null != element.toCSV()) {
                elemente.add(element);
            }
        }

        br.close(); //Wichtig

        return elemente;
    }

    /**
     * Schreibt Queue mit Elementen in Datei
     *
     * @param elemente Liste welche in Datei geschrieben werden soll
     * @throws IOException
     */
    private synchronized void writeQueue(Queue<E> elemente) throws IOException
    {
        FileWriter fw = new FileWriter(fullFilePath);
        for(Iterator<E> it = elemente.iterator(); it.hasNext();){
            fw.write(it.next().toCSV() + "\n");
        }

        fw.close();
    }

    /**
     * Sollte einen Iterator über die Queue zureuckgeben
     *
     * @return tut es nicht wirklich
     */
    @Override
    public Iterator<E> iterator()
    {
        return null;
    }

    /**
     * Anzahl an Elementen in der Queue
     * @return Anzahl
     */
    @Override
    public int size()
    {
        try
        {
            return readQueue().size();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Versucht ein Element zu Queue hinzuzufuegen
     *
     * @param element das Element das hinzugefuegt werden soll
     * @return <tt>true</tt> wenn das Element zu Queue hinzugefügt wurde
     *         <tt>false</tt>
     */
    @Override
    public boolean offer(E element)
    {
        boolean status = false;
        try
        {
            Queue <E> inner = readQueue();
            status = inner.offer(element);
            writeQueue(inner);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return status;
    }

    /**
     * Entnimmt und entfernt das naechste Element der Queue
     *
     * @return das naechste Element der Queue oder null
     */
    @Override
    public E poll()
    {
        E element = null;
        try
        {
            Queue <E> inner = readQueue();
            element = inner.poll();
            writeQueue(inner);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return element;
    }

    /**
     * Entnimmt aber entfernt nicht das naechste Elemente der FileQueue
     *
     * @return Element oder null wenn kein Element vorhanden oder Exception
     */
    @Override
    public E peek()
    {
        try
        {
            return readQueue().peek();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
