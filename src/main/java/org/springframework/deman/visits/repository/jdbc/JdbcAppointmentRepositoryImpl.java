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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.deman.visits.model.Appointment;
import org.springframework.deman.visits.repository.AppointmentRepository;
import org.springframework.deman.visits.repository.VisitRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 * A simple JDBC-based implementation of the {@link AppointmentRepository} interface.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 * @author Michael Isvy
 */
@Repository
public class JdbcAppointmentRepositoryImpl implements AppointmentRepository {

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert insertAppointment;

    @Autowired
    public JdbcAppointmentRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);

        this.insertAppointment = new SimpleJdbcInsert(dataSource)
                .withTableName("visits")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public void save(Appointment appointment) throws DataAccessException {
        if (appointment.isNew()) {
            Number newKey = this.insertAppointment.executeAndReturnKey(
                    createAppointmentParameterSource(appointment));
            appointment.setId(newKey.intValue());
        } else {
            throw new UnsupportedOperationException("Appointment update not supported");
        }
    }

    public void deleteCustomer(int id) throws DataAccessException {
        this.jdbcTemplate.update("DELETE FROM customers WHERE id=?", id);
    }


    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Appointment} instance.
     */
    private MapSqlParameterSource createAppointmentParameterSource(Appointment appointment) {
        return new MapSqlParameterSource()
                .addValue("id", appointment.getId())
                .addValue("appointment_date", appointment.getDate().toDate())
                .addValue("description", appointment.getDescription())
                .addValue("customer_id", appointment.getCustomer().getId());
    }

    @Override
    public List<Appointment> findByCustomerId(Integer customerId) {
        final List<Appointment> appointments = this.jdbcTemplate.query(
                "SELECT id, appointment_date, description FROM appointments WHERE customer_id=?",
                new ParameterizedRowMapper<Appointment>() {
                    @Override
                    public Appointment mapRow(ResultSet rs, int row) throws SQLException {
                        Appointment appointment = new Appointment();
                        appointment.setId(rs.getInt("id"));
                        Date appointmentDate = rs.getDate("appointment_date");
                        appointment.setDate(new DateTime(appointmentDate));
                        appointment.setDescription(rs.getString("description"));
                        return appointment;
                    }
                },
                customerId);
        return appointments;
    }

}
