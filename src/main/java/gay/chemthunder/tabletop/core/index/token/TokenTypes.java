package gay.chemthunder.tabletop.core.index.token;

import gay.chemthunder.tabletop.api.token.TokenBuilder;
import gay.chemthunder.tabletop.api.token.TokenCategory;
import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.Tabletop;
import gay.chemthunder.tabletop.core.index.TabletopRegistries;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.registry.Registry;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface TokenTypes {
    TokenType BLANK = register(builder()
            .name("blank")
            .costs(0)
    );

    TokenType CREEPER = register(builder()
            .name("creeper")
            .costs(1)
            .line("This Token can explode at any time, destroying all Minions")
            .line("in a large range, including this Token.")
    );

    TokenType ZOMBIE = register(builder()
            .name("zombie")
            .costs(1)
            .line("This Token can only move forward to one of the three")
            .line("tiles in front of it, and can only attack in the")
            .line("lateral directions.")
    );

    TokenType PIG = register(builder()
            .name("pig")
            .costs(1)
            .line("This Token cannot attack. However, upon playing this Token,")
            .line("The player may draw one Token from their Deck. The player may")
            .line("draw again when this Token is killed.")
    );

    TokenType SKELETON = register(builder()
            .name("skeleton")
            .costs(3)
            .line("Can attack in diagonal directions up to")
            .line("three tiles, yet is restricted to lateral")
            .line("movement.")
    );

    TokenType RABBIT = register(builder()
            .name("rabbit")
            .costs(2)
            .line("This Token cannot attack. However, it can leap")
            .line("across surrounding tiles, drawing one Token for each")
            .line("Token they leap over.")
    );

    TokenType PUFFERFISH = register(builder()
            .name("pufferfish")
            .costs(2)
            .line("Although this Token can only move in lateral directions,")
            .line("it can attack in all four diagonal tiles at once.")
    );

    TokenType IRON_GOLEM = register(builder()
            .name("iron_golem")
            .costs(2)
            .line("This Token can move in all directions, and attack")
            .line("in the three tiles corresponding to a direction.")
    );

    TokenType FROG = register(builder()
            .name("frog")
            .costs(2)
            .modifier(TokenModifiers.HAS_ABILITY)
    );

    TokenType KING = register(builder()
            .name("the_king")
            .costs(0)
            .line("Played at the start of the game.")
            .line("Represents you, the player, and will lose you the game")
            .line("when killed.")
    );

    TokenType TOTEM = register(builder()
            .name("totem")
            .costs(0)
            .category(TokenCategory.CHIP)
    );

    TokenType FIELD_PLAINS = register(builder()
            .name("plains")
            .costs(6)
            .category(TokenCategory.FIELD)
    );

    static void init() {}

    private static TokenType register(TokenBuilder builder) {
        TokenType built = builder.build();
        return Registry.register(
                TabletopRegistries.TOKEN_TYPE,
                Tabletop.id(built.name()),
                built
        );
    }

    private static TokenBuilder builder() {
        return new TokenBuilder();
    }
}
