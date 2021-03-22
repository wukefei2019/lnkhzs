package com.ultrapower.lnkhzs.base.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ultrapower.eoms.common.core.dao.IDao;
import com.ultrapower.lnkhzs.base.dao.IBaseDAO;
import com.ultrapower.lnkhzs.base.service.IBaseService;
import com.ultrapower.omcs.common.model.ICommonModel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class BaseManager implements IBaseService{
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private IDao dao;
    
    private IBaseDAO baseDAO;
    
    public IDao getDao() {
        return dao;
    }
    
    public void setBaseDAO(IBaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    public void save(ICommonModel entity)  throws Exception {
        dao.save(entity);
    }
    
    public void saveBatch(List entities) throws Exception{
        baseDAO.saveBatch(entities);
    }
    
    public void update(ICommonModel entity) throws Exception{
        dao.saveOrUpdate(entity);
    }
    
    public ICommonModel getEntity(String pid) {
        return (ICommonModel) dao.get(pid);
    }
    
    public List findByExample(ICommonModel example) {
        return baseDAO.findByExample(example);
    }

    @Override
    public void delete(ICommonModel entity) {
        dao.remove(entity);
    }

    public IBaseDAO getBaseDAO() {
        return baseDAO;
    }

}
