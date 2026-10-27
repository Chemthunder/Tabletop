package gay.chemthunder.tabletop.api.token;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

/**
 * @author Chemthunder
 */
public record TokenModifier(String name) {
    public static final Codec<TokenModifier> CODEC = RecordCodecBuilder.create(codec -> codec.group(
            Codec.STRING
                    .fieldOf("name")
                    .forGetter(TokenModifier::name)
    ).apply(codec, TokenModifier::new));
}
