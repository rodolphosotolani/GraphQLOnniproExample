package br.com.rts.graphqlonniproexample.country.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String emoji;

    private String currency;

    private String code;

    private String capital;

    private String awsRegion;
}
