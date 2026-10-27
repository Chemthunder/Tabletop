package gay.chemthunder.tabletop.core.index;

import gay.chemthunder.tabletop.core.Tabletop;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatType;
import net.minecraft.text.Text;

/**
 * @author Chemthunder
 */
public interface TabletopStatTypes {
    StatType<Item> PACKS_OPENED = Registry.register(
            Registries.STAT_TYPE,
            Tabletop.id("packs_opened"),
            new StatType<>(Registries.ITEM, Text.literal("Packs Opened"))
    );

    static void init() {}
}
