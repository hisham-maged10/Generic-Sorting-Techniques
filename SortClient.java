import java.util.Comparator;
import static java.lang.System.err;
import static java.lang.System.out;
public class SortClient<T extends Comparable<? super T>> implements ClientInterface<T> {

    // private static SortClient client=null;
    private SortBuilder<T> builder;
    private T[] arr;
    private T[] sortedArr;
    // {
    //     builder=new SortBuilder();
    // }
    // private SortClient(){}
    public SortClient(T[] arr){
        this.arr=arr;
        this.builder=new SortBuilder<T>(arr);
    }
    // public static <T extends Comparable<? super T>> SortClient<T> getInstance()
    // {
    //     return client==null?client=new SortClient<T>():client;
    // }

    @Override
    public T[] sort(SortingTechnique technique, Comparator<? super T> comparator) {
        return sortedArr=builder.sort(this.arr,technique,comparator);
    }
    @Override
    public T[] sort(SortingTechnique technique)
    {
      return this.sort(technique,null);
    }
    public T[] getSortedArray()
    {
        return this.sortedArr;
    }

    @Override
    public void printSortedArray() {
        if(this.isNull())
        {
            err.println("Sorting was not yet used, returning");
            return;
        }
        out.println("------ Sorted Array --------");
        HelperMethods.<T>printArray(this.sortedArr);
        out.println("==============================");

    }

    @Override
    public void printUnSortedArray() {
        out.println("------ Unsorted Array --------");
        HelperMethods.<T>printArray(this.builder.getSafeUnsortedArray(this.arr));
        out.println("==============================");
    }

    @Override
    public void printTimeTechnique() {
        out.println(builder.getSortMessage());
    }
    @Override
    public double getTimeValue()
    {
        return builder.getTimeValue();
    }
    
    private boolean isNull()
    {
        return sortedArr==null;
    }
}