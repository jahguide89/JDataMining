package es.ull.datamining.core;
/**
 * @author Ivan Ramirez *
 */
public class AttributeNumeric extends Attribute {
	private double max;
	private double min;
	private double avg;
	private double stdDev;
	
	public AttributeNumeric() {
		super("");
		setIsClass(false);
		max = Double.MIN_VALUE;
		min = Double.MAX_VALUE;
		avg = 0d;
		stdDev = 0d;
	}
	
	public AttributeNumeric(String name) {
		super(name);
		setIsClass(false);
		max = Double.MIN_VALUE;
		min = Double.MAX_VALUE;
		avg = 0d;
		stdDev = 0d;
	}

	public void setMax(double value) {
		this.max = value;
	}

	public double getMax() {
		return this.max;
	}

	public void setMin(double value) {
		this.min = value;
	}

	public double getMin() {
		return this.min;
	}

	public void setAvg(double value) {
		this.avg = value;
	}

	public double getAvg() {
		return this.avg;
	}

	public void setStdDev(double value) {
		this.stdDev = value;
	}

	public double getStdDev() {
		return this.stdDev;
	}

	public void updateData() {
		updateMin();
		updateMax();
		updateAvg();
		updateStdDev();
	}

	private void updateMax() {
		max = Double.MIN_VALUE;
		for (int i = 0; i < getValues().size(); i++) {
			if ((Double) getValues().get(i).getValue() > max)
				max = (Double) getValues().get(i).getValue();

		}
	}

	private void updateMin() {
		min = Double.MAX_VALUE;
		for (int i = 0; i < getValues().size(); i++) {
			if ((Double) getValues().get(i).getValue() < min)
				min = (Double) getValues().get(i).getValue();
		}
	}

	private void updateAvg() {
		avg = 0d;
		for (int i = 0; i < getValues().size(); i++) {
			avg += (Double) getValues().get(i).getValue();
		}
		avg /= getValues().size();
	}

	private void updateStdDev() {
		stdDev = 0d;
		for (int i = 0; i < getValues().size(); i++) {
			stdDev += Math.pow((Double) getValues().get(i).getValue() - avg, 2);
		}
		stdDev = Math.sqrt(stdDev / (getValues().size()-1));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		builder.append(getName());
		builder.append(',');
		builder.append(getWeight()+"");
		builder.append(',');
		builder.append(isClass()+"");
		builder.append(',');
		builder.append(getMax()+"");
		builder.append(',');
		builder.append(getMin()+"");
		builder.append(',');
		builder.append(getAvg()+"");
		builder.append(',');
		builder.append(getStdDev()+"");
		builder.append(']');
		return builder.toString();
	}

	@Override
	public boolean isNumeric() {
		return true;
	}

	@Override
	public String getType() {
		return Attribute.NUMERIC;
	}

}
