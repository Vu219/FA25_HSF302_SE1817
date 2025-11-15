package sum25.se193604.service;

import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyCategories;

import java.util.List;

@Service
public interface SonyCategoriesService {
    boolean add(SonyCategories sonyCategories);
    SonyCategories findById(Integer id);
    List<SonyCategories> getAll();
}
