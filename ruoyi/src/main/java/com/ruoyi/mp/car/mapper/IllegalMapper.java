package com.ruoyi.mp.car.mapper;

import com.ruoyi.mp.car.domain.CarInfo;
import com.ruoyi.mp.car.domain.IllegaDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 调度任务日志信息 数据层
 * 
 * @author ruoyi
 */
@Mapper
public interface IllegalMapper
{
    /**
     * 查询违章信息
     *
     * @param lsprefix
     * @param lsnum
     * @return 违章信息
     */
    public List<IllegaDetail> selectIllegaDetail(@Param("lsprefix")String lsprefix,@Param("lsnum")String lsnum);
    public CarInfo getCarInfo(CarInfo carInfo);
    public int insertCarInfo(CarInfo carInfo);
    public int deleteIllegaDetailList(CarInfo carInfo);
    public int insertIllegaDetailList(@Param("illegaList")List<IllegaDetail> illegaList,@Param("lsprefix")String lsprefix,@Param("lsnum")String lsnum);
    public int addCarQuery(CarInfo carInfo);

    public int processIllegas(@Param("IllegaNumbers")String[] IllegaNumbers);
    public int unProcessIllegas(@Param("IllegaNumbers")String[] IllegaNumbers);
    public int completeIllegas(@Param("IllegaNumbers")String[] IllegaNumbers);
    public int backMonyCarIllega(@Param("IllegaNumbers")String[] IllegaNumbers);
    public List<IllegaDetail> queryIllegaDetailOrder(@Param("param")Map data);
    public List<CarInfo> queryIllegaCars(@Param("param")Map data);
}
