package cs.ea.appointmentreservation.service;

import cs.ea.appointmentreservation.dao.IPersonRepository;
import cs.ea.appointmentreservation.dao.ISessionRepository;
import cs.ea.appointmentreservation.domain.Person;
import cs.ea.appointmentreservation.domain.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SessionService implements ISessionService{

    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private ISessionRepository sessionRepository;
    @Autowired
    private PersonDetailService personDetailService;

    @Override
    public Session createSession(Session session) {
        Person counselor = personDetailService.getCurrentPerson();
        session.setCounselor(counselor);
        System.out.println("SESSION IN SERVICE : " + session);
        try{
            return sessionRepository.save(session);
        }catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public Session getSession(long sessionId) {
        return sessionRepository.findById(sessionId).get();
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public List<Session> getCounselorSessions() {
        Person counselor = personDetailService.getCurrentPerson();
        return sessionRepository.findSessionByCounselor_Id(counselor.getId());
    }


    @Override
    public void deleteSession(long sessionId) {
        sessionRepository.deleteById(sessionId);
    }
}
