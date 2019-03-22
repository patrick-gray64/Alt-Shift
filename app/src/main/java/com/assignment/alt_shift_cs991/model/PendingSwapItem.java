package com.assignment.alt_shift_cs991.model;

public class PendingSwapItem {
    /**
     * Class for a Pending shiftSwap
     */
    private String name;
    private int index;

    /**
     * Constructor for PendingSwapItem
     */
    public PendingSwapItem() {
    }

    /**
     * Constructor for PendingSwapItem
     *
     * @param name
     */
    public PendingSwapItem(String name) {
        this.name = name;
    }

    /**
     * Getter for name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for index
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter for index
     *
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }
}

