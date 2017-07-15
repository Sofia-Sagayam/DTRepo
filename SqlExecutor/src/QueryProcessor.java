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
		String st=sb.toString();
	rowdata.put(index,st.substring(0, st.length()-1));
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
	BufferedReader reader=null;
	String colname="";
	String valu="";
    int index=0,h=0;
    int colcount=0;
    String str="";
	try{
	reader=new BufferedReader(new FileReader(parser.getPath()));
	colname=parser.getCondition().get(0).getCol_name();
	 valu=parser.getCondition().get(0).getValue();
     colcount=parser.getCols().size();
     str=reader.readLine();
    str=reader.readLine();
    if(parser.getCols().get(0).equals("*")){

    	while(str!=null){
if(parser.getCondition().get(0).getOperator().equals("=")){
		String aftersplit[]=str.split(",");
			if(aftersplit[header.get(colname)].equals(valu)){
											rowdata.put(index,str);
				h++;
	
				
			}index++;
			str=reader.readLine();	
	}}
    	
return rowdata;
    }
else
{
while(str!=null){
	String aftersplit[]=str.split(",");
	
	if(parser.getCondition().get(0).getOperator().equals("=")){
	if(aftersplit[header.get(colname)].equals(valu)){
			StringBuffer sb=new StringBuffer();
	for( h=0;h<colcount;h++){
		sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
		String st=sb.toString();
	rowdata.put(index,st.substring(0, st.length()-1));
	}
	}
	index++;
	str=reader.readLine();	}
	else if(parser.getCondition().get(0).getOperator().equals(">")){
		long res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
				if(Long.toString(res).equals(aftersplit[header.get(colname)])&& !Long.toString(res).equals(valu)){
							StringBuffer sb=new StringBuffer();
		for( h=0;h<colcount;h++){
			sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
			String st=sb.toString();
		rowdata.put(index,st.substring(0, st.length()-1));
		}
		}
		index++;
		str=reader.readLine();	}
	else if(parser.getCondition().get(0).getOperator().equals(">=")){
		long res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
				if(Long.toString(res).equals(aftersplit[header.get(colname)])){
							StringBuffer sb=new StringBuffer();
		for( h=0;h<colcount;h++){
			sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
			String st=sb.toString();
		rowdata.put(index,st.substring(0, st.length()-1));
		}
		}
		index++;
		str=reader.readLine();	}
	else if(parser.getCondition().get(0).getOperator().equals("<")){
		long res=Math.min(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
				if(Long.toString(res).equals(aftersplit[header.get(colname)])&& !Long.toString(res).equals(valu)){
							StringBuffer sb=new StringBuffer();
		for( h=0;h<colcount;h++){
			sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
			String st=sb.toString();
		rowdata.put(index,st.substring(0, st.length()-1));
		}
		}
		index++;
		str=reader.readLine();	}
	else if(parser.getCondition().get(0).getOperator().equals("<=")){
		long res=Math.min(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
				if(Long.toString(res).equals(aftersplit[header.get(colname)])){
							StringBuffer sb=new StringBuffer();
		for( h=0;h<colcount;h++){
			sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
			String st=sb.toString();
		rowdata.put(index,st.substring(0, st.length()-1));
		}
		}
		index++;
		str=reader.readLine();	}
	
}
			}
}

	catch(Exception io){
		rowdata= whereForStringColumns(parser, header);
	}
return rowdata;
}

public Map<Integer,String> whereForStringColumns(QueryParser parser,Map<String,Integer> header){
	Map<Integer,String> rowdata=new HashMap<>();
	try{
	BufferedReader	reader=new BufferedReader(new FileReader(parser.getPath()));
		String colname=parser.getCondition().get(0).getCol_name();
		String valu=parser.getCondition().get(0).getValue();
	   int  colcount=parser.getCols().size();
	  String   str=reader.readLine();
	    str=reader.readLine();
	    int index=0,h=0;
	    if(parser.getCols().get(0).equals("*")){

	    	while(str!=null){
	if(parser.getCondition().get(0).getOperator().equals(">")){
			String aftersplit[]=str.split(",");
				if(aftersplit[header.get(colname)].equals(valu)){
												rowdata.put(index,str);
					h++;
		
					
				}index++;
				str=reader.readLine();	
		}}
	    	
	return rowdata;
	    }
	else
	{
	while(str!=null){
		String aftersplit[]=str.split(",");
		if(parser.getCondition().get(0).getOperator().equals(">")){
		//long res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
		int res=aftersplit[header.get(colname)].compareTo(valu);
		
				if(res>0){
							StringBuffer sb=new StringBuffer();
		for( h=0;h<colcount;h++){
			sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
			String st=sb.toString();
		rowdata.put(index,st.substring(0, st.length()-1));
		}
		}
		index++;
		str=reader.readLine();	}
		if(parser.getCondition().get(0).getOperator().equals("<")){
			//long res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
			int res=aftersplit[header.get(colname)].compareTo(valu);
			
					if(res<0){
								StringBuffer sb=new StringBuffer();
			for( h=0;h<colcount;h++){
				sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
				String st=sb.toString();
			rowdata.put(index,st.substring(0, st.length()-1));
			}
			}
			index++;
			str=reader.readLine();	}
		if(parser.getCondition().get(0).getOperator().equals(">=")){
			//long res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
			int res=aftersplit[header.get(colname)].compareTo(valu);
			
					if(res>0 || res==0 ){
								StringBuffer sb=new StringBuffer();
			for( h=0;h<colcount;h++){
				sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
				String st=sb.toString();
			rowdata.put(index,st.substring(0, st.length()-1));
			}
			}
			index++;
			str=reader.readLine();	}
		if(parser.getCondition().get(0).getOperator().equals("<=")){
			//long res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
			int res=aftersplit[header.get(colname)].compareTo(valu);
			
					if(res<0 || res==0){
								StringBuffer sb=new StringBuffer();
			for( h=0;h<colcount;h++){
				sb.append(aftersplit[header.get(parser.getCols().get(h))]+",");
				String st=sb.toString();
			rowdata.put(index,st.substring(0, st.length()-1));
			}
			}
			index++;
			str=reader.readLine();	}
}}
	    }
	catch(IOException io){}
	return rowdata;
	}
	
}


