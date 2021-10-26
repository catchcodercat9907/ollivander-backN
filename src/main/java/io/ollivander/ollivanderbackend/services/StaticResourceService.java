package io.ollivander.ollivanderbackend.services;

import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.model.entities.Account;
import io.ollivander.ollivanderbackend.model.entities.AccountPermission;
import io.ollivander.ollivanderbackend.model.entities.Feature;
import io.ollivander.ollivanderbackend.model.repos.FeatureRepository;
import io.ollivander.ollivanderbackend.utils.SecurityContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StaticResourceService {

    @Autowired
    private FeatureRepository featureRp;

    @Transactional(readOnly = true)
    public List<Feature> getListFeature() throws BaseException {

        Account current = SecurityContextHelper.getCurrentAccount();
        List<Feature> features = featureRp.findAll();


        return features;
    }
}
