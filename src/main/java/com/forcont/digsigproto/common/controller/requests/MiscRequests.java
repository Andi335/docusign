package com.forcont.digsigproto.common.controller.requests;

import com.forcont.digsigproto.common.config.EndPoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MiscRequests
{

    private final EndPoints endPoints;

    @Autowired
    public MiscRequests(EndPoints endPoints)
    {
        this.endPoints = endPoints;
    }


    @GetMapping(EndPoints.Json.LINKS)
    public Map<String, String> pageIndex() throws NoSuchFieldException
    {
        Map<String, String> links = new HashMap<>();
        for (Field field : this.endPoints.getLinks().keySet())
        {
            try
            {
                links.put(this.endPoints.getLinks().get(field), this.endPoints.getWithContextPath((String) field.get(new Object())));
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        return links;
    }
}
