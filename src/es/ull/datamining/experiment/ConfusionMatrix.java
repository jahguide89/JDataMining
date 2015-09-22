package es.ull.datamining.experiment;

import java.util.ArrayList;
import java.util.Arrays;

import es.ull.datamining.core.AttributeClass;

public class ConfusionMatrix {

	private ArrayList<AttributeClass> classes;
	private int rows;
	private int cols;
	private int[][] matrix;

	public ConfusionMatrix(ArrayList<AttributeClass> classes) {
		this.classes = classes;
		this.cols = classes.size();
		this.rows = classes.size();
		this.matrix = new int[cols][rows];
	}
	
	public ConfusionMatrix(AttributeClass[] classes) {
		this.classes = new ArrayList<AttributeClass>(Arrays.asList(classes));
		this.cols = classes.length;
		this.rows = classes.length;
		this.matrix = new int[cols][rows];
	}
	
	public int indexOf(String value) {
		if (value == null) {
			for (int i = 0; i < classes.size(); i++)
				if (classes.get(i).getValue() == null)
					return i;
		} else {
			for (int i = 0; i < classes.size(); i++)
				if (value.equals(classes.get(i).toString()))
					return i;
		}
		return -1;
	}
	
	public void addPrediction(String predict, String prediction){
		int indexPredict = indexOf(predict);
		int indexPrediction = indexOf(prediction);
		matrix[indexPredict][indexPrediction]++;
	}
	
	public double correct() {
		double correct = 0;
		for (int i = 0; i < size(); i++) {
			correct += matrix[i][i];
		}
		return correct;
	}

	public double incorrect() {
		double incorrect = 0;
		for (int row = 0; row < size(); row++) {
			for (int col = 0; col < size(); col++) {
				if (row != col) {
					incorrect += matrix[row][col];
				}
			}
		}
		return incorrect;
	}
	
	public int size() {
		return rows;
	}

	public double getPositivePredictiveValue(){
		return correct()/(correct()+incorrect());
	}
	
	
	public String toString() {

	    return toString("=== Confusion Matrix ===\n");
	  }

	  /**
	   * Outputs the performance statistics as a classification confusion
	   * matrix. For each class value, shows the distribution of 
	   * predicted class values.
	   *
	   * @param title the title for the confusion matrix
	   * @return the confusion matrix as a String
	   */
	  public String toString(String title) {

	    StringBuffer text = new StringBuffer();
	    char [] IDChars = {'a','b','c','d','e','f','g','h','i','j',
			       'k','l','m','n','o','p','q','r','s','t',
			       'u','v','w','x','y','z'};
	    int IDWidth;
	    boolean fractional = false;

	    // Find the maximum value in the matrix
	    // and check for fractional display requirement 
	    double maxval = 0;
	    for (int i = 0; i < size(); i++) {
	      for (int j = 0; j < size(); j++) {
		double current = matrix[i][j];
	        if (current < 0) {
	          current *= -10;
	        }
		if (current > maxval) {
		  maxval = current;
		}
		double fract = current - Math.rint(current);
		if (!fractional
		    && ((Math.log(fract) / Math.log(10)) >= -2)) {
		  fractional = true;
		}
	      }
	    }

	    IDWidth = 1 + Math.max((int)(Math.log(maxval) / Math.log(10) 
					 + (fractional ? 3 : 0)),
				     (int)(Math.log(size()) / 
					   Math.log(IDChars.length)));
	    text.append(title).append("\n");
	    for (int i = 0; i < size(); i++) {
	      if (fractional) {
		text.append(" ").append(num2ShortID(i,IDChars,IDWidth - 3))
	          .append("   ");
	      } else {
		text.append(" ").append(num2ShortID(i,IDChars,IDWidth));
	      }
	    }
	    text.append("     actual class\n");
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < size(); j++) {
				text.append(" ").append(
						doubleToString(matrix[i][j], IDWidth, (fractional ? 2
								: 0)));
			}
			text.append(" | ").append(num2ShortID(i, IDChars, IDWidth))
					.append(" = ").append(classes.get(i).getValue())
					.append("\n");
		}
	    return text.toString();
	  }
	  
	  private static String num2ShortID(int num, char [] IDChars, int IDWidth) {
		    
		    char ID [] = new char [IDWidth];
		    int i;
		    
		    for(i = IDWidth - 1; i >=0; i--) {
		      ID[i] = IDChars[num % IDChars.length];
		      num = num / IDChars.length - 1;
		      if (num < 0) {
			break;
		      }
		    }
		    for(i--; i >= 0; i--) {
		      ID[i] = ' ';
		    }

		    return new String(ID);
		  }
	  
	  public static String doubleToString(double value, int width,
			    int afterDecimalPoint) {

			    String tempString = doubleToString(value, afterDecimalPoint);
			    char[] result;
			    int dotPosition;

			    if ((afterDecimalPoint >= width) || (tempString.indexOf('E') != -1)) { // Protects
			                                                                           // sci
			                                                                           // notation
			      return tempString;
			    }

			    // Initialize result
			    result = new char[width];
			    for (int i = 0; i < result.length; i++) {
			      result[i] = ' ';
			    }

			    if (afterDecimalPoint > 0) {
			      // Get position of decimal point and insert decimal point
			      dotPosition = tempString.indexOf('.');
			      if (dotPosition == -1) {
			        dotPosition = tempString.length();
			      } else {
			        result[width - afterDecimalPoint - 1] = '.';
			      }
			    } else {
			      dotPosition = tempString.length();
			    }

			    int offset = width - afterDecimalPoint - dotPosition;
			    if (afterDecimalPoint > 0) {
			      offset--;
			    }

			    // Not enough room to decimal align within the supplied width
			    if (offset < 0) {
			      return tempString;
			    }

			    // Copy characters before decimal point
			    for (int i = 0; i < dotPosition; i++) {
			      result[offset + i] = tempString.charAt(i);
			    }

			    // Copy characters after decimal point
			    for (int i = dotPosition + 1; i < tempString.length(); i++) {
			      result[offset + i] = tempString.charAt(i);
			    }

			    return new String(result);
			  }
	  
	  public static String doubleToString(double value,
			    int afterDecimalPoint) {

			    StringBuffer stringBuffer;
			    double temp;
			    int dotPosition;
			    long precisionValue;

			    temp = value * Math.pow(10.0, afterDecimalPoint);
			    if (Math.abs(temp) < Long.MAX_VALUE) {
			      precisionValue = (temp > 0) ? (long) (temp + 0.5) : -(long) (Math
			        .abs(temp) + 0.5);
			      if (precisionValue == 0) {
			        stringBuffer = new StringBuffer(String.valueOf(0));
			      } else {
			        stringBuffer = new StringBuffer(String.valueOf(precisionValue));
			      }
			      if (afterDecimalPoint == 0) {
			        return stringBuffer.toString();
			      }
			      dotPosition = stringBuffer.length() - afterDecimalPoint;
			      while (((precisionValue < 0) && (dotPosition < 1)) || (dotPosition < 0)) {
			        if (precisionValue < 0) {
			          stringBuffer.insert(1, '0');
			        } else {
			          stringBuffer.insert(0, '0');
			        }
			        dotPosition++;
			      }
			      stringBuffer.insert(dotPosition, '.');
			      if ((precisionValue < 0) && (stringBuffer.charAt(1) == '.')) {
			        stringBuffer.insert(1, '0');
			      } else if (stringBuffer.charAt(0) == '.') {
			        stringBuffer.insert(0, '0');
			      }
			      int currentPos = stringBuffer.length() - 1;
			      while ((currentPos > dotPosition)
			        && (stringBuffer.charAt(currentPos) == '0')) {
			        stringBuffer.setCharAt(currentPos--, ' ');
			      }
			      if (stringBuffer.charAt(currentPos) == '.') {
			        stringBuffer.setCharAt(currentPos, ' ');
			      }

			      return stringBuffer.toString().trim();
			    }
			    return new String("" + value);
			  }

}
