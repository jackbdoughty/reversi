package reversi;

import java.util.Arrays;

public class ReversiController implements IController {

	private int m_nWidth;
	private int m_nHeight;
	private IModel m_Model;
	private IView m_View;
	private int m_VECTORS[][] = { { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };

	public void initialise(IModel model, IView view) {

		m_Model = model;
		m_View = view;

		m_nWidth = m_Model.getBoardWidth();
		m_nHeight = m_Model.getBoardHeight();

	}

	public void startup() {

		for (int i = 0; i < m_nWidth; i++) {

			for (int j = 0; j < m_nHeight; j++) {

				m_Model.setBoardContents(i, j, 0);

			}

		}

		m_Model.setBoardContents(m_nWidth / 2 - 1, m_nHeight / 2 - 1, 1);
		m_Model.setBoardContents(m_nWidth / 2 - 1, m_nHeight / 2, 2);
		m_Model.setBoardContents(m_nWidth / 2, m_nHeight / 2, 1);
		m_Model.setBoardContents(m_nWidth / 2, m_nHeight / 2 - 1, 2);
		m_Model.setPlayer(1);

		update();

		m_View.feedbackToUser(1, "White player - choose where to put your piece");
		m_View.feedbackToUser(2, "Black player - not your turn");
		m_View.refreshView();

	}

	public void update() {

		if (canPlayerMove(m_Model.getPlayer()) == false)
			m_Model.setPlayer(Math.abs(m_Model.getPlayer() - 2) + 1);
		if (canPlayerMove(m_Model.getPlayer()) == false) {

			String feedback;
			int p1 = countOwnPieces(1);
			int p2 = countOwnPieces(2);

			if (p1 > p2) {
				feedback = "White won. White " + p1 + " to Black " + p2 + ".";
			} else if (p2 > p1) {
				feedback = "Black won. Black " + p2 + " to white " + p1 + ".";
			} else {
				feedback = "Draw. Both players ended with " + p1 + " pieces";
			}

			m_View.feedbackToUser(1, feedback);
			m_View.feedbackToUser(2, feedback);

			m_Model.setFinished(true);

		}

		else {

			String whiteFeedback;
			String blackFeedback;
			
			m_Model.setFinished(false);

			if (m_Model.getPlayer() == 1) {

				whiteFeedback = "White player - choose where to put your piece";
				blackFeedback = "Black player - not your turn";

			} else {

				blackFeedback = "Black player - choose where to put your piece";
				whiteFeedback = "White player - not your turn";

			}

			m_View.feedbackToUser(1, whiteFeedback);
			m_View.feedbackToUser(2, blackFeedback);

		}

		m_View.refreshView();

	}

	private int countOwnPieces(int player) {

		int n = 0;

		for (int i = 0; i < m_Model.getBoardWidth(); i++) {

			for (int j = 0; j < m_Model.getBoardHeight(); j++) {

				if (m_Model.getBoardContents(i, j) == player)
					n++;

			}
		}

		return n;

	}

	private boolean canPlayerMove(int player) {

		boolean playerCanPlay = false;

		for (int i = 0; i < m_Model.getBoardWidth(); i++) {

			for (int j = 0; j < m_Model.getBoardHeight(); j++) {

				if (countPieces(findPieces(player, i, j)) != 0) {

					playerCanPlay = true;

				}

			}

		}

		return playerCanPlay;

	}

	public void squareSelected(int player, int x, int y) {

		int[][][] targets;

		if (player != m_Model.getPlayer()) {

			m_View.feedbackToUser(player, "It is not your turn!");
			return;

		}

		targets = findPieces(player, x, y);

		if (countPieces(targets) != 0) {

			m_Model.setBoardContents(x, y, player);

			for (int[][] directions : targets) {

				for (int[] target : directions) {

					if (target[0] > -1 && target[1] > -1) {

						m_Model.setBoardContents(target[0], target[1], player);

					}
				}

			}

			m_Model.setPlayer(Math.abs(player - 2) + 1);

		}

		update();

	}

	public void doAutomatedMove(int player) {

		int move[] = { 0, 0 };
		int score = -1;
		int tempScore = 0;

		for (int i = 0; i < m_Model.getBoardWidth(); i++) {

			for (int j = 0; j < m_Model.getBoardHeight(); j++) {

				if ((tempScore = countPieces(findPieces(player, i, j))) > score) {

					score = tempScore;
					move[0] = i;
					move[1] = j;

				}

			}

		}

		squareSelected(player, move[0], move[1]);

	}

	private int[][][] findPieces(int player, int x, int y) {

		int target = Math.abs(player - 2) + 1;
		int[][][] targets = new int[8][Math.round((float) Math.hypot(m_Model.getBoardHeight(), m_Model.getBoardWidth()))][2];

		fillArray(targets, -1);

		if (m_Model.getBoardContents(x, y) != 0)
			return targets;

		for (int n = 0; n < m_VECTORS.length; n++) {

			int[] vector = m_VECTORS[n];
			int scale = 1;
			boolean line = false;

			int i = x + vector[0] * scale;
			int j = y + vector[1] * scale;

			while ((0 < i) && (i < m_Model.getBoardWidth()) && (0 < j) && (j < m_Model.getBoardHeight())) {

				if (m_Model.getBoardContents(i, j) == 0) {
					fillArray(targets[n], -1);
					break;
				} else if (m_Model.getBoardContents(i, j) == target) {
					targets[n][scale - 1][0] = i;
					targets[n][scale - 1][1] = j;
				} else {
					line = true;
					break;
				}

				scale++;

				i = x + vector[0] * scale;
				j = y + vector[1] * scale;

			}

			if (line == false)
				fillArray(targets[n], -1);

		}

		return targets;

	}

	private int countPieces(int[][][] targets) {

		int pieces = 0;

		for (int[][] directions : targets) {

			for (int[] target : directions) {

				if (target[0] > -1 && target[1] > -1) {

					pieces += 1;

				}

			}

		}

		return pieces;

	}

	private void fillArray(int[][][] arr, int n) {

		for (int[][] i : arr) {

			for (int[] j : i) {

				Arrays.fill(j, n);

			}

		}

	}

	private void fillArray(int[][] arr, int n) {

		for (int[] i : arr) {

			Arrays.fill(i, n);

		}

	}

}
