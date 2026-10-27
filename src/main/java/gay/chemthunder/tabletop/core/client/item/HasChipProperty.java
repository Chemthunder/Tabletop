package gay.chemthunder.tabletop.core.client.item;

import com.mojang.serialization.MapCodec;
import gay.chemthunder.tabletop.core.Tabletop;
import gay.chemthunder.tabletop.core.index.TabletopComponentTypes;
import net.minecraft.client.render.item.property.bool.BooleanProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
public record HasChipProperty() implements BooleanProperty {
    public static final MapCodec<HasChipProperty> CODEC = MapCodec.unit(new HasChipProperty());
    public static final Identifier ID = Tabletop.id("has_chip");

    public MapCodec<? extends BooleanProperty> getCodec() {
        return CODEC;
    }

    public boolean test(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity entity, int seed, ItemDisplayContext displayContext) {
        if (stack.contains(TabletopComponentTypes.HELD_CHIP)) {
            ItemStack heldChip = stack.get(TabletopComponentTypes.HELD_CHIP);

            if (heldChip != null) {
                return true;
            }
        }
        return false;
    }
}
