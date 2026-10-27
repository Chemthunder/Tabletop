package gay.chemthunder.tabletop.core.item;

import gay.chemthunder.tabletop.api.CardPack;
import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.index.TabletopComponentTypes;
import gay.chemthunder.tabletop.core.index.TabletopItems;
import gay.chemthunder.tabletop.core.index.TabletopStatTypes;
import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class PackItem extends Item {
    public PackItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (stack.contains(TabletopComponentTypes.CARD_PACK)) {
            CardPack pack = stack.get(TabletopComponentTypes.CARD_PACK);

            if (pack != null) {
                for (int i = 0; i < world.random.nextBetween(6, 7); i++) {
                    ItemStack rewarded = new ItemStack(TabletopItems.TOKEN);
                    rewarded.set(
                            TabletopComponentTypes.TOKEN_TYPE,
                            pack.possibleTypes().get(world.random.nextInt(pack.possibleTypes().size()))
                    );
                    user.giveItemStack(rewarded);
                }
            }

            user.incrementStat(TabletopStatTypes.PACKS_OPENED.getOrCreateStat(TabletopItems.CARD_PACK));
            user.spawnItemParticles(stack, 6);
        }
        return super.use(world, user, hand);
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            if (stack.isOf(TabletopItems.CARD_PACK)) {
                if (stack.contains(TabletopComponentTypes.CARD_PACK)) {
                    CardPack pack = stack.get(TabletopComponentTypes.CARD_PACK);

                    if (pack != null) {
                        lines.accept(Text.literal(MiscUtils.formatString(pack.name())).formatted(Formatting.DARK_GRAY));

                        for (TokenType type : pack.possibleTypes()) {
                            lines.accept(Text.empty()
                                    .append(Text.literal("- ").formatted(Formatting.DARK_GRAY))
                                    .append(Text.literal(MiscUtils.formatString(type.name())).withColor(type.category().color))
                            );
                        }
                    }
                }
            }
        }
    }
}
