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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.joda.time.DateTime;
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
@SessionAttributes("appointment")
public class AppointmentController {

    private final ClinicService clinicService;


    @Autowired
    public AppointmentController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public String getAll(Map<String, Object> model) {
    	model.put("selections", this.clinicService.findAppointments());
    	return "appointments/appointmentsList";
    }
    
    @RequestMapping(value = "/appointments-week", method = RequestMethod.GET)
    public String getAppointmentsWeek(Map<String, Object> model) {
    	DateTime currentDateTime = new DateTime(new Date());
    	Collection<Date> daysOfWeek = new ArrayList<>();
    	for(int i = 0; i < 7; i++) {
    		Date dayOfWeek = new Date(currentDateTime.withDayOfWeek(i + 1).getMillis());
    		daysOfWeek.add(dayOfWeek);
    	}
    	model.put("daysOfWeek", daysOfWeek);
    	return "appointments/appointmentsList/week";
    }
    
}
