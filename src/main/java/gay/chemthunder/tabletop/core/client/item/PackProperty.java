package gay.chemthunder.tabletop.core.client.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import gay.chemthunder.tabletop.api.CardPack;
import gay.chemthunder.tabletop.core.Tabletop;
import gay.chemthunder.tabletop.core.index.TabletopComponentTypes;
import gay.chemthunder.tabletop.core.index.TabletopRegistries;
import net.minecraft.client.render.item.property.select.SelectProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
public record PackProperty() implements SelectProperty<CardPack> {
    public static final Identifier ID = Tabletop.id("pack");

    public static final Type<PackProperty, CardPack> TYPE = Type.create(
            MapCodec.unit(PackProperty::new),
            TabletopRegistries.PACK.getCodec()
    );

    @Nullable
    public CardPack getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ItemDisplayContext displayContext) {
        if (stack.contains(TabletopComponentTypes.CARD_PACK)) {
            return stack.get(TabletopComponentTypes.CARD_PACK);
        }
        return null;
    }

    public Codec<CardPack> valueCodec() {
        return TabletopRegistries.PACK.getCodec();
    }

    public Type<? extends SelectProperty<CardPack>, CardPack> getType() {
        return TYPE;
    }
}
