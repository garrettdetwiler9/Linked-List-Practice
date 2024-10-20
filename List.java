/**
 * List.java - creates the constructors and methods for editing 
 * single linked lists 
 * @author Tony Smith, Garrett Detwiler
 * @version 15 October 2023
 */
public class List
{
    private Item list;

    public List()
    {
        list = null;
    }

    public void insertFirst(int i)
    {
        Item r = new Item(i);
        r.next = list;
        list = r;
    }

    public int removeFirst()
    {
        if (isEmpty()) {
            System.out.println("Error in removeFirst(): list is empty");
            System.err.println("Error in removeFirst(): list is empty");
            System.exit(1);
        }
        Item r = list;
        int x = r.info;
        list = r.next;
        return x;
    }

    public Boolean isEmpty()
    {
        return list == null;
    }

    public int count()
    {
        int count = 0;
        Item r = list;
        while (r != null) {
            ++count;
            r = r.next;
        }
        return count;
    }
    
    public void insertAfter(Item p, int i)
    {
        if (isEmpty() || p == null) {
            System.out.println("Error in insertAfter(): list is empty or p not set");
            System.err.println("Error in insertAfter(): list is empty or p not set");
            System.exit(2);
        }
        Item r = new Item(i);
        r.next = p.next;
        p.next = r;
    }

    public int deleteAfter(Item p)
    {
        if (p.next == null || p == null) {
            System.out.println("Error in deleteAfter(): p not set or is last item");
            System.err.println("Error in deleteAfter(): p not set or is last item");
            System.exit(3);
        }
        Item r = p.next;
        int x = r.info;
        p.next = r.next;
        return x;
    }

    //returns reference to first occurrence of i in list
    //returns null if not found
    public Item find(int i)
    {
        if (isEmpty()) {
            System.out.println("Error in find(): list is empty");
            System.err.println("Error in find(): list is empty");
            System.exit(4);
        }
        Item r = list;
        while (r != null && r.info != i)
            r = r.next;
        return r;
    }
    
    //WRITE ALL 8 OF YOUR NEW METHODS DOWN HERE
    //You are required to Javadoc comment only these new methods
    /**
     * @param ArrayConstructor creates a list with the values of the inputted array
     */
    public List(int[] array)
    {
        if (array == null || array.length == 0)
        {
            list = null; 
        }
        else
        {
            list = new Item(array[0]); //input first array element
            Item r = list;
            for (int i = 1; i < array.length; i++)
            {
                r.next = new Item(array[i]); //input each array element
                r = r.next;
            }
        }
    }
    /**
     * @param toString returns the list's contents as a String
     * @return list's contents in String form
     */
    public String toString()
    {
        Item r = list;
        StringBuilder str = new StringBuilder();
        while (r != null)
        {
            str.append(r.info); //build onto the string with each item node
            if (r.next != null)
            {
                str.append(", "); //separate the nodes in the string
            }
            r = r.next;
        }
        return str.toString();
    }
    /**
     * @param findLast tells you the last value in the list
     * @return the last value of the list
     */
    public int findLast()
    {
        Item r = list;
        while (r != null) //go to the end of the list
        {
            r = r.next;
        }
        return r.info;
    }
    /**
     * @param insertLast inserts an integer into the last position of the list
     */
    public void insertLast(int num)
    {
        Item newItem = new Item(num);
        
        if (list == null)
        {
            list = newItem;
        }
        else
        {
            Item r = list;
            while (r.next != null) //go to the end of the list
            {
                r = r.next;
            }
            r.next = newItem; // set new last value
        }
    }
    /**
     * @param removeLast removes the last item in the list
     */
    public void removeLast()
    {
        Item removed = list; 
        if (list == null)
        {
            return;
        }
        else
        {
            Item r = list;
            while (r.next.next != null) //go to the end of the list
            {
                r = r.next;
            }
            r.next = null; //remove the last item
        }
    }
    /**
     * @param copy creates a copy of the list
     * @return the copied list
     */
    public List copy()
    {
        List copy = new List();
        if (list == null)
        {
            return copy;
        }
        else
        {
            Item og = list; // the original gangster list is transferred to a new item
            Item r = new Item(og.info); //set first value
            copy.list = r; //pair the lists
            
            while (og.next != null) //copy each item node to the copied list
            {
                og = og.next;
                r.next = new Item(og.info);
                r = r.next;
            }
            
            return copy;
        }
    }
    /**
     * @param join creates a union of the two inputted lists
     * @return the joined list
     */
    public List join(List list2)
    {
        List join = new List();
        if (list2 == null || list2.list == null) //if list2 is empty, return list
        {
            join.list = list;
            return join;
        }
        if (list == null) //if list is empty, return list2
        {
            join.list = list2.copy().list;
            return join;
        }
        else
        {
            join.list = copy().list; //make the first half of the list the contents
                                     //of the first list
            Item r = join.list; // pair the lists
            while (r.next !=  null) //go to the end of join.list
            {
                r = r.next;
            }
            
            r.next = list2.copy().list; //copy second list to join.list
            
            return join; 
        }
    }
    /**
     * @param intersect creates a list that only includes the values
     * that both lists possess
     * @return the intersected list
     */
    public List intersect(List list2)
    {
        List intersect = new List();
        if (list2 == null || list2.list == null || list == null)
        {
            return intersect;
        }
        else
        {
            Item both = new Item(); //initialize intersect Item
            intersect.list = both; //initialize intersect List with intersect Item
            Item first = list; //pair first with list
            while (first != null)
            {
                Item second = list2.list; //pair second with list2.list
                boolean isIncluded = false; //will tell if the value will be included
                                            //for each value of first
                boolean isZero = false; //will tell if the value kept is a zero
                while (second != null)
                {
                    if (second.info == first.info) //if the same value, set both.info
                                                   //that value and set isIncluded true
                    {
                        if (second.info == 0)
                        {
                            isZero = true;
                        }
                        isIncluded = true;
                        both.next = new Item();
                        both.info = second.info;
                    }
                    second = second.next;
                }
                if (both != null && (both.info != 0 || isZero)) //if a common value was
                                                    //was found, move both to next node
                {
                    both = both.next;
                }
                first = first.next;
            }
            
            intersect.removeLast(); //remove the last value, as it will always 
                                    //be an unwanted zero
            
            return intersect;
        }
    }
}