/**
 * IFPB - TSI/POO
 * Prof. Fausto Ayres
 * 
 * Aplicação swing do Quebra-Cabeca
 * 
 * Alunos: Lucas Sales
 *         Maurício Pereira
 * 
 */

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class QuebraCabecaSwing {

	private JFrame frame;
	private JButton btnStart;
	private QuebraCabeca jogo;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuebraCabecaSwing window = new QuebraCabecaSwing();
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
	public QuebraCabecaSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(51, 51, 51));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setTitle("Quebra Cabeca");
		frame.setBounds(100, 100, 1029, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// RADIO BUTTONS LEVELS

		JRadioButton btnEasy = new JRadioButton("Easy");
		btnEasy.setForeground(new Color(102, 153, 153));
		btnEasy.setBackground(new Color(51, 51, 51));
		btnEasy.setFont(new Font("Showcard Gothic", Font.PLAIN, 14));
		btnEasy.setSelected(true);
		buttonGroup.add(btnEasy);
		btnEasy.setBounds(30, 192, 80, 23);
		frame.getContentPane().add(btnEasy);
		
		JRadioButton btnNormal = new JRadioButton("Normal");
		btnNormal.setForeground(new Color(102, 153, 153));
		btnNormal.setBackground(new Color(51, 51, 51));
		btnNormal.setFont(new Font("Showcard Gothic", Font.PLAIN, 14));
		buttonGroup.add(btnNormal);
		btnNormal.setBounds(30, 229, 94, 23);
		frame.getContentPane().add(btnNormal);
		
		JRadioButton btnHard = new JRadioButton("Hard");
		btnHard.setForeground(new Color(102, 153, 153));
		btnHard.setBackground(new Color(51, 51, 51));
		btnHard.setFont(new Font("Showcard Gothic", Font.PLAIN, 14));
		buttonGroup.add(btnHard);
		btnHard.setBounds(30, 265, 80, 23);
		frame.getContentPane().add(btnHard);
		
		JLabel lblDifficulty = new JLabel("Difficulty:");
		lblDifficulty.setForeground(new Color(153, 204, 204));
		lblDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifficulty.setFont(new Font("Showcard Gothic", Font.PLAIN, 18));
		lblDifficulty.setBounds(10, 154, 154, 23);
		frame.getContentPane().add(lblDifficulty);
		
		//componentes inciais da tela do programa
		
		JPanel initial = new JPanel();
		initial.setSize(830, 480);
		initial.setLocation(174, 11);
		initial.setBackground(new Color(51, 51, 51));
		frame.getContentPane().add(initial);
		initial.setLayout(null);
		
		JLabel lblPuzzle = new JLabel();
		lblPuzzle.setBackground(new Color(51, 51, 51));
		lblPuzzle.setBounds(315, 68, 200, 200);
		ImageIcon im = new ImageIcon(QuebraCabeca.class.getResource("/img/puzzle.png"));
		lblPuzzle.setIcon(new ImageIcon(im.getImage().getScaledInstance(
				lblPuzzle.getWidth(),lblPuzzle.getHeight(), Image.SCALE_DEFAULT)));
		lblPuzzle.setOpaque(true);
		initial.add(lblPuzzle);
		
		JLabel lblOrientation = new JLabel("Choice one difficulty and click START");
		lblOrientation.setBackground(new Color(0, 153, 153));
		lblOrientation.setForeground(new Color(255, 255, 255));
		lblOrientation.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		lblOrientation.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrientation.setBounds(163, 323, 503, 54);
		lblOrientation.setOpaque(true);
		initial.add(lblOrientation);
		
		// botão start
		
		btnStart = new JButton("START");
		btnStart.setForeground(new Color(255, 255, 255));
		btnStart.setBackground(new Color(0, 153, 153));
		btnStart.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().remove(initial);
				if(btnStart.getText() == "RESTART") {
					frame.getContentPane().remove(jogo);
				}
				
				int dificuldade = 4;
				if(btnEasy.isSelected()) {
					dificuldade = 4;
				} else if (btnNormal.isSelected()){
					dificuldade = 5;
				} else if(btnHard.isSelected()) {
					dificuldade = 8;
				}
				
				//criar o quebra-cabeca
				try {
					jogo = new QuebraCabeca(dificuldade);
					jogo.setSize(830, 480);
					jogo.setLocation(174, 11);
					jogo.setBackground(new Color(102, 102, 102));
					frame.getContentPane().add(jogo);
					
					jogo.iniciar();
					btnStart.setText("RESTART");
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnStart.setBounds(10, 20, 154, 62);
		frame.getContentPane().add(btnStart);
		
		//nomes dos integrantes do grupo
		
		JLabel lblLucas = new JLabel("Lucas Sales");
		lblLucas.setHorizontalAlignment(SwingConstants.CENTER);
		lblLucas.setForeground(new Color(204, 204, 204));
		lblLucas.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
		lblLucas.setBounds(10, 431, 154, 30);
		frame.getContentPane().add(lblLucas);
		
		JLabel lblMauricio = new JLabel("Mauricio Pereira");
		lblMauricio.setHorizontalAlignment(SwingConstants.CENTER);
		lblMauricio.setForeground(new Color(204, 204, 204));
		lblMauricio.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
		lblMauricio.setBounds(10, 461, 154, 30);
		frame.getContentPane().add(lblMauricio);
	}
}