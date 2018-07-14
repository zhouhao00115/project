package com.zhaopin.core.mapper;

import com.zhaopin.core.mapper.provider.MysqlProvider;
import com.zhaopin.core.model.RamanModel;
import com.zhaopin.core.model.RowsModel;
import com.zhaopin.core.model.msqlModel.BaseData;
import com.zhaopin.core.model.msqlModel.RamanUpdate;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MysqlMapper {
    @SelectProvider(method = "getQuerySql", type = MysqlProvider.class)
    List<RamanModel> allRaman(RowsModel rowsModel);

    @Update("update t_raman_data set t_raman_data.material_id_server=#{serverId}," +
            "t_raman_data.material_name_server=#{serverName}," +
            "t_raman_data.material_id_local=#{localId}," +
            "t_raman_data.material_name_local=#{localName}" +
            " where t_raman_data.device_id=#{deviceId} AND " +
            "t_raman_data.test_id=#{testId}")
    int updateRamanData(RamanUpdate model);


    @Select("select c_name as name,index_id as id from t_htna_chemical order by id asc limit #{start},#{rows}")
    List<BaseData> getBaseData(RowsModel rowsModel);
}
