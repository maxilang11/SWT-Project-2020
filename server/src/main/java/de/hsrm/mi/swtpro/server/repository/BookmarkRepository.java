package de.hsrm.mi.swtpro.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.hsrm.mi.swtpro.server.models.Bookmark;
@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark findById(int id);
}