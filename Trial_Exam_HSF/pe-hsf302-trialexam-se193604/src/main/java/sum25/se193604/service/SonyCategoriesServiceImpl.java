package sum25.se193604.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyCategories;
import sum25.se193604.repository.SonyCategoriesRepository;

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
