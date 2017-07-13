import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class InputQueryTest {
	QueryParser parser;
	CsvFileRead csv;
	@Before
	public void objectCreate(){
		 parser=new QueryParser();
		 csv=new CsvFileRead();
	}
	
	@Test
	public void testPath(){
		display(csv.collectQuery("select * from myfile.csv "));
		
		}
		
		public void display(Map<Integer,String> map){
			for(String s:map.values()){
				System.out.println(s);
		}
	}
	
	
	/*
	@Test
		public void testPath(){
		assertEquals("myfile.csv",qp2.getPath());
	}
	@Test
	public void testCondtion(){
		assertEquals("<",qp2.getCondition().get(0).getOperator());
	}
	@Test
	public void testcolumns(){
		assertEquals("[]",qp2.getCols().toString());
	}
	@Test
	public void testClause(){
		assertEquals("edept",qp2.getGrp_clause());
	}
*/
}
