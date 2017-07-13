import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryProcessor {
public Map<Integer,String> simpleQueryProcessor(List<String> inputcolumn,Map<Integer,String> rowdata,Map<String,Integer> header){
	int count=inputcolumn.size();
	List<String> csvdata=new ArrayList<>();
	List<String> csvdataAfterParse=new ArrayList<>();
	int headIndex[]=new int[count];
	if(inputcolumn.get(0).equals("*")){
		return rowdata;
	}
	else{
		int h=0;
		for(String col:inputcolumn){
			headIndex[h]=header.get(col);
			h++;
		}
for(String in:rowdata.values()){
	csvdata.add(in);
}
//System.out.println(csvdata);
Map<Integer,String> newMap=new HashMap<>();

int csvlen=csvdata.size();
String store[]=new String[csvlen];

for(int i=0;i<csvlen;i++){
store[i]=csvdata.get(i);
//System.out.println(store[i]);
String aftersplit[]=store[i].split(",");
//String recombine[]=new String[csvlen];
//int m=0;
StringBuffer sb=null;
sb=new StringBuffer();
for(int he=0;he<count;he++){
		sb.append(aftersplit[headIndex[he]]+",");
		}
newMap.put(i, sb.toString());
}

return newMap;
	}
	

	
}
}
