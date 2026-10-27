package gay.chemthunder.tabletop.datagen.providers.server;

import gay.chemthunder.tabletop.core.Tabletop;
import gay.chemthunder.tabletop.core.index.TabletopItems;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class TabletopAdvancementProvider extends FabricAdvancementProvider {
    public TabletopAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry root = Advancement.Builder.createUntelemetered()
                .display(
                        TabletopItems.TOKEN.getDefaultStack(),
                        Text.literal(MiscUtils.formatString(Tabletop.MOD_ID)),
                        Text.literal("Several fanciful utilities for your favorite games!"),
                        Tabletop.id("gui/advancements/backgrounds/tabletop"),
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("tick")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("tick", TickCriterion.Conditions.createTick())
                .build(Tabletop.id("root"));

        consumer.accept(root);
    }

    private AdvancementEntry offerBasic(
            AdvancementEntry root,
            String title,
            String desc,
            ItemStack disp,
            AdvancementCriterion<?> criterion
    ) {
        return Advancement.Builder.createUntelemetered()
                .parent(root)
                .display(
                        disp,
                        Text.literal(title),
                        Text.literal(desc),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("criterion")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("criterion", criterion)
                .build(Tabletop.id(title));
    }
}
