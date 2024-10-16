package com.luv2code.springboot.mvc.validationdemo;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        //Null görene kadar boşluk kırpar
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/show")
    public String showForm(Model model){
        Customer customer=new Customer();
        model.addAttribute("customer",customer);
        return "customerForm";
    }

    @PostMapping("/processForm")
    public String ProcessForm(@Valid @ModelAttribute("customer") Customer customer,
                              BindingResult bindingResult){
        //bindingresult doğrulama sonuçlarını tutuyor
        if(bindingResult.hasErrors()){
            return "customer-form";
        }
        else{
            return "customer-confirmation";
        }
}
}
