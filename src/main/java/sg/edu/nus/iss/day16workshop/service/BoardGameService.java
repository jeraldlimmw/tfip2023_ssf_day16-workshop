package sg.edu.nus.iss.day16workshop.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day16workshop.model.Mastermind;
import sg.edu.nus.iss.day16workshop.repository.BoardGameRepo;

@Service
public class BoardGameService {
    @Autowired
    private BoardGameRepo bgRepo;

    public int saveGame(final Mastermind md) {
        return bgRepo.saveGame(md);
    }

    public Mastermind findById(final String msId) throws IOException {
        return bgRepo.findById(msId);
    }

    public int updateBoardGame(final Mastermind ms){
        return bgRepo.updateBoardGame(ms);
    }
}
