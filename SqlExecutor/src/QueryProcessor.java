import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueryProcessor {
public Map<Integer,String> specifiedQueryProcessor(QueryParser parser,Map<String,Integer> header){
	Map<Integer,String> rowdata=new HashMap<>();
	try{
	BufferedReader reader=new BufferedReader(new FileReader(parser.getPath()));
	
int index=0;
int colcount=parser.getCols().size();
String str=reader.readLine();
while(str!=null){
	String aftersplit[]=str.split(",");
	StringBuffer sb=new StringBuffer();
	for(int h=0;h<colcount;h++){
		sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
	rowdata.put(index,sb.toString());
	}
	index++;
	str=reader.readLine();
	}
		
			}

	catch(IOException io){
		
	}
return rowdata;
	
	}
public void whereQueryProcessor(QueryParser parser,Map<String,Integer> header){
}
}