package com.springboot.controller;

import com.springboot.model.Feature;
import com.springboot.service.UserService;
import javafx.application.Application;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ManageUsersController {

    @Autowired
    private UserService userService;

    /**
     * @author navi
     * @param featureName name of the feature
     * @param email email address
     * @return
     */

    @RequestMapping(value = "/feature", method = RequestMethod.GET)
    Map<String, String> getFeature(@RequestParam String featureName,@RequestParam String email){
        HashMap<String, String> map = new HashMap<>();
       List<Feature> l=userService.findByFeatureNameAndEmail(featureName,email);
        l.stream().forEach(a-> map.put("canAccess",a.getEnable()));
        return map;

    }

    /**
     * @author Nßavi
     * @param feature lsit of feature values to save in to db
     * @return Success
     */
    @RequestMapping(value = "/feature", method = RequestMethod.POST)
    String addFeature(@RequestBody Feature feature){
        Feature savedFeature = userService.save(feature);
        return "SUCCESS";
    }

}
