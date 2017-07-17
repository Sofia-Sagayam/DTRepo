import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QueryProcessor {

	public Map<Integer, String> getOrderByDataForAllRows(QueryParser queryparse, Map<String, Integer> header) {

		BufferedReader reader;
		Map<Integer, String> rowdata = new HashMap<>();
		try {
			reader = new BufferedReader(new FileReader(queryparse.getPath()));

			int index = 0;
			String str = reader.readLine();
			str = reader.readLine();
			List<String> col_of_orderby = new ArrayList<>();
			while (str != null) {
				String afterSplit[] = str.split(",");
				col_of_orderby.add(afterSplit[header.get(queryparse.getOrd_clause())]);
				str = reader.readLine();
			}

			Collections.sort(col_of_orderby);
			reader.close();

			reader = new BufferedReader(new FileReader(queryparse.getPath()));
			str = reader.readLine();
			str = reader.readLine();
			int l = col_of_orderby.size();

			String afterSplit[] = str.split(",");
			for (int k = 0; k < l; k++) {

				while (str != null) {
					if (str.contains(col_of_orderby.get(k))) {
						rowdata.put(index, str);
						index++;
					}
					str = reader.readLine();
				}

				reader = new BufferedReader(new FileReader(queryparse.getPath()));
				str = reader.readLine();
				str = reader.readLine();
			}
		}

		catch (IOException io) {

		}

		return rowdata;
	}

	public Map<Integer, String> getOrderByForSpecifiedCols(QueryParser queryparse, Map<String, Integer> header) {

		BufferedReader reader;
		Map<Integer, String> rowdata = new HashMap<>();
		try {
			reader = new BufferedReader(new FileReader(queryparse.getPath()));

			int index = 0;
			String str = reader.readLine();
			str = reader.readLine();
			List<String> col_of_orderby = new ArrayList<>();
			while (str != null) {
				String afterSplit[] = str.split(",");
				col_of_orderby.add(afterSplit[header.get(queryparse.getOrd_clause())]);
				str = reader.readLine();
			}

			Collections.sort(col_of_orderby);
			reader.close();

			reader = new BufferedReader(new FileReader(queryparse.getPath()));
			str = reader.readLine();
			str = reader.readLine();
			int l = col_of_orderby.size();
			int colcount = queryparse.getCols().size();

			for (int k = 0; k < l; k++) {

				while (str != null) {
					String afterSplit[] = str.split(",");
					if (str.contains(col_of_orderby.get(k))) {
						StringBuffer sb = new StringBuffer();
						for (int h = 0; h < colcount; h++) {
							sb.append(afterSplit[header.get(queryparse.getCols().get(h))] + ",");
							String st = sb.toString();

							rowdata.put(index, st.substring(0, st.length() - 1));
						}
						index++;
					}

					str = reader.readLine();
				}

				reader = new BufferedReader(new FileReader(queryparse.getPath()));
				str = reader.readLine();
				str = reader.readLine();

			}
		}

		catch (IOException io) {

		}

		return rowdata;

	}

	public Map<Integer, String> specifiedQueryProcessor(QueryParser parser, Map<String, Integer> header) {
		Map<Integer, String> rowdata = new HashMap<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(parser.getPath()));

			int index = 0;
			int colcount = parser.getCols().size();
			String str = reader.readLine();
			while (str != null) {
				String aftersplit[] = str.split(",");
				StringBuffer sb = new StringBuffer();
				for (int h = 0; h < colcount; h++) {
					sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
					String st = sb.toString();

					rowdata.put(index, st.substring(0, st.length() - 1));
				}
				index++;
				str = reader.readLine();
			}
		}

		catch (IOException io) {

		} finally {
			try {
				reader.close();
			} catch (IOException i) {
			}
		}
		return rowdata;

	}

	public Map<Integer, String> whereQueryProcessor(QueryParser parser, Map<String, Integer> header) {
		Map<Integer, String> rowdata = new HashMap<>();
		BufferedReader reader = null;
		String colname = "";
		String valu = "";
		int index = 0, h = 0;
		int colcount = 0;
		String str = "";
		try {
			reader = new BufferedReader(new FileReader(parser.getPath()));
			colname = parser.getCondition().get(0).getCol_name();
			valu = parser.getCondition().get(0).getValue();
			colcount = parser.getCols().size();
			str = reader.readLine();
			str = reader.readLine();
			if (parser.getCols().get(0).equals("*")) {

				while (str != null) {
					if (parser.getCondition().get(0).getOperator().equals("=")) {
						String aftersplit[] = str.split(",");
						if (aftersplit[header.get(colname)].equals(valu)) {
							rowdata.put(index, str);
							h++;

						}
						index++;
						str = reader.readLine();
					}
				}

				return rowdata;
			} else {
				while (str != null) {
					String aftersplit[] = str.split(",");

					if (parser.getCondition().get(0).getOperator().equals("=")) {
						if (aftersplit[header.get(colname)].equals(valu)) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					}

					if (parser.getCondition().get(0).getOperator().equals("!=")) {
						if (!aftersplit[header.get(colname)].equals(valu)) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					}

					else if (parser.getCondition().get(0).getOperator().equals(">")) {
						long res = Math.max(Long.parseLong(aftersplit[header.get(colname)]), Long.parseLong(valu));
						if (Long.toString(res).equals(aftersplit[header.get(colname)])
								&& !Long.toString(res).equals(valu)) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					} else if (parser.getCondition().get(0).getOperator().equals(">=")) {
						long res = Math.max(Long.parseLong(aftersplit[header.get(colname)]), Long.parseLong(valu));
						if (Long.toString(res).equals(aftersplit[header.get(colname)])) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					} else if (parser.getCondition().get(0).getOperator().equals("<")) {
						long res = Math.min(Long.parseLong(aftersplit[header.get(colname)]), Long.parseLong(valu));
						if (Long.toString(res).equals(aftersplit[header.get(colname)])
								&& !Long.toString(res).equals(valu)) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					} else if (parser.getCondition().get(0).getOperator().equals("<=")) {
						long res = Math.min(Long.parseLong(aftersplit[header.get(colname)]), Long.parseLong(valu));
						if (Long.toString(res).equals(aftersplit[header.get(colname)])) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					}

				}
			}
		}

		catch (Exception io) {
			rowdata = whereForStringColumns(parser, header);
		}
		return rowdata;
	}

	public Map<Integer, String> multiWhereProcessor(QueryParser parser, Map<String, Integer> header) {
		Map<Integer, String> rowdata = new HashMap<>();
		BufferedReader reader = null;
		int index = 0, h = 0;
		int colcount = 0;
		String str = "";
		try {
			reader = new BufferedReader(new FileReader(parser.getPath()));
			colcount = parser.getCols().size();
			str = reader.readLine();
			str = reader.readLine();
			if (parser.getCols().get(0).equals("*")) {
			} else {

				int logiOperLen = parser.getOperators().size() + 1;
				boolean mulRes[] = new boolean[logiOperLen];
				boolean fres = false;
				while (str != null) {
					String aftersplit[] = str.split(",");
					for (int logOp = 0; logOp < logiOperLen; logOp++) {
						if (parser.getCondition().get(logOp).getOperator().equals("=")) {
							mulRes[logOp] = aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())]
									.equals(parser.getCondition().get(logOp).getValue());
						} else if (parser.getCondition().get(logOp).getOperator().equals("!=")) {
							mulRes[logOp] = !(aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())]
									.equals(parser.getCondition().get(logOp).getValue()));
						} else if (parser.getCondition().get(logOp).getOperator().equals(">")) {
							long res = Math.max(
									Long.parseLong(
											aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())]),
									Long.parseLong(parser.getCondition().get(logOp).getValue()));
							mulRes[logOp] = Long.toString(res)
									.equals(aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())])
									&& !Long.toString(res).equals(parser.getCondition().get(logOp).getValue());
						} else if (parser.getCondition().get(logOp).getOperator().equals("<")) {
							long res = Math.min(
									Long.parseLong(
											aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())]),
									Long.parseLong(parser.getCondition().get(logOp).getValue()));
							mulRes[logOp] = Long.toString(res)
									.equals(aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())])
									&& !Long.toString(res).equals(parser.getCondition().get(logOp).getValue());

						} else if (parser.getCondition().get(logOp).getOperator().equals(">=")) {
							long res = Math.max(
									Long.parseLong(
											aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())]),
									Long.parseLong(parser.getCondition().get(logOp).getValue()));
							mulRes[logOp] = Long.toString(res)
									.equals(aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())]);
						} else if (parser.getCondition().get(logOp).getOperator().equals("<=")) {
							long res = Math.min(
									Long.parseLong(
											aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())]),
									Long.parseLong(parser.getCondition().get(logOp).getValue()));
							mulRes[logOp] = Long.toString(res)
									.equals(aftersplit[header.get(parser.getCondition().get(logOp).getCol_name())]);

						}
					}
					boolean res = mulRes[0];
					for (int god = 0; god < logiOperLen - 1; god++) {

						if (parser.getOperators().get(god).equals("and")) {
							res = res && mulRes[god + 1];
						} else if (parser.getOperators().get(god).equals("or")) {
							res = res || mulRes[god + 1];
						}
					}
					if (res) {
						StringBuffer sb = new StringBuffer();
						for (h = 0; h < colcount; h++) {
							sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
							String st = sb.toString();
							rowdata.put(index, st.substring(0, st.length() - 1));
						}
					}
					index++;
					str = reader.readLine();
				}

			}

		}

		catch (IOException e) {
		}
		return rowdata;
	}

	public Map<Integer, String> whereForStringColumns(QueryParser parser, Map<String, Integer> header) {
		Map<Integer, String> rowdata = new HashMap<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(parser.getPath()));
			String colname = parser.getCondition().get(0).getCol_name();
			String valu = parser.getCondition().get(0).getValue();
			int colcount = parser.getCols().size();
			String str = reader.readLine();
			str = reader.readLine();
			int index = 0, h = 0;
			if (parser.getCols().get(0).equals("*")) {

				while (str != null) {
					if (parser.getCondition().get(0).getOperator().equals(">")) {
						String aftersplit[] = str.split(",");
						if (aftersplit[header.get(colname)].equals(valu)) {
							rowdata.put(index, str);
							h++;

						}
						index++;
						str = reader.readLine();
					}
				}

				return rowdata;
			} else {
				while (str != null) {
					String aftersplit[] = str.split(",");
					if (parser.getCondition().get(0).getOperator().equals(">")) {
						// long
						// res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
						int res = aftersplit[header.get(colname)].compareTo(valu);

						if (res > 0) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					}
					if (parser.getCondition().get(0).getOperator().equals("<")) {
						// long
						// res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
						int res = aftersplit[header.get(colname)].compareTo(valu);

						if (res < 0) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					}
					if (parser.getCondition().get(0).getOperator().equals(">=")) {
						// long
						// res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
						int res = aftersplit[header.get(colname)].compareTo(valu);

						if (res > 0 || res == 0) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					}
					if (parser.getCondition().get(0).getOperator().equals("<=")) {
						// long
						// res=Math.max(Long.parseLong(aftersplit[header.get(colname)]),Long.parseLong(valu));
						int res = aftersplit[header.get(colname)].compareTo(valu);

						if (res < 0 || res == 0) {
							StringBuffer sb = new StringBuffer();
							for (h = 0; h < colcount; h++) {
								sb.append(aftersplit[header.get(parser.getCols().get(h))] + ",");
								String st = sb.toString();
								rowdata.put(index, st.substring(0, st.length() - 1));
							}
						}
						index++;
						str = reader.readLine();
					}
				}
			}
		} catch (IOException io) {
		}
		return rowdata;
	}

	public Map<Integer, String> orderByWhereQueryProcessor(QueryParser parser, Map<String, Integer> header) {

		Map<Integer, String> rowdata = new HashMap<>();
		BufferedReader reader = null;
		rowdata = whereQueryProcessor(parser, header);

		try {
			reader = new BufferedReader(new FileReader(parser.getPath()));

			int index = 0;
			String str = reader.readLine();
			str = reader.readLine();
			List<String> col_of_orderby = new ArrayList<>();
			while (str != null) {
				String afterSplit[] = str.split(",");
				col_of_orderby.add(afterSplit[header.get(parser.getOrd_clause())]);
				str = reader.readLine();
			}

			Collections.sort(col_of_orderby);
			int l = col_of_orderby.size();

			reader.close();
			List<String> list = new ArrayList<String>(rowdata.values());
			rowdata = new HashMap<>();
			for (int k = 0; k < l; k++) {

				for (String st : list) {
					if (st.contains(col_of_orderby.get(k))) {

						rowdata.put(index, st);
						index++;
					}

				}

			}
		} catch (IOException e) {
		}

		return rowdata;
	}

	public Map<Integer, String> aggregateQuery(QueryParser parser, Map<String, Integer> header) {
		Map<Integer, String> rowdata = new HashMap<>();
		String col = parser.getCols().get(0);
		BufferedReader reader = null;
		String aggr = "";
		if (col.contains("sum")) {

			aggr = col.split("sum")[1];
			aggr = aggr.replaceAll("\\p{P}", "");
			try {
				reader = new BufferedReader(new FileReader(parser.getPath()));
				String str = reader.readLine();
				str = reader.readLine();
				List<String> col_of_Aggr = new ArrayList<>();
				while (str != null) {
					String afterSplit[] = str.split(",");
					col_of_Aggr.add(afterSplit[header.get(aggr)]);
					str = reader.readLine();
				}
				int sum = 0;
				for (String st : col_of_Aggr)
					sum += Integer.parseInt(st);

				rowdata.put(0, Integer.toString(sum));
			} catch (IOException e) {
			}
			return rowdata;
		}

		else if (col.contains("count")) {

			aggr = col.split("count")[1];
			aggr = aggr.replaceAll("\\p{P}", "");
			try {
				reader = new BufferedReader(new FileReader(parser.getPath()));
				String str = reader.readLine();
				str = reader.readLine();
				List<String> col_of_Aggr = new ArrayList<>();
				while (str != null) {
					String afterSplit[] = str.split(",");
					col_of_Aggr.add(afterSplit[header.get(aggr)]);
					str = reader.readLine();
				}
				String len = Integer.toString(col_of_Aggr.size());
				rowdata.put(0, len);
			} catch (IOException e) {
			}

			return rowdata;
		} else if (col.contains("avg")) {
			aggr = col.split("avg")[1];
			aggr = aggr.replaceAll("\\p{P}", "");
			try {
				reader = new BufferedReader(new FileReader(parser.getPath()));
				String str = reader.readLine();
				str = reader.readLine();
				List<String> col_of_Aggr = new ArrayList<>();
				while (str != null) {
					String afterSplit[] = str.split(",");
					col_of_Aggr.add(afterSplit[header.get(aggr)]);
					str = reader.readLine();
				}
				int sum = 0;
				for (String st : col_of_Aggr)
					sum += Integer.parseInt(st);

				int avg = sum / col_of_Aggr.size();

				rowdata.put(0, Integer.toString(avg));
			} catch (IOException e) {
			}

			return rowdata;
		} else if (col.contains("min")) {
			aggr = col.split("min")[1];
			aggr = aggr.replaceAll("\\p{P}", "");
			try {
				reader = new BufferedReader(new FileReader(parser.getPath()));
				String str = reader.readLine();
				str = reader.readLine();
				List<String> col_of_Aggr = new ArrayList<>();
				while (str != null) {
					String afterSplit[] = str.split(",");
					col_of_Aggr.add(afterSplit[header.get(aggr)]);
					str = reader.readLine();
				}
				List<Integer> list = new ArrayList<>();
				for (String strg : col_of_Aggr)
					list.add(Integer.parseInt(strg));
				int min = Collections.min(list);

				rowdata.put(0, Integer.toString(min));
			} catch (IOException e) {
			}

			return rowdata;
		} else if (col.contains("max")) {
			aggr = col.split("max")[1];
			aggr = aggr.replaceAll("\\p{P}", "");
			try {
				reader = new BufferedReader(new FileReader(parser.getPath()));
				String str = reader.readLine();
				str = reader.readLine();
				List<String> col_of_Aggr = new ArrayList<>();
				while (str != null) {
					String afterSplit[] = str.split(",");
					col_of_Aggr.add(afterSplit[header.get(aggr)]);
					str = reader.readLine();
				}
				List<Integer> list = new ArrayList<>();
				for (String strg : col_of_Aggr)
					list.add(Integer.parseInt(strg));
				int max = Collections.max(list);

				rowdata.put(0, Integer.toString(max));

			} catch (IOException e) {
			}

			return rowdata;
		}

		return null;

	}

	public Map<Integer, String> aggregateWithWhere(QueryParser parser, Map<String, Integer> header) {
		Map<Integer, String> filterDataToAggre = null;
		String col = parser.getCols().get(0);
		try {
			QueryParser alter = (QueryParser) parser.clone();

			alter.getCols().add(0, "*");
			filterDataToAggre = whereQueryProcessor(alter, header);
		} catch (CloneNotSupportedException c) {
		}
		Map<Integer, String> rowdata = new HashMap<>();

		String aggr = "";
		if (col.contains("sum")) {

			aggr = col.split("sum")[1];
			aggr = aggr.replaceAll("\\p{P}", "");

			List<String> col_of_Aggr = new ArrayList<>();
			for (String fil : filterDataToAggre.values()) {
				String afterSplit[] = fil.split(",");
				col_of_Aggr.add(afterSplit[header.get(aggr)]);
			}
			int sum = 0;
			for (String st : col_of_Aggr)
				sum += Integer.parseInt(st);

			rowdata.put(0, Integer.toString(sum));
			return rowdata;
		}
		return null;
	}

	public Map<Integer,String> groupByQueryProcessor(QueryParser parser, Map<String, Integer> header) {
		Set<String> uniqueValue = new LinkedHashSet<>();
		List<String> csvData=new ArrayList<>();
		Map<Integer,String> rowdata=new HashMap<>();
		Map<Integer,String> resSet=new HashMap<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(parser.getPath()));
			String str = reader.readLine();
			str = reader.readLine();
			while (str != null) {
				String afterSplit[] = str.split(",");
				uniqueValue.add(afterSplit[header.get(parser.getGrp_clause())]);
				csvData.add(str);
				str = reader.readLine();
			}
			boolean res=false;
			for(int i=0;i<parser.getCols().size();i++){
				res=parser.getCols().get(i).contains("count");
			}
			if(res){
			int index=0;
			for(String uniq:uniqueValue){
				StringBuffer sb=new StringBuffer();
				for(String csv:csvData){
					if(csv.contains(uniq)){
					sb.append(csv+",");
					}
					
				}
				rowdata.put(index, sb.toString());
				index++;
			}
			int count=0;
			index=0;
			
			for(String ite:rowdata.values()){
				String splitVal[]=ite.split(",");
				count=splitVal.length;
				
			resSet.put(index, Integer.toString(count/header.size()));
			index++;
			}
			}
			else {
				String aggr=" ";
				res=false;
				int i=0;
				for( i=0;i<parser.getCols().size();i++){
					if(parser.getCols().get(i).contains("sum")){
						aggr = parser.getCols().get(i).split("sum")[1];
						aggr = aggr.replaceAll("\\p{P}", "");
					}
				}
				int headerposi=header.get(aggr);
				int salsum=0,index=0;
				for(String uni:uniqueValue){
				for(String csv:csvData){					
					String split[]=csv.split(",");
					if(csv.contains(uni)){
					salsum+=Integer.parseInt(split[headerposi]);}
					}
					resSet.put(index, Integer.toString(salsum));
					index++;
					salsum=0;
				}
				}
			Iterator<String>iter=uniqueValue.iterator();
			int l=uniqueValue.size();
			StringBuilder sb=new StringBuilder();
			for(int data=0;data<l;data++){
				sb.append(iter.next()+" "+resSet.get(data));
				rowdata.put(data, sb.toString());
				sb.setLength(0);
			}
		
			

		}
				
		catch (IOException io) {
		}
		
		
		return rowdata;
		
	}
}