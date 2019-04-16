package ivg.cn.sharding.jdbc.springboot.example.common;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author: lj
 * @description:
 * @date: 10:55 2018/7/23
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface MyMapper<T> extends Mapper<T> {
    /**
     * 批量插入
     * @param recordList
     * @return
     */
//    @Options(useGeneratedKeys = false, keyProperty = "fid")
    @InsertProvider(type = SpecialCustomIdProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);
}
