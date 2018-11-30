package cl.minsal.api.object;

import java.util.ArrayList;
import java.util.List;

public class BarPlot {
	
	private List<String> labels;
	private List<Integer>  val;
	private String dateStart;
	private String dateEnd;
	
	public BarPlot() {
		super();
		this.labels = new ArrayList<String>();
		this.val = new ArrayList<Integer>();
		this.dateStart = "00-00-2200";
		this.dateEnd = "00-00-1800";
	}
	
	public BarPlot(List<String> labels, List<Integer> values, String dateStart, String dateEnd) {
		super();
		this.labels = labels;
		this.val = values;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}
	
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public List<Integer> getVal() {
		return val;
	}
	public void setVal(List<Integer> values) {
		this.val = values;
	}
	public void addVal(Integer value){
		this.val.add(value);
	}
	public void addLabel(String label){
		this.labels.add(label);
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
		
}
