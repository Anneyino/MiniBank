// register panel

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class RegisterSurface extends JFrame 
{

	private JPanel contentPane;
	private JTextField name;
	private JPasswordField password;
	private JPasswordField confirmPassword;


	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					RegisterSurface frame = new RegisterSurface();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterSurface() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700,300, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		setContentPane(contentPane);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("", Font.PLAIN, 18));
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("", Font.PLAIN, 18));
		
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setFont(new Font("", Font.PLAIN, 18));
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setBackground(new Color(175, 238, 238));
		confirmButton.setBorderPainted(false);
		confirmButton.setFocusPainted(false);
		
		name = new JTextField();
		name.setFont(new Font("", Font.PLAIN, 18));
		name.setOpaque(false);
		
		password = new JPasswordField();
		password.setFont(new Font("", Font.PLAIN, 18));
		password.setOpaque(false);
		
		confirmPassword = new JPasswordField();
		confirmPassword.setFont(new Font("", Font.PLAIN, 18));
		confirmPassword.setOpaque(false);
		
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(245, 222, 179));
		cancelButton.setBorderPainted(false);
		cancelButton.setFocusPainted(false);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(passwordLabel)
						.addComponent(confirmPasswordLabel)
						.addComponent(nameLabel))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(confirmButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(cancelButton))
						.addComponent(confirmPassword, Alignment.TRAILING)
						.addComponent(password, Alignment.TRAILING)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addContainerGap(37, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameLabel))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordLabel))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(confirmPasswordLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(confirmButton)
						.addComponent(cancelButton))
					.addGap(30))
		);
		//name.addKeyListener(new PasswordText());
		password.addKeyListener(new PasswordText());
		confirmPassword.addKeyListener(new PasswordText());
		confirmButton.addActionListener(new confirmButtonHandler(name, password, confirmPassword));
		contentPane.setLayout(gl_contentPane);
	}
	
	// numbers only
	public class PasswordText extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			int key=e.getKeyChar();
			
			if((!(key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9)) )
			{
				e.consume();
			}
		}
	}
	
	// confirm listener
	public class confirmButtonHandler implements ActionListener{
		private JTextField name;
		private JPasswordField password;
		private JPasswordField confirmPassword;
		private int sameChar = 0;
		
		public confirmButtonHandler(JTextField name,JPasswordField newPassword,JPasswordField confirmPassword) {
			this.name = name;
			this.password = newPassword;
			this.confirmPassword = confirmPassword;
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			//Todo
		}
			
	}
}