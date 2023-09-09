package reversi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Square extends JPanel {

	private Color m_Color = Color.GREEN;
	private Color m_BorderColor = Color.BLACK;

	private int m_nBorderSize = 2;
	private int m_nWidth = 50;
	private int m_nHeight = 50;
	private int m_nXPos;
	private int m_nYPos;

	Circle m_Circle;
	SquareListener m_Listener;
	IController m_Controller;
	IModel m_Model;
	Button m_Button;
	PlayerWindow m_Window;

	public class SquareListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			m_Controller.squareSelected(m_Window.WINDOW_NUMBER, m_nXPos, m_nYPos);

		}

	}

	public Square(int x, int y, PlayerWindow window, IController c, IModel m) {

		m_nXPos = x;
		m_nYPos = y;
		m_Controller = c;
		m_Model = m;
		m_Window = window;

		setMinimumSize(new Dimension(m_nWidth, m_nHeight));
		setPreferredSize(new Dimension(m_nWidth, m_nHeight));

		m_Circle = new Circle(m_nWidth, m_nHeight, m_nXPos, m_nYPos, m_nBorderSize, m_Model);
		this.add(m_Circle);

		m_Button = new Button(m_nWidth, m_nHeight);
		m_Button.invis();
		m_Button.addActionListener(new SquareListener());

		this.add(m_Button);

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		m_nWidth = getWidth();
		m_nHeight = getHeight();

		g.setColor(m_BorderColor);
		g.fillRect(0, 0, m_nWidth, m_nHeight);
		g.setColor(m_Color);
		g.fillRect(m_nBorderSize, m_nBorderSize, m_nWidth - m_nBorderSize * 2, m_nHeight - m_nBorderSize * 2);

		m_Circle.paintComponent(g);

	}

}