package gay.chemthunder.tabletop.core.index;

import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.Tabletop;
import gay.chemthunder.tabletop.core.item.PackItem;
import gay.chemthunder.tabletop.core.item.TokenItem;
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;

/**
 * @author Chemthunder
 */
public interface TabletopItems {
    ItemRegistrant plugin = new ItemRegistrant(Tabletop.MOD_ID);

    Item TOKEN = plugin.register("token", TokenItem::new, new Item.Settings()
            .component(TabletopComponentTypes.TOKEN_TYPE, TabletopTokenTypes.BLANK)
    );

    Item CARD_PACK = plugin.register("card_pack", PackItem::new, new Item.Settings()
            .maxCount(16)
    );

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            for (TokenType type : TabletopRegistries.TOKEN_TYPE) {
                ItemStack stack = new ItemStack(TOKEN);
                stack.set(TabletopComponentTypes.TOKEN_TYPE, type);
                entries.add(stack);
            }
        });
    }
}
