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
public Map<Integer,String> whereQueryProcessor(QueryParser parser,Map<String,Integer> header){
	Map<Integer,String> rowdata=new HashMap<>();
	try{
	BufferedReader reader=new BufferedReader(new FileReader(parser.getPath()));
	String colname=parser.getCondition().get(0).getCol_name();
	String valu=parser.getCondition().get(0).getValue();
    int index=0,h=0;
    int colcount=parser.getCols().size();
    String str=reader.readLine();
    str=reader.readLine();
    if(parser.getCols().get(0).equals("*")){

    	while(str!=null){

		String aftersplit[]=str.split(",");
			if(aftersplit[header.get(colname)].equals(valu)){
											rowdata.put(index,str);
				h++;
	
				
			}index++;
			str=reader.readLine();	
	}
return rowdata;
    }
else
{
while(str!=null){
	String aftersplit[]=str.split(",");
	
		//if(Integer.parseInt(aftersplit[header.get(colname)])==20000){
	if(aftersplit[header.get(colname)].equals(valu)){
			StringBuffer sb=new StringBuffer();
	for( h=0;h<colcount;h++){
		sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
	rowdata.put(index,sb.toString());
	}
	}
	index++;
	str=reader.readLine();	
}
			}
}

	catch(IOException io){
		
	}
return rowdata;
}
}