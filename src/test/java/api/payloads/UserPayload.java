package api.payloads;

import api.entitites.User;

public class UserPayload {

    public static String userPayload(User user) {
        return "{\n" +
                "  \"id\": " + user.getId() + ",\n" +
                "  \"username\": \"" + user.getUsername() + "\",\n" +
                "  \"firstName\": \"" + user.getFirstName() + "\",\n" +
                "  \"lastName\": \"" + user.getLastName() + "\",\n" +
                "  \"email\": \"" + user.getEmail() + "\",\n" +
                "  \"password\": \"" + user.getPassword() + "\",\n" +
                "  \"phone\": \"" + user.getPhone() + "\",\n" +
                "  \"userStatus\": " + user.getUserStatus() + "\n" +
                "}";
    }

    public static String userPayloadWithoutId(User user) {
        return "{\n" +
                "  \"username\": \"" + user.getUsername() + "\",\n" +
                "  \"firstName\": \"" + user.getFirstName() + "\",\n" +
                "  \"lastName\": \"" + user.getLastName() + "\",\n" +
                "  \"email\": \"" + user.getEmail() + "\",\n" +
                "  \"password\": \"" + user.getPassword() + "\",\n" +
                "  \"phone\": \"" + user.getPassword() + "\",\n" +
                "  \"userStatus\": " + user.getUserStatus() + "\n" +
                "}";
    }

    public static String userListPayload(User[] users) {
        StringBuilder body = new StringBuilder();
        for (User u : users) {
            body.append("{\n").append("  \"id\": ").append(u.getId()).append(",\n").append("  \"username\": \"").append(u.getUsername()).append("\",\n").append("  \"firstName\": \"").append(u.getFirstName()).append("\",\n").append("  \"lastName\": \"").append(u.getLastName()).append("\",\n").append("  \"email\": \"").append(u.getEmail()).append("\",\n").append("  \"password\": \"").append(u.getPassword()).append("\",\n").append("  \"phone\": \"").append(u.getPassword()).append("\",\n").append("  \"userStatus\": ").append(u.getUserStatus()).append("\n").append("}")
                    .append(",");
        }
        body.deleteCharAt(body.length()-1);
        return "[" + body + "]";
    }
}
