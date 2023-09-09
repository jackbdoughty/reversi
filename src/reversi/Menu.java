package reversi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Menu extends JPanel {

	Button m_ButtonAI;
	Button m_ButtonRestart;
	IController m_Controller;
	IModel m_Model;
	PlayerWindow m_Window;

	private class ButtonAIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			m_Controller.doAutomatedMove(m_Window.WINDOW_NUMBER);

		}

	}

	private class ButtonRestart implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			m_Controller.startup();

		}

	}

	public Menu(PlayerWindow window, IModel model, IController controller) {

		m_Controller = controller;
		m_Window = window;
		m_Model = model;
		String player;

		if (window.WINDOW_NUMBER == 1) {
			player = "white";
		} else {
			player = "black";
		}

		m_ButtonAI = new Button("Greedy AI (play " + player + ")");
		m_ButtonRestart = new Button("Restart");

		m_ButtonAI.addActionListener(new ButtonAIListener());
		m_ButtonRestart.addActionListener(new ButtonRestart());

		setLayout(new BorderLayout());

		add(m_ButtonAI, BorderLayout.NORTH);
		add(m_ButtonRestart, BorderLayout.SOUTH);

	}

}
