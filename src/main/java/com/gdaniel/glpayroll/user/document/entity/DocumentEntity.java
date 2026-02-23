package com.gdaniel.glpayroll.user.document.entity;

import java.time.LocalDateTime;

import com.gdaniel.glpayroll.user.employee.entitiy.EmployeeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//72d697a38ff74b5ca9e23f3b6b7c2813
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tblb44ff2a82efc4884a5ecde9721980e55")
public class DocumentEntity {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LONG") // Configures the primary
    @Column(name = "cola721e3947c6944c0be6233ce8440d4c0")
    private Long documentId;

    @Column(name = "col72d697a38ff74b5ca9e23f3b6b7c2813", nullable = false, length = 100)
    private String alias;

    @Column(name = "col8f1340ed8d6e45b79b6fff6268e038f9", nullable = false, length = 100)
    private String url;

    @Column(name = "col2a82b930f7ef43a98769de1192aa4faf", nullable = false)
    private LocalDateTime datecreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "col4295b1e1b2f64aeb94a65790155834df", nullable = false)
    private EmployeeEntity employee;

    @Column(name = "colf542028b3aa848c28b60571a4f35a2a8", nullable = false, length = 100)
    private String originalFileName;

    @Column(name = "colc2424910c9b444f5c811bded6a6aba105", nullable = false, length = 45)
    private String mimeType;

}
