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
	/*
	@Test
	public void testAllCol(){
		Map<Integer,String> resultset=csv.executeQuery("select * from myfile.csv ");
		display(resultset);
		assertEquals(5,resultset.size());
		}
	
	@Test
	public void testSpecifiedCol(){
		Map<Integer,String> resultSet=csv.executeQuery("select eid,esal,edept,ename from myfile.csv");
		display(resultSet);
		assertEquals(1,resultSet.size());
		}
	/*@Test
	public void testAllColWhere(){
		Map<Integer,String> resultSet=csv.executeQuery("select * from myfile.csv where ename=ravan");
		display(resultSet);
		assertEquals(1,resultSet.size());
		}

	
	
	@Test
	public void testSpecColWhere(){
		Map<Integer,String> resultSet=csv.executeQuery("select eid,esal,edept,ename from myfile.csv where edept=travel");
		display(resultSet);
		assertEquals(1,resultSet.size());
		}
	*/
	/*
	@Test
	public void testMultiWhere(){
		Map<Integer,String> resultSet=csv.executeQuery("select * from myfile.csv where edept=travel or esal>20000 OR esal==23 or");
		displayMultiWhr(parser);
		assertEquals(1,resultSet.size());
		}*/
		public void display(Map<Integer,String> map){
			for(String s:map.values()){
				System.out.println(s);
		}
			
		}/*
			public void displayMultiWhr(QueryParser parser){
					System.out.println(parser);
			
				
	}*/
	
	@Test
	public void multiWhereAllOper(){
		Map<Integer,String> resultSet=csv.executeQuery("select ename,edept from myfile.csv where ename=lakshman");
		display(resultSet);
		//assertEquals(1,resultSet.size());
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
