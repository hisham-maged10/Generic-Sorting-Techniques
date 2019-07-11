import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public final class SortBuilder<T extends Comparable<? super T>> implements SortInterface<T>{

    // public static SortBuilder instance; 
    private T[] usedArr;
    private double timeTaken;
    private SortingTechnique lastUsedTechnique;
    {
        this.timeTaken=0.0;
        this.lastUsedTechnique=null;
    }
    // private SortBuilder(String filePath)
    public SortBuilder(T[] arr)
    {
        this.initialize(arr);
    }
    public String getSortMessage()
    {
        return "Last used Technique: "+(this.lastUsedTechnique==null?"none\n":SortingTechnique.getName(this.lastUsedTechnique))+"\n"+"Time Taken: "+String.format("%f",this.timeTaken);
    }

    public double getTimeValue()
    {
        return this.timeTaken;
    }
    // public static SortBuilder<T> getInstance()
    // {
    //     return instance==null? instance = new SortBuilder("./data/Movies.csv"):instance;
    // }
    public T[] getSafeUnsortedArray(T[] arr)
    {
        // Movie[] referenceSafeArray=new Movie[this.usedArr.length];
        // System.arraycopy(this.usedArr,0,referenceSafeArray,0,this.usedArr.length);
        List<T> referenceSafeArray=new ArrayList<>(Arrays.asList(this.usedArr));
        return referenceSafeArray.toArray(arr);
    }
    private void initialize(T[] arr)
    {
         if(arr==null)
            throw new NullPointerException("array to be sorted can't be null");
        if(arr.length<2)
            throw new IllegalArgumentException("Array size must be atleast 2 ");
        // System.arraycopy(arr,0,usedArr,0,arr.length);
        this.usedArr=new ArrayList<>(Arrays.asList(arr)).toArray(arr);
    }
    @Override
    public T[] sort(T[] arr,SortingTechnique technique,Comparator<? super T> comparator)
    {
        // Movie[] tempArr=new Movie[this.usedArr.length];
        List<T> tempArr=new ArrayList<>(Arrays.asList(this.usedArr));
        // Collections.shuffle(tempArr);
        
        // System.out.println(tempArr);
        // System.arraycopy(this.usedArr,0,tempArr,0,this.usedArr.length);
        long startTime=System.nanoTime();
        long endTime=HelperMethods.<T>sortArr(tempArr,technique,null);
        // this.timeTaken=Math.pow((endTime-startTime),-6);
        this.timeTaken=(endTime-startTime)/1000000000D;
        this.lastUsedTechnique=technique;
        return tempArr.toArray(arr);
    }
    
}