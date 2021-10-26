package io.ollivander.ollivanderbackend.services;

import io.ollivander.ollivanderbackend.common.BaseException;

import java.util.List;

public interface StaffService {
    List<Object> list() throws BaseException;
}
