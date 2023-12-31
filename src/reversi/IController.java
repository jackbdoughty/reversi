package reversi;

public interface IController {
	/**
	 * Initialise the controller for a model and view
	 * 
	 * @param model The model to use
	 * @param view  The view to use.
	 */
	void initialise(IModel model, IView view);

	/**
	 * Start the game again - set up view and board appropriately See requirements -
	 * don't forget to set the player number and finished flag to the correct values
	 * as well as clearing the board.
	 */
	void startup();

	void update();
	
	void squareSelected(int player, int x, int y);

	void doAutomatedMove(int player);
}
