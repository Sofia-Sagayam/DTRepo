import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InputQueryTest {
	CsvFileRead csv;

	@Before
	public void objectCreate() {
		csv = new CsvFileRead();
	}

/*	@Test
	public void selectAllWithoutWhereTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select * from CsvDB.csv");
		assertNotNull(dataSet);
		display("selectAllWithoutWhereTestCase", dataSet);

	}

	@Test
	public void selectColumnsWithoutWhereTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select city,dept,name from CsvDB.csv");
		assertNotNull(dataSet);
		display("selectColumnsWithoutWhereTestCase", dataSet);

	}

	@Test
	public void withWhereGreaterThanTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select city,name,salary from CsvDB.csv where salary>30000");
		assertNotNull(dataSet);
		display("withWhereGreaterThanTestCase", dataSet);

	}

	@Test
	public void withWhereLessThanTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select city,name,salary from CsvDB.csv where salary<35000");
		assertNotNull(dataSet);
		display("withWhereLessThanTestCase", dataSet);

	}

	@Test
	public void withWhereLessThanOrEqualToTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select city,name,salary from CsvDB.csv where salary<=35000");
		assertNotNull(dataSet);
		display("withWhereLessThanOrEqualToTestCase", dataSet);

	}

	@Test
	public void withWhereGreaterThanOrEqualToTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select city,name,salary from CsvDB.csv where salary>=35000");
		assertNotNull(dataSet);
		display("withWhereLessThanOrEqualToTestCase", dataSet);

	}

	@Test
	public void withWhereNotEqualToTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select city,name,salary from CsvDB.csv where salary!=35000");
		assertNotNull(dataSet);
		display("withWhereNotEqualToTestCase", dataSet);

	}

	@Test
	public void withWhereEqualAndNotEqualTestCase() {

		Map<Integer, String> dataSet = csv
				.executeQuery("select city,name,salary from CsvDB.csv where city=Bangalore and salary<=40000");
		assertNotNull(dataSet);
		display("withWhereEqualAndNotEqualTestCase", dataSet);

	}

	@Test
	public void selectColumnsWithoutWhereWithOrderByTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select city,name,salary from CsvDB.csv orderby name");
		assertNotNull(dataSet);
		display("selectColumnsWithoutWhereWithOrderByTestCase", dataSet);

	}

	@Test
	public void selectColumnsWithWhereWithOrderByTestCase() {

		Map<Integer, String> dataSet = csv
				.executeQuery("select city,name,salary from CsvDB.csv where city=Bangalore orderby name");
		assertNotNull(dataSet);
		display("selectColumnsWithWhereWithOrderByTestCase", dataSet);

	}

	@Test
	public void selectCountColumnsWithoutWhereTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select count(name) from CsvDB.csv");
		assertNotNull(dataSet);
		display("selectCountColumnsWithoutWhereTestCase", dataSet);

	}

	@Test
	public void selectSumColumnsWithoutWhereTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select sum(salary) from CsvDB.csv");
		assertNotNull(dataSet);
		display("selectSumColumnsWithoutWhereTestCase", dataSet);
	}

	@Test
	public void selectAvgColumnsWithoutWhereTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select avg(salary) from CsvDB.csv");
		assertNotNull(dataSet);
		display("selectAvgColumnsWithoutWhereTestCase", dataSet);
	}

	@Test
	public void selectMinColumnsWithoutWhereTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select min(salary) from CsvDB.csv");
		assertNotNull(dataSet);
		display("selectMinColumnsWithoutWhereTestCase", dataSet);
	}

	@Test
	public void selectMaxColumnsWithoutWhereTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select max(salary) from CsvDB.csv");
		assertNotNull(dataSet);
		display("selectMaxColumnsWithoutWhereTestCase", dataSet);
	}

	@Test
	public void selectSumColumnsWithWhereTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select sum(salary) from CsvDB.csv where city=Bangalore");
		assertNotNull(dataSet);
		display("selectSumColumnsWithWhereTestCase", dataSet);

	}

	@Test
	public void selectColumnsWithoutWhereWithGroupByCountTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select city,count(name) from CsvDB.csv groupby city");
		assertNotNull(dataSet);
		display("selectColumnsWithoutWhereWithOrderByTestCase", dataSet);

	}
*/
	@Test
	public void selectColumnsWithoutWhereWithGroupBySumTestCase() {

		Map<Integer, String> dataSet = csv.executeQuery("select sum(salary) from CsvDB.csv where city=Bangalore");
		assertNotNull(dataSet);
		display("selectColumnsWithoutWhereWithOrderByTestCase", dataSet);

	}

	private void display(String testCaseName, Map<Integer, String> dataSet) {
		System.out.println(testCaseName);
		System.out.println("================================================================");
		for (String d : dataSet.values())
			System.out.println(d);
	}

}
