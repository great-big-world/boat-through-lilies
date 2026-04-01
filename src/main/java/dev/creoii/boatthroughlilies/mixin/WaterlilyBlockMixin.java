package dev.creoii.boatthroughlilies.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterlilyBlock.class)
public abstract class WaterlilyBlockMixin extends VegetationBlock {
    protected WaterlilyBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (collisionContext instanceof EntityCollisionContext entityCollisionContext && entityCollisionContext.getEntity() != null && entityCollisionContext.getEntity().getType().is(EntityTypeTags.BOAT)) {
            return Shapes.empty();
        }
        return super.getCollisionShape(blockState, blockGetter, blockPos, collisionContext);
    }
}
