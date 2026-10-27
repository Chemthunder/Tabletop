package gay.chemthunder.tabletop.core.index;

import gay.chemthunder.tabletop.api.token.TokenCategory;
import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.Tabletop;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Collections;
import java.util.List;

import static net.minecraft.text.Text.literal;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface TabletopTokenTypes {
    TokenType BLANK = registerMinion("blank", 0, Collections.emptyList());

    TokenType CREEPER = registerMinion(
            "creeper",
            1,
            List.of(
                    literal("This Token can explode at any time, destroying all Minions"),
                    literal("in a large range, including this Token.")
            )
    );

    TokenType ZOMBIE = registerMinion(
            "zombie",
            1,
            List.of(
                    literal("This Token can only move forward to one of the three"),
                    literal("tiles in front of it, and can only attack in the"),
                    literal("lateral directions.")
            )
    );

    TokenType PIG = registerMinion(
            "pig",
            1,
            List.of(
                    literal("This Token cannot attack. However, upon playing this Token,"),
                    literal("The player may draw one Token from their Deck. The player may"),
                    literal("draw again when this Token is killed.")
            )
    );

    TokenType SKELETON = registerMinion(
            "skeleton",
            3,
            List.of(
                    literal("Can attack in diagonal directions up to"),
                    literal("three tiles, yet is restricted to lateral"),
                    literal("movement.")
            )
    );

    TokenType RABBIT = registerMinion(
            "rabbit",
            2,
            List.of(
                    literal("This Token cannot attack. However, it can leap"),
                    literal("across surrounding tiles, drawing one Token for each"),
                    literal("Token they leap over.")
            )
    );

    TokenType PUFFERFISH = registerMinion(
            "pufferfish",
            2,
            List.of(
                    literal("Although this Token can only move in lateral directions,"),
                    literal("it can attack in all four diagonal tiles at once.")
            )
    );

    TokenType IRON_GOLEM = registerMinion(
            "iron_golem",
            2,
            List.of(
                    literal("This Token can move in all directions, and attack"),
                    literal("in the three tiles corresponding to a direction.")
            )
    );

    TokenType KING = registerMinion(
            "the_king",
            0,
            List.of(
                    literal("Played at the start of the game."),
                    literal("Represents you, the player, and will lose you the game"),
                    literal("when killed.")
            )
    );

    TokenType FIELD_PLAINS = registerField(
            "plains",
            6,
            List.of()
    );

    static void init() {}

    private static TokenType registerMinion(String name, int cost, List<Text> lines) {
        return Registry.register(
                TabletopRegistries.TOKEN_TYPE,
                Tabletop.id(name.toLowerCase()),
                new TokenType(
                        name,
                        cost,
                        TokenCategory.MINION,
                        lines
                )
        );
    }

    private static TokenType registerField(String name, int cost, List<Text> lines) {
        return Registry.register(
                TabletopRegistries.TOKEN_TYPE,
                Tabletop.id(name.toLowerCase()),
                new TokenType(
                        name,
                        cost,
                        TokenCategory.FIELD,
                        lines
                )
        );
    }
}
