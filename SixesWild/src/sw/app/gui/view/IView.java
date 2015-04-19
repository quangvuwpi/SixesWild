package sw.app.gui.view;


public interface IView {

	/** Initialize the View, this method is called after View is created */
	void initialize();
	
	/** Clean up the View before switching to another View */
	void cleanup();
	
}
