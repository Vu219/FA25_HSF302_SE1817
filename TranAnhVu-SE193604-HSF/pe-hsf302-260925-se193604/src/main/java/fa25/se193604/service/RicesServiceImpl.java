package fa25.se193604.service;

import fa25.se193604.entity.Rices;
import fa25.se193604.repository.AccountsRepository;
import fa25.se193604.repository.RicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RicesServiceImpl implements RicesService {
    private final RicesRepository ricesRepository;


    @Override
    public boolean add(Rices product) {
        return ricesRepository.save(product) != null;
    }

    @Override
    public List<Rices> getAll() {
        return ricesRepository.findAll();
    }

    @Override
    public Rices getById(int id) {
        return ricesRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        Rices rices = ricesRepository.findById(id).orElse(null);
        if(rices != null) {
            ricesRepository.delete(rices);
        }
    }

    @Override
    public void update(int id, Rices sonyProducts) {

    }
}
