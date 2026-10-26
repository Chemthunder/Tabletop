package gay.chemthunder.tabletop.core;

import gay.chemthunder.tabletop.core.client.item.PackProperty;
import gay.chemthunder.tabletop.core.client.item.TokenProperty;
import gay.chemthunder.tabletop.core.networking.TabletopNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.item.property.select.SelectProperties;

/**
 * @author Chemthunder
 */
public class TabletopClient implements ClientModInitializer {
    public void onInitializeClient() {
        SelectProperties.ID_MAPPER.put(TokenProperty.ID, TokenProperty.TYPE);
        SelectProperties.ID_MAPPER.put(PackProperty.ID, PackProperty.TYPE);

        TabletopNetworking.s2c();
    }
}
