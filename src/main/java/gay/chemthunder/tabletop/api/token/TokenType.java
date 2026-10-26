package gay.chemthunder.tabletop.api.token;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;

import java.util.List;

/**
 * @author Chemthunder
 */
public record TokenType(String name, int cost, TokenCategory category, List<Text> lines) {
    public static final Codec<TokenType> CODEC = RecordCodecBuilder.create(codec -> codec.group(
            Codec.STRING
                    .fieldOf("name")
                    .forGetter(TokenType::name),
            Codec.INT
                    .fieldOf("cost")
                    .forGetter(TokenType::cost),
            TokenCategory.CODEC
                    .fieldOf("category")
                    .forGetter(TokenType::category),
            TextCodecs.CODEC
                    .listOf()
                    .fieldOf("lines")
                    .forGetter(TokenType::lines)
    ).apply(codec, TokenType::new));

    public static final PacketCodec<ByteBuf, TokenType> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
