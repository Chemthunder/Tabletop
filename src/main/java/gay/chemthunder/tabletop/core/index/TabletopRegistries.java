package gay.chemthunder.tabletop.core.index;

import gay.chemthunder.tabletop.api.CardPack;
import gay.chemthunder.tabletop.api.token.TokenType;
import gay.chemthunder.tabletop.core.Tabletop;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.stat.Stats;

/**
 * @author Chemthunder
 */
public interface TabletopRegistries {
    RegistryKey<Registry<TokenType>> TYPE_KEY = RegistryKey.ofRegistry(Tabletop.id("token_type"));
    Registry<TokenType> TOKEN_TYPE = FabricRegistryBuilder.createSimple(TYPE_KEY)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    RegistryKey<Registry<CardPack>> PACK_KEY = RegistryKey.ofRegistry(Tabletop.id("card_pack"));
    Registry<CardPack> PACK = FabricRegistryBuilder.createSimple(PACK_KEY)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    static void init() {}
}