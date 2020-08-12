package com.forcont.digsigproto.common.controller;

import com.docusign.esign.client.ApiException;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exception logging
 * TODO
 */
public class ErrorHandler
{
    private static void logAll(Exception e)
    {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "DIG_SIG_ERR", e);
    }

    public static void log(ApiException e)
    {
        ErrorHandler.logAll(e);
    }

    public static void log(IOException e)
    {
        ErrorHandler.logAll(e);
    }

    public static void log(HttpClientErrorException.BadRequest e)
    {
        ErrorHandler.logAll(e);
    }

    public static void log(MalformedURLException e) {
        ErrorHandler.logAll(e);
    }
}
