package reversi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Circle extends JPanel {

	private Color m_Color;
	private int m_nWidth;
	private int m_nHeight;
	private int m_nPos;
	private int m_nXPos;
	private int m_nYPos;
	private IModel m_Model;
	Color m_PlayerOneColor = Color.WHITE;
	Color m_PlayerTwoColor = Color.BLACK;

	public Circle(int x, int y, int i, int j, int b, IModel model) {

		m_Model = model;
		m_nWidth = x - b * 4;
		m_nHeight = y - b * 4;
		m_nPos = (x / 2 - m_nWidth / 2) - 1;
		m_nXPos = i;
		m_nYPos = j;

		this.setPreferredSize(new Dimension(m_nWidth, m_nHeight));

	}

	protected void paintComponent(Graphics g) {

		switch (m_Model.getBoardContents(m_nXPos, m_nYPos)) {

		case 0:
			m_Color = null;
			this.setVisible(false);
			break;

		case 1:
			m_Color = m_PlayerOneColor;
			this.setVisible(true);
			g.setColor(m_PlayerTwoColor);
			break;

		case 2:
			m_Color = m_PlayerTwoColor;
			this.setVisible(true);
			g.setColor(m_PlayerOneColor);
			break;

		}

		g.fillOval(m_nPos - 1, m_nPos - 1, m_nWidth + 2, m_nHeight + 2);
		g.setColor(m_Color);
		g.fillOval(m_nPos, m_nPos, m_nWidth, m_nHeight);

		g.dispose();

	}

}
