package com.forcont.digsigproto.common.controller.accessors.security;

/**
 * Common interface to access auth data.
 */
interface AuthDataAccessor
{
    boolean isAuthenticated();

    String getAccessToken();

    Object getAuthUser();
}
