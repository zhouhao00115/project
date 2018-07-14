package com.zhaopin.core.dao;

import com.zhaopin.core.model.RamanModel;
import com.zhaopin.core.model.RowsModel;
import com.zhaopin.core.model.msqlModel.BaseData;
import com.zhaopin.core.model.msqlModel.RamanUpdate;

import java.util.List;

public interface RamanDao {
    public List<RamanModel> allRaman(RowsModel rowsModel);

    public List<BaseData> getBaseData(RowsModel rowsModel);

    public int updateOrders(RamanUpdate model);
}
