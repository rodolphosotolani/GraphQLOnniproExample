package br.com.rts.graphqlonniproexample.tag.domains;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_sequence_generator")
    @SequenceGenerator(name = "tag_sequence_generator", sequenceName = "id_tag_sequence")
    private Integer id;

    private String name;

}
