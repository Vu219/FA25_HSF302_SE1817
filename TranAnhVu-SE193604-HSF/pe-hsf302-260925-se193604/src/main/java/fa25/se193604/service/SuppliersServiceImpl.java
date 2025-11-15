package fa25.se193604.service;

import fa25.se193604.entity.Suppliers;
import fa25.se193604.repository.SuppliersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SuppliersServiceImpl implements SuppliersService {
    private final SuppliersRepository suppliersRepository;

    @Override
    public boolean add(Suppliers suppliers) {
        return suppliersRepository.save(suppliers) != null;
    }

    @Override
    public Suppliers findById(int id) {
        return suppliersRepository.findById(id).get();
    }

    @Override
    public List<Suppliers> getAll() {
        return suppliersRepository.findAll();
    }
}
