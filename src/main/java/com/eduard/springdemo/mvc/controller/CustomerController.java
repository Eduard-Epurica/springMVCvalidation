package com.eduard.springdemo.mvc.controller;

import com.eduard.springdemo.mvc.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    @GetMapping("/")
    public String showForm(Model theModel){

        theModel.addAttribute("customer", new Customer());

        return "customer-form";

    }

    @PostMapping("processForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer,
                              BindingResult theBindingResult){

        System.out.println("Last name: |" + theCustomer.getLastName() + "|");

        System.out.println("Binding results: " + theBindingResult.toString());

        System.out.println("\n\n\n\n");

        if(theBindingResult.hasErrors()){
            return "customer-form";
        }else {
            return "customer-confirmation";
        }

    }

    //InitBinder preprocesses all requests coming in from our controller.
    // remove leading and trailing whitespace
    //resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}