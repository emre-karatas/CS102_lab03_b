import java.util.Iterator;

/**
 * This is a test class.
 * @author Emre Karatas-22001641
 * @version v1.0- 30.06.2021
 */
public class TestIterators
{
    public static void lineSeperator()
    {
        System.out.println("------------------------------");
    }
    public static void main(String[] args)
    {
        IntBag bag = new IntBag();
        // ITERATOR
        Iterator i;
        Iterator j;
        i =  bag.iterator();
        while ( i.hasNext() )
        {
            System.out.println( i.next() );
            j = (Iterator) bag.iterator();
            while ( j.hasNext() )
            {
                System.out.println( "--" + j.next() );
            }
        }

        lineSeperator();

        // STEP ITERATOR
        IntBag newBag = new IntBag(2);
        Iterator k;
        k = newBag.iterator();
        while(k.hasNext())
        {
            System.out.println(k.next());
        }
    }
}
