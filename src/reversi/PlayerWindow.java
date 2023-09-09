package reversi;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PlayerWindow {

	JPanel m_Grid;
	Menu m_Menu;
	JFrame m_GuiFrame;
	JTextArea m_Text;
	int WINDOW_NUMBER;

	public PlayerWindow(int p, IModel model, IController controller) {

		int h;

		WINDOW_NUMBER = p;
		m_GuiFrame = new JFrame();
		m_Text = new JTextArea();
		m_Grid = new JPanel();
		m_Menu = new Menu(this, model, controller);

		m_GuiFrame.setVisible(false);
		m_GuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_GuiFrame.getContentPane().setLayout(new BorderLayout());

		m_GuiFrame.getContentPane().add(m_Grid, BorderLayout.CENTER);
		m_GuiFrame.getContentPane().add(m_Menu, BorderLayout.SOUTH);
		m_GuiFrame.getContentPane().add(m_Text, BorderLayout.NORTH);

		m_Grid.setLayout(new GridLayout(model.getBoardWidth(), model.getBoardHeight()));

		if (WINDOW_NUMBER == 1) {
			m_GuiFrame.setTitle("Reversi - white player");
			h = 0;
		} else {
			m_GuiFrame.setTitle("Reversi - black player");
			h = model.getBoardHeight() - 1;
		}

		for (int i = 0; i < model.getBoardWidth(); i++) {

			for (int j = 0; j < model.getBoardHeight(); j++) {

				m_Grid.add(new Square(Math.abs(h - i), Math.abs(h - j), this, controller, model));

			}

		}

		m_GuiFrame.pack();
		m_GuiFrame.setResizable(false);
		m_GuiFrame.setLocationRelativeTo(null);
		m_GuiFrame.setVisible(true);

	}

}
