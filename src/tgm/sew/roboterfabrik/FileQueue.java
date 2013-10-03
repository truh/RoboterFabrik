package tgm.sew.roboterfabrik;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;

/**
 * FileStack implementiert ein Stack wobei die
 * Elemente direkt in einer Datei gespeichert sind.
 * Funktioniert nur mit Elementen des Typ Orderable
 *
 * IO lose von http://rosettacode.org/wiki/Remove_lines_from_a_file#Java übernommen
 */
public class FileQueue <E extends Stringifyable> implements Queue<E> {

	private String filePfad;
    private Class<E> genericType;

	/**
	 * @param filePfad Pfad an dem FileStack seine Elemente speichern soll.
     * @throws IOException
     * @throws NullPointerException wenn genericType == null
	 */
	public FileQueue(String filePfad, Class<E> genericType) throws IOException
    {
        //Datei löschen wenn die existiert
        if(new File("build/test").exists()){
            Files.delete(FileSystems.getDefault().getPath("build", "test"));
        }
        this.filePfad = filePfad;

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
            br = new BufferedReader(new FileReader(filePfad));
        }
        catch (FileNotFoundException fnfe){
            return new LinkedList<E>();
        }
        String line;
        LinkedList<E> elemente = new LinkedList<E>();
        while((line=br.readLine())!=null)
        {
            E stringi = this.genericType.newInstance();
            stringi.fromCSV(line);
            if(null != stringi.toCSV()) {
                elemente.add(stringi);
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
        FileWriter fw = new FileWriter(filePfad);
        for(Iterator<E> it = elemente.iterator(); it.hasNext();){
            fw.write(it.next().toCSV() + "\n");
        }

        fw.close();
    }

    /**
     * Returns the number of elements in this collection.  If this collection
     * contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of elements in this collection
     */
    @Override
    public int size()
    {
        try
        {
            return this.readQueue().size();
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0; //TODO, verhalten dokumentieren
    }

    /**
     * Returns <tt>true</tt> if this collection contains no elements.
     *
     * @return <tt>true</tt> if this collection contains no elements
     */
    @Override
    public boolean isEmpty()
    {
        return this.size()==0;
    }

    /**
     * Returns <tt>true</tt> if this collection contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this collection
     * contains at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
     *
     * @param o element whose presence in this collection is to be tested
     * @return <tt>true</tt> if this collection contains the specified
     *         element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this collection
     *                              (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              collection does not permit null elements
     *                              (<a href="#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object o)
    {
        if(!(o instanceof Stringifyable)) {
            throw new ClassCastException();
        }

        if(o == null) {
            throw new NullPointerException();
        }

        try
        {
            return this.readQueue().contains(0);
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * @return an <tt>Iterator</tt> over the elements in this collection
     */
    @Override
    public Iterator<E> iterator()
    {
        try
        {
            return this.readQueue().iterator();
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     * <p/>
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     * <p/>
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this collection
     */
    @Override
    public Object[] toArray()
    {
        try
        {
            return this.readQueue().toArray();
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Not Implemented
     */
    @Override
    public <T> T[] toArray(T[] a)
    {
        throw new NotImplementedException();
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * <tt>true</tt> upon success and throwing an <tt>IllegalStateException</tt>
     * if no space is currently available.
     *
     * @param e the element to add
     * @return <tt>true</tt> (as specified by {@link java.util.Collection#add})
     * @throws IllegalStateException    if the element cannot be added at this
     *                                  time due to capacity restrictions
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null and
     *                                  this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this queue
     */
    @Override
    public boolean add(E e)
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        boolean b = queue.add(e);

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        return b;
    }

    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present (optional operation).  More formally,
     * removes an element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if
     * this collection contains one or more such elements.  Returns
     * <tt>true</tt> if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     *
     * @param o element to be removed from this collection, if present
     * @return <tt>true</tt> if an element was removed as a result of this call
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this collection
     *                                       (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       collection does not permit null elements
     *                                       (<a href="#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this collection
     */
    @Override
    public boolean remove(Object o)
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        boolean b = queue.remove(o);

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        return b;
    }

    /**
     * Returns <tt>true</tt> if this collection contains all of the elements
     * in the specified collection.
     *
     * @param c collection to be checked for containment in this collection
     * @return <tt>true</tt> if this collection contains all of the elements
     *         in the specified collection
     * @throws ClassCastException   if the types of one or more elements
     *                              in the specified collection are incompatible with this
     *                              collection
     *                              (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this collection does not permit null
     *                              elements
     *                              (<a href="#optional-restrictions">optional</a>),
     *                              or if the specified collection is null.
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection<?> c)
    {
        try
        {
            return this.readQueue().containsAll(c);
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;//Todo nochmal drüber nachdenken
    }

    /**
     * Adds all of the elements in the specified collection to this collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this collection, and this collection is
     * nonempty.)
     *
     * @param c collection containing elements to be added to this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     *                                       is not supported by this collection
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this collection
     * @throws NullPointerException          if the specified collection contains a
     *                                       null element and this collection does not permit null elements,
     *                                       or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this
     *                                       collection
     * @throws IllegalStateException         if not all the elements can be added at
     *                                       this time due to insertion restrictions
     * @see #add(Object)
     */
    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        boolean b = queue.addAll(c);

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        return b;
    }

    /**
     * Removes all of this collection's elements that are also contained in the
     * specified collection (optional operation).  After this call returns,
     * this collection will contain no elements in common with the specified
     * collection.
     *
     * @param c collection containing elements to be removed from this collection
     * @return <tt>true</tt> if this collection changed as a result of the
     *         call
     * @throws UnsupportedOperationException if the <tt>removeAll</tt> method
     *                                       is not supported by this collection
     * @throws ClassCastException            if the types of one or more elements
     *                                       in this collection are incompatible with the specified
     *                                       collection
     *                                       (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this collection contains one or more
     *                                       null elements and the specified collection does not support
     *                                       null elements
     *                                       (<a href="#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> c)
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        boolean b = queue.removeAll(c);

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        return b;
    }

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation).  In other words, removes from
     * this collection all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the <tt>retainAll</tt> operation
     *                                       is not supported by this collection
     * @throws ClassCastException            if the types of one or more elements
     *                                       in this collection are incompatible with the specified
     *                                       collection
     *                                       (<a href="#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this collection contains one or more
     *                                       null elements and the specified collection does not permit null
     *                                       elements
     *                                       (<a href="#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean retainAll(Collection<?> c)
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        boolean b = queue.retainAll(c);

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        return b;
    }

    /**
     * Removes all of the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this collection
     */
    @Override
    public void clear()
    {
        try
        {
            this.writeQueue(new LinkedList<E>());
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param e the element to add
     * @return <tt>true</tt> if the element was added to this queue, else
     *         <tt>false</tt>
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this queue
     * @throws NullPointerException     if the specified element is null and
     *                                  this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *                                  prevents it from being added to this queue
     */
    @Override
    public boolean offer(E e)
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        boolean b = queue.offer(e);

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;
        }
        return b;
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * @return the head of this queue
     * @throws java.util.NoSuchElementException
     *          if this queue is empty
     */
    @Override
    public E remove()
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        E element = queue.remove();

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            //return element;
        }
        return element;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns <tt>null</tt> if this queue is empty.
     *
     * @return the head of this queue, or <tt>null</tt> if this queue is empty
     */
    @Override
    public E poll()
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        E element = queue.poll();

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            //return element;
        }
        return element;
    }

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws java.util.NoSuchElementException
     *          if this queue is empty
     */
    @Override
    public E element()
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        E element = queue.element();

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            //return element;
        }
        return element;
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns <tt>null</tt> if this queue is empty.
     *
     * @return the head of this queue, or <tt>null</tt> if this queue is empty
     */
    @Override
    public E peek()
    {
        Queue<E> queue = null;
        try
        {
            queue = this.readQueue();
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        E element = queue.peek();

        try
        {
            this.writeQueue(queue);
        } catch (IOException e1)
        {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            //return element;
        }
        return element;
    }
}
