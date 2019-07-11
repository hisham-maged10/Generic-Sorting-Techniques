import java.util.Map;
import javafx.stage.Stage;
public class Tester
{
    private Tester(){}
    public static void test(Stage primaryStage,int n,int width,int height)
    {
        Movie[] movies=HelperMethods.loadMovies("./data/Movies3.csv");
        Map<SortingTechnique,Map<String,Number>> map=HelperMethods.getReadings(movies,Movie.class,n);;
        FxInitializer.getInstance(primaryStage).init("Sorting Techniques Program","Number of Items","Time(sec)",width,height,map);
        // for(int i=0;i<100;i++)
        //     System.out.println(movies[i+4899]);
        // SortClient.getInstance().printUnSortedArray();
        // SortClient.getInstance().sort(SortingTechnique.BUBBLE);
        //  SortClient.getInstance().printTimeTechnique();
        // Movie[] movies=HelperMethods.loadMovies("./data/Movies3.csv");
        //  SortClient<String> client = new SortClient<>(new String[]{"Zoro","Yaya","Hisham","Flash","Atom"});
        //  SortClient<Integer> client = new SortClient<>(new Integer[]{120,91,8,7,6,5,4,3,2,1});
        // HelperMethods.getReadings(movies,Movie.class,10);
        // movies=HelperMethods.loadMovies("./data/Movies.csv");
        // SortClient<Movie> client = new SortClient<>(movies);
        // Movie[][] moviesReadings=new Movie[10][];
        // for(int i=1;i<=moviesReadings.length;i++)
        // {
        //     moviesReadings[i-1]=new Movie[i*1000];
        //     System.arraycopy(movies,0,moviesReadings[i-1],0,i*1000);
        //     SortClient<Movie> temp=new SortClient<>(moviesReadings[i-1]);
        //     System.out.println("Sorting "+i+"k items");
        //     temp.sort(SortingTechnique.MERGE);
        //     map.get(SortingTechnique.MERGE).put(i+"k",temp.getTimeValue());
        //     temp.printTimeTechnique();
        // }
        // System.out.println(map);
        // client.printUnSortedArray();
        // client.sort(SortingTechnique.INSERTION);
        // client.printSortedArray();
        // client.printTimeTechnique();


    }
    // private static void getReadings(Movie[] movies)
    // {
    //     Movie[][] moviesReadings=new Movie[10][];
    //     for(int i=1;i<=moviesReadings.length;i++)
    //     {
    //         moviesReadings[i-1]=new Movie[i*1000];
    //         System.arraycopy(movies,0,moviesReadings[i-1],0,i*1000);
    //         SortClient<Movie> temp=new SortClient<>(moviesReadings[i-1]);
    //         System.out.println("Sorting "+i+"k items");
    //         for(SortingTechnique technique:SortingTechnique.values())
    //         {
    //             temp.sort(technique);
    //             readings.get(technique).put(i+"k",temp.getTimeValue());
    //         }
    //         temp.printTimeTechnique();
    //     }
    //     System.out.println(readings);
    // }
}