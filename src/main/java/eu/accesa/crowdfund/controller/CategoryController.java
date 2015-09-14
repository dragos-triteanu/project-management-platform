package eu.accesa.crowdfund.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller class for managing the categories for the ADMINISTRATOR user..
 */
@Controller
@RequestMapping("categories")
public class CategoryController {
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllCategories(){
        boolean myVal = true;

        ModelAndView categoryPage = new ModelAndView();
        categoryPage.setViewName("categories");
        categoryPage.addObject("myVal", myVal);

        List<String> myList = new ArrayList<>(Arrays.asList("yo","shi","tu"));
        categoryPage.addObject("myList", myList);

        return categoryPage;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createCategory(@RequestParam("name") final String name){

        LOG.info("Creating category with categoryName="+name);
        boolean myVal = true;
        ModelAndView categoryPage = new ModelAndView();
        categoryPage.setViewName("categories");
        categoryPage.addObject("myVal", myVal);

        List<String> myList = new ArrayList<>(Arrays.asList("yo","shi","tu"));
        categoryPage.addObject("myList", myList);
        return categoryPage;
    }



}
