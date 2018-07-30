package modelelementsatruntime.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class AboutHandler.
 */
public class AboutHandler {
	
	/**
	 * Execute.
	 *
	 * @param shell the shell
	 */
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, "About", "Creating Model elements at runtime 1.0");
	}
}
