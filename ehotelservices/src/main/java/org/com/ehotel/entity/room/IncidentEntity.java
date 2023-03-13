package org.com.ehotel.entity.room;

import lombok.Getter;
import lombok.Setter;
import org.com.ehotel.entity.user.EmployeeEntity;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/12/2023, Sunday
 **/

@Entity
@Table(name = "incident")
@Getter @Setter
public class IncidentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incident_id", unique = true)
    private Integer id;

    @Column(name = "incident_description")
    private String description;

    @Column(name ="incident_date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "room_id", name = "room_id")
    private RoomEntity room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "employee_nas", name = "reporter_id")
    private EmployeeEntity reporter;
}
