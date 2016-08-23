package com.coller.clibrary.repository;

import com.coller.clibrary.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long>{

    List<Keyword> findKeywordByKeywordContainingIgnoreCase(String keyword);
}
