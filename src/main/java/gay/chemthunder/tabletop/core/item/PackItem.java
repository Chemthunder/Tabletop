package gay.chemthunder.tabletop.core.item;

import gay.chemthunder.tabletop.core.index.TabletopItems;
import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class PackItem extends Item {
    public PackItem(Settings settings) {
        super(settings);
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            if (stack.isOf(TabletopItems.CARD_PACK)) {

            }
        }
    }
}
