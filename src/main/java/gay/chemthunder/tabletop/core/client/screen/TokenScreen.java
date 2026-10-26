package gay.chemthunder.tabletop.core.client.screen;

import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.Tabletop;
import gay.chemthunder.tabletop.core.index.TabletopComponentTypes;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.attribute.timeline.EasingType;
import org.joml.Matrix3x2fStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Chemthunder
 */
public class TokenScreen extends Screen {
    private final List<Function<DrawContext, Drawable>> drawableFunctions = new ArrayList<>();

    private final MinecraftClient client;
    private final ItemStack stack;

    private float scale = 0.0F;

    private int ticksActive = 0;
    private int maxPages = 3;
    private int page = 0;

    public TokenScreen(ItemStack stack) {
        super(Text.empty());
        this.stack = stack;
        this.client = MinecraftClient.getInstance();
    }

    protected void init() {
        if (stack.contains(TabletopComponentTypes.TOKEN_TYPE)) {
            TokenType type = stack.get(TabletopComponentTypes.TOKEN_TYPE);

            if (type != null) {
                // TODO: adjust max pages on initialize
                maxPages = 2;
            }
        }

        ButtonWidget cycle = ButtonWidget.builder(
                Text.literal("Next"),
                button -> this.clickCycleButton()
        ).build();

        cycle.setDimensions(48, 16);
        cycle.setPosition((width / 2 - (48 / 2)) + 30, height - 40);
        drawableFunctions.add(context -> cycle);
        this.addDrawableChild(cycle);

        ButtonWidget cycleBack = ButtonWidget.builder(
                Text.literal("Back"),
                button -> this.clickCycleBackButton()
        ).build();

        cycleBack.setDimensions(48, 16);
        cycleBack.setPosition((width / 2 - (48 / 2)) - 30, height - 40);
        drawableFunctions.add(context -> cycleBack);
        this.addDrawableChild(cycleBack);
    }

    public void tick() {
        this.ticksActive++;

        if (this.scale < 1.0F) {
            this.scale += 0.05F;
        }
    }

    private void clickCycleButton() {
        if (this.page < maxPages - 1) {
            this.page++;
        } else {
            this.page = 0;
        }
    }

    private void clickCycleBackButton() {
        if (this.page > 0) {
            this.page--;
        } else {
            this.page = maxPages - 1;
        }
    }

    private void drawPageOne(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        Matrix3x2fStack matrices = context.getMatrices();

        context.fill(
                context.getScaledWindowWidth() / 2 - 50,
                0,
                context.getScaledWindowWidth() / 2 + 50,
                context.getScaledWindowHeight(),
                ColorHelper.withAlpha(0.4F, 0xFF000000)
        );

        matrices.pushMatrix();

        matrices.translate(
                context.getScaledWindowWidth() / 2F,
                context.getScaledWindowHeight() / 2F
        );

        matrices.scale(EasingType.OUT_EXPO.apply(scale) * 4);

        matrices.translate(-8, -20);

        context.drawItem(
                stack,
                0, 0
        );

        matrices.popMatrix();

        if (stack.contains(TabletopComponentTypes.TOKEN_TYPE)) {
            TokenType type = stack.get(TabletopComponentTypes.TOKEN_TYPE);

            if (type != null) {
                List<Text> tooltip = new ArrayList<>();

                tooltip.add(Text.empty()
                        .append(Text.literal("Costs ").formatted(Formatting.DARK_GRAY))
                        .append(Text.literal(type.cost() + "").withColor(type.category().color))
                        .append(Text.literal(" to play.").formatted(Formatting.DARK_GRAY))
                );

                tooltip.addAll(type.lines());

                matrices.pushMatrix();

                for (Text line : tooltip) {
                    int index = tooltip.indexOf(line);

                    context.drawCenteredTextWithShadow(
                            client.textRenderer,
                            line,
                            width / 2,
                            height / 2 + (index * 13),
                            0xFFffffff
                    );
                }

                matrices.popMatrix();

                float strength = 1.12F;

                float smoothX = 0;
                float smoothY = 0;

                float cachedX = (float) mouseX / width * 2.0F;
                float cachedY = (float) mouseY / height * 2.0F;

                smoothX += (cachedX - smoothX) * strength;
                smoothY += (cachedY - smoothY) * strength;

                matrices.pushMatrix();
                matrices.translate(-smoothX * 2.055F, smoothY * 2.035F);

                context.drawCenteredTextWithShadow(
                        client.textRenderer,
                        Text.literal(MiscUtils.formatString(type.name())).withColor(type.category().color),
                        width / 2,
                        30,
                        0xFFffffff
                );

                matrices.popMatrix();
            }
        }
    }

    private void drawPageTwo(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        if (stack.contains(TabletopComponentTypes.TOKEN_TYPE)) {
            TokenType type = stack.get(TabletopComponentTypes.TOKEN_TYPE);

            if (type != null) {
                int spacing = 170;
                int beamWidth = 45;

                context.fill(
                        spacing - (112 - beamWidth),
                        0,
                        spacing + (112 - beamWidth),
                        height,
                        ColorHelper.withAlpha(0.4F, 0xFF000000)
                );

                context.fill(
                        (width - spacing) - (112 - beamWidth),
                        0,
                        (width - spacing) + (112 - beamWidth),
                        height,
                        ColorHelper.withAlpha(0.4F, 0xFF000000)
                );


                context.drawGuiTexture(
                        RenderPipelines.GUI_TEXTURED,
                        Tabletop.id("empty_board"),
                        spacing - (112 / 2),
                        height / 2 - (112 / 2),
                        112,
                        112
                );

                context.drawGuiTexture(
                        RenderPipelines.GUI_TEXTURED,
                        Tabletop.id("empty_board"),
                        (width - spacing) - (112 / 2),
                        height / 2 - (112 / 2),
                        112,
                        112
                );

                context.drawGuiTexture(
                        RenderPipelines.GUI_TEXTURED,
                        Tabletop.id("token/" + type.name() + "_attack"),
                        spacing - (112 / 2),
                        height / 2 - (112 / 2),
                        112,
                        112
                );

                context.drawGuiTexture(
                        RenderPipelines.GUI_TEXTURED,
                        Tabletop.id("token/" + type.name() + "_movement"),
                        (width - spacing) - (112 / 2),
                        height / 2 - (112 / 2),
                        112,
                        112
                );

                context.drawCenteredTextWithShadow(
                        client.textRenderer,
                        Text.literal("Attack Range"),
                        spacing,
                        (height / 2 - (112 / 2)) - 20,
                        0xFFffffff
                );

                context.drawCenteredTextWithShadow(
                        client.textRenderer,
                        Text.literal("Movement Range"),
                        width - spacing,
                        (height / 2 - (112 / 2)) - 20,
                        0xFFffffff
                );
            }
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        switch (page) {
            case 0: {
                drawPageOne(context, mouseX, mouseY, deltaTicks);
                break;
            }
            case 1: {
                drawPageTwo(context, mouseX, mouseY, deltaTicks);
                break;
            }
        }

        drawableFunctions.forEach(func ->
                func.apply(context).render(
                        context,
                        mouseX,
                        mouseY,
                        deltaTicks
                )
        );
    }
}
