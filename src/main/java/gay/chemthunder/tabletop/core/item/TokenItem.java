package gay.chemthunder.tabletop.core.item;

import gay.chemthunder.tabletop.api.token.TokenCategory;
import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.client.screen.TokenScreen;
import gay.chemthunder.tabletop.core.index.TabletopComponentTypes;
import gay.chemthunder.tabletop.core.index.token.TokenTypes;
import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class TokenItem extends Item {
    public TokenItem(Settings settings) {
        super(settings);
    }

    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            if (stack.contains(TabletopComponentTypes.TOKEN_TYPE)) {
                TokenType type = stack.get(TabletopComponentTypes.TOKEN_TYPE);

                if (MinecraftClient.getInstance().isAltPressed()) {
                    if (otherStack.isEmpty()) { // lead into wiki
                        if (type != TokenTypes.BLANK) {
                            if (player.getEntityWorld().isClient()) {
                                player.playSound(SoundEvents.ITEM_BOOK_PAGE_TURN);
                                MinecraftClient.getInstance().setScreen(new TokenScreen(stack));
                            }
                            return true;
                        }
                    }
                } else {
                    if (!otherStack.isEmpty()) {
                        if (otherStack.contains(TabletopComponentTypes.TOKEN_TYPE)) {
                            TokenType otherType = otherStack.get(TabletopComponentTypes.TOKEN_TYPE);

                            if (otherType != null) {
                                if (otherType.category().equals(TokenCategory.CHIP)) {
                                    ItemStack split = otherStack.split(1);
                                    stack.set(TabletopComponentTypes.HELD_CHIP, split);
                                    return true;
                                }
                            }
                        }
                    } else {
                        if (stack.contains(TabletopComponentTypes.HELD_CHIP)) {
                            ItemStack chipStack = stack.get(TabletopComponentTypes.HELD_CHIP);

                            if (chipStack != null) {
                                cursorStackReference.set(chipStack);
                                stack.remove(TabletopComponentTypes.HELD_CHIP);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public Text getName(ItemStack stack) {
        if (stack.contains(TabletopComponentTypes.TOKEN_TYPE)) {
            TokenType type = stack.get(TabletopComponentTypes.TOKEN_TYPE);

            if (type != null) {
                return Text.literal("Token").withColor(type.category().color);
            }
        }
        return Text.literal("Token").formatted(Formatting.YELLOW);
    }

    public ItemStack getDefaultStack() {
        ItemStack original = super.getDefaultStack();
        original.set(TabletopComponentTypes.TOKEN_TYPE, TokenTypes.BLANK);
        return original;
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            if (stack.contains(TabletopComponentTypes.TOKEN_TYPE)) {
                TokenType type = stack.get(TabletopComponentTypes.TOKEN_TYPE);

                if (type != null) {
                    lines.accept(Text.empty()
                            .append(Text.literal(type.category().icon + " ").withColor(type.category().color))
                            .append(Text.literal(MiscUtils.formatString(type.name())).formatted(Formatting.DARK_GRAY))
                    );
                }
            }
        }
    }
}
