import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class CsvFileRead {
	QueryParser queryParse=new QueryParser();
	QueryProcessor processor=new QueryProcessor();
	BufferedReader reader;
	public Map<Integer,String> collectQuery(String query){
		QueryParser parsedQuery=queryParse.validateThruRegex(query);
		//storeHeaderOfCsv(parsedQuery.getPath());//header from csv
		//getDataFromCsv(parsedQuery);//content from csv
		if(parsedQuery.getType2()==2){
			processor.whereQueryProcessor(parsedQuery, storeHeaderOfCsv(parsedQuery.getPath()));
		
		return null;
		}
		if(parsedQuery.getType1()==1){
			if(parsedQuery.getCols().get(0).equals("*"))
		return getDataFromCsv(parsedQuery);	
			else
				return processor.specifiedQueryProcessor(parsedQuery,storeHeaderOfCsv(parsedQuery.getPath()));
		}
		
		return null;
}
	public Map<String,Integer> storeHeaderOfCsv(String csvpath)
	{
		Map<String,Integer> headerInMap=new HashMap<>();
		try{
			reader=new BufferedReader(new FileReader(csvpath));
			String firstline=reader.readLine();
			String headerarr[]=firstline.split(",");
			int len=headerarr.length;
			for(int i=0;i<len;i++){
				headerInMap.put(headerarr[i], i);
			}
				
		}
		catch(IOException io){
			
		}
		
return headerInMap;
	}
	public Map<Integer,String> getDataFromCsv(QueryParser queryparse)
	{
		Map<Integer,String> rowdata=new HashMap<>();
		try{
		reader=new BufferedReader(new FileReader(queryparse.getPath()));
		
int index=0;
String str=reader.readLine();
while(str!=null){
		rowdata.put(index,str);
		index++;
		str=reader.readLine();
			}
				}
	
		catch(IOException io){
			
		}
	return rowdata;
	}
	
}