package football.service;

public interface Transferable {
    String transfer(Long senderId, Long receiverId, Long playerId);
}
