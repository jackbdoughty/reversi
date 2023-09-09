package reversi;

public class GUIView implements IView {

	private PlayerWindow m_PlayerOne;
	private PlayerWindow m_PlayerTwo;
	private IModel m_Model;
	private IController m_Controller;

	public void initialise(IModel model, IController controller) {

		m_Model = model;
		m_Controller = controller;

		m_PlayerOne = new PlayerWindow(1, m_Model, m_Controller);
		m_PlayerTwo = new PlayerWindow(2, m_Model, m_Controller);

		m_PlayerTwo.m_GuiFrame.setLocation(m_PlayerOne.m_GuiFrame.getX() + m_PlayerOne.m_GuiFrame.getWidth() + 5,
				m_PlayerOne.m_GuiFrame.getY());

	}

	public void refreshView() {

		m_PlayerOne.m_GuiFrame.repaint();
		m_PlayerTwo.m_GuiFrame.repaint();

	}

	public void feedbackToUser(int player, String message) {

		if (m_PlayerOne.WINDOW_NUMBER == player) {
			m_PlayerOne.m_Text.setText(message);
			return;
		}
		;

		m_PlayerTwo.m_Text.setText(message);

	}

}