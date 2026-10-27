package gay.chemthunder.tabletop.core;

import gay.chemthunder.tabletop.core.index.*;
import gay.chemthunder.tabletop.core.index.token.TokenModifiers;
import gay.chemthunder.tabletop.core.index.token.TokenTypes;
import gay.chemthunder.tabletop.core.item.PackItem;
import gay.chemthunder.tabletop.core.item.TokenItem;
import gay.chemthunder.tabletop.core.networking.TabletopNetworking;
import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chemthunder
 */
public class Tabletop implements ModInitializer {
	public static final String MOD_ID = "tabletop";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
		TabletopItems.init();
		TabletopRegistries.init();
		TabletopComponentTypes.init();
		TabletopStatTypes.init();
		TabletopCardPacks.init();

		TokenModifiers.init();
		TokenTypes.init();

		TabletopNetworking.register();

		BetterItemTooltipEvent.EVENT.register(new TokenItem.Tooltip());
		BetterItemTooltipEvent.EVENT.register(new PackItem.Tooltip());

		LOGGER.info("Tabletop has been initialized!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
