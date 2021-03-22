package com.ultrapower.omcs.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2015年12月16日
 * <p>Title		 : 移动代维系统_[子系统统名]_[模块名]</p>
 * <p>Description: [滚动翻页结果集合]</p>
 * @author		 : 佟广恩 tongguangen@ultrapower.com.cn
 * @version      : 1.0
 */
/*--------------------------------------------------------------
 *修改履历： 1) [修改时间] [修改人] [修改说明]；
 *--------------------------------------------------------------
 */
public class RollingResult<E>  extends com.ultrapower.eoms.common.core.component.data.DataTable {

    public RollingResult() {
        super("RollingResult");
    }
    
    protected String lastrecord;
    
    protected boolean hasMore = false;
    
    protected List<E> entities = new ArrayList<E>();

    public String getLastrecord() {
        return lastrecord;
    }

    public void setLastrecord(String lastrecord) {
        this.lastrecord = lastrecord;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<E> getEntities() {
        return entities;
    }

    public void setEntities(List<E> entities) {
        this.entities = entities;
    }



}
