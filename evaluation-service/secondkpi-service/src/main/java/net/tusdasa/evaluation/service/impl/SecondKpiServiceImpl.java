package net.tusdasa.evaluation.service.impl;

import net.tusdasa.evaluation.dao.SecondKpiMapper;
import net.tusdasa.evaluation.entity.SecondKpi;
import net.tusdasa.evaluation.service.SecondKpiService;
import net.tusdasa.evaluation.vo.IdsRequest;
import net.tusdasa.evaluation.vo.SecondKpiRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class SecondKpiServiceImpl implements SecondKpiService {

    private SecondKpiMapper secondKpiMapper;

    public SecondKpiServiceImpl(SecondKpiMapper secondKpiMapper) {
        this.secondKpiMapper = secondKpiMapper;
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "allSecondKpi", allEntries = true),
                    @CacheEvict(value = "firstKpiId", allEntries = true),
                    @CacheEvict(value = "firstKpiIdAndSecondKpiIds", allEntries = true),
                    @CacheEvict(value = "firstKpiIdsAndSecondKpiIds", allEntries = true),
                    @CacheEvict(value = "firstKpiIds", allEntries = true)
            }
    )
    @Override
    public void addSecondKpi(SecondKpiRequest request) {
        this.secondKpiMapper.insert(request.build());
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "allSecondKpi", allEntries = true),
                    @CacheEvict(value = "firstKpiId", allEntries = true),
                    @CacheEvict(value = "firstKpiIdAndSecondKpiIds", allEntries = true),
                    @CacheEvict(value = "firstKpiIdsAndSecondKpiIds", allEntries = true),
                    @CacheEvict(value = "firstKpiIds", allEntries = true)
            }
    )
    @Transactional
    @Override
    public void updateSecondKpi(SecondKpiRequest request) {
        SecondKpi secondKpi = this.secondKpiMapper.selectByPrimaryKey(request.getFirstKpiId());
        if (secondKpi.compareTo(request) != 0) {
            this.secondKpiMapper.updateByPrimaryKeySelective(request.build());
        }
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "allSecondKpi", allEntries = true),
                    @CacheEvict(value = "firstKpiId", allEntries = true),
                    @CacheEvict(value = "firstKpiIdAndSecondKpiIds", allEntries = true),
                    @CacheEvict(value = "firstKpiIdsAndSecondKpiIds", allEntries = true),
                    @CacheEvict(value = "firstKpiIds", allEntries = true)
            }
    )
    @Transactional
    @Override
    public void deleteSecondKpi(Integer secondKpiId) {
        this.secondKpiMapper.deleteByPrimaryKey(secondKpiId);
    }

    @Override
    public SecondKpi findSecondKpiById(Integer secondKpiId) {
        return this.secondKpiMapper.selectByPrimaryKey(secondKpiId);
    }

    @Cacheable("allSecondKpi")
    @Transactional(readOnly = true)
    @Override
    public List<SecondKpi> findAll() {
        return this.secondKpiMapper.findAll();
    }

    @Cacheable(value = "firstKpiId", key = "methodName + #firstKpiId")
    @Transactional(readOnly = true)
    @Override
    public List<SecondKpi> findAllByFirstKpiId(Integer firstKpiId) {
        return this.secondKpiMapper.findAllByFirstKpiId(firstKpiId);
    }

    @Cacheable(value = "firstKpiIdAndSecondKpiIds", key = "methodName + #secondKpiIds")
    @Transactional(readOnly = true)
    @Override
    public List<SecondKpi> findAllByFirstKpiIdAndSecondKpiIds(Integer firstKpiId, IdsRequest secondKpiIds) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("firstKpiId", firstKpiId);
        parameter.put("secondKpiIds", secondKpiIds.getFirstArray());
        return this.secondKpiMapper.findAllByFirstKpiIdAndSecondKpiIds(parameter);
    }

    @Cacheable(value = "firstKpiIdsAndSecondKpiIds", key = "methodName + #Ids")
    @Transactional(readOnly = true)
    @Override
    public List<SecondKpi> finAllFirstKpiIdsAndSecondKpiIds(IdsRequest Ids) {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("firstKpiIds", Ids.getFirstArray());
        parameter.put("secondKpiIds", Ids.getSecondIdsArray());
        return this.secondKpiMapper.finAllFirstKpiIdsAndSecondKpiIds(parameter);
    }

    @Cacheable(value = "firstKpiIds", key = "methodName + #firstKpiIds")
    @Transactional(readOnly = true)
    @Override
    public List<SecondKpi> findAllByFirstKpiIds(IdsRequest firstKpiIds) {
        return this.secondKpiMapper.findAllByFirstKpiIds(firstKpiIds.getFirstArray());
    }
}
