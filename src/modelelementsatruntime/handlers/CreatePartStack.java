
package modelelementsatruntime.handlers;

import java.util.Random;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

/**
 * The Class CreatePartStack.
 */
public class CreatePartStack {

	/** The rand. */
	Random rand = new Random();

	/**
	 * Execute.
	 *
	 * @param application  the application
	 * @param modelservice the modelservice
	 */
	@Execute
	public void execute(MApplication application, EModelService modelservice) {
		MPartSashContainer mPartSashContainer = (MPartSashContainer) modelservice
				.find("ModelElementsAtRuntime.partsashcontainer.sample", application);

		if (mPartSashContainer != null) {
			MPartStack partStack = MBasicFactory.INSTANCE.createPartStack();
			partStack.setElementId("dynamicpart.samplepartstack" + rand.nextInt(15));

			MPart dynamicPart = createDynamicPart();

			partStack.getChildren().add(dynamicPart);

			mPartSashContainer.setHorizontal(true);
			mPartSashContainer.getChildren().add(partStack);
			mPartSashContainer.setSelectedElement(partStack);
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
		samplePart.setLabel("Dynamic Part");

		// Set unique element id to the part
		samplePart.setElementId("samplepart" + rand.nextInt(10));

		return samplePart;
	}

}