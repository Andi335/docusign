package com.forcont.digsigproto.common.api.features;

import com.forcont.digsigproto.common.api.Feature;

public class CovenantExpiration extends Feature
{
    private int expiresAfterDay;
    private int remindDaysBeforeExpiration;

    public CovenantExpiration(int expiresAfterDay, int remindDaysBeforeExpiration)
    {
        this.expiresAfterDay = expiresAfterDay;
        this.remindDaysBeforeExpiration = remindDaysBeforeExpiration;
    }

    public int getExpiresAfterDay()
    {
        return expiresAfterDay;
    }

    public void setExpiresAfterDay(int expiresAfterDay)
    {
        this.expiresAfterDay = expiresAfterDay;
    }

    public int getRemindDaysBeforeExpiration()
    {
        return remindDaysBeforeExpiration;
    }

    public void setRemindDaysBeforeExpiration(int remindDaysBeforeExpiration)
    {
        this.remindDaysBeforeExpiration = remindDaysBeforeExpiration;
    }
}
