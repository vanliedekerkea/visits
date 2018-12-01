/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.deman.visits.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.deman.visits.model.Customer;
import org.springframework.deman.visits.model.Pet;
import org.springframework.deman.visits.model.PetType;
import org.springframework.deman.visits.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@SessionAttributes("pet")
public class PetController {

    private final ClinicService clinicService;


    @Autowired
    public PetController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.clinicService.findPetTypes();
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/customers/{customerId}/pets/new", method = RequestMethod.GET)
    public String initCreationForm(@PathVariable("customerId") int customerId, Map<String, Object> model) {
        Customer customer = this.clinicService.findCustomerById(customerId);
        Pet pet = new Pet();
        customer.addPet(pet);
        model.put("pet", pet);
        return "pets/createOrUpdatePetForm";
    }

    @RequestMapping(value = "/customers/{customerId}/pets/new", method = RequestMethod.POST)
    public String processCreationForm(@ModelAttribute("pet") Pet pet, BindingResult result, SessionStatus status) {
        new PetValidator().validate(pet, result);
        if (result.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        } else {
            this.clinicService.savePet(pet);
            status.setComplete();
            return "redirect:/customers/{customerId}";
        }
    }

    @RequestMapping(value = "/customers/*/pets/{petId}/edit", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        Pet pet = this.clinicService.findPetById(petId);
        model.put("pet", pet);
        return "pets/createOrUpdatePetForm";
    }

    @RequestMapping(value = "/customers/{customerId}/pets/{petId}/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public String processUpdateForm(@ModelAttribute("pet") Pet pet, BindingResult result, SessionStatus status) {
        // we're not using @Valid annotation here because it is easier to define such validation rule in Java
        new PetValidator().validate(pet, result);
        if (result.hasErrors()) {
            return "pets/createOrUpdatePetForm";
        } else {
            this.clinicService.savePet(pet);
            status.setComplete();
            return "redirect:/customers/{customerId}";
        }
    }

}
