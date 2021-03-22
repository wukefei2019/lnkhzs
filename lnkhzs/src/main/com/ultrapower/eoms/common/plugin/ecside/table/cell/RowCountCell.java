/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ultrapower.eoms.common.plugin.ecside.table.cell;

import com.ultrapower.eoms.common.plugin.ecside.core.TableModel;
import com.ultrapower.eoms.common.plugin.ecside.core.bean.Column;

/**
 * Display the current row number.
 * 
 * @author Jeff Johnston
 */
public class RowCountCell extends AbstractCell {
    @Override
	protected String getCellValue(TableModel model, Column column) {
        int rowcount = ((model.getLimit().getPage() - 1) 
                * model.getLimit().getCurrentRowsDisplayed()) 
                + model.getRowHandler().getRow().getRowCount();
        return String.valueOf(rowcount);
    }
}
