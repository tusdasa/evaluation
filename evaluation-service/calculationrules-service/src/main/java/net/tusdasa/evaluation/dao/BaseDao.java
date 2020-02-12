package net.tusdasa.evaluation.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface BaseDao<Model, PK extends Serializable> {
    /**
     * 按主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(PK id);

    /**
     * 添加
     *
     * @param record
     * @return
     */

    int insert(Model record);

    /**
     * 按选定的条件删除
     *
     * @param record
     * @return
     */

    int insertSelective(Model record);

    /**
     * @param id
     * @return
     */
    Model selectByPrimaryKey(PK id);

    /**
     * 按条件更新
     *
     * @param record
     * @return
     */

    int updateByPrimaryKeySelective(Model record);

    /**
     * 按主键更新
     *
     * @param record
     * @return
     */

    int updateByPrimaryKey(Model record);

    /**
     * 找到所有
     *
     * @return 返回集合
     */

    List<Model> findAll(Map<String, Integer> parameter);

}

