
import java.util.*;
import java.util.Iterator;

/**
 * That class does some implementations on IntBag!
 * @author Emre Karatas-22001641
 * @version 1.2 - 30.06.2021
 */
public class IntBag
{
    // Constants
    final int SENTINEL = -1;

    // Properties
    private int[] bag;
    // TO USE IN ITERATORS
    private int m = 1;

    // Constructors
    public IntBag()
    {
        bag = new int[4];

    }
    public IntBag(int m)
    {
        bag = new int[4];
        this.m =m;
        Iterator iter = new IntBagStepIterator(m);
    }

    // LAB03 B
    public  class IntBagIterator implements Iterator
    {
        int index;
        IntBag aBag;
        public IntBagIterator()
        {
            aBag = new IntBag();
            index = 0;
            for ( int i = 0; i < 3; i++)
            {
                aBag.addEnd(i);
            }
        }

        public boolean hasNext() {
            if ( index < aBag.getSize() )
            {
                return true;
            }
            return false;
        }
        public void remove()
        {
            aBag.removeParticularIndex(index);
        }

        public Object next()
        {
            return nextInt();
        }

        public int nextInt() {

            if (  hasNext() )
            {

                return aBag.getValueAtIndex(index++);

            }
            else
            {

                return -1;
            }
        }
    }

    // LAB03 B
    public class IntBagStepIterator implements Iterator
    {
        private int m;
        private int index;
        IntBag newBag = new IntBag();
        IntBagStepIterator(int m)
        {
            this.m = m;
            index = 0;
            for ( int i = 0; i <= 20; i++)
            {
                newBag.addEnd(i);
            }
        }

        public boolean hasNext()
        {
            if(index < newBag.getSize())
            {
                return true;
            }
            return false;
        }

        public Object next()
        {
            if ( hasNext() )
            {
                if ( index % getM() == 0 )
                {
                    newBag.removeParticularIndex(index++);
                    return newBag.getValueAtIndex(index);
                }
                else
                {
                    index++;
                    return "";
                }

            }
            else
            {
                return null;
            }
        }

        @Override
        public void remove()
        {
            removeParticularIndex(index);
        }


    }


    public Iterator iterator()
    {
        if ( getM() != 1)
        {
            Iterator stepIterator = new IntBagStepIterator(getM());
            return stepIterator;
        }
        Iterator iterator = new IntBagIterator();
        return iterator;

    }



    // Methods

    public int getM()
    {
        return m;
    }
    /**
     * This method adds a value at the end.
     */
    public void addEnd( int value )
    {
        // if array is not full yet
        if ( bag[bag.length - 1] != SENTINEL )
        {
            // to check if we find -1 or not
            boolean sentinelOccured = false;
            for ( int i = 0; i < bag.length; i++ )
            {
                if ( bag[i] == SENTINEL )
                {
                    // pushing -1 to next slot in array
                    bag[i] = value;
                    bag[i + 1] = SENTINEL;
                    sentinelOccured = true;
                    break;
                }
            }
            // if array is completely empty
            if ( sentinelOccured == false )
            {
                bag[0] = value;
                bag[1] = SENTINEL;
            }
        }
        // if array is already full
        else
        {
            // creating new array whose size is doubled
            int[] newArray = new int[bag.length * 2];
            // adding value at the end
            bag[bag.length - 1] = value;
            for ( int i = 0; i < bag.length; i++ )
            {
                //copying array
                newArray[i] = bag[i];
            }
            // pointing -1 to last element to show array is over
            newArray[bag.length] = SENTINEL;
            // referencing
            bag = newArray;

        }
    }

    /**
     * This method adds a value at the specific point in array.
     */
    public void addSpecIndex( int value, int index )
    {
        // if array is not full yet
        if ( bag[ bag.length - 1] != SENTINEL )
        {
            int[] newArray = new int[bag.length];
            int i;
            for ( i = 0; i < bag.length; i++ )
            {
                // copying array
                if ( i < index )
                {
                    newArray[i] = bag[i];
                }
                // getting the value to index in array
                else if ( i == index )
                {
                    newArray[i] = value;
                }
                else
                {
                    //pushing values up to top
                    newArray[i] = bag[i - 1];
                    if ( bag[i] == SENTINEL )
                    {
                        break;
                    }
                }
            }
            newArray[i + 1] = SENTINEL;
            bag = newArray;

        }
        // if array is already full
        else
        {
            int[] newArray = new int[bag.length * 2];
            for ( int i = 0; i < bag.length; i++ )
            {
                // copying array
                if ( i < index )
                {
                    newArray[i] = bag[i];
                }
                // getting the value to index in array
                else if ( i == index )
                {
                    newArray[i] = value;
                }
                //copying array
                else
                {
                    newArray[i + 1] = bag[i];
                }
            }
            // last value should be -1
            newArray[bag.length] = SENTINEL;
            // referencing
            bag = newArray;
        }


    }

    /**
     * This method removes a value at the specific point in array.
     */
    public void removeParticularIndex( int index )
    {
        boolean sentinelFound = false;
        int i;
        for (  i = 0; i < bag.length; i++ )
        {
            if ( bag[i] == SENTINEL )
            {
                // if we found -1.
                sentinelFound = true;
            }
        }
        // when we found -1, get the last element into the index to remove.
        if (sentinelFound)
        {
            bag[index] = bag[i - 1];
        }

    }

    /**
     * This method finds whether the value exists or not in array.
     */
    public boolean containsOrNot( int value )
    {
        for ( int i = 0; i < bag.length; i++ )
        {
            if ( bag[i] == value )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Tostring method to print.
     */
    @Override
    public String toString()
    {
        // printing first element
        String output = bag[0] + ", ";
        for ( int i = 1; i < bag.length; i++ )
        {
            // to not show -1 or 0s
            if ( bag[i] > 0 )
            {
                output += bag[i] + ", ";
            }
        }
        return output;

    }

    /**
     * This method gives a value at specific index.
     */
    public int getValueAtIndex( int index )
    {
        return bag[index];
    }

    public int getSize()
    {

        int count = 0;
        for ( int i = 0; i < bag.length; i++ )
        {
            count++;
        }
        return count;
    }

    /**
     * This method removes all instance of value in array.
     */
    public void removeAll( int value )
    {
        int[] newArray = new int[bag.length];
        for ( int i = 0; i < bag.length; i++ )
        {
            // we do not add the value && we do not want to show -1 and 0s.
            if ( bag[i] != value && bag[i] > 0 )
            {
                newArray[i] = bag[i];
            }
        }
        bag = newArray;
    }

}