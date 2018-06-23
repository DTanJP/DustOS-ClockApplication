import javax.swing.JPanel;

import org.cardboard.dtanjp.Computer;
import Plugin.Plugin;

/**
 * MainApp.java
 * DustOS Application
 * Draws a animated clock
 * 
 * @author David
 **/
public class MainApp implements Plugin {

	@Override
	public void Destruct() {
		System.out.println("[Clock App]: Destruct");
	}

	@Override
	public void Initialize() {
		System.out.println("[Clock App]: Initialized");
		instance = this;
	}

	@Override
	public void OnDisable() {
		System.out.println("[Clock App]: Disabled");
	}

	@Override
	public void OnEnable() {
		instance = this;
		appState = AppState.getInstance();
		System.out.println("[Clock App]: Enabled");
		Computer.getInstance().GetOS().Request(this, "OS", "add-component", "main-panel", appState);
	}

	@Override
	public void Request(Plugin sender, String plugin, String command, Object... param) {
		
	}

	@Override
	public boolean RequestShutDown() {
		return false;
	}

	@Override
	public void Update() {
	}
	
	/** Variables **/
	private static JPanel appState = AppState.getInstance();
	public static MainApp instance = null;
}
