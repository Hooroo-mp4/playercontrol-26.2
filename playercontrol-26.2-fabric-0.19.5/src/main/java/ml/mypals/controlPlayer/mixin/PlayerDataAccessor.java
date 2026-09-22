package ml.mypals.controlPlayer.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Player.class)
public interface PlayerDataAccessor {
    @Invoker("addAdditionalSaveData")
    void invokeAddAdditionalSaveData(ValueOutput output);

    @Invoker("readAdditionalSaveData")
    void invokeReadAdditionalSaveData(ValueInput input);
}
