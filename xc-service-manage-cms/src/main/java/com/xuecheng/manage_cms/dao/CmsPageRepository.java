package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Nick zhang
 * @Date: 2019/7/21 21:23
 */
@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {

}
