package nl.novi.eindopdracht.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private EAuthority name;

    public Authority(EAuthority name) {
        this.name = name;
    }

    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public EAuthority getName() {
        return name;
    }

    public void setName(EAuthority name) {
        this.name = name;
    }
}