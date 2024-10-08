package org.cash.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @Getter
    @Column(nullable = false)
    private String cif;

    @NotNull
    @Getter @Setter
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotNull
    @Getter @Setter
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotNull
    @Getter @Setter
    @Past
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @Getter @Setter
    @Email
    @Column(nullable = false, unique = true)
    private String email;

}
