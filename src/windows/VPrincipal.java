package windows;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.FlowLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import core.Operacion;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JToggleButton;

import util.Mascara;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.text.ParseException;


public class VPrincipal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tF1;
	private JTextField tFoper;
	private JTextField tF2;
	//private JTextField tFResultado;
	JFormattedTextField tFResultado = new JFormattedTextField();

	final Contador tiempo;
	private  JLabel operaciones;
	private  JProgressBar pBOperaciones;
	private JButton tBEstado;
	public JProgressBar pBTiempo;

	public Operacion op;

	static int TIME=60;
	static int OPERACIONES=3;
	static int REMAINOP=OPERACIONES;
	
	public static final String MSJ_GANADO =  "ENHORABUENA has ganado!";
	public static final String MSJ_PERDIDO = "Vuelve a intentarlo";
	
	public VPrincipal() throws ParseException {
		Mascara numeros = new Mascara(Mascara.Numero3Digitos);
		numeros.install(tFResultado);
		
		tFResultado.setFocusLostBehavior(JFormattedTextField.PERSIST);  
		tFResultado.addFocusListener(new FocusListener(){  
		  	            public void focusGained(FocusEvent e)   
		            {  
		            	tFResultado.setText(tFResultado.getText().trim());  
		            }  
		  	            public void focusLost(FocusEvent e) {  
		                // TODO Auto-generated method stub  
		                  		            }});     
		
		setTitle("CALCULADORA HUMANA");
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelSur = new JPanel();
		panelSur.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new BorderLayout(0, 0));

		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(panelNorte, BorderLayout.NORTH);

		JButton bStart = new JButton("Start");		
		panelNorte.add(bStart);

		JButton bSetup = new JButton("Setup");
		panelNorte.add(bSetup);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panelW = new JPanel();
		panel.add(panelW, BorderLayout.WEST);
		panelW.setLayout(new BorderLayout(0, 0));

		pBTiempo = new JProgressBar();
		panelW.add(pBTiempo, BorderLayout.CENTER);
		pBTiempo.setOrientation(SwingConstants.VERTICAL);


		tiempo=new Contador(TIME,this);
		panelW.add(tiempo, BorderLayout.SOUTH);
		tiempo.setFont(new Font("Tahoma", Font.PLAIN, 26));

		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				iniciaJuego();


			}

		});

		JLabel lblTime = new JLabel("TIME");
		panelW.add(lblTime, BorderLayout.NORTH);

		JPanel panelE = new JPanel();
		panel.add(panelE, BorderLayout.EAST);
		panelE.setLayout(new BorderLayout(0, 0));

		operaciones = new JLabel(String.valueOf(this.OPERACIONES));
		panelE.add(operaciones, BorderLayout.SOUTH);
		operaciones.setFont(new Font("Tahoma", Font.PLAIN, 26));

		pBOperaciones = new JProgressBar();		
		pBOperaciones.setOrientation(SwingConstants.VERTICAL);
		panelE.add(pBOperaciones, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("OP");
		panelE.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panelCentro = new JPanel();
		panel.add(panelCentro);
		panelCentro.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panelCentro.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("15px"),
				ColumnSpec.decode("50px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("25px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("50px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("25px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("50px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(10dlu;default)"),},
				new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),}));

		tF1 = new JTextField();
		tF1.setHorizontalAlignment(SwingConstants.CENTER);
		tF1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tF1.setEditable(false);
		panelCentro.add(tF1, "2, 8, fill, fill");
		tF1.setColumns(3);

		tFoper = new JTextField();
		tFoper.setHorizontalAlignment(SwingConstants.CENTER);
		tFoper.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tFoper.setEditable(false);
		panelCentro.add(tFoper, "4, 8, fill, top");
		tFoper.setColumns(1);

		tF2 = new JTextField();
		tF2.setHorizontalAlignment(SwingConstants.CENTER);
		tF2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tF2.setEditable(false);
		panelCentro.add(tF2, "6, 8, fill, fill");
		tF2.setColumns(3);

		JLabel label = new JLabel("=");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelCentro.add(label, "8, 8, fill, center");	
		
		tFResultado.setHorizontalAlignment(SwingConstants.CENTER);
		tFResultado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tFResultado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
					//Comprobar si el resultado es correcto
					//System.out.println("Se ha pulsado enter");
					validaRes();
				}
			}
		});
		
		// Listen for changes in the text
		tFResultado.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			//System.out.println("Se ha pulsado key");
			tBEstado.setVisible(false);
		  }
		  public void removeUpdate(DocumentEvent e) {
			 //System.out.println("Se ha pulsado key");
			tBEstado.setVisible(false);
		  }
		  public void insertUpdate(DocumentEvent e) {
			//System.out.println("Se ha pulsado key");
			tBEstado.setVisible(false);
		  }
		  
		});
		
		panelCentro.add(tFResultado, "10, 8, fill, fill");
		tFResultado.setColumns(3);

		tBEstado = new JButton("");
		tBEstado.setVisible(false);
		//tBEstado.setOpaque(true);
		panelCentro.add(tBEstado, "10, 10, fill, fill");


	}

	private void iniciaJuego(){
		this.REMAINOP=this.OPERACIONES;
		op=new Operacion();
		this.tF1.setText(op.getOp1());
		this.tFoper.setText(op.getOp());
		this.tF2.setText(op.getOp2());
		tiempo.start(TIME);
		operacionesUpdate();
		this.tFResultado.setEditable(true);
		this.tFResultado.setText("");

	}

	private void setOperacion(){
		this.tF1.setText(op.getOp1());
		this.tFoper.setText(op.getOp());
		this.tF2.setText(op.getOp2());
		this.tFResultado.setText("");
	}

	private void validaRes(){
		String temp=tFResultado.getText();
		if (temp.isEmpty()){
			return;
		}
		temp=temp.trim();
		short res=Short.parseShort(temp);	
		
		if( res!=op.getResult()){

			//Fallo
			fallo();
			return;

		}
		
		if( res==op.getResult() && REMAINOP==1 ){
			tiempo.stop();			
			mostrarDialogo(this.MSJ_GANADO);

			//Acierto 
			acierto();

		}

		else 
			if( res==op.getResult() && REMAINOP > 1 ){
				op.next();
				this.setOperacion();

				//Acierto
				acierto();

			}


	}

	private void acierto(){
		//Acierto
		REMAINOP--;	
		this.operacionesUpdate();
		tBEstado.setVisible(true);
		this.tBEstado.setBackground(Color.GREEN);

	}

	private void fallo(){
		//Fallo
		tBEstado.setVisible(true);
		this.tBEstado.setBackground(Color.RED);

	}

	private void operacionesUpdate(){
		this.operaciones.setText(String.valueOf(REMAINOP));
		this.pBOperaciones.setMinimum(0);
		this.pBOperaciones.setMaximum(OPERACIONES);
		this.pBOperaciones.setValue(REMAINOP);
	}
	
	public void mostrarDialogo(String msg){
		this.tFResultado.setEditable(false);
	 	JOptionPane.showInternalMessageDialog(this.getContentPane(), msg, "information",	JOptionPane.INFORMATION_MESSAGE);
	}
}
