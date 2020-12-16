
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerSurface extends JFrame {

	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerSurface frame = new ManagerSurface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManagerSurface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JButton accountsButton = new JButton("Accounts Information");
		accountsButton.setBackground(new Color(204, 204, 255));
		accountsButton.setBorderPainted(true);
		accountsButton.setFocusPainted(true);
		
		
		// go to accounts chart
		accountsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				// new NewAccountPanel().setVisible(true);
			}
		});
		
		JButton bankOverviewButton = new JButton("Bank Overview");
		bankOverviewButton.setBackground(new Color(255, 192, 203));
		bankOverviewButton.setBorderPainted(true);
		bankOverviewButton.setFocusPainted(true);

		bankOverviewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		
		JButton transButton = new JButton("Transaction Information");
		transButton.setBackground(new Color(204, 204, 255));
		transButton.setBorderPainted(true);
		transButton.setFocusPainted(true);
		
		transButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManagerTransactionQuery().setVisible(true);
			}
		});
		
		JButton exitButton = new JButton("Log Out");
		exitButton.setBackground(new Color(255, 239, 213));
		exitButton.setBorderPainted(true);
		exitButton.setFocusPainted(true);

		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
//				NewAccountPanel mainFrame = new NewAccountPanel();
//				mainFrame.setVisible(true);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(130)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(exitButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(transButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(accountsButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(bankOverviewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(accountsButton)
					.addGap(28)
					.addComponent(bankOverviewButton)
					.addGap(25)
					.addComponent(transButton)
					.addGap(25)
					.addComponent(exitButton)
					.addContainerGap(35, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}