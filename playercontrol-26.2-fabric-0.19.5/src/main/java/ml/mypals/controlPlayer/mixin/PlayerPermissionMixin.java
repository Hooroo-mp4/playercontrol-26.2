package ml.mypals.controlPlayer.mixin;

import com.mojang.authlib.GameProfile;
import ml.mypals.controlPlayer.PlayerControl;
import ml.mypals.controlPlayer.core.ModGameRules;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.players.NameAndId;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public abstract class PlayerPermissionMixin {
    /**
     * Prevents the permission lookup used for the swap partner from re-entering
     * this mixin. The nested call is allowed to reach vanilla's original
     * getProfilePermissions implementation.
     */
    private static final ThreadLocal<Boolean> PLAYERCONTROL_RESOLVING =
            ThreadLocal.withInitial(() -> false);

    @Shadow public abstract PlayerList getPlayerList();
    @Shadow public abstract GameRules getGameRules();

    @Inject(method = "getProfilePermissions", at = @At("HEAD"), cancellable = true)
    private void playercontrol$getProfilePermissions(NameAndId nameAndId, CallbackInfoReturnable<LevelBasedPermissionSet> cir) {
        if (PLAYERCONTROL_RESOLVING.get()) return;
        if (this.getGameRules().get(ModGameRules.PERMISSION_SWAP)) return;

        if (PlayerControl.getSwapManager().isSwapped(nameAndId.id())) {
            PlayerControl.getSwapManager().getSwapPartner(nameAndId.id()).ifPresent(partnerId -> {
                ServerPlayer partner = this.getPlayerList().getPlayer(partnerId);
                if (partner != null) {
                    GameProfile profile = partner.getGameProfile();
                    MinecraftServer server = (MinecraftServer) (Object) this;
                    PLAYERCONTROL_RESOLVING.set(true);
                    try {
                        cir.setReturnValue(server.getProfilePermissions(
                                new NameAndId(profile.id(), profile.name())
                        ));
                    } finally {
                        PLAYERCONTROL_RESOLVING.set(false);
                    }
                }
            });
        }
    }
}
