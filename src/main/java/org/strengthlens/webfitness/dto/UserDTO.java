package org.strengthlens.webfitness.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO { //transfer the data between client and server
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
