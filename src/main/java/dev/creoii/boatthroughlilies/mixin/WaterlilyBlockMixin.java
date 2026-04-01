package dev.creoii.boatthroughlilies.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(WaterlilyBlock.class)
public abstract class WaterlilyBlockMixin extends BushBlock {
    protected WaterlilyBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("deprecation")
    protected VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (collisionContext instanceof EntityCollisionContext entityCollisionContext && entityCollisionContext.getEntity() != null && isBoat(entityCollisionContext.getEntity().getType())) {
            return Shapes.empty();
        }
        return super.getCollisionShape(blockState, blockGetter, blockPos, collisionContext);
    }

    @Unique
    private static boolean isBoat(EntityType<?> entityType) {
        return entityType == EntityType.BOAT || entityType == EntityType.CHEST_BOAT;
    }
}
