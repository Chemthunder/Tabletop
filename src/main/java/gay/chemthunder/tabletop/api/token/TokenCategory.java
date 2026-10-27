package gay.chemthunder.tabletop.api.token;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;

/**
 * @author Chemthunder
 */
public enum TokenCategory implements StringIdentifiable {
    MINION("minion", "⌛", 0xFFffff24),
    FIELD("field", "⭐", 0xFF9524ff),
    CHIP("chip", "\uD83D\uDD27", 0xFF32a852);

    public final String id;
    public final String icon;
    public final int color;

    public static final Codec<TokenCategory> CODEC = StringIdentifiable.createCodec(TokenCategory::values);

    TokenCategory(String id, String icon, int color) {
        this.id = id;
        this.icon = icon;
        this.color = color;
    }

    public String asString() {
        return id;
    }
}
