package com.gdaniel.glpayroll.user.employee.entitiy;

import java.time.LocalDateTime;
import java.util.List;

import com.gdaniel.glpayroll.admin.employeestatus.entity.EmployeeStatusEntity;
import com.gdaniel.glpayroll.admin.ratetype.entity.RateTypeEntity;
import com.gdaniel.glpayroll.user.document.entity.DocumentEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl6651fa10bc634af1946fd09c19543004")
public class EmployeeEntity {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LONG") // Configures the primary
    @Column(name = "col4295b1e1b2f64aeb94a65790155834df")
    private Long employeeId;

    @Column(name = "col265665ff52084b81a911bfc3c9a55cee", nullable = false, length = 45)
    private String firstName;

    @Column(name = "col6ea3fe0a1d424174a712f54b12701982", nullable = false, length = 45)
    private String lastName;

    @Column(name = "col5cf54160a7694658a306d51e6fce663a", nullable = true, length = 45)
    private String middleName;

    @ManyToOne
    @JoinColumn(name = "col17127604a30e483e808eb0de8dbaf599", nullable = false)
    private EmployeeStatusEntity employeeStatus;

    @Column(name = "col8054e99747404f09a57a528d1e4edcb7")
    private LocalDateTime dateHired;

    @Column(name = "col004afe28a8b14cc68e09bf23f5e0bbbe")
    private LocalDateTime dateTerminated;

    @Column(name = "col92a0e9d0a5d40518ec6186831f4c833", nullable = false, length = 11)
    private String ssn;

    @Column(name = "col7bf4a0bcca3d45adbb77f43bd8244ebf", nullable = true, length = 15)
    private String telephoneLandline;

    @Column(name = "col200a17dfe4ba43eeba016ee91e0fab28", nullable = true, length = 15)
    private String telephoneCell;

    @Column(name = "colca95d1a9fe89b40ee9dd04f88b95122ae", nullable = false, length = 100)
    private String email;

    @Column(name = "col6b6806ba8a0a4257a87364b816e1dd45")
    private double rate;

    @Column(name = "colbeb4fed78b184676ada90a36598a1546", nullable = false, length = 100)
    private String title;

    @Column(name = "col59de268d68e847769bdca0e7ba1da66d")
    private long companyId;

    @Column(name = "col267f74369670435b8e97633834c361af", nullable = false, length = 50)
    private String employeeNumber;

    @ManyToOne
    @JoinColumn(name = "col7c58f73208714685b70bc4ec07ef553e", nullable = false) //
    // Specifies the foreign key column in the
    private RateTypeEntity rateType;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentEntity> documents = new java.util.ArrayList<>();
}
