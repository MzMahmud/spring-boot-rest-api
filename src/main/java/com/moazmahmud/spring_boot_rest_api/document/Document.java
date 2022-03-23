package com.moazmahmud.spring_boot_rest_api.document;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_document")
    @SequenceGenerator(name = "seq_document", sequenceName = "seq_document", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "unique_file_name")
    private String uniqueFileName;

    @Column(name = "content_type")
    private String contentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_container_id", foreignKey = @ForeignKey(name = "fk_document_container_id"))
    private DocumentContainer documentContainer;
}