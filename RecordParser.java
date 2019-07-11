import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;
import java.util.LinkedHashSet;
public class RecordParser
{
	private Map<String,ArrayList<String>> parsedInfo;
	private String[] headers;
	public RecordParser(File file)
	{
		parsedInfo=parse(file==null?this.getFile():file);
	}
	public RecordParser()
	{
		this(null);
	}
	public Map<String,ArrayList<String>> getParsedInfo()
	{
		return this.parsedInfo;
	}
	public String[] getHeaders()
	{
		return this.headers;
	}
	public ArrayList<String> getItemsByHeader(String header)
	{
		return parsedInfo.get(header);
	}
	public File getFile()
	{
		JFileChooser chooser=new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileFilter(new FileNameExtensionFilter("CSV Files","csv","CSV","Csv"));
		try
		{
			System.out.println("Please choose a csv file to read from...");
			chooser.showOpenDialog(null);
			return chooser.getSelectedFile();
		}
		catch(NullPointerException ex)
		{
			System.out.println("Wrong response!");
			return getFile();
		}
	}
	private Map<String,ArrayList<String>> parse(File file)
	{
		Map<String,ArrayList<String>> tempMap=null;
		try(BufferedReader input=new BufferedReader(new FileReader(file)))
		{
			tempMap=this.getHeaders(input.readLine());
			String[] items=null;
			String line=null;
			while((line=input.readLine())!=null)
			{
				items=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				for(int i=0;i<this.headers.length;i++)
					tempMap.get(this.headers[i]).add(items[i]);
			}

		}catch(IOException ex)
		{
			System.out.println("There's something wrong with the csv file you chose, Terminating");
			System.exit(0);
		}
		return tempMap;
	}
	private HashMap<String,ArrayList<String>> getHeaders(String line)
	{
		HashMap<String,ArrayList<String>> tempMap=new HashMap<>();
		if(line!=null)
		{
		this.headers=line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		Set<String> headers=new LinkedHashSet<String>((int)(this.headers.length/0.75F));
		headers.addAll(Arrays.asList((this.headers)));
		for(String e:headers)
		{
			tempMap.put(e,new ArrayList<String>());
		}
		return tempMap;
		}
		else
			throw new NoSuchElementException("The csv file is empty");
	}
}
