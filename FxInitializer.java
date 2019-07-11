import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.util.Map;
public class FxInitializer
{
    private static FxInitializer instance;
    private Stage primaryStage;
    
    private FxInitializer(Stage primaryStage)
    {
        this.primaryStage=primaryStage;    
    }
    
    
    public static FxInitializer getInstance(Stage stageRef)
    {
        return instance==null?instance=new FxInitializer(stageRef):instance;
    }

    public void init(String programTitle,String xAxisTitle,String yAxisTitle,int width,int height,Map<SortingTechnique,Map<String,Number>> data)
    {
        primaryStage.setTitle(programTitle);
        Pane pane=this.getLineChartPane(xAxisTitle,yAxisTitle,data);
        Scene scene=new Scene(pane,width,height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private LineChart<String,Number> getLineChart(CategoryAxis xAxis,NumberAxis yAxis,Map<SortingTechnique,Map<String,Number>> data)
    {
        LineChart<String,Number> lineChart=new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("Sorting Techniques Chart");
        for(Map.Entry<SortingTechnique,Map<String,Number>> entry:data.entrySet())
        {
            lineChart.getData().add(new Charter(entry.getKey(),entry.getValue()).getData());
        }
        // for(int i=0;i<data.length;i++)
        // {
        //     lineChart.getData().add(new Charter(data[i],).getData());
        // }
        return lineChart;
    }
    private Pane getLineChartPane(String xAxisTitle,String yAxisTitle,Map<SortingTechnique,Map<String,Number>> data)
    {
        Pane pane=new StackPane();
        
        NumberAxis yAxis=new NumberAxis();
        CategoryAxis xAxis=new CategoryAxis();
        
        xAxis.setLabel(xAxisTitle);
        yAxis.setLabel(yAxisTitle);

        LineChart<String,Number> lineChart=this.getLineChart(xAxis,yAxis,data);
         
         
        pane.getChildren().add(lineChart);
        
        return pane;
    }

}