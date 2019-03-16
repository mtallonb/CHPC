package core;

import java.util.Vector;


public class Operacion {	
	public final short TOPESUP=199; //Máximo valor para resultado
	public final short TOPEINF=5; //Mínimo valor para operadores de suma y resta


	short operando1;
	char operador;
	short operando2;
	
	short op1OLD;
	short op2OLD;

	public Operacion(){
		operador=this.genOperador();
		this.setOperado1();
		this.setOperando2();
		this.op1OLD=this.operando1;
		this.op2OLD=this.operando2;

	}

	public void setOperado1(){
		operando1=(short) Util.shortRange(TOPEINF, (short) (TOPESUP-TOPEINF));

	}

	public void setOperando2(){

		do{
			operador=genNewOperador();
			switch (operador){
			case('+'): operando2=getSuma();break;
			case('-'): operando2=getResta();break;
			case('*'): operando2=getMult();break;
			case('/'): operando2=getDiv();break;
			}
		}while (operando2==-1 || operando2==this.op1OLD || operando2==this.op2OLD);

	}


	public void next(){
		//Nueva operacion
		this.op1OLD=this.operando1;
		this.op2OLD=this.operando2;
		
		operando1=getResult();		
		this.setOperando2();
		
	}

	public short getResult(){
		switch (operador){
		case('+'): return (short) (operando1+operando2);
		case('-'): return (short) (operando1-operando2);
		case('*'): return (short) (operando1*operando2);
		case('/'): return (short) (operando1/operando2);
		}
		return 0;

	}
	
	public String getOp1(){
		return String.valueOf(this.operando1);
	}
	
	public String getOp(){
		return String.valueOf(this.operador);
	}
	
	public String getOp2(){
		return String.valueOf(this.operando2);
	}

	private char genOperador(){
		int aleatorio;
		
		aleatorio=Util.Aleatorio.nextInt(6);
		switch (aleatorio){//De 0 a 3 o mejor a 5 para hacer más probable la /
		case 0: return '+';
		case 1: return '-';
		case 2:
		case 3: return '*';
		case 4: return '/';
		}
		return '/'; //Por defecto
	}

	private char genNewOperador(){ //Evitamos que se repita el operador
		char newOp;
		do {
			newOp = genOperador();
		}while(operador==newOp);

		return newOp;
	}
	
	private short getSuma() {
		// Devuelve operador válido para sumar al operando1
		short b=(short) (TOPESUP-operando1-TOPEINF);
		if (b>TOPEINF)
			return Util.shortRange(TOPEINF,b );
		else return -1;
	}

	private short getResta() {
		// Devuelve operador válido para restar al operando1
		short b=(short) (operando1-TOPEINF);
		if (b>TOPEINF)
			return Util.shortRange(TOPEINF,b );
		else return -1;
	}

	private short getMult() {
		// Devuelve operador válido para multiplicar al operando1
		short b= (short) Math.floor((double)(TOPESUP/operando1));
		if (b>1)
			return Util.shortRange((short) 2,b);
		else return -1;
	}

	private short getDiv() {
		// Devuelve operador válido para dividir al operando1
		Vector<Integer> divisores=Util.divisores(operando1);
		int tam=divisores.size();
		if(tam>2){
			return new Integer( divisores.get(Util.shortRange((short)2,(short) (tam-1) ))).shortValue(); 
			//Desde el 2 ya que añadimos como divisor el mismo
		}else return -1;
	}

}
