package org.warn.tc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;






public class SwingTemperatureConverter {

	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame("Temperature Converter");
		frame.add( new TemperatureConverterComponent());
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		frame.setSize( 350, 150 );
		frame.setBackground(Color.white);
		frame.setVisible( true );
	}

}



class TemperatureConverterComponent extends JComponent implements MouseMotionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	JButton convertButton;
	JButton switchConversionButton;
	JButton clearFieldsButton;
	JLabel unitLabel1;
	JLabel unitLabel2;
	JLabel arrowLabel;
	JLabel errMsgLabel;
	JLabel convertedTemperature;
	JTextField inputTemperature;
	
	TemperatureConverter tempConverter;
	
	
	public TemperatureConverterComponent() {
		
		tempConverter = new TemperatureConverter();
		
		convertButton = new JButton("Convert");
		switchConversionButton = new JButton("Switch Convertion");
		clearFieldsButton = new JButton("Clear");
		unitLabel1 = new JLabel(tempConverter.getUnit1Symbol());
		unitLabel2 = new JLabel(tempConverter.getUnit2Symbol());
		
		URL imgURL = getClass().getResource("images/arrow1.jpg");
		ImageIcon icon = new ImageIcon(imgURL, "");
		//arrowLabel = new JLabel("\t--->\t");
		arrowLabel = new JLabel(icon);
		
		errMsgLabel = new JLabel("");
		errMsgLabel.setForeground(Color.red);
		convertedTemperature = new JLabel("0.0");
		inputTemperature = new JTextField(5);
		
		setLayout( new FlowLayout() );
		add( convertButton );
		add( switchConversionButton );
		add( clearFieldsButton );
		add( inputTemperature );
		add( unitLabel1 );
		add( arrowLabel );
		add( convertedTemperature );
		add( unitLabel2 );
		add( errMsgLabel );
		
		convertButton.addActionListener( this );
		switchConversionButton.addActionListener( this );
		clearFieldsButton.addActionListener( this );
		addMouseMotionListener( this );
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if ( e.getSource() == convertButton ) {
			convert();
		} else if ( e.getSource() == switchConversionButton ) {
			switchConversion();
		} else if ( e.getSource() == clearFieldsButton ) {
			clearFields();
		}
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}
	
	
	public void convert() {
		
		String strInputTemperature = inputTemperature.getText();
		
		if ( !strInputTemperature.equals("") ) {
			String currentConvCode = tempConverter.getCurrentConversionCode();
			String strConvertedTemperature = tempConverter.convert(strInputTemperature, currentConvCode);
			
			if ( strConvertedTemperature==null ) {
				errMsgLabel.setText("Please enter numerical value!\t\t\t\t\t");
				inputTemperature.setText("");
			} else {
				errMsgLabel.setText("");
				convertedTemperature.setText(strConvertedTemperature);
			}
		}
		
	}
	
	
	public void switchConversion() {
		tempConverter.swapConv();
		clearFields();
		unitLabel1.setText(tempConverter.getUnit1Symbol());
		unitLabel2.setText(tempConverter.getUnit2Symbol());
	}
	
	
	public void clearFields() {
		inputTemperature.setText("");
		convertedTemperature.setText("0.0");
		errMsgLabel.setText("");
	}
	
}
