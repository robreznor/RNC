package cl.minsal.api.object;

import java.util.ArrayList;
import java.util.List;

public class BarPlot {
	
	private List<String> labels;
	private List<Integer>  val;
	
	public BarPlot() {
		super();
		this.labels = new ArrayList<String>();
		this.val = new ArrayList<Integer>();
	}
	
	public BarPlot(List<String> labels, List<Integer> values) {
		super();
		this.labels = labels;
		this.val = values;
		this.labels = new ArrayList<String>();
		this.val = new ArrayList<Integer>();
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
	
	
}
