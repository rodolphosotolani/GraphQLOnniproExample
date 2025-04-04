package br.com.rts.graphqlonniproexample.session.domains;

import br.com.rts.graphqlonniproexample.event.domains.Event;
import br.com.rts.graphqlonniproexample.tag.domains.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany
    @ToString.Exclude
//    @JoinTable(
//            name = "session_tags",
//            joinColumns = @JoinColumn(name = "session_id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @ManyToOne
    @ToString.Exclude
    private Event event;

}
