import java.util.Map;
import javafx.scene.chart.XYChart;
public class Charter
{
    private Map<String,Number> values;
    private String dataName;
    public Charter(SortingTechnique dataName,Map<String,Number> values)
    {
        this.values=values;
        this.dataName=SortingTechnique.getName(dataName);
    }

    public XYChart.Series<String,Number> getData()
    {
        XYChart.Series<String,Number> data=new XYChart.Series<>();
        for(Map.Entry<String,Number> entry:this.values.entrySet())
        {
            data.getData().add(new XYChart.Data<String,Number>(entry.getKey(),entry.getValue()));
        }
        data.setName(this.dataName);




        return data;
    }

}