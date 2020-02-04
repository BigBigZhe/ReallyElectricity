package com.ci.network;

import com.ci.tileentity.TileEntityElectronicProcessing;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageLink implements IMessage {

    public BlockPos pos;
    public int meta;

    public MessageLink(){}

    public MessageLink(BlockPos pos, int meta){
        this.pos = pos;
        this.meta = meta;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        meta = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(meta);
    }

    public static class Handle implements IMessageHandler<MessageLink, IMessage> {
        @Override
        public IMessage onMessage(MessageLink message, MessageContext ctx) {

            if (ctx.side == Side.SERVER) {
                final EntityPlayerMP player = ctx.getServerHandler().player;
                if (player == null) { return null; }

                final WorldServer playerWorldServer = player.getServerWorld();
                playerWorldServer.addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        processMessage(message, player);
                    }
                });
            }
            return null;
        }

        private void processMessage(MessageLink message, EntityPlayerMP player) {
            TileEntity tileEntity = player.world.getTileEntity(message.pos);
            TileEntityElectronicProcessing tile = (TileEntityElectronicProcessing)tileEntity;
            ItemStack itemStack = tile.getStackInSlot(0);
            itemStack.setItemDamage(message.meta);
        }
    }
}
