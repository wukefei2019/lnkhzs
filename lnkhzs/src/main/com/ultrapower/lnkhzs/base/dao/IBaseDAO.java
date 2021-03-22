package com.ultrapower.lnkhzs.base.dao;

import java.util.Collection;
import java.util.List;

import com.ultrapower.omcs.common.model.ICommonModel;

@SuppressWarnings("rawtypes")
public interface IBaseDAO {

    List<ICommonModel> findByExample(ICommonModel exampleEntity);

    void saveBatch(Collection entities);

    List<ICommonModel> find(String hql, Object[] params);



}