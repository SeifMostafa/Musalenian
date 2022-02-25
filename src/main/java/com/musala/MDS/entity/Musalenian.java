package com.musala.MDS.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Musalenian {
    String name,workplace,email,phoneNumber,password, techExperience,focusExperience,education;
    Long id;
    Date birthdate;
}
