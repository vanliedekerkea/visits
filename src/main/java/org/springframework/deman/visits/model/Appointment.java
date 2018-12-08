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
package org.springframework.deman.visits.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Simple JavaBean domain object representing an appointment.
 *
 * @author Arno Van Liedekerke
 */
@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {

    /**
     * Holds value of property date.
     */
    @Column(name = "appointment_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private DateTime dateTime;

    /**
     * Holds value of property description.
     */
    @NotEmpty
    @Column(name = "description")
    private String description;

    /**
     * Holds value of property customer.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    /**
     * Creates a new instance of Appointment for the current date
     */
    public Appointment() {
        this.dateTime = new DateTime();
    }


    /**
     * Getter for property dateTime.
     *
     * @return Value of property dateTime.
     */
    public DateTime getDate() {
        return this.dateTime;
    }

    /**
     * Setter for property dateTime.
     *
     * @param date New value of property dateTime.
     */
    public void setDate(DateTime date) {
        this.dateTime = date;
    }

    /**
     * Getter for property description.
     *
     * @return Value of property description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for property description.
     *
     * @param description New value of property description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for property customer.
     *
     * @return Value of property customer.
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Setter for property customer.
     *
     * @param customer New value of property customer.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
