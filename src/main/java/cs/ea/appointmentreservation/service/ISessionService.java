package cs.ea.appointmentreservation.service;

import cs.ea.appointmentreservation.domain.Session;

import java.util.List;

public interface ISessionService {
    Session createSession(Session session);
    Session getSession(long sessionId);
    List<Session> getAllSessions();
    List<Session> getCounselorSessions();
    void deleteSession(long sessionId);
}
