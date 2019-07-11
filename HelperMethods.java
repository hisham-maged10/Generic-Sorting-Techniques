import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Collections;
import static java.lang.System.out;
import static java.lang.System.err;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Arrays;
import java.lang.reflect.Array;
public class HelperMethods
{
    private static Map<SortingTechnique,Map<String,Number>> readings=new EnumMap<>(SortingTechnique.class);
    
    static
    {
        for(SortingTechnique technique:SortingTechnique.values())
            readings.put(technique,new LinkedHashMap<>());
    } 
    
    private HelperMethods(){}

    public static <T> boolean write(T[] arr,String filepath)
    {
        try(PrintWriter writer=new PrintWriter(filepath))
        {
            for(T e:arr)
                writer.println(e);
            return true;
        }catch(IOException ex)
        {
            err.println(ex.getMessage());
        }catch(Exception ex)
        {
            err.println(ex.getMessage());
        }catch(Throwable th)
        {
            th.printStackTrace();
        }
        finally{
            out.println("\n======================="+
                          "Write Method Terminated!"+
                        "\n=======================");
        }
        return false;
    } 

    public static <T> void printArray(T[] arr)
    {
        for(T e:arr)
            out.println(e);
    }
    public static void swap(List<?> arr,int i,int j)
    {
        // T temp=arr.get(i);
        // arr.set(i,arr.get(j));
        // arr.set(j,temp);
        Collections.swap(arr,i,j);
    }

    public static <T> T[] shuffleArr(List<? extends T> list,T[] arrType)
    {
        if(arrType==null)
            throw new NullPointerException();
        Collections.shuffle(list);
        return list.toArray(arrType);
    }

    public static Movie[] loadMovies(String filePath)
    {
        List<Movie> list=new ArrayList<>(HelperMethods.loadMoviesHelper(filePath).values());
        
        Collections.shuffle(list);
        
        Movie[] arr=list.toArray(new Movie[0]);
        // for(Movie e:arr)
        //     System.out.println(e);
        return arr;
    }

    private static Map<String,Movie> loadMoviesHelper(String filePath)
	{
		Map<String,Movie> movies=new HashMap<>();
		RecordParser parser = new RecordParser(new File(filePath));
		List<List<String>> info=getInfo(parser.getHeaders(),parser);
		String tempImdbID=null;
		for(int i=0,n=info.get(i).size();i<n;i++)
			movies.put((tempImdbID=info.get(0).get(i)),new Movie(tempImdbID,info.get(1).get(i),Integer.parseInt(info.get(2).get(i)),
					     info.get(3).get(i),info.get(4).get(i),info.get(5).get(i),
					     Integer.parseInt(info.get(6).get(i)),info.get(7).get(i)));
		return movies;
	}
    private static List<List<String>> getInfo(String[] headers,RecordParser parser)
	{
		List<List<String>> info=new ArrayList<>();
		for(String e:headers)
			info.add(parser.getItemsByHeader(e));
		return info;
	}
    public static <T extends Comparable<? super T>> long sortArr(List<T> arr,SortingTechnique technique,Comparator<? super T> comparator)
    {
        switch(technique)
        {
            case BUBBLE:return HelperMethods.<T>bubbleSort(arr,comparator);
            case INSERTION:return HelperMethods.<T>insertionSort(arr,comparator);
            case MERGE:return HelperMethods.<T>mergeSortMain(arr,comparator);
            case SELECTION:return HelperMethods.<T>selectionSort(arr);
            case HEAP:return HelperMethods.<T>heapSort(arr);
            case QUICK:return HelperMethods.<T>quickSortMain(arr);
            default:throw new IllegalArgumentException("Used Sorting Technique is not Applicable");
        }
    }
    private static <T extends Comparable<? super T>> long bubbleSort(List<T> arr,Comparator<? super T> comparator)
    {
        // System.out.println("in bubble");
        // for(int z=0;z<5;z++)
        //         System.out.println(arr.get(z));
        boolean doneSorting=false;
        for(int size=arr.size()-1;!doneSorting;--size)
        {
                //  System.out.println("Comparing");
            doneSorting=true;
            for(int i=0,n=size;i<n;i++)
            {
                if(comparator==null?arr.get(i).compareTo(arr.get(i+1))>0:comparator.compare(arr.get(i),arr.get(i+1))>0)
                {
                    HelperMethods.swap(arr,i,i+1);
                    doneSorting=false;
                }
            }
        }
        // System.out.println(arr);
        return System.nanoTime();
    }
     private static <T extends Comparable<? super T>> long selectionSort(List<T> unsortedarray) {
    	int length = unsortedarray.size();
    	for(int i = 0; i<length ; i++)
    	{
            int min_idx = i;
            for (int j = i+1; j < length; j++)
            {
            	if(unsortedarray.get(j).compareTo(unsortedarray.get(min_idx))<0)
            		min_idx = j;
            		
            }
            if(min_idx != i)
            swap(unsortedarray, min_idx, i);
            // System.out.println("Comparing");
    	}
      return System.nanoTime();
    }
    private static <T extends Comparable<? super T>> long insertionSort(List<T> arr,Comparator<? super T> comparator)
    {
        // System.out.println("in insertion");
        // for(int z=0;z<5;z++)
        //        System.out.println(arr.get(z));
        for(int i=1,n=arr.size();i<n;++i)
        {
            T marker=arr.get(i);
            int j;
            for(j=i-1;j>=0 && (comparator==null?arr.get(j).compareTo(marker)>0:comparator.compare(marker,arr.get(j))<0);--j)
            {
                arr.set(j+1,arr.get(j));
            }
            arr.set(j+1,marker);
        }
        // System.out.println(arr);
        return System.nanoTime();
    }

    private static <T extends Comparable<? super T>> long mergeSortMain(List<T> arr,Comparator<? super T> comparator)
    {
        int size;
        List<T> forMergeArr=new ArrayList<>(size=arr.size());
        mergeSortHelper(arr,forMergeArr,0,size-1,comparator);
        return System.nanoTime();
    }

    private static <T extends Comparable<? super T>> void mergeSortHelper(List<T> arr,List<T> forMergeArr,int startIndex,int endIndex,Comparator<? super T> comparator)
    {
        if(startIndex >= endIndex)
            return;
        int midIndex=(startIndex+endIndex)/2;
        mergeSortHelper(arr,forMergeArr,startIndex,midIndex,comparator);
        mergeSortHelper(arr,forMergeArr,midIndex+1,endIndex,comparator);
        merge(arr,forMergeArr,startIndex,endIndex,comparator);

    }
    private static <T extends Comparable<? super T>> void merge(List<T> arr,List<T> forMergeArr,int startIndex,int endIndex,Comparator<? super T> comparator)
    {
        //endIndex == rightArrBound
        forMergeArr.clear();
        int leftArrBound=(startIndex+endIndex)/2;
        int leftArrPtr=startIndex,rightArrPtr=leftArrBound+1;
        for(T leftElementPtr,rightElementPtr;leftArrPtr <= leftArrBound && rightArrPtr <= endIndex;)
        {
            if(comparator==null?
            (leftElementPtr=arr.get(leftArrPtr)).compareTo((rightElementPtr=arr.get(rightArrPtr)))<=0:
            comparator.compare((leftElementPtr=arr.get(leftArrPtr)),(rightElementPtr=arr.get(rightArrPtr)))<=0)
            {
                forMergeArr.add(leftElementPtr);
                ++leftArrPtr;
            }
            else
            {
                forMergeArr.add(rightElementPtr);
                ++rightArrPtr;
            }
        }
        if(leftArrPtr>leftArrBound)
            forMergeArr.addAll(arr.subList(rightArrPtr,endIndex+1));
        else if(rightArrPtr>endIndex)
            forMergeArr.addAll(arr.subList(leftArrPtr,leftArrBound+1));
        
        // System.out.println(forMergeArr);

        for(ListIterator<T> itArr=arr.listIterator(startIndex),itMerge=forMergeArr.listIterator();itArr.hasNext() && itMerge.hasNext();itArr.next(),itArr.set(itMerge.next())/*,System.out.println("working")*/);
    }
    private static <T extends Comparable<? super T>> long heapSort(List<T> unsortedarray)
	{
		int n = unsortedarray.size();
		buildMaxheap(unsortedarray);
		for(int i = n-1; i>=0 ;i--)
		{
			swap(unsortedarray, 0, i);
			maxHeapify(unsortedarray, i, 0);
		}
     return System.nanoTime();
	}
    private static <T extends Comparable<? super T>> void maxHeapify(List<T> unsortedarray,int n,int i)
	{
		//n is the size of the heap and i is the index which will be heapified.
		int l = 2*i +1; //add one because the array start from zero and l is the left child
		int r = 2*i +2; // r is the right child
		int largest = i; // assume parent is the largest
		if(l < n && unsortedarray.get(l).compareTo(unsortedarray.get(largest)) > 0) //if we still in the size of the heap and left is bigger than the parent
			largest = l; // then the largest will equal to l
		if(r < n &&  unsortedarray.get(r).compareTo(unsortedarray.get(largest)) > 0)
			largest = r;
		if(largest != i) // if largest was the right or left child
		{
			swap(unsortedarray,i,largest); //then exchange it
		    maxHeapify(unsortedarray, n, largest); // and heapify again of the new parent to check he is the largest or not
		}
			
	}
    private static <T extends Comparable<? super T>> void buildMaxheap(List<T> unsortedarray)
	{
		int n = unsortedarray.size();
		for(int i = n/2 - 1;i >= 0;i--)// i = n/2-1  to get the last parent on the heap and heapify it
			maxHeapify(unsortedarray,n,i); //then loop until we heapify all the parents
	}

     private static <T extends Comparable<? super T>> long quickSortMain(List<T> checkarray)
    {
    	List<T> unsortedarray;
         int length;
    	//to check if the array has no number or only one number then its sorted
        if (checkarray == null || checkarray.size() == 0){
        	 return System.nanoTime();
        }
        unsortedarray = checkarray;
        length = checkarray.size();
        quicksort(0, length - 1,unsortedarray);
        return System.nanoTime();
    }


    private static <T extends Comparable<? super T>> void quicksort(int low, int high,List<T> unsortedarray) {
        int l = low, r = high; //we take the first element and last element of the new array every time
        T pivot = unsortedarray.get(low + (high-low)/2);   //We get the pivot element from the middle of the list

       
        while (l <= r)  // Divide into two lists  
        {
            while (unsortedarray.get(l).compareTo(pivot)<0) { // If the current value from the left list is smaller than the pivot
                l++;                              // element then get the next element from the left list
            }
            while (unsortedarray.get(r).compareTo(pivot)>0) { // If the current value from the right list is larger than the pivot
                r--;                               // element then get the next element from the right list
            } 

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the values.
            // As we are done we can increase r and l to continue check for the small and highest than the pivot
            if (l <= r) {
                swap(unsortedarray,l, r);
                l++;
                r--;
            }
        }
        //if l exceeds than r then we already divide them into two arrays small than or higher than 
        //so we will recursion the new array 
        if (low < r)
            quicksort(low, r,unsortedarray);
        if (l < high)
            quicksort(l, high,unsortedarray);
    }

     @SuppressWarnings("unchecked")
    public static <T extends Comparable<? super T>> Map<SortingTechnique,Map<String,Number>> getReadings(T[] arr,Class<?> classType,int maxK)
    {
        if(maxK <=0 || maxK> arr.length)
            throw new IllegalArgumentException("given integer must be more the 0 and no more than given array length");
        List<List<T>> arrReadings=new ArrayList<>(maxK);
        SortClient<T> tempClient=null;
        // List<T> totalArr;
        // System.out.println("in get readings");
        List<T> tempList=Arrays.asList(arr);
        //  T[] tempArr=(T[]) Array.newInstance(classType,arr.length);
        for(int i=1;i<=maxK;i++)
        {
            arrReadings.add(new ArrayList<>(i*1000));
            // tempArr=HelperMethods.deepCopyArray(arr);
            tempList=Arrays.asList(arr);

            
            // System.arraycopy(arr,0,tempArr,0,arr.length);
            // totalArr=;
            arrReadings.get(i-1).addAll(new ArrayList<>(tempList.subList(0,i*1000)));
        //    System.err.println(i+"th time");
            // for(int z=0;z<5;z++)
            //     System.out.println(arrReadings.get(i-1).get(z));
            // tempArr=null;
            // for(T e:arrReadings.get(i-1))
            // System.out.println(e);
            // System.out.println(arrReadings.get(i-1).size());
            // System.out.println(arrReadings.get(i-1).contains(null));
            // for(T e:arrReadings.get(i-1).toArray(arr))
            //     if(e==null)
            //         System.out.println("found");
            // T[] arrshoof=arrReadings.get(i-1).toArray((T[]) Array.newInstance(classType, 0));
            // for(int z=0;z<5;z++)
            //     System.out.println(arrshoof[z]);
            System.out.println("Sorting "+i+"k items");
            for(SortingTechnique technique:SortingTechnique.values())
            {
                // System.out.println("here");
                tempClient=new SortClient<>(arrReadings.get(i-1).toArray((T[]) Array.newInstance(classType, 0)));
                tempClient.sort(technique);
                HelperMethods.readings.get(technique).put(i+"k",tempClient.getTimeValue());
            }

        }
        T[] sortedArr=tempClient.getSortedArray();
        System.arraycopy(sortedArr,0,arr,0,tempClient.getSortedArray().length);
        System.out.println(readings);
        return HelperMethods.readings;
    }
} 

