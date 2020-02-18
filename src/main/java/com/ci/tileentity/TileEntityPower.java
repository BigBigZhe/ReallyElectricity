package com.ci.tileentity;

import com.ci.api.block.DirectionThree;
import com.ci.api.other.ComponentList;
import com.ci.api.other.MultiComponent;
import com.ci.api.other.Poles;

import com.ci.blocks.wires.Wire_Copper;
import com.ci.loader.BlocksLoader;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityPower extends TileEntity implements ITickable {

    private int Electromotive_Force;
    private Poles anode = new Poles();
    private ComponentList[] componentList;
    private int componentCount;
    private BlockPos[] wirePos;
    private int wireCount;
    private int oneCount, twoCount;
    private boolean isLinkPowerOne, isLinkPowerTwo;
    private BlockPos[] shortPos;
    private MultiComponent[] multiComponent;
    private int multiCount;

    public TileEntityPower(){ reInit(); }

    public TileEntityPower(int electromotive_Force, EnumFacing enumFacing) {
        Electromotive_Force = electromotive_Force;
        anode = new Poles(enumFacing, this.pos);
        reInit();
    }

    public void setAnodeFacing(EnumFacing enumFacing){
        anode.setFacing(enumFacing);
    }

    public void setAnodePos(){
        anode.setPos(this.pos);
    }

    private void reInit(){
        multiComponent = new MultiComponent[256];
        shortPos = new BlockPos[256];
        componentList = new ComponentList[256];
        wirePos = new BlockPos[1024];
        isLinkPowerOne = false;
        isLinkPowerTwo = false;
        componentCount = 0;
        wireCount = 0;
        oneCount = 0;
        twoCount = 0;
        multiCount = 0;
        for (int i = 0; i < 256; i++){
            componentList[i] = new ComponentList();
            multiComponent[i] = new MultiComponent();
            shortPos[i] = new BlockPos(0, 0, 0);
        }
        for (int i = 0; i < 1024; i++){
            wirePos[i] = new BlockPos(0, 0, 0);
        }
    }

    private void reInitWire(){
        for (int i = 0; i < 1024; i++){
            wirePos[i] = new BlockPos(0, 0, 0);
        }
        wireCount = 0;
    }

    public int ininin(){
        if (!isLink()){ return -1; }
        findAllComponents();
        for (int i = 0; i < componentCount; i++){
            isLinkPowerOne = false;
            isLinkPowerTwo = false;
            oneCount = 0;
            twoCount = 0;
            ComponentList[] c1 = new ComponentList[256];
            ComponentList[] c2 = new ComponentList[256];
            ComponentList c = componentList[i];
            if (c == null || c.type == -1){ continue; }
            reInitWire();
            findCircuit3(c.facing, new Poles(c.facing, c.pos), c1, 1, c.pos);
            reInitWire();
            findCircuit3(c.facing.getOpposite(), new Poles(c.facing.getOpposite(), c.pos), c2, 2, c.pos);
            if (oneCount == 1 && !isLinkPowerOne){
                multiComponent[multiCount].ComPos[0][0] = c.pos.offset(c.facing.getOpposite());
                multiComponent[multiCount].ComPos[1][0] = c1[0].pos.offset(c1[0].facing);
                multiCount++;
                continue;
            }
            if (twoCount == 1 && !isLinkPowerTwo){
                multiComponent[multiCount].ComPos[0][0] = c.pos.offset(c.facing.getOpposite());
                multiComponent[multiCount].ComPos[1][0] = c2[0].pos.offset(c2[0].facing.getOpposite());
                multiCount++;
                continue;
            }
            if (oneCount >= 1 && twoCount >= 1){
                for (int j = 0; j < oneCount; j++){
                    for (int k = 0; k < twoCount; k++){
                        if (equalPos(c1[j].pos, c2[k].pos)){

                        }
                    }
                }
            }
        }
        destruction();
        return componentCount;
    }

    private boolean isLink(){
        reInit();
        Poles mirrorPoles = this.anode;
        if (findCircuit(null, mirrorPoles)){
            return false;
        }
        for (int i = 0; i < 256; i++){
            if (componentList[i] == null){break;}
            ComponentList c = componentList[i];
            if (findCircuit(c.facing, new Poles(c.facing, c.pos))) { return true; }
        }
        return false;
    }

    private void findAllComponents(){
        reInit();
        Poles mirrorPoles = this.anode;
        findCircuit2(null, mirrorPoles);
        for (int i = 0; i < 256; i++){
            if (componentList[i] == null || componentList[i].type == -1){break;}
            ComponentList c = componentList[i];
            findCircuit2(c.facing, new Poles(c.facing, c.pos));
        }
    }

    private boolean findCircuit(EnumFacing polesFacing, Poles anodeMirror){
        if (polesFacing != null){ anodeMirror.setFacing(polesFacing);}
        Block block = world.getBlockState(anodeMirror.pos.offset(anodeMirror.facing)).getBlock();
        if (block == BlocksLoader.WIRE_COPPER){
            if (wireCount >= 0){
                for (int i = 0; i < wireCount; i++){
                    BlockPos blockPos = wirePos[i];
                    if (equalPos(blockPos, anodeMirror.pos.offset(anodeMirror.facing))){
                        return false;
                    }
                }
            }
            wirePos[wireCount] = anodeMirror.pos.offset(anodeMirror.facing);
            wireCount++;
            System.out.println("Copper");
            Wire_Copper wireCopper = (Wire_Copper)block;
            IBlockState blockState = wireCopper.getActualState(wireCopper.getDefaultState(), world, anodeMirror.pos.offset(anodeMirror.facing));
            int num = blockState.getValue(Wire_Copper.LINKNUM);
            if (num == 1){
                System.out.println("num1");
                return false; }
            else if (num == 2){
                int i = 0;
                for (EnumFacing f:EnumFacing.VALUES) {
                    i++;
                    boolean b = true;
                    if (f == EnumFacing.UP){ b = blockState.getValue(Wire_Copper.UP);}if (f == EnumFacing.DOWN){ b = blockState.getValue(Wire_Copper.DOWN);}
                    if (f == EnumFacing.NORTH){ b = blockState.getValue(Wire_Copper.NORTH);}if (f == EnumFacing.SOUTH){ b = blockState.getValue(Wire_Copper.SOUTH);}
                    if (f == EnumFacing.WEST){ b = blockState.getValue(Wire_Copper.WEST);}if (f == EnumFacing.EAST){ b = blockState.getValue(Wire_Copper.EAST);}
                    if (b && anodeMirror.facing != f.getOpposite()) { System.out.println("num2" + i);
                        anodeMirror.setPos(anodeMirror.pos.offset(anodeMirror.facing));
                        if (findCircuit(f, anodeMirror)) { return true; }
                    }
                }
            }else if (num >= 3){
                int i = 0;
                for (EnumFacing f:EnumFacing.VALUES){
                    i++;
                    boolean b = true;
                    if (f == EnumFacing.UP){ b = blockState.getValue(Wire_Copper.UP);}if (f == EnumFacing.DOWN){ b = blockState.getValue(Wire_Copper.DOWN);}
                    if (f == EnumFacing.NORTH){ b = blockState.getValue(Wire_Copper.NORTH);}if (f == EnumFacing.SOUTH){ b = blockState.getValue(Wire_Copper.SOUTH);}
                    if (f == EnumFacing.WEST){ b = blockState.getValue(Wire_Copper.WEST);}if (f == EnumFacing.EAST){ b = blockState.getValue(Wire_Copper.EAST);}
                    if (b && anodeMirror.facing != f.getOpposite()){System.out.println("num3" + i);
                        Poles anodeMirrorTwo = new Poles(f, anodeMirror.pos.offset(anodeMirror.facing));
                        if (findCircuit(f, anodeMirrorTwo)){ return true; } }
                }
            }
        }else if(block == BlocksLoader.RESISTANCE10){
            DirectionThree dThree = (DirectionThree)block;
            BlockPos blockPos = anodeMirror.pos.offset(anodeMirror.facing);
            if (componentCount >= 1) {
                for (int i = 0; i < componentCount; i++) {
                    BlockPos componentPos = componentList[i].pos;
                    if (blockPos.getX() == componentPos.getX() && blockPos.getY() == componentPos.getY() && blockPos.getZ() == componentPos.getZ()) { return false; }
                }
            }
            componentList[componentCount] = new ComponentList(anodeMirror.facing, blockPos, dThree, 10);
            System.out.println("++");
            componentCount++;
        }else if (block == BlocksLoader.POWER){
            return true;
        }
        return false;
    }

    private void findCircuit2(EnumFacing polesFacing, Poles anodeMirror){
        if (polesFacing != null){ anodeMirror.setFacing(polesFacing);}
        Block block = world.getBlockState(anodeMirror.pos.offset(anodeMirror.facing)).getBlock();
        if (block == BlocksLoader.WIRE_COPPER){
            if (wireCount >= 0){
                for (int i = 0; i < wireCount; i++){
                    BlockPos blockPos = wirePos[i];
                    if (equalPos(blockPos, anodeMirror.pos.offset(anodeMirror.facing))){ return; }
                }
            }
            wirePos[wireCount] = anodeMirror.pos.offset(anodeMirror.facing);
            wireCount++;
            System.out.println("Copper");
            Wire_Copper wireCopper = (Wire_Copper)block;
            IBlockState blockState = wireCopper.getActualState(wireCopper.getDefaultState(), world, anodeMirror.pos.offset(anodeMirror.facing));
            int num = blockState.getValue(Wire_Copper.LINKNUM);
            if (num == 1){
                System.out.println("num1");
                return; }
            else if (num == 2){
                int i = 0;
                for (EnumFacing f:EnumFacing.VALUES) {
                    i++;
                    boolean b = true;
                    if (f == EnumFacing.UP){ b = blockState.getValue(Wire_Copper.UP);}if (f == EnumFacing.DOWN){ b = blockState.getValue(Wire_Copper.DOWN);}
                    if (f == EnumFacing.NORTH){ b = blockState.getValue(Wire_Copper.NORTH);}if (f == EnumFacing.SOUTH){ b = blockState.getValue(Wire_Copper.SOUTH);}
                    if (f == EnumFacing.WEST){ b = blockState.getValue(Wire_Copper.WEST);}if (f == EnumFacing.EAST){ b = blockState.getValue(Wire_Copper.EAST);}
                    if (b && anodeMirror.facing != f.getOpposite()) { System.out.println("num2" + i);
                        anodeMirror.setPos(anodeMirror.pos.offset(anodeMirror.facing));
                        findCircuit2(f, anodeMirror);
                    }
                }
            }else if (num >= 3){
                int i = 0;
                for (EnumFacing f:EnumFacing.VALUES){
                    i++;
                    boolean b = true;
                    if (f == EnumFacing.UP){ b = blockState.getValue(Wire_Copper.UP);}if (f == EnumFacing.DOWN){ b = blockState.getValue(Wire_Copper.DOWN);}
                    if (f == EnumFacing.NORTH){ b = blockState.getValue(Wire_Copper.NORTH);}if (f == EnumFacing.SOUTH){ b = blockState.getValue(Wire_Copper.SOUTH);}
                    if (f == EnumFacing.WEST){ b = blockState.getValue(Wire_Copper.WEST);}if (f == EnumFacing.EAST){ b = blockState.getValue(Wire_Copper.EAST);}
                    if (b && anodeMirror.facing != f.getOpposite()){System.out.println("num3" + i);
                        Poles anodeMirrorTwo = new Poles(f, anodeMirror.pos.offset(anodeMirror.facing));
                        findCircuit2(f, anodeMirrorTwo); }
                }
            }
        }else if(block == BlocksLoader.RESISTANCE10){
            DirectionThree dThree = (DirectionThree)block;
            BlockPos blockPos = anodeMirror.pos.offset(anodeMirror.facing);
            if (componentCount >= 1) {
                for (int i = 0; i < componentCount; i++) {
                    BlockPos componentPos = componentList[i].pos;
                    if (equalPos(blockPos, componentPos)) { return; }
                }
            }
            componentList[componentCount] = new ComponentList(anodeMirror.facing, blockPos, dThree, 10);
            System.out.println("++");
            componentCount++;
        }else if (block == BlocksLoader.POWER){
            return;
        }
    }

    private void findCircuit3(EnumFacing polesFacing, Poles anodeMirror, ComponentList[] components, int type, BlockPos ownPos){
        if (polesFacing != null){ anodeMirror.setFacing(polesFacing);}
        Block block = world.getBlockState(anodeMirror.pos.offset(anodeMirror.facing)).getBlock();
        if (block == BlocksLoader.WIRE_COPPER){
            if (wireCount >= 0){
                for (int i = 0; i < wireCount; i++){
                    BlockPos blockPos = wirePos[i];
                    if (equalPos(blockPos, anodeMirror.pos.offset(anodeMirror.facing))){ return; }
                }
            }
            wirePos[wireCount] = anodeMirror.pos.offset(anodeMirror.facing);
            wireCount++;
            Wire_Copper wireCopper = (Wire_Copper)block;
            IBlockState blockState = wireCopper.getActualState(wireCopper.getDefaultState(), world, anodeMirror.pos.offset(anodeMirror.facing));
            int num = blockState.getValue(Wire_Copper.LINKNUM);
            if (num == 1){ return; }
            else if (num == 2){
                int i = 0;
                for (EnumFacing f:EnumFacing.VALUES) {
                    i++;
                    boolean b = true;
                    if (f == EnumFacing.UP){ b = blockState.getValue(Wire_Copper.UP);}if (f == EnumFacing.DOWN){ b = blockState.getValue(Wire_Copper.DOWN);}
                    if (f == EnumFacing.NORTH){ b = blockState.getValue(Wire_Copper.NORTH);}if (f == EnumFacing.SOUTH){ b = blockState.getValue(Wire_Copper.SOUTH);}
                    if (f == EnumFacing.WEST){ b = blockState.getValue(Wire_Copper.WEST);}if (f == EnumFacing.EAST){ b = blockState.getValue(Wire_Copper.EAST);}
                    if (b && anodeMirror.facing != f.getOpposite()) {
                        anodeMirror.setPos(anodeMirror.pos.offset(anodeMirror.facing));
                        findCircuit3(f, anodeMirror, components, type, ownPos);
                    }
                }
            }else if (num >= 3){
                int i = 0;
                for (EnumFacing f:EnumFacing.VALUES){
                    i++;
                    boolean b = true;
                    if (f == EnumFacing.UP){ b = blockState.getValue(Wire_Copper.UP);}if (f == EnumFacing.DOWN){ b = blockState.getValue(Wire_Copper.DOWN);}
                    if (f == EnumFacing.NORTH){ b = blockState.getValue(Wire_Copper.NORTH);}if (f == EnumFacing.SOUTH){ b = blockState.getValue(Wire_Copper.SOUTH);}
                    if (f == EnumFacing.WEST){ b = blockState.getValue(Wire_Copper.WEST);}if (f == EnumFacing.EAST){ b = blockState.getValue(Wire_Copper.EAST);}
                    if (b && anodeMirror.facing != f.getOpposite()){
                        Poles anodeMirrorTwo = new Poles(f, anodeMirror.pos.offset(anodeMirror.facing));
                        findCircuit3(f, anodeMirrorTwo, components, type, ownPos); }
                }
            }
        }else if(block == BlocksLoader.RESISTANCE10){System.out.println("resistance");
            DirectionThree dThree = (DirectionThree)block;
            BlockPos blockPos = anodeMirror.pos.offset(anodeMirror.facing);
            if (equalPos(blockPos, ownPos)){ return; }
            int mirror = 0;
            if (type == 1) {
                mirror = oneCount;
            }else { mirror = twoCount; }
            if (mirror >= 1) {
                for (int i = 0; i < mirror; i++) {
                    BlockPos componentPos = components[i].pos;
                    if (equalPos(blockPos, componentPos)) { return; }
                }
            }
            components[mirror] = new ComponentList(anodeMirror.facing, blockPos, dThree, 10);
            if (type == 1){
                oneCount++;
            }else { twoCount++;}
        }else if (block == BlocksLoader.POWER){
            if (type == 1){
                isLinkPowerOne = true;
            }else { isLinkPowerTwo = true; }
        }
    }

    private boolean equalPos(BlockPos pos1, BlockPos pos2){
        if (pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY() && pos1.getZ() == pos2.getZ()){
            return true;
        }
        return false;
    }

    private void destruction(){
        multiComponent = null;
        shortPos = null;
        componentList = null;
        wirePos = null;
    }

    @Override
    public void update() {
        if (!world.isRemote){

        }
    }

}
