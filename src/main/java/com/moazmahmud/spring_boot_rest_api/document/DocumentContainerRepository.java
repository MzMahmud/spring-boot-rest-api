package com.moazmahmud.spring_boot_rest_api.document;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentContainerRepository extends JpaRepository<DocumentContainer, Long> {
}