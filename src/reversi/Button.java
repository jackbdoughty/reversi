package reversi;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

public class Button extends JButton {

	private int m_nWidth;
	private int m_nHeight;

	public Button(int w, int h) {

		m_nWidth = w;
		m_nHeight = h;

		setMinimumSize(new Dimension(m_nWidth, m_nHeight));
		setPreferredSize(new Dimension(m_nWidth, m_nHeight));

		this.setOpaque(true);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	public Button(String text) {

		setText(text);
		this.setOpaque(true);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));

	}

	public void invis() {

		this.setContentAreaFilled(false);
		this.setBorderPainted(false);

	}

}
