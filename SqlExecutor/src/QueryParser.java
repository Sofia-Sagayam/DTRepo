import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryParser {
	
	private List<String> cols=new ArrayList<>();
	private boolean hasAggregate;
	private String path;
	private List<Conditions> condition=new ArrayList();
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	private String grp_clause;
	private String ord_clause;
	private int type;
	
		
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
	public QueryParser validateThruRegex(String query){
		String queryHolder=query.toLowerCase();
		String splitQuery[];
		Conditions conditionObj=new Conditions();
		Pattern pat1=Pattern.compile("select\\s.*from\\s.*");
		Matcher mat1=pat1.matcher(queryHolder);
		if(mat1.find()){
			if(queryHolder.contains("groupby"))
			{
				type=4;
				splitQuery=queryHolder.split("groupby");
				grp_clause=splitQuery[1].replaceAll("\\s","");
				queryHolder=splitQuery[0];
				
				
			}
			if(queryHolder.contains("orderby")){
				type=3;
				splitQuery=queryHolder.split("orderby");
				ord_clause=splitQuery[1].replaceAll("\\s","");
				queryHolder=splitQuery[0];
				
			}
			if(queryHolder.contains("where")){
				type=2;
				String wholeWhere="";
				splitQuery=queryHolder.split("where");
				wholeWhere=splitQuery[1].replaceAll("\\s","");
				//if(wholeWhere.contains("AND"))
				String conditionsplit[]=wholeWhere.split("[\\s]*[>=|<=|!=|=|<|>][\\s]*");
				int l=conditionsplit.length;
				if(l==3){
					conditionObj.setCol_name(conditionsplit[0]);
					conditionObj.setValue(conditionsplit[2]);
					if(wholeWhere.contains(">="))conditionObj.setOperator(">=");
					if(wholeWhere.contains("<="))conditionObj.setOperator("<=");
					if(wholeWhere.contains("!="))conditionObj.setOperator("!=");
									}
				else if(l==2){
					conditionObj.setCol_name(conditionsplit[0]);
					conditionObj.setValue(conditionsplit[1]);
					if(wholeWhere.contains(">"))conditionObj.setOperator(">");
					if(wholeWhere.contains("<"))conditionObj.setOperator("<");
					if(wholeWhere.contains("!"))conditionObj.setOperator("!");
				}
				condition.add(conditionObj);
			
					queryHolder=splitQuery[0];
			}
			if(queryHolder.contains("from")){
				type=1;
				splitQuery=queryHolder.split("from");
				path="F:\\DT-3\\"+splitQuery[1].replaceAll("\\s","");
				queryHolder=splitQuery[0];
			}
			if(queryHolder.contains("select")){
				splitQuery=queryHolder.split("select");
			String columns=splitQuery[1].replaceAll("\\s","");
			if(!columns.equals("*")){
				String splitedColname[]=columns.trim().split(",");
				for(int c=0;c<splitedColname.length;c++){
					cols.add(splitedColname[c]);
				}
				
				checkaggregate();
				}
			else{
				cols.add("*");
			}
			queryHolder=splitQuery[0];
			}
			
		}
		
		return this;
	}
	
	
}
