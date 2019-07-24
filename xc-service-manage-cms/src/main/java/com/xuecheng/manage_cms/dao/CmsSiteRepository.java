package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Nick zhang
 * @Date: 2019/7/24 21:07
 */
@Repository
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {

}
