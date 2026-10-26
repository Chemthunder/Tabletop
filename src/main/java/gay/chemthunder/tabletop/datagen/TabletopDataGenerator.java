package gay.chemthunder.tabletop.datagen;

import gay.chemthunder.tabletop.datagen.providers.client.TabletopModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * @author Chemthunder
 */
public class TabletopDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fdg) {
		var pack = fdg.createPack();

		/// CLIENT ===
		pack.addProvider(TabletopModelProvider::new);
	}
}
