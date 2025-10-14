package sum25.se193604.service;

import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyCategories;

import java.util.List;

@Service
public interface SonyCategoriesService {
    public boolean addCategory(SonyCategories category);
    public boolean existSonyCategoriesByCateName (String name);
    public SonyCategories findSonyCategoriesById(Integer id);
    public List<SonyCategories> getAllCategories();
}
