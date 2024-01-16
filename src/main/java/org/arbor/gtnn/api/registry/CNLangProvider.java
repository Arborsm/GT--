package org.arbor.gtnn.api.registry;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.providers.RegistrateProvider;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.LogicalSide;
import org.arbor.gtnn.data.GTNNDataGen;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class CNLangProvider extends LanguageProvider implements RegistrateProvider {
    private final AbstractRegistrate<?> owner;
    public CNLangProvider(AbstractRegistrate<?> owner, PackOutput packOutput) {
        super(packOutput, owner.getModid(), "zh_cn");
        this.owner = owner;
    }

    @Override
    public @NotNull LogicalSide getSide() {
        return LogicalSide.CLIENT;
    }

    @Override
    public @NotNull String getName() {
        return "Lang (zh_cn)";
    }

    @Override
    protected void addTranslations() {
        owner.genData(GTNNDataGen.CN_LANG, this);
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
        return CompletableFuture.allOf(super.run(cache));
    }
}
