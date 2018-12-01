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
package org.springframework.deman.visits.repository.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.deman.visits.model.Customer;
import org.springframework.deman.visits.model.Pet;
import org.springframework.deman.visits.model.PetType;
import org.springframework.deman.visits.model.Visit;
import org.springframework.deman.visits.repository.CustomerRepository;
import org.springframework.deman.visits.repository.VisitRepository;
import org.springframework.deman.visits.util.EntityUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

/**
 * A simple JDBC-based implementation of the {@link CustomerRepository} interface.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 */
@Repository
public class JdbcCustomerRepositoryImpl implements CustomerRepository {

    private VisitRepository visitRepository;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertCustomer;

    @Autowired
    public JdbcCustomerRepositoryImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                   VisitRepository visitRepository) {

        this.insertCustomer = new SimpleJdbcInsert(dataSource)
                .withTableName("customers")
                .usingGeneratedKeyColumns("id");

        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.visitRepository = visitRepository;
    }


    /**
     * Loads {@link Customer Customers} from the data store by last name, returning all customers whose last name <i>starts</i> with
     * the given name; also loads the {@link Pet Pets} and {@link Visit Visits} for the corresponding customers, if not
     * already loaded.
     */
    @Override
    public Collection<Customer> findByLastName(String lastName) throws DataAccessException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("lastName", lastName + "%");
        List<Customer> customers = this.namedParameterJdbcTemplate.query(
                "SELECT id, first_name, last_name, address, city, telephone FROM customers WHERE last_name like :lastName",
                params,
                ParameterizedBeanPropertyRowMapper.newInstance(Customer.class)
        );
        loadCustomersPetsAndVisits(customers);
        return customers;
    }

    /**
     * Loads the {@link Customer} with the supplied <code>id</code>; also loads the {@link Pet Pets} and {@link Visit Visits}
     * for the corresponding customer, if not already loaded.
     */
    @Override
    public Customer findById(int id) throws DataAccessException {
        Customer customer;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            customer = this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT id, first_name, last_name, address, city, telephone FROM customers WHERE id= :id",
                    params,
                    ParameterizedBeanPropertyRowMapper.newInstance(Customer.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Customer.class, id);
        }
        loadPetsAndVisits(customer);
        return customer;
    }

    public void loadPetsAndVisits(final Customer customer) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", customer.getId().intValue());
        final List<JdbcPet> pets = this.namedParameterJdbcTemplate.query(
                "SELECT id, name, birth_date, type_id, customer_id FROM pets WHERE customer_id=:id",
                params,
                new JdbcPetRowMapper()
        );
        for (JdbcPet pet : pets) {
            customer.addPet(pet);
            pet.setType(EntityUtils.getById(getPetTypes(), PetType.class, pet.getTypeId()));
            List<Visit> visits = this.visitRepository.findByPetId(pet.getId());
            for (Visit visit : visits) {
                pet.addVisit(visit);
            }
        }
    }

    @Override
    public void save(Customer customer) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(customer);
        if (customer.isNew()) {
            Number newKey = this.insertCustomer.executeAndReturnKey(parameterSource);
            customer.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                    "UPDATE customers SET first_name=:firstName, last_name=:lastName, address=:address, " +
                            "city=:city, telephone=:telephone WHERE id=:id",
                    parameterSource);
        }
    }

    public Collection<PetType> getPetTypes() throws DataAccessException {
        return this.namedParameterJdbcTemplate.query(
                "SELECT id, name FROM types ORDER BY name", new HashMap<String, Object>(),
                ParameterizedBeanPropertyRowMapper.newInstance(PetType.class));
    }

    /**
     * Loads the {@link Pet} and {@link Visit} data for the supplied {@link List} of {@link Customer Customers}.
     *
     * @param customers the list of customers for whom the pet and visit data should be loaded
     * @see #loadPetsAndVisits(Customer)
     */
    private void loadCustomersPetsAndVisits(List<Customer> customers) {
        for (Customer customer : customers) {
            loadPetsAndVisits(customer);
        }
    }


}
