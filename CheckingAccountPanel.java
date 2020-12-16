import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckingAccountPanel extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckingAccountPanel frame = new CheckingAccountPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CheckingAccountPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("Checking Account");
		label.setFont(new Font("", Font.BOLD, 25));
				
		
		JButton seekButton = new JButton("Query");
		seekButton.setBackground(new Color(204, 204, 255));
		seekButton.setBorderPainted(true);
		seekButton.setFocusPainted(true);

		
		// query method
		seekButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
//				JOptionPane.showMessageDialog();
			
			
			
			
			
			}
		});
		
		JButton storeButton = new JButton("Store");
		storeButton.setBackground(new Color(135, 206, 250));
		storeButton.setBorderPainted(true);
		storeButton.setFocusPainted(true);

		storeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = JOptionPane.showInputDialog(null, "Input currency type", "store currency", JOptionPane.INFORMATION_MESSAGE);
				String cash = JOptionPane.showInputDialog(null, "Input currency amount", "store currency", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JButton takeButton = new JButton("Withdraw");
		takeButton.setBackground(new Color(221, 160, 221));
		takeButton.setForeground(new Color(0, 0, 0));
		takeButton.setBorderPainted(true);
		takeButton.setFocusPainted(true);
		takeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = JOptionPane.showInputDialog(null, "Input currency type", "withdraw currency", JOptionPane.INFORMATION_MESSAGE);
				String cash = JOptionPane.showInputDialog(null, "Input currency amount", "withdraw currency", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		JButton exitButton = new JButton("Log out");
		exitButton.setBackground(new Color(255, 204, 153));
		exitButton.setBorderPainted(true);
		exitButton.setFocusPainted(true);

		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				NewAccountPanel mainFrame = new NewAccountPanel();
				mainFrame.setVisible(true);
							
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(176)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(exitButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
						.addComponent(takeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(storeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(seekButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
					.addGap(167))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(100, Short.MAX_VALUE)
						.addComponent(label)
						.addGap(100))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(15)
					.addComponent(label)
					.addGap(20)
					.addComponent(seekButton)
					.addGap(20)
					.addComponent(storeButton)
					.addGap(20)
					.addComponent(takeButton)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(exitButton)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
