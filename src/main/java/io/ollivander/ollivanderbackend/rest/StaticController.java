package io.ollivander.ollivanderbackend.rest;

import io.ollivander.ollivanderbackend.common.BaseException;
import io.ollivander.ollivanderbackend.model.dto.ResponseObject;
import io.ollivander.ollivanderbackend.model.entities.Feature;
import io.ollivander.ollivanderbackend.services.StaticResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/static")
public class StaticController {

    @Autowired
    private StaticResourceService staticResourceService;

    @RequestMapping(value = "/feature", method = RequestMethod.GET)
    @ResponseBody
    public final ResponseEntity<Object> getListFeature() throws BaseException {
        ResponseObject<Object> response = new ResponseObject<Object>();
        List<Feature> result = staticResourceService.getListFeature();
        response.setResponseData(result);
        return new ResponseEntity<Object>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
