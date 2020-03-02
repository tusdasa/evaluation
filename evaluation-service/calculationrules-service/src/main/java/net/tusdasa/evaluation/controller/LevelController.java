package net.tusdasa.evaluation.controller;

import net.tusdasa.evaluation.commons.CommonResponse;
import net.tusdasa.evaluation.entity.Level;
import net.tusdasa.evaluation.service.LevelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tusdasa
 * @Date: 2020-03-02 5:36 PM
 */

@RestController
public class LevelController {

    private LevelService levelService;

    public LevelController(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping("/")
    public CommonResponse<Level> findAll() {
        return new CommonResponse<Level>().table(this.levelService.findAll());
    }

    @GetMapping("/{id}")
    public CommonResponse<Level> findById(@PathVariable("id") Integer id) {
        Level level = this.levelService.findById(id);
        if (level != null) {
            return new CommonResponse<Level>().ok().data(level);
        }
        return new CommonResponse<Level>().error();
    }
}
