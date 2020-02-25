package net.tusdasa.evaluation.service.imp;

import net.tusdasa.evaluation.dao.RightDao;
import net.tusdasa.evaluation.entity.Right;
import net.tusdasa.evaluation.service.RightService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RightServiceImpl implements RightService {

    private RightDao rightDao;

    public RightServiceImpl(RightDao rightDao) {
        this.rightDao = rightDao;
    }

    @Override
    public void addRight(Right right) {
        this.rightDao.insert(right);
    }

    @Override
    public void delRight(Integer id) {
        this.rightDao.deleteById(id);
    }

    @Override
    public void updateRight(Right right) {
        Right oldRight = this.rightDao.findById(right.getId()).get();
        if (oldRight != null) {
            oldRight.setFirstKpiId(right.getFirstKpiId());
            oldRight.setSecondKpiId(right.getSecondKpiId());
            oldRight.setThirdKpiId(right.getThirdKpiId());
            this.rightDao.save(oldRight);
        }
    }

    @Override
    public List<Right> findAll() {
        return this.rightDao.findAll();
    }

    @Override
    public Right findById(Integer id) {
        Right right = this.rightDao.findById(id).get();
        if (right == null) {
            return null;
        } else {
            return right;
        }
    }
}
