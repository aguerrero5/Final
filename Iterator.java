class Iterator <T> implements IteratorInterface<T> {

    T value;
    boolean done;
 
    public Iterator(T value)
    {
        this.value = value;
        this.done = false;
    }
    
    public boolean hasNext()
    {
        boolean result = !this.done;
   
        return result;
    }
    
    public T next()
    {
        this.done = true;
        T result = this.value;
       
        return result;
    }

}

class IteratorUtil<T> {

    public T getLast(Iterator iterator)
    {
        T value = null;
        boolean more = iterator.hasNext();
        while (more)
            
        {
            value = iterator.next();
            more = iterator.hasNext();
        }
        return value;
    }

}