package gay.chemthunder.tabletop.core;

import gay.chemthunder.tabletop.core.client.event.TokenStackOverlayEvent;
import gay.chemthunder.tabletop.core.client.item.HasChipProperty;
import gay.chemthunder.tabletop.core.client.item.PackProperty;
import gay.chemthunder.tabletop.core.client.item.TokenChipProperty;
import gay.chemthunder.tabletop.core.client.item.TokenProperty;
import gay.chemthunder.tabletop.core.networking.TabletopNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.DrawItemStackOverlayCallback;
import net.minecraft.client.render.item.property.bool.BooleanProperties;
import net.minecraft.client.render.item.property.select.SelectProperties;

/**
 * @author Chemthunder
 */
public class TabletopClient implements ClientModInitializer {
    public void onInitializeClient() {
        SelectProperties.ID_MAPPER.put(TokenProperty.ID, TokenProperty.TYPE);
        SelectProperties.ID_MAPPER.put(TokenChipProperty.ID, TokenChipProperty.TYPE);

        SelectProperties.ID_MAPPER.put(PackProperty.ID, PackProperty.TYPE);

        BooleanProperties.ID_MAPPER.put(HasChipProperty.ID, HasChipProperty.CODEC);

        TabletopNetworking.s2c();

        DrawItemStackOverlayCallback.EVENT.register(new TokenStackOverlayEvent());
    }
}
