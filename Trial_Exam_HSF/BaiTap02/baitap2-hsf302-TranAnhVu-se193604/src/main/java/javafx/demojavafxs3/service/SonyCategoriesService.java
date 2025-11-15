package javafx.demojavafxs3.service;

import javafx.demojavafxs3.entity.SonyCategories;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface SonyCategoriesService {
    public boolean addCategory(SonyCategories category);
    public boolean existSonyCategoriesByCateName (String name);
    public SonyCategories findSonyCategoriesById(Integer id);
    public List<SonyCategories> getAllCategories();
}
