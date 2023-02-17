package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;
    private Iterator<Block> iterator = blocks.iterator();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return Optional.ofNullable(getFirstBlockOfGivenColor(color));
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return getAllBlocksOfGivenMaterial(material);
    }

    @Override
    public int count() {
        while(iterator.hasNext()){
            Block block = iterator.next();
            if(isCompositeBlock(block)) {
                blocks.addAll(((CompositeBlock) block).getBlocks());
            }
        }
        return blocks.size();
    }
    private List<Block> getAllBlocksOfGivenMaterial(String material) {
        List<Block> blocksOfGivenMaterial = new ArrayList<>();

        while(iterator.hasNext()){
            Block block = iterator.next();
            if(isCompositeBlock(block)) {
                blocks.addAll(((CompositeBlock) block).getBlocks());
            }
            else if (block.getMaterial().equals(material)) {
                blocksOfGivenMaterial.add(block);
            }
        }
        return blocksOfGivenMaterial;
    }

    private Block getFirstBlockOfGivenColor(String color) {
        while(iterator.hasNext()){
            Block block = iterator.next();
            if(isCompositeBlock(block)) {
                blocks.addAll(((CompositeBlock) block).getBlocks());
            }
            else if (block.getColor().equals(color)) {
                return block;
            }
        }
        return null;
    }

    private boolean isCompositeBlock(Block block){
        return(block instanceof CompositeBlock);
    }
}
