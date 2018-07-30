
package modelelementsatruntime.handlers;

import java.util.Random;

import org.eclipse.e4.core.contexts.Active;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

/**
 * The Class CreatePartHandler.
 */
public class CreatePartHandler {

	/**
	 * Execute.
	 *
	 * @param activePart   the active part
	 * @param application  the application
	 * @param modelservice the modelservice
	 */
	@Execute
	public void execute(@Active MPart activePart, MApplication application, EModelService modelservice) {

		MPartStack partStack = null;
		MPartSashContainer mPartSashContainer = null;
		MElementContainer<MUIElement> container = activePart.getParent();

		String elementId = container.getElementId();

		MUIElement find = modelservice.find(elementId, application);

		if (find instanceof MPartStack) {
			partStack = (MPartStack) find;
		} else if (find instanceof MPartSashContainer) {
			mPartSashContainer = (MPartSashContainer) find;
		}

		if (partStack != null) {
			MPart dynamicPart = createDynamicPart();

			// Specify the control (PartStack/PartSashContainer/Control) you want to append the part
			partStack.getChildren().add(dynamicPart);

			// Select the dynamically created part
			partStack.setSelectedElement(dynamicPart);

		} else {
			MPart dynamicPart = createDynamicPart();

			// Specify the control (PartStack/PartSashContainer/Control) you want to append
			// this part to
			mPartSashContainer.getChildren().add(dynamicPart);

			// Select the dynamically created part
			mPartSashContainer.setSelectedElement(dynamicPart);
		}
	}

	/**
	 * Creates the dynamic part.
	 *
	 * @return the m part
	 */
	private MPart createDynamicPart() {
		// Create a part instance using MBasicfactory
		MPart samplePart = MBasicFactory.INSTANCE.createPart();

		// Make it false if you do not need the close button in Part
		samplePart.setCloseable(true);

		// Set the label for the part
		Random rand = new Random();
		samplePart.setLabel("Dynamic Part");

		// Set unique element id to the part
		samplePart.setElementId("samplepart" + rand.nextInt(10));

		return samplePart;
	}

}