package com.fl.xumm4j.impl;

import com.fl.xumm4j.api.misc;
import com.fl.xumm4j.impl.ICredentials;

public class IPing implements misc {
    private ICredentials ic;

    public IPing(ICredentials iCredentials) {
        this.ic = iCredentials;
    }

    @Override
    public void ping() {

    }
}
