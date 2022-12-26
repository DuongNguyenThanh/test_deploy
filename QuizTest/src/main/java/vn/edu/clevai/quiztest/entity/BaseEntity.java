package vn.edu.clevai.quiztest.entity;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id ;

    @Column(name = "create_by",columnDefinition = "varchar(255) default 'admin'")
    private String createBy;

    @Column(name = "create_date",columnDefinition = "timestamp")
    private Date createDate;

    @Column(name = "modified_by",columnDefinition = "varchar(255) default 'admin'")
    private String modifiedBy;

    @Column(name = "modified_date",columnDefinition = "timestamp")
    private Date modifiedDate;

    public void saveCreateTime(String name) {
        this.createBy = name;
        this.createDate = new Timestamp(System.currentTimeMillis());

    }

    public void saveModifiedTime(String name) {
        this.modifiedBy = name ;
        this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }
}
