import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParser {
	
	private List<String> cols=new ArrayList<>();
	private boolean hasAggregate;
	private String path;
	private List<Conditions> condition=new ArrayList();
	private String grp_clause;
	private String ord_clause;
	
	
		
	public boolean isHasAggregate() {
		return hasAggregate;
	}
	public void setHasAggregate(boolean hasAggregate) {
		this.hasAggregate = hasAggregate;
	}
	public List<String> getCols() {
		return cols;
	}
	public void setCols(List<String> cols) {
		this.cols = cols;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<Conditions> getCondition() {
		return condition;
	}
	public void setCondition(List<Conditions> condition) {
		this.condition = condition;
	}
	
	public String getGrp_clause() {
		return grp_clause;
	}
	public void setGrp_clause(String grp_clause) {
		this.grp_clause = grp_clause;
	}
	public String getOrd_clause() {
		return ord_clause;
	}
	public void setOrd_clause(String ord_clause) {
		this.ord_clause = ord_clause;
	}
	public void checkaggregate(){
		for(String col:cols){
			if(col.contains("sum")||col.contains("count")){
				setHasAggregate(true);
			}
			else setHasAggregate(false);
		
		}
	}
	public QueryParser validateAndFindType(String query){
		String checker;
		Conditions conditionObj=new Conditions();
		/*Pattern pat1=Pattern.compile("select\\s.*from\\s.*");
		Matcher mat1=pat1.matcher(query);
		Pattern pat2=Pattern.compile("select\\s.*from\\s.*where\\s.");
		Matcher mat2=pat2.matcher(query);
		Pattern pat3=Pattern.compile("select\\s.*from\\s.*where\\s.*groupby\\s.*");
		Matcher mat3=pat3.matcher(query);
		Pattern pat4=Pattern.compile("select\\s.*from\\s.*where\\s.*groupby\\s.*orderby\\s.*");
		Matcher mat4=pat4.matcher(query);
		if(mat1.find() || mat2.find()||mat3.find()||mat4.find() )
		{*/
			String splitedQuery[]=query.split(" ");
			for(int s=0;s<splitedQuery.length;s++){
			switch (splitedQuery[s])
			{
			case "select":
				checker=splitedQuery[s+1];
				if(!checker.equals("*")){
					String splitedColname[]=checker.split(",");
					for(int c=0;c<splitedColname.length;c++){
						cols.add(splitedColname[c]);
					}
					checkaggregate();
					}
				else{
					
				}
				break;
			case "from":
				path=splitedQuery[s+1];
				break;				
			case "where":
				checker=splitedQuery[s+1];
				String conditionsplit[]=checker.split("_");
				
				conditionObj.setCol_name(conditionsplit[0]);
				conditionObj.setOperator(conditionsplit[1]);
				conditionObj.setValue(conditionsplit[2]);
				condition.add(conditionObj);
				break;				
			case "groupby":
				grp_clause=splitedQuery[s+1];
				break;
			case "orderby":
				ord_clause=splitedQuery[s+1];
				break;
			}
			}
			return this;
		/*}
		else{
			return "no";
		}*/
	
	}
	
	
}
