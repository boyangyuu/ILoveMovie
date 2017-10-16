package com.example.yuboyang.ilovemovie1.listing.sort;

/**
 * @author BoyangYu
 */
public enum SortType
{
    MOST_POPULAR(0), HIGHEST_RATED(1), FAVORITES(2);

    public static final String PREFER = "prefer";
    private final int value;

    SortType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
