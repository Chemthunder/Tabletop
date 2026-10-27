package gay.chemthunder.tabletop.core.index.token;

import gay.chemthunder.tabletop.api.token.TokenModifier;
import gay.chemthunder.tabletop.core.Tabletop;
import gay.chemthunder.tabletop.core.index.TabletopRegistries;
import net.minecraft.registry.Registry;

/**
 * @author Chemthunder
 */
public interface TokenModifiers {
    TokenModifier HAS_ABILITY = register("ability");

    static void init() {}

    private static TokenModifier register(String name) {
        return Registry.register(
                TabletopRegistries.MODIFIER,
                Tabletop.id(name),
                new TokenModifier(name)
        );
    }
}
