package com.bigtou.umbrella.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bigtou.umbrella.bean.ApkFileInfo;

@Repository
public interface FileRepository extends JpaRepository<ApkFileInfo, String> {

}
