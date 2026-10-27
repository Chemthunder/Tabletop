package gay.chemthunder.tabletop.core.index;

import gay.chemthunder.tabletop.api.CardPack;
import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.Tabletop;
import net.minecraft.registry.Registry;

import java.util.Arrays;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface TabletopCardPacks {
    CardPack CREEPING = register("creeping", new TokenType[]{
            TabletopTokenTypes.CREEPER,
            TabletopTokenTypes.ZOMBIE,
            TabletopTokenTypes.SKELETON
    });

    CardPack VILLAGE = register("village", new TokenType[]{
            TabletopTokenTypes.PIG,
            TabletopTokenTypes.RABBIT
    });

    static void init() {}

    private static CardPack register(String name, TokenType[] possibilities) {
        return Registry.register(
                TabletopRegistries.PACK,
                Tabletop.id(name),
                new CardPack(
                        name,
                        Arrays.stream(possibilities).toList()
                )
        );
    }
}
