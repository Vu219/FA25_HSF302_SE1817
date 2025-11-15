package javafx.demojavafxs3.service;

import javafx.demojavafxs3.entity.SonyCategories;
import javafx.demojavafxs3.repository.SonyCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class SonyCategoriesServiceImpl implements SonyCategoriesService {
    @Autowired
    private SonyCategoriesRepository sonyCategoriesRepository;

    @Override
    public boolean addCategory(SonyCategories category) {
        return sonyCategoriesRepository.save(category) != null;
    }

    @Override
    public boolean existSonyCategoriesByCateName(String name) {
        return sonyCategoriesRepository.findByCateName(name) != null;
    }

    @Override
    public SonyCategories findSonyCategoriesById(Integer id) {
        return sonyCategoriesRepository.findByCateID(id);
    }

    @Override
    public List<SonyCategories> getAllCategories() {
        List<SonyCategories> categories = sonyCategoriesRepository.findAll();
        return categories;
    }
}
