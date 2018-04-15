package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class MainWindow {

	private JFrame frame;
	private JTextField textFieldUpdate;
	private JTextField textFieldCreate;
	private JTable DetailsTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 623, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
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
	}
}
