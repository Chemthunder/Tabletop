package gay.chemthunder.tabletop.core.networking.s2c;

import gay.chemthunder.tabletop.core.Tabletop;
import gay.chemthunder.tabletop.core.client.screen.TokenScreen;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

/**
 * @author Chemthunder
 */
public record OpenTokenWikiPayload(ItemStack stack) implements CustomPayload {
    public static final Id<OpenTokenWikiPayload> ID = new Id<>(Tabletop.id("open_token_wiki"));
    public static final PacketCodec<RegistryByteBuf, OpenTokenWikiPayload> CODEC = PacketCodec.tuple(
            ItemStack.PACKET_CODEC, OpenTokenWikiPayload::stack,
            OpenTokenWikiPayload::new
    );

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<OpenTokenWikiPayload> {
        public void receive(OpenTokenWikiPayload payload, ClientPlayNetworking.Context context) {
            context.client().execute(() -> context.client().setScreen(new TokenScreen(payload.stack)));
        }
    }
}
