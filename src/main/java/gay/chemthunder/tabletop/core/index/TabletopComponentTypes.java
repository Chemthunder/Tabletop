package gay.chemthunder.tabletop.core.index;

import gay.chemthunder.tabletop.api.CardPack;
import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.Tabletop;
import net.acoyt.acornlib.api.registrants.DataComponentTypeRegistrant;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;

/**
 * @author Chemthunder
 */
public interface TabletopComponentTypes {
    DataComponentTypeRegistrant plugin = new DataComponentTypeRegistrant(Tabletop.MOD_ID);

    ComponentType<TokenType> TOKEN_TYPE = plugin.register(
            "token_type",
            TokenType.CODEC,
            TokenType.PACKET_CODEC
    );

    ComponentType<CardPack> CARD_PACK = plugin.register(
            "card_pack",
            CardPack.CODEC,
            CardPack.PACKET_CODEC
    );

    ComponentType<ItemStack> HELD_CHIP = plugin.register(
            "held_chip",
            ItemStack.CODEC,
            ItemStack.PACKET_CODEC
    );

    static void init() {}
}
