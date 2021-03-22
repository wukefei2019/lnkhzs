package com.ultrapower.lnkhzs.base.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ultrapower.omcs.common.model.ICommonModel;

@SuppressWarnings("unchecked")
public class BaseDAOImpl extends HibernateDaoSupport implements IBaseDAO {

    public void saveBatch(Collection entities) {
        this.getHibernateTemplate().saveOrUpdateAll(entities);
    }

    public List<ICommonModel> find(final String hql, final Object[] params) {
        return this.getHibernateTemplate().find(hql, params);
    }

    public List<ICommonModel> findByExample(ICommonModel exampleEntity) {
        return this.getHibernateTemplate().findByExample(exampleEntity);
    }

}
