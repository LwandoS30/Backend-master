package com.relink.backend.service.implementation;

import com.relink.backend.model.Recruiter;
import com.relink.backend.record.CreateRecruiterRequest;
import com.relink.backend.repository.RecruiterRepository;
import com.relink.backend.service.RecruiterService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruiterRepository recruiterRepository;

    public RecruiterServiceImpl(RecruiterRepository recruiterRepository) {
        this.recruiterRepository = recruiterRepository;
    }

    @Override
    public Recruiter createRecruiter(CreateRecruiterRequest request) {
        if(recruiterRepository.existsByEmail(request.email().toLowerCase().trim())) {
            throw new IllegalArgumentException("Email already inuse");
        }
        if(request.username()!=null&&recruiterRepository.existsByUsername(request.username())){
            throw new IllegalArgumentException("Username already Exist");
        }

        Recruiter recruiter=new Recruiter();
        recruiter.setName(request.name());
        recruiter.setSurname(request.surname());
        recruiter.setUsername(request.username());
        recruiter.setEmail(request.email());

        return recruiterRepository.save(recruiter);


    }

    @Override
    public void deleteRecruiter(UUID id) {
        Recruiter recruiter=recruiterRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("Recruiter not found"));
        recruiterRepository.delete(recruiter);

    }

    public Recruiter getRecruiterById(UUID id){

        return recruiterRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("Recruiter not found"));
    }
}
