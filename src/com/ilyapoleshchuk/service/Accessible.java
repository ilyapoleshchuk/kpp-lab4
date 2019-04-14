package com.ilyapoleshchuk.service;

import com.ilyapoleshchuk.exception.AccessUnavailableException;

public interface Accessible {

    void communicate(AccessType accessType) throws AccessUnavailableException;
}
