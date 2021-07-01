/**
 * This is a Iterator Interface which extends IntIterator
 * @author Emre Karatas-22001641
 * @version v 1.0 - 30.06.2021
 */
public interface Iterator extends IntIterator
{
    boolean hasNext();
    void remove();
    Object next();
}
