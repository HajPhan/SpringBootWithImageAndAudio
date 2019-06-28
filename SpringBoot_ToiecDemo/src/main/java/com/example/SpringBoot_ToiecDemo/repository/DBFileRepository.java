package com.example.SpringBoot_ToiecDemo.repository;

import com.example.SpringBoot_ToiecDemo.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
}
