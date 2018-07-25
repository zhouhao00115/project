package com.zhaopin.core.mapper.provider;

import com.zhaopin.core.model.RowsModel;

public class MysqlProvider {
    public String getQuerySql(RowsModel rowsModel) {
        String buffer = ("select t_raman_data.device_id as deviceId,t_raman_data.test_id as testId," +
                "t_raman_data.material_id as materialId,t_raman_data.material_name as materialName," +
                "t_raman_data.material_id_server as materialIdServer," +
                "t_raman_data.material_name_server as materialNameServer," +
                "t_raman_data.material_name_local as materialNameLocal," +
                "t_raman_data.material_id_local as materialIdLocal," +
                "t_raman_data.wave_length as wave,t_raman_device.axialcalibration as axial," +
                "t_raman_detail_data.material_atlas as atlas from t_raman_data left join t_raman_device on " +
                "t_raman_data.device_id = t_raman_device.device_id left join " +
                "t_raman_detail_data on t_raman_data.device_id = t_raman_detail_data.device_id AND " +
                "t_raman_data.test_id = t_raman_detail_data.test_id where t_raman_data.create_time>'2018-06-01 00:00:00' AND t_raman_detail_data.material_atlas !='' order by t_raman_data.create_time desc limit #{start},#{rows}");
        return buffer;
    }
}

