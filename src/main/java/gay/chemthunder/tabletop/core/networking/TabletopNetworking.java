package gay.chemthunder.tabletop.core.networking;

import gay.chemthunder.tabletop.core.networking.s2c.OpenTokenWikiPayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

/**
 * @author Chemthunder
 */
public interface TabletopNetworking {
    static void register() {
        PayloadTypeRegistry.playS2C().register(OpenTokenWikiPayload.ID, OpenTokenWikiPayload.CODEC);
    }

    @Environment(EnvType.CLIENT)
    static void s2c() {
        ClientPlayNetworking.registerGlobalReceiver(OpenTokenWikiPayload.ID, new OpenTokenWikiPayload.Receiver());
    }
}
