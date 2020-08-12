package com.forcont.digsigproto.common.model;

import java.awt.*;

public class SignPosition
{
    private final Point location;
    private final int pageNumber;

    public SignPosition(Point location, int pageNumber)
    {
        this.location = location;
        this.pageNumber = pageNumber;
    }

    public Point getLocation()
    {
        return location;
    }

    public int getPageNumber()
    {
        return pageNumber;
    }
}
