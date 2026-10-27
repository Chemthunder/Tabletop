package gay.chemthunder.tabletop.api.token;

import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public class TokenBuilder {
    private final List<TokenModifier> modifiers;
    private final List<Text> description;

    private TokenCategory category = TokenCategory.MINION;
    private String name = "";
    private int cost = 0;

    public TokenBuilder() {
        this.modifiers = new ArrayList<>();
        this.description = new ArrayList<>();
    }

    public TokenBuilder modifier(TokenModifier modifier) {
        this.modifiers.add(modifier);
        return this;
    }

    public TokenBuilder costs(int cost) {
        this.cost = cost;
        return this;
    }

    public TokenBuilder line(String text) {
        this.description.add(Text.literal(text));
        return this;
    }

    public TokenBuilder category(TokenCategory category) {
        this.category = category;
        return this;
    }

    public TokenBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TokenType build() {
        return new TokenType(
                this.name,
                this.cost,
                this.category,
                this.description,
                this.modifiers
        );
    }
}
