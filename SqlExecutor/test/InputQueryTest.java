import static org.junit.Assert.*;

import org.junit.Test;

public class InputQueryTest {

	@Test
	public void testQuery() {
		//fail("Not yet implemented");
	QueryParser qp=new QueryParser();
	QueryParser qp2=qp.validateAndFindType("select eid,ename from myfile.csv where eid_>_9_AND_eid_<_10");
	System.out.println(qp2.getPath()+" "+qp2.getCols()+qp2.getOrd_clause());
	//assertEquals("succ",result);
	
	}

}
