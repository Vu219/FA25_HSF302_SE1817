package sum25.se193604.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sum25.se193604.entity.SonyCategories;
import sum25.se193604.repository.SonyCategoriesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SonyCategoriesServiceImpl implements SonyCategoriesService {
    private final SonyCategoriesRepository sonyCategoriesRepository;

    @Override
    public boolean add(SonyCategories sonyCategories) {
        return sonyCategoriesRepository.save(sonyCategories) != null;
    }

    @Override
    public SonyCategories findById(Integer id) {
        return sonyCategoriesRepository.findById(id).get();
    }

    @Override
    public List<SonyCategories> getAll() {
        return sonyCategoriesRepository.findAll();
    }
}
