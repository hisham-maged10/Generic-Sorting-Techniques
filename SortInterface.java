import java.util.Comparator;
public interface SortInterface<T extends Comparable<? super T>>
{
    public T[] sort(T[] arr,SortingTechnique technique,Comparator<? super T> comparator);
}
