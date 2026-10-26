package gay.chemthunder.tabletop.core.client.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import gay.chemthunder.tabletop.api.token.TokenType;
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
public record TokenProperty() implements SelectProperty<TokenType> {
    public static final Identifier ID = Tabletop.id("token");

    public static final Type<TokenProperty, TokenType> TYPE = Type.create(
            MapCodec.unit(TokenProperty::new),
            TabletopRegistries.TOKEN_TYPE.getCodec()
    );

    @Nullable
    public TokenType getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ItemDisplayContext displayContext) {
        if (stack.contains(TabletopComponentTypes.TOKEN_TYPE)) {
            return stack.get(TabletopComponentTypes.TOKEN_TYPE);
        }
        return null;
    }

    public Codec<TokenType> valueCodec() {
        return TabletopRegistries.TOKEN_TYPE.getCodec();
    }

    public Type<? extends SelectProperty<TokenType>, TokenType> getType() {
        return TYPE;
    }
}
