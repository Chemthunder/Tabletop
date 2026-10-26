package gay.chemthunder.tabletop.api;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.index.TabletopRegistries;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.List;

/**
 * @author Chemthunder
 */
public record CardPack(String name, List<TokenType> possibleTypes) {
    public static final Codec<CardPack> CODEC = RecordCodecBuilder.create(codec -> codec.group(
            Codec.STRING
                    .fieldOf("name")
                    .forGetter(CardPack::name),
            TabletopRegistries.TOKEN_TYPE.getCodec()
                    .listOf()
                    .fieldOf("possibilities")
                    .forGetter(CardPack::possibleTypes)
    ).apply(codec, CardPack::new));

    public static final PacketCodec<ByteBuf, CardPack> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
