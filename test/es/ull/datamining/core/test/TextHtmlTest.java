package es.ull.datamining.core.test;

import javax.swing.JTextPane;

public class TextHtmlTest extends JTextPane {
	final String myBirthday = "Birthday";
	private String datasetValue;

	public TextHtmlTest() {
		setContentType("text/html");
		setText("Dataset:  "
				+ "<br>Filters: "
				+ "<blockquote> Valores </blockquote>"
				+ "<br>Classes: "
				+ "<blockquote> Valores </blockquote> "
				+ "<br>Prediction Set:  "
				+ "<br>Classifier: "
				+ "<blockquote> "
				+ "<br>K:  "
				+ "<br>Metric:  "
				+ "<br>Weights: "
				+ "<blockquote> Valores </blockquote> "
				+ "<br>Majority: "
				+ "<blockquote> Threshold: </blockquote></blockquote>"
				
				+ "<table>"
				+ "<thead>"
				+ "<tr>"
				+ "<th>Instance</th>"
				+ "<th>Prediction class</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td>&nbsp;</td>"
				+ "<td>&nbsp;</td>"
				+ "</tr><tr>"
				+ "<td>&nbsp;</td>"
				+ "<td>&nbsp;</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>&nbsp;</td>"
				+ "<td>&nbsp;</td>"
				+ "</tr>"
				+ "<tr>"
				+ "<td>&nbsp;</td>"
				+ "<td>&nbsp;</td"
				+ "></tr>"
				+ "</tbody>"
				+ "</table>");
	}
}
