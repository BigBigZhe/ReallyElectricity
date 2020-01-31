package com.ci.network;

import com.ci.tileentity.TileEntityBandaCore;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageMain implements IMessage {

    public BlockPos pos;
    public int add;

    public MessageMain(){}

    public MessageMain(BlockPos pos, int add){
        this.pos = pos;
        this.add = add;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        add = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(add);
    }

    public static class Handle implements IMessageHandler<MessageMain, IMessage> {
        @Override
        public IMessage onMessage(MessageMain message, MessageContext ctx) {

            if (ctx.side == Side.SERVER) {
                final EntityPlayerMP player = ctx.getServerHandler().player;
                if (player == null) {
                    System.err.println("EntityPlayerMP was null when AirstrikeMessageToServer was received");
                    return null;
                }

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

        private void processMessage(MessageMain message, EntityPlayerMP player) {
            TileEntity tileEntity = player.world.getTileEntity(message.pos);
            TileEntityBandaCore tile = (TileEntityBandaCore)tileEntity;
            tile.addCount(message.add);
            tile.removeStackFromSlot(3);
        }
    }

}
