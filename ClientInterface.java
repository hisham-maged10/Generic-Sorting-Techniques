import java.util.Comparator;
public interface ClientInterface<T extends Comparable<? super T>>
{    
    public void printSortedArray();
    public T[] sort(SortingTechnique technique);
    public T[] sort(SortingTechnique technique,Comparator<? super T> comparator);
    public void printUnSortedArray();
    public void printTimeTechnique();
    public double getTimeValue();
}