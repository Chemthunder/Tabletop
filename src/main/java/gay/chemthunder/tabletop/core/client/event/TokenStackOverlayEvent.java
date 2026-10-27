package gay.chemthunder.tabletop.core.client.event;

import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.index.TabletopComponentTypes;
import net.fabricmc.fabric.api.client.rendering.v1.DrawItemStackOverlayCallback;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

/**
 * @author Chemthunder
 */
public class TokenStackOverlayEvent implements DrawItemStackOverlayCallback {
    public void onDrawItemStackOverlay(DrawContext context, TextRenderer textRenderer, ItemStack stack, int x, int y) {
        if (stack.contains(TabletopComponentTypes.HELD_CHIP)) {
            ItemStack chip = stack.get(TabletopComponentTypes.HELD_CHIP);

            if (chip != null) {
                if (chip.contains(TabletopComponentTypes.TOKEN_TYPE)) {
                    TokenType type = chip.get(TabletopComponentTypes.TOKEN_TYPE);

                    if (type != null) {
                        context.drawGuiTexture(
                                RenderPipelines.GUI_TEXTURED,
                                Identifier.of(
                                        chip.getCreatorNamespace(),
                                        "token/overlay/" + type.name() + "_overlay"
                                ),
                                x,
                                y,
                                16,
                                16
                        );
                    }
                }
            }
        }
    }
}
