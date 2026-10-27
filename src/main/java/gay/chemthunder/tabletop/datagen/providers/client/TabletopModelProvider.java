package gay.chemthunder.tabletop.datagen.providers.client;

import gay.chemthunder.tabletop.api.CardPack;
import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.client.item.PackProperty;
import gay.chemthunder.tabletop.core.client.item.TokenProperty;
import gay.chemthunder.tabletop.core.index.TabletopItems;
import gay.chemthunder.tabletop.core.index.TabletopRegistries;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.SelectItemModel;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public class TabletopModelProvider extends FabricModelProvider {
    public TabletopModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        uploadToken(itemModelGenerator);
        uploadPack(itemModelGenerator);
    }

    private void uploadToken(ItemModelGenerator generator) {
        Item item = TabletopItems.TOKEN;
        Identifier id = ModelIds.getItemModelId(item);

        List<SelectItemModel.SwitchCase<TokenType>> inputModels = new ArrayList<>();

        for (TokenType type : TabletopRegistries.TOKEN_TYPE) {
            Identifier tokenId = id.withPath(s -> s + "_" + type.name().toLowerCase());

            inputModels.add(
                    ItemModels.switchCase(
                            type,
                            ItemModels.basic(
                                    Models.GENERATED.upload(
                                            tokenId,
                                            TextureMap.layer0(tokenId),
                                            generator.modelCollector
                                    )
                            )
                    )
            );
        }

        generator.output.accept(item,
                ItemModels.select(
                        new TokenProperty(),
                        inputModels
                )
        );
    }

    private void uploadPack(ItemModelGenerator generator) {
        Item item = TabletopItems.CARD_PACK;
        Identifier id = ModelIds.getItemModelId(item);

        List<SelectItemModel.SwitchCase<CardPack>> inputModels = new ArrayList<>();

        for (CardPack type : TabletopRegistries.PACK) {
            Identifier suffixedId = id.withPath(s -> s + "_" + type.name().toLowerCase());

            inputModels.add(
                    ItemModels.switchCase(
                            type,
                            ItemModels.basic(
                                    Models.GENERATED.upload(
                                            suffixedId,
                                            TextureMap.layer0(suffixedId),
                                            generator.modelCollector
                                    )
                            )
                    )
            );
        }

        generator.output.accept(item,
                ItemModels.select(
                        new PackProperty(),
                        inputModels
                )
        );
    }
}
