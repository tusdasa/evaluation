package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.ThirdKpiMapper;
import net.tusdasa.evaluation.entity.ThirdKpi;
import net.tusdasa.evaluation.service.ThirdKpiService;
import net.tusdasa.evaluation.vo.IdsRequest;
import net.tusdasa.evaluation.vo.ThirdKpiRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class ThirdKpiServiceImpl implements ThirdKpiService {

    private ThirdKpiMapper thirdKpiMapper;

    public ThirdKpiServiceImpl(ThirdKpiMapper thirdKpiMapper) {
        this.thirdKpiMapper = thirdKpiMapper;

    }

    @Caching(
            evict = {
                    @CacheEvict(value = "findThirdKpiById", allEntries = true),
                    @CacheEvict(value = "findAll", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiId", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiIdAndThirdKpiIds", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiId", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiIdAndThirdKpiIds", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiIds", allEntries = true)
            }
    )
    @Transactional
    @Override
    public void addThirdKpi(ThirdKpiRequest request) {
        this.thirdKpiMapper.insert(request.build());
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "findThirdKpiById", allEntries = true),
                    @CacheEvict(value = "findAll", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiId", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiIdAndThirdKpiIds", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiId", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiIdAndThirdKpiIds", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiIds", allEntries = true)
            }
    )
    @Transactional
    @Override
    public void updateThirdKpi(ThirdKpiRequest request) {
        ThirdKpi thirdKpi = this.thirdKpiMapper.selectByPrimaryKey(request.getThirdKpiId());
        if (thirdKpi.compareTo(request) != 0) {
            this.thirdKpiMapper.updateByPrimaryKeySelective(request.build());
        }
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "findThirdKpiById", allEntries = true),
                    @CacheEvict(value = "findAll", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiId", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiIdAndThirdKpiIds", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiId", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiIdAndThirdKpiIds", allEntries = true),
                    @CacheEvict(value = "findAllBySecondKpiIds", allEntries = true)
            }
    )
    @Transactional
    @Override
    public void deleteThirdKpi(Integer thirdKpiId) {
        this.thirdKpiMapper.deleteByPrimaryKey(thirdKpiId);
    }

    @Cacheable(value = "findThirdKpiById", key = "methodName + #thirdKpiId")
    @Transactional(readOnly = true)
    @Override
    public ThirdKpi findThirdKpiById(Integer thirdKpiId) {
        return this.thirdKpiMapper.selectByPrimaryKey(thirdKpiId);
    }

    @Cacheable(value = "findAll")
    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findAll() {
        return this.thirdKpiMapper.findAll();
    }

    @Cacheable(value = "findAllBySecondKpiId", key = "methodName + #secondKpiId")
    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findAllBySecondKpiId(Integer secondKpiId) {
        return this.thirdKpiMapper.findAllBySecondKpiId(secondKpiId);
    }

    @Cacheable(value = "findAllBySecondKpiIdAndThirdKpiIds", key = "methodName + #secondKpiId + #thirdKpiIds")
    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findAllBySecondKpiIdAndThirdKpiIds(Integer secondKpiId, IdsRequest thirdKpiIds) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("secondKpiId", secondKpiId);
        parameter.put("thirdKpiIds", thirdKpiIds.getFirstArray());
        return this.thirdKpiMapper.findAllBySecondKpiIdAndThirdKpiIds(parameter);
    }

    @Cacheable(value = "findAllBySecondKpiIdsAndThirdKpiIds", key = "methodName + #thirdKpiIds")
    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findAllBySecondKpiIdsAndThirdKpiIds(IdsRequest Ids) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("thirdKpiIds", Ids.getSecondIdsArray());
        parameter.put("secondKpiIds", Ids.getFirstArray());
        return this.thirdKpiMapper.findAllBySecondKpiIdsAndThirdKpiIds(parameter);
    }

    @Cacheable(value = "findAllBySecondKpiIds", key = "methodName + #secondKpiIds")
    @Transactional(readOnly = true)
    @Override
    public List<ThirdKpi> findAllBySecondKpiIds(IdsRequest secondKpiIds) {
        return this.thirdKpiMapper.findAllBySecondKpiIds(secondKpiIds.getFirstArray());
    }
}
