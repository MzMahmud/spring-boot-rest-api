package com.moazmahmud.spring_boot_rest_api.document;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "document_container")
public class DocumentContainer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_document_container")
    @SequenceGenerator(name = "seq_document_container", sequenceName = "seq_document_container", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "documentContainer", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Document> documents = new HashSet<>();
}