package es.ull.datamining.core.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.renjin.sexp.DoubleVector;

public class TryRenjin {
	// http://www.r-tutor.com/
	// create a script engine manager
	static ScriptEngineManager manager = new ScriptEngineManager();

	static // create a Renjin engine:
	ScriptEngine engine = manager.getEngineByName("Renjin");
	public static void main(String[] args) {
         
        // check if engine has loaded correctly:
		if (engine == null)
			throw new RuntimeException(
					"Renjin Script Engine not found on the classpath.");
		else
			System.out.println("Renjin Script Engine initialized!");
	    // ... put your Java code here ...
	    
	    
		// engine.eval("mydata = read.csv(\"glass.csv\")");
		// engine.eval ("print(mydata)");
		double x = 12.79;
		double mean = 13.24228571;
		double sd = 0.499301458;

		// engine.eval ("print(x)");
		System.out.println(normalDist(x,mean,sd));
		
//		try {
//			engine.eval("library(histogram)");
//			engine.eval("y<-rnorm(100)");
//			engine.eval("histogram(y)");
//		} catch (ScriptException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
	public static double normalDist(double x, double mean, double sd){
		DoubleVector result;
		try {
			result = (DoubleVector)engine.eval ("dnorm("+x+", mean="+mean+", sd="+sd+")");
			return result.get(0);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
}
