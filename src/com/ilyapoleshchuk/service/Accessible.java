package com.ilyapoleshchuk.service;

import com.ilyapoleshchuk.exception.AccessUnavailableException;

public interface Accecible {

    void communicate(AccessType accessType) throws AccessUnavailableException;
}
