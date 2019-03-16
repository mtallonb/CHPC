package core;
import java.util.Random;
import java.util.Vector;


public class Util {
	public static Random Aleatorio=new Random(System.currentTimeMillis());
	
		 
		  public static short  shortRange(short a, short b){
		    if ( a > b ) {
		      throw new IllegalArgumentException("Start cannot exceed End.");
		    }
		    //get the range, casting to long to avoid overflow problems
		    short range = (short) (b - a + 1);
		    
		    return (short) (Aleatorio.nextInt(range)+a);
		  }
		  
		  public static Vector<Integer> divisores(int a){
			 Vector<Integer> divisores = new Vector<Integer>(2);
			 divisores.add(1);
			 divisores.add(a);
			  for (int i=2; i<Math.floor(a/2);i++){
				  if(Math.IEEEremainder(a,i)==0){
					  divisores.add(i);
				  }
			  }
			  return divisores;
		  }
}

