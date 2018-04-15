package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import org.apache.log4j.Logger;
import com.StartApp;


public class MainWindow extends JFrame{

	final static Logger LOG = Logger.getLogger(MainWindow.class);
	
	private JFrame frame;
	private JTextField textFieldUpdate;
	private JTextField textFieldCreate;
	private JTable DetailsTable;

	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("CURD Operation");
		frame.setBounds(300, 300, 623, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnCreate);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(10, 56, 89, 23);
		frame.getContentPane().add(btnUpdate);
		
		textFieldUpdate = new JTextField();
		textFieldUpdate.setEnabled(false);
		textFieldUpdate.setBounds(246, 57, 46, 20);
		frame.getContentPane().add(textFieldUpdate);
		textFieldUpdate.setColumns(10);
		
		textFieldCreate = new JTextField();
		textFieldCreate.setEnabled(false);
		textFieldCreate.setBounds(246, 12, 46, 20);
		frame.getContentPane().add(textFieldCreate);
		textFieldCreate.setColumns(10);
		
		JLabel lblCreatedCount = new JLabel("Created count  :");
		lblCreatedCount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCreatedCount.setBounds(125, 12, 111, 19);
		frame.getContentPane().add(lblCreatedCount);
		
		JLabel lblUpdatecount = new JLabel("Update count :");
		lblUpdatecount.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUpdatecount.setBounds(125, 59, 111, 14);
		frame.getContentPane().add(lblUpdatecount);
		
		DetailsTable = new JTable();
		DetailsTable.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		DetailsTable.setBounds(10, 417, 570, -220);
		frame.getContentPane().add(DetailsTable);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent ev)
            {
                LOG.debug("WindowEvent on " + ev.paramString());

                if (confirmBeforeExit())
                {
                    System.exit(0);
                }
            }
        });
	}
	
	 public boolean confirmBeforeExit()
	    {
		 	LOG.debug("Display confirm dialog...");

	        if (JOptionPane.showConfirmDialog(this, "YES", "NO", JOptionPane.YES_NO_OPTION) == 0)
	        {
	        	LOG.debug("User answer YES.");
	            return true;
	        }

	        LOG.debug("User answer NO.");
	        return false;
	    }
}
